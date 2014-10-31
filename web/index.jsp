<%--
  Created by IntelliJ IDEA.
  User: 斌
  Date: 2014/10/26
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%
    // 将过期日期设置为一个过去时间

    response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");

// 设置 HTTP/1.1 no-cache 头
    response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");

// 设置 IE 扩展 HTTP/1.1 no-cache headers， 用户自己添加
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");

// 设置标准 HTTP/1.0 no-cache header.
    response.setHeader("Pragma", "no-cache");
%>
<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<%@ page import="com.team.domain.Account" %>
<%
    String path = request.getContextPath();
//    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   Integer state = (Integer)request.getSession().getAttribute("state");
    // redirect if haven't logged in
    if(state ==null || state != 1) {
        response.sendRedirect("login.jsp");
        return;
    }
//    String username=(String)session.getAttribute("username");
%>
<html>
  <head>
      <link href="webstatic/css/bootstrap.css" rel="stylesheet">
      <link href="webstatic/css/bootstrap-theme.css" rel="stylesheet">
      <link rel="stylesheet" type="text/css" href="webstatic/css/login.css">
      <link rel="stylesheet" type="text/css" href="webstatic/css/jquery-ui.css">
      <link rel="stylesheet" type="text/css" href="webstatic/css/animate.css">

      <script type="text/javascript" src="webstatic/js/jquery-2.1.1.js"></script>
      <script type="text/javascript" src="webstatic/js/bootstrap.js"></script>
      <script type="text/javascript" src="webstatic/js/student_main.js"></script>
      <script type="text/javascript" src="webstatic/js/jquery-ui.js"></script>
      <script type="text/javascript" src="webstatic/js/d3.js"></script>

      <title></title>
  </head>
  <body>
  <jsp:include page="header.jsp" />

  <main class="clearfix">
      <div style="width: 20%; float: left;">
          <jsp:include page="main-left.jsp"/>
      </div>
      <div style="width: 80%; float: right; padding-left: 60px;">
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
