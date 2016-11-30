<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>兑换列表</title>
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
 
	<script language="javascript" src="/weixin/pages/score/jquery.js"></script>
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
	$.post('/weixin/cj/Cjaction!getAllJfdhxm.action',null,function(data){
		for (var i = 0 ; i<data.length ;i++){
			var dhxm =data[i];
			var xmid =dhxm.xmid;
			var tupian =dhxm.tupian; 
			var total = dhxm.total;
			var starttime = dhxm.startTime;
			var dbt = dhxm.dbt;
			var jiage = dhxm.jiage;
			var fenshu =dhxm.fenshu;
			$div = $("#clonediv").clone();
			if(0 == fenshu)
			{
				$div.find("div").eq(0).html("<image src='/weixin/pages/score/file/"+tupian+"'>");
			}else
			{
				$div.find("div").eq(0).html("<a href = '/weixin/pages/score/dhxm"+xmid+".jsp?user="+user+"&xmid="+xmid+"'><image src='/weixin/pages/score/file/"+tupian+"'></a>");
			}
			
			
			$div.find("div").eq(1).find("div").eq(1).html("<b>"+dbt+"</b>");
			$div.find("div").eq(1).find("div").eq(2).html("所需积分："+jiage);
			
			$div.find("div").eq(1).find("div").eq(3).html("剩余:"+fenshu+" 份, 总共:"+total+" 份");
			$div.find("div").eq(1).find("div").eq(4).html("开始时间:"+starttime);
			if(0 == fenshu)
			{
				$div.find("div").eq(1).find("div").eq(5).html(" <input style= 'font-size:16px; width:120px; height:40px;background:#6c6c6c;color:#ffffff' type='button' value='兑换结束'> ");
			}else
			{
				$div.find("div").eq(1).find("div").eq(5).html(" <input  type='button' value='点击兑换' onclick='javascript:cj("+xmid+");return false;'>");
			}
			$div.appendTo($table);
		}
	},"json");
}

function cj(xmid){
	var user = $("#userid").val();
	window.location.href='/weixin/pages/score/dhxm'+xmid+'.jsp?user='+user+'&xmid='+xmid;
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
	window.location.href='/weixin/pages/score/duihuanjilu.jsp?user='+user;
}

function myscore()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/score/myinvitecode.jsp?user='+user;
}
</script>

  </head>
  
  <body >
 <nav>兑换列表</nav>
  <div id='showall'>
  </div>
  <div style="display:none;">
	<div id='clonediv' class="pro fl">
		<div class="pic fl">
		</div>
		<div class="info fr" >
			<div style="height:1px;"></div>
			<div class="title"></div>
			<div class="score"></div>
			<div class="summery"></div>
			<div class="starttime"></div>
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
                <p class="name">礼品列表</p>
            </a>
        </li>
        
         <li>
            <a href="#"  onClick="record()" class="cur">
                <div class="icon">
                    <b class="iconfont recoder"></b>
                </div>
                <p class="name">兑换记录</p>
            </a>
        </li>
        
         <li>
            <a href="#" onClick="myscore()" class="cur">
                <div class="icon">
                    <b class="iconfont rule"></b>
                </div>
                <p class="name">我的积分</p>
            </a>
        </li>
    </ul>
</div>
<input type='hidden' id='userid' value='<%=user%>'/>
  </body>
  
</html>
