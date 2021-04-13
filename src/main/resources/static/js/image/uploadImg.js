var imgFile = []; //文件流
var imgSrc = []; //图片路径
var imgName = []; //图片名字
$(function() {
    // 鼠标经过显示删除按钮
    $('.content-img-list').on('mouseover', '.content-img-list-item', function() {
        $(this).children('div').removeClass('hide');
    });
    // 鼠标离开隐藏删除按钮
    $('.content-img-list').on('mouseleave', '.content-img-list-item', function() {
        $(this).children('div').addClass('hide');
    });
    // 单个图片删除
    $(".content-img-list").on("click", '.content-img-list-item a .gcllajitong', function() {
        var index = $(this).parents(".content-img-list-item").index();
        imgSrc.splice(index, 1);
        imgFile.splice(index, 1);
        imgName.splice(index, 1);
        var boxId = ".content-img-list";
        addNewContent(boxId);
        if (imgSrc.length < 4) { //显示上传按钮
            $('.content-img .file').show();
        }
    });
    $(".content-img-list").on("click",".content-img-list-item a .gclfangda",function() {
        var index = $(this).parent().parent().parent().index();
        $("#upLoadModal .modal-content").addClass('hide');
        $(".modal-dialog").append('<div id="temp" class="modal-content"><div class="show"><img src="' + imgSrc[index] + '" alt=""></div></div>');
        $(".show img").on('dblclick',function () {
            $("#temp").remove();
            $("#upLoadModal .modal-content").removeClass('hide');
        });
    });
});
//图片上传
$('#upload').on('change', function(e) {
    var Ealt = new Eject();
    var imgBox = '.content-img-list';
    var fileList = this.files;
    for(var index=0; index<fileList.length; index++){
        var imgSize = fileList[index].size;
        var imgType = fileList[index].type;
        if (imgSize > Math.pow(2,20)) { //1M
            Ealt.Ealert({
                title:'反馈信息',
                message:'第'+ (index+1) +'个图片不能超过1MB'
            });
        }
        if (imgType!='image/png'&&imgType!='image/jpeg'&&imgType!='image/gif') {
            Ealt.Ealert({
                title:'反馈信息',
                message:'第'+ (index+1) +'个图片上传格式目前不支持'
            });
        }
    }
    for (var i = 0; i < fileList.length; i++) {
        var imgSrcI = getObjectURL(fileList[i]);
        imgName.push(fileList[i].name);
        imgSrc.push(imgSrcI);
        imgFile.push(fileList[i]);
    }
    addNewContent(imgBox);
    this.value = null; //上传相同图片
});

//提交请求
$('#upLoadModal button[type="submit"]').on('click', function() {
    // FormData上传图片
    var Ealt = new Eject();
    var formFile = new FormData();
    // 遍历图片imgFile添加到formFile里面
    $.each(imgFile, function(i, file) {
        formFile.append('myFile', file);
    });
    console.log(formFile.getAll("myFile"));
    $.ajax({
        type:"POST",
        url:"/image/uploadPhotos",
        async:false,
        cache : false,
        processData : false,
        contentType : false,
        dataType:'json',
        data:formFile,
        success:function () {
            window.alert=function(){
                Ealt.Ealert({
                    title:'反馈信息',
                    message:'上传成功'
                })
            };
            $("#upLoadModal").modal("hide");
        },
        error:function(xhr){
            if(xhr.status==200){
                Ealt.Ealert({
                    title:'反馈信息',
                    message:'上传成功'
                });
                $("#upLoadModal").modal("hide");
            }else{
                Ealt.Ealert({
                    title:'反馈信息',
                    message:'上传失败！！！'
                })
            }
        }
    });
    imgSrc = [];imgFile = [];imgName = [];
    addNewContent(".content-img-list");
});

//图片展示
function addNewContent(obj) {
    // console.log(videoSrc)
    $(obj).html("");
    for (var a = 0; a < imgSrc.length; a++) {
        var oldBox = $(obj).html();
        $(obj).html(oldBox + '<li class="content-img-list-item "><img class="size" src="' + imgSrc[a] + '" alt="'+imgName[a]+'" >' +
            '<div class="hide"><a index="' + a + '" class="delete-btn"><i class="gcl gcllajitong"></i></a>' +
            '<a index="' + a + '" class="big-btn" type="button" data-toggle="modal" data-target="#bs-example-modal-lg"><i class="gcl gclfangda"></i></a></div></li>');
    }
}

//建立可存取到file的url
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

$("[data-toggle='popover']").popover({
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
       url:"/addCategory/image",
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
    var img_formdata = new FormData();
    var cate_formdata = new FormData();
    if($("#gallery div").hasClass("delete")){
        for(var img=0;img<$("#gallery div").length;img++){
            if($($("#gallery div")[img]).hasClass("choose")){
                img_formdata.append("delImg",$($("#gallery div img")[img]).attr("image_id"));
                img_formdata.append("delImgName",$($("#gallery div img")[img]).attr("name"))
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
                url:"/delCategory/image",
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
        if(img_formdata.getAll("delImg").length>0){
            $.ajax({
                type:"post",
                url:"/image/deleteImage",
                data:img_formdata,
                contentType: false,
                processData: false,
                async:true,
                success:function () {
                    Ealt.Ealert({
                        title:'反馈信息',
                        message:'删除成功'
                    })
                },
                error:function () {
                    if(xhr.status==200){
                        Ealt.Ealert({
                            title:'反馈信息',
                            message:'删除成功'
                        });
                        $("#upLoadModal").modal("hide");
                    }else{
                        Ealt.Ealert({
                            title:'反馈信息',
                            message:'删除失败！！！'
                        })
                    }
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
