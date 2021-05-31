package com.DAO;

import com.hibernate.SemesterEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SemesterDAO {
    public static List<SemesterEntity> getAllSemester(){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SemesterEntity> acc = null;
        try {
            final String hql = "select st from SemesterEntity st";
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
