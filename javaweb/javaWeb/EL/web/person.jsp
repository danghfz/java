<%@ page import="com.local.servlet.pijo.Person" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/21
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
      Person person = new Person();
      person.setName("Tom");
      person.setPhone(new String[]{"123456789","987654321"});
      person.setCities(Arrays.asList("Beijing","Shanghai","Guangzhou"));
      HashMap<String, Object> map = new HashMap<>();
      map.put("name","Tom");
      map.put("phone",new String[]{"123456789","987654321"});
      person.setMap(map);
      pageContext.setAttribute("person",person);
  %>
    <%
        request.setAttribute("mm","赵迪");
    %>
    输出person对象：${person}<br>
    person.name:${person.name}<br>
    person.phone:${person.phone[0]}<br>
    person.cities:${person.cities[0]}<br>
    person.map:${person.map.get("name")}<br>
    request域数据:${requestScope.get("mm")}<br>
    request域数据:${pageContext.request.getAttribute("mm")}<br>
    <%--
        获取参数,request发送的
    --%>
    username : ${param.get("username")}
</body>
</html>
