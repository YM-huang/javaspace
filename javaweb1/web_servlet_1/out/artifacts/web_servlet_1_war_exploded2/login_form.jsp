<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/5/15
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<p><font color="#6666CC"> 请登陆 </font></p>
<hr>
<form name="form1" method="post" action="loginServlet">
    <table width="68%" border="0" cellpadding="2" cellspacing="2">
        <tr>
            <td width="33%" align="right"> 用户名： </td>
            <td width="67%">
                <input type="text" name="email"></td>
        </tr>
        <tr>
            <td align="right"> 密码： </td>
            <td><input type="text" name="password" ></td>
        </tr>
        <tr align="center">
            <td colspan="2">
                <input type="submit" name="Submit" value=" 登陆 ">
                <a href='inputCustomer.jsp'> 未注册？ </a>
            </td>
        </tr>
    </table>
</form>
