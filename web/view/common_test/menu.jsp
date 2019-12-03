<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/11/29
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>菜单栏</title>
</head>
<script src="/static/js/jquery-3.3.1.js" type="text/javascript"></script>
<script>
    $(function () {
        $.ajax({
            url: "/sys/menu",
            type: "get",
            data: "",
            dataType: "json",
            success: function (data) {
                var html = "";
                var parent = data.parent;
                var son = data.son;
                for (var i = 0; i < parent.length; i++) {
                    html = html + parent[i].name;
                    html = html + '<ul>';
                    for (var j = 0; j < son.length; j++) {
                        if (son[j].pId == parent[i].id) {
                            html = html + '<li><a href="' + son[j].menuUrl + '">' + son[j].name + '</a></li>';
                        }
                    }
                    html = html + '</ul>';
                }
                $("#div-menu").append(html);

            }
        })
    })


</script>
<body>
<div id="div-menu" style="border:1px solid red;height: 85%;width: 10%;float: left">

</div>
</body>
</html>
