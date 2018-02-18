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
	<ul id="sale_spu_class_1" style="float:left;width:70px;"></ul>
	 <ul id="sale_spu_class_2" style="float:left;width:70px;"></ul>
</body>
<script type="text/javascript">
$(function() {
	$.getJSON("js/json/class_1.js", function(data) {
		$(data).each(function(i, json) {
			$("#sale_spu_class_1").append("<li onmouseover='spu_get_class_2("+json.id+")' value="+json.id+">" + json.flmch1+ "</li>");
		});
	});
});
	function spu_get_class_2(class_1_id){
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#sale_spu_class_2").empty();
			$(data).each(function(i,json){
				$("#sale_spu_class_2").append("<li value="+json.id+"><a target='_blank' href='goto_sku_list.do?class_2_id="+json.id+"'>"+json.flmch2+"</a></li>");
			});
		});
		}
</script>
</html>