<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/28
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="loginServlet" method="get">
        用户名:<input type="text" name="username" value="${cookie.username.value}"><br>
        密码:<input type="password" name="pw"><br>
        <input type="submit" value="登录">
    </form>
</body>
</html>
