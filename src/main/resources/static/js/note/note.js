$(document).ready(function () {
   $(".editor .fa-trash-o").on("click",function () {
       $(this).parents(".editor").find("textarea").data("wysihtml5").editor.setValue('');
   });
   $(".editor .fa-plus").on("click",function () {
      var innerHtml ="<div class='col-xs-6'>" +
          "                <section class='panel'>" +
          "                    <header class='panel-heading'>" +
          "                        Note Files" +
          "                        <span class='tools pull-right'>" +
          "                            <a class='fa fa-chevron-down' href='javascript:;'></a>" +
          "                            <a class='fa fa-times' href='javascript:;'></a>" +
          "                        </span>" +
          "                    </header>" +
          "                    <div class='panel-body'>" +
          "                        <div class='form'>" +
          "                            <form action='#' class='form-horizontal'>" +
          "                                <div class='form-group'>" +
          "                                    <div class='upload-content'>" +
          "                                        <div class='content-img'>" +
          "                                            <ul class='content-img-list'>" +
          "                                                <li class='content-img-list-item'>" +
          "                                                    <input class='hide' name='id' value=\"1\">" +
          "                                                    <i class='btn btn-sm btn-info fa fa-share tool'></i>" +
          "                                                    <i class='btn btn-sm btn-success fa fa-edit tool'></i>" +
          "                                                    <i class='btn btn-sm btn-danger fa fa-trash-o tool'></i>" +
          "                                                </li>" +
          "                                            </ul>" +
          "                                            <div class='file'>" +
          "                                                <i class='fa fa-plus'></i>" +
          "                                                <input type='file' name='image_file' accept=\".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel, .pdf\" multiple>\n" +
          "                                            </div>" +
          "                                        </div>" +
          "                                    </div>" +
          "                                </div>" +
          "                            </form>" +
          "                        </div>" +
          "                    </div>" +
          "                </section>" +
          "            </div>";
      $(".files").append(innerHtml);
   });
   $(".files").on("click",".content-img-list-item .fa-trash-o",function () {
      $(this).parent().remove();
   });

   $(".editor .fa-save").on("click",function () {
       var content = $(".editor textarea").val();
       console.log(content);
   });
});