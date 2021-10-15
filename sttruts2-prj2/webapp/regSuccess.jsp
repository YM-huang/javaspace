<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功</title>
</head>
<body>
    <s:property value="regUser.account"/>
    <s:if test="%{regUser.sex == \"1\"}">
        <s:text name="先生，"/>
    </s:if>
    <s:else>
        <s:text name="女士，"/>
    </s:else>
    您注册成功了
    <s:set name="user" value="regUser" scope="session"/>
</body>
</html>
