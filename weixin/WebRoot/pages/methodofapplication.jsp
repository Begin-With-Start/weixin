<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
String xmid = request.getParameter("xmid");
String yhqbm = request.getParameter("yhqbm");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    

<title>使用方法</title>

 <link type="text/css" rel="stylesheet" href="file/css/reset.css">
 <link type="text/css" rel="stylesheet" href="file/css/default.css">
 <link type="text/css" rel="stylesheet" href="file/css/rule.css">
 <script language="javascript" src="/weixin/pages/jquery.js"></script>
	<script type="text/javascript" src="/weixin/pages/file/scripts/MessageBox.js" charset='utf-8'></script>
<script type="text/javascript">


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


</script>
</head>

<body>
 <nav>使用方法</nav>
<div class="block">
    <h1>第一步：</h1>
    
    <div id='copy' style = "font-size: 14px;
	    line-height: 1.4rem;
	    color: #404040;
	    margin: 10px 0px;
	    text-indent: 2em;">
	         长按   <a style ="color:red"><%=yhqbm%></a>  复制优惠券编码，以便使用时粘贴。
    </div>
    
    <h1>第二步：</h1>
    <p>长按下方二维码，关注《潇湘晨报96360》订阅号。</p>
    <DIV align="center">
	<IMG id=u0 src="file/96360qrcode.JPG" class="u0"    style="width:60%; height: 200px">
	</DIV>
	
	<h1>第三步：</h1>
    <p>进入《潇湘晨报96360》订阅号，如下图所示点击“商品购买”，点击系统回复消息之后进入商品选购页面选购商品。</p>
    <DIV align="center">
	<IMG id=u0 src="file/96360jietu.JPG" class="u0"    style="width:80%; height: 420px">
	</DIV>
	
	<h1>第四步：</h1>
    <p>勾选“使用优惠卡”，粘贴或输入卡号、密码使用，其中密码为：123456。</p>
    <DIV align="center">
	<IMG id=u0 src="file/shuruyouhuika.jpg" class="u0"    style="width:80%; height: 420px">
	</DIV>
	
</div>

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
<input type='hidden' id='userid' value='<%=user%>'/>
<input type='hidden' id='bm' value='<%=yhqbm%>'/>
</body>
</html>