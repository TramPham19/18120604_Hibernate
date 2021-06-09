package com.view;

import com.DAO.*;
import com.hibernate.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Student extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    public JTextField txtMSSV;
    public JTextField txtPass;
    public JTextField txtName;
    public JTextField txtEmail;
    public JComboBox cmbGenderStudent;
    private JButton btnRegistration;
    private JTable tbCourse;
    private JButton btnChangeProfileStudent;
    private JButton btnChangePassStudent;
    private JTable tbRegistation;
    private JButton btnLogOutStudent;
    private JTextField txtNewProfileStudent;
    private JButton button1;
    private JPanel ListUserRegistation;
    public String passProfileStudent;
    public String mssv;

    public DefaultTableModel tableModel = new DefaultTableModel();
    String [] columnCourse = new String [] {"STT", "Mã môn học","Tên môn học", "Số tín chỉ","Giáo viên","Phòng học","Thứ","Ca học","Slot","Đăng kí"};

    public Student(){

        txtMSSV.setEnabled(false);
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

        btnLogOutStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn signIn = new SignIn();
                signIn.setVisible(true);
                setVisible(false);
            }
        });
        
        btnChangePassStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                List<StudentEntity> studentEntity = StudentDAO.getInfoStudentByMSSV(txtMSSV.getText());
                if(txtPass.getText().compareTo(passProfileStudent) == 0) {
                    studentEntity.get(0).setPassword(txtNewProfileStudent.getText());
                    boolean result = StudentDAO.updateStudent(studentEntity.get(0));
                    if (result == true) {
                        strBuild.append("Thay đổi mật khẩu thành công");
                        passProfileStudent = txtNewProfileStudent.getText();
                    } else {
                        strBuild.append("Thay đổi mật khẩu thất bại");
                    }
                }else
                    strBuild.append("Mật khẩu hiện tại không đúng");
                JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Thông báo", JOptionPane.DEFAULT_OPTION);
            }
        });
        btnChangeProfileStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                if (txtName.getText().equals("")) {
                    strBuild.append("Họ tên không thể trống");
//                   txtNameTeacher.setBackground(Color.red);
                } else if (txtEmail.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("Email không thể trống");
//                   txtEmailTeacher.setBackground(Color.red);
                }

                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    List<StudentEntity> studentList = StudentDAO.getInfoStudentByMSSV(txtMSSV.getText());
                    StudentEntity student = studentList.get(0);
                    student.setMssv(txtMSSV.getText());
                    student.setFullname(txtName.getText());
                    student.setEmail(txtEmail.getText());
                    student.setGender((String) cmbGenderStudent.getItemAt(cmbGenderStudent.getSelectedIndex()));
                    boolean result = StudentDAO.updateStudent(student);
                    if (result == true) {
                        strBuild.append("Chỉnh sửa thông tin thành công");
                    } else {
                        strBuild.append("Chỉnh sửa thông tin thất bại");
                    }
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Thông báo", JOptionPane.DEFAULT_OPTION);

                }
            }
        });
        ListUserRegistation.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                super.componentMoved(e);
                List<JoinCourseEntity> JoinCourse = new ArrayList<>();
                JoinCourse = JoinCourseDAO.getAllJoinCourse(mssv);
                showListJoinCourse(JoinCourse);
            }
        });
        tbCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StringBuilder strBuild = new StringBuilder();
                List<JoinCourseEntity> joinList = JoinCourseDAO.getAllJoinCourse(txtMSSV.getText());
                List<StudentEntity> studentList = StudentDAO.getInfoStudentByMSSV(txtMSSV.getText());
                List<CourseEntity> course = CourseDAO.getAllCourse();
                JoinCourseEntity join = new JoinCourseEntity();
                int temp=tbCourse.getSelectedRow();
                join.setId_course(course.get(temp).getId());
                join.setId_student(studentList.get(0).getId());
                boolean result = JoinCourseDAO.addJoinCourse(join);
                if (result == true) {
                    strBuild.append("Đăng kí khóa học thành công");
                } else {
                    strBuild.append("Đăng kí khóa học thất bại");
                }
                JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Thông báo", JOptionPane.DEFAULT_OPTION);
                showListJoinCourse(JoinCourseDAO.getAllJoinCourse(txtMSSV.getText()));
            }
        });
        tbRegistation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StringBuilder strBuild = new StringBuilder();
                List<JoinCourseEntity> joinList = JoinCourseDAO.getAllJoinCourse(txtMSSV.getText());
                List<StudentEntity> studentList = StudentDAO.getInfoStudentByMSSV(txtMSSV.getText());
//                List<CourseEntity> course = CourseDAO.getAllCourse();
                JoinCourseEntity join = new JoinCourseEntity();
                int temp = tbRegistation.getSelectedRow();
                if (temp > -1) {
                    join.setId_course(joinList.get(temp).getId_course());
                    join.setId_student(studentList.get(0).getId());
                    List<JoinCourseEntity> deleteJoin = JoinCourseDAO.getInfoJoinCourseByStudentandCourseID(join.getId_student(),join.getId_course());
                    System.out.println(join.getId_student());
                    System.out.println(join.getId_course());
                    if(deleteJoin!=null) {
                        boolean result = JoinCourseDAO.deleteJoinCourse(deleteJoin.get(0).getId());
                        if (result == true) {
                            strBuild.append("Xóa khóa học đã đăng kí thành công");
                        } else {
                            strBuild.append("Xóa khóa học đã đăng kí thất bại");
                        }
                        JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Thông báo", JOptionPane.DEFAULT_OPTION);
                        showListJoinCourse(JoinCourseDAO.getAllJoinCourse(txtMSSV.getText()));
                    }
                }
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
//            course[i][9]=list.get(i).getSlotMax();
            }


        tbCourse.setModel(new DefaultTableModel(course,columnCourse) {Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class,  java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class};
            public  Class getColumnClass(int columnIndex){return  types[columnIndex];}
        }
        );
    }

    public void showListJoinCourse(List<JoinCourseEntity> list){
        int size= list.size();
        Object [][]course=new Object[size][10];
        for (int i=0;i<size; i++){
            CourseEntity courseEntity = CourseDAO.getInfoCourseByID(list.get(i).getId_course());
            SubjectEntity subjectEntity = SubjectDAO.getInfoSubjectByID(courseEntity.getIdSubject());
            course[i][0]=i+1;
            course[i][1]=subjectEntity.getSubjectId();
            course[i][2]=subjectEntity.getSubjectName();
            course[i][3]=subjectEntity.getCredits();
            course[i][4]=courseEntity.getTeacherName();
            course[i][5]=courseEntity.getRoomName();
            course[i][6]=courseEntity.getDayOfWeek();
            course[i][7]=courseEntity.getTimeOfDay();
            course[i][8]=courseEntity.getSlotMax();
        }

        tbRegistation.setModel(new DefaultTableModel(course,columnCourse) {Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class,  java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class};
            public  Class getColumnClass(int columnIndex){return  types[columnIndex];}
        });
    }
}
