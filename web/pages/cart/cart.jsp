<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="../../community/header.jsp"%>
</head>
<script>
	$(function () {
		$("#clear").click(function () {
			return confirm("你确定要清除购物车吗？");
		});

		$(".del").click(function () {
			return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？");

		});

	});
</script>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="../../community/menu_comm.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<c:if test="${not empty sessionScope.cartObj.item}">
				<c:forEach items="${sessionScope.cartObj.item.values()}" begin="0" end="${sessionScope.cartObj.item.values().size()-1}" var="cartitem">
					<tr>
						<td>${cartitem.name}</td>
						<td>${cartitem.count}</td>
						<td>${cartitem.price}</td>
						<td>${cartitem.totalPrice}</td>
						<td><a class="del" href="cartServlet?action=delItem&id=${cartitem.id}">删除</a></td>
					</tr>
				</c:forEach>


			</c:if>

			<c:if test="${empty sessionScope.cartObj.item}">
				<td colspan="5">
						<a href="index.jsp" style="Text-decoration:none"><span class="cart_span" style="color: red">购物车空空的，去选点商品吧！</span></a>
				</td>
			</c:if>
		</table>

		<c:if test="${not empty sessionScope.cartObj.item}">
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cartObj.count}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cartObj.totalPrice}</span>元</span>
			<span class="cart_span"><a id="clear" href="cartServlet?action=clear">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder&userId=${sessionScope.userId}">去结账</a></span>
		</div>
		</c:if>

	</div>


	<%@include file="../../community/footer.jsp"%>
</body>
</html>