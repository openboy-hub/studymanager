$().ready(function(){
    Eject = function(){
        var _this = this;
        // 全屏遮罩背景
        var qback = $('<div class="qback"></div>');
        // alert提示窗
        _this.Ealert = function(obj) {
            var alertBox = $('<div class="alertBox"></div>');
            var alertHead = $('<div class="alertHead">' + obj.title + '</div>');
            var alertMes = $('<div class="alertMes">' + obj.message + '</div>');
            var alertBtn = $('<span class="alertBtn">确定</span>').on('click', function () {
                qback.remove();
                alertBox.remove();
                window.location.reload();
            });
            alertBox.append(alertHead);
            alertBox.append(alertMes);
            alertBox.append(alertBtn);
            qback.append(alertBox);
            $('body').append(qback);
            alertBox.css({'marginTop': -alertBox.outerHeight() / 2 + 'px'});
        }
    }
});

