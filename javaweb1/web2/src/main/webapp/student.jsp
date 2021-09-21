<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/5/27
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="userBean" class="com.model.User" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生登录页面</title>
</head>
<body>

<h1 align="center"><jsp:getProperty property="userName" name="userBean"/>同学，欢迎您！</h1>
</body>
</html>

