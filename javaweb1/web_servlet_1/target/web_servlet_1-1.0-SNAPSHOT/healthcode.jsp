<%--
  Created by IntelliJ IDEA.
  User: kbdnzzzzz
  Date: 2021/5/16
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>杭州健康码</title>

    <link rel="stylesheet" type="text/css" href="css/1.css">
    <link rel="stylesheet" type="text/css" href="css/2.css">
</head>
<body>
    <form action="healthCodeServlet" method="post" onSubmit="return custCheck()">
        <table align="center"width="350px">
            <tr><td><input style="width:350px;height:180px"type="image"src="photo/5.png"/></td></tr>
            <tr>
            <td  height=35 align=left style="word-break:keep-all;"><span style="color:orange">*</span>姓名
                <font style="border:1px solid #CADEFB;padding:1px 1px 1px 1px;width:27px;border-radius: 25px; color:#CADEFB;font-size:70%;" >填空</font>
                <br><br>
                <input type="text" name="name" style="width: 350px">
                <br><br>
            </tr>
            <tr>
                <td  height=35 align=left style="word-break:keep-all;"><span style="color:orange">*</span>学号
                    <font style="border:1px solid #CADEFB;padding:1px 1px 1px 1px;width:27px;border-radius: 25px; color:#CADEFB;font-size:70%;" >填空</font>
                    <br><br>
                <input type="text" name="stuid" style="width: 350px">
                    <br><br>
            </tr>
            <tr>
                <td  height=35 align=left style="word-break:keep-all;"><span style="color:orange">*</span>身份证号
                    <font style="border:1px solid #CADEFB;padding:1px 1px 1px 1px;width:27px;border-radius: 25px; color:#CADEFB;font-size:70%;" >填空</font>
                    <br><br>
                    <input type="text" name="idnumber" style="width: 350px">
                    <br><br>
            </tr>
            <tr>
                <td  height=35 align=left style="word-break:keep-all;"><span style="color:orange">*</span>手机号
                    <font style="border:1px solid #CADEFB;padding:1px 1px 1px 1px;width:27px;border-radius: 25px; color:#CADEFB;font-size:70%;" >填空</font>
                    <br><br>
                    <input type="text" name="phone" style="width: 350px">
                    <br><br>
            </tr>

            <tr bgcolor=#F5F5F5>
                <td></td>
            </tr>
            <tr><td  height=35 align=left style="word-break:keep-all;"><span style="color:orange">*</span>您最近14天是否去过重点疫区？
                <font style="border:1px solid #CADEFB;padding:1px 1px 1px 1px;width:27px;border-radius: 25px; color:#CADEFB;font-size:70%;" >单选</font>
                <br><br><input type="radio" name="0"id="0" value="no">
                <label for="0"></label>
                <span>否</span>
                <hr align=right width=330 color=#F6F6F6 size=1>
                <br><input type="radio"name="0"id="10" value="yes">
                <label for="10"></label>
                <span>是</span>
                <br>
                <br>
            </td>
            </tr>
            <tr bgcolor=#F5F5F5>
                <td></td>
            </tr>
            <tr><td  height=35 align=left style="word-break:keep-all;"><span style="color:orange">*</span>您最近14天是否去过国外？
                <font style="border:1px solid #CADEFB;padding:1px 1px 1px 1px;width:27px;border-radius: 25px; color:#CADEFB;font-size:70%;" >单选</font>
                <br><br><input type="radio" name="1"id="1" value="no">
                <label for="1"></label>
                <span>否</span>
                <hr align=right width=330 color=#F6F6F6 size=1>
                <br><input type="radio"name="1"id="11" value="yes">
                <label for="11"></label>
                <span>是</span>
                <br>
            </td>
            </tr>
            <tr bgcolor=#F5F5F5>
                <td>&nbsp;</td>
            </tr>
            <tr><td height=35 align=left><font color=orange>*</font>您最近14天是否接触过新冠确诊病人或疑似病人？
                <font style="border:1px solid #CADEFB;padding:1px 1px 1px 1px;width:27px;border-radius: 25px; color:#CADEFB;font-size:70%;" >单选</font>
                <br><br><input type="radio" name="2"id="2" value="no">
                <label for="2"></label>
                <span>否</span>
                <hr align=right width=330 color=#F6F6F6>
                <br><input type="radio"name="2" id="12" value="yes">
                <label for="12"></label>
                <span>是</span>
            </td>
            </tr>
            <tr bgcolor=#F5F5F5>
                <td>&nbsp;</td>
            </tr>
            <tr><td height=35 align=left><font color=orange>*</font>您最近14天是否是否被卫生部门确认为新冠肺炎确诊病例或疑似病例？
                <font style="border:1px solid #CADEFB;padding:1px 1px 1px 1px;width:27px;border-radius: 25px; color:#CADEFB;font-size:70%;" >单选</font>
                <br><br><input type="radio" name="3"id="3" value="no">
                <label for="3"></label>
                <span>否</span>
                <hr align=right width=330 color=#F6F6F6>
                <br><input type="radio"name="3"id="13" value="yes">
                <label for="13"></label>
                <span>是</span>
            </td>
            </tr>
            <tr bgcolor=#F5F5F5>
                <td>&nbsp;</td>
            </tr>
            <tr><td height=90 align=left><font color=#FFA500>*</font>当前健康状况（若有以下状况，请在方框内勾选）
                <font style="border:1px solid #FFA500; padding:1px 1px 1px 1px;  width:27px; border-radius:25px; color:orange; font-size:70%;">多选</font>
                <br>
                <br><input type="checkbox"name="4"id="4"value="checkbox0"
                           οnfοcus="disableElement(this)" onBlur="renewElement(this)">
                <label for="4"></label>
                <span>无异常</span>

                <hr align=right width=330 color=#F6F6F6>
                <br><input type="checkbox"name="4"id="5"value="checkbox1"
                           οnfοcus="disableElement(this)" onBlur="renewElement(this)">
                <label for="5"></label>
                <span>发烧（≥37.3℃）</span>

                <hr align=right width=330 color=#F6F6F6>
                <br><input type="checkbox"name="4"id="6"value="checkbox2"
                           οnfοcus="disableElement(this)" onBlur="renewElement(this)">
                <label for="6"></label>
                <span>乏力</span>
                <hr align=right width=330 color=#F6F6F6>
                <br><input type="checkbox"name="4"id="7"value="checkbox3"
                           οnfοcus="disableElement(this)" onBlur="renewElement(this)">
                <label for="7"></label>
                <span>干咳</span>
                <hr align=right width=330 color=#F6F6F6>
                <br><input type="checkbox"name="4"id="8"value="checkbox4"
                           οnfοcus="disableElement(this)" onBlur="renewElement(this)">
                <label for="8"></label>
                <span>鼻塞</span>
                <hr align=right width=330 color=#F6F6F6>
                <br><input type="checkbox"name="4"id="9"value="checkbox5"
                           οnfοcus="disableElement(this)" onBlur="renewElement(this)">
                <label for="9"></label>
                <span>流涕</span>
                <hr align=right width=330 color=#F6F6F6>
                <br><input type="checkbox"name="4"id="91"value="checkbox6"
                           οnfοcus="disableElement(this)" onBlur="renewElement(this)">
                <label for="91"></label>
                <span>咽痛</span>
                <hr align=right width=330 color=#F6F6F6>
                <br><input type="checkbox"name="4"id="92"value="checkbox7"
                           οnfοcus="disableElement(this)" onBlur="renewElement(this)">
                <label for="92"></label>
                <span>腹泻</span>
            </td>
            </tr>
            <tr bgcolor=#F5F5F5>
                <td>&nbsp;</td>
            </tr>
            <tr><td height=30 align=left><font size="4"><b>本人郑重承诺：</b></font>
                <br><br>
                <br><input type="checkbox" name="5" id="93">
                <label for="93"></label>
                <span>为疫情防控，本人同意以上信息依法提交所在辖区疫情防控部门统筹管理。</span>
                <hr align=right width=330 color=#F6F6F6>
                <br><input type="checkbox" name="5" id="94">
                <label for="94"></label>
                <span>上述信息是我本人填写，本人对信息内容的真实性和完整性负责。如果信息有误或缺失，本人愿承担相应的法律责任。同时，本人保证遵守防疫管控的各项规定，配合并听从各项措施和要求。</span>
            </td>
            </tr>
            <tr height=100px bgcolor=#F5F5F5 align=center>
                <td>
                    <input style="background:#0088FE ;border:1px solid #0088FE; padding:1px 1px 1px 1px;
        width:250px;height:60px; border-radius:25px; color:white; font-size:100%;"type="submit"value="提交">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
