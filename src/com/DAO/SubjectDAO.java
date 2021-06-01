package com.DAO;

import com.hibernate.SubjectEntity;
import com.hibernate.SubjectEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SubjectDAO {
    public static List<SubjectEntity> getAllSubject(){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SubjectEntity> subj = null;
        try {
            final String hql = "select st from SubjectEntity st";
            Query query = session.createQuery(hql);
            subj = query.list();

        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return subj;
    }
    public static List<SubjectEntity> getInfoSubjectById(String id){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SubjectEntity> subj = null;
        try {
            final String hql = "select st from SubjectEntity st where st.subjectId = :id ";
            Query query = session.createQuery(hql);
            query.setString("id", id);
            subj = query.list();
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  subj;
    }

    public static List<SubjectEntity> getInfoSubjectByName(String name){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SubjectEntity> subj = null;
        try {
            final String hql = "select st from SubjectEntity st where st.subjectName = :name ";
            Query query = session.createQuery(hql);
            query.setString("name", name);
            subj = query.list();

        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return subj;
    }

    public static List<SubjectEntity> getInfoSubjectByCredit(String credit){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SubjectEntity> subj = null;
        try {
            final String hql = "select st from SubjectEntity st where st.credits = :credit ";
            Query query = session.createQuery(hql);
            query.setString("credit", credit);
            subj = query.list();

        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return subj;
    }


    public static boolean addSubject(SubjectEntity Subject){
        Session session = hibernateUtils.getSessionFactory().openSession();
        if(Subject.getSubjectId() == null)
            return false;
        else if(SubjectDAO.getInfoSubjectById(Subject.getSubjectId()).size()>0){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(Subject);
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

    public static  boolean updateSubject(SubjectEntity Subject) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        if (SubjectDAO.getInfoSubjectById(Subject.getSubjectId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(Subject);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static  boolean deleteSubject(String id) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SubjectEntity> Subject = SubjectDAO.getInfoSubjectById(id);
        SubjectEntity subj = null;
        if (Subject.size()<= 0) {
            return false;
        }
        else{
            try {
                subj = Subject.get(Subject.size()-1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(subj);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }
}
