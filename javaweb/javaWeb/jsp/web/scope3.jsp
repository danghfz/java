<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/20
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
pageContext域数据：<%=pageContext.getAttribute("key")%><br>
request域数据：<%=request.getAttribute("key")%><br>
session域数据：<%=session.getAttribute("key")%><br>
application域数据：<%=application.getAttribute("key")%><br>
</body>
</html>
