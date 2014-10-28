package com.team.dao;

/**
 * Created by 星宇 on 2014/10/27.
 */
import com.team.domain.Admin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.team.until.HibernateUtil;
import org.hibernate.Query;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private String errMessage;
    private int adminnumber;
    public String getErrMessage() { return this.errMessage; }
    public int getAdminnumber(){return this.adminnumber;}
    public static void Changeinfo(Admin admin){
        Session s = null;
        Transaction tx=null;
        try {
            s=HibernateUtil.getSession();
            //开始事务
            tx = s.beginTransaction();
            s.update(admin);
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
    public static Admin Showinfo(int adminid){
        Session s=null;
        Transaction tx=null;
        Admin ad=null;
        try {
            s=HibernateUtil.getSession();
            tx=s.beginTransaction();
            ad=(Admin)s.get(Admin.class,adminid);
            tx.commit();
            return ad;
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
    public static ArrayList<Admin> Showalladmin(){
        Session s=null;
        Transaction tx=null;;
        try {
            s=HibernateUtil.getSession();
            tx=s.beginTransaction();
            String hql="from Admin";
            Query q=s.createSQLQuery(hql);
            List alladmin=q.list();
            tx.commit();
            return ((ArrayList<Admin>)alladmin);
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
