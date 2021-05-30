package com.view;

import javax.swing.*;

public class managerTeacher extends JFrame {
    public JPanel panelTeacher;
    private JComboBox comboBox1;
    private JTable table1;
    private JButton thêmButton;
    private JButton cậpNhậtButton;
    private JButton xóaButton;
    private JButton button4;
    private JTextField txtName;
    private JTextField txtId;
    private JTextField txtEmail;
    private JButton resetButton;
    private JButton tìmKiếmButton;
    private JTextField txtGender;
    private JTextField txtCMND;

    public managerTeacher()
    {
        add(panelTeacher);
        setTitle("LOG IN");
        setSize(1000,500);
    }
}
