<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Register Page</title>

</head>
<body class="my-login-page">

                        <form method="POST" class="my-login-validation" novalidate="" action="register">
                            <div class="form-group">
                                <label for="account">注册账号：</label>
                                <input id="account" type="text" class="form-control" name="loginUser.account" value="" required autofocus>
                            </div>

                            <div class="form-group">
                                <label for="pwd1">密码：</label>
                                <input id="pwd1" type="password" class="form-control" name="loginUser.password">
                            </div>
                            <button type="submit">
                                注册
                            </button>
                        </form>

</body>
</html>
