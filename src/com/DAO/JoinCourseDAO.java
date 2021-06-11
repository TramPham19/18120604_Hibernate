package com.DAO;

import com.hibernate.*;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class JoinCourseDAO extends CourseDAO {
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

    public static List<JoinCourseEntity> getAllStudentJoinCourseId(int id){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<JoinCourseEntity> acc = null;
        try {
            final String hql = "select st from JoinCourseEntity st where st.id_course= :id  ";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            acc = query.list();
        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return acc;
    }

    public static List<JoinCourseEntity> getAllStudentJoinCourseIdAndStudentID(int id,int idStudent){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<JoinCourseEntity> acc = null;
        try {
            final String hql = "select st from JoinCourseEntity st where st.id_course= :id and st.id_student =:idStudent ";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            query.setInteger("idStudent",idStudent);
            acc = query.list();
        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return acc;
    }

    public static List<JoinCourseEntity> getAllStudentJoinCourse(String course){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<CourseEntity> courseEntity = CourseDAO.getInfoCourseById(course);
        List<JoinCourseEntity> acc = null;
        try {
            final String hql = "select st from JoinCourseEntity st where st.id_course= :id  ";
            Query query = session.createQuery(hql);
            query.setInteger("id",courseEntity.get(0).getId());
            acc = query.list();
        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return acc;
    }

    public static JoinCourseEntity getInfoJoinCourseByID(int id){
        Session session = hibernateUtils.getSessionFactory().openSession();
        JoinCourseEntity acc = null;
        try {
            acc = (JoinCourseEntity) session.get(JoinCourseEntity.class,id);
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  acc;
    }

    public static List<JoinCourseEntity> getInfoJoinCourseByStudentandCourseID(int idStudent,int idCourse){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<JoinCourseEntity> acc = null;
//        JoinCourseEntity semester = JoinCourseDAO.getInfoJoinCourseByID(id);
        try {
            final String hql = "select st from JoinCourseEntity st where st.id_student = :idStudent and st.id_course=:idCourse ";
            Query query = session.createQuery(hql);
            query.setInteger("idStudent",idStudent);
            query.setInteger("idCourse",idCourse);
            acc = query.list();
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  acc;
    }

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


    public static boolean addJoinCourse(JoinCourseEntity course) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<StudentEntity> studentEntity = (List<StudentEntity>) StudentDAO.getInfoStudentByID(course.getId());
//        if(course.getId_course() == 0 || course.getId_course() == 0)
//            return false;
//        else if(JoinCourseDAO.getAllJoinCourse(studentEntity.get(0).getMssv()).size()>0){
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

    public static  boolean deleteJoinCourse(int id) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        JoinCourseEntity course = JoinCourseDAO.getInfoJoinCourseByID(id);
        if (course == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(course);
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
