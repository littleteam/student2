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
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
%>
<div id="main-left" class="list-group">
    <a href="#" id="aPerInfo" class="list-group-item active">个人信息</a>
    <a href="#" id="aCourseQuery" class="list-group-item">课表查询</a>
    <a href="#" class="list-group-item" style="display:none">密码修改</a>
</div>