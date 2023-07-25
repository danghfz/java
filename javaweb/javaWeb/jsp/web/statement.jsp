<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: lenvoo
  Date: 2022/4/20
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!--声明类属性-->
    <%!
        String name = "lenvoo";
        int age = 18;
        private static Map<String,Object> map;
    %>
    <!--声明static静态代码块-->
    <%!
        static {
            map = new HashMap<String,Object>();
            map.put("name","name");
            map.put("age",20);
        }
    %>
    <!--声明方法-->
    <%!
        public String getName(){
            return name;
        }
        public int getAge(){
            return age;
        }
        public static Map<String,Object> getMap(){
            return map;
        }
    %>
    <!--声明内部类-->
    <%!
        public class InnerClass{
            private String name;
            private int age;
            public InnerClass(){
                this.name = "inner";
                this.age = 18;
            }
            public String getName(){
                return name;
            }
            public int getAge(){
                return age;
            }
        }
    %>
</body>
</html>
