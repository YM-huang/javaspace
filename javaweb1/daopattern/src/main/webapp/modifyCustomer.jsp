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
    <title>修改客户信息</title>
</head>
<body>
<font color="red">${result}</font>
<p>修改客户信息</p>
<form action="modifyCustomer.do" method="post">
    <table>
        <tr><td>客户号：</td><td><input type="text" name="cust_id" value="${customer.cust_id}"></td></tr>
        <tr><td>客户名：</td><td><input type="text" name="cname" value="${customer.cname}"></td></tr>
        <tr><td>Email：</td><td><input type="text" name="email" value="${customer.email}"></td></tr>
        <tr><td>余  额：</td><td><input type="text" name="balance" value="${customer.balance}"></td></tr>
        <tr>
            <td><input type="submit" value="确定"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
</body>
</html>
