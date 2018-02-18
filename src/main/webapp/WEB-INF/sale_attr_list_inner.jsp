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
<script type="text/javascript">
	function append_shxzh_shxzhmch(){
		$("#checkedSHUXINDiv").append("参数");
		$.get("getCheckedSpuList.do",{},function(html){
			alert(html);
			$("#sku_list_inner").html(html);
		});
	}
</script>
<body>
	<hr>
已选中的属性限制:<div id="checkedSHUXINDiv">

			</div>
属性列表：<br>
	<c:forEach items="${attr_list }" var="attr" varStatus="status">
		<div id="divId_${attr.id }">
		${attr.shxmMch }：
			<c:forEach items="${attr.t_mall_value_list }" var="val" >
				<a href="javascript:append_shxzh_shxzhmch();">${val.shxzh }${val.shxzhMch }</a>a
			</c:forEach>
		</div>
		<br>
	</c:forEach>
</body>
</html>