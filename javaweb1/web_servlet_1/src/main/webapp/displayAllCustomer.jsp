<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.FileInputStream" %><%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/5/15
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>全部顾客信息</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>邮件</th>
            <th>名字</th>
            <th>电话</th>
        </tr>
        <%
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\customerinfo.txt")));

            String strLine = "";
            while (br.ready()) {
                strLine = br.readLine();
                sb.append("<tr>");
                sb.append("<td>"+strLine.split("\\|")[0]+"</td>");
                sb.append("<td>"+strLine.split("\\|")[2]+"</td>");
                sb.append("<td>"+strLine.split("\\|")[3]+"</td>");
                sb.append("</tr>");
            }
        %>
        <%=sb%>
    </table>
    <a href="login.jsp"> 马上去登录 </a>
</body>
</html>
