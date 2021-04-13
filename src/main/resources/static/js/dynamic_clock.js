//define dynamic time
$(document).ready(function(){
    var hour = 0;
    minute = 0;
    second = 0;
    function setTime() {
        var initdate = new Date();
        if(second<59){
            second+=1;
        }
        else{
            second = 0;
            if(minute<59){
                minute+=1;
            }
            else{
                minute = 0;
                hour+=1;
            }
        }
        initdate.setSeconds(second);
        initdate.setMinutes(minute);
        initdate.setHours(hour);
        var mm = initdate.getMinutes();
        ss = initdate.getSeconds()
        if(initdate.getSeconds()<10){
            ss = "0"+initdate.getSeconds();
        }
        if(initdate.getMinutes()<10){
            mm = "0"+initdate.getMinutes();
        }
       if(hour%2==0&&hour!=0){
            alert("你已经学习两个小时了休息一下");
        }
        $('.green-txt').text(initdate.getHours() + ":" + mm + ":" + ss);
    }
    setInterval(setTime,1000);
});