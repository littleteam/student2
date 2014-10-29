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
<div hidden>
<div id="userPass">
    <div class="panel-heading">修改密码</div>
    <%--Table--%>
    <div class="form-group">
      <label for="oldPass" class="control-label">原始密码</label>
      <input type="password" id="oldPass" name="some_name" class="control-label">
    </div>
    <div class="form-group">
      <label for="newPass" class="control-label">新密码</label>
      <input type="password" id="newPass" name="some_name" class="control-label">
    </div>
    <div class="form-group">
      <button id="btnModifyPass" class="btn btn-default">提交</button>
    </div>
</div>
<div id="userCourse">
    <div class="panel-heading">课表信息</div>
    <%--Table--%>
    <table id="userCourseTable" class="table">

    </table>
</div>
<div id="userInfo">
    <div class="panel-heading">用户信息</div>
    <%--Table--%>
    <table id="userInfoTable" class="table">
      
    </table>
</div>
</div>