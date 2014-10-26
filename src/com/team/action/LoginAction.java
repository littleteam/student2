package com.team.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.team.dao.AccountDao;
import com.team.domain.Account;

/**
 * Created by 星宇 on 2014/10/26.
 */
public class LoginAction extends ActionSupport {
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }



    /* ��֤�û���¼ */
    public String CheckLogin() {
        AccountDao acDAO = new AccountDao();
        ActionContext ctx = ActionContext.getContext();
        if (!acDAO.CheckLogin(account)) {
            ctx.put("error",  java.net.URLEncoder.encode(acDAO.getErrMessage()));
            return "error";
        }
        ctx.getSession().put("username", account.getAccUname());
        return "main_view";

		/*
		 * ActionContext ctx = ActionContext.getContext();
		 * ctx.getApplication().put("app", "Ӧ�÷�Χ");//��ServletContext�����app
		 * ctx.getSession().put("ses", "session��Χ");//��session�����ses ctx.put("req",
		 * "request��Χ");//��request�����req ctx.put("names", Arrays.asList("����", "����",
		 * "�Ϸ�")); HttpServletRequest request = ServletActionContext.getRequest();
		 * ServletContext servletContext = ServletActionContext.getServletContext();
		 * request.setAttribute("req", "����Χ����");
		 * request.getSession().setAttribute("ses", "�Ự��Χ����");
		 * servletContext.setAttribute("app", "Ӧ�÷�Χ����"); // HttpServletResponse
		 * response = ServletActionContext.getResponse();
		 */
    }

}