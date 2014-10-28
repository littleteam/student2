package com.team.dao;

/**
 * Created by 星宇 on 2014/10/28.
 */
import com.team.domain.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.team.until.HibernateUtil;
public class StudentDao {
    public static void Changeinfo(Student student){
        Session s = null;
        Transaction tx=null;
        try {
            s=HibernateUtil.getSession();
            //开始事务
            tx = s.beginTransaction();
            s.update(student);
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
    public static Student Showinfo(int stuid){
        Session s=null;
        Transaction tx=null;
        Student stu=null;
        try {
            s=HibernateUtil.getSession();
            tx=s.beginTransaction();
            stu=(Student)s.get(Student.class,stuid);
            tx.commit();
            return stu;
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
