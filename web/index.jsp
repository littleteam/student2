<%--
  Created by IntelliJ IDEA.
  User: 斌
  Date: 2014/10/26
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<%@ page import="com.team.domain.Account" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    List<Account> accountInfoList = (List<Account>)request.getSession().getAttribute("acclist");
    String username=(String)session.getAttribute("username");
%>
<html>
  <head>
    <title></title>
  </head>
  <body>
<ol>
    <li><%=accountInfoList.get(0).getAccUid()%></li>
    <li><%=accountInfoList.get(0).getAccUname()%></li>
    <li><%=accountInfoList.get(0).getAccPass()%></li>
    <li><%=accountInfoList.get(0).getAccIsadmin()%></li>
</ol>
  </body>
</html>
