package com.DAO;

import com.hibernate.CourseEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CourseDAO {
    public static List<CourseEntity> getAllCourse(){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<CourseEntity> acc = null;
        try {
            final String hql = "select st from CourseEntity st";
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
