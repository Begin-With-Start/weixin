<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
String xmid = request.getParameter("xmid");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>帮他洗脚吧</title>   
	<meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    
 <link type="text/css" rel="stylesheet" href="file/css/reset.css">
 <link type="text/css" rel="stylesheet" href="file/css/default.css">
 <link type="text/css" rel="stylesheet" href="file/css/content.css">
 <link type="text/css" rel="stylesheet" href="file/css/rule.css">
    
	<script language="javascript" src="/weixin/pages/jquery.js"></script>
	<script type="text/javascript" src="/weixin/pages/file/scripts/MessageBox.js" charset='utf-8'></script>
<script type="text/javascript">
$(document).ready(function(){
	setTimeout(function(){ 
		var openid = "<%=user%>";
		$div = $("#amount");
		var amount = 0;
		$.post('/weixin/cj/Cjaction!getFootBathAmount.action',{openId:openid},function(data)
		{
			amount = $.trim(data);
			$div.find("div").eq(0).html(amount);
		},"text");	
		
	},100); 
});

function footbath()
{
	var openid = "<%=user%>";
	var nickname = null;
	var helper = "helpertest1";
	//根据openid获取nickname
	$.post('/weixin/cj/Cjaction!getNicknameByOpenId.action',{openId:openid},function(data)
	{
		nickname = $.trim(data);
		$.post('/weixin/cj/Cjaction!saveFootBathRecord.action',{openId:openid,nickName:nickname,helper:helper},function(data)
		{
		},"text");
		location.reload();
	},"text");	
}

function showAlert(str)
{
	var messContent="<div style='background:#eee;font-size:14px;line-height:1.5rem;padding:5px 10px 10px 10px;text-align:center;'>"+str+"</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

function showMessage5yuan(zjbm){  
	var messContent="<div style='font-size:16;padding:5px 10px 10px 10px;text-align:center;'>您的抽奖编号：</div>  <div style = 'font-size:24;color:#FF0000;text-align:center;'>"+zjbm+"<\div><div  style = 'font-size:22; color:#000000'>8月20日17点公布结果</div> <br/> <br/><div style = 'font-size:16;color:#000000'>同时恭喜您获三等奖</div><div><img src = '/weixin/pages/file/5yuan_xiao.jpg'/></div> <div style = 'font-size:16;color:#000000'>96360商城代金券5元</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

function showMessage10yuan(zjbm){  
	var messContent="<div style='font-size:16;padding:5px 10px 10px 10px;text-align:center;'>您的抽奖编号：</div>  <div style = 'font-size:24;color:#FF0000;text-align:center;'>"+zjbm+"<\div> <br/><div  style = 'font-size:22; color:#000000'>开奖日公布结果</div> <br/> <br/><div style = 'font-size:16;color:#000000'>获二等奖</div><div><img src = '/weixin/pages/file/10yuan_xiao.jpg'/></div> <div style = 'font-size:16;color:#000000'>96360商城代金券10元</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

function showMessageCJBH(zjbm){  
	var messContent="<div style='font-size:20;padding:5px 10px 10px 10px;text-align:center;'>您的抽奖编号：</div>  <div style = 'font-size:22;color:#FF0000;text-align:center;'>"+zjbm+"<\div> <br/><div  style = 'font-size:20; color:#000000'>开奖日公布结果</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

var messageBox; (function(){ messageBox = new __messageBox(); })(); 


</script>

  </head>
  
  <BODY>
      <nav>帮他洗脚吧</nav>
	<DIV class="main_container">
	
	<DIV id=u0_container class="u0_container">
	<DIV id="u0_img" class="u0_original"></DIV>
	<DIV id=u1 class="u1" >
	<DIV id=u1_rtf>&nbsp;</DIV></DIV>
	</DIV>
    
	<div class="btn">
	<input onClick="footbath()"  type="button"  value="帮TA洗脚"></input>
	</div>
	</DIV>
	
	<div id='amount' class="block">
		<div class="p"></div>
    　</div>
    
    <div class="clear h50"></div>
	</BODY>
	
</html>
