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

/* $(function(){
		$(":checkbox").each(function(i,check){
			var checkw = check.value;
			if(checkw=="1"){
				$(this).attr("checked",true);
			}
		});
}); */
	
</script>
</head>
<body>
	<c:forEach items="${cartsPutInModel}" var="var">
	
		<input type="checkbox" onclick="changeTheCheck(${var.skuId},checked)" ${var.shfxz==1?"checked":""}/>&nbsp;&nbsp;
		商品名称：${var.skuMch }&nbsp;&nbsp;
		商品单价${var.skuJg }&nbsp;&nbsp;
		本商品总价：${var.hj }&nbsp;&nbsp;
		<img title="我在${var.kcdz }" src="upload/image/${var.shpTp}" width="70px" />
		<br>
	</c:forEach>
	<div align="right">
		<big>总金额：${cartsSum}</big>&nbsp;&nbsp;&nbsp;
		<hr>
		<form action="check_order.do" method="post">
			<input type="submit" value="去结算"/>
		</form>
	</div>
</body>
</html>