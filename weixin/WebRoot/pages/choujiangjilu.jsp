<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>我的抽奖记录</title>   
	<meta charset="utf-8">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    
 <link type="text/css" rel="stylesheet" href="file/css/reset.css">
 <link type="text/css" rel="stylesheet" href="file/css/default.css">
 <link type="text/css" rel="stylesheet" href="file/css/recorder.css">
	<script language="javascript" src="/weixin/pages/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var user = $("#userid").val();
	
	
	$table = $("#showall");
	$table.html("");
	
	
	$.post('/weixin/cj/Cjaction!showZjjlByUser.action',{userid:user},function(data){
		for (var i = 0 ; i<data.length ;i++)
		{
			$tr = $("#clonetr").clone(); //拷贝tr一行 	
		    var zjlx=data[i].zjlx;
		    var kjsj = data[i].kjsj;
		    $div = $("#divcontent").clone();
		    
		   $div.find("div").eq(0).html(data[i].dbt);
		   $div.find("div").eq(1).html(data[i].zjbm);
		   $div.find("div").eq(2).html(kjsj.substr(0,10));
		   $div.appendTo($table);
	    }
	},"json");
});

function prizelist()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/0yuangou.jsp?user='+user;
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
  
  <body>
  <nav>抽奖记录</nav>
 	<table align="center"  border="1" class="tablesorter"  bordercolor="#ffffff" id="mytable" width="98%" style="display: none">
 		<tr id="clonetr">
 			<td class="title">活动名称</td>
 			<td class="title">抽奖编号</td>
 			<td class="title">抽奖时间</td>
 		</tr>
 	</table>
 	<div style="display: none">
		<table>
	 		<tr id='clonetr'>
	 			<td class="content"></td>
	 			<td class="content"></td>
	 			<td class="content"></td>
	 		</tr>
	 	</table>
 	</div>
    
    
    
    
    <div id='divtitle' style="width:98%;height=30px; margin:auto;">
		<div style="width:32%;height:30px;text-align:center;float:left" >活动名称</div>
    	<div style="width:32%;height:30px;text-align:center;float:left" >抽奖编号</div>
    	<div style="width:36%;height:30px;text-align:center;float:right" >抽奖时间</div>
    </div>
    
    
    <div id='showall'>
    </div>
    <div id='divcontent' style="width:98%;height=30px; margin:auto;">
		<div style="width:32%;height:30px;text-align:center;float:left" ></div>
    	<div style="width:32%;height:30px;text-align:center;float:left" ></div>
    	<div style="width:36%;height:30px;text-align:center;float:right" ></div>
    </div>
    
    
    
    
    
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
            <a href="#" class="cur">
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
