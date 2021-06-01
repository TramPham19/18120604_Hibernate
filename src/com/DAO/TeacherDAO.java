package com.DAO;

import com.hibernate.TeacherEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public static List<TeacherEntity> getInfoTeacherByCMND(String CMND){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<TeacherEntity> acc = null;
        try {
            final String hql = "select st from TeacherEntity st where st.cmnd = :name ";
            Query query = session.createQuery(hql);
            query.setString("name", CMND);
            acc = query.list();
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  acc;
    }

    public static List<TeacherEntity> getInfoTeacherByFullname(String name){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<TeacherEntity> acc = null;
        try {
            final String hql = "select st from TeacherEntity st where st.fullname = :name ";
            Query query = session.createQuery(hql);
            query.setString("name", name);
            acc = query.list();

        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return acc;
    }

    public static List<TeacherEntity> getInfoTeacherByEmail(String email){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<TeacherEntity> acc = null;
        try {
            final String hql = "select st from TeacherEntity st where st.email = :email ";
            Query query = session.createQuery(hql);
            query.setString("email", email);
            acc = query.list();

        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return acc;
    }

    public static List<TeacherEntity> getInfoTeacherByGender(String gender){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<TeacherEntity> acc = null;
        try {
            final String hql = "select st from TeacherEntity st where st.gender = :gender ";
            Query query = session.createQuery(hql);
            query.setString("gender", gender);
            acc = query.list();

        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return acc;
    }

    public static boolean addTeacher(TeacherEntity teacher){
        Session session = hibernateUtils.getSessionFactory().openSession();
        if(teacher.getCmnd() == null)
            return false;
        else if(TeacherDAO.getInfoTeacherByCMND(teacher.getCmnd()).size()>0){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(teacher);
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

    public static  boolean updateTeacher(TeacherEntity teacher) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        if (TeacherDAO.getInfoTeacherByCMND(teacher.getCmnd()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(teacher);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static  boolean deleteTeacher(String cmnd) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<TeacherEntity> teacher = TeacherDAO.getInfoTeacherByCMND(cmnd);
        TeacherEntity acc = null;
        if (teacher.size()< 0) {
            return false;
        }
        else{
            try {
                acc = teacher.get(teacher.size()-1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(acc);
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
