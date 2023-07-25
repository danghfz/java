<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/24
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@include file="../common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            //加入购物车
            <%--$(".add").click(function (){--%>
            <%--    //attr获取设置元素的属性值--%>
            <%--    var attr = $(this).attr("bookId");--%>
            <%--    location.href = "${requestScope.base}cartServlet?action=addItem&id="+attr;--%>

            <%--})--%>
            $(".add").click(function (){
                //获取id
                var attr = $(this).attr("bookId");
                $.getJSON(
                    "http://localhost:8080/books/cartServlet",
                    "action=ajaxAddItem&id="+attr,
                    function (data){//
                       /*map.put("totalCount",cart.getTotalCount());
                        map.put("last",cartItem.getName());*/
                        $("#cartTotal").html("您的购物车中有"+data.totalCount+"件商品");
                        $(".cartLastName").html(data.last+"加入到了购物车中");
                    }
                )
            })

        })

    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${empty sessionScope.user}">
        <a href="pages/user/login.jsp">登录</a>
        <a href="pages/user/regist.jsp">注册</a>
        </c:if>
        <c:if  test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
            <a href="index.jsp">返回</a>
        </c:if>&nbsp;&nbsp;
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/clientBookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="minPrice" value="${param.minPrice}"> 元 -
                <input id="max" type="text" name="maxPrice" value="${param.maxPrice}"> 元
                <input type="submit" value="查询" />
            </form>

        </div>
        <div style="text-align: center">
            <c:if test="${not empty sessionScope.cart.items}">
            <span id="cartTotal">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
            <div>
                您刚刚将<span class="cartLastName" style="color: red">${sessionScope.last.name}</span>加入到了购物车中
            </div>
            </c:if>
            <c:if test="${empty sessionScope.cart.items}">
                <span> </span>
                <div>
                    <span class="cartLastName" style="color: red">当前购物车为空</span>
                </div>
            </c:if>
        </div>

        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="static/img/default.jpg" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="add">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>


    </div>
    <div id="page_nav">

        <c:if test="${requestScope.page.pageNo>1}">
            <a href="${ requestScope.page.url }&pageNo=1">首页</a>
            <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        </c:if>

        <%--页码输出--%>
        <c:choose>
            <c:when test="${requestScope.page.pageTotal<=5}"><%--全部--%>
                <c:set scope="page" var="begin" value="1"></c:set>
                <c:set scope="page" var="end" value="${requestScope.page.pageTotal}"></c:set>
            </c:when>
            <c:when test="${requestScope.page.pageTotal>5}">
                <c:choose>
                    <%--当前页码1，2，3的情况--%>
                    <c:when test="${requestScope.page.pageNo<=3}"><%--输出1-5页--%>
                        <c:set scope="page" var="begin" value="1"></c:set>
                        <c:set scope="page" var="end" value="5"></c:set>
                    </c:when>
                    <%--当前页码 最后三个 的情况--%>
                    <c:when test="${requestScope.page.pageNo+3 > requestScope.page.pageTotal}">
                        <c:set scope="page" var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
                        <c:set scope="page" var="end" value="${requestScope.page.pageTotal}"></c:set>
                    </c:when>
                    <%--中间部分--%>
                    <c:otherwise>
                        <c:set scope="page" var="begin" value="${requestScope.page.pageNo-2}"></c:set>
                        <c:set scope="page" var="end" value="${requestScope.page.pageNo+2}"></c:set>
                    </c:otherwise>

                </c:choose>
            </c:when>
        </c:choose>

        <c:forEach begin="${pageScope.begin}" end="${pageScope.end}" var="i">
            <c:if test="${i==requestScope.page.pageNo}">
                【${i}】 <%--当前页码不可点击--%>
            </c:if>
            <c:if test="${i!=requestScope.page.pageNo}">
                <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
            </c:if>
        </c:forEach>

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
                    //http:localhost:8080/books/    +    ServletPath
                    location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNo="+pageNo
                })
            })
        </script>
    </div>

</div>

<%@include file="../common/footer.jsp"%>
</body>
</html>