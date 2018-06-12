package com.example.progmobile.startruck.controller;

import android.content.Context;

import com.example.progmobile.startruck.model.bean.Vehicle;
import com.example.progmobile.startruck.model.dao.VehicleDAO;

public class VehicleController {
    Vehicle v = new Vehicle();


    public void save(Context context, String typeVehicle, String name, String platee, String markk, String typeFuel,
                     int axess, int capacityy, String observationn) {
        VehicleDAO vdao = new VehicleDAO(context);

        v.setTypeVehicle(typeVehicle);
        v.setNameVehicle(name);
        v.setPlate(platee);
        v.setMark(markk);
        v.setFuelType(typeFuel);
        v.setAxesNumber(axess);
        v.setCapacity(capacityy);
        v.setObservation(observationn);

        vdao.insert(v, true);

    }

    public void save2(Context context, String typeVehicle, String name, String platee, String markk, String typeFuel, String observationn) {
        VehicleDAO vdao = new VehicleDAO(context);

        v.setTypeVehicle(typeVehicle);
        v.setNameVehicle(name);
        v.setPlate(platee);
        v.setMark(markk);
        v.setFuelType(typeFuel);

        v.setObservation(observationn);

        vdao.insert(v, false);
    }
}
