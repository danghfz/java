<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/20
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        response.getWriter().write("Hello World! --response.getWriter().write()");
        response.getWriter().write("<br>");
        out.write("Hello World!  -- out.write");
        out.write("<br>");
    %>
</body>
</html>
