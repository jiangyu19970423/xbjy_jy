<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/12/3
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
</head>
<body>
<form action="/sys/user/forgetPassWord" method="get">
    账号：<input type="text" name="account" value=""><br><br>
    新密码：<input type="text" name="password" value=""><br><br>
    邮箱：<input type="text" id="email" value="">
    <input type="button" value="发送验证码" id="btn-send"/> <span id="time"></span><br><br>
    验证码：<input type="text" name="code" value=""><br><br>
    <input type="submit" name="" value="修改">

</form>
</body>
<script>
    //倒数计时60秒内可以验证
    var time = 60;
    //定时器
    var dsq;

    $(function () {
        $("#btn-send").click(function () {

            dsq = window.setInterval("changeTime()", 1000);
            $("#btn-send").attr("disabled", "disabled");

            var email = $("#email").val();
            $.ajax({
                url: "/sys/email/sendEmail",
                data: {email: email},
                type: "get",
                dataType: "text",
                success: function (data) {
                    alert(data);
                }
            });

        });
    });

    function changeTime() {
        --time;
        $("#time").text(time);
        if (time == 0) {
            $("#time").text("");
            time = 60;
            window.clearInterval(dsq);
            $("#btn-send").attr("disabled", null);
        }
    }

</script>
</html>
