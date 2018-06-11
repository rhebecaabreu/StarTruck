package com.example.progmobile.startruck.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.progmobile.startruck.model.bean.Vehicle;

public class VehicleDAO {

    private static final String TABLE_NAME = "vehicle";

    private static final String COLUMN_ID = "idVehicle";
    private static final String COLUMN_TYPEVEHICLE = "typeVehicle";
    private static final String COLUMN_plate = "plate";
    private static final String COLUMN_YEAR = "year" ;
    private static final String COLUMN_MARK = "mark";
    private static final String COLUMN_FUEL = "fuelType";
    private static final String COLUMN_COLOR = "color";
    private static final String COLUMN_AXES = "axesNumber";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_OBSERVATION ="observation";
    private static final String COLUMN_TYPEHL = "typeHeavyLoad";
    private static final String COLUMN_USERID = "userId";


    private SQLiteDatabase db = null, db2;


    public static final String TABLE_DROP_VEHICLE = "DROP TABLE IF EXISTS "+TABLE_NAME;


    public static final String TABLE_CREATE_VEHICLE =
            "CREATE TABLE vehicle ("+
                    "idVehicle integer not null,  " +
                    "typeVehicle text not null,"+
                    "plate text not null, "+
                    "year integer not null, "+
                    "mark text not null, "+
                    "fuelType text not null, "+
                    "color text not null, "+
                    "axesNumber integer, " +
                    "capacity integer, "+
                    "observation text, "+
                    "userId integer not null, "+
                    "FOREIGN KEY (userId) REFERENCES user(idUser) ON UPDATE CASCADE ON DELETE NO ACTION,"+
                    "PRIMARY KEY(idVehicle))";

    private static VehicleDAO instance;

    private static VehicleDAO getInstance(Context context){
        if(instance == null)
            instance = new VehicleDAO(context);
        return instance;
    }

    public VehicleDAO(Context context){
        BDHelper bdHelper = BDHelper.getInstance(context);
        db = bdHelper.getWritableDatabase();
        db2= bdHelper.getReadableDatabase();
    }

    public void insert(Vehicle v, boolean heavyLoad){
        ContentValues values = new ContentValues();

        values.put(COLUMN_TYPEVEHICLE, v.getId());
        values.put(COLUMN_plate, v.getPlate());
        values.put(COLUMN_YEAR, v.getYearManufacture());
        values.put(COLUMN_MARK, v.getMark());
        values.put(COLUMN_FUEL, v.getFuelType());
        values.put(COLUMN_COLOR, v.getColor());

        if(heavyLoad){
            values.put(COLUMN_AXES, v.getAxesNumber());
            values.put(COLUMN_CAPACITY, v.getCapacity());
        }


        values.put(COLUMN_OBSERVATION, v.getObservation());
        values.put(COLUMN_USERID, v.getUserId());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
