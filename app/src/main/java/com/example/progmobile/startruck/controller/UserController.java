package com.example.progmobile.startruck.controller;

import android.content.Context;

import com.example.progmobile.startruck.model.bean.User;
import com.example.progmobile.startruck.model.dao.UserDAO;
import com.example.progmobile.startruck.view.MainActivity;

public class UserController {

    User u = new User();

    public void save(Context context, String firstName, String lastName, String enterprise, String function, String email, String username, String password) {
        UserDAO udao = new UserDAO(context);
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setFunction(function);
        u.setEmail(email);
        u.setUsername(username);
        u.setPassword(password);
        u.setEnterprise(enterprise);
        udao.insert(u);
    }

    public boolean login(Context context, String username, String password){
        UserDAO udao = new UserDAO(context);
        if(udao.checkUsernamePassword(username, password)){
            return true;
        }
        else return false;
    }

    public int searchIdUsr(Context context, String username) {
        UserDAO udao = new UserDAO(context);
        int userId = udao.searchUserID(username);
        return userId;
    }

    public String seachFullName(Context context, String username){
        UserDAO udao = new UserDAO(context);
        String fullName = udao.searchFullName(username);
        return  fullName;
    }
}
