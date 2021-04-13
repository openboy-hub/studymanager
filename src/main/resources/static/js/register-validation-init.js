$().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
    $("#register_form").validate({
        rules: {
            userName:{
                required:true,
                checkuserName:true
            },
            passWord: {
                required:true,
                checkpassWord:true
            },
            repassWord: {
                required: true,
                equalTo: "#password"
            },
            Email: {
                required: true,
                email: true
            },
            agree: "required"
        },
        messages: {
            userName:{
                required:"请输入用户名"
            },
            passWord: {
                required:"请输入密码"
            },
            repassWord: {
                required: "请再次输入密码",
                equalTo: "密码要求一致"
            },
            Email: {
                required: "请输入电子邮件",
                email: "请输入正确的电子邮件"
            },
            agree:"你必须同意协议"
        },
        focusInvalid:true,
        errorClass:"error",
        success:function (lable) {
            lable.prev("input").after("<span class='success'>OK</span>")
        },
        submitHandler: function(form) {
            form.submit();
        },
        /*errorPlacement: function(error, element) {
            error.appendTo(element.prev("p"));
        }*/
    });
    $.validator.addMethod("checkuserName",function(value,element,param){
        var regex=/^[A-Z][a-z][0-9]{6,}$/;
        return this.optional(element)||(regex.test(value));
    },"用户名要求同时包含1位大，写字母开头及6位以上数字");
    $.validator.addMethod("checkpassWord",function(value,element,param) {
        var regex = /^[A-Za-z]\d{6,}$/;
        return this.optional(element) || (regex.test(value));
    },"大写或小写字母开头及6个以上数字");
});