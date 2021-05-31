package com.view;

import com.DAO.SubjectDAO;
import com.DAO.TeacherDAO;
import com.hibernate.SubjectEntity;
import com.hibernate.TeacherEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class manager extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JPanel panelTeacher;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtGender;
    private JTextField txtCMND;
    private JButton thêmButton;
    private JButton xóaButton;
    private JButton cậpNhậtButton;
    private JButton resetMậtKhẩuButton;
    private JButton tìmKiếmButton;
    private JTable tbTeaecher;
    private JPanel Teacherpanel;
    private JTable tbSubject;

    String [] columnNames = new String [] {"ID", "MSSV", "EMAIL", "FULLNAME", "Giới tính"};
    public DefaultTableModel tableModel = new DefaultTableModel();

    public manager(){
        List<TeacherEntity> Teacher = new ArrayList<>();
        Teacher = TeacherDAO.getAllTeacher();
        showListTeacher(Teacher);
        List<SubjectEntity> Subject = new ArrayList<>();
        Subject = SubjectDAO.getAllSubject();
        showListSubject(Subject);
        add(Teacherpanel);
        setTitle("LOG IN");
        setSize(1000,500);
    }


    public void showListTeacher(List<TeacherEntity> list){
        int size= list.size();
        Object [][]teachers=new Object[size][6];
        for (int i=0;i<size; i++){
            teachers[i][0]=list.get(i).getId();
            teachers[i][1]=list.get(i).getCmnd();
            teachers[i][2]=list.get(i).getEmail();
            teachers[i][3]=list.get(i).getFullname();
            teachers[i][4]=list.get(i).getGender();
        }
        tbTeaecher.setModel(new DefaultTableModel(teachers,columnNames));

    }

    public void showListSubject(List<SubjectEntity> list){
        int size= list.size();
        Object [][]Subjects=new Object[size][6];
        for (int i=0;i<size; i++){
            Subjects[i][0]=list.get(i).getId();
            Subjects[i][1]=list.get(i).getSubjectId();
            Subjects[i][2]=list.get(i).getSubjectName();
            Subjects[i][3]=list.get(i).getCredits();
        }

        tbSubject.setModel(new DefaultTableModel(Subjects,columnNames));
    }
}
