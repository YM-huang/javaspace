<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/6/14
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>模糊查询客户</title>
</head>
<body>
<form action="fuzzyqueryCustomer.do" method="post">
    请输入客户姓名或编号（支持模糊查询）：
    <input type="text" name="cname" size="15">
    <input type="submit" value="确定">
</form>
<p>
    <a href="allCustomer.do">查询所有客户</a>
</p>
</body>
</html>
