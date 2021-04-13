$(document).ready(function () {
  $("div[id^='play']").bind("show.bs.modal", function () {
    $("video").removeAttr("id");
    var play_video = $(this).find("video")[0];//获取视频
    $(play_video).attr("id","playing");
    var videoflag = true;
    var video = document.getElementById("playing");
    var currentTime = $(video).parent().find(".currentTime");
    var playprogress = $(video).parent().find(".playprogress");
    var play = $(video).parent().find(".play");
    var _switch = $(video).parent().find(".switch");
    var enlarge = $(video).parent().find(".enlarge");
    var time = $(video).parent().find(".time");
    var speed = $(video).parent().find(".speed");
    var volume_div = $(video).parent().find(".volume");
    var controls = $(video).parent();
    var video_duration = video.duration;   //获取视频总时长  (vd  video duration)
    vd_m = Math.floor(video_duration / 60);   //得到分钟
    vd_s = Math.floor(video_duration % 60);   //得到秒
    vd_h = Math.floor(video_duration / 3600);
    vd_h = vd_h >= 10 ? vd_h : "0" + vd_h;
    vd_m = vd_m >= 10 ? vd_m : "0" + vd_m;    //当结果为一位数时，在前面加一个字符0
    vd_s = vd_s >= 10 ? vd_s : "0" + vd_s;
    $(this).find(".duration").text(vd_h+ ":" + vd_m + ":" + vd_s);
    video.addEventListener("timeupdate", function () {         //视频播放事件
      vc_m = Math.floor(this.currentTime / 60);
      vc_s = Math.floor(this.currentTime % 60);
      vc_h = Math.floor(this.currentTime / 3600);
      vc_h = vc_h >= 10 ? vc_h : "0" + vc_h;
      vc_m = vc_m >= 10 ? vc_m : "0" + vc_m;
      vc_s = vc_s >= 10 ? vc_s : "0" + vc_s;
      $(currentTime).text(vc_h + ":" + vc_m + ":" + vc_s);
      $(playprogress).width(this.currentTime / video_duration * 100 + "%")   //更改进度条遮罩的尺寸
    });

    video.onended  = function(){   //视频播放完成事件
      videoflag = true;
      $(play).show();
      $(_switch).html('&#xe623')
    };
    $("a[name='close_video']").click(function () {
      $(video).trigger("pause");     //暂停视频
      videoflag = true;
      $(play).show();
      $(_switch).html('&#xe623')
    });
    // 视频播放控制
    video.click(function () {
      if (videoflag) {
        $(this).trigger("play");      //播放视频
        videoflag = false;
        $(play).hide();
        $(_switch).html('&#xe693');
      } else {
        $(this).trigger("pause");     //暂停视频
        videoflag = true;
        $(play).show();
        $(_switch).html('&#xe623')
      }
    });
    play.click(function () {
      $(video).trigger("play");
      videoflag = false;
      $(this).hide();
      $(_switch).html('&#xe693');
    });
    _switch.click(function () {
      if (videoflag) {
        $(video).trigger("play");      //播放视频
        videoflag = false;
        $(play).hide();
        $(this).html('&#xe693');
      } else {
        $(video).trigger("pause");   //暂停视频
        videoflag = true;
        $(play).show();
        $(this).html('&#xe623');
      }
    });

    var enlarge_flag = true;      //判断放大缩小变量
    var video_box = $(video).parents(".video_box");   //获取视频和控件盒子（不包括标题）
    var time_w=0;
    var page_w=document.body.offsetWidth;
    for(var i=0;;i++){             //获取控件盒子中除了进度条以外其他盒子的大小（放大之后要用到）
      var s_w=$(time).siblings().eq(i).width();
      if(typeof(s_w)=="number"){
        time_w+=s_w;
      }else{
        break;
      }
    }
/*    var m_timer=null;
    enlarge.click(function () {
      var res_width = $(video_box).width();
      var res_height = $(video_box).height();
      if (enlarge_flag) {
        $(video).width(screen.availWidth);  //全屏
        $(video).height(screen.availHeight);
        $(this).html("&#xe6db;");     //更改图标
        video.style.height = '100%';   //因为放大的是盒子所以要将视频高度设置成100%
        enlarge_flag = false;
        $(time).css("width",page_w-time_w-16+'px');    //进度条盒子的宽度
        $(video_box).find(".video").mousemove(function(){     //全屏状态下鼠标移动事件
          $(controls).show();              //先将控件显示
          clearInterval(m_timer);             //移动时清除定时器
          m_timer=setInterval(function(){     //判断鼠标是否静止（静止2.5s时就隐藏控件）
            $(controls).hide();
          },2500)
        })
      }else{
          $(time).removeAttr("style");                  //清除行内样式（也就是恢复默认样式）
          enlarge_flag = true;
          $(this).html("&#xe63d;");
          $(video_box).width(res_width);  //清空全屏
          $(video_box).height(res_height);
      }
    });
    window.addEventListener("resize",function(){    //因为按下esc也可以退出全屏
      if(video.clientWidth<page_w){                 //判断当前是进入全屏还是取消全屏
        video.style.height = '';         //恢复高度
        enlarge_flag = true;
        $(enlarge).html("&#xe63d;");
        $(time).removeAttr("style");             //恢复默认宽度
        clearInterval(m_timer);
        $(video_box).find(".video").off("mousemove");    //解绑鼠标移动事件
        $(controls).show();
      }
    });*/


    //进度条
    $(playprogress).parent().mouseenter(function(){
      $(playprogress).children(".slider").show();              //鼠标经过时显示滑块
    });
    $(playprogress).parent().mouseleave(function(){
      $(playprogress).children(".slider").hide();
    });
    $(playprogress).parent().click(function (e) {
      var ps_w = $(this).width();
      var ps_x = $(this).offset().left;   //获取进度条的距离窗口左边的距离(后面的数字是进度条的在父元素中的左定位)
      var w = e.pageX - ps_x;   //获取鼠标点击在进度条的位置
      $(playprogress).width(w + "px");
      video.currentTime = w / ps_w * video_duration;    //改变视频的播放位置
    });

    // 音量控制
    var volume_flag = true;
    var volume;
    $(volume_div).find(".volume_btn").click(function () {   //声音开关
      if (volume_flag) {
        volume = video.volume;
        volume_flag = false;
        video.volume = 0;
        $(this).html("&#xe63f;");
        $(volume_div).find(".volume_range").val(0);
      } else {
        volume_flag = true;
        video.volume = volume;
        $(volume_div).find(".volume_range").val(volume * 100);
        $(this).html("&#xe63e;")
      }
    });
    $(volume_div).find(".volume_range").change(function () {    //range表单调整音量
      volume = $(this).val() / 100;
      if (volume == 0) {         //当音量为0时改变图标
        $(volume_div).find(".volume_btn").html("&#xe63f;")
      } else {
        $(volume_div).find(".volume_btn").html("&#xe63e;")
      }
      video.volume = volume;
    });

    //播放倍速
    $(speed).mouseenter(function () {
      $(speed).find("ul").show();
    });
    $(speed).mouseleave(function () {
      $(speed).find("ul").hide();
    });
    $(speed).find("li").click(function () {
      var speed_val = $(this).html();
      $(speed).find("span").html(speed_val);
      speed_val = parseFloat(speed_val.split("x")[0]);    //将x作为分隔符，分割成字符串数组
      video.playbackRate = speed_val;
    })
  });
});