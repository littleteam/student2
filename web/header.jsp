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
    if(state ==null || state != 1) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<header>
    <div>
        <span>课程管理系统</span>
        <script type="text/javascript">
        </script>
        <span class="userName dropdown">
            <a id="userNameDropdown" href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                loading...
            </a>
            <span class="caret"></span>
            <ul class="dropdown-menu" role="menu" aria-labelledby="userNameDropdown">
                <%--<li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="dealModifyPass()">修改密码</a></li>--%>
                <li role="presentation"><a type="button" role="menuitem" tabindex="-1" href="#" data-toggle="modal"
                    data-target="#modifyPassModal" onclick="clearModifyPassForm()">修改密码</a></li>
                <li role="presentation" class="divider"></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="/login_Logout">注销登陆</a></li>
            </ul>
        </span>
    </div>
</header>