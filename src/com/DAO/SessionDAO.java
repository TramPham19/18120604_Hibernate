package com.DAO;

import com.hibernate.SemesterEntity;
import com.hibernate.SessionEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public static List<SessionEntity> getInfoSessionByNameYear(String semesterName, String year){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SessionEntity> acc = null;
        try {
            final String hql = "select st from SessionEntity st where st.semesterName= :semesterName and st.year = :year  ";
            Query query = session.createQuery(hql);
            query.setString("semesterName", semesterName);
            query.setString("year", year);
            acc = query.list();
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  acc;
    }
    
    public static boolean addSession(SessionEntity sessionEntity){
        Session session = hibernateUtils.getSessionFactory().openSession();
        if(sessionEntity.getSemesterName() == null || sessionEntity.getYear() == null)
            return false;
//        else if(SemesterDAO.getInfoSemesterByNameYear(sessionEntity.getSemesterName(), sessionEntity.getYear()).size()>0){
//            return false;
//        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(sessionEntity);
            transaction.commit();
        }
        catch (HibernateException e){
            transaction.rollback();;
            System.err.println(e);
        }finally {
            session.close();
        }
        return true;
    }
}
