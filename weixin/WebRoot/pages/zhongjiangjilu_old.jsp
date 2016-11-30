<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>     
    <title>我的奖品</title>   
	<meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
 
 <link type="text/css" rel="stylesheet" href="file/css/reset.css">
 <link type="text/css" rel="stylesheet" href="file/css/default.css">
 <link type="text/css" rel="stylesheet" href="file/css/recorder.css">
    <style type = "text/css">
    	#tab{
    		width:100%;
    		border-bottom:1px solid #CCCCCC
    	}
    	
    div#tab h3{
    		margin:0px;
    		font-size:16px;
    		float:left;
    		width:50%;
    		text-align:center;
    	}
    	#tab div{
    		clear:both;
    		height:100px;
    		font-size:20px;
    	}
    	.active{
    		background-color:#FFFFFF;
    	}
    	.hide{ background-color:#FFFFFF;}
    </style>
    
    <script language="javascript" src="/weixin/pages/jquery.js"></script>
    <script type="text/javascript" src="/weixin/pages/file/scripts/MessageBox.js" charset='utf-8'></script>
<script type="text/javascript">

var sum_exceed = 0;
var length_exceed = 0;
var sum_zjjl = 0;
var length_zjjl = 0;

window.onload = function(){
	loadzjjl();
	exceedtimelimit();	
	var oTab = document.getElementById("tab");
	var aH3 = oTab.getElementsByTagName("h3");
	$("#clonediv1").show();
	aH3[0].className = '';
	aH3[0].className = 'hide';   
	aH3[1].className = 'active';
	aH3[1].className = '';
	aH3[0].onclick = function(){
		if((length_zjjl ==0) || (sum_zjjl == length_zjjl))
		{
			showAlert("暂无中奖记录");
		}else
		{
			$("#clonediv1").show();
			$("#clonediv2").hide();
			aH3[0].className = '';
			aH3[0].className = 'hide';
			aH3[1].className = 'active';
			aH3[1].className = '';
		}
	}
	aH3[1].onclick = function(){
		if((length_exceed ==0) || (sum_exceed == length_exceed))
		{
			showAlert("暂无已过期记录");
		}
		else
		{
			$("#clonediv1").hide();
			$("#clonediv2").show();
			aH3[1].className = '';
			aH3[1].className = 'hide';
			aH3[0].className = 'active';
			aH3[0].className = '';
		}
	}
	
	
}

//已过期的奖品
function exceedtimelimit(){
	var user = $("#userid").val();
	$table1 = $("#clonediv2");
	$table1.html("");
	var now = new Date();
	$.post('/weixin/cj/Cjaction!getAllZjjlByUser.action',{userid:user},function(data){
		//length_exceed = data.length;
		for (var i = 0 ; i<data.length ;i++){
			var zjjl =data[i];
			var zjlx =zjjl.zjlx;
			if(zjlx != 1)
			{
				length_exceed++;
			}
			var yhqbm =zjjl.yhqbm; 
			var yhqmm = zjjl.yhqmm;
			var jzsj= zjjl.jzsj;
			jzsj = jzsj.replace(/-/g,"/"); var sj =  new Date(Date.parse(jzsj));
			if(sj < now)
			{
				$div = $("#exceedtimelimite").clone();
				if(zjlx == "2")
				{
					$div.find("div").eq(1).html("<image src='/weixin/pages/file/10yuan_xiao.jpg' width='100%' height='100'>");
				}
				else if(zjlx == "3") 
				{
					$div.find("div").eq(1).html("<image src='/weixin/pages/file/5yuan_xiao.jpg' width='100%' height='100'>");
				}
				$div.find("div").eq(2).html("卡号:"+yhqbm+""+" 密码:"+yhqmm+"");
				$div.find("div").eq(3).html("截止日期： "+jzsj);
				$div.appendTo($table1);
			}
			else
			{
				sum_exceed++;
			}
		}
	},"json");
}

function loadzjjl(){
	var user = $("#userid").val(); //lidu
	$table = $("#clonediv1");
	$table.html("");
	var now = new Date();
	$.post('/weixin/cj/Cjaction!getAllZjjlByUser.action',{userid:user},function(data){
		//length_zjjl = data.length;
		for (var i = 0 ; i<data.length ;i++){
			var zjjl =data[i];
			var zjlx =zjjl.zjlx;
			if(zjlx != 1)
			{
				length_zjjl++;	
			}
			var yhqbm =zjjl.yhqbm; 
			var yhqmm = zjjl.yhqmm;
			var jzsj= zjjl.jzsj;
			jzsj = jzsj.replace(/-/g,"/");
			var sj =  new Date(Date.parse(jzsj));
			if(sj > now)
			{
				$div = $("#clonediv").clone();
				if(zjlx == "2")
				{
					$div.find("div").eq(1).html("<image src='/weixin/pages/file/9636010.jpg' onclick='javascript:ljsy();return false;'width='100%' height='100'>");
				}
				else if(zjlx == "3") 
				{
					$div.find("div").eq(1).html("<a href = '/weixin/pages/methodofapplication.jsp?user="+user+"&yhqbm="+yhqbm+"'><image src='/weixin/pages/file/963605.jpg' width='100%' height='100'></a>");
				}
				$div.find("div").eq(2).html("卡号:"+yhqbm+"  "+" 密码:"+yhqmm);
				$div.find("div").eq(3).html("有效期截止:"+jzsj+"");
				$div.appendTo($table);
			}
			else
			{
				sum_zjjl++;
			}
		}
		if((length_zjjl ==0) || (sum_zjjl == length_zjjl))
		{
			showAlert("暂无中奖记录");
		}
	},"json");
	
	if(user == 'oyaV3uGIVnjN0dO15JUdfQpGHHcU')
	{
		$div = $("#clonediv").clone();
		$div.find("div").eq(1).html("<image src='/weixin/pages/file/huangtao.jpg' width='100%' height='100'>");
		$div.find("div").eq(2).html("奖品名称：怀化麻阳高山黄桃1箱/10斤装");
		$div.appendTo($table);
	}
}

function myrecord()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/choujiangjilu.jsp?user='+user;
}

function prizelist()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/0yuangou.jsp?user='+user;
}

function rule()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/rule.jsp?user='+user;
}

function showAlert(str)
{
	var messContent="<div style='background:#eee;font-size:14px;line-height:1.5rem;padding:5px 10px 10px 10px;text-align:center;'>"+str+"</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

function ljsy()
{
	showAlert("使用PC端访问www.hn96360.com选购商品,在结算页面的“优惠卡”中输入卡号、密码使用。");
}
var messageBox; (function(){ messageBox = new __messageBox(); })(); 

</script>

  </head>
  <body >
  <nav>奖品列表</nav>
  <div id = "tab" style="width:100%;height:26px;">
  	<h3 class = "active">我的奖品</h3>
  	<h3>已过期</h3>
  	<div style = "display:none;" id="clonediv1">
  	</div>
  	<div style = "display:none;" id="clonediv2">
  	</div>
  </div>
<input type='hidden' id='userid' value='<%=user%>'/>

	<div id="walldiv" style="display:none;">
		<div id='clonediv' style="width:100%; height:160px;">
			  <div style="height:25px;"></div>
			  <div class="pic"></div>
			  <div class="cardnumber"></div>
			  <div class="date"></div>
		  </div>
		  <div id='exceedtimelimite'>
		  	  <div style="height:25px;"></div>
			  <div class="pic"></div>
			  <div class="cardnumber"></div>
			  <div class="date"></div>
		  </div>
	</div>
    <div class="clear h50"></div>
<div class="hytpl-navbar hytpl-navbar-new">
    <ul class="bar-list">
        <li>
            <a href="#" onClick="prizelist()" class="cur">
                <div class="icon">
                    <b class="iconfont list"></b>
                </div>
                <p class="name">奖品列表</p>
            </a>
        </li>
        
         <li>
            <a href="#" onClick="myrecord()" class="cur">
                <div class="icon">
                    <b class="iconfont number"></b>
                </div>
                <p class="name">抽奖编号</p>
            </a>
        </li>
        
         <li>
            <a href="#" class="cur">
                <div class="icon">
                    <b class="iconfont recoder"></b>
                </div>
                <p class="name">中奖记录</p>
            </a>
        </li>
        
         <li>
            <a href="#" onClick="rule()" class="cur">
                <div class="icon">
                    <b class="iconfont rule"></b>
                </div>
                <p class="name">大奖玩法</p>
            </a>
        </li>
    </ul>
</div>
  </body>
</html>
