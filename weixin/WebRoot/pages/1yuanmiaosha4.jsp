<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
String xmid = request.getParameter("xmid");
//String addrip = request.getParameter("addrip");
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
	var addr = document.getElementById('addr').value;
	var total = document.getElementById('total').value;
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
					fenshu = qgxm.fenshu;
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
								$.post('/weixin/cj/Cjaction!saveQgjl.action',{name:name,tel:phonenumber,xmid:xmid,userid:user,addr:addr,total:total},function(data)
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
	    <IMG id=u0 src="file/pingguo.jpg" class="u0" >
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
                 <c>历经三次霜降的冰糖心精品大果</c>
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
     
     <input name="total" type="text" id="total" value="数量（箱）" size="30"               
		 onmouseover=this.focus();this.select();              
		 onclick="if(value==defaultValue){value='';this.style.color='#000'}"              
		 onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
		 style="color:#999; position:relative;left:10%;width:30%;height:25px;font-size:16px" 
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
                 <span>新疆阿克苏冰糖心苹果</span>
             </li>
         </ol>
     </div>
	
	<div class="content">
         <ol>
             <li>
                 <b>价格：</b>
                 <span>原价108元，现价98元！</span>
             </li>
         </ol>
     </div>
	
	<div class="content">
         <ol>
             <li>
                 <b>领取方式：</b>
                 <span>送货上门（请填写正确的收货地址）</span>
             </li>
         </ol>
     </div>
     
     <div class="content">
         <ol>
             <li>
                 <b>配送范围：</b>
                 <span>湖南全省配送（14个地州市主城区）</span>
             </li>
         </ol>
     </div>
     
     <div class="content">
         <ol>
             <li>
                 <b>到货时间：</b>
                 <span>预计11月25日到货，到货后48-96小时送达</span>
             </li>
         </ol>
     </div>
     
     <div class="content">
         <ol>
             <li>
                 <b>温馨提醒：</b>
                 <span>支持开箱验货，货到付款，请收到货后请一定要开箱验货，认真检查一下，发现问题请当场与配送人员协商处理，建议尽量不要托人代收，如找人代收我们将视为本人签收。希望亲们理解。</span>
             </li>
         </ol>
     </div>
     
    <DIV>
		<IMG id=u0 src="file/pingguo1.jpg" class="u0"    style="width:100%; height: 236px">
	</DIV>
	
     
	<div class="content">
         <ol>
             <li>
                 <b>首批阿克苏到货后好评如潮，它的清香脆口，汁水丰沛，口感甘甜都在这里↓↓~咬一口正宗阿克苏冰糖心苹果，至香至脆，再一口至心至甜，直至心底。越是靠近那肉眼可见的冰糖心，越是香甜如蜜，吃后嘴留有清香。精选果径85左右的果子，原产地直采，确保送到微信网友手中的每一个阿克苏苹果都是正宗的~</b>
             </li>
         </ol>
    </div>
	
	<DIV>
		<IMG id=u0 src="file/pingguo2.jpg" class="u0"    style="width:100%; height: 236px">
	</DIV>
	
	<div class="content">
         <ol>
             <li>
                 <b>实实在在的冰糖心，好吃看的见！</b>
             </li>
         </ol>
    </div>
    
	<DIV>
		<IMG id=u0 src="file/pingguo3.jpg" class="u0"    style="width:100%; height: 236px">
	</DIV>
	
	<div class="content">
         <ol>
             <li>
                 <b>历经3次霜降的严酷洗礼才造就了一颗醇甜的冰糖心，阿克苏冰糖心苹果对生长环境要求极高，只有红旗坡少量区域才能种植。为了让大家尝到正宗甜蜜的阿克苏苹果，我们耐心等待，等到它经过三次霜降后口感才是上佳。这样的霜降更有利于果实的着色和糖分积累，让阿克苏糖心苹果鲜红透亮，果肉鲜嫩，汁多甘甜，酥脆爽口，同时，由于昼夜温差较大，阿克苏糖心苹果不需要使用任何农药进行除虫，真正绿色，健康的水果。</b>
             </li>
         </ol>
    </div>
    
	<DIV>
		<IMG id=u0 src="file/pingguo4.jpg" class="u0"    style="width:100%; height: 236px">
	</DIV>

	<div class="content">
         <ol>
             <li>
                 <b>为了吃到正宗的阿克苏冰糖心苹果，这一切的等待都是值得的~</b>
             </li>
         </ol>
    </div>
    
    <DIV>
		<IMG id=u0 src="file/pingguo5.jpg" class="u0"    style="width:100%; height: 236px">
	</DIV>

	<div class="content">
         <ol>
             <li>
                 <b>冰糖心苹果同样是红富士苹果，但它特别的地方在哪里？是新疆的气候不一样，每天长时间太阳照，昼夜温差大，而且采摘时间在霜降以后，所以说是大自然造就了冰糖心。霜降的天气晚上特别冷使苹果的糖分部分凝结了。所以也不是每个苹果都有明显的糖心，毕竟是大自然的产物，但是这里的苹果含糖量相对要高一些，得益于它的特殊气候~</b>
             </li>
         </ol>
    </div>
    
    <DIV>
		<IMG id=u0 src="file/pingguo6.jpg" class="u0"    style="width:100%; height: 236px">
	</DIV>

	<div class="content">
         <ol>
             <li>
                 <b>现在下单，预计25日左右到货，到货后按订单先后顺序进行配送！</b>
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
