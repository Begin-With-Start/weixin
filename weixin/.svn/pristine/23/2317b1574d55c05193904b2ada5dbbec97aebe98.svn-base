<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="jquery.js"></script>
		<title>测试验证码</title>

	
	
<script type="text/javascript">

function changeImg() {
	var src = "verifyCode?r=" + Math.random();
	$("#imgObj").attr("src", src);
}

function check()
{
var img=document.getElementById("info").innerHTML;
img = img.replace(/^\s*/, "").replace(/\s*$/, "");

		if(document.form1.aaa.value.length==0)
		{
		alert("请输入用户名");
		return false;
		}
		if(document.form1.bbb.value.length==0)
		{
		alert("请填写密码");
		return false;
		}
	    if(img=="验证码错误")
		{
		alert("请重新填写验证码");
		return false; 
		}
		return true; 
}
</script>
	
		</head>
	<body>
	<form name="form1" id="form1"  action="login" method="post" onSubmit="return check()" >
	
	    用户名：<input  name="aaa" type="text" /><br/>
	    密  码：<input  name="bbb" type="text" /><br/>
		验证码：<input id="veryCode" name="veryCode" type="text"/>
		<img id="imgObj" alt="" src="verifyCode" onclick="changeImg()" />
		<a href="#" onclick="changeImg()">换一张</a> <div id="info"></div><br/> 
		<input id="a" name="a" type="text" /><br/>
		<input type="submit" name="Submit" value="提交" />
		
		</form>
	</body>

</html>
 