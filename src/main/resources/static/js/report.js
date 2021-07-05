/**
 * Created by Administrator on 2015/12/11.
 */
dateRange = {
    currentDate: new Date(),
    millisecond: 1000 * 60 * 60 * 24,//一天的毫秒数
    getThisWeek: function() {
        //起止日期数组
        var startStop = new Array();
        //返回date是一周中的某一天
        var week = dateRange.currentDate.getDay();
        //减去的天数
        var minusDay = week != 0 ? week - 1 : 6;
        //本周 周一
        var monday = new Date(dateRange.currentDate.getTime() - (minusDay * dateRange.millisecond));
        //本周 周日
        var sunday = new Date(monday.getTime() + (6 * dateRange.millisecond));
        //添加本周时间
        startStop.push(dateRange.formatterDate(monday)); //本周起始时间
        //添加本周最后一天时间
        startStop.push(dateRange.formatterDate(sunday)); //本周终止时间
        //返回
        return startStop;
    },
    /**
     * 获得上一周的起止日期
     */
    getLastWeek: function() {
        //起止日期数组
        var startStop = new Array();
        //返回date是一周中的某一天
        var week = dateRange.currentDate.getDay();
        //减去的天数
        var minusDay = week != 0 ? week - 1 : 6;
        //获得当前周的第一天
        var currentWeekDayOne = new Date(dateRange.currentDate.getTime() - (dateRange.millisecond * minusDay));
        //上周最后一天即本周开始的前一天
        var priorWeekLastDay = new Date(currentWeekDayOne.getTime() - dateRange.millisecond);
        //上周的第一天
        var priorWeekFirstDay = new Date(priorWeekLastDay.getTime() - (dateRange.millisecond * 6));

        startStop.push(dateRange.formatterDate(priorWeekFirstDay));
        startStop.push(dateRange.formatterDate(priorWeekLastDay));
        //返回
        return startStop;
    },
    /**
     * 获得这个月的起止日期
     */
    getThisMonth: function() {
        var startStop = new Array();
        //获得当前月份0-11
        var currentMonth = dateRange.currentDate.getMonth();
        //获得当前年份4位年
        var currentYear = dateRange.currentDate.getFullYear();
        //求出本月第一天
        var firstDay = new Date(currentYear, currentMonth, 1);
        //当为12月的时候年份需要加1
        //月份需要更新为0 也就是下一年的第一个月
        //否则只是月份增加,以便求的下一月的第一天
        if (currentMonth == 11) {
            currentYear++;
            currentMonth = 0;
        } else {
            currentMonth++;
        }
        //下月的第一天
        var nextMonthDayOne = new Date(currentYear, currentMonth, 1);
        //求出上月的最后一天
        var lastDay = new Date(nextMonthDayOne.getTime() - dateRange.millisecond);

        startStop.push(dateRange.formatterDate(firstDay));
        startStop.push(dateRange.formatterDate(lastDay));
        //返回
        return startStop;
    },
    /**
     * 获得上个月的起止日期
     */
    getLastMonth: function() {
        var startStop = new Array();
        //获得当前月份0-11
        var currentMonth = dateRange.currentDate.getMonth();
        //获得当前年份4位年
        var currentYear = dateRange.currentDate.getFullYear();
        var currentDay = new Date(currentYear, currentMonth, 1);
        //上个月的第一天
        //年份为0代表,是本年的第一月,所以不能减
        if (currentMonth == 0) {
            currentMonth = 11; //月份为上年的最后月份
            currentYear--; //年份减1
        }
        else {
            currentMonth--;
        }
        var firstDay =  new Date(currentYear, currentMonth, 1);
        //求出上月的最后一天
        var lastDay = new Date(currentDay.getTime() - dateRange.millisecond);

        startStop.push(dateRange.formatterDate(firstDay));
        startStop.push(dateRange.formatterDate(lastDay));
        //返回
        return startStop;
    },
    /**
     * 格式化日期（不含时间）
     */
    formatterDate : function(date) {
        var datetime = date.getFullYear()
            + "-"// "年"
            + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
                + (date.getMonth() + 1))
            + "-"// "月"
            + (date.getDate() < 10 ? "0" + date.getDate() : date
                .getDate());
        return datetime;
    }
}

function drawEchars(data) {
    var chartType = echarts.init(document.getElementById('storetype'));
    var chartMoney = echarts.init(document.getElementById('storemoney'));
    // 指定图表的配置项和数据
    var typeE = [];
    var monetE = [];
    for(var i =0;i<data.length;i++){
        typeE.push({value:data[i].totaltype, name:data[i].typename});
        monetE.push({value:data[i].totalmoney, name:data[i].typename});
    }
    var optionType = {
        title: {
            text: '商品分类',//根据订单数和订单金额来显示饼状图
            left: 'center',
            top:'bottom',
            textStyle: {
                color: '#ccc'
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {d}%"
        },
        series : [
            {
                name: '分类',
                type: 'pie',
                radius: '55%',
                roseType: 'angle',
                data:typeE
            }
        ]
    };
    var optionMoney = {
        title: {
            text: '订单金额',//根据订单数和订单金额来显示饼状图
            left: 'center',
            top:'bottom',
            textStyle: {
                color: '#ccc'
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {d}%"
        },
        series : [
            {
                name: '分类',
                type: 'pie',
                radius: '55%',
                roseType: 'angle',
                data:monetE
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    chartMoney.setOption(optionType);
    chartType.setOption(optionMoney);
}

function gentenTable(datatop,databuttom) {
    layui.use('table', function() {
        var table = layui.table;
        var data =[];
        if(datatop == 0)
            datatop = data;
        table.render({
            elem: '#toptable'
            , cols: [[ //标题栏
                {field: 'ordernum', title: '订单数', width: '35%'}
                , {field: 'orderusernum', title: '订单客户数', width: '30%'}
                , {field: 'money', title: '总金额', width: '35%'}]]
            , data: datatop
        });
        table.render({
            elem: '#bottomtable'
            , cols: [[ //标题栏
                {field: 'orderid', title: '订单编号', width: '30%'}
                , {field: 'goodsid', title: '商品编号', width: '20%'}
                , {field: 'username', title: '购买人', width: '20%'}
                , {field: 'ordertime', title: '订单创建时间', width: '30%'}]]
            , data: databuttom
        });
    });
}

function cls(timestr) {
    layui.use(['laydate', 'layer', 'laypage','table'], function() {
        var laydate = layui.laydate;
        var $ = layui.$;
        var layer = layui.layer;

        laydate.render({
            elem: '#time',
            range: '~',
            done: function(value){
                $('#lastweek').addClass("layui-btn-primary");
                $('#lastmonth').addClass("layui-btn-primary");
                $('#thisweek').addClass("layui-btn-primary");
                $('#thismonth').addClass("layui-btn-primary");
            }
        });
        $.ajax({
            type:'post',
            url:'/report',
            dataType:'json',
            async:false,
            data:timestr,
            contentType:'text/plain',
            success:function (data) {
                drawEchars(data.data.echars);
                gentenTable(data.data.top,data.data.bottom);
            },
            error:function (data) {
                layer.alert("请求超时！")
            }
        });
    });
}