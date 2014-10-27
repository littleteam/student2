<%--
  Created by IntelliJ IDEA.
  User: 星宇
  Date: 2014/10/26
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="webstatic/css/bootstrap-theme.css" rel="stylesheet">
    <link href="webstatic/css/bootstrap.css" rel="stylesheet">
    <link href="webstatic/css/bootstrap-theme.css" rel="stylesheet">
    <script type="text/javascript" src="webstatic/js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="webstatic/js/bootstrap.js"></script>
    <script type="text/javascript" src="webstatic/js/student_login.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><s:text name="loginPage"/></title>
</head>5
<body>
<%--<s:form action="/login/login_CheckLogin">--%>
    <%--<s:textfield name="account.accUid" key="user"/>--%>
    <%--<s:textfield name="account.accPass" key="pass"/>--%>
    <%--<s:submit key="login" id="loginBtn"/>--%>
<%--</s:form>--%>
<form id="login" action="/login/login_CheckLogin" method="POST">
    <table>
        <tbody>
        <tr>
            <td><label>用户名:<input id="uid" type="text" name="account.uid"></label></td>
        </tr>
        <tr>
            <td><label>密　码:<input id="upass" type="text" name="account.accPass"></label></td>
        </tr>
        <tr>
            <td colspan="2"><input id="loginBtn" type="button" value="登陆"></td>
        </tr>
        </tbody>
    </table>
</form>
</body>

