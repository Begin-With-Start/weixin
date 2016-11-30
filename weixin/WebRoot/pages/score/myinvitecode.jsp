<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>我的邀请码</title>   
	<meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    
 <link type="text/css" rel="stylesheet" href="file/css/reset.css">
 <link type="text/css" rel="stylesheet" href="file/css/default.css">
 <link type="text/css" rel="stylesheet" href="file/css/content.css">
 <link type="text/css" rel="stylesheet" href="file/css/score.css">  
	
	<script language="javascript" src="/weixin/pages/jquery.js"></script>
	<script type="text/javascript" src="/weixin/pages/file/scripts/MessageBox.js" charset='utf-8'></script>
<script type="text/javascript">
var cjarr = new Array();
var qianggou = true;

$(document).ready(function(){
	setTimeout(function(){ 
			loadcode();  
		 },100); 
});

function loadcode()
{
	var user = "<%=user%>";
	$.post('/weixin/cj/Cjaction!getCodeByUserId.action',{userId:user},function(data){
		if(data == "0")
		{
			$div = $("#button");
			$div.html(" <input  type='button' value='生成邀请码' onclick='javascript:createcode();return false;'>");		
		}
		else
		{
			
			var scoreCode = data[0];
			var invitationCode = scoreCode.invitationCode;
			var score = scoreCode.score;
			$div = $("#mycode");
			$div.html("我的邀请码");
			$div = $("#code");
			$div.html(invitationCode);
			$div = $("#myscore");
			$div.html("我的积分");
			$div = $("#score");
			$div.html(score+"积分");
			$div = $("#title");
			$div.html("积分规则");
		}
		$div = $("#rule");
		$div.html("点击“生成邀请码”之后将生成一个随机的六位数邀请编码，当您邀请好友关注《湖南真好玩》公众号之后，由好友在“帮TA加分”页面输入您的邀请码，那么您的积分会相应增加，每个好友可为您增加1个积分，获得积分后可在《积分商城》兑换相应的礼品，积分越多，礼品越多，赶快参与吧！");
	},"json");
}

function createcode()
{
	var user = "<%=user%>";
	$.post('/weixin/cj/Cjaction!saveInviteCode.action',{userId:user},function(data){
		window.location.reload();
	},"text");
}

function showAlert(str)
{
	var messContent="<div style='background:#eee;font-size:16px;line-height:1.5rem;padding:5px 10px 10px 10px;text-align:center;'>"+str+"</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

function list()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/score/jifenduihuan.jsp?user='+user;
}

function record()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/score/duihuanjilu.jsp?user='+user;
}

var messageBox; (function(){ messageBox = new __messageBox(); })(); 

</script>
<style type="text/css">

body {background-image: url(../file/scoreback1.jpg)}
h2.pos_abs
{
position:absolute;
left:100px;
top:150px
}

</style>
  </head>
  
  <body>
  	<div id='showall'>
  	</div>
  	<div id='button' class="btn">
	</div>
  	<div style="height:5px;"></div>
  	<div id='mycode' style="font-size:22px; font-weight:700; line-height:1.5; color:#FBFFFD; text-align:center"></div>
	<div id='code' style="font-size:28px; font-weight:700; line-height:1.5; color:yellow; text-align:center"></div>
	<div style="height:8px;"></div>
	<div id='myscore' style="font-size:22px; font-weight:700; line-height:1.5; color:#FBFFFD; text-align:center"></div>
  	<div id='score' style="font-size:28px; font-weight:700; line-height:1.5; color:yellow; text-align:center"></div>
  	
  	<div id='text2' class="pos_abs"> </div>
  	<div style="height:18px;"></div>
  	<div id='title' style = "font-size:20px; font-weight:bold; color:#FBFFFD; line-height:2rem; border-bottom:1px #ccc solid; border-top:1px #ccc solid;padding:10px;"></div>
  	<div id='rule' style = "font-size:16px; line-height:1.4rem; color:#FBFFFD; margin:10px 0px; text-indent:2em;padding:10px;">	</div>
<div class="hytpl-navbar hytpl-navbar-new">
    <ul class="bar-list">
        <li>
            <a href="#" onClick="list()" class="cur">
                <div class="icon">
                    <b class="iconfont list"></b>
                </div>
                <p class="name">礼品列表</p>
            </a>
        </li>
        
         <li>
            <a href="#" onClick="record()" class="cur">
                <div class="icon">
                    <b class="iconfont recoder"></b>
                </div>
                <p class="name">兑换记录</p>
            </a>
        </li>
        
         <li>
            <a href="#" class="cur">
                <div class="icon">
                    <b class="iconfont rule"></b>
                </div>
                <p class="name">我的积分</p>
            </a>
        </li>
    </ul>
</div>


  </body>
	
</html>
