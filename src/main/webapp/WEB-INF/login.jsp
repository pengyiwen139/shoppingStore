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
</head>
<body>

	<form action="do_login.do" method="post">
		登陆账号：<input type="text" name="yhMch" value="admin"/><br>
		登陆密码:<input type="text" name="yhMm" value="admin"/><br>
		<div class="box_01">
					<div class="box_01_both">
						<div class="box_01_both_1">数据源1：<input type="radio" name="dataSource_type" value="1"/></div>
						<div class="box_01_both_2">数据源2：<input type="radio" name="dataSource_type" value="2"/></div>
					</div>
				</div>
		<input type="submit" value="确认登陆"/><br>
	</form>

</body>
</html>