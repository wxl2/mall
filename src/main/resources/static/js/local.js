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
            return null;
            console.log(type);
            console.log(err);
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
    $.ajax({
        type: "POST",
        dataType:'text',
        data : JSON.stringify(str),
        url: "/setAddrPhone",
        contentType:'application/json',
        success:function (data) {
            layer.alert(data);
        },
        error:function (data) {
            layer.alert("请求超时！")
        }
    });

}