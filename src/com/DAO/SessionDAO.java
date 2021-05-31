package com.DAO;

import com.hibernate.SessionEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SessionDAO {
    public static List<SessionEntity> getAllSession(){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SessionEntity> acc = null;
        try {
            final String hql = "select st from SessionEntity st";
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
