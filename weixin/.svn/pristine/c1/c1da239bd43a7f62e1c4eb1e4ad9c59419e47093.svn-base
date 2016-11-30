<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>     
    <title>我的奖品</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"/>
	<meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="content-type" content="text/xml; charset=utf-8" />
    
    
    <style type = "text/css">
    	#tab{
    		width:300px;
    		border:3px solid #CCCCCC
    	}
    	
    	#h3{
    		margin:0px;
    		font-size:14px;
    		float:left;
    		width:60px;
    		background-color:#CCCCCC;
    		text-align:center;
    		height:24px;
    		line-height:24px;
    	}
    	#tab div{
    		clear:both;
    		height:100px;
    		font-size:20px;
    	}
    	#tab.activ{
    		background-color:#FFFFFF;
    	}
    </style>
    
    <script language="javascript" src="/weixin/pages/jquery.js"></script>
<script type="text/javascript">

window.onload = function(){
	loadzjjl();
	var oTab = document.getElementById("tab");
	var aH3 = oTab.getElementsByTagName("h3");
	aH3[0].onclick = function(){
		loadzjjl();
	}
	aH3[1].onclick = function(){
		exceedtimelimit();
	}	
}

//已过期的奖品
function exceedtimelimit(){
	var user = $("#userid").val();
	//$table = $("#showall");
	var now = new Date();
	$.post('/weixin/cj/Cjaction!getAllZjjlByUser.action',{userid:user},function(data){
		for (var i = 0 ; i<data.length ;i++){
			var zjjl =data[i];
			var zjlx =zjjl.zjlx;
			var yhqbm =zjjl.yhqbm; 
			var jzsj= zjjl.jzsj;
			jzsj = jzsj.replace(/-/g,"/"); var sj =  new Date(Date.parse(jzsj));
			if(sj < now)
			{
				$div = $("#exceedtimelimite");
				$div.find("div").eq(0).html("<image src='/weixin/pages/file/daijinquan.jpg' width='300' height='100'>");
				if(zjlx == "2")
				{
					$div.find("div").eq(1).html("<b>96360代金券(10元)</b>");
				}
				else if(zjlx == "3") 
				{
					$div.find("div").eq(1).html("<b>96360代金券(5元)</b>");
				}
				$div.find("div").eq(2).html("您的96360代金券编码： <b>"+yhqbm+"</b>");
				$div.find("div").eq(3).html("<b>截止日期：</b> "+jzsj);
				//$div.appendTo($table)
				$("#clonediv1").hide();
				$("#clonediv2").show();
			}
		}
	},"json");
	//$table.trigger("update");
}


function loadzjjl(){
	var user = $("#userid").val(); //lidu
	//$table = $("#showall");
	var now = new Date();
	$.post('/weixin/cj/Cjaction!getAllZjjlByUser.action',{userid:user},function(data){
		for (var i = 0 ; i<data.length ;i++){
			var zjjl =data[i];
			var zjlx =zjjl.zjlx;
			var yhqbm =zjjl.yhqbm; 
			var yhqmm = zjjl.yhqmm;
			var jzsj= zjjl.jzsj;
			$div = $("#clonediv");
			$div.find("div").eq(0).html("<image src='/weixin/pages/file/daijinquan.jpg' width='300' height='100'>");
			if(zjlx == "2")
			{
				$div.find("div").eq(1).html("<b>96360代金券(10元)</b>");
			}
			else if(zjlx == "3") 
			{
				$div.find("div").eq(1).html("<b>96360代金券(5元)</b>");
			}
			$div.find("div").eq(2).html("您的96360代金券编码： <b>"+yhqbm+"</b>");
			$div.find("div").eq(3).html("您的96360代金券密码： <b>"+yhqmm+"</b>");
			$div.find("div").eq(4).html("<b>截止日期：</b> "+jzsj);
			//$div.appendTo($table);
			$("#clonediv1").show();
			$("#clonediv2").hide();
		}
	},"json");
	//$table.trigger("update");
}
</script>

  </head>
  
  <body >
  <style> body{background-color:ef4254} </style>
  
  <div id = "tab">
  	<h3 class = "active">我的奖品</h3>
  	<h3>已过期</h3>
  	<div style = "display:block;" id="clonediv1">
  		<div id='clonediv' style="width:300px; height:height=400px;">
		  <div style="height:100px;"></div>
	      <div style="height:30px;"></div>
		  <div style="height:30px;"></div>
		  <div style="height:30px;"></div>
		  <div style="height:30px;"></div>
	  </div>
  	</div>
  	<div style = "display:none;"  id="clonediv2">
  		<div id='exceedtimelimite' style="width:300px; height:height=400px;">
		  <div style="height:100px;"></div>
	      <div style="height:30px;"></div>
		  <div style="height:30px;"></div>
		  <div style="height:30px;"></div>
	  </div>
  	</div>
  </div>
	
<input type='hidden' id='userid' value='<%=user%>'/>
  </body>
  
</html>
