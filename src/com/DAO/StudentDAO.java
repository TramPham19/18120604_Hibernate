package com.DAO;

import com.hibernate.SemesterEntity;
import com.hibernate.StudentEntity;
import com.hibernate.TeacherEntity;
import com.utils.hibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAO {
    public static List<StudentEntity> getAllStudent(){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<StudentEntity> acc = null;
        try {
            final String hql = "select st from StudentEntity st";
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

    public static StudentEntity getInfoStudentByID(int id){
        Session session = hibernateUtils.getSessionFactory().openSession();
        StudentEntity acc = null;
        try {
            acc = (StudentEntity) session.get(StudentEntity.class,id);
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  acc;
    }
    
    public static List<StudentEntity> getInfoStudentByMSSV(String MSSV){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<StudentEntity> acc = null;
        try {
            final String hql = "select st from StudentEntity st where st.mssv = :name ";
            Query query = session.createQuery(hql);
            query.setString("name", MSSV);
            acc = query.list();
        }catch (HibernateException ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return  acc;
    }

    public static List<StudentEntity> getInfoStudentByFullname(String name){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<StudentEntity> acc = null;
        try {
            final String hql = "select st from StudentEntity st where st.fullname = :name ";
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

    public static List<StudentEntity> getInfoStudentByEmail(String email){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<StudentEntity> acc = null;
        try {
            final String hql = "select st from StudentEntity st where st.email = :email ";
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

    public static List<StudentEntity> getInfoStudentByClass(String className){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<StudentEntity> acc = null;
        try {
            final String hql = "select st from StudentEntity st where st.className = :className ";
            Query query = session.createQuery(hql);
            query.setString("className", className);
            acc = query.list();

        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return acc;
    }


    public static List<StudentEntity> getInfoStudentByGender(String gender){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<StudentEntity> acc = null;
        try {
            final String hql = "select st from StudentEntity st where st.gender = :gender ";
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

    public static List<StudentEntity> getInfoStudentByGenderAndClass(String gender, String className){
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<StudentEntity> acc = null;
        try {
            final String hql = "select st from StudentEntity st where st.gender = :gender and st.className = :className";
            Query query = session.createQuery(hql);
            query.setString("gender", gender);
            query.setString("className", className);
            acc = query.list();

        }
        catch (HibernateException e){
            System.err.println(e);
        }finally {
            session.close();
        }
        return acc;
    }

    public static boolean addStudent(StudentEntity student){
        Session session = hibernateUtils.getSessionFactory().openSession();
        if(student.getMssv() == null)
            return false;
        else if(StudentDAO.getInfoStudentByMSSV(student.getMssv()).size()>0){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(student);
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

    public static  boolean updateStudent(StudentEntity student) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        if (StudentDAO.getInfoStudentByMSSV(student.getMssv()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static  boolean deleteStudent(String mssv) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        List<StudentEntity> student = StudentDAO.getInfoStudentByMSSV(mssv);
        StudentEntity acc = null;
        if (student.size()<= 0) {
            return false;
        }
        else{
            try {
                acc = student.get(student.size()-1);
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
