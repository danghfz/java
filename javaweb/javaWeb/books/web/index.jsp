<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/23
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--只做请求转发--%>
<%--<jsp:forward page="client/clientBookServlet?action=page">--%>

<%--</jsp:forward>--%>
<%
    request.getRequestDispatcher("client/clientBookServlet?action=page").forward(request,response);
%>