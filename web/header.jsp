<%@ page import="com.team.domain.Account" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: 斌
  Date: 2014/10/28
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer state = (Integer)request.getSession().getAttribute("state");
    // redirect if haven't logged in
    if(state != 1) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<header>
    <div>
        <span>课程管理系统</span>
        <span class="userName"><a href="#" title="查看信息">用户名</a></span>
    </div>
</header>