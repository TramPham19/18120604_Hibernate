package com.DAO;

import com.hibernate.TeacherEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TeacherDAO {
    public static List<TeacherEntity> getAllTeacher(){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<TeacherEntity> acc = null;
        try {
            final String hql = "select st from TeacherEntity st";
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
