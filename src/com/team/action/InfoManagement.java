package com.team.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.team.dao.AccountDao;
import com.team.dao.CourseDao;
import com.team.domain.Account;
import com.team.domain.Admin;
import com.team.domain.Course;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
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
    private Course course;
    private String newpasswd;
    private ArrayList<Course> courselist;
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

        // todo: 添加认证

        Map<String, Object> map = new HashMap<String, Object>();
        CourseDao courdao=new CourseDao();
        ActionContext ctx = ActionContext.getContext();
        map.put("querylist", courdao.Query(course));
        map.put("case",RequestType.ListCourse);
        result = JSONObject.fromObject(map).toString();
        System.out.println(result);

        return Action.SUCCESS;
    }

    // 请求修改课表
    public String ModifyCourse() {
        CourseDao coudao=new CourseDao();

        return Action.SUCCESS;
    }

    // 请求修改密码
    public String ModifyPass() {
        ActionContext ctx = ActionContext.getContext();
        AccountDao.ChangePass((Integer)ctx.getSession().get("accid"),newpasswd);
        Map<String,String> map = new HashMap<String, String>();
//        map.put("querlist",);
        result = JSONObject.fromObject(map).toString();
        return Action.SUCCESS;
    }
}
