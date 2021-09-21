<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/5/27
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登陆界面</title>
    <script language="JavaScript" type="text/javascript">
        function custcheck() {
            var userName=document.getElementById("userName");
            var password=document.getElementById("password");

            if(userName.value.trim()==""){
                alert("用户名不能为空")
                return false;
            }
            else if(password.value.trim()==""){
                alert("密码不能为空");
                return false;
            }

        }
    </script>
</head>
<body>
    <form action ="LoginActionServlet" method="post" onsubmit="return custcheck()">
        <table>
            <tr><td>用户类型：</td><td><input type="text" name="userType" id="userType" value="学生"></td></tr>
            <tr><td>用户名</td><td><input type="text" name="id" id="id"></td></tr>
            <tr><td>用户密码：</td><td><input type="text" name="password" id="password"></td></tr>
            <tr><td><input type="submit" value="登录"></td><td><input type="reset" value="重置"></td></tr>
        </table>
    </form>
</body>
</html>

