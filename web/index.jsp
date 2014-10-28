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
    // redirect if haven't logged in
    if(accountInfoList == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String username=(String)session.getAttribute("username");
%>
<html>
  <head>
      <link href="webstatic/css/bootstrap-theme.css" rel="stylesheet">
      <link href="webstatic/css/bootstrap.css" rel="stylesheet">
      <link href="webstatic/css/bootstrap-theme.css" rel="stylesheet">
      <link rel="stylesheet" type="text/css" href="webstatic/css/login.css">

      <script type="text/javascript" src="webstatic/js/jquery-2.1.1.js"></script>
      <script type="text/javascript" src="webstatic/js/bootstrap.js"></script>
      <script type="text/javascript" src="webstatic/js/student_main.js"></script>

      <title></title>
  </head>
  <body>
  <jsp:include page="header.jsp" />

  <main>
      <div style="width: 20%; float: left;">
          <jsp:include page="main-left.jsp"/>
      </div>
      <div style="width: 80%; float: right;">
          <jsp:include page="main-right.jsp"/>
      </div>
      <%--<ol>--%>
          <%--<li><%=accountInfoList.get(0).getAccUid()%></li>--%>
          <%--<li><%=accountInfoList.get(0).getAccUname()%></li>--%>
          <%--<li><%=accountInfoList.get(0).getAccPass()%></li>--%>
          <%--<li><%=accountInfoList.get(0).getAccIsadmin()%></li>--%>
      <%--</ol>--%>
  </main>
  <jsp:include page="footer.jsp"/>
  </body>
</html>
