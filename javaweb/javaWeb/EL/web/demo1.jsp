<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/21
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setAttribute("name", "lenvoo");
    %>
    表达式脚本输出:name1
    <%=
        request.getAttribute("name1")
    %>
    <br>name
    <%=
        request.getAttribute("name")
    %>
    <br>
    el表达式脚本输出:
    name:${name}<br>
    name1:${name1}
</body>
</html>
