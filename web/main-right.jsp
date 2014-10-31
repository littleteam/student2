<%@ page import="com.team.domain.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: 斌
  Date: 2014/10/28
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer state = (Integer)request.getSession().getAttribute("state");
    // redirect if haven't logged in
    if(state ==null || state != 1) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<div id="main-right" class="panel panel-default">
</div>

<div class="modal fade" id="modifyPassModal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                    <div class="form-group">
                        <label for="oldPass" class="control-label">旧密码</label>
                        <input type="password" class="form-control" id="oldPass" placeholder="">
                    </div>
                    <div class="form-group">
                        <label for="newPass" class="control-label">新密码</label>
                        <input type="password" class="form-control" id="newPass" placeholder="">
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="dealModifyPass()">提交</button>
            </div>
        </div>
    </div>
</div>

<div hidden>
    <div id="userPass">
        <div class="panel-heading">修改密码</div>
        <%--Table--%>
    </div>

    <div id="userCourse">
        <div class="panel-heading">课表信息</div>
        <%--Table--%>
        <table id="userCourseTable" class="table">

        </table>
        <button id="courseDelete" class="btn btn-danger"
                style="float:right; margin-right: 9px; margin-bottom: 10px;">删除</button>
        <button id="courseSubmit" class="btn btn-default"
                style="float:right; margin-right: 9px; margin-bottom: 10px;">提交</button>
    </div>
    <div id="userInfo">
        <div class="panel-heading">用户信息</div>
        <%--Table--%>
        <table id="userInfoTable" class="table">

        </table>
    </div>
</div>