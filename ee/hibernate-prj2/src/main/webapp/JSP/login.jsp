<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <s:property value="#request.tip"/>
    <s:form action="login" method="post">
        <s:textfield name="loginUser.account" label="请输入用户名"/>
        <s:password name="loginUser.password" label="请输入密码"/>
        <s:submit value="登录"/>
    </s:form>
</body>
</html>
