<%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/21
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8090/el/uploadServlet" method="post" enctype="multipart/form-data">
    <table border="1">
        <tr>
            <td>
                用户名 :
            </td>
            <td>
                <input type="text" name = "username">
            </td>
        </tr>
        <tr>
            <td>
                文件 :
            </td>
            <td>
                <input type="file" name = "file">
            </td>
        </tr>
        <tr>
            <td><input type="reset"></td>
            <td><input type="submit"></td>
        </tr>
    </table>
</form>
</body>
</html>
