<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
//String addrip = request.getParameter("addrip");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>特价秒杀</title>
    <meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
 
 <link type="text/css" rel="stylesheet" href="file/css/reset.css">
 <link type="text/css" rel="stylesheet" href="file/css/default.css">
 <link type="text/css" rel="stylesheet" href="file/css/list.css">
 
	<script language="javascript" src="/weixin/pages/jquery.js"></script>
<script type="text/javascript">
var cjarr = new Array();

$(document).ready(function(){
	setTimeout(function(){ 
			loadcjxm();  
		 },100); 
});
function loadcjxm(){
	var user = $("#userid").val(); //lidu
	//var addrip = $("#addrip").val(); //lidu
	$table = $("#showall");
	$table.html("");
	var now = new Date();
	$.post('/weixin/cj/Cjaction!getAllQgxm.action',null,function(data){
		for (var i = 0 ; i<data.length ;i++){
			var qgxm =data[i];
			var xmid =qgxm.xmid;
			var tupian =qgxm.tupian; 
			var dbt = qgxm.dbt;
			var xbt= qgxm.xbt;
			var yuanjia = qgxm.jiage;
			var xianjia = qgxm.cyrs;
			var fenshu =qgxm.fenshu;
			var qgsj = qgxm.qgsj;  qgsj = qgsj.replace(/-/g,"/"); var sj =  new Date(Date.parse(qgsj)); 
			$div = $("#clonediv").clone();
			//$div.find("div").eq(0).html("<a href = '/weixin/pages/1yuanmiaosha.jsp?user="+user+"&xmid="+xmid+"&addrip="+addrip+"'><image src='/weixin/pages/file/huangtao0.jpg'></a>");
			if(1 == xmid)
			{
				$div.find("div").eq(0).html("<a href = '/weixin/pages/qianggou/1yuanmiaosha.jsp?user="+user+"&xmid="+xmid+"'><image src='/weixin/pages/file/beizi_xiao.jpg'></a>");
			}
			else if(2 == xmid)
			{
				$div.find("div").eq(0).html("<a href = '/weixin/pages/qianggou/1yuanmiaosha2.jsp?user="+user+"&xmid="+xmid+"'><image src='/weixin/pages/file/beizi_xiao.jpg'></a>");
			}
			else if(3 == xmid)
			{
				$div.find("div").eq(0).html("<a href = '/weixin/pages/qianggou/1yuanmiaosha3.jsp?user="+user+"&xmid="+xmid+"'><image src='/weixin/pages/file/beizi_xiao.jpg'></a>");
			}
			$div.find("div").eq(1).find("div").eq(1).html("<b>"+dbt+"</b>");
			$div.find("div").eq(1).find("div").eq(2).html("原价"+yuanjia+"抢购价"+xianjia+"元！");
			$div.find("div").eq(1).find("div").eq(3).html("剩余 "+fenshu+" 份");
			$div.find("div").eq(1).find("div").eq(4).html("开始时间为："+qgsj);
			$div.find("div").eq(1).find("div").eq(5).html(" <input  type='button' value='点击秒杀' onclick='javascript:cj("+xmid+");return false;'>");
			$div.appendTo($table);
		}
	},"json");
}

function cj(xmid){
	var user = $("#userid").val();
	if(1 == xmid)
	{
		window.location.href='/weixin/pages/qianggou/1yuanmiaosha.jsp?user='+user+'&xmid='+xmid;
	}
	else if(2 == xmid)
	{
		window.location.href='/weixin/pages/qianggou/1yuanmiaosha2.jsp?user='+user+'&xmid='+xmid;
	}
	else if(3 == xmid)
	{
		window.location.href='/weixin/pages/qianggou/1yuanmiaosha3.jsp?user='+user+'&xmid='+xmid;
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
</script>

  </head>
  
  <body >
 <nav>秒杀列表</nav>
  <div id='showall'>
  </div>
  <div style="display:none;">
	<div id='clonediv' class="pro fl">
		<div class="pic fl">
		</div>
		<div class="info fr" >
			<div style="height:1px;"></div>
			<div class="title"></div>
			<div class="price"></div>
			<div class="total"></div>
			<div class="time"></div>
			<div class="btn"></div>
		</div> 
    　</div>
     
   

</div>
<div class="clear h50"></div>
<div class="hytpl-navbar hytpl-navbar-new">
    <ul class="bar-list">
         <li>
            <a href="#" class="cur">
                <div class="icon">
                    <b class="iconfont list"></b>
                </div>
                <p class="name">商品列表</p>
            </a>
        </li>
        
         <li>
            <a href="#"  onClick="record()" class="cur">
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
<input type='hidden' id='userid' value='<%=user%>'/>
  </body>
  
</html>
