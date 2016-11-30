<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/c.tld" prefix="c"%>
<%@ taglib uri="/tld/fmt.tld" prefix="fmt"%>

<%
String user = request.getParameter("user");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>组团信息</title>
    <meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
 
  <link rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/weui.min.css"/>
 <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/common.css"/>
 <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/totalgroup.css"/>  
 
	<script language="javascript" src="${basePath}pages/jquery.js"></script>
<script type="text/javascript">

function mygroup(){
    var userId = $("#userid").val(); 
	window.location.href='${basePath}xyt/XytGroupOrderAction!getUserInGroupByUserId.action?userid='+userId;
}
function creategroup(){
	var userid = $("#userid").val(); 
	window.location.href='${basePath}xyt/XytGroupOrderAction!createGroup.action?userid='+userid;
}
function group(groupid)
{
	var userid = $("#userid").val(); 
	window.location.href='${basePath}xyt/XytGroupOrderAction!getGroupOrderByGroupId.action?userId='+userid+'&groupId='+groupid;
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
         <h2>组团列表</h2>
      </div>  
      <div class="weui_cells weui_cells_access">
      <c:forEach items="${listXytGroupOrderInProceed }" var="groupOrder" varStatus="i">
          <div class="x-classlist-content-detail clearfix">
              <span class="x-classlist-content-detail-avatar">
	           <p><img src="${basePath}${groupOrder.xytTercherInfo.photoPath}" class="img-circle"></p>
	           <p>${groupOrder.xytTercherInfo.name}</p>
	          </span>
              <span class="x-classlist-content-detail-text">
                  <p class="totlegroup-coursename">${groupOrder.xytCourse.courseName}</p>
                  <p><s>原价：${groupOrder.originalFee}</s></p>
                  <p class="totlegroup-groupfee">组团价：${groupOrder.groupFee}</p>
                  
                  <a class="weui_cell" onClick = "showdetail('${groupOrder.xytCourse.courseIntroduction}')">
	               <div class="weui_cell_bd weui_cell_primary">
	                 	  课程详情
	               </div>
	               <div class="weui_cell_ft coursesummary" id = 'courseIntroduction'>${groupOrder.xytCourse.courseIntroduction}</div>
	          	  </a>
                  
                  <c:if test="${groupOrder.groupName!=null}">
                  	<p class="weui_cell">团队名称：${groupOrder.groupName}</p>  
                  </c:if>
                  
                   <c:if test="${userId != groupOrder.colonel.rid}">
                     <p class="weui_cell"><a href="${basePath}xyt/userInfo!showHisHome.action?userId=${userId}&hisUserId=${groupOrder.colonel.rid}">团长：${groupOrder.colonel.nickname}</a><span class="weui_cell_ft" style="float:right; position:absolute; right:10px; top:-1px"></span></p>
              	  </c:if>
              	  <c:if test="${userId == groupOrder.colonel.rid}">
                     <p class="weui_cell"><a href="${basePath}xyt/userInfo!showMyHome.action?userId=${userId}">团长：${groupOrder.colonel.nickname}</a><span class="weui_cell_ft"></span></p>
              	  </c:if>

                  <p class="weui_cell">团员人数：${groupOrder.memberNumber}</p>
                  <p class="weui_cell">人数上限：${groupOrder.totalMemberNumber}</p>
                   <a onClick="group(${groupOrder.rid})" class="weui_btn weui_btn_plain_primary totalcourse-circle">了解详情</a>
                  
              
              </span>
          </div>   
      </c:forEach>
      </div>
      <div class="x-search-requestmore" style="display:none">点击加载更多组团</div>
      
<!--无组团状态-->
<c:if test="${!hasGroup}">
	<div class="nothing-main">
	    <div class="nothing-wrap">
	        <img src="${basePath}pages/xytpages/image/nogroup.png"/>
	        <p>暂无组团信息！</p>
	    </div>
	</div>	 
</c:if>
  
<!--底部菜单--> 
<div class="m-bottomPanel"	>
    <span class="js-order">
        <input type="button" onClick="mygroup()" value="我的团队" class="myteam">
        <span class="m-bottomPanel-line"></span>
        <input type="button" onClick="creategroup()" value="我来建团" class="creatgroup">
    </span>
    <span class=""></span>
</div>  
<input type='hidden' id='userid' value='<%=user%>'/>

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
