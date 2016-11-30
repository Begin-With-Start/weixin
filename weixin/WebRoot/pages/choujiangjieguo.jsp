<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
String xmid = request.getParameter("xmid");
String fenshu = request.getParameter("fenshu");
String result = request.getParameter("result");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>     
    <title>抽奖结果</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
 <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
 <meta name="format-detection" content="telephone=no"/>
 <meta http-equiv="content-type" content="text/xml; charset=utf-8" />
	<script language="javascript" src="/weixin/pages/jquery.js"></script>
<script type="text/javascript">
//var cjarr = new Array();
$(document).ready(function(){
	is_weixn();
	 //checkuser();
	 setTimeout(function(){ 
			cj(xmid);  
		 },100); 
  	
});

function is_weixn(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger") {
    	//loaduser();
      //  return true;
  
    } else {
        alert("请在微信浏览器中打开该链接!");    
        window.close(); 
           
      //  return false; 
    }
} 
function cj(xmid){
	var user = $("#userid").val();
	
	//如果奖品份数为0，那么未得奖
	if(fenshu < 1)
	{
		alert("谢谢惠顾");
	}
	else
	{	
		/*
		$.post('/weixin/cj/Cjaction!savecj.action',{userid:user,xmid:xmid},function(data){
		//返回中奖类型
		if ($.trim(data)=="1")
		{
			alert("恭喜您中得一等奖");
		}
		else if ($.trim(data)=="2")
		{
			alert("恭喜您中得二等奖");
		}
		
		//奖品份数-1
		
		
		window.location.href='/weixin/pages/lipin.jsp?user='+user;
		},"text");
		*/
		if (result=="1")
		{
			alert("恭喜您中得一等奖");
		}
		else if (result=="2")
		{
			alert("恭喜您中得二等奖");
		}
		
	}
}


Array.prototype.contains = function (element) { 
	  
    for (var i = 0; i < this.length; i++) { 
        if (this[i] == element) { 
            return true; 
        } 
    } 
    return false; 
} 

function loaduser(){
	
}
</script>

  </head>
  
  <body >
  <div id='showall'>
  </div>
<div style='hidden'>
	<div id='clonediv' style="width:300px;height=400px; margin:0px auto;">
		<div style="width:150px;height:200px;float:left" >
	
		</div>
		<div style="width:150px;height:200px;float:right" >
			<div  style="height:50px;"></div>
			<div style="height:50px;"></div>
			<div style="height:50px;"></div>
			<div style="height:50px;"></div>
		</div>
		<div style="width:150px;height:100px;float:left" >
		</div>
  </div>

</div>
<input type='hidden' id='userid' value='<%=user%>'/>
  </body>
  
</html>
