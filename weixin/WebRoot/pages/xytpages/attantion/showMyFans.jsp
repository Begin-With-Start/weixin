<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/tld/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我的粉丝</title>
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
    <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/totalcourse.css"/>   
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
     <h2>我的粉丝</h2>
   </div>
   
   <c:if test="${!hasAttantion}">
	<div class="nothing-main">
		<div class="nothing-wrap">
		<img src="${basePath}pages/xytpages/image/nothing.png"/>
		<p>暂无粉丝记录！</p>
		</div>
	</div>
   </c:if>
   
   <!--私信列表开始-->
   <div class="private-dialogue-warp">
      
       <c:forEach items="${userAttantionList }" var="userAttantion" varStatus="i">
               <dl class="private_SRLl clearfix" onclick="javascript:location.href='${basePath}xyt/userInfo!showHisHome.action?userId=${user.rid}&hisUserId=${userAttantion.user.rid}'" >
		           <dt class="face">
		           	   <img src="${userAttantion.user.headimgurl}" class="img-circle" style="display:block; visibility: visible;">
		           </dt>
                   <dd class="private-name content af_content">
                       ${userAttantion.user.nickname}
                   </dd>
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