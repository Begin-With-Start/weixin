<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/c.tld" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>选择社团</title>
    <meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
 
  <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/weui.min.css"/> 
 <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/common.css"/> 
  <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/campus.css"/>    
 
 
	<script language="javascript" src="${basePath}pages/jquery.js"></script>
<script type="text/javascript">

function jointravelroute()
{
	var userId = ${userId}; 
	var routeId = document.getElementById("collegeSelect").value;
	window.location.href='${basePath}dxj/DxjTravelRouteAction!getTravelRoute.action?userId='+userId+'&routeId='+routeId;
}
</script>
  </head>
  <body class="g-classDetail">
  <div class="m-nav2">
     <h2>选择校园社团</h2>
 </div>
 <div class="campus-main">
 <div class="campus-wrap">
 <div class="input-group input-bottom campus-select">
  <select id = 'collegeSelect'>
  	<c:forEach items="${listDxjTravelRoute }" var="travelroute" varStatus="i">
  		<option value ="${travelroute.id}">${travelroute.travelRouteName}</option>  
  	</c:forEach>
  </select>
 </div> 
  

</div>
</div>

<!--底部-->
<div class="m-bottomPanel"	>
    <span class="js-order">
    <a onClick="jointravelroute()" href="#">确定</a>
    </span>
</div>
  </body>
  
</html>
