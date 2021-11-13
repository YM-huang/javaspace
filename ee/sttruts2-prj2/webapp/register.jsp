<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <s:head theme="xhtml"/>
    <sx:head parseContent="true" extraLocales="GBK"/>
</head>
<body>

    <s:form action="register" method="POST">
        <s:textfield name="regUser.account" key="register.account.label"/>
        <s:password name="regUser.password" key="register.password.label"/>
        <s:password name="regUser.repassword" key="register.repassword.label"/>
        <s:radio list="#{1:'男',0:'女'}" label="register.sex.label" name="regUser.sex.label"/>
        <sx:datetimepicker name="regUser.birthday" displayFormat="yyyy-MM-dd" key="register.birthday.label"/>
        <s:submit name="register.submit.button"/>
        <s:reset name="register.reset.buttton"/>
    </s:form>

</body>
</html>
