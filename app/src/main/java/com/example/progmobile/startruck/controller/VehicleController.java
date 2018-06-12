package com.example.progmobile.startruck.controller;

import android.content.Context;

import com.example.progmobile.startruck.model.bean.Vehicle;
import com.example.progmobile.startruck.model.dao.VehicleDAO;
import com.example.progmobile.startruck.view.MainActivity;

public class VehicleController {
    Vehicle v = new Vehicle();


    public void save(Context context, String typeVehicle, String name, String platee, String markk, String typeFuel,
                     String axess, String capacityy, String observationn) {
        VehicleDAO vdao = new VehicleDAO(context);

        System.out.println(typeVehicle);
        v.setTypeVehicle(typeVehicle);
        v.setNameVehicle(name);
        v.setPlate(platee);
        v.setMark(markk);
        v.setFuelType(typeFuel);

        if(axess!=null || capacityy != null){
            v.setAxesNumber(Integer.parseInt(axess));
            v.setCapacity(Integer.parseInt(capacityy));
        }

        v.setObservation(observationn);
        v.setUserId(MainActivity.usr);

        System.out.print("save");


        vdao.insertVehicle(v);

    }

}
