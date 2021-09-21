<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/5/16
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="healthcode" class="com.example.web_servlet_1.healthcode" scope="session">--%>
<%--    <jsp:setProperty name="healthcode" property="*"/>--%>
<%--</jsp:useBean>--%>
<html>
<head>
    <title>健康码</title>
    <script type="text/javascript" src="js/time.js"></script>
    <style>
        body {
            /* 文字/行高 */
            font: 16px/28px 'Microsoft YaHei';
        }


        a {
            text-decoration: none;
            /* 去除下划线 */

        }

        p{
            font-size: 13px;
            text-align: center;
        }

        tr{
            text-align: center;
        }

        /*.left{*/
        /*    text-align: left;*/
        /*    font-size: 16px;*/
        /*    font-weight: 800;*/
        /*}*/

        /*.right{*/
        /*    text-align: right;*/
        /*    font-size: 13px;*/
        /*}*/
    </style>
</head>
<body>
    <table align="center" width="350px">
        <tr>
            <td><div id="displaydate"></div></td>
        </tr>
        <tr>
            <td><div id="displaytime"></div></td>
        </tr>
        <tr>
            <td><hr width=330 color=#F6F6F6></td>
        </tr>
        <tr>
            <td><span style="font-weight: 800; font-size: 20px;">黄益妙</span>&nbsp&nbsp&nbsp&nbsp
                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                <a href="healthcode.jsp" style="font-weight: 200; font-size: 13px;">修改</a></td>
        </tr>
        <tr>
            <td>
                <input style="width:300px;height:300px"type="image"src="photo/qrcode.jpg"/>
            </td>
        </tr>
        <tr>
            <td>
                <p>绿码：凭此码可在浙江省范围内通行，请主动出示,</br>
                    配合检查；并做好自身防护工作，绿码颜色将根据您的</br>
                    申报由当地政府按照相关政策动态更新，出行前请仔</br>
                    细检查您的健康码
                </p>
            </td>
        </tr>
    </table>
</body>
</html>
