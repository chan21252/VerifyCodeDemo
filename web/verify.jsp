<%--
  Created by IntelliJ IDEA.
  User: cuican
  Date: 2019/3/28
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人机验证</title>
</head>
<body>
<div id="msg">${pageContext.session.getAttribute("msg")}</div>
<form action="/submit.do" method="post">
    <img src="/verifyCode" id="verify-img"/>
    <br/>
    <input type="text" name="verifyText" id="input-text"/>
    <br/>
    <input type="submit" value="验证" id="input-btn"/>
</form>

<script>
    document.getElementById("verify-img").onclick = function () {
        document.getElementById("verify-img").src = "/verifyCode?" + new Date().getTime();
    };
</script>
</body>
</html>
