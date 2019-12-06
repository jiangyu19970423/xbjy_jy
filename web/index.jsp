<%@ page import="com.sys.entity.User" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.TypeReference" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script src="/static/bootstrap-3.3.7-dist/js/jquery-3.3.1.min.js"></script>
    <script src="/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <title>首页登录</title>
    <style>
        body {
            width: 20em;
            margin: 300px auto;
            background: url("/static/img/bg.jpg") no-repeat fixed;
            background-size: cover;
        }
    </style>
</head>

<%
    Cookie[] cookies = request.getCookies();
    User loginUser = new User();
    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            if ("cookieLoginUser".equals(cookies[i].getName())) {
                String value = cookies[i].getValue();
                //解码
                String strJson = URLDecoder.decode(value, "utf-8");
                //把json字符串转换成java对象
                loginUser = JSON.parseObject(strJson, new TypeReference<User>() {
                });
            }
        }
    }

%>
<body>

<form action="/sys/login/login" method="get">
    <div class="form-group">
        <div class="input-group">
            <div class="input-group-addon">账号</div>
            <input type="text" class="form-control" placeholder="请输入账号" name="account"
                   value="<%=loginUser.getAccount()==null?"":loginUser.getAccount()%>"
                   id="account">
        </div>
        <br>
        <div class="input-group">
            <div class="input-group-addon">密码</div>
            <input type="text" class="form-control" placeholder="请输入密码" name="password"
                   value="<%=loginUser.getPassword()==null?"":loginUser.getPassword()%>"
                   id="password">
        </div>
        <br>
        <div class="input-group">
            <div class="input-group-addon">验证码</div>
            <img src="/sys/login/getPic" alt="无法加载" id="img" onclick="getPic()">
            <input type="text" class="form-control" placeholder="请输入四位验证码" name="picCode" value="">
        </div>
        <br>
        <div class="input-group">
            <div class="input-group-addon">7天免登录</div>
            <input type="checkbox" class="form-control" name="remember" value="1">
        </div>
        <br>
        <div class="text-center">
            <input type="submit" class="btn btn-primary" value="登录">
            <a href="/view/sys/login/forget.jsp" class="btn btn-warning">忘记密码？</a>
        </div>
    </div>
</form>

</body>

<script>
    function getPic() {
        $("#img").attr("src", $("#img").attr("src") + "?nocache=" + new Date().getTime());
        //document.getElementById("img").src = document.getElementById("img").src + "?nocache=" + new Date().getTime();
    }
</script>
</html>
