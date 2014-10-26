package com.team.dao;

/**
 * Created by 星宇 on 2014/10/26.
 */

import com.team.domain.Account;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.team.until.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {/*保存业务逻辑错误信息字段*/
    private String errMessage;
    public String getErrMessage() { return this.errMessage; }

    /*验证用户登录*/
    public boolean CheckLogin(Account account) {
        Session s = null;

        //实例化Configuration，
        Configuration conf = new Configuration()
                //下面方法默认加载hibernate.cfg.xml文件
                .configure();
        //以Configuration创建SessionFactory
        SessionFactory sf = conf.buildSessionFactory();
        //创建Session
        s = sf.openSession();
        //开始事务
        Transaction tx =s.beginTransaction();

        Account db_acc = (Account)s.get(Account.class, account.getAccUid());
        if(db_acc == null) {
            this.errMessage = " 账号不存在 ";
            System.out.print(this.errMessage);
            return false;
        } else if( !db_acc.getAccPass().equals(account.getAccPass())) {
            this.errMessage = " 密码不正确! ";
            System.out.print(this.errMessage);
            return false;
        }
        tx.commit();
        s.close();

        return true;
    }
    /*账号修改密码*/
    public static void ChangePass(int accuid,String accpass) {
        Session s = null;
        Transaction tx=null;
        try {
            //实例化Configuration，
            Configuration conf = new Configuration()
                    //下面方法默认加载hibernate.cfg.xml文件
                    .configure();
            //以Configuration创建SessionFactory
            SessionFactory sf = conf.buildSessionFactory();
            //创建Session
            s = sf.openSession();
            //开始事务
            tx = s.beginTransaction();
            Account acc = (Account)s.get(Account.class,accuid);
            acc.setAccPass(accpass);
            tx.commit();
        }
        catch (HibernateException e)
        {
            if(tx!=null)
                tx.rollback();
            throw e;
        } finally {
            s.close();
        }
    }
   /*删除账号*/
    public static void DeleateAcc(int accuid){
        Session s = null;
        Transaction tx=null;
        try {
            //实例化Configuration，
            Configuration conf = new Configuration()
                    //下面方法默认加载hibernate.cfg.xml文件
                    .configure();
            //以Configuration创建SessionFactory
            SessionFactory sf = conf.buildSessionFactory();
            //创建Session
            s = sf.openSession();
            //开始事务
            tx = s.beginTransaction();
           Object acc = (Account)s.get(Account.class,accuid);
            s.delete(acc);
            tx.commit();
        }
        catch (HibernateException e)
        {
            if(tx!=null)
                tx.rollback();
            throw e;
        } finally {
            s.close();
        }
    }
    public static ArrayList<Account> ShowAccount()
    {
        Session s = null;
        try {
            s=HibernateUtil.getSession();
            String hql="From Account";
            Query q=s.createQuery(hql);
            List accoutlist=q.list();
            return (ArrayList<Account>) accoutlist;
        }
       finally {
            HibernateUtil.closeSession();
        }
    }
}
