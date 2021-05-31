package com.DAO;

import com.hibernate.SubjectEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SubjectDAO {
    public static List<SubjectEntity> getAllSubject(){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SubjectEntity> acc = null;
        try {
            final String hql = "select st from SubjectEntity st";
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
