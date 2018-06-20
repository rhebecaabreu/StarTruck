package com.example.progmobile.startruck.controller;

import android.content.Context;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.progmobile.startruck.model.bean.Driver;
import com.example.progmobile.startruck.model.dao.DriverDAO;
import com.example.progmobile.startruck.view.DriversListFragment;
import com.example.progmobile.startruck.view.MainActivity;

public class DriverController {
    Driver d = new Driver();

    public void save(Context context, String Name, String CPF, String RG, String email, String Phone, String category, int numberCNH) {
        DriverDAO ddao = new DriverDAO(context);
        UserController userController = new UserController();
        d.setName(Name);
        d.setCPF(CPF);
        d.setRG(RG);
        d.setEmail(email);
        d.setPhone(Phone);
        d.setUserId(MainActivity.usr);
        d.setCatCnh(category);
        d.setCNHnumber(numberCNH);
        ddao.insert(d);
    }


    public void update(Context context, String Name, String CPF, String RG, String email, String Phone, String category, int numberCNH) {
        DriverDAO ddao = new DriverDAO(context);
        d.setName(Name);
        d.setCPF(CPF);
        d.setRG(RG);
        d.setEmail(email);
        d.setPhone(Phone);
        d.setUserId(MainActivity.usr);
        d.setCatCnh(category);
        d.setCNHnumber(numberCNH);


        System.out.println(Name+"   =>   "+ddao.searchDriverID(Name));

        d.setId(ddao.searchDriverID(DriversListFragment.driverSelected));

        ddao.updateDriver(d);
    }


    public int searchIdDvr(Context context, String username) {
        DriverDAO ddao = new DriverDAO(context);
        int driverId = ddao.searchDriverID(username);
        return driverId;
    }
}
