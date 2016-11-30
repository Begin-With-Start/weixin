<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/tld/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我的私信</title>
    <meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/weui.min.css"/> 
    <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/common.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/privatemessages.css"/>   
 	<script language="javascript" src="${basePath}pages/jquery.js"></script>
 	<script type="text/javascript">
 		$().ready(function() {
 			$("#backDiv").click(function() {
 				location.href = "${basePath}xyt/userInfo!showMyHome.action?userId=${user.rid}";
 			});
 		});
 	</script>
  </head>
  
  <body class="g-classDetail">
  <div class="m-nav2">
     <h2>我的私信</h2>
   </div>
   
   <!--私信列表开始-->
   <div class="private-dialogue-warp">
      
       <c:forEach items="${pmessageList }" var="pmessage" varStatus="i">
               <c:if test="${pmessage.my}">
           	   	   <dl class="private_SRLl clearfix" onclick="javascript:location.href='${basePath}xyt/pmessage!showSendPmessage.action?seuserId=${pmessage.seuser.rid}&reuserId=${pmessage.reuser.rid}'" >
           	   </c:if>
               <c:if test="${not pmessage.my}">
           	   	   <dl class="private_SRLl clearfix" onclick="javascript:location.href='${basePath}xyt/pmessage!showSendPmessage.action?seuserId=${pmessage.reuser.rid}&reuserId=${pmessage.seuser.rid}'" >
           	   </c:if>
		           <dt class="face">
		           	   <c:if test="${pmessage.my}">
		           	   	   <img src="${pmessage.reuser.headimgurl}" class="img-circle" style="display:block; visibility: visible;">
		           	   </c:if>
		               <c:if test="${not pmessage.my}">
		           	   	   <img src="${pmessage.seuser.headimgurl}" class="img-circle" style="display:block; visibility: visible;">
		           	   </c:if>
		           </dt>
                   <dd class="private-name content af_content">
                       <c:if test="${pmessage.my}">
		           	   	   ${pmessage.reuser.nickname}
		           	   </c:if>
		               <c:if test="${not pmessage.my}">
		           	   	   ${pmessage.seuser.nickname}
		           	   </c:if>
                   </dd>
		           <dd class="W_border content af_content">
		               <div class="R_msg">
		                   <div class="private_operate clearfix">
		                       <div class="txt">
		                          ${pmessage.content}
		                       </div> 
		                   </div>
		               </div>
		           </dd>
		           <c:if test="${pmessage.unReadCount ne 0}">
		               <dd class="messagesnumber">
	                       <span>${pmessage.unReadCount}</span>
	                   </dd>
		           </c:if>
		       </dl>
		       <div style="font: 0px/0px sans-serif;clear: both;display: block"></div>
       </c:forEach>
      
   </div>
   
   
  
<div class="m-bottomPanel" id="backDiv">
<span class="js-order">
返回个人中心
</span></div>
  </body>
</html>