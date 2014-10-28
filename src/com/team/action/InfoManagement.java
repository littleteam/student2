package com.team.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.team.dao.AccountDao;
import com.team.domain.Admin;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 斌 on 2014/10/28.
 */
public class InfoManagement extends ActionSupport {
    private String result;
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String Info() throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        AccountDao acDAO = new AccountDao();
        ActionContext ctx = ActionContext.getContext();
        map.put("userinfo",ctx.getSession().get("userinfo"));
        result = JSONObject.fromObject(map).toString();
        System.out.println(result);

        return Action.SUCCESS;
    }
}
