<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
String xmid = request.getParameter("xmid");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>购买</title>   
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

function order()
{
	//保存订单信息
	var name = document.getElementById('name').value;
	var phonenumber = document.getElementById('phonenumber').value;
	var address = document.getElementById('address').value;
	var number = document.getElementById('number').value;
	var product = "邵阳秘制牙签牛肉";
	var reg = new RegExp("^[0-9]*$");
	if((!reg.test(phonenumber)) || (phonenumber.length != 11)){
		showAlert("输入的电话号码有误，请重新输入");
	}
	else
	{
		$.post('/weixin/cj/Cjaction!saveOrder.action',{name:name,phoneNumber:phonenumber,address:address,number:number,product:product},function(data)
		{
		},"text");	
		showAlert("成功生成订单！感谢您的购买，我们将在48小时内发货！");
	}
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
      <nav>立即购买</nav>
	<DIV class="main_container">
	
	<DIV id=u0_container class="u0_container">
	<DIV id="u0_img" class="u0_original"></DIV>
	<DIV id=u1 class="u1" >
	<DIV id=u1_rtf>&nbsp;</DIV></DIV>
	</DIV>
    
	<div class="content">
         <ol>
             <li>
                 <b>邵阳秘制牙签牛肉</b>
             </li>
             <li>
                 <span>请输入您的姓名</span>
             </li>
         </ol>
     </div>
	
	 <input name="name" type="text" id="name" value="姓名" size="30"               
		 onmouseover=this.focus();this.select();              
		 onclick="if(value==defaultValue){value='';this.style.color='#000'}"              
		 onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
		 style="color:#999; position:relative;left:30px;width:100px;height:25px;font-size:16px" 
	 />
	 
	 <div class="content">
         <ol>
             <li>
                 <span>请输入您的手机号码</span>
             </li>
         </ol>
     </div>
	 
	 <input name="phonenumber" type="text" id="phonenumber" value="手机号码" size="30"               
		 onmouseover=this.focus();this.select();              
		 onclick="if(value==defaultValue){value='';this.style.color='#000'}"              
		 onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
		 style="color:#999; position:relative;left:30px;width:150px;height:25px;font-size:16px" 
	 />
	 
	 <div class="content">
         <ol>
             <li>
                 <span>请输入您的收货地址</span>
             </li>
         </ol>
     </div>
	 
	 <input name="address" type="text" id="address" value="地址" size="30"               
		 onmouseover=this.focus();this.select();              
		 onclick="if(value==defaultValue){value='';this.style.color='#000'}"              
		 onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
		 style="color:#999; position:relative;left:30px;width:250px;height:25px;font-size:16px" 
	 />
	 
	 <div class="content">
         <ol>
             <li>
                 <span>请输入您的购买数量（份）</span>
             </li>
         </ol>
     </div>
	 
	 <input name="number" type="text" id="number" value="数量（份）" size="30"               
		 onmouseover=this.focus();this.select();              
		 onclick="if(value==defaultValue){value='';this.style.color='#000'}"              
		 onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
		 style="color:#999; position:relative;left:30px;width:100px;height:25px;font-size:16px" 
	 />
	 
	
	<div class="btn">
	<input onClick="order()"  type="button"  value="立即购买"></input>
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
                <p class="name">奖品列表</p>
            </a>
        </li>
        
         <li>
            <a href="#" onClick="myrecord()" class="cur">
                <div class="icon">
                    <b class="iconfont number"></b>
                </div>
                <p class="name">抽奖编号</p>
            </a>
        </li>
        
         <li>
            <a href="#" onClick="mygift()" class="cur">
                <div class="icon">
                    <b class="iconfont recoder"></b>
                </div>
                <p class="name">中奖记录</p>
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
