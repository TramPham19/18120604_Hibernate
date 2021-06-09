package com.view;

import com.DAO.StudentDAO;
import com.DAO.TeacherDAO;
import com.hibernate.StudentEntity;
import com.hibernate.TeacherEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SignIn extends JFrame{

    public JPanel panel1;
    private JTextField txtUser;
    private JButton ĐĂNGNHẬPButton;
    private JPasswordField txtPassword;
    private JComboBox cmbTypeUser;


    public SignIn()
    {
        add(panel1);
        setTitle("LOG IN");
        setSize(500,300);
        ĐĂNGNHẬPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                String user = txtUser.getText();
                boolean check = false;
                char[] pass = txtPassword.getPassword();
                List<StudentEntity> studentEntities = StudentDAO.getAllStudent();
                List<TeacherEntity> teacherEntities = TeacherDAO.getAllTeacher();

                if(cmbTypeUser.getItemAt(cmbTypeUser.getSelectedIndex()).toString().compareTo("Sinh viên") == 0) {
                    for (StudentEntity item : studentEntities) {
                        if (user.compareTo(item.getMssv()) == 0 && compareCharString(pass, item.getPassword()) == 1) {
                            Student student = new Student();
                            check = true;
                            student.txtMSSV.setText(user);
                            List<StudentEntity> studentEntitiesClassStudent = StudentDAO.getInfoStudentByMSSV(student.txtMSSV.getText());
                            if(studentEntitiesClassStudent.size()>0) {
                                student.txtMSSV.setText(studentEntitiesClassStudent.get(0).getMssv());
                                student.mssv = studentEntitiesClassStudent.get(0).getMssv();
                                student.txtEmail.setText(studentEntitiesClassStudent.get(0).getEmail());
                                student.txtName.setText(studentEntitiesClassStudent.get(0).getFullname());
//                                student.txtPass.setText(studentEntitiesClassStudent.get(0).getPassword());
                                student.passProfileStudent = studentEntitiesClassStudent.get(0).getPassword();
                                student.cmbGenderStudent.setSelectedItem(studentEntitiesClassStudent.get(0).getGender());
                                strBuild.append("Đăng nhập thành công!");
                                student.setVisible(true);
                                setVisible(false);
                                break;
                            }
                        }
                    }
                } else{
                    for (TeacherEntity item : teacherEntities) {
                        if (user.compareTo(item.getCmnd()) == 0 && compareCharString(pass, item.getPassword()) == 1) {
                            manager teacher = new manager();
                            teacher.txtCMNDTeacher.setText(user);
                            check = true;
                            List<TeacherEntity> teacherEntitiesProfile = TeacherDAO.getInfoTeacherByCMND(teacher.txtCMNDTeacher.getText());
                            if(teacherEntitiesProfile.size()>0) {
                                teacher.txtProfileCMND.setText(teacherEntitiesProfile.get(0).getCmnd());
                                teacher.txtProfileEmail.setText(teacherEntitiesProfile.get(0).getEmail());
                                teacher.txtProfileName.setText(teacherEntitiesProfile.get(0).getFullname());
//                                teacher.txtProfilePass.setText(teacherEntitiesProfile.get(0).getPassword());
                                teacher.passProfile = teacherEntitiesProfile.get(0).getPassword();
                                teacher.cmbProfileGender.setSelectedItem(teacherEntitiesProfile.get(0).getGender());
                                strBuild.append("Đăng nhập thành công!");

                                teacher.setVisible(true);
                                setVisible(false);
                                break;
                            }
                        }
                    }
                }
                if(check == false){
                    strBuild.append("Đăng nhập không thành công!");
                }

                if(strBuild.length()>0)
                {
                    JOptionPane.showMessageDialog(panel1,strBuild.toString(),"Thông báo", JOptionPane.DEFAULT_OPTION);
                }
            }
        });
    }
    public int compareCharString(char[] c, String s)
    {
        if(c.length == s.length()) {
            for (int i = 0; i < s.length(); i++) {
                if (c[i] != s.charAt(i))
                    return 0;
            }
            return 1;
        }
        return 0;

    }
}

