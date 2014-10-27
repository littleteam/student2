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
    <script type="text/javascript" src="webstatic/js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="webstatic/js/bootstrap.js"></script>
    <script type="text/javascript" src="webstatic/js/student_login.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户登陆</title>
</head>
<body>

<div class="modal fade in" id="myModal" aria-labelledby="myModalLabel" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    登录或注册
                </h4>
            </div>
            <div id="userDetail" class="modal-body">
                <form id="login">
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
            </div>
        </div>`
    </div>
</div>
<%--<s:form>--%>
    <%--<s:textfield name="account.uid" label="用户名" />--%>
    <%--<s:textfield name="account.accPass" label="密码" />--%>
    <%--<s:submit id="loginBtn" value="登陆" />--%>
<%--</s:form>--%>
</body>

