<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/c.tld" prefix="c"%>
<%@ taglib uri="/tld/fmt.tld" prefix="fmt"%>
<%
String user = request.getParameter("user");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>我的课程</title>
    <meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
 
 <link rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/weui.min.css"/>
<link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/common.css"/>
 <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/totalcourse.css"/>  
 
	<script language="javascript" src="${basePath}pages/jquery.js"></script>
<script type="text/javascript">

function showdetail(introduction)
{
	$('#dialogtitle').html("课程详情");
	showdialog2(introduction);
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
         <h2>我的课程</h2>
       </div>
       
       <div class="weui_cells_title mycourse-panle">已报名课程</div>
       <c:forEach items="${listApplideOrders}" var="applideOrder" varStatus="i">
           <div class="weui_cells weui_cells_access">
                <div class="x-classlist-content-detail clearfix">
                    <span class="x-classlist-content-detail-avatar">
	                    <p><img src="${basePath}${applideOrder.xytCourse.teacher.photoPath}" class="img-circle"></p>
	                    <p id = 'teachernameapplied'> ${applideOrder.xytTercherInfo.name}</p>
	                </span>
                    <span class="x-classlist-content-detail-text">
                        <p class="x-classlist-content-detail-text-title x-classlist-content-detail-space" id = 'courseNameapplied'> ${applideOrder.xytCourse.courseName}</p>
                        <p class="x-classlist-content-detail-text-name x-classlist-content-detail-space" id = 'courseCodeapplied'> ${applideOrder.xytCourse.courseCode}</p>
                        <p class="x-classlist-content-detail-text-money x-classlist-content-detail-space">¥<span id = 'feeapplied'>${applideOrder.fee}</span>
                        <c:if test="${applideOrder.xytCourse.discount}"><span class="m-tabpanel-content-greennum">${applideOrder.xytCourse.discountNumber}折优惠</span></c:if>
	                    <c:if test="${applideOrder.xytCourse.listenTest}"><span class="m-tabpanel-content-circle">可以试听</span></c:if>
                        </p>
                        <p class="x-classlist-content-detail-text-time x-classlist-content-detail-space" ><fmt:formatDate value="${applideOrder.xytCourse.startDate}" pattern="yyyy.MM.dd"/>  至   <fmt:formatDate value="${applideOrder.xytCourse.endDate}" pattern="yyyy.MM.dd"/>
                        </p>
                        <c:if test="${!applideOrder.xytCourse.onLineClass}"><p class="x-classlist-content-dataol-add x-classlist-content-detail-space" id = 'offLineAddressapplied'> ${applideOrder.xytCourse.offLineAddress}</p></c:if>
	                    <c:if test="${applideOrder.xytCourse.onLineClass}"><p class="x-classlist-content-dataol-onLineUrl x-classlist-content-detail-space" id = 'onLineUrlapplied'> ${applideOrder.xytCourse.onLineUrl}</p></c:if>
                        <a class="weui_cell" onClick = "showdetail('${applideOrder.xytCourse.courseIntroduction}')">
	                        <div class="weui_cell_bd weui_cell_primary">
	                            <p>课程详情</p>
	                        </div>
	                        <div class="weui_cell_ft coursesummary" id = 'courseIntroductionapplied'>  ${applideOrder.xytCourse.courseIntroduction}</div>
	                    </a>
                        <a class="weui_cell" href="tel:${applideOrder.xytCourse.consultTelNumber}" >
	               <div class="weui_cell_bd weui_cell_primary">电话咨询</div>
	               <div class="weui_cell_ft" id = 'consultTelNumberapplied'>  ${applideOrder.xytCourse.consultTelNumber} </div>
	           </a>
                    </span>
                </div>
           </div>
           </c:forEach>
           
           <!--无订单状态-->
           <c:if test="${!hasApplideOrders}">
			<div class="nothing-main">
			    <div class="nothing-wrap">
			        <img src="${basePath}pages/xytpages/image/nothing.png"/>
			        <p>暂无报名课程记录！</p>
			    </div>
			</div>
			</c:if>
			
           
           <div class="weui_cells_title mycourse-panle">试听课程</div>
           <c:forEach items="${listAuditionOrders }" var="auditionOrder" varStatus="i">
               <div class="weui_cells weui_cells_access">
                <div class="x-classlist-content-detail clearfix">
                    <span class="x-classlist-content-detail-avatar">
	                    <p><img src="${basePath}${auditionOrder.xytCourse.teacher.photoPath}" class="img-circle"></p>
	                    <p id = 'teachername'>${auditionOrder.xytTercherInfo.name}</p>
	                </span>
                    <span class="x-classlist-content-detail-text">
                        <p class="x-classlist-content-detail-text-title x-classlist-content-detail-space" id = 'courseName'>   ${auditionOrder.xytCourse.courseName}</p>
                        <p class="x-classlist-content-detail-text-name x-classlist-content-detail-space" id = 'courseCode'>  ${auditionOrder.xytCourse.courseCode}</p>
                        </p>
                        <p class="x-classlist-content-detail-text-time x-classlist-content-detail-space" ><fmt:formatDate value="${auditionOrder.xytCourse.startDate}" pattern="yyyy.MM.dd"/>  至   <fmt:formatDate value="${auditionOrder.xytCourse.endDate}" pattern="yyyy.MM.dd"/>
                        </p>
                        <c:if test="${!auditionOrder.xytCourse.onLineClass}"><p class="x-classlist-content-dataol-add x-classlist-content-detail-space" id = 'offLineAddressaudition'> ${auditionOrder.xytCourse.offLineAddress}</p></c:if>
	                    <c:if test="${auditionOrder.xytCourse.onLineClass}"><p class="x-classlist-content-dataol-onLineUrl x-classlist-content-detail-space" id = 'onLineUrlaudition'> ${auditionOrder.xytCourse.onLineUrl}</p></c:if>
                    	<a class="weui_cell" onClick = "showdetail('${auditionOrder.xytCourse.courseIntroduction}')">
	                        <div class="weui_cell_bd weui_cell_primary">
	                            <p>课程详情</p>
	                        </div>
	                        <div class="weui_cell_ft coursesummary" id = 'courseIntroductionaudition'>  ${auditionOrder.xytCourse.courseIntroduction}</div>
	                    </a>
                        <a class="weui_cell" href="tel:${auditionOrder.xytCourse.consultTelNumber}" >
	               		<div class="weui_cell_bd weui_cell_primary">电话咨询</div>
	               		<div class="weui_cell_ft" id = 'consultTelNumberapplied'>  ${auditionOrder.xytCourse.consultTelNumber} </div>
                        </a>

                    </span>
                </div>
           </div>
           </c:forEach>
           
           <!--无订单状态-->
           <c:if test="${!hasAuditionOrders}">
			<div class="nothing-main">
			    <div class="nothing-wrap">
			        <img src="${basePath}pages/xytpages/image/nothing.png"/>
			        <p>暂无试听课程记录！</p>
			    </div>
			</div>
			</c:if>
           
           
<div class="weui_dialog_alert" id="dialog2" style="display: none;">
   <div class="weui_mask"></div>
   <div class="weui_dialog">
      <div class="weui_dialog_hd" id = "dialogtitle"><strong class="weui_dialog_title">温馨提醒</strong></div>
      <div class="weui_dialog_bd" id = "dialogmessage"></div>
      <div class="weui_dialog_ft">
          <a id="ok" onClick = "hidedialog2()" class="weui_btn_dialog primary">确定</a>
      </div>
   </div>
</div>
           
<input type='hidden' id='userid' value='<%=user%>'/>

  </body>
  
</html>
