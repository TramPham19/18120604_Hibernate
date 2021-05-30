package com.DAO;

import com.hibernate.AccountEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AccountDAO {
    public static List<AccountEntity> getAllAccount(){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<AccountEntity> acc = null;
        try {
            final String hql = "select st from AccountEntity st";
            Query query = session.createQuery(hql);
            acc = query.list();

        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return acc;
    }
}
