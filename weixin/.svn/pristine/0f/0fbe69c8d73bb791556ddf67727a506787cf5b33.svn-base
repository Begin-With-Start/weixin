<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
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
    

<title>玩法规则</title>

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

</script>
</head>

<body>
 <nav>玩法规则</nav>
<div class="block">
    <h1>抽奖规则一：</h1>
    <p>点击“参与抽奖”之后系统将提醒您是否中奖。</p>
    <p>点击“我的奖品”可查看您的中奖记录。</p>
    
    
    <h1>抽奖规则二：</h1>
    <p>点击“参与抽奖”之后您将获得抽奖编号。</p>
    <p>中奖编号将在开奖当日公布在本抽奖页面。</p>
    <p>点击“抽奖记录”可查看自己的历次抽奖编号。</p>
    <p>将开奖日收盘时的上证指数（周末或节假日则取前一工作日收盘时的上证指数）的6位数字按照倒序排列为一组新的6位数字作为“中奖编号”。</p>
    <p>如果您的“抽奖编号”和这个“中奖编号”一致则为中大奖，如果没有与中奖编号一致的抽奖编号，则取与中奖编号差值绝对值最小的抽奖编号（若存在两个抽奖编号与中奖编号的绝对值相同，则选取较大的抽奖编号）作为大奖得主。 </p>

	<p>例如2015年6月25号收盘时上证指数为3627.74点，当天开奖的中奖编号就为477263！抽奖编号为477263或者与477263最接近的编号即为大奖得主。 </p>
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
            <a href="#" class="cur">
                <div class="icon">
                    <b class="iconfont rule"></b>
                </div>
                <p class="name">大奖玩法</p>
            </a>
        </li>
    </ul>
</div>
<input type='hidden' id='userid' value='<%=user%>'/>
</body>
</html>