<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
String xmid = request.getParameter("xmid");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>兑换</title>   
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

$(document).ready(function(){
	setTimeout(function(){ 
			loadqgxm();  
		 },100); 
});


function loadqgxm()
{
	$div = $("#button");
	$div.html(" <input  type='button' value='立即兑换' onclick='javascript:dh();return false;'>");
}

function tx()
{
	showAlert("秒杀暂未开始，感谢您的关注！");	
}

function over()
{
	showAlert("此宝贝已抢购完，感谢您的关注！");	
}

function dh()
{
	$div = $("#button");
	$div.html(" <input  type='button' value='稍等'>");
	var user = "<%=user%>";
	var xmid = "<%=xmid%>";
	var name = document.getElementById('name').value;
	var phonenumber = document.getElementById('phonenumber').value;
	var now = new Date();
	var fenshu;
	var jiage;
	var score;
	var reg = new RegExp("^[0-9]*$");
	if((!reg.test(phonenumber)) || (phonenumber.length != 11)){
		showAlert("输入的电话号码有误，请重新输入");
		$div.html(" <input  type='button' value='立即兑换' onclick='javascript:dh();return false;'>");
	}
	else
	{
		if("姓名"==name)
		{
			showAlert("姓名不能为空");
			$div.html(" <input  type='button' value='立即兑换' onclick='javascript:dh();return false;'>");
		}
		else
		{
			$.post('/weixin/cj/Cjaction!getDhxmByxmid.action',{xmid:xmid},function(data){
				var dhxm =data[0];
				fenshu = dhxm.fenshu
				jiage = dhxm.jiage;
				
				//获取用户的积分
				$.post('/weixin/cj/Cjaction!getCodeByUserId.action',{userId:user},function(data){
					if(data == "0")
					{
						showAlert("对不起！您暂无邀请码！请先获取您的邀请码，然后邀请好友关注“湖南真好玩”获取积分吧！");
						$div.html(" <input  type='button' value='立即兑换' onclick='javascript:dh();return false;'>");
					}
					else
					{
						var scoreCode = data[0];
						score = scoreCode.score;
						if(fenshu == 0)
						{
							showAlert("对不起！该物品已兑换结束，请重新选择其他礼品！");
							$div.html(" <input  type='button' value='立即兑换' onclick='javascript:dh();return false;'>");
						}
						else
						{
							if(score < jiage)
							{
								showAlert("对不起！您的积分不足以兑换该礼品！");
								$div.html(" <input  type='button' value='立即兑换' onclick='javascript:dh();return false;'>");
							}else
							{
								$.post('/weixin/cj/Cjaction!saveJfdhjl.action',{name:name,tel:phonenumber,xmid:xmid,userid:user,jiage:jiage},function(data)
								{
									showAlert("兑换成功！感谢您的关注！");
									$div.html(" <input  type='button' value='立即兑换' onclick='javascript:dh();return false;'>");
								},"text");	
							}
						}
					}
				},"json");
			},"json");
		}
	}
}

Array.prototype.contains = function (element) { 
	for (var i = 0; i < this.length; i++) { 
        if (this[i] == element) { 
            return true; 
        } 
    } 
    return false; 
} 

function showAlert(str)
{
	var messContent="<div style='background:#eee;font-size:14px;line-height:1.5rem;padding:5px 10px 10px 10px;text-align:center;'>"+str+"</div>";  // 弹出提示框  
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

function myscore()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/score/myinvitecode.jsp?user='+user;
}

var messageBox; (function(){ messageBox = new __messageBox(); })(); 

</script>

  </head>
  
  <BODY>
      <nav>宝贝详情</nav>
	<DIV class="main_container">
	
	<DIV id=u0_container class="u0_container">
	<DIV id="u0_img" class="u0_original"></DIV>
	<DIV id=u1 class="u1" >
	<DIV id=u1_rtf>&nbsp;</DIV></DIV>
	</DIV>
    
    <div class="big-pic">
	    <IMG id=u0 src="file/tongzhuangshui.jpg" class="u0" >
    </div>
    
     <div class="content">
         <ol>
             <li>
             </li>
         </ol>
     </div>
     <div class="content">
         <ol>
             <li>
             </li>
         </ol>
     </div>
     
     
     <input name="name" type="text" id="name" value="姓名" size="20"               
		 onmouseover=this.focus();this.select();              
		 onclick="if(value==defaultValue){value='';this.style.color='#000'}"              
		 onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
		 style="color:#999; position:relative;left:10%;width:20%;height:25px;font-size:16px" 
	 />
    
     <input name="phonenumber" type="text" id="phonenumber" value="手机号码" size="30"               
		 onmouseover=this.focus();this.select();              
		 onclick="if(value==defaultValue){value='';this.style.color='#000'}"              
		 onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
		 style="color:#999; position:relative;left:40px;width:40%;height:25px;font-size:16px" 
	 />
    
    <div class="content">
         <ol>
             <li>
             </li>
         </ol>
     </div>
     
    <input name="addr" type="text" id="addr" value="收货地址" size="30"               
		 onmouseover=this.focus();this.select();              
		 onclick="if(value==defaultValue){value='';this.style.color='#000'}"              
		 onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
		 style="color:#999; position:relative;left:10%;width:60%;height:25px;font-size:16px" 
	 />
   
    
	<div id='button' class="btn">
	</div>
	
	</DIV>
	<DIV id=u7 class="u7" >
	<DIV id=u7_rtf>
    
     <div class="content">
         <ol>
             <li>
                 <b>物品名称：</b>
                 <span>岩井泉--生态溶滤水(18.9L)</span>
             </li>
         </ol>
     </div>
	
	<div class="content">
         <ol>
             <li>
                 <b>领取方式：</b>
                 <span>拨打统一订水热线：0731-85180007，即可免费送票、送水上门（限长沙市）！</span>
             </li>
         </ol>
     </div>
     
	<div class="content">
         <ol>
             <li>
                 <b>活天下桶装水特点：1、天然渗透，生态、天然的活性水；2、富含丰富的微量元素；3、水桶是食品级PC材质。</b>
             </li>
         </ol>
    </div>
	
<div class="clear h50"></div>
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
            <a href="#" onClick="myscore()" class="cur">
                <div class="icon">
                    <b class="iconfont rule"></b>
                </div>
                <p class="name">我的积分</p>
            </a>
        </li>
    </ul>
</div>
	</BODY>
	
</html>
