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
    List<Account> accountInfoList = (List<Account>)request.getSession().getAttribute("acclist");
    // redirect if haven't logged in
    if(accountInfoList == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Map<String, String> allFunctions = new HashMap<String, String>();
    allFunctions.put("用户信息", "/userInfo");
    allFunctions.put("课表查询", "/queryCourse");
    allFunctions.put("密码修改", "/modifyInfo");
    // 生成列表
//    switch (accountInfoList.get(0).getAccIsadmin()) {
//        case 0:
//
//            break;
//        case 1:
//            break;
//        default:response.sendRedirect("/login.jsp");
//    }
%>
<div id="main-left" class="list-group">
    <a href="#" class="list-group-item active">个人信息</a>
    <a href="#" class="list-group-item">课表查询</a>
    <a href="#" class="list-group-item">密码修改</a>
</div>