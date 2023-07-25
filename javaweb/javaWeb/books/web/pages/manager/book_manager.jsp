<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/head.jsp"%>
	<script>
		$(function (){
			//绑定删除单击事件
			$(".deleteClass").click(function (){
				//this当前元素
				var text = $(this).parent().parent().find("td:first").text();
				return confirm("你确定要删除"+text+"吗?");
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/pages/common/manager.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?id=${book.id}&action=getBook&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>

		</table>
		<div id="page_nav">

			<c:if test="${requestScope.page.pageNo>1}">
				<a href="${ requestScope.page.url }&pageNo=1">首页</a>
				<a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>

			<%--页码输出--%>
			<c:choose>
				<c:when test="${requestScope.page.pageTotal<=5}"><%--全部--%>
						<c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
							<c:if test="${i==requestScope.page.pageNo}">
								【${i}】 <%--当前页码不可点击--%>
							</c:if>
							<c:if test="${i!=requestScope.page.pageNo}">
								<a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
							</c:if>
						</c:forEach>
				</c:when>
				<c:when test="${requestScope.page.pageTotal>5}">
					<c:choose>
						<%--当前页码1，2，3的情况--%>
						<c:when test="${requestScope.page.pageNo<=3}"><%--输出1-5页--%>
							<c:forEach var="i" begin="1" end="5">
								<c:if test="${i==requestScope.page.pageNo}">
									【${i}】 <%--当前页码不可点击--%>
								</c:if>
								<c:if test="${i!=requestScope.page.pageNo}">
									<a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:when>
						<%--当前页码 最后三个 的情况--%>
						<c:when test="${requestScope.page.pageNo+3 > requestScope.page.pageTotal}">
							<c:forEach var="i" begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}">
								<c:if test="${i==requestScope.page.pageNo}">
									【${i}】 <%--当前页码不可点击--%>
								</c:if>
								<c:if test="${i!=requestScope.page.pageNo}">
									<a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:when>
						<%--中间部分--%>
						<c:otherwise>
							<c:forEach var="i" begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}">
								<c:if test="${i==requestScope.page.pageNo}">
									【${i}】 <%--当前页码不可点击--%>
								</c:if>
								<c:if test="${i!=requestScope.page.pageNo}">
									<a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:otherwise>

					</c:choose>
				</c:when>
			</c:choose>

<%--			【${requestScope.page.pageNo}】--%>
			<%--页码输出--%>


			<c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
				<a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>

			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">
			<script>
				$(function (){
					$("#searchPageBtn").click(function (){
						var pageNo = $("#pn_input").val();
						if (pageNo>${requestScope.page.pageTotal}||pageNo<1){
							alert("页码不合法")
							return false;
						}
						//javaScript提供了Location地址栏对象
						//有一个href对象,获取浏览器地址栏中的地址
						//该属性可读可写,可以赋值(页面跳转)
						location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNo="+pageNo
					})
				})
			</script>
		</div>
	</div>


	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>