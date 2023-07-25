<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/5/19
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //动态过去base值
    String base = request.getScheme()+"://" //=====>获取协议 http   ://
            +request.getServerName()+":"  //===>获取服务器的名子,local或者127.0.0.1
            +request.getServerPort()+    //====>获取端口
            request.getContextPath()+"/"; //===>工程路径  /springmvc
    pageContext.setAttribute("basePath",base); //pageContext当前jsp页面有效
%>
<base href="<%=base%>"/>

