<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/5/14
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action = "CustomerServlet.do" method = "post">
        <table>
            <tr><td>邮件地址：</td> <td><input type="text" name="email" ></td></tr>
            <tr><td>密码：</td><td><input type="text" name="password"></td></tr>
            <tr><td>姓名：</td><td><input type="text" name="custName" ></td></tr>
            <tr><td>电话：</td><td><input type="text" name="phone" ></td></tr>
            <tr><td><input type="submit" value="确定" ></td>
                <td><input type="reset" value="重置" ></td>
            </tr>
        </table>
    </form>
</body>
</html>
