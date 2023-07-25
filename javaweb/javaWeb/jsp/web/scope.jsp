<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/20
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!--演示域属性-->
    <%
        pageContext.setAttribute("key", "pageContext");
        request.setAttribute("key", "request");
        session.setAttribute("key", "session");
        application.setAttribute("key", "application");

    %>
    <%
        String key = (String)pageContext.getAttribute("key");
        String key1 = (String)request.getAttribute("key");
        String key2 = (String)session.getAttribute("key");
        String key3 = (String)application.getAttribute("key");
    %>
    pageContext域数据：<%=key%><br>
    request域数据：<%=key1%><br>
    session域数据：<%=key2%><br>
    application域数据：<%=key3%><br>
    <%
        //请求转发，一次请求
        request.getRequestDispatcher("/scope2.jsp").forward(request, response);
    %>
</body>
</html>
