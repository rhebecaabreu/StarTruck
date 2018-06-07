package com.example.progmobile.startruck.controller;

import android.content.Context;

import com.example.progmobile.startruck.model.bean.Driver;
import com.example.progmobile.startruck.model.dao.DriverDAO;

public class DriverController {
    Driver d = new Driver();

    public void save(Context context, String Name, String CPF, String RG, String email, String Phone) {
        DriverDAO ddao = new DriverDAO(context);
        d.setName(Name);
        d.setCPF(CPF);
        d.setRG(RG);
        d.setEmail(email);
        d.setPhone(Phone);
        ddao.insert(d);
    }

    public int searchIdDvr(Context context, String username) {
        DriverDAO ddao = new DriverDAO(context);
        int driverId = ddao.searchDriverID(username);
        return driverId;
    }
}
