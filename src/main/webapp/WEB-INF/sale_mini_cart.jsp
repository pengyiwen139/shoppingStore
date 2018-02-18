<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function show_MINI_cart(){
		$.get("getMiniCart.do",function(html){
			$("#miniCartDiv").html(html);
			$("#miniCartDiv").show();
		});
	}
	function hide_MINI_cart(){
		$("#miniCartDiv").hide();
	}
</script>
</head>
<body>
	<a href="javascript:;" onmouseover="show_MINI_cart()" onmouseout="hide_MINI_cart()" onclick="window.location.href='sale_cart.do'">购物车</a>
	<div id="miniCartDiv"></div>
</body>
</html>