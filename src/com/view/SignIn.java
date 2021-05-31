package com.view;

import com.DAO.AccountDAO;
import com.hibernate.AccountEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SignIn extends JFrame{

    public JPanel panel1;
    private JTextField txtUser;
    private JButton ĐĂNGNHẬPButton;
    private JPasswordField txtPassword;
    private JComboBox comboBox1;


    public SignIn()
    {
        add(panel1);
        setTitle("LOG IN");
        setSize(500,350);
        ĐĂNGNHẬPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strBuild = new StringBuilder();
                String user = txtUser.getText();
                char[] pass = txtPassword.getPassword();
                List<AccountEntity> Teacher = new ArrayList<>();
                Teacher = AccountDAO.getAllAccount();
                for (AccountEntity item : Teacher) {
                    if (user.compareTo(item.getMssv()) == 0 && compareCharString(pass,item.getPassword())==1) {
                        managerTeacher managerTeacher = new managerTeacher();
                        managerTeacher.setVisible(true);
                        setVisible(false);
                        break;
                    } else
                        strBuild.append("Đăng nhập không thành công!");
                }
                if(strBuild.length()>0)
                {
                    JOptionPane.showMessageDialog(panel1,strBuild.toString(),"Error", JOptionPane.ERROR_MESSAGE);
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

