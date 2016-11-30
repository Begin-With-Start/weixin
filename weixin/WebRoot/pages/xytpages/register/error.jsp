<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/tld/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>错误</title>
    <link rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/weui.min.css"/>
 	<link rel="stylesheet" href="${basePath}pages/xytpages/weui/dist/style/weui.css"/>
 	<script language="javascript" src="${basePath}pages/jquery.js"></script>
 	<script type="text/javascript">
 		$().ready(function() {
 			$("#submitDiv").click(function() {
 				history.back(-1);
 			});
 		});
 	</script>
  </head>
  
  <body>
  	${errorMsg}<br/>
  	<div style="position:fixed; bottom:0;width:100%" class="weui_btn weui_btn_primary" id="submitDiv">返回</div>
  </body>
</html>