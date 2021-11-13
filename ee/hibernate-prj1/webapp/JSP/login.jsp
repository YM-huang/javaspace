<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
<%--    <title>登录</title>--%>
<%--    <style>--%>
<%--        .text-left{--%>
<%--            text-align: right;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<s:i18n name="cn.edu.zjut.local.message">--%>
<%--    <form action="login" method="post">--%>
<%--        <s:textfield name="loginUser.account" label="account"/><br>--%>
<%--        <s:password name="loginUser.password" label="password"/>--%>
<%--        <s:submit name="submit" align="left"/>--%>
<%--    </form>--%>
<%--</s:i18n>--%>
<%--</body>--%>
<%--</html>--%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="../css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="../css/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="../css/component.css" />
    <!--[if IE]>
    <script src="../js/html5.js"></script>
    <![endif]-->
</head>
<body>
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>欢迎你</h3>
                <s:i18n name="cn.edu.zjut.local.message">
                    <form action="login" method="post">
                        <div class="input_outer">
                            <span class="u_user"></span>
                            <input name="loginUser.account" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
<%--                            <s:textfield name="loginUser.account" label="account" class="text" style="color: #FFFFFF !important" placeholder="请输入账户"/>--%>
                        </div>
                        <div class="input_outer">
                            <span class="us_uer"></span>
                            <input name="loginUser.password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
<%--                            <s:password name="loginUser.password" label="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;" placeholder="请输入密码"/>--%>
                        </div>
<%--                        <div class="mb2" name="submit" ><a class="act-but submit" href="javascript:;" style="color: #FFFFFF">登录</a></div>--%>
                        <s:submit name="submit" align="center"/>
                    </form>
                </s:i18n>
            </div>
        </div>
    </div>
</div><!-- /container -->
<script src="../js/TweenLite.min.js"></script>
<script src="../js/EasePack.min.js"></script>
<script src="../js/rAF.js"></script>
<script src="../js/demo-1.js"></script>
</body>
</html>