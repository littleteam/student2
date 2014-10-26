package com.team.dao;

/**
 * Created by 星宇 on 2014/10/26.
 */

import com.team.domain.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
}
