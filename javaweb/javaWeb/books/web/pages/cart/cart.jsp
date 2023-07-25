<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@ include file="/pages/common/login_success.jsp"%>
		<script>
			$(function (){
				$("a.deleteItem").click(function (){
					//提示信息
					return confirm("确定删除"+$(this).parent().parent().find("td:first").text()+"吗")
				})
				$("#clearCart").click(function (){
					return confirm("确定清空购物车吗?")
				})
				//给输入框绑定事件
				$(".updateCount").change(function (){
					//
					if(confirm("你确定将"+$(this).parent().parent().find("td:first").text()
							+"商品数量修改为"+this.value+"吗")){
						//发起请求

						location.href = "${requestScope.base}cartServlet?action=updateCount&count="+this.value+"&id="+$(this).attr("bookId")
					}else {//取消
						//defaultValue属性是表单项dom对象的属性,表示默认的
						//Value属性值
						this.value = this.defaultValue;
					}
				})
			})
		</script>
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
			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input class="updateCount"
							   bookId="${entry.value.id}"
							   style="width: 40px" type="text" value="${entry.value.count}">
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>
			
		</table>
		<c:if test="${empty sessionScope.cart.items}">
			<%--购物车为null--%>
			<tr>
				<th colspan="5">
					亲,当前购物车为空
					<a href="index.jsp">
						亲,浏览商品
					</a>
				</th>
			</tr>
		</c:if>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>