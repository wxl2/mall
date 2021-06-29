$(function(){
	
	init();
	$(window).resize(function(){
		init();
	});
	
	//菜单
	$(".menuFA").click(function(){
		menuFAClick($(this));
	});
	$(".menuFA").mouseenter(function(){
		menuFAMouseenter($(this));
	});
	$(".menuFA").mouseleave(function(){
		menuFAMouseleave($(this));
	});
	
})

function menuFAMouseenter(_this){
	_this.animate({
	    borderWidth:'7px'
	},200);
}

function menuFAMouseleave(_this){
	_this.animate({
	    borderWidth:'0px'
	},200);
}

function menuFAClick(_this){
	var dl = _this.siblings("dl");
	var bgColor = "#282a32";
	if(dl.length > 0){
		if(dl.css("display") == "none"){
			dl.show();
			_this.find(".right").attr("class","layui-icon layui-icon-down right");
			bgColor = "#282a32";
		}else{
			dl.hide();
			_this.find(".right").attr("class","layui-icon layui-icon-down right");
			bgColor = "transparent";
		}
	}
	_this.css("background-color",bgColor);
	_this.parent().siblings().find("dl").hide();
	_this.parent().siblings().find("a.menuFA").css("background-color","transparent");
}

//二级菜单点击后的处理方法
function menuCAClick(url,_this){
	
	//处理frameMain url地址
	$("#mainIframe").attr("src",url);
	//处理frameMain title名称变化
	$("#frameMainTitle span").html('<i class="layui-icon layui-icon-set"></i>'+$(_this).text());
}

function titleClick(url,_message) {
	$("#mainIframe").attr("src",url);
	//处理frameMain title名称变化
	$("#frameMainTitle span").html('<i class="layui-icon layui-icon-set"></i>'+_message);
}

//初始化页面
function init(){
	var win_h = $(window).height();
	var win_w = $(window).width();
	var frameMenuW = $(".frameMenu").width();
	var logoH = 110;
	var frameTopH = $(".frameTop").height();
	
	$(".frameMenu").height(win_h);
	$(".frameMenu .menu").height(win_h - logoH);
	$(".main").width(win_w - frameMenuW).height(win_h);
	$(".frameMain").height(win_h - frameTopH);
	$(".frameMain .con").height(win_h - frameTopH - 40);
	$(".frameMain .con iframe").height(win_h - frameTopH - 40);
	
	//自定义滚动条
	$(".menu").mCustomScrollbar();
}

function getSessionName() {
	$.ajax({
		type:'GET',
		url:'/getuser',
		dataType:'text',
		success: function(data){
			$('#userSession').html(JSON.parse(data).username);
			console.log("recv message:"+data);
		},
		error: function(data, type, err){
			console.log(type);
			console.log(err);
		}
	});
}
