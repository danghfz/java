<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/5/19
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org"></html>
<html>
<head>
    <title>Title</title>
    <jsp:include page="head.jsp"/> <!--base路径-->

</head>
<body>
    <div>
        <h1>首页</h1>
    </div><hr>
    <div>
        <!--超链接中的路径是浏览器解析的-->
        <a href="success">http://localhost:8080/springmvc/success</a>
    </div>
</body>
</html>
