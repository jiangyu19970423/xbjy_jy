<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/12/2
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <form action="/sys/user/update" method="get">
                <%--隐藏域--%>
                <input type="text" name="id" value="${user.id}" style="display:none;">
                <div class="form-group">
                    <label>部门</label>
                    <select name="deptId" id="dept" class="form-control">

                    </select>
                </div>
                <div class="form-group">
                    <label>账号</label>
                    <input type="text" name="account" class="form-control" value="${user.account}">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" name="password" class="form-control" value="${user.password}">
                </div>
                <div class="form-group">
                    <label>姓名</label>
                    <input type="text" name="name" class="form-control" value="${user.name}">
                </div>
                <div class="form-group">
                    <label>年龄</label>
                    <input type="text" name="age" class="form-control" value="${user.age}">
                </div>
                <div class="form-group">
                    <label>性别</label>
                    <input type="radio" name="sex" value="1"
                           <c:if test="${user.sex==1}">checked</c:if> >男
                    <input type="radio" name="sex" value="0"
                           <c:if test="${user.sex==0}">checked</c:if> >女
                </div>
                <div class="form-group">
                    <label>邮箱</label>
                    <input type="text" name="email" class="form-control" value="${user.email}">
                </div>
                <div class="form-group">
                    <label>出生日期</label>
                    <input type="text" name="birthDate" class="form-control" value="${user.birthDate}">
                </div>
                <div class="form-group">
                    <label>创建时间</label>
                    <input type="text" name="createTime" class="form-control" value="${user.createTime}">
                </div>

                <input type="submit" value="修改">

            </form>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        var deptId = ${user.deptId}
            $.ajax({
                url: "/sys/dept/listAll",
                type: "get",
                data: "",
                dataType: "json",
                success: function (data) {
                    var html = '';
                    for (var i = 0; i < data.length; i++) {
                        if (deptId == data[i].id) {

                            html = html + '<option selected value="' + data[i].id + '">' + data[i].name + '</option>';
                        } else {
                            html = html + '<option  value="' + data[i].id + '">' + data[i].name + '</option>';

                        }
                    }
                    $("#dept").append(html);
                }
            });
    });
</script>
</html>
