<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/css.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	
</script>
<body>
	<hr>
	商品列表<br>
	<div class="Sbox">
		<c:forEach items="${model_sku_list}" var="sku">
			<div class="list">
				<div class="img">
					<img title="${sku.mall_product.shpMsh}" width="100%" height="70%" onclick="goto_detail('+${sku.id}+','+${sku.mall_product.id}+')" src="upload/image/${sku.mall_product.shpTp}">
				</div>
				<div class="price">${sku.jg}RMB</div>
				<div class="title"><a href="goto_sku_detail.do?id=${sku.id}&spuId=${sku.mall_product.id}" target="_blank">${sku.skuMch}</a></div>
			</div>
		</c:forEach>
	</div>
	
	
</body>
</html>