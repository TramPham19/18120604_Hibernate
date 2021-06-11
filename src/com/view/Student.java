package com.view;

import com.DAO.*;
import com.hibernate.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
    private JPanel ListUserRegistation;
    private JButton btnDeleteRegistration;
    public String passProfileStudent;
    public String mssv;

    public DefaultTableModel tableModel = new DefaultTableModel();
    String[] columnCourse = new String[]{"STT", "Mã môn học", "Tên môn học", "Số tín chỉ", "Giáo viên", "Phòng học", "Thứ", "Ca học", "Slot", "Slot đã đăng kí", "Đăng kí"};
    String[] columnCourseMy = new String[]{"STT", "Mã môn học", "Tên môn học", "Số tín chỉ", "Giáo viên", "Phòng học", "Thứ", "Ca học", "Slot", "Hủy đăng kí"};

    public Student() {

        txtMSSV.setEnabled(false);
        List<CourseEntity> Course = new ArrayList<>();
        Course = CourseDAO.getAllCourse();
        showListCourse(Course);


        add(panel1);
        setTitle("LOG IN");
        setSize(1500, 1000);

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
                if (txtNewProfileStudent.getText().equals("")) {
                    strBuild.append("Nhập mật khẩu mới");
                }
                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (txtPass.getText().compareTo(passProfileStudent) == 0) {
                        studentEntity.get(0).setPassword(txtNewProfileStudent.getText());
                        boolean result = StudentDAO.updateStudent(studentEntity.get(0));
                        if (result == true) {
                            strBuild.append("Thay đổi mật khẩu thành công");
                            passProfileStudent = txtNewProfileStudent.getText();
                        } else {
                            strBuild.append("Thay đổi mật khẩu thất bại");
                        }
                    } else
                        strBuild.append("Mật khẩu hiện tại không đúng");
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Thông báo", JOptionPane.DEFAULT_OPTION);
                }
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

        btnRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                List<JoinCourseEntity> joinList = JoinCourseDAO.getAllJoinCourse(txtMSSV.getText());
                List<StudentEntity> studentList = StudentDAO.getInfoStudentByMSSV(txtMSSV.getText());
                List<CourseEntity> course = CourseDAO.getAllCourse();
                List<SemesterEntity> semester = SemesterDAO.getAllSemester();
                SemesterEntity semesterEntity = new SemesterEntity();
                for (SemesterEntity s : semester)
                    if (s.getType() == 1)
                        semesterEntity = SemesterDAO.getInfoSemesterByID(s.getId());
                int countRegister = 0;

                //Đếm số cột sv muôn đăng kí
                for (int i = 0; i < tbCourse.getRowCount(); i++) {
                    boolean check = Boolean.valueOf(tbCourse.getValueAt(i, 10).toString());
                    if (check == true) {
                        countRegister++;
                    }
                }

                boolean checkTimeCourse = true; //kiểm tra 2 khóa học có trùng giờ không
//                int temp = tbCourse.getSelectedRow();

                for (int i = 0; i < tbCourse.getRowCount(); i++) {

                    if (joinList.size() + countRegister <= 8) {
                        JoinCourseEntity join = new JoinCourseEntity();
//                        System.out.println(temp);
                        boolean check = Boolean.valueOf(tbCourse.getValueAt(i, 10).toString());

                        if (check == true) {
                            join.setId_course(course.get(i).getId());
                            join.setId_student(studentList.get(0).getId());
                            join.setDayRegistration(LocalDateTime.now());
                            CourseEntity courseEntity = CourseDAO.getInfoCourseByID(join.getId_course());
                            SubjectEntity subjectEntity = SubjectDAO.getInfoSubjectByID(courseEntity.getIdSubject());
                            if (JoinCourseDAO.getAllStudentJoinCourseId(courseEntity.getId()).size() == courseEntity.getSlotMax())
                                strBuild.append("Không còn slot" + "\n");
                            else {
                                if (checkTimeRegistInSession(semesterEntity, LocalDateTime.now()) == true) {
                                    for (JoinCourseEntity c : joinList) {
                                        if (checkTimeTwoCourse(course.get(i).getId(), c.getId_course()) == false) {
                                            checkTimeCourse = false;
                                            CourseEntity temp = CourseDAO.getInfoCourseByID(c.getId_course());
                                            SubjectEntity sub = SubjectDAO.getInfoSubjectByID(temp.getIdSubject());
                                            strBuild.append("Khóa học " + sub.getSubjectName() + ": Không thể đăng kí 2 giờ trùng nhau" + "\n");
                                            break;
                                        }
                                        System.out.println(course.get(i).getTimeOfDay());
                                        System.out.println(CourseDAO.getInfoCourseByID(c.getId_course()).getTimeOfDay());
                                    }
                                    System.out.println(checkTimeCourse);
                                    if (checkTimeCourse == true) {
                                        boolean result = JoinCourseDAO.addJoinCourse(join);
                                        CourseEntity temp = CourseDAO.getInfoCourseByID(join.getId_course());
                                        SubjectEntity sub = SubjectDAO.getInfoSubjectByID(temp.getIdSubject());
                                        if (result == true)
                                            strBuild.append("Khóa học: " + sub.getSubjectName() + " đăng kí thành công " + "\n");
                                    }
                                } else {
                                    strBuild.append("Hết hạn đăng kí " + "\n");
                                }
                            }
                        }
                    } else {
                        strBuild.append("Chỉ được đăng kí tối đa 8 môn ");
                        break;
                    }

                }
                JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Thông báo", JOptionPane.DEFAULT_OPTION);
                showListJoinCourse(JoinCourseDAO.getAllJoinCourse(txtMSSV.getText()));
                showListCourse(CourseDAO.getAllCourse());
            }
        });

        btnDeleteRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                List<JoinCourseEntity> joinList = JoinCourseDAO.getAllJoinCourse(txtMSSV.getText());
                List<StudentEntity> studentList = StudentDAO.getInfoStudentByMSSV(txtMSSV.getText());
                List<SemesterEntity> semester = SemesterDAO.getAllSemester();

                SemesterEntity semesterEntity = new SemesterEntity();
                for (SemesterEntity s : semester)
                    if (s.getType() == 1)
                        semesterEntity = SemesterDAO.getInfoSemesterByID(s.getId());
//                List<CourseEntity> course = CourseDAO.getAllCourse();
                for (int i = 0; i < tbRegistation.getRowCount(); i++) {
                    boolean check = Boolean.valueOf(tbRegistation.getValueAt(i, 9).toString());
                    if (check == true) {
                        JoinCourseEntity join = new JoinCourseEntity();
                        join.setId_course(joinList.get(i).getId_course());
                        join.setId_student(studentList.get(0).getId());
                        if (checkTimeRegistInSession(semesterEntity, LocalDateTime.now()) == true) {
                            List<JoinCourseEntity> deleteJoin = JoinCourseDAO.getInfoJoinCourseByStudentandCourseID(join.getId_student(), join.getId_course());
                            if (deleteJoin.size() > 0) {
                                CourseEntity temp = CourseDAO.getInfoCourseByID(join.getId_course());
                                SubjectEntity sub = SubjectDAO.getInfoSubjectByID(temp.getIdSubject());
                                boolean result = JoinCourseDAO.deleteJoinCourse(deleteJoin.get(0).getId());
                                if (result == true) {
                                    strBuild.append(sub.getSubjectName() + " : Xóa đăng kí thành công" + "\n");
                                } else {
                                    strBuild.append(sub.getSubjectName() + "Xóa đăng kí thất bại" + "\n");
                                }
                            }
                        } else {
                            strBuild.append("Hết hạn đăng kí " + "\n");
                        }
                    }
                }
                JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Thông báo", JOptionPane.DEFAULT_OPTION);
                showListJoinCourse(JoinCourseDAO.getAllJoinCourse(txtMSSV.getText()));
                showListCourse(CourseDAO.getAllCourse());
            }
        });
    }

    public String showPass(String pass) {
        String result = "";
        for (int i = 0; i < pass.length(); i++)
            result += "*";
        return result;
    }

    public void showListCourse(List<CourseEntity> list) {
        int size = list.size();
        Object[][] course = new Object[size][11];
        int j = 0;
        for (int i = 0; i < size; i++) {
            SubjectEntity subjectEntity = SubjectDAO.getInfoSubjectByID(list.get(i).getIdSubject());
            SemesterEntity semesterEntity = SemesterDAO.getInfoSemesterByID(list.get(i).getIdSemester());
            if (semesterEntity.getType() == 1) {
                course[i][0] = j + 1;
                course[i][1] = subjectEntity.getSubjectId();
                course[i][2] = subjectEntity.getSubjectName();
                course[i][3] = subjectEntity.getCredits();
                course[i][4] = list.get(i).getTeacherName();
                course[i][5] = list.get(i).getRoomName();
                course[i][6] = list.get(i).getDayOfWeek();
                course[i][7] = list.get(i).getTimeOfDay();
                course[i][8] = list.get(i).getSlotMax();
                course[i][9] = JoinCourseDAO.getAllStudentJoinCourseId(list.get(i).getId()).size();
                course[i][10] = false;
                j++;
            }
        }


        tbCourse.setModel(new DefaultTableModel(course, columnCourse) {
                              Class[] types = new Class[]{
                                      java.lang.Integer.class, java.lang.String.class, java.lang.String.class,
                                      java.lang.String.class, java.lang.String.class, java.lang.String.class,
                                      java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class};

                              public Class getColumnClass(int columnIndex) {
                                  return types[columnIndex];
                              }
                          }
        );
    }

    public boolean checkTimeTwoCourse(int idCourse1, int idCourse2) {
        CourseEntity course1 = CourseDAO.getInfoCourseByID(idCourse1);
        CourseEntity course2 = CourseDAO.getInfoCourseByID(idCourse2);
        if (course1 == null || course2 == null)
            return false;
        if (course1.getTimeOfDay().compareTo(course2.getTimeOfDay()) == 0 && course1.getDayOfWeek().compareTo(course2.getDayOfWeek()) == 0)
            return false;
        else
            return true;
    }

    public void showListJoinCourse(List<JoinCourseEntity> list) {
        int size = list.size();
        Object[][] course = new Object[size][10];
        for (int i = 0; i < size; i++) {
            CourseEntity courseEntity = CourseDAO.getInfoCourseByID(list.get(i).getId_course());
            SubjectEntity subjectEntity = SubjectDAO.getInfoSubjectByID(courseEntity.getIdSubject());
            course[i][0] = i + 1;
            course[i][1] = subjectEntity.getSubjectId();
            course[i][2] = subjectEntity.getSubjectName();
            course[i][3] = subjectEntity.getCredits();
            course[i][4] = courseEntity.getTeacherName();
            course[i][5] = courseEntity.getRoomName();
            course[i][6] = courseEntity.getDayOfWeek();
            course[i][7] = courseEntity.getTimeOfDay();
            course[i][8] = courseEntity.getSlotMax();
            course[i][9] = false;

        }

        tbRegistation.setModel(new DefaultTableModel(course, columnCourseMy) {
            Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

    }

    public boolean checkTimeRegistInSession(SemesterEntity s, LocalDateTime t) {
        List<SessionEntity> sessionEntities = SessionDAO.getInfoSessionByIdSemester(s.getId());
        for (SessionEntity session : sessionEntities) {
            if (session.getDateBegin().toLocalDate().compareTo(t.toLocalDate()) <0
                    && session.getDateEnd().toLocalDate().compareTo(t.toLocalDate()) >0) {
                return true;
            }

        }
        return false;
    }
}
