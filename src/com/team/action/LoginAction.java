package com.team.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.team.dao.AccountDao;
import com.team.domain.Account;
import net.sf.json.JSONObject;
import org.apache.struts2.RequestUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.JSON;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
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

    public String CheckLogin() {
        AccountDao acDAO = new AccountDao();
        ActionContext ctx = ActionContext.getContext();
        if (!acDAO.CheckLogin(account)) {
            ctx.put("error", java.net.URLEncoder.encode(acDAO.getErrMessage()));
            return "error";
        }
        ctx.getSession().put("username", account.getAccUname());
        List<Account> acc=AccountDao.ShowAccount();
        ctx.put("acclist",acc);
        return "main_view";
    }
    public String Ajax() throws IOException {

        HttpServletResponse response = ServletActionContext.getResponse();
//        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();

        Map<String, String> map = new HashMap<String, String>();
        map.put("result", "ok");

//        Map map = new HashMap();
//        map.put("result", "fsd");
        result = JSONObject.fromObject(map).toString();

        AccountDao acDAO = new AccountDao();
        ActionContext ctx = ActionContext.getContext();
//        if (!acDAO.CheckLogin(account)) {
//            printWriter.write("{\"result\":\"false\"}");
//        }
//        printWriter.write("{\"result\":\"ok\"}");
//        printWriter.flush();
//        printWriter.close();

        return Action.SUCCESS;
    }
}