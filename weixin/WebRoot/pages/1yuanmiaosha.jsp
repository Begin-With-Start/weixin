<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
String xmid = request.getParameter("xmid");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>秒杀</title>   
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
var qianggou = true;

$(document).ready(function(){
	setTimeout(function(){ 
			loadqgxm();  
		 },100); 
});

function checkuser(){
	var user = "<%=user%>";
	var xmid = "<%=xmid%>";
	$.post('/weixin/cj/Cjaction!getAllQgjlByUser.action',{userid:user},function(data){
		for (var i = 0 ; i<data.length ;i++)
		{
			if(!cjarr.contains(data[i].xmid))
			{
				cjarr.push(data[i].xmid);
			}
		}
		
		if(!cjarr.contains(xmid)) {
			qianggou = false;
		}
	},"json");
}

function loadqgxm()
{
	var xmid = "<%=xmid%>";
	$.post('/weixin/cj/Cjaction!getQgxmByXmid.action',{xmid:xmid},function(data){
		var qgxm = data[0];
		var now = new Date();
		$div = $("#button");
		var fenshu = qgxm.fenshu;
		var qgsj = qgxm.qgsj;  qgsj = qgsj.replace(/-/g,"/"); var sj =  new Date(Date.parse(qgsj)); 
		if(now < sj)
		{
			$div.html(" <input  type='button' value='敬请期待' onclick='javascript:tx();return false;'>");		
		}
		else
		{
			if(fenshu > 0)
			{
				$div.html(" <input  type='button' value='立即抢购' onclick='javascript:cj();return false;'>");
			}
			else
			{
				$div.html(" <input  type='button' value='立即抢购' onclick='javascript:over();return false;'>");	
			}
		}
	},"json");
}

function tx()
{
	showAlert("秒杀暂未开始，感谢您的关注！");	
}

function over()
{
	showAlert("此宝贝已抢购完，感谢您的关注！");	
}

function cj()
{
	var user = "<%=user%>";
	var xmid = "<%=xmid%>";
	var name = document.getElementById('name').value;
	var phonenumber = document.getElementById('phonenumber').value;
	var now = new Date();
	var fenshu;
	var reg = new RegExp("^[0-9]*$");
	if((!reg.test(phonenumber)) || (phonenumber.length != 11)){
		showAlert("输入的电话号码有误，请重新输入");
	}
	else
	{
		if("姓名"==name)
		{
			showAlert("姓名不能为空");
		}
		else
		{
			$.post('/weixin/cj/Cjaction!getAllQgxm.action',null,function(data){
				for (var i = 0 ; i<data.length ;i++){
					var qgxm =data[i];
					fenshu == qgxm.fenshu
					var qgsj = qgxm.qgsj;  qgsj = qgsj.replace(/-/g,"/"); var sj =  new Date(Date.parse(qgsj)); 
					if( xmid == qgxm.xmid)
					{
						if(qianggou)
						{
							showAlert("您已参加了此项秒杀活动，感谢您的参与！");
						}
						else
						{
							if(fenshu == 0)
							{
								showAlert("物品已秒杀完毕，感谢您的参与！");
							}
							else
							{
								$.post('/weixin/cj/Cjaction!saveQgjl.action',{name:name,tel:phonenumber,xmid:xmid,userid:user},function(data)
								{
								},"text");	
								showAlert("秒杀成功！感谢您的购买！");
							}
							break;
						}
					}
				}
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

checkuser();

function showAlert(str)
{
	var messContent="<div style='background:#eee;font-size:14px;line-height:1.5rem;padding:5px 10px 10px 10px;text-align:center;'>"+str+"</div>";  // 弹出提示框  
	messageBox.showMessageBox('',messContent,250); 
}

function list()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/1yuangou.jsp?user='+user;
}

function record()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/qianggoujilu.jsp?user='+user;
}

function rule()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/qgrule.jsp?user='+user;
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
	    <IMG id=u0 src="file/beizi_xiao.jpg" class="u0" >
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
                 <c>原价488元，抢购价199元！</c>
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
    
    
   
    
	<div id='button' class="btn">
	</div>
	
	</DIV>
	<DIV id=u7 class="u7" >
	<DIV id=u7_rtf>
    
     <div class="content">
         <ol>
             <li>
                 <b>物品名称：</b>
                 <span>梦洁200*230春秋棉被2.5kg</span>
             </li>
         </ol>
     </div>
	
	<div class="content">
         <ol>
             <li>
                 <b>图片仅供参考，以实体店实物为准！</b>
             </li>
         </ol>
    </div>
	
	<div class="content">
         <ol>
             <li>
                 <b> 需要到梦洁宝马家居店向店员出示“湖南真好玩”秒杀成功订单信息，现场提货并付款。</b>
             </li>
         </ol>
    </div>
	
	<div class="content">
         <ol>
             <li>
                 <b>实体店地址：韶山南路宝马家私城1楼梦洁家居——潇湘晨报社对面（附近公交车站：市中心医院站、潇湘晨报站）</b>
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
                <p class="name">商品列表</p>
            </a>
        </li>
        
         <li>
            <a href="#" onClick="record()" class="cur">
                <div class="icon">
                    <b class="iconfont recoder"></b>
                </div>
                <p class="name">抢购成功记录</p>
            </a>
        </li>
        
         <li>
            <a href="#" onClick="rule()" class="cur">
                <div class="icon">
                    <b class="iconfont rule"></b>
                </div>
                <p class="name">秒杀说明</p>
            </a>
        </li>
    </ul>
</div>
	</BODY>
	
</html>
