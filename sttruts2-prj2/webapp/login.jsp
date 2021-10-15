<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>
        .text-left{
            text-align: right;
        }
    </style>
</head>
<body>

    <s:i18n name="cn.edu.zjut.local.message">
        <s:fielderror/>
        <s:actionerror/>
        <s:form action="login" method="post">
            <s:textfield name="loginUser.account" key="login.account.label"/><br>
            <s:password name="loginUser.password" key="login.password.label"/>
            <s:submit name="submit" align="left" key="login.submit.button"/>
        </s:form>
    </s:i18n>
</body>
</html>
