package com.example.progmobile.startruck.controller;

import android.content.Context;

import com.example.progmobile.startruck.model.bean.Enterprise;
import com.example.progmobile.startruck.model.dao.EnterpriseDAO;
import com.example.progmobile.startruck.view.MainActivity;

import java.util.ArrayList;

public class EnterpriseController {
    Enterprise e = new Enterprise();

    public void save(Context context, String name, String cnpj, String adress, String phone) {
        EnterpriseDAO edao = new EnterpriseDAO(context);

        e.setName(name);
        e.setCpnj(cnpj);
        e.setAdress(adress);
        e.setPhone(phone);
        edao.insert(e);
    }


    public ArrayList<String> spinnerArrayList(Context context) {
        EnterpriseDAO edao = new EnterpriseDAO(context);
        ArrayList <String> enterprise = new ArrayList<String>();
        enterprise.add("Selecione sua empresa");
        for(Enterprise etp: edao.selectAllEnterprises()){
            enterprise.add(etp.getName().toString());
        }
        enterprise.add("Minha empresa não esta na lista");
        return enterprise;
    }
}
