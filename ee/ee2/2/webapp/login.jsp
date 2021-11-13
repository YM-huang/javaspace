<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/9/27
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<s:i18n name="cn.edu.zjut.local.message">
    <form action="login" method="post">
        <s:textfield name="loginUser.account" key="login.account.label"/><br>
        <s:password name="loginUser.password" key="login.password.label"/>
        <s:submit name="submit" key="login.submit.button" align="left"/>
    </form>
</s:i18n>
</body>
</html>
