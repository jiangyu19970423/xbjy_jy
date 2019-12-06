<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/11/29
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导航栏</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/main.css">
    <script src="/static/js/jquery-3.3.1.min.js"></script>
    <script src="/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
<div class="nav navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a href="" class="navbar-brand">小标交友</a>

        </div>
        <form class="navbar-form navbar-right">
            当前在线人数:${applicationScope.applicationLoginCount}
            <a href="/index.jsp">登出</a>

        </form>
    </div>
</div>
</body>
</html>
