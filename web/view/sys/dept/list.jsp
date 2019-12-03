<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/12/2
  Time: 20:02
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
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>部门名称</th>
                    <th>创建时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${dept}" var="dept" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${dept.name}</td>
                        <td>
                                <%--//把日期字符串转化为日期对象--%>
                            <fmt:parseDate var="createTime" pattern="yyyy-MM-dd HH:mm:ss"
                                           value="${dept.createTime}"></fmt:parseDate>
                                <%--//把日期对象转化为规定的日期字符串格式--%>
                            <fmt:formatDate value="${createTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"></fmt:formatDate>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
