<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>免费抽奖</title>
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
	$table = $("#showall");
	$table.html("");
	var now = new Date();
	$.post('/weixin/cj/Cjaction!getAllCjxm.action',null,function(data){
		for (var i = 0 ; i<data.length ;i++){
			var cjxm =data[i];
			var xmid =cjxm.xmid;
			var tupian =cjxm.tupian; 
			var dbt = cjxm.dbt;
			var xbt= cjxm.xbt;
			var jiage = cjxm.jiage;
			var fenshu =cjxm.fenshu;
			var kjsj = cjxm.kjsj;  kjsj = kjsj.replace(/-/g,"/"); var sj =  new Date(Date.parse(kjsj)); 
			$div = $("#clonediv").clone();
			$div.find("div").eq(0).html("<image src='/weixin/pages/file/"+tupian+"'>");
			$div.find("div").eq(1).find("div").eq(1).html("<b>"+dbt+"</b>");
			$div.find("div").eq(1).find("div").eq(2).html(""+xbt+"");
			
			//$div.find("div").eq(1).find("div").eq(3).html("<span>原价"+jiage+"元</span>　<b>"+"现价0元"+"</b>");
			$div.find("div").eq(1).find("div").eq(3).html("<span>价格"+jiage+"元</span>");
			if (sj<now){
				$div.find("div").eq(1).find("div").eq(4).html(" <input style= 'font-size:16px; width:120px; height:40px;background:#6c6c6c;color:#ffffff' type='button' value='抽奖已结束' onclick='javascript:cj("+xmid+");return false;'> ");
			}
			else
			{
				$div.find("div").eq(1).find("div").eq(4).html(" <input  type='button' value='点击抽奖' onclick='javascript:cj("+xmid+");return false;'>");
			}	 
			$div.appendTo($table);
		}
	},"json");
}

function cj(xmid){
	var user = $("#userid").val();
	//根据xmid跳转到该项目指定的单个奖品抽奖页面
	if(1 == xmid)
	{
		window.location.href='/weixin/pages/huangtaochoujiang.jsp?user='+user+'&xmid='+xmid;
	}
	else if(2 == xmid)
	{
		window.location.href='/weixin/pages/pangxiechoujiang.jsp?user='+user+'&xmid='+xmid;
	}
	else if(3 == xmid)
	{
		window.location.href='/weixin/pages/pangxiechoujiang2.jsp?user='+user+'&xmid='+xmid;
	}
	else if(4 == xmid)
	{
		window.location.href='/weixin/pages/niurouchoujiang.jsp?user='+user+'&xmid='+xmid;
	}
	else if(8 == xmid)
	{
		window.location.href='/weixin/pages/tongzhuangshuicj1.jsp?user='+user+'&xmid='+xmid;
	}
	else if(11 == xmid)
	{
		window.location.href='/weixin/pages/tongzhuangshuicj2.jsp?user='+user+'&xmid='+xmid;
	}
	else if(5 == xmid)
	{
		window.location.href='/weixin/pages/pijiantan1.jsp?user='+user+'&xmid='+xmid;
	}
	else if(6 == xmid)
	{
		window.location.href='/weixin/pages/pijiantan2.jsp?user='+user+'&xmid='+xmid;
	}
	else if(10 == xmid)
	{
		window.location.href='/weixin/pages/niurouchoujiang2.jsp?user='+user+'&xmid='+xmid;
	}
	else if(9 == xmid)
	{
		window.location.href='/weixin/pages/tongzhuangshuicj3.jsp?user='+user+'&xmid='+xmid;
	}else
	{
		window.location.href='/weixin/pages/cjxm'+xmid+'.jsp?user='+user+'&xmid='+xmid;
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

function rule()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/rule.jsp?user='+user;
}

</script>

  </head>
  
  <body >
 <nav>抽奖活动</nav>
  <div id='showall'>
  </div>
  <div style="display:none;">
	<div id='clonediv' class="pro fl">
		<div class="pic fl">
		</div>
		<div class="info fr" >
			<div style="height:18px;"></div>
			<div class="title"></div>
			<div class="summery"></div>
			<div class="money"></div>
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
<input type='hidden' id='userid' value='<%=user%>'/>
  </body>
  
</html>
