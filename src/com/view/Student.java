package com.view;

import com.DAO.CourseDAO;
import com.DAO.SessionDAO;
import com.DAO.StudentDAO;
import com.hibernate.CourseEntity;
import com.hibernate.SessionEntity;
import com.hibernate.StudentEntity;
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
    private JButton btnSearchTeacher;
    private JButton btnShowTeacher;

    public DefaultTableModel tableModel = new DefaultTableModel();
    String [] columnCourse = new String [] {"STT", "Mã môn học","Tên môn học", "Số tín chỉ","Giáo viên","Phòng học","Thứ","Ca học","Slot","Đăng kí"};

    public Student(){

        List<CourseEntity> Course = new ArrayList<>();
        Course = CourseDAO.getAllCourse();
        showListCourse(Course);
        
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
            course[i][0]=list.get(i).getId();
            course[i][1]=list.get(i).getCourseId();
            course[i][2]=list.get(i).getCoursetName();
            course[i][3]=list.get(i).getCredits();
            course[i][4]=list.get(i).getTeacherName();
            course[i][5]=list.get(i).getRoomName();
            course[i][6]=list.get(i).getDayOfWeek();
            course[i][7]=list.get(i).getTimeOfDay();
            course[i][8]=list.get(i).getSlotMax();
        }

        tbCourse.setModel(new DefaultTableModel(course,columnCourse));
    }
}
