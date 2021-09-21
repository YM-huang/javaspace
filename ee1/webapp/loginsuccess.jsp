<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/9/18
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>登陆成功</title>
</head>
<body>
<% UserBean user = (UserBean)(request.getAttribute("USER"));%>
登陆成功，欢迎您，<%=user.getUsername()%>!
</body>
</html>
