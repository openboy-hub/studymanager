$().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
    $("#setIndividualForm").validate({
        rules: {
            "user.userName":{
                required:true,
                checkuserName:true
            },
            realname:{
                required:true,
                checkrealname:true
            },
            birthday:{
                required:true
            },
            age: {
                required:true
            },
            sex:{
                required:true
            },
            phone:{
                checkphone:true
            },
            email: {
                required: true,
                email: true
            }
        },
        messages: {
            "user.userName":{
                required:"请输入用户名"
            },
            sex:{
                required:"请交代一下你的性别"
            },
            birthday:{
                required:"请选择你的出生日期"
            },
            age: {
                required:"请选择你的年龄"
            },
            email: {
                required: "请输入电子邮件",
                email: "请输入正确的电子邮件"
            }
        },
        focusInvalid:true,
        errorClass:"error",
        success:function (lable) {
            lable.prev("input").after("<span class='success'>OK</span>")
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
    $.validator.addMethod("checkrealname",function(value,element,param){
        var regex=/^[^\x00-\xff]{2,3}$/;
        return this.optional(element)||(regex.test(value));
    },"请输入正确的真实姓名格式");
    $.validator.addMethod("checkuserName",function(value,element,param){
        var regex=/^[A-Z][a-z][0-9]{6,}$/;
        return this.optional(element)||(regex.test(value));
    },"用户名要求同时包含1位大，写字母开头及6位以上数字");
    $.validator.addMethod("checkphone",function(value,element){
        var regex=/^1[2-9]{10}$/;
        return this.optional(element)||(regex.test(value));
    },"请输入正确的手机格式");
});