package com.team.dao;

/*
 * Created by 星宇 on 2014/10/27.
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import com.team.until.HibernateUtil;
import com.team.domain.School;
import java.util.ArrayList;
import java.util.List;
public class SchoolDao {
    private int totalnum;
    public void setTotatNumber(int recordNumber) {
        this.totalnum = recordNumber;
    }
    public int getTotalNumber() {
        Session s=null;
        Transaction tx=null;
        try {
            s=HibernateUtil.getSession();
            tx=s.beginTransaction();
            String hql="From School";
            Query q=s.createQuery(hql);
            List schoollist=q.list();
            totalnum=q.list().size();
            tx.commit();
            return totalnum;
        }
        finally {
            HibernateUtil.closeSession();
        }
    }
    public static int QueryShool(String schname){
        int schid;
        Session s=null;
        Transaction tx=null;
        try {
            s=HibernateUtil.getSession();
            tx=s.beginTransaction();
            String hql="select s.schId From School s where s.schName=:schname";
            Query q=s.createQuery(hql).setString("schname",schname);
            schid=(Integer)q.list().get(0);
            tx.commit();
            return schid;
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
    public static void Addinfo(School school){
        Session s=null;
        Transaction tx=null;
        try {
            s=HibernateUtil.getSession();
            tx=s.beginTransaction();
            s.save(school);
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
    public static void Deleateinfo(int schid){
        Session s=null;
        Transaction tx=null;
        try {
            s=HibernateUtil.getSession();
            tx=s.beginTransaction();
            Object schinfo = s.get(School.class,schid);
            s.delete(schinfo);
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
    public static void Updatainfo(School school){
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(school);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    public static ArrayList<School> ShowSchool(){
        Session s=null;
        Transaction tx=null;
        try {
            s=HibernateUtil.getSession();
            tx=s.beginTransaction();
            String hql="From School";
            Query q=s.createQuery(hql);
            List schoollist=q.list();
            tx.commit();
            return (ArrayList<School>)schoollist;
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

}
