/***获取当前时间 - 开始***/
function getDate(){
    var today=new Date();
    var y=today.getFullYear();
    var mo=today.getMonth()+1;
    var d=today.getDate();
    var h=today.getHours();
    var m=today.getMinutes();
    var s=today.getSeconds();// 在小于10的数字前加一个‘0’
    mo=checkTime(mo);
    d=checkTime(d);
    h=checkTime(h);
    m=checkTime(m);
    s=checkTime(s);
    return y+"/"+mo+"/"+d+"&nbsp;&nbsp;"+h+":"+m+":"+s;
}
function checkTime(i){
    if (i<10){
        i="0" + i;
    }
    return i;
}
/***获取当前时间 - 结束***/