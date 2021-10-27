<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品列表</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>编号</th>
            <th>书名</th>
            <th>说明</th>
            <th>单价</th>
            <th>图片</th>
        </tr>
        <s:iterator value="items">
            <tr>
<%--                <td><s:property value="isbn"/> </td>--%>
<%--                <td><s:property value="title"/> </td>--%>
                <td><s:property value="ipk.itemID"/> </td>
                <td><s:property value="ipk.title"/> </td>
                <td><s:property value="description"/> </td>
                <td><s:property value="cost"/> </td>
                <td><s:property value="image"/> </td>
            </tr>
        </s:iterator>
    </table>
</body>
</html>
