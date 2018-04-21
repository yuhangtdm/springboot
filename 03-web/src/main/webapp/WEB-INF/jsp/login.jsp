<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form method="post" action="/user/login">
        <label>用户名：</label>
        <input type="text" name="name" />
        <br>
        <lable>密码：</lable>
        <input type="password" name="password" />
        <input type="submit" value="登录" />
    </form>

</body>
</html>
