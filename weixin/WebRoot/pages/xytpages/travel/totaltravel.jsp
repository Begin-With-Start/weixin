<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/c.tld" prefix="c"%>
<%@ taglib uri="/tld/fmt.tld" prefix="fmt"%>

<%
String userid = request.getParameter("user");
%>
<!DOCTYPE HTML>
<html>
  <head>     
    <title>HIGH假期</title>
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
function reserve(id){
	showdialog2("对不起，暂未开通报名功能，请耐心等待！");
	/*
	var userid = $("#userid").val(); 
	var travelrouteid = id;
	//判断是否已经注册，未注册跳转到注册页面
	$('#loadingToast').show();
	
	$.post('${basePath}xyt/userInfo!getXytUserInfoByRid.action',{userid:userid},function(data){
		var xytUserInfo = data[0];
		//如果电话号码为空，则认为没有注册
		if(null == xytUserInfo.telNumber)
		{
			$('#loadingToast').hide();
			var redirectUrl = "${basePath}dxj/DxjTravelRouteAction!getAlltravelRoute.action?user="+userid;
			window.location.href="${basePath}xyt/register!showRegister.action?openId=" + xytUserInfo.openid + "&redirectUrl=" + encodeURIComponent(redirectUrl);
		}else{
			///判断是否已经购买该旅游线路
			$.post('${basePath}dxj/DxjTravelRouteOrderAction!getOrderByUserAndCourse.action',{userid:userid,routeId:id},function(data){
				if(0 == data.length)
				{
					pay(travelrouteid);
				}else{
					$('#loadingToast').hide();
					showdialog2("您已订购该旅游线路！");
				}
				
			},"json"); 
			$('#loadingToast').hide();
		}
	},"json");
	*/
}

function pay(travelrouteid){
	if (typeof WeixinJSBridge == "undefined"){
	   if( document.addEventListener ){
	       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	   }else if (document.attachEvent){
	       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	   }
	}else{
	    onBridgeReady(travelrouteid);
	} 
}

function onBridgeReady(travelrouteid){	
	//获取appId、timeStamp、nonceStr、package、signType、paySign
	var userid = $("#userid").val(); 
	var appId;
	var timeStamp;
	var nonceStr;
	var signType;
	var packAge;
	var paySign;
	$.post('${basePath}dxj/DxjTravelRouteOrderAction!travelRouteOrderPay.action',{userid:userid,routeId:travelrouteid},function(data)
	{
		$('#loadingToast').hide();
		var brandWCPayParameterVo = data;
		appId = brandWCPayParameterVo.appId;
		timeStamp = brandWCPayParameterVo.timeStamp;
		nonceStr = brandWCPayParameterVo.nonceStr;
		signType = brandWCPayParameterVo.signType;
		packAge = brandWCPayParameterVo.packAge;
		paySign = brandWCPayParameterVo.paySign;
		WeixinJSBridge.invoke(
			'getBrandWCPayRequest', {
				"appId" : appId,     //公众号名称，由商户传入     
				"timeStamp": timeStamp,         //时间戳，自1970年以来的秒数     
				"nonceStr" : nonceStr, //随机串     
				"package" : packAge,     
				"signType" : signType,         //微信签名方式:     
				"paySign" : paySign    //微信签名 
			},
				      
			function(res){     
				if(res.err_msg == "get_brand_wcpay_request:ok" ) {
				    showdialog2("支付成功");
				}     
			}
		); 
	},"json");
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
		               <dt>${courseNumberRank.courseName}</dt>
		                   <dd><span class="normalcolor" style = "width:${courseNumberRank.percentage}%; background:#FF5151" id="data-one">${courseNumberRank.percentage}%</span></dd>
		           	  </c:if>
		           	  <c:if test="${!courseNumberRank.max}">
		               <dt>${courseNumberRank.courseName}</dt>
		                   <dd><span class="normalcolor" style = "width:${courseNumberRank.percentage}%" id="data-one">${courseNumberRank.percentage}%</span></dd>
		           	  </c:if>
		           </c:forEach>
	               
	           </dl>
	       </code>
	   </div>
   </c:if>
   
   <!--课程列表-->
   <div class="weui_cells weui_cells_access">
   <c:forEach items="${listDxjTravelRoute}" var="route" varStatus="i">
	   <div class="x-classlist-content-detail clearfix clearfloat">     
	       <img src="${basePath}${route.pictureUrl}" width="100%" height="100%">  
           <div class="tourism-info">
               <span class="tourism-l">${route.travelRouteName}</span>
               <span class="tourism-r">一日游</span>
           </div>
           <br/>
	       <span class="x-classlist-content-detail-text">
	           
	           <p class="x-classlist-content-detail-text-money x-classlist-content-detail-space">¥<span id = 'fee'>${route.fee}</span> 
               
	           </p>
	           
	          <p class="bm-l"><a onClick = "reserve(${route.id})" class="weui_btn weui_btn_plain_primary ">报名</a></p>
	       	  <p class="bm-r"><a href="${route.weixinUrl}" class="weui_btn weui_btn_plain_primary ">去看看</a></p>
	       
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
      <div class="weui_dialog_bd" id = "dialogmessage">您已订购了该旅游线路</div>
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
