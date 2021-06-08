package com.DAO;

import com.hibernate.ClassEntity;
import com.hibernate.StudentEntity;
import com.hibernate.SubjectEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
    
    public static List<ClassEntity> getInfoClassByName(String name){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<ClassEntity> cla = null;
        try {
            final String hql = "select st from ClassEntity st where st.className = :name ";
            Query query = session.createQuery(hql);
            query.setString("name", name);
            cla = query.list();
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  cla;
    }
    
    public static boolean addClass(ClassEntity Class){
        Session session = hibernateUtils.getSessionFactory().openSession();
        if(Class.getClassName() == null)
            return false;
        else if(ClassDAO.getInfoClassByName(Class.getClassName()).size()>0){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(Class);
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

    public static  boolean updateClass(ClassEntity classEntity) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        if (ClassDAO.getInfoClassByName(classEntity.getClassName()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(classEntity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }
    
    public static  boolean deleteClass(String name) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<ClassEntity> Class = ClassDAO.getInfoClassByName(name);
        ClassEntity classEntity = null;
        if (Class.size()<= 0) {
            return false;
        }
        else{
            try {
                classEntity = Class.get(Class.size()-1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(classEntity);
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
