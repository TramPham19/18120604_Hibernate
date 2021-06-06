package com.DAO;

import com.hibernate.CourseEntity;
import com.hibernate.SemesterEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public static List<CourseEntity> getInfoCourseBySemester(String semesterName, String year){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<CourseEntity> acc = null;
        try {
            final String hql = "select st from CourseEntity st where st.semesterName = :semesterName and st.year = :year  ";
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

    public static List<CourseEntity> getInfoCourseByName(String courseName){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<CourseEntity> acc = null;
        try {
            final String hql = "select st from CourseEntity st where st.coursetName = :courseName ";
            Query query = session.createQuery(hql);
            query.setString("courseName", courseName);
            acc = query.list();
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  acc;
    }
//
//
//    public static List<CourseEntity> getInfoCourseByYear(String year){
//        Session session = hibernateUtils.getSessionFactory().openSession();
//        List<CourseEntity> acc = null;
//        try {
//            final String hql = "select st from CourseEntity st where st.year = :year ";
//            Query query = session.createQuery(hql);
//            query.setString("year", year);
//            acc = query.list();
//
//        }
//        catch (HibernateException e){
//            System.err.println(e);
//        }finally {
//            session.close();
//        }
//        return acc;
//    }


    public static boolean addCourse(CourseEntity course){
        Session session = hibernateUtils.getSessionFactory().openSession();
//        if(course.getCourseId() == null || course.getYear() == null)
//            return false;
//        else if(CourseDAO.getInfoCourseByNameYear(course.getCourseName(), course.getYear()).size()>0){
//            return false;
//        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(course);
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

//    public static  boolean updateCourse(CourseEntity course) {
//        Session session = hibernateUtils.getSessionFactory().openSession();
//        if (CourseDAO.getInfoCourseByNameYear(course.getCourseName(), course.getYear()).size()<=0) {
//            return false;
//        }
//        Transaction transaction = null;
//        try {
//            transaction = session.beginTransaction();
//            session.update(course);
//            transaction.commit();
//        } catch (HibernateException e) {
//            transaction.rollback();
//            System.err.println(e);
//        } finally {
//            session.close();
//        }
//        return true;
//    }

    public static  boolean deleteCourse(String name) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<CourseEntity> course = CourseDAO.getInfoCourseByName(name);
        CourseEntity acc = null;
        if (course.size()<= 0) {
            return false;
        }
        else{
            try {
                acc = course.get(course.size()-1);
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
