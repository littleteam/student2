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


enum RequestType{
    PerRequest,     // 查看个人信息
    ListCourse,     //查看课表
    ModifyCourse,   //修改课程
    ModifyPass      //修改密码
}


public class InfoManagement extends ActionSupport {
    private String result;
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    // 请求个人信息
    public String PerInfo() throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        AccountDao acDAO = new AccountDao();
        ActionContext ctx = ActionContext.getContext();

        // 添加请求类型
        map.put("case", RequestType.PerRequest);

        map.put("userinfo",ctx.getSession().get("userinfo"));
        result = JSONObject.fromObject(map).toString();
        System.out.println(result);

        return Action.SUCCESS;
    }

    // 请求查看课表
    public String ListCourse() {

        return Action.SUCCESS;
    }

    // 请求修改课表
    public String ModifyCourse() {

        return Action.SUCCESS;
    }

    // 请求修改密码
    public String ModifyPass() {

        return Action.SUCCESS;
    }
}
