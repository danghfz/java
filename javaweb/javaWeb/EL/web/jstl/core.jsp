<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.local.servlet.pijo.Person" %><%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/21
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
    <c:set/>往域中保存数据
    scope 表示域对象 : setAttribute(String name, Object value)
        page 表示 pageContext域(默认)
        request 表示 request域
        session 表示 session域
        application 表示 application域
    var表示 key
    value表示值
--%>
<c:set scope="request" var="name" value="lenvoo"/>
<c:set scope="page" var="age" value="18"/>
<%--
    if:<c:if/>if判断
        text 表示 判断的条件(el表达式)${}
--%>
<c:if test="${12 == 12}">
    ${12}
</c:if>
<%--
    <c:choose>选择
    <c:when text> 判断
    <c:otherwise>
    注意
        1.标签内不要使用html注释,要使用jsp注释
        2.when标签的父标签一定是choose标签(在otherwise中写when标签也要写choose标签)
--%>
<c:set scope="request" var="height" value="183"/>
<c:choose>
    <c:when test="${requestScope[height]}">
        ${"有点高"}
    </c:when>
    <c:otherwise>
        ${"刚刚好"}
    </c:otherwise>
</c:choose>
<%--
    <c:forEach items="">遍历
        begin 设置开始索引
        end 设置结束索引
        step 步长
        var 表示遍历的变量(也是当前的数据)
        varStatus表示当前输出的状态

--%>
<c:forEach begin="0" end="10" var="i" step="1">
    ${i}
</c:forEach>
<%--
    for(Object : arr)
    item 表示数据源 requestScope[arr]
    var 表示当前数据
 --%>
<c:forEach items="${arr}" var="i">
    ${i}
</c:forEach>
<%--遍历map--%>
<%
    HashMap<String, String> map = new HashMap<>();
    map.put("name", "lenvoo");
    map.put("age", "18");
    request.setAttribute("map", map);
%>
<c:forEach items="${requestScope.map}" var="i">
    ${i.key} : ${i.value}
</c:forEach>
<br>
<%--遍历list集合--%>
<%
    ArrayList<Person> list = new ArrayList<>();
    list.add(new Person("lenvoo",null,null,null));
    list.add(new Person());
    request.setAttribute("list", list);

%>
    <c:forEach items="${requestScope.list}" var="i" varStatus="stat">
        ${i}<br>
        ${stat}
    </c:forEach>
</body>
</html>
