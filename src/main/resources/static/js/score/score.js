$(document).ready(function () {
    setDate = function(obj){
        var innerHtml =  "<input type='date' name='uplaod_date' style='width:100px' autofocus>"
        $(obj).html(innerHtml);
    };

    setDiscription = function(obj){
        var innerHtml = "<textarea name='description' rows='3' autofocus/>";
        $(obj).html(innerHtml);
    };

    setSubject = function(obj){
        var innerHtml = "<input name='subject' type='text' autofocus/>";
        $(obj).html(innerHtml);
    };

    setScore = function(obj){
        var innerHtml = "<input name='score' type='number' autofocus/>";
        $(obj).html(innerHtml);
    };

    setType = function(obj){
        var innerHtml = "<input name='type' type='text' autofocus/>";
        $(obj).html(innerHtml);
    };
    Date.prototype.format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };
//图片展示
    function addNewContent(obj,imgName,imgSrc) {
        $(obj).append('<li class="content-img-list-item "><img class="size" src="' + imgSrc+imgName+ '" alt="'+imgName+'" >' +
            '<div class="hide"><a class="delete-btn"><i class="gcl gcllajitong"></i></a>' + '<a class="big-btn" type="button" data-toggle="modal" data-target="#bs-example-modal-lg"><i class="gcl gclfangda"></i></a></div></li>');
    }

    $(".timeline").on("click",".close",function () {
        var cutPoint = $(this).parents("article");
        var score_id = $(cutPoint).find("input[name='id']").attr("value");
        var formdata = new FormData();
        formdata.append("score_id",score_id);
        $.ajax({
            type:"post",
            url:"/score/deleteScoreInfo",
            data:formdata,
            async:true,
            cache:false,
            processData:false,
            contentType:false,
            success:function () {
                $(cutPoint).remove();
            },
            error:function () {
                console.log("error");
            }
        });
    });

    $(".timeline").on("click",".panel .timeline-icon",function () {
        var date = new Date().format("yyyy-MM-dd");
        var formdata = new FormData();
        var cutPoint = $(this).parents("article");
        formdata.append("upload_date", date);
        $.ajax({
            type:"post",
            url:"/score/setNewScore",
            data: formdata,
            async:true,
            cache : false,
            processData : false,
            contentType : false,
            dataType:'json',
            success:function (data) {
                var dir = "left";
                var color = "red";
                var arrow = "arrow-alt";
                if($(cutPoint).hasClass("left")){
                    dir = "right";
                    color = "green";
                    arrow = "arrow";
                }
                var innerHtml = "<article class='timeline-item "+dir+"'><div class='timeline-desk'>" +
                    "<div class='panel'><div class='panel-body'>" +
                    "<span class='"+arrow+"'></span>" +
                    "<span class='timeline-icon'>+</span>" +
                    "<a class='close'>×</a>" +
                    "<h1 class='"+color+" timeline-date' name='date'>"+ date+"</h1>" +
                    "<div class='info'><input class='hide' type='text' name='id' value='"+ data+"'>" +
                    "<h2 ondblclick='setType(this)'><input name='type' type='text' autofocus/></h2>"+
                    "<p ondblclick='setDiscription(this)'>Dclick for description...</p>" +
                    "<h2 ondblclick='setSubject(this)'>Dclick for subject...</h2> "+
                    "<h2 ondblclick='setScore(this)'>Dclick for score...</h2>"+
                    "</div><div class='upload-content'>" +
                    "<div class='content-img'>" +
                    "<ul class='content-img-list'></ul>" +
                    "<div class='file'>" +
                    "<i class='fa fa-plus'></i>" +
                    "<input type='file' name='image_file' accept='image/*' multiple>" +
                    "</div></div></div></div></div></div></article>";
                if($(cutPoint).nextAll("article").html()===undefined){
                    $(".timeline").append(innerHtml);
                }
                else{
                    $(cutPoint).next().before(innerHtml);
                }
            }
        });
    });
    $(".timeline").on("blur","textarea, input[name='discription'], input[name='subject'], input[name='score'], input[name='type']",function () {
        var cutPoint = $(this);
        var info = $(this).val();
        if(info==""){
            $(this).parent().html("<span class='red'>Dclick for type...</span>");
            return;
        }
        var score = {};
        score["id"] = $(this).parents(".info").find("input[class='hide']").val();
        if($(this).attr("name")=="upload_date"){
            score["upload_date"] = info;
        }
        else if($(this).attr("name")=="description"){
            score["description"] = info;
        }
        else if($(this).attr("name")=="subject"){
            score["subject"] = info;
        }
        else if($(this).attr("name")=="score"){
            score["score"] = info;
        }
        else{
            score["type"] = info;
        }
        $.ajax({
            type:"post",
            url:"/score/updateScore",
            async:true,
            cache : false,
            dataType:'json',
            data:{"score":JSON.stringify(score)},
            success:function () {
                console.log("Complete Setting...");
                $(cutPoint).parent().html(info);
            },
            error:function(){
                console.log("error");
            }
        })
    });

    // 鼠标经过显示删除按钮
    $('.timeline').on('mouseover', '.content-img-list-item', function() {
        $(this).children('div').removeClass('hide');
    });
    // 鼠标离开隐藏删除按钮
    $('.timeline').on('mouseleave', '.content-img-list-item', function() {
        $(this).children('div').addClass('hide');
    });
    // 单个图片删除
    $(".timeline").on("click", '.content-img-list-item a .gcllajitong', function() {
        var formdata = new FormData();
        var boxImg = $(this).parents(".content-img-list-item");
        var image_name = $(boxImg).find("img").attr("alt");
        var score_id =  $(this).parents("article").find("input[name='id']").val();
        formdata.append("image_name", image_name);
        formdata.append("score_id",score_id);
        $.ajax({
            type:"post",
            url:"/score/deleteScoreImage",
            async:true,
            cache:false,
            contentType:false,
            processData:false,
            data:formdata,
            success:function(){
                $(boxImg).remove();
                console.log("Completely delete...");
            },
            error:function () {
                console.log("error");
            }
        });
    });
    $(".timeline").on("click",".content-img-list-item a .gclfangda",function() {
        var imgSrc = $(this).parents(".content-img-list-item").find("img").attr("src");
        $(".modal-dialog").append('<div id="temp" class="modal-content"><div class="show"><img src="' + imgSrc + '" alt=""></div></div>');
        $("#showModal").modal("show");
        $(".show img").on('dblclick',function () {
            $("#showModal").modal("hide");
            $("#temp").remove();
        });
    });
    //图片上传
    $(".timeline").on('change',"input[type='file']", function(e) {
        var Ealt = new Eject();
        var imgBox = $(this).parents(".content-img").find(".content-img-list");
        var fileList = this.files;
        for(var index=0; index<fileList.length; index++){
            var imgSize = fileList[index].size;
            var imgType = fileList[index].type;
            if (imgSize > Math.pow(2,20)) { //1M
                Ealt.Ealert({
                    title:'反馈信息',
                    message:'第'+ (index+1) +'个图片不能超过1MB'
                });
                return;
            }
            if (imgType!='image/png'&&imgType!='image/jpeg'&&imgType!='image/gif') {
                Ealt.Ealert({
                    title:'反馈信息',
                    message:'第'+ (index+1) +'个图片上传格式目前不支持'
                });
                return;
            }
            var formdata = new FormData();
            var score_id =  $(this).parents("article").find("input[name='id']").val();
            formdata.append("file",fileList[index]);
            formdata.append("score_id",score_id);
            $.ajax({
                type:"post",
                url:"/score/uploadScoreImage",
                data:formdata,
                async:false,
                cache:false,
                processData:false,
                contentType:false,
                success:function () {
                    var imgName = fileList[index].name;
                    var imgSrc = "/images/score/image/";
                    addNewContent(imgBox,imgName,imgSrc);
                    this.value = null; //上传相同图片
                }
            });
        }
    });
});