<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/12/2
  Time: 16:47
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
            <form action="/sys/user/list" method="get">
                账号：<input type="text" value="${account}" name="account">
                <input type="submit" value="查询" class="btn btn-primary">
            </form>
            <a href="/view/sys/user/add.jsp" class="btn btn-success">添加</a>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>部门名称</th>
                    <th>账号</th>
                    <th>姓名</th>
                    <th>年龄</th>
                    <th>性别</th>
                    <th>出生日期</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="user" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${user.deptName}</td>
                        <td>${user.account}</td>
                        <td>${user.name}</td>
                        <td>${user.age}</td>
                        <td>
                            <c:if test="${user.sex==1}">男</c:if>
                            <c:if test="${user.sex==0}">女</c:if>
                        </td>
                        <td>
                                <%--把日期字符串转化为日期对象--%>
                            <fmt:parseDate var="birthDate" pattern="yyyy-MM-dd"
                                           value="${user.birthDate}"></fmt:parseDate>
                                <%--//把日期对象转化为规定的日期字符串格式--%>
                            <fmt:formatDate value="${birthDate}" pattern="yyyy年MM月dd日"></fmt:formatDate>
                        </td>
                        <td>
                                <%--//把日期字符串转化为日期对象--%>
                            <fmt:parseDate var="createTime" pattern="yyyy-MM-dd HH:mm:ss"
                                           value="${user.createTime}"></fmt:parseDate>
                                <%--//把日期对象转化为规定的日期字符串格式--%>
                            <fmt:formatDate value="${createTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"></fmt:formatDate>
                        </td>
                        <td>
                            <a href="/sys/user/toUpdate?id=${user.id}" class="btn btn-info">修改</a>
                            <a href="/sys/user/deleteById?id=${user.id}" class="btn btn-danger">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="/sys/user/list?account=${account}&page=1">首页</a>
            <a href="/sys/user/list?account=${account}&page=${page.pageCurrent<=1 ? 1 : (page.pageCurrent-1)}">上一页</a>
            <a href="/sys/user/list?account=${account}&page=${page.pageCurrent>=page.pageCount ? page.pageCount : (page.pageCurrent+1)}">下一页</a>
            <a href="/sys/user/list?account=${account}&page=${page.pageCount}">末页</a>
            当前页：${page.pageCurrent},总页数：${page.pageCount},总记录数：${page.count}
        </div>
    </div>
</div>
</body>
</html>
