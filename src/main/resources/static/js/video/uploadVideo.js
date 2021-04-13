$(document).ready(function(){
    var i=0;
    /*add——创建tbx下的div加文字和变宽度的方法*/
    function add(i,name){
        if(i>=100){
            $(".tiao").html("ok").fadeIn("slow");
            $(".box").fadeOut(1500);
            var alertHTML="<div class='alert alert-success alert-dismissable'><strong>"+name+"上传成功</strong></div>";
            $("body").append(alertHTML);
            $(".alert").on("animationend",function () {
                $(this).remove();
            });
        }
        var tbox =$(".tbox");
        var tiao =$(".tiao");
        tiao.css("width",i+"%").html(i+"%");
    }
    $("button[data-toggle='popover']").popover({
        trigger: 'click',
        title: "Uploading...",
        html:true ,
        container:'.panel',
        placement: 'left',
        content: function () {
            return "<button class='btn btn-success btn-sm add btn-file'>" +
                "<i class='fa fa-plus'></i><input type='file' accept='video/*' name='videoFiles' multiple></button>"
        }
    });
    $("a[data-toggle='popover']").popover({
        trigger: 'click',
        title: "Set New",
        html:true ,
        container:'.panel',
        placement: 'right',
        content: function () {
            return "<div style='width:80%'><input style='width:90%;display:inline' name='newCategory' type='text' placeholder='please input a new category' class='form-control'>" +
                "<a class='btn btn-sm btn-success' style='float:right;margin-top:3px;margin-bottom:3px' onclick='newCategory()'><i class='fa fa-check'></i></a></div>";
        }
    });
    newCategory = function () {
        var val = $("input[name='newCategory']").val();
        var formData = new FormData();
        formData.append("category",val);
        $.ajax({
            type:"POST",
            url:"/addCategory/video",
            data:formData,
            contentType: false,
            processData: false,
            async:true,
            success:function(){
                var innerhtml = "<li><a href='#' data-filter='."+ val+"' >"+val+"</a></li>";
                $("#filters").append(innerhtml);
                $("select[name='category']").append("<option value='"+val+"'>"+val+"</option>");
                var $container = $('#gallery');
                $container.isotope({
                    itemSelector: '.item',
                    animationOptions: {
                        duration: 750,
                        easing: 'linear',
                        queue: false
                    }
                });
                // filter items when filter link is clicked
                $('#filters a').click(function () {
                    var selector = $(this).attr('data-filter');
                    $container.isotope({filter: selector});
                    return false;
                });
                $("[data-toggle='popover']").popover('hide')
            },
            error:function(){
                alert("error");
            }
        });
    };
    $("button:first").bind("click",function () {
        var Ealt = new Eject();
        var video_formdata = new FormData();
        var cate_formdata = new FormData();
        if($("#gallery div").hasClass("delete")){
            for(var video=0; video<$("#gallery div").length; video++){
                if($($("#gallery div")[video]).hasClass("choose")){
                    video_formdata.append("delVideo",$($("#gallery div img")[video]).attr("video_id"));
                    video_formdata.append("delVideoName",$($("#gallery div img")[video]).attr("name"));
                }
            }
            for(var cate=0;cate<$("#filters li:gt(0) a").length;cate++){
                if($($("#filters li:gt(0) a")[cate] ).hasClass("choose_category")){
                    cate_formdata.append("delCate",$($("#filters li:gt(0) a")[cate]).text());
                }
            }
            if(cate_formdata.getAll("delCate").length>0){
                $.ajax({
                    type:"post",
                    url:"/delCategory/video",
                    data:cate_formdata,
                    contentType: false,
                    processData: false,
                    async:false,
                    success:function () {
                        $("#filters li a[class='choose_category']").remove();
                    },
                    error:function () {
                        alert("error")
                    }
                });
            }
            if(video_formdata.getAll("delVideo").length>0){
                $.ajax({
                    type:"post",
                    url:"/video/deleteVideo",
                    data:video_formdata,
                    contentType: false,
                    processData: false,
                    async:true,
                    success:function () {
                        Ealt.Ealert({
                            title:'反馈信息',
                            message:'删除成功'
                        });
                    },
                    error:function () {
                        Ealt.Ealert({
                            title:'反馈信息',
                            message:'删除失败！！！'
                        });
                    }
                });
            }
            $("#gallery div").removeClass("delete");
            $("#gallery div").removeClass("choose");
            $("#filters li:gt(0) a").removeClass("del_category");
            $("#filters li:gt(0) a").removeClass("choose_category");
            var $container = $('#gallery');
            $container.isotope({
                itemSelector: '.item',
                animationOptions: {
                    duration: 750,
                    easing: 'linear',
                    queue: false
                }
            });
            // filter items when filter link is clicked
            $('#filters li:gt(0) a').click(function () {
                var selector = $(this).attr('data-filter');
                $container.isotope({filter: selector});
                return false;
            });
            $("#gallery a").attr("data-toggle","modal");
            $("button:first").html("<i class='fa fa-trash-o'></i> Delete");
            $("a[data-toggle='popover']").show();
        }
        else{
            $("button:first").html("<i class='fa fa-check'></i> OK");
            $("a[data-toggle='popover']").hide();
            $("#filters li:gt(0) a").addClass("del_category");
            $("#filters li:gt(0) a").unbind();
            $("#filters li:gt(0) a").bind("click",function () {
                if($(this).hasClass("del_category")){
                    $(this).removeClass("del_category");
                    $(this).addClass("choose_category");
                }
                else if($(this).hasClass("choose_category")){
                    $(this).removeClass("choose_category");
                    $(this).addClass("del_category");
                }
            });

            $("#gallery div").addClass("delete");
            $("#gallery a").removeAttr("data-toggle");
        }
    });
    $("#gallery div").bind("click",function(){
        if($(this).hasClass("delete")){
            $(this).removeClass("delete");
            $(this).addClass("choose");
        }
        else if($(this).hasClass("choose")){
            $(this).removeClass("choose");
            $(this).addClass("delete");
        }
    });
    //视频上传并获取上传进度
    var Ealt = new Eject();
    $( ".add input[type='file']" ).live("change", function(){
        var innerHTML = "<div class='box'><div class='tbox'>"+
            "<div class='tiao'></div></div></div>";
        var formFile = new FormData();  //得到表单
        var fileList = this.files;
        var length = fileList.length;
        $.each(fileList, function (index,file){
            $("div[class='popover-content']").append(innerHTML);
            formFile.append('myVideos', file);
            $.ajax({
                url:  '/video/uploadVideos',
                type:  'POST',
                Accept: 'text/html;charset=UTF-8' ,
                cache:  false ,
                contentType: "multipart/form-data" ,
                data:formFile,
                processData:  false ,
                contentType:  false ,
                async:true,
                xhr: function(){
                    myXhr = $.ajaxSettings.xhr();
                    if (myXhr.upload){  // check if upload property exists
                        myXhr.upload.addEventListener( 'progress' , function(e){
                            var  loaded = e.loaded; //已经上传大小情况
                            var  tot = e.total; //附件总大小
                            var  per =  Math .floor( 100 *loaded/tot);  //已经上传的百分比
                            add(per,file.name);
                        },false );  // for handling the progress of the upload
                    }
                    return  myXhr;
                },
                success:function(){
                    console.log("上传成功");
                },
                error:function(){
                    console.log("上传失败!!!");
                }
            });
            formFile.delete("myVideos");
        });
        setTimeout(function () {
            window.location.reload();
        },length*500);
    });

});

