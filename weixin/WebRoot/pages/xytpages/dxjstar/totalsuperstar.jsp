<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/c.tld" prefix="c"%>
<%@ taglib uri="/tld/fmt.tld" prefix="fmt"%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>大神show</title>
    <meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    
<link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/weui.min.css"/> 
<link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/common.css"/>
<link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/totalcourse.css"/>   
	<script language="javascript" src="${basePath}pages/jquery.js"></script>
<script type="text/javascript">
function star(starid)
{
	var userid = ${userId}
	window.location.href='${basePath}dxj/DxjSuperStarAction!getSuperStar.action?userId='+userid+'&starId='+starid;
}

function showdetail(detail)
{
	$('#dialogtitle').html("大招");
	showdialog2(detail);
}

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
     <h2>大神show</h2>
   </div>
   
   <!--网红列表-->
   <div class="weui_cells weui_cells_access">
   <c:forEach items="${listDxjSuperStar }" var="star" varStatus="i">
	   <div class="x-classlist-content-detail clearfix">
	       <span class="x-classlist-content-detail-avatar">
	           <p><img src="${basePath}${star.photoPath}" class="img-circle"></p>
	       </span>
	       <span class="x-classlist-content-detail-text">
	           <div class="weui_cell_bd weui_cell_primary">
	              <p></p>
	           </div>
	           <a class="weui_cell">
	               <div class="weui_cell_bd weui_cell_primary">
	                   <p>名称</p>
	               </div>
	               <c:if test="${star.abstractIntroduction != null}">
	               	<div class="weui_cell_ft coursesummary" >${star.name}&nbsp&nbsp&nbsp&nbsp${star.abstractIntroduction}</div>
	               </c:if>
	               <c:if test="${star.abstractIntroduction == null}">
	               	<div class="weui_cell_ft coursesummary" >${star.name}</div>
	               </c:if>
	           </a>
	           
	           <a class="weui_cell">
	               <div class="weui_cell_bd weui_cell_primary">
	                   <p>神籍</p>
	               </div>
	               <div class="weui_cell_ft coursesummary" >${star.schoolName}</div>
	           </a>
	               
	           <a class="weui_cell" onClick = "showdetail('${star.introduction}')">
	               <div class="weui_cell_bd weui_cell_primary">
	                   <p>大招</p>
	               </div>
	               <div class="weui_cell_ft coursesummary" id = 'courseIntroduction'>${star.introduction}</div>
	          </a>
               <a onClick = "star(${star.id})" class="weui_btn weui_btn_plain_primary totalcourse-circle">拜大神</a>
	       </span>
	   </div> 
   </c:forEach>   
   </div>
   
   <!-- <div class="x-search-requestmore" style="display:block">点击加载更多课程</div> -->
  <div class="weui_dialog_alert" id="dialog2" style="display: none;">
   <div class="weui_mask"></div>
   <div class="weui_dialog">
      <div class="weui_dialog_hd" id = "dialogtitle"><strong class="weui_dialog_title">大招</strong></div>
      <div class="weui_dialog_bd" id = "dialogmessage"></div>
      <div class="weui_dialog_ft">
          <a id="ok" onClick = "hidedialog2()" class="weui_btn_dialog primary">确定</a>
      </div>
   </div>
</div>
</body>
</html>
