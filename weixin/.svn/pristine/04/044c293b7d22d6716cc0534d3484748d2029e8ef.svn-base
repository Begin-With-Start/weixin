<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String user = request.getParameter("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>     
    <title>我的奖品</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
 <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
 <meta name="format-detection" content="telephone=no"/>
 <meta http-equiv="content-type" content="text/xml; charset=utf-8" />
	<script language="javascript" src="/weixin/pages/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	var user = $("#userid").val();
	$.post('/weixin/cj/Cjaction!showZjjlByUser.action',{userid:user},function(data){
		for (var i = 0 ; i<data.length ;i++){
			$tr = $("#clonetr").clone(); //拷贝tr一行 	
	      	 
	      	$("td:eq(0)",$tr).html(data[i].dbt);
		    $("td:eq(1)",$tr).html(data[i].zjbm);  
		    $("td:eq(2)",$tr).html(data[i].yhqbm); 
	    	$("td:eq(3)",$tr).html(data[i].kjsj);
	    	
	    	$tr.appendTo($("#mytable"));
			}
	},"json");
});
</script>
  
  <body>
 	<table align="center"  border="1" class="tablesorter"  bordercolor="#dddddd" id="mytable" width="98%" >
 		<tr id="clonetr" style="width: 923px; ">
 			<td>奖品名称</td>
 			<td>中奖编号</td>
 			<td>优惠券编号</td>
 			<td>中奖时间</td>
 		</tr>
 	</table>
 	<div style="display: none">
		<table>
	 		<tr id='clonetr'>
	 			<td></td>
	 			<td></td>
	 			<td></td>
	 			<td></td>
	 		</tr>
	 	</table>
 	</div>
	<input type='hidden' id='userid' value='<%=user%>'/>
  </body>
</html>
