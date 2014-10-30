package com.team.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.team.dao.AccountDao;
import com.team.dao.AdminDao;
import com.team.dao.StudentDao;
import com.team.domain.Account;
import com.team.domain.Admin;
import com.team.domain.Student;
import net.sf.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

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

    private String result;
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public String Logout() {
        return "logout";
    }

    public String CheckLogin() {
        AccountDao acDAO = new AccountDao();
        ActionContext ctx = ActionContext.getContext();
        if (!acDAO.CheckLogin(account)) {
            ctx.put("error", java.net.URLEncoder.encode(acDAO.getErrMessage()));
            return "error";
        }

        ctx.getSession().put("username", account.getAccUname());
        List<Account> acc=AccountDao.ShowallAccount();
        ctx.put("acclist",acc);
        return "main_view";
    }
    public String Ajax() throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        AccountDao acDAO = new AccountDao();
        ActionContext ctx = ActionContext.getContext();
        if (acDAO.CheckLogin(account)) {
            map.put("result", "ok");
            map.put("url", "index.jsp");
            // 将登陆信息存入session
            putSession();
//            return "main_view";
        }  else {
            map.put("result", "error");

        }
        result = JSONObject.fromObject(map).toString();

        return Action.SUCCESS;
    }

    private void putSession() {
        ActionContext ctx = ActionContext.getContext();
        Account ac=AccountDao.Showinfo(account.getAccUid());
        ctx.getSession().put("username", ac.getAccUname());
        ctx.getSession().put("isadmin", ac.getAccIsadmin());
        ctx.getSession().put("accid", ac.getAccUid());
        ctx.getSession().put("state",1);
        Admin admin=null;
        Student stu=null;
        if(ac.getAccIsadmin()==1){
            admin=AdminDao.Showinfo(ac.getAccUid());
            ctx.getSession().put("userinfo",admin);
        }
        else if(ac.getAccIsadmin()==0){
            stu= StudentDao.Showinfo(ac.getAccUid());
            ctx.getSession().put("userinfo",stu);
        }
    }
}