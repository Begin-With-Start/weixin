
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
    <meta name="format-detection" content="telephone=yes" />
    
 <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/weui.min.css"/> 
 <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/common.css"/> 
 <link type="text/css" rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/course.css"/>    
 
	<script language="javascript" src="${basePath}pages/jquery.js"></script>
	<script language="javascript" src="${basePath}/pages/xytpages/jweixin-1.0.0.js"></script>
<script type="text/javascript">

function showdetail()
{
	$('#dialogtitle').html("课程详情");
	showdialog2("${xytCourse.courseIntroduction}");
}

function showdialog1()
{
	
}

function showdialog2(message)
{
	$('#dialogmessage').html(message);
	$('#dialog2').show();
}

function hidedialog2()
{
	$('#dialog2').hide();
	window.location.reload();
}

function leaveMessage(){
	$('#loadingToast').show();
	//留言
	var starId = ${starId};
	var userId = ${userId};
	var content = document.getElementById("messageText").value 
	$.post('${basePath}xyt/XytMessageAction!saveDxjMessage.action',{starId:starId,userId:userId,content:content},function(data){
		if(data.result == "success")
		{
			$('#loadingToast').hide();
			window.location.reload(); 
		}else{
			$('#loadingToast').hide();
			showdialog2("留言失败，请稍后重试！");
		}
		
	},"json");
}

function leaveMessageAnonymity(){
	$('#loadingToast').show();
	//留言
	var starId = ${starId};
	var userId = ${userId};
	var content = document.getElementById("messageText").value 
	$.post('${basePath}xyt/XytMessageAction!saveDxjMessageAnonymity.action',{starId:starId,userId:userId,content:content},function(data){
		if(data.result == "success")
		{
			$('#loadingToast').hide();
			window.location.reload(); 
		}else{
			$('#loadingToast').hide();
			showdialog2("留言失败，请稍后重试！");
		}
	},"json");
	 //刷新页面
}

function likepoint(){
	$('#loadingToast').show();
	var starId = ${starId};
	var userId = ${userId};
	$.post('${basePath}dxj/DxjSuperStarAction!addLikePoint.action',{starId:starId,userId:userId},function(data){
	    //获取留言信息
	    if(-1 == data.likePointNumber)
	    {
	    	$('#loadingToast').hide();
	    	showdialog2("你已经送过花，每人只能送一次哦！");
	    }else
	    {
	    	$('#loadingToast').hide();
	    	$("#likepoint").html(data.likePointNumber);
	    }
	},"json");
}
</script>
  </head>
  <body class="g-classDetail">
  
 <div class="m-nav2">
     <h2>大神show</h2>
 </div>
 
 <!--教师列表-->
 <div class="g-box-wrap">
     <div class="m-box">
         <div class="m-teacherDetail">
             <p class="m-teacherDetail-title">
                 <img id = 'starimg' src="${basePath}${star.photoPath}">
                 <span class="m-teacherDetail-name" id = 'teachername'>${star.name}</span>
             </p>
             <p class="m-teacherDetail-summary" id = 'teacherIntroduction'>${star.introduction}</p>
         </div>
         <p class="m-zan-num">获得<span class="m-zan-num-red" id = 'likepoint'>${star.likePointNumber}</span>束花</p>
         <p class="m-zan-img">
             <img src="http://m.xdf.cn/public/assets/Mobile_Classroom/images/zan_female.png">
         </p>
          <a href="#" class="weui_btn weui_btn_warn" onclick="likepoint()">给TA送花</a>
          <p class="course-messagetext"><textarea name="" cols="" rows="" id = 'messageText' placeholder="请输入您的评论内容"></textarea></p>
         <a href="#" class="weui_btn weui_btn_plain_primary" onclick="leaveMessage()">发表评论</a>  
         <a href="#" class="weui_btn weui_btn_plain_primary" onclick="leaveMessageAnonymity()">匿名评论</a> 
     </div>
 </div>
 
<!--留言区域-->
 <div class="weui_cells weui_cells_access m-margin" id = 'leaveMessageArea'>
 	<c:forEach items="${listXytMessage }" var="xytMessage" varStatus="i">
     <div class="m-messages-area" id = 'messageBlock' style = "display:block;">
         <p class="m-messages-title" id = 'messageTitle'>
         	
         	 <c:if test="${!xytMessage.anonymity}">
         	 
             	<c:if test="${userId != xytMessage.xytUserInfo.rid}">
             		<a href="${basePath}xyt/userInfo!showHisHome.action?userId=${userId}&hisUserId=${xytMessage.xytUserInfo.rid}"><img id = 'messageHeadUrl' src="${xytMessage.xytUserInfo.headimgurl}" class="img-circle" style="display: inline; visibility: visible;"></a>
             	</c:if>
             	<c:if test="${userId == xytMessage.xytUserInfo.rid}">
             		<a href="${basePath}xyt/userInfo!showMyHome.action?userId=${userId}"><img id = 'messageHeadUrl' src="${xytMessage.xytUserInfo.headimgurl}" class="img-circle" style="display: inline; visibility: visible;"></a>
             	</c:if>
             </c:if>
             
             <c:if test="${xytMessage.anonymity}">
                <img id = 'messageHeadUrl' src="${basePath}pages/xytpages/image/anonymity/anonymity.png" class="img-circle" style="display: inline; visibility: visible;">
             </c:if>
             <c:if test="${!xytMessage.anonymity}">
             	<span class="m-messages-nickname" id = 'messageUserName'>${xytMessage.xytUserInfo.nickname}</span>
             </c:if>
              <c:if test="${xytMessage.anonymity}">
             	<span class="m-messages-nickname" id = 'messageUserName'>深海匿名</span>
             </c:if>
             <span class="m-messages-nickname m-messages-time" id = 'leaveMessageTime' style = "float:right"><fmt:formatDate value="${xytMessage.createDate}" pattern="yyyy-MM-dd"/></span>
         </p>
         <p class="m-messages-summary" id = 'messageContent'>${xytMessage.content}</p>
     </div>
     </c:forEach>
     
  </div>	
 
<div class="weui_dialog_confirm" id="dialog1" style="display: none;">
  <div class="weui_mask"></div>
  <div class="weui_dialog">
     <div class="weui_dialog_hd"><strong class="weui_dialog_title">弹窗标题</strong></div>
     <div class="weui_dialog_bd">自定义弹窗内容，居左对齐显示，告知需要确认的信息等</div>
     <div class="weui_dialog_ft">
       <a id="cancel" href="javascript:;" class="weui_btn_dialog default">取消</a>
       <a id="confirm" href="javascript:;" class="weui_btn_dialog primary">确定</a>
     </div>
  </div>
</div>

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

<div id="loadingToast" class="weui_loading_toast" style="display:none;">
   <div class="weui_mask_transparent"></div>
   <div class="weui_toast">
       <div class="weui_loading">
           <!-- :) -->
           <div class="weui_loading_leaf weui_loading_leaf_0"></div>
           <div class="weui_loading_leaf weui_loading_leaf_1"></div>
           <div class="weui_loading_leaf weui_loading_leaf_2"></div>
           <div class="weui_loading_leaf weui_loading_leaf_3"></div>
           <div class="weui_loading_leaf weui_loading_leaf_4"></div>
           <div class="weui_loading_leaf weui_loading_leaf_5"></div>
           <div class="weui_loading_leaf weui_loading_leaf_6"></div>
           <div class="weui_loading_leaf weui_loading_leaf_7"></div>
           <div class="weui_loading_leaf weui_loading_leaf_8"></div>
           <div class="weui_loading_leaf weui_loading_leaf_9"></div>
           <div class="weui_loading_leaf weui_loading_leaf_10"></div>
           <div class="weui_loading_leaf weui_loading_leaf_11"></div>
       </div>
       <p class="weui_toast_content">数据加载中</p>
   </div>
</div>

</body>
  
</html>
