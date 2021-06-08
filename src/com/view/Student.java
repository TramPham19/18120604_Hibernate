package com.view;

import com.DAO.*;
import com.hibernate.*;
import sun.java2d.pipe.TextRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Student extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    public JTextField txtMSSVStudent;
    public JTextField txtPassStudent;
    public JTextField txtNameStudent;
    public JTextField txtEmailStudent;
    public JComboBox cmbGenderStudent;
    private JButton btnRegistration;
    private JTable tbCourse;
    private JButton btnShowTeacher;
    private JButton btnSearchTeacher;
    private JTable tbRegistation;

    public DefaultTableModel tableModel = new DefaultTableModel();
    String [] columnCourse = new String [] {"STT", "Mã môn học","Tên môn học", "Số tín chỉ","Giáo viên","Phòng học","Thứ","Ca học","Slot","Đăng kí"};

    public Student(){

        List<CourseEntity> Course = new ArrayList<>();
        Course = CourseDAO.getAllCourse();
        showListCourse(Course);


        List<JoinCourseEntity> JoinCourse = new ArrayList<>();
//        JoinCourse = JoinCourseDAO.getAllJoinCourse(mssvStudent);
//        showListJoinCourse(JoinCourse);
        
        add(panel1);
        setTitle("LOG IN");
        setSize(1000,500);

        btnRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public String showPass(String pass){
        String result = "";
        for(int i=0;i<pass.length();i++)
            result += "*";
        return result;
    }

    public void showListCourse(List<CourseEntity> list){
        int size= list.size();
        Object [][]course=new Object[size][10];
        for (int i=0;i<size; i++){
            SubjectEntity subjectEntity = SubjectDAO.getInfoSubjectByID(list.get(i).getIdSubject());
            course[i][0]= i+1;
            course[i][1]=subjectEntity.getSubjectId();
            course[i][2]=subjectEntity.getSubjectName();
            course[i][3]=subjectEntity.getCredits();
            course[i][4]=list.get(i).getTeacherName();
            course[i][5]=list.get(i).getRoomName();
            course[i][6]=list.get(i).getDayOfWeek();
            course[i][7]=list.get(i).getTimeOfDay();
            course[i][8]=list.get(i).getSlotMax();
        }

        tbCourse.setModel(new DefaultTableModel(course,columnCourse));
    }

    public void showListJoinCourse(List<JoinCourseEntity> list){
        int size= list.size();
        Object [][]course=new Object[size][10];
        for (int i=0;i<size; i++){
//            SubjectEntity subjectEntity = SubjectDAO.getInfoSubjectByID(list.get(i).getId());
//            CourseEntity courseEntity = CourseDAO.getInfoCourseByID(list.get(i).getId());
//            course[i][0]=i+1;
//            course[i][1]=subjectEntity.getSubjectId();
//            course[i][2]=subjectEntity.getSubjectName();
//            course[i][3]=subjectEntity.getCredits();
//            course[i][4]=courseEntity.getTeacherName();
//            course[i][5]=courseEntity.getRoomName();
//            course[i][6]=courseEntity.getDayOfWeek();
//            course[i][7]=courseEntity.getTimeOfDay();
//            course[i][8]=courseEntity.getSlotMax();
            course[i][0]=i+1;
            course[i][1]=list.get(i).getId_student();
            course[i][2]=list.get(i).getId_course();
        }

        tbRegistation.setModel(new DefaultTableModel(course,columnCourse));
    }
}
