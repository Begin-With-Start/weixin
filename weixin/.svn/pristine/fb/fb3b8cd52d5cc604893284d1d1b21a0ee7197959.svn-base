<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/c.tld" prefix="c"%>
<%@ taglib uri="/tld/fmt.tld" prefix="fmt"%>

<%
String userid = request.getParameter("user");
%>
<!DOCTYPE HTML>
<html>
  <head>     
    <title>更多课程</title>
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
function course(courseid)
{
	var userid = $("#userid").val(); 
	window.location.href='${basePath}xyt/XytCourseAction!getXytCourse.action?user='+userid+'&courseId='+courseid;
}

function showdetail(detail)
{
	$('#dialogtitle').html("课程详情");
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
     <h2>HIGH假期</h2>
   </div>
   
   <!--柱状图-->
   <c:if test="${isShow}">
	   <div class="weui_cells weui_cells_access percentwrap">
	       <code>
	           <dl id="chart">
	           	   <c:forEach items="${listCourseNumberRank }" var="courseNumberRank" varStatus="i">
	           	   	  <c:if test="${courseNumberRank.max}">
		               <dt>${courseNumberRank.courseName}&nbsp&nbsp课程编码:${courseNumberRank.courseCode}</dt>
		                   <dd><span class="normalcolor" style = "width:${courseNumberRank.percentage}%; background:#FF5151" id="data-one">${courseNumberRank.percentage}%</span></dd>
		           	  </c:if>
		           	  <c:if test="${!courseNumberRank.max}">
		               <dt>${courseNumberRank.courseName}&nbsp&nbsp课程编码:${courseNumberRank.courseCode}</dt>
		                   <dd><span class="normalcolor" style = "width:${courseNumberRank.percentage}%" id="data-one">${courseNumberRank.percentage}%</span></dd>
		           	  </c:if>
		           </c:forEach>
	               
	           </dl>
	       </code>
	   </div>
   </c:if>
   
   <!--课程列表-->
   <div class="weui_cells weui_cells_access">
   <c:forEach items="${listXytCourse }" var="course" varStatus="i">
	   <div class="x-classlist-content-detail clearfix clearfloat">     
	       <img src="${basePath}${course.teacher.photoPath}" width="100%" height="100%">  
           <div class="tourism-info">
               <span class="tourism-l">周洛摄影团周洛摄影团周洛摄影团</span>
               <span class="tourism-r">一日游</span>
           </div>
	       <span class="x-classlist-content-detail-text">
	           
	           <p class="x-classlist-content-detail-text-money x-classlist-content-detail-space">¥<span id = 'fee'>${course.fee}</span> 
               
	           </p>
	           
	          <p class="bm-l"><a onClick = "course(${course.rid})" class="weui_btn weui_btn_plain_primary ">报名</a></p>
	       	  <p class="bm-r"><a onClick = "course(${course.rid})" class="weui_btn weui_btn_plain_primary ">去看看</a></p>
	       
	       </span>
	   </div> 
   </c:forEach>   
   </div>
   
   <!-- <div class="x-search-requestmore" style="display:block">点击加载更多课程</div> -->
  <input type='hidden' id='userid' value='<%=userid%>'/>
  
  <div class="weui_dialog_alert" id="dialog2" style="display: none;">
   <div class="weui_mask"></div>
   <div class="weui_dialog">
      <div class="weui_dialog_hd" id = "dialogtitle"><strong class="weui_dialog_title">温馨提醒</strong></div>
      <div class="weui_dialog_bd" id = "dialogmessage">您已订购了该课程</div>
      <div class="weui_dialog_ft">
          <a id="ok" onClick = "hidedialog2()" class="weui_btn_dialog primary">确定</a>
      </div>
   </div>
</div>
</body>
</html>
