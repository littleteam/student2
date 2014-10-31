package com.team.dao;

/**
 * Created by 星宇 on 2014/10/28.
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import com.team.until.HibernateUtil;
import com.team.domain.Course;
import java.util.ArrayList;
import java.util.List;
public class CourseDao {
    public static void Changeinfo(Course course){
        Session s=null;
        Transaction tx=null;
        try {
            s=HibernateUtil.getSession();
            tx=s.beginTransaction();
            s.update(course);
            tx.commit();
        }
        catch (HibernateException e)
        {
            if(tx!=null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    public static void Deleate(int couid){
        Session s=null;
        Transaction tx=null;
        try {
            s=HibernateUtil.getSession();
            tx=s.beginTransaction();
            Course c=(Course)s.get(Course.class,couid);
            s.delete(c);
            tx.commit();

        }
        catch (HibernateException e)
        {
            if(tx!=null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    public static void Addinfo(Course course){
        Session s=null;
        Transaction tx=null;
        try {
            s=HibernateUtil.getSession();
            tx=s.beginTransaction();
            s.save(course);
            tx.commit();
        }
        catch (HibernateException e)
        {
            if(tx!=null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    public static ArrayList<Object> Query(Course course){
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From Course c where 1=1";
            if(null!=course)
            {
                if(!(course.getCouId()==0)) hql += " and c.couId='" + course.getCouId() + "'";
                if(!course.getCouName().equals(""))  hql += " and c.couName='" + course.getCouName() + "'";
                if(!course.getCouGrade().equals(0)) hql += " and c.couGrade='" + course.getCouGrade() + "'";
                if(!course.getCouSchName().equals("")) hql += " and c.couSchName='" + course.getCouSchName() + "'";

            }
            Query q = s.createQuery(hql);
            /*计算当前显示页码的开始记录*/
            List info =q.list();
            HibernateUtil.closeSession();
            return (ArrayList<Object>) info;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    public static ArrayList<Course> Showall(Course course){
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From Course c where 1=1";
            Query q = s.createQuery(hql);
            List scoreInfoList = q.list();
            return (ArrayList<Course>) scoreInfoList;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
