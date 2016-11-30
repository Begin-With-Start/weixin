<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>输入邀请码</title>   
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
    
	<script language="javascript" src="/weixin/pages/jquery.js"></script>
	<script type="text/javascript" src="/weixin/pages/file/scripts/MessageBox.js" charset='utf-8'></script>
<script type="text/javascript">
var cjarr = new Array();
var input = true;

$(document).ready(function(){
	setTimeout(function(){ 
		loadbutton();  
	},100); 
});

function loadbutton()
{
	//判断是否已经输入过邀请码
	var user = "<%=user%>";
	$.post('/weixin/cj/Cjaction!getInviteRecordByUserId.action',{userId:user},function(data){
		if(data == "0")
		{
			input = false;
			$div = $("#button");
			$div.html(" <input  type='button' value='确定' onclick='javascript:inputcode();return false;'>");		
		}
		else
		{
			$div = $("#button");
			$div.html(" <input  type='button' value='确定' onclick='javascript:inputcodetwice();return false;'>");		
		}
		
		$div = $("#button1");
		$div.html(" <input  type='button' value='生成我的邀请码' onclick='javascript:createcode();return false;'>");	
		
		$div = $("#rule");
		$div.html("输入好友给您提供的邀请码，然后点击“确定”按钮，您的好友将会获得相应积分！你是不是也想免费兑换阿克苏苹果、双肩背包等精美礼品呢？点击“生成我的邀请码”！");
	},"json");		
}

function createcode()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/score/myinvitecode.jsp?user='+user;	
}


function inputcode()
{
	var code = document.getElementById('code').value;
	var user = "<%=user%>";
	if(input)
	{
		showAlert("您已输入邀请码，请勿重复操作，谢谢！");	
	}else
	{
		$.post('/weixin/cj/Cjaction!saveInviteRecord.action',{userId:user, code:code},function(data){
			if("0" == data)
			{
				showAlert("您输入的邀请码有误，请核对后再输入！");		
			}else if("-1" == data){
				showAlert("请勿输入自己生成的邀请码！");		
			}
			else if("1" == data)
			{
				showAlert("输入邀请码正确，操作成功！");
				input = true;
			}
		},"json");	
	}
}

function inputcodetwice()
{
	showAlert("您已输入邀请码，请勿重复操作，谢谢！");	
}

function showAlert(str)
{
	var messContent="<div style='background:#eee;font-size:16px;line-height:1.5rem;padding:5px 10px 10px 10px;text-align:center;'>"+str+"</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

var messageBox; (function(){ messageBox = new __messageBox(); })(); 

</script>
	<style type="text/css">
	
	body {background-image: url(../file/background.jpg)}
	h2.pos_abs
	{
	position:absolute;
	left:100px;
	top:150px
	}
	
	</style>
  </head>
  
   
  
  <BODY >
  	<nav>输入邀请码</nav>
  	<DIV id=u0_container class="u0_container">
	<DIV id="u0_img" class="u0_original"></DIV>
	<DIV id=u1 class="u1" >
	<DIV id=u1_rtf>&nbsp;</DIV></DIV>
	</DIV>
	
	
	<div style="height:80px;"></div>
	<div id='rule' style = "font-size:16px; line-height:1.4rem; color:#FBFFFD; margin:10px 0px; text-indent:2em;padding:10px;">	</div>
	
	<input name="code" type="text" id="code" value="邀请码" size="20"               
		 onmouseover=this.focus();this.select();              
		 onclick="if(value==defaultValue){value='';this.style.color='#000'}"              
		 onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
		 style="color:#999; position:relative;left:35%;width:30%;height:30px;font-size:16px" 
	 />
	 
	 
  	<div id='button' class="btn" >
	</div>
  	
  	<div id='button1' class="btn" >
	</div>
	
  	<div id='text1'>	</div>
  	
  </BODY>
	
</html>
