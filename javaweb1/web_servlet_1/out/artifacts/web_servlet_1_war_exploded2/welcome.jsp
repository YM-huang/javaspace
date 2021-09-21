<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/5/15
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import= "com.example.model.Customer"%>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h1>登陆成功！</h1>
    <%Customer u = (Customer)session.getAttribute("customer");%>
    欢迎你：<font color= red><%=u.getCustName()%></font>
</body>
</html>
