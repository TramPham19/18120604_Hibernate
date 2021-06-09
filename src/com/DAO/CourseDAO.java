package com.DAO;

import com.hibernate.CourseEntity;
import com.hibernate.SemesterEntity;
import com.hibernate.SubjectEntity;
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

    public static CourseEntity getInfoCourseByID(int id){
        Session session = hibernateUtils.getSessionFactory().openSession();
        CourseEntity acc = null;
        try {
            acc = (CourseEntity) session.get(CourseEntity.class,id);
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  acc;
    }

    public static List<CourseEntity> getInfoCourseById(String courseId){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<CourseEntity> acc = null;
        List<SubjectEntity> subjectEntity = SubjectDAO.getInfoSubjectById(courseId);
        if(subjectEntity.size()<=0)
            return acc;
        else {
            try {
                final String hql = "select st from CourseEntity st where st.idSubject = :id ";
                Query query = session.createQuery(hql);
                query.setInteger("id", subjectEntity.get(0).getId());
                acc = query.list();
            } catch (HibernateException ex) {
                System.err.println(ex);
            } finally {
                session.close();
            }
            return acc;
        }
    }

//    public static List<CourseEntity> getInfoStudentOfCourseByIdCourse(String courseId){
//        Session session = hibernateUtils.getSessionFactory().openSession();
//        List<CourseEntity> acc = null;
//        List<SubjectEntity> subjectEntity = SubjectDAO.getInfoSubjectById(courseId);
//        try {
//            final String hql = "select st from CourseEntity st where st.idSubject = :id ";
//            Query query = session.createQuery(hql);
//            query.setInteger("id", subjectEntity.get(0).getId());
//            acc = query.list();
//        }catch (HibernateException ex){
//            System.err.println(ex);
//        }finally {
//            session.close();
//        }
//        return  acc;
//    }
    
    public static List<CourseEntity> getInfoCourseBySemester(int id){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<CourseEntity> acc = null;
        SemesterEntity semester = SemesterDAO.getInfoSemesterByID(id);
        try {
            final String hql = "select st from CourseEntity st where st.idSemester = :idSemester  ";
            Query query = session.createQuery(hql);
            query.setInteger("idSemester",semester.getId());
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
        List<SubjectEntity> subjectEntity = SubjectDAO.getInfoSubjectByName(courseName);
        try {
            final String hql = "select st from CourseEntity st where st.idSubject = :id ";
            Query query = session.createQuery(hql);
            query.setInteger("id", subjectEntity.get(0).getId());
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
