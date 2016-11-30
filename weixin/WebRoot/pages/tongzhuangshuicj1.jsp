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
				}
				else
				{
					if(choujiang)
					{
						showAlert("您已参加了此项抽奖，请点击屏幕下方按钮查看我的抽奖信息");
					}
					else
					{
						window.location.href='/weixin/pages/tzsinputphonenumber.jsp?user='+user+'&xmid='+xmid;
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

function showMessageCJBH(zjbm){  
	var messContent="<div style='font-size:20;padding:5px 10px 10px 10px;text-align:center;'>您的抽奖编号：</div>  <div style = 'font-size:22;color:#FF0000;text-align:center;'>"+zjbm+"<\div> <br/><div  style = 'font-size:20; color:#000000'>开奖日公布结果</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

var messageBox; (function(){ messageBox = new __messageBox(); })(); 


</script>

  </head>
  
  <BODY>
      <nav>奖品详情</nav>
	<DIV class="main_container">
	
	<DIV id=u0_container class="u0_container">
	<DIV id="u0_img" class="u0_original"></DIV>
	<DIV id=u1 class="u1" >
	<DIV id=u1_rtf>&nbsp;</DIV></DIV>
	</DIV>
    
    <div class="big-pic">
        <div class="state" id='u6'>
           <div class="state_l"></div>
           <div class="state_r"></div>
        </div>
	    <IMG id=u0 src="file/tongzhuangshui1.jpg" class="u0" >
    </div>
    
	<div class="btn">
	<input type="button"  onClick="cj()" value="立即抽奖"></input>
	</div>
	</DIV>
	<DIV id=u7 class="u7" >
	<DIV id=u7_rtf>
    
     <div class="content">
         <ol>
         	 
         	 <li>
                 <b>获得本次活天下桶装水的幸运粉丝为：</b>
                 </br><span style = "color:red; asign:center">188****5481</span>
                 </br><span style = "color:red; asign:center">150****8367</span>
                 </br><span style = "color:red; asign:center">151****1949</span>
                 </br><span style = "color:red; asign:center">187****4712</span>
                 </br><span style = "color:red; asign:center">136****2271</span>
                 </br><span style = "color:red; asign:center">133****1255</span>
                 </br><span style = "color:red; asign:center">138****8491</span>
                 </br><span style = "color:red; asign:center">186****3962</span>
                 </br><span style = "color:red; asign:center">182****9583</span>
                 </br><span style = "color:red; asign:center">132****0833</span>
             </li>
             
             <li>
                 <b>奖品名称：</b>
                 <span>岩井泉--生态溶滤水(18.9L)（可选择送2张票，或者送一桶水+1张水票）</span>
             </li>
             
             <li>
                 <b>奖品数量：</b>
                 <span>10份（每份2桶）</span>
             </li>
             
             <li>
                 <b>抽奖时段：</b>
                 <span>10月21号9:00-10月21号24:00</span>
             </li>
                          
             <li>
                 <b>领奖方式：</b>
                 <span>拨打统一订水热线：0731-85180007，即可免费送票、送水上门（限长沙市）！</span>
             </li>
         </ol>
     </div>
	
	
	<h1>奖品详情：</h1>
	</DIV>
	</DIV>
	<DIV>
		<IMG id=u0 src="file/tongzhuangshui2.jpg" class="u0"    style="width:100%; height: 236px">
		<DIV align="center" style="color:red; font-size: 22">
			<b><span>岩井泉生态溶虑水</span></b>
		</DIV>
	</DIV>
	
	<div class="content">
         <ol>
             <li>
                 <b>活天下桶装水特点：1、天然渗透，生态、天然的活性水；2、富含丰富的微量元素；3、水桶是食品级PC材质。</b>
             </li>
         </ol>
    </div>
    
	<DIV>
	<IMG id=u0 src="file/1001.jpg" class="u0"    style="width:100%; height: 236px">
	</DIV>
	
	
	
	<DIV>
	<IMG id=u0 src="file/2001.jpg" class="u0"    style="width:100%; height: 236px">
	</DIV>
	
	<DIV>
	<IMG id=u0 src="file/3001.jpg" class="u0"    style="width:100%; height: 236px">
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
