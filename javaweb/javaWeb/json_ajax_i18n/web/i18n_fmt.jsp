<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
1.使用标签设置locale信息
2.使用标签设置basename
3.使用标签输出国际化信息
--%>
	<!--1.-->
	<fmt:setLocale value="${param.locale}"/>
	<!--2.-->
	<fmt:bundle basename="i18n"/>
	<!--3.-->
<%--	<fmt:message key="username"/>--%>
	<a href="i18n_fmt.jsp?locale=zn_CN">中文</a>|
	<a href="i18n_fmt.jsp?locale=en_US">english</a>
	<center>
		<h1>注册</h1>
		<table>
		<form>
			<tr>
				<td><fmt:message key="username"/></td>
				<td><input name="username" type="text" /></td>
			</tr>
			<tr>
				<td><fmt:message key="password"/></td>
				<td><input type="password" /></td>
			</tr>
			<tr>
				<td><fmt:message key="sex"/></td>
				<td><input type="radio" />男<input type="radio" />女</td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><input type="text" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="reset" value="重置" />&nbsp;&nbsp;
				<input type="submit" value="提交" /></td>
			</tr>
			</form>
		</table>
		<br /> <br /> <br /> <br />
	</center>
</body>
</html>