<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>51job企业会员登陆界面</title>
    <script language="JavaScript" type="text/javascript">
        function custCheck() {
            var memberName = document.getElementById("memberName");
            var userName = document.getElementById("userName");
            var password = document.getElementById("password");
            if (memberName.value == "" || memberName.value == "请输入会员名") {
                alert("会员名不能为空!");
                return false;
            }
            else if (userName.value == "" || userName.value == "请输入用户名") {
                alert("用户名不能为空!");
                return false;
            } else if (password.value == "" || password.value == "请输入密码") {
                alert("密码不能为空!");
                return false;
            }
        }
    </script>
</head>
<body>

</body>
</html>