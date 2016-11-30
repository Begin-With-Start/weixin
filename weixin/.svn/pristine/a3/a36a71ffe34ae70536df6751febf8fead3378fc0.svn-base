<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String tel = request.getParameter("tel");
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="target-densitydpi=320,width=640,user-scalable=no" />
<title>test</title>

<link rel="stylesheet" href="css/style.css">

<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/idangerous.swiper-2.1.min.js"></script>
<script src="js/PicCarousel.js"></script>

</head>
<body>
<div class="container">
<div class="poster-main A_Demo">
	<div class="poster-btn poster-prev-btn"></div>
	<ul class="poster-list">
		<li class="poster-item"><img src="img/1.jpg" width="60%" ></li>
		<li class="poster-item"><img src="img/1.jpg" width="60%" ></li>
		<li class="poster-item"><img src="img/1.jpg" width="60%" ></li>
		<li class="poster-item"><img src="img/1.jpg" width="60%" ></li>
		<li class="poster-item"><img src="img/1.jpg" width="60%" ></li>
		<li class="poster-item"><img src="img/1.jpg" width="60%" ></li>
		<li class="poster-item"><img src="img/1.jpg" width="60%" ></li>
		
	</ul>
	<div class="poster-btn poster-next-btn"></div>
</div>

<script>
$(".A_Demo").PicCarousel("init");
$(".B_Demo").PicCarousel({
"width":320,		
"height":600,		
"posterWidth":520,	
"posterHeight":300,
"scale":0.9,		
"speed":500,	
"autoPlay":true,	
"delay":1000,	
"verticalAlign":"top"	
});

function getQueryString(name) { 
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
var r = window.location.search.substr(1).match(reg); 
if (r != null) return unescape(r[2]); return null; 
} 

function loadywjl(){
	var tel = getQueryString("tel");
	$table = $("#showall");
	$table.html("");
	$.post('/weixin/cj/Cjaction!getAllYwjlByNameAndTel.action',{tel:tel},function(data){
		for (var i = 0 ; i<data.length ;i++){
			var ywjl =data[i];
			var kh_createddate =ywjl.kh_createddate;
			var kh_workordertype =ywjl.kh_workordertype; 
		}
	},"json");
}
loadywjl();
</script>
</div>
<input type='hidden' id='tel' value='<%=tel%>'/>
</body>
</html>