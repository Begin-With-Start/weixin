<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
String xmid = request.getParameter("xmid");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>抽奖</title>   
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
var choujiang = true;

$(document).ready(function(){
	setTimeout(function(){ 
			loadkj();  
		 },100); 
});

function loadkj(){
	$div = $("#u6");
	var user = "<%=user%>";
	var xmid = "<%=xmid%>";
	var now = new Date();
	$.post('/weixin/cj/Cjaction!getAllCjxm.action',null,function(data){
		for (var i = 0 ; i<data.length ;i++){
			var cjxm =data[i];
			var kjsj = cjxm.kjsj;  kjsj = kjsj.replace(/-/g,"/"); var sj =  new Date(Date.parse(kjsj)); 
			if(xmid == cjxm.xmid)
			{
				if (sj<now){
					$div.find("div").eq(0).html("<p><span>￥0</span>");
					$div.find("div").eq(1).html("<p><span>已开奖</span>");
				}else{
					$div.find("div").eq(0).html("<p><span>￥0</span>");
					$div.find("div").eq(1).html("<p><span>尚未开奖</span>");
				}
				break;
			}
		}
	},"json");
}


function checkuser(){
	var user = "<%=user%>";
	var xmid = "<%=xmid%>";
	$.post('/weixin/cj/Cjaction!getAllZjjlByUser.action',{userid:user},function(data){
		for (var i = 0 ; i<data.length ;i++)
		{
			if(!cjarr.contains(data[i].xmid))
			{
				cjarr.push(data[i].xmid);
			}
		}
		
		if(!cjarr.contains(xmid)) {
			choujiang = false;
		}
	},"json");
}

function cj()
{
	var user = "<%=user%>";
	var xmid = "<%=xmid%>";
	var now = new Date();
	//保存电话号码
	var phonenumber = document.getElementById('key').value;
	var reg = new RegExp("^[0-9]*$");
		
		$.post('/weixin/cj/Cjaction!getAllCjxm.action',null,function(data){
			for (var i = 0 ; i<data.length ;i++){
				var cjxm =data[i];
				if( xmid == cjxm.xmid)
				{
					var kjsj = 	cjxm.kjsj;
					kjsj = kjsj.replace(/-/g,"/"); sj =  new Date(Date.parse(kjsj));
					if(sj < now)
					{
						showAlert("此项抽奖活动已经结束，感谢您的参与！");
						if (document.getElementById("btn").isclick == 1 )
						{
							javascript:history.back(-1);
						}
					}
					else
					{
						if(choujiang)
						{
							showAlert("您已参加了此项抽奖，请点击屏幕下方按钮查看我的抽奖信息");
						}
						else
						{
							if((!reg.test(phonenumber)) || (phonenumber.length != 11)){
								showAlert("输入的电话号码有误，请重新输入");
							}
							else
							{
								$.post('/weixin/cj/Cjaction!updateUserPhoneNumber.action',{openId:user,phoneNumber:phonenumber},function(data)
								{
								},"text");
								
								$.post('/weixin/cj/Cjaction!savecj.action',{userid:user,xmid:xmid,phoneNumber:phonenumber},function(data)
								{
									var result = $.trim(data);
									var cjjg = result.substr(0,1);
									var zjbm = result.substr(1,6);
									if (cjjg=="0")
									{
										showMessageCJBH(zjbm);
									}else if (cjjg=="2")
									{
										showMessage10yuan(zjbm);
									}else if (cjjg=="3")
									{
										showMessage5yuan(zjbm);
									}else
									{
										showAlert("谢谢参与，祝您好运");
									}		
								},"text");
																
								choujiang = true;
							}
						}	
					}
					break;
				}
			}
		},"json");
	
}

function myrecord()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/choujiangjilu.jsp?user='+user;
}

function mygift()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/zhongjiangjilu.jsp?user='+user;
}

function prizelist()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/0yuangou.jsp?user='+user;
}

function rule()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/rule.jsp?user='+user;
}

Array.prototype.contains = function (element) { 
	for (var i = 0; i < this.length; i++) { 
        if (this[i] == element) { 
            return true; 
        } 
    } 
    return false; 
} 

checkuser();

function showAlert(str)
{
	var messContent="<div style='background:#eee;font-size:14px;line-height:1.5rem;padding:5px 10px 10px 10px;text-align:center;'>"+str+"</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

function showMessage5yuan(zjbm){  
	var messContent="<div style='font-size:16;padding:5px 10px 10px 10px;text-align:center;'>您的抽奖编号：</div>  <div style = 'font-size:24;color:#FF0000;text-align:center;'>"+zjbm+"<\div><div  style = 'font-size:22; color:#000000'>8月20日17点公布结果</div> <br/> <br/><div style = 'font-size:16;color:#000000'>同时恭喜您获三等奖</div><div><img src = '/weixin/pages/file/5yuan_xiao.jpg'/></div> <div style = 'font-size:16;color:#000000'>96360商城代金券5元</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

function showMessage10yuan(zjbm){  
	var messContent="<div style='font-size:16;padding:5px 10px 10px 10px;text-align:center;'>您的抽奖编号：</div>  <div style = 'font-size:24;color:#FF0000;text-align:center;'>"+zjbm+"<\div> <br/><div  style = 'font-size:22; color:#000000'>开奖日公布结果</div> <br/> <br/><div style = 'font-size:16;color:#000000'>获二等奖</div><div><img src = '/weixin/pages/file/10yuan_xiao.jpg'/></div> <div style = 'font-size:16;color:#000000'>96360商城代金券10元</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

function showMessageCJBH(zjbm){  
	var messContent="<div style='font-size:20;padding:5px 10px 10px 10px;text-align:center;'>您的抽奖编号：</div>  <div style = 'font-size:22;color:#FF0000;text-align:center;'>"+zjbm+"<\div> <br/><div  style = 'font-size:20; color:#000000'>开奖日公布结果</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

var messageBox; (function(){ messageBox = new __messageBox(); })(); 


</script>

  </head>
  
  <BODY>
      <nav>参与抽奖</nav>
	<DIV class="main_container">
	
	<DIV id=u0_container class="u0_container">
	<DIV id="u0_img" class="u0_original"></DIV>
	<DIV id=u1 class="u1" >
	<DIV id=u1_rtf>&nbsp;</DIV></DIV>
	</DIV>
    
	<div class="content">
         <ol>
             <li>
                 <b>联系手机号码</b>
             </li>
             <li>
                 <span>手机号码是兑奖的唯一依据，请正确填写</span>
             </li>
         </ol>
     </div>
	
	 <input name="phonenumber" type="text" id="key" value="请输入手机号码" size="30"               
		 onmouseover=this.focus();this.select();              
		 onclick="if(value==defaultValue){value='';this.style.color='#000'}"              
		 onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
		 style="color:#999; position:relative;left:30px;width:200px;height:25px;font-size:16px" 
	 />
	 
	
	<div class="btn">
	<input onClick="cj()"  type="button"  value="参与抽奖"></input>
	</div>
	</DIV>
	
    <div class="clear h50"></div>
    
    <div class="hytpl-navbar hytpl-navbar-new">
    <ul class="bar-list">
        <li>
            <a href="#" onClick="prizelist()" class="cur">
                <div class="icon">
                    <b class="iconfont list"></b>
                </div>
                <p class="name">抽奖活动</p>
            </a>
        </li>
        
         <li>
            <a href="#" onClick="myrecord()" class="cur">
                <div class="icon">
                    <b class="iconfont number"></b>
                </div>
                <p class="name">抽奖记录</p>
            </a>
        </li>
        
         <li>
            <a href="#" onClick="mygift()" class="cur">
                <div class="icon">
                    <b class="iconfont recoder"></b>
                </div>
                <p class="name">我的奖品</p>
            </a>
        </li>
        
        <li>
            <a href="#" onClick="rule()" class="cur">
                <div class="icon">
                    <b class="iconfont rule"></b>
                </div>
                <p class="name">大奖玩法</p>
            </a>
        </li>
    </ul>
</div>
	</BODY>
	
</html>
