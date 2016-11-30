<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/tld/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>完善用户信息</title>
    <meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/weui.min.css"/> 
    <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/common.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/showRegister.css"/>   
 	<script language="javascript" src="${basePath}pages/jquery.js"></script>
 	<script type="text/javascript">
 		$().ready(function() {
 			
 			$("#verifyCodeImg").click(function() {
 				var src = "${basePath}verifyCode?r=" + Math.random();
 				$("#verifyCodeImg").attr("src", src);
 				$("#verifyCode").val("");
 			});
 			
 			$("#submitDiv").click(function() {
 				
 				var name = $.trim($("#name").val());
 				var telNumber = $.trim($("#telNumber").val());
 				var verifyCode = $.trim($("#verifyCode").val());
 				
 		        if(name == "") {
 		        	showdialog2("请填写您的姓名！");
 		        	$("#name").focus();
 		        	return false;
 		        }
 		        if(telNumber == "") {
 		        	showdialog2("请填写您的手机号码！");
 		        	$("#telNumber").focus();
 		        	return false;
 		        }
 				if(telNumber.length != 11){
 					showdialog2("请填写有效的手机号码！");
 					$("#telNumber").focus();
 		        	return false;
 				}
 				if(verifyCode == "") {
 		        	showdialog2("请填写验证码！");
 		        	$("#verifyCode").focus();
 		        	return false;
 		        }
 				
 				$.ajax({
 					url: "${basePath}xyt/register!register.action",
 					type: "POST",
 					data: $("#registerForm").serialize(),
 					dataType: "json",
 					cache: false,
 					beforeSend: function() {
 						
 					},
 					success: function(data) {
 						if (data.result == "success") {
 							var redirectUrl = "${redirectUrl}";
 							if(redirectUrl == "") {
 								location.href = "${basePath}xyt/userInfo!showMyHome.action?userId=${xytUserInfo.rid}";
 							}else {
 								location.href = redirectUrl;
 							}
 						}else {
 							showdialog2(data.content);
 							var src = "${basePath}verifyCode?r=" + Math.random();
 			 				$("#verifyCodeImg").attr("src", src);
 			 				$("#verifyCode").val("");
 						}										    
 					}
 	           });
 				
 			});
 			
 		});
 		
 		function showdialog2(message)
		{
			$('#dialogmessage').html(message);
			$('#dialog2').show();
		}
		
		function hidedialog2()
		{
			$('#dialog2').hide();
		}
 	</script>
  </head>
  
  <body class="g-classDetail">
  <div class="m-nav2">
     <h2>完善用户信息</h2>
   </div>
   <form id="registerForm" method="post">
   <div class="showRegister-main">
       <div class="showRegister-wrap">
           <span class=""></span>
           <span class="error-tip"></span>
  	
  		<input type="hidden" name="xytUserInfo.openid" value="${xytUserInfo.openid}" />
  		
  		<%-- <c:if test="${!xytUserInfo.hasInputScoreCode}">
	  		<div class="input-group input-bottom">
	  		<input type="text" id="scoreCode" name="xytUserInfo.inputScoreCode" maxlength="20" class="p139-input" placeholder="请输入好友邀请码（没有可不输入）"  />
	        </div>
        </c:if>
        
        <c:if test="${xytUserInfo.hasInputScoreCode}">
	  		<div class="input-group input-bottom">
	  		<input readonly="readonly" type="text" id="scoreCode" name="xytUserInfo.inputScoreCode" value="我的好友邀请码:${xytUserInfo.inputScoreCode}" maxlength="20" class="p139-input"/>
	        </div>
        </c:if> --%>
        
        <div class="input-group input-bottom">
  		<input type="text" id="name" name="xytUserInfo.name" value="${xytUserInfo.name}" maxlength="20" class="p139-input" placeholder="请输入姓名"  />
        </div>
        <div class="input-group input-bottom">
  		<input type="text" id="telNumber" name="xytUserInfo.telNumber" value="${xytUserInfo.telNumber}" maxlength="11" class="p139-input" placeholder="请输入手机号码"   />
        </div>
        <div class="input-group input-bottom input-captcha">
  		<input type="text" id="verifyCode" name="verifyCode" value="" maxlength="4" class="p139-input" placeholder="请输入验证码" /><span class="captcha-img"><img id="verifyCodeImg" src="${basePath}verifyCode" /></span>
        </div>
        <a href="#" class="weui_btn weui_btn_primary" id="submitDiv">提交</a> 
  	
        </div>
    </div>
    </form>
    
    <div class="weui_dialog_alert" id="dialog2" style="display: none;">
	   <div class="weui_mask"></div>
	   <div class="weui_dialog">
	      <div class="weui_dialog_hd"><strong class="weui_dialog_title">温馨提醒</strong></div>
	      <div class="weui_dialog_bd" id = "dialogmessage"></div>
	      <div class="weui_dialog_ft">
	          <a id="ok" onClick = "hidedialog2()" class="weui_btn_dialog primary">确定</a>
	      </div>
	   </div>
	</div>
	
  </body>
</html>