package com.company;

import com.DAO.AccountDAO;
import com.hibernate.AccountEntity;
import com.view.SignIn;
import com.view.manager;
import com.view.managerTeacher;

import java.util.List;

public class Main {

    public static void main(String[] args) {
//        List<AccountEntity> rs = AccountDAO.getAllAccount();
//        for (AccountEntity item:rs)
//            System.out.println(item.getEmail());
//        SignIn signIn = new SignIn();
//        signIn.setVisible(true);
//        managerTeacher signIn = new managerTeacher();
//        signIn.setVisible(true);
        manager signIn = new manager();
        signIn.setVisible(true);
    }

}

