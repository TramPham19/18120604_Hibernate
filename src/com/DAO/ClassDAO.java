package com.DAO;

import com.hibernate.ClassEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ClassDAO {
    public static List<ClassEntity> getAllClass(){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<ClassEntity> acc = null;
        try {
            final String hql = "select st from ClassEntity st";
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
