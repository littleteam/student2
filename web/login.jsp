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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><s:text name="loginPage"/></title>
</head>
<body>
<s:form action="/login/login_CheckLogin">
    <s:textfield name="account.accUid" key="user"/>
    <s:textfield name="account.accPass" key="pass"/>
    <s:submit key="login"/>
</s:form>
</body>

