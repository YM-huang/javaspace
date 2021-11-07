<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="./findItems">查看商品信息</a>
    <s:property value="#request.tip"/> 修改个人信息
    <s:form action="update" method="post">
        <s:hidden name="loginUser.customerId" value="%{#request.loginUser.customerId}"/>
        <s:textfield name="loginUser.account" label="用户名不能修改" value="%{#request.loginUser.account}" readonly="true"/>
        <s:textfield type="password" name="loginUser.password" label="修改密码" value="%{#request.loginUser.password}"/>
        <s:submit value="修改"/>
    </s:form>
    <s:form action="delete" method="post"> <s:hidden name="loginUser.customerId" value="%{#request.loginUser.customerId}"/>
        <s:submit value="删除"/>
    </s:form>
</body>
</html>
