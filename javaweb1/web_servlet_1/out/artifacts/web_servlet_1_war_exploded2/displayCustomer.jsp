<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/5/15
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="customer" class="com.example.model.Customer" scope="session">
    <jsp:setProperty name="customer" property="*"/>
</jsp:useBean>
<html>
<head>
    <title>显示信息</title>
</head>
<body>
    新用户已经创建！
    <font color="#0000FF"> <%=request.getParameter("email")%></font>
    <hr><a href="login.jsp"> 马上去登录 </a>
    <h4>顾客信息如下</h4>
    <table border="1">
        <tr>
            <td>邮件:</td>
            <td><jsp:getProperty name="customer" property="email"/></td>
        </tr>
        <tr>
            <td>姓名:</td>
            <td><jsp:getProperty name="customer" property="custName"/></td>
        </tr>
        <tr>
            <td>电话:</td>
            <td><jsp:getProperty name="customer" property="phone"/></td>
        </tr>
    </table>
    <a href="displayAllCustomer.jsp"> 查看所有人的信息 </a>
</body>
</html>
