function getSessionName() {
    var ret = '';
    $.ajax({
        type:'GET',
        async:false,
        url:'/getuser',
        dataType:'text',
        success: function(data){
            ret = data;
            console.log("recv message:"+data);
        },
        error: function(data, type, err){
            console.log(type);
            console.log(err);
            return null;
        }
    });
    return ret;
}

function setRole() {
    //载入 更改用户身份
    $.ajax({
        type: "GET",
        url: "/getuser",
        success: function (data) {
            //placeholder
            $("#username").val(data.username);
            if (data.role == "0") {
                $("#val12").val("客户");
                $("#div3").show();
            } else if (data.role == "2") {
                $("#val12").val("商家");
            }
            $("#phone").val(data.phone);
            $("#addr").val(data.addrs);
        },
        error: function () {
            alert("error");
        }
    });
}

// 将选中的地址填入我的地址框中
function setAddr(){
    div1.style.display ='none';
    var obj = document.getElementById("sheng"); //定位id
    var index = obj.selectedIndex; // 选中索引
    var value = obj.options[index].value; // 选中值
    var obj1 = document.getElementById("shi"); //定位id
    var index1 = obj1.selectedIndex; // 选中索引
    var value1 = obj1.options[index1].value; // 选中值
    var obj2 = document.getElementById("qu"); //定位id
    var index2 = obj2.selectedIndex; // 选中索引
    var value2 = obj2.options[index2].value; // 选中值
    addr.value = value+"  "+value1+"  "+value2;
}

//发生商家申请
function sendApply(){
    var str={};
    str.username =$("#username").val();
    str.action = "0";
    $.ajax({
        type: "POST",
        dataType:'text',
        data : JSON.stringify(str),
        url: "/applyStore",
        contentType:'application/json',
        success: function (data) {
            if(data == -1)
                alert("申请以提交，不能重复申请！");
            else alert("申请已提交，请耐心等候！");
        }
    });
}

//   发送电话号码，地址
function sendAddr(){
    var str={};
    str.username =$("#username").val();
    str.phone =$("#phone").val() ;
    str.addr =$("#addr").val() ;
    postAjax(str,'/setAddrPhone','text','application/json');

}

function postAjax(dataObj,url,datatype,contenttype) {
    $.ajax({
        type:'post',
        url:url,
        dataType:datatype,
        async:false,
        data:JSON.stringify(dataObj),
        contentType:contenttype,
        success:function (data) {
            layer.alert(data);
        },
        error:function (data) {
            layer.alert("请求超时！")
        }
    });
}

function typeDiv(actionId,divTitle) {
    var table = layui.table;
    layer.open({
        type:'1',
        content:$('.addTypePage'),
        title:divTitle,
        area:['430px','195px'],
        btn:['提交','重置','取消'],
        closeBtn:'1',
        btn1:function (index,layero) {
            //提交回调
            var typeName = $('#typeStore').val();
            if(typeStore.length<=0){
                layer.alert("类别不能为空");
                return false;
            }
            var params = {};
            if(actionId ==2) {
                params.action = actionId;
                params.data = typeName;
            }else{
                params.action = 1;
                var data_ = {};
                data_.id = actionId;
                data_.type = typeName;
                params.data =data_;
            }
            postAjax(params,'/mangerType','text','text/plain');
            layer.close(index);
            table.reload('goodsType', {});
        },

        btn2:function (index,layero) {
            //重置回调
            var typeStore = $('#typeStore').val("");
            // 防止添加页面关闭
            return false;
        },
        btn3:function (index,layero) {
            //取消回调
        }
    });
}

function paging(res) {
    var dataList = res.data;
    var total = res.count;
    var list = [];
    var page = $("#layui-table-page1").find(".layui-laypage-em").next().html();
    var limit = $("#layui-table-page1").find(".layui-laypage-limits select").val();
    if(page == undefined || page == null || page == ""){
        page = 1;
    }
    if(limit == undefined || limit == null || limit == ""){
        limit = 10;
    }
    var start = (page-1) * limit;
    var end = page * limit;
    if(end > total){
        end = total;
    }
    for(var i=start; i<end; i++){
        list.push(dataList[i]);
    }
    return {
        "code": res.code,
        "msg": res.msg,
        "count": res.count,
        "data": list
    }
}

function loginout() {
    $.get('/loginout');
}
