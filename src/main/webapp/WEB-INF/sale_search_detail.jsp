<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
/* 	function cart_submit(){
		$("#cart_form").submit();
	} */
	
	function jia(){
		var count = $("#count").val();
		var kc = $("#hdkc").val();
		count++;
		document.getElementById("count").value=count; 
	}
	
	function jian(){
		var count = $("#count").val();
		if(count>=2){
			count--;
		}
		document.getElementById("count").value=count; 
	}
	
	function cart_submit(){
		$("#submit").click();		
	}
	
</script>
<title>硅谷商城</title>
</head>
<body>
	<jsp:include page="sale_header.jsp"></jsp:include>

	<jsp:include page="sale_search_area.jsp"></jsp:include>

<div class="Dbox">
		<div class="box">
			<div class="left">
				<div class="timg"><img src="images/img_5.jpg" alt=""></div>
				<div class="timg2">
					<div class="lf"><img src="images/lf.jpg" alt=""></div>
					<div class="center">
						<span><img src="images/icon_2.jpg" alt=""></span>
						<span><img src="images/icon_3.jpg" alt=""></span>
						<span><img src="images/icon_2.jpg" alt=""></span>
						<span><img src="images/icon_3.jpg" alt=""></span>
						<span><img src="images/icon_2.jpg" alt=""></span>
					</div>
					<div class="rg"><img src="images/rg.jpg" alt=""></div>
				</div>
				<div class="goods"><img src="images/img_6.jpg" alt=""></div>
			</div>
			<div class="cent">
				<div class="title">${detail_sku.spu.shpMch}</div>
				<div class="bg">
					<p>市场价：<strong>${detail_sku.jg}元</strong></p>
					<p>促销价：<strong>${detail_sku.jg}元</strong></p>
				</div>
				<div class="clear">
					<div class="min_t">选择版本：</div>
						<c:forEach items="${sku_list}" var="sku">
							<div class="min_mx"><a href="goto_sku_detail.do?id=${sku.id}&spuId=${sku.shpId}" >${sku.skuMch}</a></div>
						</c:forEach>
				</div>
				<div class="clear">
	 				<div class="min_t" >服务：</div>
					<div class="min_mx" >分期两次</div>
					<div class="min_mx" >分期三次</div>
					<div class="min_mx" >分期五次</div>
					<div class="min_mx" >分期六次</div>
				</div>
				<div class="clear" style="margin-top:20px;">
					<div id="kc" class="min_t" style="line-height:36px">数量：${detail_sku.kc} 件</div>
					<div class="num_box">
						<input type="text" name="num" id="count" value="1" style="width:40px;height:32px;text-align:center;float:left">
						<div class="rg">
							<img src="images/jia.jpg" id='jia' onclick="jia()" alt="">
							<img src="images/jian.jpg" id='jian' onclick="jian()" alt="">
						</div>
					</div>
				</div>
				<div class="clear" style="margin-top:20px;">
						<form  id="cart_form" action="add_cart.do" method="post">
							<input type="hidden" name="skuMch" value="${detail_sku.skuMch}" />
							<input type="hidden" name="skuJg" value="${detail_sku.jg}" />
							<input type="hidden" name="kc" id="hdkc" value="${detail_sku.kc}" />
							<input type="hidden" name="tjshl" value="1" />
							<input type="hidden" name="hj" value="${detail_sku.jg}" />
							<input type="hidden" name="shpId" value="${detail_sku.shpId}" />
							<input type="hidden" name="skuId" value="${detail_sku.id}" />
							<input type="hidden" name="shpTp" value="${detail_sku.spu.shpTp}" />
							<input type="hidden" name="shfxz" value="1" />
							<input type="hidden" name="kcdz" value="${detail_sku.kcdz}" />
							<c:if test="${not empty sessionScope.user}">
								<input type="hidden" name="yhId" value="${user.id}" />
							</c:if>
							<img src="images/shop.jpg" onclick="cart_submit()" alt="" style="cursor:pointer;">
							<input type="submit" id="submit"/>
						</form>		
				</div>
			</div>
		</div>
	</div>
	<div class="Dbox1">
		<div class="boxbottom">
			<div class="top">
				<span>商品详情</span>


				<span>评价</span>
			</div>
			<div class="btm" align="center">
				${detail_sku.spu.shpMsh}
				<br>
				<c:forEach items="${detail_sku.list_av_name}" var="av_name">
					${av_name.attrName}:${av_name.valueName}<br>
					<br>
				</c:forEach>
				<c:forEach items="${detail_sku.list_image}" var="image">
					<img src="upload/image/${image.url}" width="900px" height="550px"/>
					<br>
				</c:forEach>
			</div>
		</div>
	</div>
	
	

	
	<jsp:include page="sale_footer.jsp"></jsp:include>
</body>
</html>