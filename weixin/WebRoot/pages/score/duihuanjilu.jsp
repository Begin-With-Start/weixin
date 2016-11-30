<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
%>

<!DOCTYPE HTML>
<html>
  <head>     
    <title>我的抢购记录</title>   
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
	$.post('/weixin/cj/Cjaction!showDhjlByUser.action',{userid:user},function(data){
		for (var i = 0 ; i<data.length ;i++)
		{
			$tr = $("#clonetr").clone(); //拷贝tr一行 	
		    var dbt=data[i].dbt;
		    var qgsj = data[i].qgsj;
		    $div = $("#divcontent").clone();
		    
		    $div.find("div").eq(0).html(data[i].dbt);
		    $div.find("div").eq(1).html("1份");
		    $div.find("div").eq(2).html(data[i].qgsj);
		    $div.appendTo($table);
	    }
	},"json");
});

function list()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/score/jifenduihuan.jsp?user='+user;
}

function myscore()
{
	var user = "<%=user%>";
	window.location.href='/weixin/pages/score/myinvitecode.jsp?user='+user;
}

</script>
  
  <body>
  <nav>积分兑换记录</nav>
 	<table align="center"  border="1" class="tablesorter"  bordercolor="#ffffff" id="mytable" width="98%" style="display: none">
 		<tr id="clonetr">
 			<td class="title">礼品名称</td>
 			<td class="title">数量</td>
 			<td class="title">兑换时间</td>
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
		<div style="width:50%;height:30px;text-align:center;float:left" >礼品名称</div>
		<div style="width:15%;height:30px;text-align:center;float:left" >数量</div>
    	<div style="width:35%;height:30px;text-align:center;float:right" >兑换时间</div>
    </div>
    
    
    <div id='showall'>
    </div>
    <div id='divcontent' style="width:98%;height=30px; margin:auto;">
		<div style="width:50%;height:50px;text-align:center;float:left" ></div>
		<div style="width:15%;height:50px;text-align:center;float:left" ></div>
    	<div style="width:35%;height:50px;text-align:center;float:right" ></div>
    </div>
    
    <div class="hytpl-navbar hytpl-navbar-new">
    <ul class="bar-list">
        <li>
            <a href="#" onClick="list()" class="cur">
                <div class="icon">
                    <b class="iconfont list"></b>
                </div>
                <p class="name">礼品列表</p>
            </a>
        </li>
        
         <li>
            <a href="#" class="cur">
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
