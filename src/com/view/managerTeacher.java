package com.view;

import com.DAO.AccountDAO;
import com.hibernate.AccountEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class managerTeacher extends JFrame {
    public JPanel panelTeacher;
    private JComboBox comboBox1;
    private JTable table1;
    private JButton button4;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtGender;
    private JTextField txtCMND;
    private JButton thêmButton;
    private JButton xóaButton;
    private JButton cậpNhậtButton;
    private JButton resetButton;
    private JButton tìmKiếmButton;

    String [] columnNames = new String [] {
            "ID", "MSSV", "EMAIL", "FULLNAME", "Giới tính","Loại"};
    private DefaultTableModel tableModel = new DefaultTableModel();

    public managerTeacher()
    {


        List<AccountEntity> Teacher = new ArrayList<>();
        Teacher = AccountDAO.getAllAccount();
        showListStudents(Teacher);

        add(panelTeacher);
        setTitle("LOG IN");
        setSize(1000,500);

    }

    public void showListStudents(List<AccountEntity> list){
        int size= list.size();
        Object [][]students=new Object[size][6];
        for (int i=0;i<size; i++){
            students[i][0]=list.get(i).getId();
            students[i][1]=list.get(i).getMssv();
            students[i][2]=list.get(i).getEmail();
            students[i][3]=list.get(i).getFullname();
            students[i][4]=list.get(i).getGender();
        }

        table1.setModel(new DefaultTableModel(students,columnNames));
    }
}
