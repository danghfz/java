<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/20
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>scope2</h1>
pageContext域数据：<%=pageContext.getAttribute("key")%><br>
request域数据：<%=request.getAttribute("key")%><br>
session域数据：<%=session.getAttribute("key")%><br>
application域数据：<%=application.getAttribute("key")%><br>
<%
    //请求重定向:两次请求
    response.sendRedirect("/jsp/scope3.jsp");
%>
</body>
</html>
