<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="">
<head>
    <% String path=request.getContextPath(); %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Register Page</title>
    <script src="<%=path%>/js/login_code1.js"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/my-login.css">
</head>
<body class="my-login-page">
<section class="h-100">
    <div class="container h-100">

        <div class="row justify-content-md-center h-100">
            <div class="card-wrapper">
                <div class="brand">
                    <img src="<%=path%>/img/pic.png" alt="logo">
                </div>
                <div class="card fat">
                    <div class="card-body">
                        <h4 class="card-title">javaEE_第八次实验(注册界面)</h4>
                        <h5> <s:actionerror/></h5>
                        <form method="POST" class="my-login-validation" novalidate="" action="register">
                            <div class="form-group">
                                <label for="account">注册账号：</label>
                                <input id="account" type="text" class="form-control" name="loginUser.account" value="" required autofocus>
                            </div>

                            <div class="form-group">
                                <label for="name">密码：</label>
                                <input id="pwd1" type="password" class="form-control" name="loginUser.password">
                            </div>
                            <div class="form-group">
                                <label for="name">重复密码：</label>
                                <input id="pwd2" type="password" class="form-control" name="loginUser.repassword" >
                            </div>
                            <div class="form-group">
                                <label for="name">用户名：</label>
                                <input id="name" type="text" class="form-control" name="loginUser.name" value="" >
                            </div>
                            <div class="form-group">
                                <label>性别：</label>
                                <label style="width: 20%"><input  type="radio"  name="loginUser.sex" value="0" >女</label>
                                <label style="width: 20%"><input  type="radio"  name="loginUser.sex" value="1" >男</label>
                            </div>
                            <div class="form-group">
                                <label for="birthday">生日：</label>
                                <input id="birthday" type="date" class="form-control" name="loginUser.birthday" value="">
                            </div>
                            <div class="form-group">
                                <label for="phone">电话号码：</label>
                                <input id="phone" type="text" class="form-control" name="loginUser.contactInfo.phone" value="">
                            </div>
                            <div class="form-group">
                                <label for="email">电子邮箱：</label>
                                <input id="email" type="email" class="form-control" name="loginUser.contactInfo.email" value="">
                            </div>
                            <div class="form-group">
                                <label for="address">地址：</label>
                                <input id="address" type="text" class="form-control" name="loginUser.contactInfo.address" value="">
                            </div>
                            <div class="form-group">
                                <label for="zipcode">邮政编码：</label>
                                <input id="zipcode" type="text" class="form-control" name="loginUser.contactInfo.zipcode" value="">
                            </div>
                            <div class="form-group">
                                <label for="fax">传真：</label>
                                <input id="fax" type="text" class="form-control" name="loginUser.contactInfo.fax" value="">
                            </div>
                            <div class="form-group m-0">
                                <button type="submit" class="btn btn-primary btn-block">
                                    注册
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="footer">
                    Copyright &copy; 2021 zjut JAVA_EE experience_6 by zcq
                </div>
            </div>
        </div>
    </div>
</section>
<script src="<%=path%>/js/pointandline.js" type="text/javascript"></script>
</body>
</html>
