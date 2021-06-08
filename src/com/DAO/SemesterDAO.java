package com.DAO;

import com.hibernate.SemesterEntity;
import com.hibernate.TeacherEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public static SemesterEntity getInfoSemesterByID(int id){
        Session session = hibernateUtils.getSessionFactory().openSession();
        SemesterEntity acc = null;
        try {
            acc = (SemesterEntity) session.get(SemesterEntity.class,id);
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  acc;
    }


    public static List<SemesterEntity> getInfoSemesterByNameYear(String semesterName, String year){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SemesterEntity> acc = null;
        try {
            final String hql = "select st from SemesterEntity st where st.semesterName = :semesterName and st.year = :year  ";
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

    public static List<SemesterEntity> getInfoSemesterByName(String semesterName){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SemesterEntity> acc = null;
        try {
            final String hql = "select st from SemesterEntity st where st.semesterName = :semesterName ";
            Query query = session.createQuery(hql);
            query.setString("semesterName", semesterName);
            acc = query.list();
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  acc;
    }
    

    public static List<SemesterEntity> getInfoSemesterByYear(String year){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SemesterEntity> acc = null;
        try {
            final String hql = "select st from SemesterEntity st where st.year = :year ";
            Query query = session.createQuery(hql);
            query.setString("year", year);
            acc = query.list();

        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return acc;
    }
    

    public static boolean addSemester(SemesterEntity semester){
        Session session = hibernateUtils.getSessionFactory().openSession();
        if(semester.getSemesterName() == null || semester.getYear() == null)
            return false;
        else if(SemesterDAO.getInfoSemesterByNameYear(semester.getSemesterName(), semester.getYear()).size()>0){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(semester);
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

    public static  boolean updateSemester(SemesterEntity semester) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        if (SemesterDAO.getInfoSemesterByNameYear(semester.getSemesterName(), semester.getYear()).size()<=0) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(semester);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static  boolean deleteSemester(String name,String year) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<SemesterEntity> semester = SemesterDAO.getInfoSemesterByNameYear(name,year);
        SemesterEntity acc = null;
        if (semester.size()<= 0) {
            return false;
        }
        else{
            try {
                acc = semester.get(semester.size()-1);
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
