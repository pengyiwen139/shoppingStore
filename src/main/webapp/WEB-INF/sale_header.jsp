<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
 	$(function(){
		var yh_nch = getMyCookie("yhNch");
		$("#putIntoCookie").text(yh_nch);
	});
	
	function getMyCookie(key){
		var val = "";
		var cookies =document.cookie.replace(/\s/,"");
		var cookie_array = cookies.split(";");
		
		for(i=0;i<cookie_array.length;i++){
			cookie = cookie_array[i].split("=");
			if(cookie[i] == key){
				val = cookie[i+1];
			}
		}
		return decodeURIComponent(val) ;
	} 
</script>
</head>
<body>
	<c:if test="${empty user}">
		<a href="to_login.do"><span style="color:red;" id="putIntoCookie"></span>请登录哇</a>
	</c:if>
	<c:if test="${not empty user}">
		欢迎${user.yhNch} 订单列表  &nbsp;<a href="logout.do">退出登录</a>
	</c:if>
	<br>
</body>
</html>