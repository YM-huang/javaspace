<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/5/14
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="student" class="com.example.web_servlet_1.student" scope="session">
    <jsp:setProperty name="student" property="*"/>
</jsp:useBean>
<html>
<head>
    <title>显示学生信息</title>
</head>
<body>

    <h4>学生信息如下</h4>
    <table border="1">
        <tr>
            <td>学号:</td>
            <td>${student.stuid }</td>
        </tr>
        <tr>
            <td>名字:</td>
            <td><jsp:getProperty name="student" property="name"/></td>
        </tr>
        <tr>
            <td>专业:</td>
            <td><jsp:getProperty name="student" property="major"/></td>
        </tr>
    </table>
</body>
</html>
