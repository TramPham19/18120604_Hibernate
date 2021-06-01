package com.view;

import com.DAO.ClassDAO;
import com.DAO.SubjectDAO;
import com.DAO.TeacherDAO;
import com.hibernate.ClassEntity;
import com.hibernate.SubjectEntity;
import com.hibernate.TeacherEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class manager extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JPanel panelTeacher;
    private JTextField txtPassTeacher;
    private JTextField txtNameTeacher;
    private JTextField txtEmailTeacher;
    private JTextField txtGenderTeacher;
    private JTextField txtCMNDTeacher;
    private JButton btnAddTeacher;
    private JButton btnDeleteTeacher;
    private JButton btnUpdateTeacher;
    private JButton btnResetPassTeacher;
    private JButton btnSearchTeacher;
    private JTable tbTeacher;
    private JPanel Teacherpanel;
    private JTable tbSubject;
    private JButton btnShowTeacher;
    private JComboBox cmbGender;
    private JButton btnAddSubject;
    private JButton btnDeleteSubject;
    private JButton btnUpdateSubject;
    private JButton btnShowSubject;
    private JButton btnSearchSubject;
    private JTextField txtCreditsSubject;
    private JTextField txtNameSubject;
    private JTextField txtIdSubject;
    private JSpinner spCreditSubject;
    private JTextField txtNameClass;
    private JButton btnAddClass;
    private JButton btnDeleteClass;
    private JButton btnSearchClass;
    private JButton btnShowClass;
    private JTable tbClass;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JSpinner spinner1;
    private JComboBox comboBox3;

    String [] columnTeacher = new String [] {"STT", "CMND", "Mật khẩu", "Họ tên", "Email","Giới tính"};
    String [] columnSubject = new String [] {"STT", "Mã môn học","Tên môn học","Số tín chỉ"};
    String [] columnClass = new String [] {"STT", "Tên lớp học","Tổng số sinh viên","Số sinh viên nam","Số sinh viên nữ"};
    public DefaultTableModel tableModel = new DefaultTableModel();



    public manager(){
        List<TeacherEntity> Teacher = new ArrayList<>();
        Teacher = TeacherDAO.getAllTeacher();
        showListTeacher(Teacher);

        List<SubjectEntity> Subject = new ArrayList<>();
        Subject = SubjectDAO.getAllSubject();
        showListSubject(Subject);

        List<ClassEntity> Class = new ArrayList<>();
        Class = ClassDAO.getAllClass();
        showListClass(Class);
        
        add(panel1);
        setTitle("LOG IN");
        setSize(1000,500);
        List<TeacherEntity> finalTeacher = Teacher;

        // action in table teacher
        btnAddTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                if(txtCMNDTeacher.getText().equals(""))
                {
                    strBuild.append("CMND/CCCD không thể trống");
                }
                else {
                    for (int i = 0; i < finalTeacher.size(); i++) {
                        if (finalTeacher.get(i).getCmnd().compareTo(txtCMNDTeacher.getText()) == 0) {
                            strBuild.delete(0,strBuild.length());
                            strBuild.append("CMND/CCCD trên đã tồn tại tài khoản.");
                            break;
                        }
                    }
                }
                if(txtNameTeacher.getText().equals(""))
                {
                    strBuild.delete(0,strBuild.length());
                    strBuild.append("Họ tên không thể trống");
                }
                if(txtEmailTeacher.getText().equals(""))
                {
                    strBuild.delete(0,strBuild.length());
                    strBuild.append("Email không thể trống");
                }
                if(strBuild.length()>0)
                {
                    JOptionPane.showMessageDialog(panel1,strBuild.toString(),"Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    TeacherEntity teacher = new TeacherEntity();
                    teacher.setCmnd(txtCMNDTeacher.getText());
                    teacher.setPassword(txtCMNDTeacher.getText());
                    teacher.setFullname(txtNameTeacher.getText());
                    teacher.setEmail(txtEmailTeacher.getText());
                    teacher.setGender((String) cmbGender.getItemAt(cmbGender.getSelectedIndex()));

                    boolean result = TeacherDAO.addTeacher(teacher);
                    if(result == true){
                        strBuild.append("Thêm tài khoản giáo vụ thành công");
                        finalTeacher.add(teacher);
                    }
                    else{
                        strBuild.append("Thêm tài khoản giáo vụ thất bại");
                    }
                    JOptionPane.showMessageDialog(panel1,strBuild.toString(),"Success",JOptionPane.DEFAULT_OPTION);
                    showListTeacher(finalTeacher);
                }
            }

        });
        btnDeleteTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check = 0;

                StringBuilder strBuild = new StringBuilder();
                if(txtCMNDTeacher.getText().equals(""))
                {
                    strBuild.append("CMND/CCCD không thể trống");
                }
                else {
                    for (int i = 0; i < finalTeacher.size(); i++) {
                        if (finalTeacher.get(i).getCmnd().compareTo(txtCMNDTeacher.getText()) == 0) {
                            check = 1;
                        }
                    }
                    if (check == 0) strBuild.append("CMND/CCCD không tồn tại");
                    else {
                        List<TeacherEntity> list = TeacherDAO.getInfoTeacherByCMND(txtCMNDTeacher.getText());
                        boolean result = TeacherDAO.deleteTeacher(txtCMNDTeacher.getText());
                        if (result == true) {
                            strBuild.append("Xóa thành công");

                        } else {
                            strBuild.append("Xóa thất bại");
                        }
                    }
                    List<TeacherEntity> result = TeacherDAO.getAllTeacher();
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Thông báo", JOptionPane.DEFAULT_OPTION);
                    showListTeacher(result);
                }
            }
        });
        btnUpdateTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();

                if (txtCMNDTeacher.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("CMND/CCCD không thể trống");
//                   txtCMNDTeacher.setBackground(Color.red);
                }
                else if (TeacherDAO.getInfoTeacherByCMND(txtCMNDTeacher.getText()) == null){
                    strBuild.append("Tài khoản không tồn tại");
                } else if (txtNameTeacher.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("Họ tên không thể trống");
//                   txtNameTeacher.setBackground(Color.red);
                } else if (txtEmailTeacher.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("Email không thể trống");
//                   txtEmailTeacher.setBackground(Color.red);
                }

                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    List<TeacherEntity> teacherList = TeacherDAO.getInfoTeacherByCMND(txtCMNDTeacher.getText());
                    TeacherEntity teacher = teacherList.get(0);
                    teacher.setCmnd(txtCMNDTeacher.getText());
                    teacher.setPassword(teacher.getPassword());
                    teacher.setFullname(txtNameTeacher.getText());
                    teacher.setEmail(txtEmailTeacher.getText());
                    teacher.setGender((String) cmbGender.getItemAt(cmbGender.getSelectedIndex()));
                    boolean result = TeacherDAO.updateTeacher(teacher);
                    if (result == true) {
                        strBuild.append("Chỉnh sửa tài khoản giáo vụ thành công");
                    } else {
                        strBuild.append("Chỉnh sửa tài khoản giáo vụ thất bại");
                    }
                }
                showListTeacher (TeacherDAO.getAllTeacher());
            }
        });
        btnResetPassTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<TeacherEntity> tempTeacher = new ArrayList<>();
                tempTeacher = TeacherDAO.getAllTeacher();
                for(TeacherEntity teacher:tempTeacher)
                {
//                    teacher = TeacherDAO.getInfoTeacher(Integer.parseInt(txtCMNDTeacher.getText()));
                    teacher.setCmnd(teacher.getCmnd());
//                    teacher.setPassword(txtPassTeacher.getText());
                    teacher.setFullname(teacher.getFullname());
                    teacher.setEmail(teacher.getEmail());
                    teacher.setGender(teacher.getGender());
                    teacher.setPassword(teacher.getCmnd());
                    boolean result = TeacherDAO.updateTeacher(teacher);
                }
                showListTeacher(TeacherDAO.getAllTeacher());
            }
        });
        btnSearchTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                TeacherEntity teacher = TeacherDAO.getInfoTeacherByCMND(Integer.parseInt(txtCMNDTeacher.getText()));
//                List<TeacherEntity> tempTeacher = new ArrayList<>();
//                tempTeacher.add(teacher);
//                showListTeacher(tempTeacher);
                List<TeacherEntity> teacher = TeacherDAO.getInfoTeacherByFullname(txtNameTeacher.getText());
                System.out.println(teacher.get(0).getFullname());
                showListTeacher(teacher);
            }
        });
        btnShowTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListTeacher(TeacherDAO.getAllTeacher());
            }
        });
// ==================End button in table teacher================================

//================Start butun in table Subject=====================
        btnAddSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SubjectEntity> finalSubject = SubjectDAO.getAllSubject();
                StringBuilder strBuild = new StringBuilder();
                if(txtIdSubject.getText().equals(""))
                {
                    strBuild.append("Mã môn học không thể trống");
                }
                else {
                    for (int i = 0; i < finalSubject.size(); i++) {
                        if (finalSubject.get(i).getSubjectId().compareTo(txtIdSubject.getText()) == 0) {
                            strBuild.append("Mã môn học đã tồn tại tài khoản.");
                            break;
                        }
                    }
                }
                if(txtNameSubject.getText().equals(""))
                {
                    strBuild.delete(0,strBuild.length());
                    strBuild.append("Tên môn học không thể trống");
                }
                if(strBuild.length()>0)
                {
                    JOptionPane.showMessageDialog(panel1,strBuild.toString(),"Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    SubjectEntity subject = new SubjectEntity();
                    subject.setSubjectId(txtIdSubject.getText());
                    subject.setSubjectName(txtNameSubject.getText());
                    subject.setCredits((Integer) spCreditSubject.getValue());

                    boolean result = SubjectDAO.addSubject(subject);
                    if(result == true){
                        strBuild.append("Thêm môn học thành công");
                    }
                    else{
                        strBuild.append("Thêm môn học thất bại");
                    }
                    JOptionPane.showMessageDialog(panel1,strBuild.toString(),"Success",JOptionPane.DEFAULT_OPTION);
                    showListSubject(SubjectDAO.getAllSubject());
                }
            }

        });
        btnDeleteSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SubjectEntity> finalSubject = SubjectDAO.getAllSubject();
                int check = 0;

                StringBuilder strBuild = new StringBuilder();
                if(txtIdSubject.getText().equals(""))
                {
                    strBuild.append("Mã môn học không thể trống");
                }
                else {
                    for (int i = 0; i < finalSubject.size(); i++) {
                        if (finalSubject.get(i).getSubjectId().compareTo(txtIdSubject.getText()) == 0) {
                            check = 1;
                        }
                    }
                    if (check == 0) strBuild.append("Mã môn học không tồn tại");
                    else {
                        List<SubjectEntity> list = SubjectDAO.getInfoSubjectById(txtIdSubject.getText());
                        boolean result = SubjectDAO.deleteSubject(txtIdSubject.getText());
                        if (result == true) {
                            strBuild.append("Xóa thành công");

                        } else {
                            strBuild.append("Xóa thất bại");
                        }
                    }
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Thông báo", JOptionPane.DEFAULT_OPTION);
                    showListSubject(SubjectDAO.getAllSubject());
                }
            }

        });
        btnUpdateSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();

                if (txtIdSubject.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("Mã môn học không thể trống");
//                   txtCMNDTeacher.setBackground(Color.red);
                }
                else if (SubjectDAO.getInfoSubjectById(txtIdSubject.getText()) == null){
                    strBuild.append("Môn học không tồn tại");
                } else if (txtNameSubject.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("Tên môn học không thể trống");
//                   txtNameTeacher.setBackground(Color.red);
                } 

                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    List<SubjectEntity> subjectList = SubjectDAO.getInfoSubjectById(txtIdSubject.getText());
                    SubjectEntity subject = subjectList.get(0);
                    subject.setSubjectId(txtIdSubject.getText());
                    subject.setSubjectName(txtNameSubject.getText());
                    subject.setCredits((Integer) spCreditSubject.getValue());
                    boolean result = SubjectDAO.updateSubject(subject);
                    if (result == true) {
                        strBuild.append("Chỉnh sửa môn học thành công");
                    } else {
                        strBuild.append("Chỉnh sửa môn học thất bại");
                    }
                }
                showListSubject(SubjectDAO.getAllSubject());
            }

        });
        btnShowSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListSubject(SubjectDAO.getAllSubject());
            }
        });
        btnSearchSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SubjectEntity> subject = SubjectDAO.getInfoSubjectById(txtIdSubject.getText());
                showListSubject(subject);
            }
        });

        //End button in table Subject

        //Start button in table Class
        btnAddClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ClassEntity> finalClass = ClassDAO.getAllClass();
                StringBuilder strBuild = new StringBuilder();
                if(txtNameClass.getText().equals(""))
                {
                    strBuild.append("Tên lớp không thể trống");
                }
                else {
                    for (int i = 0; i < finalClass.size(); i++) {
                        if (finalClass.get(i).getClassName().compareToIgnoreCase(txtNameClass.getText()) == 0) {
                            strBuild.append("Lớp học đã tồn tại tài khoản.");
                            break;
                        }
                    }
                }
                if(strBuild.length()>0)
                {
                    JOptionPane.showMessageDialog(panel1,strBuild.toString(),"Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    ClassEntity classEntity = new ClassEntity();
                    classEntity.setClassName(txtNameClass.getText());

                    boolean result =ClassDAO.addClass(classEntity);
                    if(result == true){
                        strBuild.append("Thêm lớp học thành công");
                    }
                    else{
                        strBuild.append("Thêm lớp học thất bại");
                    }
                    JOptionPane.showMessageDialog(panel1,strBuild.toString(),"Success",JOptionPane.DEFAULT_OPTION);
                    showListClass(ClassDAO.getAllClass());
                }
            }

        });
        btnDeleteClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ClassEntity> finalClass = ClassDAO.getAllClass();
                int check = 0;

                StringBuilder strBuild = new StringBuilder();
                if(txtNameClass.getText().equals(""))
                {
                    strBuild.append("Tên lớp học không thể trống");
                }
                else {
                    for (int i = 0; i < finalClass.size(); i++) {
                        if (finalClass.get(i).getClassName().compareToIgnoreCase(txtNameClass.getText()) == 0) {
                            check = 1;
                        }
                    }
                    if (check == 0) strBuild.append("Tên lớp học không tồn tại");
                    else {
                        List<ClassEntity> list = ClassDAO.getInfoClassByName(txtNameClass.getText());
                        boolean result = ClassDAO.deleteClass(txtNameClass.getText());
                        if (result == true) {
                            strBuild.append("Xóa thành công");

                        } else {
                            strBuild.append("Xóa thất bại");
                        }
                    }
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Thông báo", JOptionPane.DEFAULT_OPTION);
                    showListClass(ClassDAO.getAllClass());
                }
            }

        });
        btnSearchClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ClassEntity> classEntities = ClassDAO.getInfoClassByName(txtNameClass.getText());
                showListClass(classEntities);
            }
        });

        btnShowClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListClass(ClassDAO.getAllClass());
            }
        });
        // End button in table Class

    }


    public void showListTeacher(List<TeacherEntity> list){
        int size= list.size();
        Object [][]teachers=new Object[size][6];
        for (int i=0;i<size; i++){
            teachers[i][0]= i+1;
            teachers[i][1]=list.get(i).getCmnd();
            teachers[i][2]= showPass(list.get(i).getPassword());
            teachers[i][3]=list.get(i).getFullname();
            teachers[i][4]=list.get(i).getEmail();
            teachers[i][5]=list.get(i).getGender();
        }
        tbTeacher.setModel(new DefaultTableModel(teachers,columnTeacher));
    }

    public String showPass(String pass){
        String result = "";
        for(int i=0;i<pass.length();i++)
            result += "*";
        return result;
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

        tbSubject.setModel(new DefaultTableModel(Subjects,columnSubject));
    }

    public void showListClass(List<ClassEntity> list){
        int size= list.size();
        Object [][] Class=new Object[size][6];
        for (int i=0;i<size; i++){
            Class[i][0]=list.get(i).getId();
            Class[i][1]=list.get(i).getClassName();
            Class[i][2]=list.get(i).getCountStudent();
            Class[i][3]=list.get(i).getCountMen();
            Class[i][4]=list.get(i).getCountWomen();
        }
        tbClass.setModel(new DefaultTableModel(Class,columnClass));
    }
}
