package com.view;

import com.DAO.*;
import com.hibernate.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class manager extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField txtPassTeacher;
    private JTextField txtNameTeacher;
    private JTextField txtEmailTeacher;
    private JTextField txtGenderTeacher;
    public JTextField txtCMNDTeacher;
    private JButton btnAddTeacher;
    private JButton btnDeleteTeacher;
    private JButton btnUpdateTeacher;
    private JButton btnResetPassTeacher;
    private JButton btnSearchTeacher;
    private JTable tbTeacher;
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
    private JComboBox cmbYearSemester;
    private JComboBox cmbTimeCourse;
    private JSpinner spSlotCourse;
    private JComboBox cmbCourseWeek;
    private JButton btnDeleteSemester;
    private JButton btnAddSemester;
    private JButton btnUpdateSemester;
    private JButton btnSearchSemester;
    private JButton btnShowSemester;
    private JTable tbSemester;
    private JTextField txtNameSemester;
    private JPanel jpStartSemester;
    private JPanel jpEndSemester;
    private JComboBox cmbNameSemester;
    private JTable tbCourse;
    private JComboBox cmbTeacherCourse;
    private JComboBox cmbRoomCourse;
    private JPanel jbStartSession;
    private JPanel jbEndSession;
    private JButton btnAddSession;
    private JTable tbSession;
    private JButton btnSetSemester;
    private JComboBox cmbCoursetId;
    private JComboBox cmbCourseName;
    private JSpinner spCourseCredit;
    private JButton btnAddCourse;
    private JButton btnSearchCourse;
    private JButton btnShowCourse;
    private JButton btnDeleteCourse;
    private JComboBox cmbGenderStudent;
    private JComboBox cmbStudentClass;
    private JTextField txtMssvStudent;
    private JTextField txtNameStudent;
    private JTextField txtEmailStudent;
    private JTable tbStudent;
    private JButton btnAddStudent;
    private JButton btnShowStudent;
    private JButton btnSearchStudent;
    private JButton btnUpdateStudent;
    private JButton btnResetPassStudent;
    private JButton btnLogOut;
    private JLabel lbSemesterCourse;
    public JTextField txtProfileCMND;
    public JTextField txtProfileName;
    public JTextField txtProfileEmail;
    public JComboBox cmbProfileGender;
    private JButton btnChangePass;
    public JTextField txtProfilePass;
    public JTextField txtNewPass;
    private JButton btnChangeProfile;
    private JTextField txtSearchTeacher;
    private JTextField txtSearchSubject;
    private JTextField txtSearchCourse;
    private JTextField txtSearchStudent;
    private JButton btnShowCourseOfStudent;
    private JButton btnShowStudentOfCourse;
    private JButton btnSearchStudentOfCourse;
    private JLabel lbcourse;
    public String passProfile;


    String [] columnTeacher = new String [] {"STT", "CMND", "M???t kh???u", "H??? t??n", "Email","Gi???i t??nh"};
    String [] columnSubject = new String [] {"STT", "M?? m??n h???c","T??n m??n h???c","S??? t??n ch???"};
    String [] columnSemester = new String [] {"STT", "T??n h???c k??","N??m h???c","Ng??y b???t ?????u","Ng??y k???t th??c","H???c k?? hi???n t???i"};
    String [] columnClass = new String [] {"STT", "T??n l???p h???c","T???ng s??? sinh vi??n","S??? sinh vi??n nam","S??? sinh vi??n n???"};
    String [] columnSession = new String [] {"STT", "T??n h???c k??","N??m h???c","Ng??y b???t ?????u","Ng??y k???t th??c"};
    String [] columnCourse = new String [] {"STT", "M?? m??n h???c","T??n m??n h???c", "S??? t??n ch???","Gi??o vi??n","Ph??ng h???c","Th???","Ca h???c","Slot","N??m h???c"};
    String [] columnStudent = new String [] {"STT","L???p", "MSSV", "M???t kh???u", "H??? t??n", "Email","Gi???i t??nh"};
    String [] columnStudentOfCourse = new String [] {"STT","L???p", "MSSV", "H??? t??n", "M?? m??n h???c","T??n m??n h???c","T??n gi??o vi??n","Th???i gian h???c","Th???","Th???i gian ????ng k??"};


    public DefaultTableModel tableModel = new DefaultTableModel();

    //    private JPanel JPCalendar;
    Calendar calendar = Calendar.getInstance();
    JDateChooser dateChooserStartSemester = new JDateChooser(calendar.getTime());
    JDateChooser dateChooserEndSemester = new JDateChooser(calendar.getTime());
    JDateChooser dateChooserStartSession = new JDateChooser(calendar.getTime());
    JDateChooser dateChooserEndSession = new JDateChooser(calendar.getTime());
    public manager() {

        cmbCoursetId.setEnabled(false);
        spCourseCredit.setEnabled(false);
        txtProfileCMND.setEnabled(false);
        //set comboBox
        List<ClassEntity> tempClass = ClassDAO.getAllClass();
        for (ClassEntity classEntity : tempClass) {
            cmbStudentClass.addItem(classEntity.getClassName());
        }

        List<SubjectEntity> tempSubject = SubjectDAO.getAllSubject();
        for (SubjectEntity subjectEntity : tempSubject) {
            cmbCourseName.addItem(subjectEntity.getSubjectName());
            cmbCoursetId.addItem(subjectEntity.getSubjectId());
        }


        List<TeacherEntity> Teacher = new ArrayList<>();
        Teacher = TeacherDAO.getAllTeacher();
        showListTeacher(Teacher);

        List<SubjectEntity> Subject = new ArrayList<>();
        Subject = SubjectDAO.getAllSubject();
        showListSubject(Subject);

        List<SemesterEntity> Semester = new ArrayList<>();
        Semester = SemesterDAO.getAllSemester();
        showListSemester(Semester);

        List<ClassEntity> Class = new ArrayList<>();
        Class = ClassDAO.getAllClass();
        showListClass(Class);

        List<SessionEntity> Session = new ArrayList<>();
        Session = SessionDAO.getAllSession();
        showListSession(Session);

        List<CourseEntity> Course = new ArrayList<>();
        Course = CourseDAO.getAllCourse();
        showListCourse(Course);

        List<StudentEntity> Student = new ArrayList<>();
        Student = StudentDAO.getAllStudent();
        showListStudent(Student);

        add(panel1);
        setTitle("LOG IN");
        setSize(1500, 1000);
        List<TeacherEntity> finalTeacher = Teacher;

        dateChooserStartSemester.setDateFormatString("dd/MM/yyyy");
        dateChooserEndSemester.setDateFormatString("dd/MM/yyyy");
        dateChooserStartSession.setDateFormatString("dd/MM/yyyy");
        dateChooserEndSession.setDateFormatString("dd/MM/yyyy");
        jpStartSemester.add(dateChooserStartSemester);
        jpEndSemester.add(dateChooserEndSemester);
        jbStartSession.add(dateChooserStartSession);
        jbEndSession.add(dateChooserEndSession);


        List<SemesterEntity> semester = SemesterDAO.getAllSemester();
        for(SemesterEntity s:semester)
            if(s.getType()==1)
                lbSemesterCourse.setText(s.getSemesterName() + " " + s.getYear());
        // action in table teacher
        btnAddTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<TeacherEntity> finalTeacher = TeacherDAO.getAllTeacher();
                StringBuilder strBuild = new StringBuilder();
                if (txtCMNDTeacher.getText().equals("")) {
                    strBuild.append("CMND/CCCD kh??ng th??? tr???ng");
                } else {
                    for (int i = 0; i < finalTeacher.size(); i++) {
                        if (finalTeacher.get(i).getCmnd().compareTo(txtCMNDTeacher.getText()) == 0) {
                            strBuild.delete(0, strBuild.length());
                            strBuild.append("CMND/CCCD tr??n ???? t???n t???i t??i kho???n.");
                            break;
                        }
                    }
                }
                if (txtNameTeacher.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("H??? t??n kh??ng th??? tr???ng");
                }
                if (txtEmailTeacher.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("Email kh??ng th??? tr???ng");
                }
                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    TeacherEntity teacher = new TeacherEntity();
                    teacher.setCmnd(txtCMNDTeacher.getText());
                    teacher.setPassword(txtCMNDTeacher.getText());
                    teacher.setFullname(txtNameTeacher.getText());
                    teacher.setEmail(txtEmailTeacher.getText());
                    teacher.setGender((String) cmbGender.getItemAt(cmbGender.getSelectedIndex()));

                    boolean result = TeacherDAO.addTeacher(teacher);
                    if (result == true) {
                        strBuild.append("Th??m t??i kho???n gi??o v??? th??nh c??ng");
                        finalTeacher.add(teacher);
                    } else {
                        strBuild.append("Th??m t??i kho???n gi??o v??? th???t b???i");
                    }
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Success", JOptionPane.DEFAULT_OPTION);
                    showListTeacher(TeacherDAO.getAllTeacher());
                }
            }

        });
        btnDeleteTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check = 0;

                StringBuilder strBuild = new StringBuilder();
                if (txtCMNDTeacher.getText().equals("")) {
                    strBuild.append("CMND/CCCD kh??ng th??? tr???ng");
                } else {
                    for (int i = 0; i < finalTeacher.size(); i++) {
                        if (finalTeacher.get(i).getCmnd().compareTo(txtCMNDTeacher.getText()) == 0) {
                            check = 1;
                        }
                    }
                    if (check == 0) strBuild.append("CMND/CCCD kh??ng t???n t???i");
                    else {
                        List<TeacherEntity> list = TeacherDAO.getInfoTeacherByCMND(txtCMNDTeacher.getText());
                        boolean result = TeacherDAO.deleteTeacher(txtCMNDTeacher.getText());
                        if (result == true) {
                            strBuild.append("X??a th??nh c??ng");

                        } else {
                            strBuild.append("X??a th???t b???i");
                        }
                    }
                    List<TeacherEntity> result = TeacherDAO.getAllTeacher();
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Th??ng b??o", JOptionPane.DEFAULT_OPTION);
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
                    strBuild.append("CMND/CCCD kh??ng th??? tr???ng");
//                   txtCMNDTeacher.setBackground(Color.red);
                } else if (TeacherDAO.getInfoTeacherByCMND(txtCMNDTeacher.getText()) == null) {
                    strBuild.append("T??i kho???n kh??ng t???n t???i");
                } else if (txtNameTeacher.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("H??? t??n kh??ng th??? tr???ng");
//                   txtNameTeacher.setBackground(Color.red);
                } else if (txtEmailTeacher.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("Email kh??ng th??? tr???ng");
//                   txtEmailTeacher.setBackground(Color.red);
                }

                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    List<TeacherEntity> teacherList = TeacherDAO.getInfoTeacherByCMND(txtCMNDTeacher.getText());
                    TeacherEntity teacher = teacherList.get(0);
                    teacher.setCmnd(txtCMNDTeacher.getText());
                    teacher.setPassword(teacher.getPassword());
                    teacher.setFullname(txtNameTeacher.getText());
                    teacher.setEmail(txtEmailTeacher.getText());
                    teacher.setGender((String) cmbGender.getItemAt(cmbGender.getSelectedIndex()));
                    boolean result = TeacherDAO.updateTeacher(teacher);
                    if (result == true) {
                        strBuild.append("Ch???nh s???a t??i kho???n gi??o v??? th??nh c??ng");
                    } else {
                        strBuild.append("Ch???nh s???a t??i kho???n gi??o v??? th???t b???i");
                    }
                }
                showListTeacher(TeacherDAO.getAllTeacher());
            }
        });
        btnResetPassTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();

                if (txtCMNDTeacher.getText().equals("")) {
                    strBuild.append("Nh???p s??? CMND/CCCD ????? reset m???t kh???u cho t??i kho???n gi??o v???");
                }

                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    List<TeacherEntity> tempTeacher = TeacherDAO.getAllTeacher();
                    for (TeacherEntity teacher : tempTeacher) {
                        if (txtCMNDTeacher.getText().compareTo(teacher.getCmnd()) == 0) {
                            teacher.setCmnd(teacher.getCmnd());
                            teacher.setFullname(teacher.getFullname());
                            teacher.setEmail(teacher.getEmail());
                            teacher.setGender(teacher.getGender());
                            teacher.setPassword(teacher.getCmnd());
                        }
                        boolean result = TeacherDAO.updateTeacher(teacher);
                        if (result == true) {
                            strBuild.append("Reset m???t kh???u t??i kho???n gi??o v??? th??nh c??ng");
                        } else {
                            strBuild.append("Reset m???t kh???u t??i kho???n gi??o v??? th???t b???i");
                        }
                    }
                    showListTeacher(TeacherDAO.getAllTeacher());
                }
            }
        });
        btnSearchTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<TeacherEntity> teacher = new ArrayList<>();
                if (txtSearchTeacher.getText().compareTo("") != 0) {
                    teacher = TeacherDAO.getInfoTeacherByCMND(txtSearchTeacher.getText());
                    if (teacher.size() <= 0) {
                        teacher = TeacherDAO.getInfoTeacherByFullname(txtSearchTeacher.getText());
                        if (teacher.size() <= 0)
                            teacher = TeacherDAO.getInfoTeacherByGender(txtSearchTeacher.getText());
                    }
                }
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
                if (txtIdSubject.getText().equals("")) {
                    strBuild.append("M?? m??n h???c kh??ng th??? tr???ng");
                } else {
                    for (int i = 0; i < finalSubject.size(); i++) {
                        if (finalSubject.get(i).getSubjectId().compareTo(txtIdSubject.getText()) == 0) {
                            strBuild.append("M?? m??n h???c ???? t???n t???i t??i kho???n.");
                            break;
                        }
                    }
                }
                if (txtNameSubject.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("T??n m??n h???c kh??ng th??? tr???ng");
                }
                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    SubjectEntity subject = new SubjectEntity();
                    subject.setSubjectId(txtIdSubject.getText());
                    subject.setSubjectName(txtNameSubject.getText());
                    subject.setCredits((Integer) spCreditSubject.getValue());

                    boolean result = SubjectDAO.addSubject(subject);
                    if (result == true) {
                        strBuild.append("Th??m m??n h???c th??nh c??ng");
                    } else {
                        strBuild.append("Th??m m??n h???c th???t b???i");
                    }
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Success", JOptionPane.DEFAULT_OPTION);
                    showListSubject(SubjectDAO.getAllSubject());
                    cmbCourseName.addItem(subject.getSubjectName());
                    cmbCoursetId.addItem(subject.getSubjectId());
                }
            }

        });
        btnDeleteSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SubjectEntity> finalSubject = SubjectDAO.getAllSubject();
                int check = 0;

                StringBuilder strBuild = new StringBuilder();
                if (txtIdSubject.getText().equals("")) {
                    strBuild.append("M?? m??n h???c kh??ng th??? tr???ng");
                } else {
                    for (int i = 0; i < finalSubject.size(); i++) {
                        if (finalSubject.get(i).getSubjectId().compareTo(txtIdSubject.getText()) == 0) {
                            check = 1;
                        }
                    }
                    if (check == 0) strBuild.append("M?? m??n h???c kh??ng t???n t???i");
                    else {
                        List<SubjectEntity> list = SubjectDAO.getInfoSubjectById(txtIdSubject.getText());
                        boolean result = SubjectDAO.deleteSubject(txtIdSubject.getText());
                        if (result == true) {
                            strBuild.append("X??a th??nh c??ng");

                        } else {
                            strBuild.append("X??a th???t b???i");
                        }
                    }
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Th??ng b??o", JOptionPane.DEFAULT_OPTION);
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
                    strBuild.append("M?? m??n h???c kh??ng th??? tr???ng");
//                   txtCMNDTeacher.setBackground(Color.red);
                } else if (SubjectDAO.getInfoSubjectById(txtIdSubject.getText()) == null) {
                    strBuild.append("M??n h???c kh??ng t???n t???i");
                } else if (txtNameSubject.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("T??n m??n h???c kh??ng th??? tr???ng");
//                   txtNameTeacher.setBackground(Color.red);
                }

                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    List<SubjectEntity> subjectList = SubjectDAO.getInfoSubjectById(txtIdSubject.getText());
                    SubjectEntity subject = subjectList.get(0);
                    subject.setSubjectId(txtIdSubject.getText());
                    subject.setSubjectName(txtNameSubject.getText());
                    subject.setCredits((Integer) spCreditSubject.getValue());
                    boolean result = SubjectDAO.updateSubject(subject);
                    if (result == true) {
                        strBuild.append("Ch???nh s???a m??n h???c th??nh c??ng");
                    } else {
                        strBuild.append("Ch???nh s???a m??n h???c th???t b???i");
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
                List<SubjectEntity> subject = new ArrayList<>();
                if(txtSearchSubject.getText().compareTo("")!=0) {
                     subject = SubjectDAO.getInfoSubjectById(txtSearchSubject.getText());
                    if(subject.size()<=0) {
                        subject = SubjectDAO.getInfoSubjectByName(txtSearchSubject.getText());
                        if (subject.size() <= 0)
                            subject = SubjectDAO.getInfoSubjectByCredit(txtSearchSubject.getText());
                    }
                }
                showListSubject(subject);
            }
        });

        //End button in table Subject

        //Start button in table Session
        btnAddSemester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SemesterEntity> finalSemester = SemesterDAO.getAllSemester();
                StringBuilder strBuild = new StringBuilder();
                for (int i = 0; i < finalSemester.size(); i++) {
                    if (finalSemester.get(i).getSemesterName().compareToIgnoreCase((String) cmbNameSemester.getItemAt(cmbNameSemester.getSelectedIndex())) == 0
                            && finalSemester.get(i).getYear().compareTo((String) cmbYearSemester.getItemAt(cmbYearSemester.getSelectedIndex())) == 0) {
                        strBuild.append("H???c k?? ???? t???n t???i t??i kho???n.");
                        break;
                    }
                }
                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    SemesterEntity semesterEntity = new SemesterEntity();
                    semesterEntity.setSemesterName((String) cmbNameSemester.getItemAt(cmbNameSemester.getSelectedIndex()));
                    semesterEntity.setYear((String) cmbYearSemester.getItemAt(cmbYearSemester.getSelectedIndex()));
                    semesterEntity.setDateBegin(new java.sql.Date(dateChooserStartSemester.getDate().getTime()));
                    semesterEntity.setDateEnd(new java.sql.Date(dateChooserEndSemester.getDate().getTime()));
                    boolean result = SemesterDAO.addSemester(semesterEntity);
                    if (result == true) {
                        strBuild.append("Th??m h???c k?? th??nh c??ng");
                    } else {
                        strBuild.append("Th??m h???c k?? th???t b???i");
                    }
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Success", JOptionPane.DEFAULT_OPTION);
                    showListSemester(SemesterDAO.getAllSemester());
                }
            }

        });
        btnDeleteSemester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SemesterEntity> finalSemester = SemesterDAO.getAllSemester();
                int semester = 0;
                List<CourseEntity> courseEntities = new ArrayList<>();
                for (SemesterEntity s : finalSemester) {
                    if (s.getType() == 1) {
                        courseEntities = CourseDAO.getInfoCourseBySemester(s.getId());
                        semester=s.getId();
                    }
                }
                StringBuilder strBuild = new StringBuilder();
                if(SemesterDAO.getInfoSemesterByNameYear((String) cmbNameSemester.getItemAt(cmbNameSemester.getSelectedIndex()),
                        (String) cmbYearSemester.getItemAt(cmbYearSemester.getSelectedIndex())).size()>0) {
                    if (CourseDAO.getInfoCourseBySemester(SemesterDAO.getInfoSemesterByNameYear((String) cmbNameSemester.getItemAt(cmbNameSemester.getSelectedIndex()),
                            (String) cmbYearSemester.getItemAt(cmbYearSemester.getSelectedIndex())).get(0).getId()).size() > 0)
                        strBuild.append("T???n t???i kh??a h???c thu???c h???c k?? n??n kh??ng th??? x??a");
                    else {
                        if (SemesterDAO.getInfoSemesterByNameYear((String) cmbNameSemester.getItemAt(cmbNameSemester.getSelectedIndex()), (String) cmbYearSemester.getItemAt(cmbYearSemester.getSelectedIndex())).size() == 0) {
                            strBuild.append("H???c k?? kh??ng t???n t???i");
                        } else if (SemesterDAO.getInfoSemesterByNameYear((String) cmbNameSemester.getItemAt(cmbNameSemester.getSelectedIndex()), (String) cmbYearSemester.getItemAt(cmbYearSemester.getSelectedIndex())).get(0).getType() == 1) {
                            strBuild.append("??ang l?? h???c k?? hi???n t???i kh??ng th??? x??a");
                        } else {
                            boolean result = false;
                            List<SemesterEntity> list = SemesterDAO.getInfoSemesterByNameYear((String) cmbNameSemester.getItemAt(cmbNameSemester.getSelectedIndex()), (String) cmbYearSemester.getItemAt(cmbYearSemester.getSelectedIndex()));
                            if(list.size()>0) {
                                SessionDAO.DeleteSessionByIdSemester(list.get(0).getId());
                                result = SemesterDAO.deleteSemester((String) cmbNameSemester.getItemAt(cmbNameSemester.getSelectedIndex()), (String) cmbYearSemester.getItemAt(cmbYearSemester.getSelectedIndex()));
                            }
                            if (result == true) {
                                strBuild.append("X??a th??nh c??ng");

                            } else {
                                strBuild.append("X??a th???t b???i");
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Th??ng b??o", JOptionPane.DEFAULT_OPTION);
                showListSemester(SemesterDAO.getAllSemester());
                showListSession(SessionDAO.getAllSession());
            }
        });
        btnSetSemester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SemesterEntity> finalSemester = SemesterDAO.getAllSemester();
                int check = 0;
                StringBuilder strBuild = new StringBuilder();
                if (SemesterDAO.getInfoSemesterByNameYear((String) cmbNameSemester.getItemAt(cmbNameSemester.getSelectedIndex()), (String) cmbYearSemester.getItemAt(cmbYearSemester.getSelectedIndex())).size() == 0) {
                    strBuild.append("H???c k?? kh??ng t???n t???i");
                } else {
                    List<SemesterEntity> semesterSet = SemesterDAO.getInfoSemesterByNameYear((String) cmbNameSemester.getItemAt(cmbNameSemester.getSelectedIndex()), (String) cmbYearSemester.getItemAt(cmbYearSemester.getSelectedIndex()));

                    for (SemesterEntity semester : finalSemester) {
                        semester.setType(0);
                        boolean reset = SemesterDAO.updateSemester(semester);
                    }
                    semesterSet.get(0).setType(1);
                    boolean result = SemesterDAO.updateSemester(semesterSet.get(0));
                    if (result == true) {
                        strBuild.append("Set th??nh c??ng");
                        lbSemesterCourse.setText( semesterSet.get(0).getSemesterName()+ " " + semesterSet.get(0).getYear());
                    } else {
                        strBuild.append("Set th???t b???i");
                    }
                }
                JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Th??ng b??o", JOptionPane.DEFAULT_OPTION);
                showListSemester(SemesterDAO.getAllSemester());
                showListCourse(CourseDAO.getAllCourse());
            }
        });
        //End button in table Session

        //Start button in table Class
        btnAddClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ClassEntity> finalClass = ClassDAO.getAllClass();
                StringBuilder strBuild = new StringBuilder();
                if (txtNameClass.getText().equals("")) {
                    strBuild.append("T??n l???p kh??ng th??? tr???ng");
                } else {
                    for (int i = 0; i < finalClass.size(); i++) {
                        if (finalClass.get(i).getClassName().compareToIgnoreCase(txtNameClass.getText()) == 0) {
                            strBuild.append("L???p h???c ???? t???n t???i t??i kho???n.");
                            break;
                        }
                    }
                }
                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ClassEntity classEntity = new ClassEntity();
                    classEntity.setClassName(txtNameClass.getText());

                    boolean result = ClassDAO.addClass(classEntity);
                    if (result == true) {
                        strBuild.append("Th??m l???p h???c th??nh c??ng");
                    } else {
                        strBuild.append("Th??m l???p h???c th???t b???i");
                    }
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Success", JOptionPane.DEFAULT_OPTION);
                    showListClass(ClassDAO.getAllClass());
                    cmbStudentClass.addItem(classEntity.getClassName());
                }

            }

        });
        btnDeleteClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ClassEntity> finalClass = ClassDAO.getAllClass();
                int check = 0;

                StringBuilder strBuild = new StringBuilder();
                if (txtNameClass.getText().equals("")) {
                    strBuild.append("T??n l???p h???c kh??ng th??? tr???ng");
                } else {
                    for (int i = 0; i < finalClass.size(); i++) {
                        if (finalClass.get(i).getClassName().compareToIgnoreCase(txtNameClass.getText()) == 0) {
                            check = 1;
                        }
                    }
                    if (check == 0) strBuild.append("T??n l???p h???c kh??ng t???n t???i");
                    else {
                        List<ClassEntity> list = ClassDAO.getInfoClassByName(txtNameClass.getText());
                        if (list.get(0).getCountStudent() > 0)
                            strBuild.append("C?? sinh vi??n thu???c l???p n??n kh??ng th??? x??a");
                        else {
                            boolean result = ClassDAO.deleteClass(txtNameClass.getText());
                            if (result == true) {
                                strBuild.append("X??a th??nh c??ng");

                            } else {
                                strBuild.append("X??a th???t b???i");
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Th??ng b??o", JOptionPane.DEFAULT_OPTION);
                    showListClass(ClassDAO.getAllClass());
                }
            }

        });
//        btnSearchClass.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<ClassEntity> classEntities = ClassDAO.getInfoClassByName(txtNameClass.getText());
//                showListClass(classEntities);
//            }
//        });
//        btnShowClass.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showListClass(ClassDAO.getAllClass());
//            }
//        });
        // End button in table Class


        //Start button in table Student
        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StringBuilder strBuild = new StringBuilder();
                List<StudentEntity> finalStudent = StudentDAO.getAllStudent();
                if (txtMssvStudent.getText().equals("")) {
                    strBuild.append("MSSV kh??ng th??? tr???ng");
                } else {
                    for (int i = 0; i < finalStudent.size(); i++) {
                        if (finalStudent.get(i).getMssv().compareTo(txtMssvStudent.getText()) == 0) {
                            strBuild.delete(0, strBuild.length());
                            strBuild.append("MSSV tr??n ???? t???n t???i t??i kho???n.");
                            break;
                        }
                    }
                }
                if (txtNameStudent.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("H??? t??n kh??ng th??? tr???ng");
                }
                if (txtEmailStudent.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("Email kh??ng th??? tr???ng");
                }
                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    StudentEntity student = new StudentEntity();
                    student.setClassName((String) cmbStudentClass.getItemAt(cmbStudentClass.getSelectedIndex()));
                    student.setMssv(txtMssvStudent.getText());
                    student.setPassword(txtMssvStudent.getText());
                    student.setFullname(txtNameStudent.getText());
                    student.setEmail(txtEmailStudent.getText());
                    student.setGender((String) cmbGenderStudent.getItemAt(cmbGenderStudent.getSelectedIndex()));

                    boolean result = StudentDAO.addStudent(student);
                    if (result == true) {
                        strBuild.append("Th??m t??i kho???n gi??o v??? th??nh c??ng");
                        finalStudent.add(student);
                    } else {
                        strBuild.append("Th??m t??i kho???n gi??o v??? th???t b???i");
                    }
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Success", JOptionPane.DEFAULT_OPTION);
                    showListStudent(finalStudent);
                }
                List<ClassEntity> classEntities = ClassDAO.getAllClass();
                showListClass(classEntities);
            }

        });
        btnShowStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListStudent(StudentDAO.getAllStudent());
            }
        });
        btnSearchStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<StudentEntity> student = new ArrayList<>();
                if(txtSearchStudent.getText().compareTo("")!=0) {
                    student = StudentDAO.getInfoStudentByClass(txtSearchStudent.getText());
                    if (student.size() <= 0) {
                        student = StudentDAO.getInfoStudentByMSSV(txtSearchStudent.getText());
                        if (student.size() <= 0) {
                            student = StudentDAO.getInfoStudentByFullname(txtSearchStudent.getText());
                            if (student.size() <= 0) {
                                student = StudentDAO.getInfoStudentByGender(txtSearchStudent.getText());
                            }
                        }
                    }
                }
                showListStudent(student);
            }
        });
        btnUpdateStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();

                if (txtMssvStudent.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("MSSV kh??ng th??? tr???ng");
//                   txtCMNDStudent.setBackground(Color.red);
                } else if (StudentDAO.getInfoStudentByMSSV(txtMssvStudent.getText()) == null) {
                    strBuild.append("T??i kho???n kh??ng t???n t???i");
                } else if (txtNameStudent.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("H??? t??n kh??ng th??? tr???ng");
//                   txtNameStudent.setBackground(Color.red);
                } else if (txtEmailStudent.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("Email kh??ng th??? tr???ng");
//                   txtEmailStudent.setBackground(Color.red);
                }

                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    List<StudentEntity> studentList = StudentDAO.getInfoStudentByMSSV(txtMssvStudent.getText());
                    StudentEntity student = studentList.get(0);
                    student.setMssv(txtMssvStudent.getText());
                    student.setPassword(student.getPassword());
                    student.setFullname(txtNameStudent.getText());
                    student.setEmail(txtEmailStudent.getText());
                    student.setGender((String) cmbGenderStudent.getItemAt(cmbGenderStudent.getSelectedIndex()));
                    boolean result = StudentDAO.updateStudent(student);
                    if (result == true) {
                        strBuild.append("Ch???nh s???a t??i kho???n gi??o v??? th??nh c??ng");
                    } else {
                        strBuild.append("Ch???nh s???a t??i kho???n gi??o v??? th???t b???i");
                    }
                }
                showListStudent(StudentDAO.getAllStudent());
                showListClass(ClassDAO.getAllClass());
            }
        });
        btnResetPassStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();

                if (txtMssvStudent.getText().equals("")) {
                    strBuild.append("Nh???p s??? MSSV ????? reset m???t kh???u cho t??i kho???n sinh vi??n");
                }

                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    List<StudentEntity> tempStudent = StudentDAO.getAllStudent();
                    for (StudentEntity student : tempStudent) {
                        if (txtMssvStudent.getText().compareTo(student.getMssv()) == 0) {
                            student.setMssv(student.getMssv());
                            student.setFullname(student.getFullname());
                            student.setEmail(student.getEmail());
                            student.setGender(student.getGender());
                            student.setPassword(student.getMssv());
                        }
                        boolean result = StudentDAO.updateStudent(student);
                        if (result == true) {
                            strBuild.append("Reset m???t kh???u sinh vi??n th??nh c??ng");
                        } else {
                            strBuild.append("Reset m???t kh???u sinh vi??n th???t b???i");
                        }
                    }
                    showListStudent(StudentDAO.getAllStudent());
                }
            }
        });
        //End Button in table Student


        // Start button in table Session
        btnAddSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                List<SemesterEntity> semester = SemesterDAO.getAllSemester();
                for (int i = 0; i < semester.size(); i++) {
                    if (semester.get(i).getType() == 1) {
                        SessionEntity session = new SessionEntity();
                        session.setIdSemester(semester.get(i).getId());
                        session.setDateBegin(new java.sql.Date(dateChooserStartSession.getDate().getTime()));
                        session.setDateEnd(new java.sql.Date(dateChooserEndSession.getDate().getTime()));
                        boolean result = SessionDAO.addSession(session);
                        if (result == true) {
                            strBuild.append("Th??m k?? ????ng k?? th??nh c??ng");
                        } else {
                            strBuild.append("Th??m k?? ????ng k?? th???t b???i");
                        }
                        JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Th??ng b??o", JOptionPane.DEFAULT_OPTION);
                        showListSession(SessionDAO.getAllSession());
                        break;
                    }
                }
            }
        });

        // End button in table Session
        btnAddCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                List<SemesterEntity> semester = SemesterDAO.getAllSemester();
                List<SubjectEntity> subjectEntities = SubjectDAO.getInfoSubjectById((String) cmbCoursetId.getItemAt(cmbCoursetId.getSelectedIndex()));
                for (int i = 0; i < semester.size(); i++) {
                    if (semester.get(i).getType() == 1) {
                        CourseEntity course = new CourseEntity();
                        course.setIdSemester(semester.get(i).getId());
                        course.setIdSubject(subjectEntities.get(0).getId());
                        course.setTeacherName((String) cmbTeacherCourse.getItemAt(cmbTeacherCourse.getSelectedIndex()));
                        course.setRoomName((String) cmbRoomCourse.getItemAt(cmbRoomCourse.getSelectedIndex()));
                        course.setDayOfWeek((String) cmbCourseWeek.getItemAt(cmbCourseWeek.getSelectedIndex()));
                        course.setTimeOfDay((String) cmbTimeCourse.getItemAt(cmbTimeCourse.getSelectedIndex()));
                        course.setSlotMax((Integer) spSlotCourse.getValue());
                        boolean result = CourseDAO.addCourse(course);
                        if (result == true) {
                            strBuild.append("Th??m l???p h???c th??nh c??ng");
                        } else {
                            strBuild.append("Th??m l???p h???c th???t b???i");
                        }
                        JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Success", JOptionPane.DEFAULT_OPTION);
                        showListCourse(CourseDAO.getAllCourse());
                    }
                }
            }
        });
        btnSearchCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<CourseEntity> courseEntities = new ArrayList<>();
                if (txtSearchCourse.getText().compareTo("") != 0) {
                    courseEntities = CourseDAO.getInfoCourseById(txtSearchCourse.getText());
                }
                showListCourse(courseEntities);
            }
        });
        btnShowCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListCourse(CourseDAO.getAllCourse());
            }
        });
        btnDeleteCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<CourseEntity> finalCourse = CourseDAO.getAllCourse();

                StringBuilder strBuild = new StringBuilder();
                List<CourseEntity> course = new ArrayList<>();
                List<SemesterEntity> semester = SemesterDAO.getAllSemester();
                for(SemesterEntity s:semester)
                    if(s.getType()==1) {
                        course = CourseDAO.getInfoCourseBySemester(s.getId());
                    }
//                List<CourseEntity> list = CourseDAO.getInfoCourseByName((String) cmbCourseName.getItemAt(cmbCourseName.getSelectedIndex()));
                int check = tbCourse.getSelectedRow();
                    if(check<0){
                        strBuild.append("Ch???n d??ng ????? x??a");
                    }
                    else {
                        if (JoinCourseDAO.getAllStudentJoinCourseId(course.get(check).getId()).size() <= 0) {
                            boolean result = CourseDAO.DeleteCourse2(course.get(check).getId());
                            if (result == true) {
                                strBuild.append("X??a th??nh c??ng");
                            } else {
                                strBuild.append("X??a th???t b???i");
                            }
                        } else
                            strBuild.append("C?? sinh vi??n ????ng k?? n??n kh??ng th??? x??a");
                    }
                JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Th??ng b??o", JOptionPane.DEFAULT_OPTION);
                showListCourse(CourseDAO.getAllCourse());
            }
        });
        // End button in table Course
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn signIn = new SignIn();
                signIn.setVisible(true);
                setVisible(false);
            }
        });

        cmbCourseName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                List<SubjectEntity> subjectEntities = SubjectDAO.getInfoSubjectByName((String) cmbCourseName.getItemAt(cmbCourseName.getSelectedIndex()));
                cmbCoursetId.setSelectedItem(subjectEntities.get(0).getSubjectId());
                spCourseCredit.setValue(subjectEntities.get(0).getCredits());
            }
        });
        btnChangePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                List<TeacherEntity> teacherEntity = TeacherDAO.getInfoTeacherByCMND(txtCMNDTeacher.getText());
                if (txtNewPass.getText().equals("")) {
                    strBuild.append("Nh???p m???t kh???u m???i");
                }
                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (txtProfilePass.getText().compareTo(passProfile) == 0) {
                        teacherEntity.get(0).setPassword(txtNewPass.getText());
                        boolean result = TeacherDAO.updateTeacher(teacherEntity.get(0));
                        if (result == true) {
                            strBuild.append("Thay ?????i m???t kh???u th??nh c??ng");
                            passProfile = txtNewPass.getText();
                        } else {
                            strBuild.append("Thay ?????i m???t kh???u th???t b???i");
                        }
                    } else
                        strBuild.append("M???t kh???u hi???n t???i kh??ng ????ng");
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Th??ng b??o", JOptionPane.DEFAULT_OPTION);
                    showListTeacher(TeacherDAO.getAllTeacher());
                }
            }
        });
        btnChangeProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                if (txtProfileName.getText().equals("")) {
                    strBuild.append("H??? t??n kh??ng th??? tr???ng");
//                   txtNameTeacher.setBackground(Color.red);
                } else if (txtProfileEmail.getText().equals("")) {
                    strBuild.delete(0, strBuild.length());
                    strBuild.append("Email kh??ng th??? tr???ng");
//                   txtEmailTeacher.setBackground(Color.red);
                }

                if (strBuild.length() > 0) {
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    List<TeacherEntity> teacherList = TeacherDAO.getInfoTeacherByCMND(txtProfileCMND.getText());
                    TeacherEntity teacher = teacherList.get(0);
                    teacher.setCmnd(txtProfileCMND.getText());
                    teacher.setFullname(txtProfileName.getText());
                    teacher.setEmail(txtProfileEmail.getText());
                    teacher.setGender((String) cmbProfileGender.getItemAt(cmbProfileGender.getSelectedIndex()));
                    boolean result = TeacherDAO.updateTeacher(teacher);
                    if (result == true) {
                        strBuild.append("Ch???nh s???a th??ng tin th??nh c??ng");
                    } else {
                        strBuild.append("Ch???nh s???a th??ng tin th???t b???i");
                    }
                    JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Th??ng b??o", JOptionPane.DEFAULT_OPTION);

                }
            }
        });
        btnShowCourseOfStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                List<StudentEntity> student = StudentDAO.getAllStudent();
                int check = tbStudent.getSelectedRow();
                if (check < 0) {
                    strBuild.append("Ch???n sinh vi??n ????? xem danh s??ch kh??a h???c ");
                } else {
                    List<JoinCourseEntity> joinCourseEntities = JoinCourseDAO.getAllJoinCourse(student.get(check).getMssv());
                    showListJoinCourse(joinCourseEntities);
                }
                JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Th??ng b??o", JOptionPane.DEFAULT_OPTION);
            }
        });
        btnShowStudentOfCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                List<CourseEntity> course = new ArrayList<>();
                List<SemesterEntity> semester = SemesterDAO.getAllSemester();
                for (SemesterEntity s : semester)
                    if (s.getType() == 1) {
                        course = CourseDAO.getInfoCourseBySemester(s.getId());
                    }

                int check = tbCourse.getSelectedRow();
                if (check < 0) {
                    strBuild.append("Ch???n kh??a h???c ????? xem danh s??ch sinh vi??n");
                } else {
                    List<JoinCourseEntity> joinCourseEntities = JoinCourseDAO.getAllStudentJoinCourseId(course.get(check).getId());
                    showListStudentOfCourse(joinCourseEntities);
                    lbcourse.setText(SubjectDAO.getInfoSubjectByID(CourseDAO.getInfoCourseByID(course.get(check).getIdSubject()).getIdSubject()).getSubjectName());
                }
                JOptionPane.showMessageDialog(panel1, strBuild.toString(), "Th??ng b??o", JOptionPane.DEFAULT_OPTION);
            }
        });
        btnSearchStudentOfCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<JoinCourseEntity> joinCourseEntities = new ArrayList<>();
                if (txtSearchCourse.getText().compareTo("") != 0) {
                    List<StudentEntity> studentEntities = StudentDAO.getInfoStudentByMSSV(txtSearchCourse.getText());
                    joinCourseEntities = JoinCourseDAO.getAllStudentJoinCourseIdAndStudentID(CourseDAO.getInfoCourseByName(lbcourse.getText()).get(0).getId(),studentEntities.get(0).getId());
                }
                showListStudentOfCourse(joinCourseEntities);
            }
        });
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
            Subjects[i][0]=i+1;
            Subjects[i][1]=list.get(i).getSubjectId();
            Subjects[i][2]=list.get(i).getSubjectName();
            Subjects[i][3]=list.get(i).getCredits();
        }

        tbSubject.setModel(new DefaultTableModel(Subjects,columnSubject));
    }

    public void showListSemester(List<SemesterEntity> list){
        int size= list.size();
        Object [][]semester=new Object[size][6];
        for (int i=0;i<size; i++){
            semester[i][0]=i+1;
            semester[i][1]=list.get(i).getSemesterName();
            semester[i][2]=list.get(i).getYear();
            semester[i][3]=list.get(i).getDateBegin();
            semester[i][4]=list.get(i).getDateEnd();
            semester[i][5]=list.get(i).getType();
        }

        tbSemester.setModel(new DefaultTableModel(semester,columnSemester));
    }

    public void showListClass(List<ClassEntity> list){
        int size= list.size();
        Object [][] Class=new Object[size][6];
        for (int i=0;i<size; i++){
            Class[i][0]=i+1;
            Class[i][1]=list.get(i).getClassName();
            list.get(i).setCountStudent(StudentDAO.getInfoStudentByClass(list.get(i).getClassName()).size());
            Class[i][2]=list.get(i).getCountStudent();
            list.get(i).setCountMen(StudentDAO.getInfoStudentByGenderAndClass("Nam",list.get(i).getClassName()).size());
            Class[i][3]=list.get(i).getCountMen();
            list.get(i).setCountWomen(StudentDAO.getInfoStudentByGenderAndClass("N???",list.get(i).getClassName()).size());
            Class[i][4]=list.get(i).getCountWomen();
            boolean a = ClassDAO.updateClass(list.get(i));
        }
        tbClass.setModel(new DefaultTableModel(Class,columnClass));
    }

    public void showListSession(List<SessionEntity> list){
        int size= list.size();
        Object [][]session=new Object[size][6];
        for (int i=0;i<size; i++){
            SemesterEntity semesterEntity = SemesterDAO.getInfoSemesterByID(list.get(i).getIdSemester());
            session[i][0]=i+1;
            session[i][1]=semesterEntity.getSemesterName();
            session[i][2]=semesterEntity.getYear();
            session[i][3]=list.get(i).getDateBegin();
            session[i][4]=list.get(i).getDateEnd();
        }

        tbSession.setModel(new DefaultTableModel(session,columnSession));
    }

    public void showListCourse(List<CourseEntity> list){
        int size= list.size();
        Object [][]course=new Object[size][10];
        int j=0;
        for (int i=0;i<size; i++){
            SubjectEntity subjectEntity = SubjectDAO.getInfoSubjectByID(list.get(i).getIdSubject());
            SemesterEntity semesterEntity = SemesterDAO.getInfoSemesterByID(list.get(i).getIdSemester());
            if(semesterEntity.getType()==1) {
                course[i][0] = j + 1;
                course[i][1] = subjectEntity.getSubjectId();
                course[i][2] = subjectEntity.getSubjectName();
                course[i][3] = subjectEntity.getCredits();
                course[i][4] = list.get(i).getTeacherName();
                course[i][5] = list.get(i).getRoomName();
                course[i][6] = list.get(i).getDayOfWeek();
                course[i][7] = list.get(i).getTimeOfDay();
                course[i][8] = list.get(i).getSlotMax();
                course[i][9] = semesterEntity.getSemesterName() + " " + semesterEntity.getYear();
                j++;
            }
        }

        tbCourse.setModel(new DefaultTableModel(course,columnCourse));
    }

    public void showListStudent(List<StudentEntity> list){
        int size= list.size();
        Object [][]students=new Object[size][7];
        for (int i=0;i<size; i++){
            students[i][0]= i+1;
            students[i][1]=list.get(i).getClassName();
            students[i][2]=list.get(i).getMssv();
            students[i][3]= showPass(list.get(i).getPassword());
            students[i][4]=list.get(i).getFullname();
            students[i][5]=list.get(i).getEmail();
            students[i][6]=list.get(i).getGender();
        }
        tbStudent.setModel(new DefaultTableModel(students,columnStudent));
    }

    public void showListJoinCourse(List<JoinCourseEntity> list) {
        int size = list.size();
        Object[][] course = new Object[size][10];
        int j=0;
        for (int i = 0; i < size; i++) {
            CourseEntity courseEntity = CourseDAO.getInfoCourseByID(list.get(i).getId_course());
            SubjectEntity subjectEntity = SubjectDAO.getInfoSubjectByID(courseEntity.getIdSubject());
            SemesterEntity semesterEntity = SemesterDAO.getInfoSemesterByID(courseEntity.getIdSemester());
            if(semesterEntity.getType()==1) {
                course[i][0] = j + 1;
                course[i][1] = subjectEntity.getSubjectId();
                course[i][2] = subjectEntity.getSubjectName();
                course[i][3] = subjectEntity.getCredits();
                course[i][4] = courseEntity.getTeacherName();
                course[i][5] = courseEntity.getRoomName();
                course[i][6] = courseEntity.getDayOfWeek();
                course[i][7] = courseEntity.getTimeOfDay();
                course[i][8] = courseEntity.getSlotMax();
                course[i][9] = semesterEntity.getSemesterName() + " " + semesterEntity.getYear();
                j++;
            }
        }
        tbStudent.setModel(new DefaultTableModel(course, columnCourse));
    }

    public void showListStudentOfCourse(List<JoinCourseEntity> list){
        int size= list.size();
        Object [][]students=new Object[size][10];
        for (int i=0;i<size; i++){
            students[i][0]= i+1;
            CourseEntity courseEntity =CourseDAO.getInfoCourseByID(list.get(0).getId_course());
            StudentEntity studentEntity = StudentDAO.getInfoStudentByID(list.get(i).getId_student());
            SubjectEntity subjectEntity = SubjectDAO.getInfoSubjectByID(courseEntity.getIdSubject());
            students[i][1]=studentEntity.getClassName();
            students[i][2]=studentEntity.getMssv();
            students[i][3]=studentEntity.getFullname();
            students[i][4]=subjectEntity.getSubjectId();
            students[i][5]=subjectEntity.getSubjectName();
            students[i][6]=courseEntity.getTeacherName();
            students[i][7]=courseEntity.getTimeOfDay();
            students[i][8]=courseEntity.getDayOfWeek();
            students[i][9]=list.get(i).getDayRegistration();
        }
        tbCourse.setModel(new DefaultTableModel(students,columnStudentOfCourse));
    }
}
