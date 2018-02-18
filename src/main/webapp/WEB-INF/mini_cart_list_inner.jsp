<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${cartsPutInModel}" var="var">
		<li>商品名称：${var.skuMch }</li>
		<li>商品单价${var.skuJg }</li>
		<li>本商品总价：${var.hj }</li>
		<img title="我在${var.kcdz }" src="upload/image/${var.shpTp}" width="70px">
	</c:forEach>
</body>
</html>