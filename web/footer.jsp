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
<footer>
    <div>
        <p>
            课程管理系统<br/>
            &copy;小组成员:@zxy,@cb,@oyyl版权所有
        </p>
    </div>
</footer>