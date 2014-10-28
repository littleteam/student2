package com.team.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.team.dao.AccountDao;
import com.team.domain.Account;
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
    public String Ajax() throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        AccountDao acDAO = new AccountDao();
        ActionContext ctx = ActionContext.getContext();
        if (acDAO.CheckLogin(account)) {
            map.put("result", "ok");
            map.put("url", "index.jsp");
            putSession();
        }  else {
            map.put("result", "error");
            // 将登陆信息存入session
            putSession();
        }
        result = JSONObject.fromObject(map).toString();

        return Action.SUCCESS;
    }

    private void putSession() {
        ActionContext ctx = ActionContext.getContext();
        ctx.getSession().put("username", account.getAccUname());
        List<Account> acc=AccountDao.ShowAccount();
        ctx.getSession().put("acclist",acc);
    }
}