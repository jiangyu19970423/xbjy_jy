<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/12/2
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${path}/static/jquery-validation-1.9.0/jquery.validate.js"></script>
<html>
<head>
    <title>Title</title>
</head>

<body>
<div>
    <%@include file="/view/common_test/head.jsp" %>
    <div>
        <%@include file="/view/common_test/menu.jsp" %>
        <div style="border:1px solid red;height: 85%;width:85%;float: right">
            <form action="/sys/dept/add" method="get" id="form-add">

                <div class="form-group">
                    <label>部门名称</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="请输入部门名称">
                </div>
                <div class="form-group">
                    <label>创建时间</label>
                    <input type="datetime-local" class="form-control" id="createTime" name="createTime">
                </div>

                <!--居中-->
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">保存</button>
                    <button type="reset" class="btn btn-default">重置</button>
                    <a href="/sys/dept/list" class="btn btn-danger">返回</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

</html>
