<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function changeTheCheck(skuId,check){
		var shfxz = "0";
		if(check){
			shfxz = "1";
		}
		$.get("updateCartsStatus.do",{shfxz:shfxz,skuId:skuId},function(data){
			$("#sale_inner_div").html(data);
		});
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="sale_inner_div">
		<jsp:include page="sale_cart_inner.jsp"></jsp:include>
	</div>
</body>
</html>