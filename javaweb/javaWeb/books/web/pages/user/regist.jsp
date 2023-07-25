<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
<!--    写base标签固定相对路径-->
    <!-- css jQuery base-->
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function(){
            $("#sub_btn").click(function(){
                //验证用户名称
                var username = $("#username").val();
                //判断
                var patt = /^\w{6,12}$/;
                if (!patt.test(username)) {
                    $(".errorMsg").text("用户名称不合法");
                    return false;
                }
                //用户密码
                var pw = $("#password").val();
                //判断
                patt = /^\w{6,12}$/;
                if (!patt.test(pw)) {
                    $(".errorMsg").text("用户密码不合法");
                    return false;
                }
                //确认密码
                var repwd = $("#repwd").val();
                if (repwd!==pw){
                    $(".errorMsg").text("两次密码不一致");
                    return false;
                }
                //电子邮件
                var email = $("#email").val();
                patt = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                if (!patt.text(email)){
                    $(".errorMsg").text("邮箱不合法")
                    return false;
                }
                //验证码
                var $code = $("#code").val();
                if ($code==null||$code===""){
                    $(".errorMsg").text("验证码错误")
                    return false;
                }


            })
            <%--//给验证码图片绑定单机时间--%>
            <%--$("#code_img").click(function (){--%>
            <%--    //this表示当前dom对象--%>
            <%--    this.src = "${basePath}/kaptcha.jpg";--%>
            <%--})--%>
            //给验证码图片绑定单机事件
            $("#code_img").click(function (){
                //this表示当前dom对象
                this.src = "${basePath}/kaptcha.jpg?d=" + new Date();//跳过缓存
            })
            //失去焦点事件
            $("#username").blur(function (){
                //获取用户名
                var username = this.value;
                //Ajax请求
                $.getJSON(
                    "http://localhost:8080/books/userServlet",
                    "action=ajaxExistsUsername&username"+username,
                    function (data){//bool : bool(false用户不存在)
                        if (data.bool){//true,用户名存在
                            $("#span.errorMsg").text("用户名已存在");
                            return false;
                        }
                    }
                )

            })
        })

    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">${requestScope.get("msg")}</span> <!--不合法输出-->
                </div>
                <div class="form">
                    <form action="userServlet" method="post"><!--提交地址-->
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username" id="username"
                               value="${requestScope.get("username")}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"
                            value="${requestScope.get("password")}"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"
                               value="${requestScope.get("password")}"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               autocomplete="off" tabindex="1" name="email" id="email"
                               value="${requestScope.get("email")}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" name="code" style="width: 150px;" id="code"/>
                        <img alt="" id="code_img" src="kaptcha.jpg" width=90px height=40px style="float: right; margin-right: 40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>