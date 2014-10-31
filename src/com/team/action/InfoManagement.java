package com.team.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.team.dao.AccountDao;
import com.team.dao.CourseDao;
import com.team.domain.Account;
import com.team.domain.Admin;
import com.team.domain.Course;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

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

    Map<String, String> wordImage = new HashMap<String, String>();
    public InfoManagement() {
    }

    private String result;
    private Course course;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    private String operate;

    public JSONArray getCourseID() {
        return courseID;
    }

    public void setCourseID(JSONArray courseID) {
        this.courseID = courseID;
    }

    private JSONArray courseID;

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    private String newPass;

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    private String oldPass;
    private ArrayList<Course> courselist;
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }


    //判断是否登陆
    public boolean islogin() {
        ActionContext ctx = ActionContext.getContext();
        return (Integer) ctx.getSession().get("state") == 1;
    }

    // 请求个人信息
    public String PerInfo() throws IOException {
        if (islogin()) {
            Map<String, Object> map = new HashMap<String, Object>();
            AccountDao acDAO = new AccountDao();
            ActionContext ctx = ActionContext.getContext();

            // 添加请求类型
            map.put("case", RequestType.PerRequest);

            map.put("isAdmin", ctx.getSession().get("isadmin"));
            map.put("userinfo", ctx.getSession().get("userinfo"));
            result = JSONObject.fromObject(map).toString();
            System.out.println(result);
            return Action.SUCCESS;
        } else return "logout";
    }

    // 请求查看课表
    public String ListCourse() {

        if (islogin()) {
            Map<String, Object> map = new HashMap<String, Object>();
            CourseDao courdao = new CourseDao();
            ActionContext ctx = ActionContext.getContext();
            map.put("querylist", CourseDao.Query(course));
            map.put("case", RequestType.ListCourse);
            result = JSONObject.fromObject(map).toString();
            System.out.println(result);

            return Action.SUCCESS;
        } else
            return "logout";
    }
    private String stringJson;

    public String getStringJson() {
        return stringJson;
    }

    public void setStringJson(String stringJson) {
        this.stringJson = stringJson;
    }

    // 请求修改课表
    public String ModifyCourse() {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject joob;
        Course cou = new Course();
        if (islogin()) {
           //            JSONObject jsonObject = JSONObject.fromObject(stringJson);
            JSONArray jsonArray = JSONArray.fromObject(stringJson);
            for(int i=0;i<jsonArray.size();i++) {
                joob = JSONObject.fromObject(jsonArray.get(i));
                if(joob.get("couOperate").equals("delete"))
                {
                    CourseDao.Deleate( Integer.parseInt(joob.get("couID").toString()));
                }
                else if(joob.get("couOperate").equals("modify")){

                }
            }
            map.put("querylist",CourseDao.Query(cou));
            map.put("result", "ok");
            result = JSONObject.fromObject(map).toString();
            return Action.SUCCESS;
        } else
            return "logout";
    }

    // 请求修改密码
    public String ModifyPass() {
        String presult;
        if (islogin()) {
            ActionContext ctx = ActionContext.getContext();
            if (AccountDao.ChangePass((Integer) ctx.getSession().get("accid"), oldPass, newPass))
                presult = "ok";
            else
                presult = "error";
            Map<String, Object> map = new HashMap<String, Object>();
//        map.put("querlist",);
            map.put("case", RequestType.ListCourse);
            map.put("result", presult);
            result = JSONObject.fromObject(map).toString();
            return Action.SUCCESS;
        } else
            return "logout";
    }

}
