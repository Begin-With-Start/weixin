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
    

<title>秒杀说明</title>

 <link type="text/css" rel="stylesheet" href="file/css/reset.css">
 <link type="text/css" rel="stylesheet" href="file/css/default.css">
 <link type="text/css" rel="stylesheet" href="file/css/rule.css">
 <script language="javascript" src="/weixin/pages/jquery.js"></script>
	<script type="text/javascript" src="/weixin/pages/file/scripts/MessageBox.js" charset='utf-8'></script>
<script type="text/javascript">

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

</script>
</head>

<body>
 <nav>秒杀说明</nav>
<div class="block">
    <h1>说明：</h1>
    <p>本次秒杀活动由《湖南真好玩》和梦洁宝马家居店合作举办，具有丰厚的折扣优惠，先到先得，抢完为止！</p>
   	<p>抢购成功后需要到梦洁宝马家居店向店员出示“湖南真好玩”秒杀成功订单信息，现场提货并付款。实体店地址：韶山南路宝马家私城1楼梦洁家居——潇湘晨报社对面（附近公交车站：市中心医院站、潇湘晨报站）。</p>
    <p>点击“抢购成功记录”可查看您的抢购记录。</p>
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
            <a href="#" class="cur">
                <div class="icon">
                    <b class="iconfont rule"></b>
                </div>
                <p class="name">秒杀说明</p>
            </a>
        </li>
    </ul>
</div>
<input type='hidden' id='userid' value='<%=user%>'/>
</body>
</html>