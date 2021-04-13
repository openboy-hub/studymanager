
//echarts pie
$(document).ready(function(){
    var myecharts = echarts.init(document.getElementById("Pie1"));
    myecharts.showLoading();
    $.ajax({
        type:"POST",
        async:true,
        url:"/echarts/getPieData",
        dataType:"json",
        success:function(result){
            myecharts.hideLoading();
            var count=0;
            $.each(result,function(index,obj){
                count+=result[index];
            });
            option = {
                tooltip: {
                    trigger: 'item'
                },
                series: [
                    {
                        name: '资源统计',
                        type: 'pie',
                        radius: ['60%', '90%'],
                        roseType:'radius',
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#49586e',
                            borderWidth: 1
                        },
                        label: {
                            show: false,
                            position: 'center',
                            formatter: function(args){
                                return args.name+":"+(args.value*100/count).toFixed(1)+'%';
                            }
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '20',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: [
                            {value: result["video_count"], name: 'Video',itemStyle:{color: '#4bcacc'}},
                            {value: result["image_count"], name: 'Image',itemStyle:{color: '#5ab6df'}},
                            {value: result["score_count"], name: 'Score',itemStyle:{color: '#6a8bbe'}},
                            {value: result["note_count"], name: 'Node',itemStyle:{color: '#fb8575'}}
                        ]
                    }
                ]
            };
            myecharts.setOption(option);
        }
    });
});