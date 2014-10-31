<%--
  Created by IntelliJ IDEA.
  User: 星宇
  Date: 2014/10/26
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="s" uri="/struts-tags"%>--%>
<!DOCTYPE html>
<html>
<head>
    <link href="webstatic/css/bootstrap-theme.css" rel="stylesheet">
    <link href="webstatic/css/bootstrap.css" rel="stylesheet">
    <link href="webstatic/css/bootstrap-theme.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="webstatic/css/login.css">
    <script type="text/javascript" src="webstatic/js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="webstatic/js/bootstrap.js"></script>
    <script type="text/javascript" src="webstatic/js/student_login.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户登陆</title>
</head>
<body>
<div class='loginDiv'>
    <div id="loginForm">
        <div class="form-group" style="text-align: center;">
            <label style="font-size: x-large;">请登录</label>
            <div id="wrongAccError" class="alert alert-danger" role="alert" style="display: none;"><strong>用户名密码不匹配</strong></div>
            <div id="wrongAccEmpty" class="alert alert-danger" role="alert" style="display: none;"><strong>请输入用户名或密码</strong></div>
        </div>
        <div class="form-group">
            <label for="uid" class="control-label">用户名</label>
            <input type="text" class="form-control" id="uid" placeholder="只能为数字">
        </div>
        <div class="form-group">
            <label for="upass" class="control-label">密码</label>
            <input type="password" class="form-control" id="upass" placeholder="">
        </div>
        <div class="form-group" id="divLoginBtn"><button id="loginBtn" class="btn btn-primary">登陆</button></div>
    </div>
</div>
<script>
</script>
</body>

