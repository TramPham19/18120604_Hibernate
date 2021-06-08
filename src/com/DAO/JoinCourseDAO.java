package com.DAO;

import com.hibernate.*;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class JoinCourseDAO {
    public static List<JoinCourseEntity> getAllJoinCourse(String mssv){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<StudentEntity> studentEntity = StudentDAO.getInfoStudentByMSSV(mssv);
        List<JoinCourseEntity> acc = null;
        try {
            final String hql = "select st from JoinCourseEntity st where st.id_student= :idStudent  ";
            Query query = session.createQuery(hql);
            query.setInteger("idStudent",studentEntity.get(0).getId());
            acc = query.list();
        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return acc;
    }

//    public static JoinCourseEntity getInfoJoinCourseByID(int id){
//        Session session = hibernateUtils.getSessionFactory().openSession();
//        JoinCourseEntity acc = null;
//        try {
//            acc = (JoinCourseEntity) session.get(JoinCourseEntity.class,id);
//        }catch (HibernateException ex){
//            System.err.println(ex);
//        }finally {
//            session.close();
//        }
//        return  acc;
//    }

//    public static List<JoinCourseEntity> getInfoJoinCourseBySemester(int id){
//        Session session = hibernateUtils.getSessionFactory().openSession();
//        List<JoinCourseEntity> acc = null;
//        SemesterEntity semester = SemesterDAO.getInfoSemesterByID(id);
//        try {
//            final String hql = "select st from JoinCourseEntity st where st.idSemester = :idSemester  ";
//            Query query = session.createQuery(hql);
//            query.setInteger("idSemester",semester.getId());
//            acc = query.list();
//        }catch (HibernateException ex){
//            System.err.println(ex);
//        }finally {
//            session.close();
//        }
//        return  acc;
//    }

//    public static List<JoinCourseEntity> getInfoJoinCourseByName(String courseName){
//        Session session = hibernateUtils.getSessionFactory().openSession();
//        List<JoinCourseEntity> acc = null;
//        List<SubjectEntity> subjectEntity = SubjectDAO.getInfoSubjectByName(courseName);
//        try {
//            final String hql = "select st from JoinCourseEntity st where st.idSubject = :id ";
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
//
//
//    public static List<JoinCourseEntity> getInfoJoinCourseByYear(String year){
//        Session session = hibernateUtils.getSessionFactory().openSession();
//        List<JoinCourseEntity> acc = null;
//        try {
//            final String hql = "select st from JoinCourseEntity st where st.year = :year ";
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


//    public static boolean addJoinCourse(JoinCourseEntity course) {
//        Session session = hibernateUtils.getSessionFactory().openSession();
//        if(course.getJoinCourseId() == null || course.getYear() == null)
//            return false;
//        else if(JoinCourseDAO.getInfoJoinCourseByNameYear(course.getJoinCourseName(), course.getYear()).size()>0){
//            return false;
//        }
//        Transaction transaction = null;
//        try {
//            transaction = session.beginTransaction();
//            session.save(course);
//            transaction.commit();
//        }
//        catch (HibernateException e){
//            transaction.rollback();;
//            System.err.println(e);
//        }finally {
//            session.close();
//        }
//        return true;
//    }

//    public static  boolean updateJoinCourse(JoinCourseEntity course) {
//        Session session = hibernateUtils.getSessionFactory().openSession();
//        if (JoinCourseDAO.getInfoJoinCourseByNameYear(course.getJoinCourseName(), course.getYear()).size()<=0) {
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

//    public static  boolean deleteJoinCourse(String name) {
//        Session session = hibernateUtils.getSessionFactory().openSession();
//        List<JoinCourseEntity> course = JoinJoinCourseDAO.getInfoJoinCourseByName(name);
//        JoinCourseEntity acc = null;
//        if (course.size()<= 0) {
//            return false;
//        }
//        else{
//            try {
//                acc = course.get(course.size()-1);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        Transaction transaction = null;
//        try {
//            transaction = session.beginTransaction();
//            session.delete(acc);
//            transaction.commit();
//        } catch (HibernateException e) {
//            transaction.rollback();
//            System.err.println(e);
//        } finally {
//            session.close();
//        }
//        return true;
//    }

}
