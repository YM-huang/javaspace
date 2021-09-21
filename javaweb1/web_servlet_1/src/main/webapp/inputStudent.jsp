<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/5/14
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>title</title>
</head>
<body>
    <h4>输入学生信息</h4>
    <form action="StudentServlet" method="post">
        <tr>
            <td>学号</td>
            <td><input type="text" name="stuid"></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>专业</td>
            <td><input type="text" name="major"></td>
        </tr>
        <tr>
            <td><input type="submit" value="确定"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </form>
</body>
</html>
