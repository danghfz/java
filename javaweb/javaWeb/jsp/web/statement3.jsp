<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/20
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         isErrorPage="true"
         language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        int i = 0;
        if (i == 12) {
            System.out.println("i is 12");
        } else if (i == 13) {
            System.out.println("i is 13");
        } else {
            System.out.println("i is not 12 or 13");
        }
    %>
    <%
        for (int j = 0;j < 10; j++){
    %>
    <%= j %>
    <%
        }
    %>
    <%
        String name = request.getParameter("name");

    %>
</body>
</html>
