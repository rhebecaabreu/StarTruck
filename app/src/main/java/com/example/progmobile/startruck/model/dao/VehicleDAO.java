package com.example.progmobile.startruck.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;

import com.example.progmobile.startruck.model.bean.Vehicle;
import com.example.progmobile.startruck.model.bean.Vehicle;

import java.io.IOException;
import java.util.ArrayList;

public class VehicleDAO {

    private static final String TABLE_NAME = "vehicle";

    private static final String COLUMN_ID = "idVehicle";
    private static final String COLUMN_TYPEVEHICLE = "typeVehicle";
    private static final String COLUMN_NAMEVEHICLE ="nameVehicle";
    private static final String COLUMN_plate = "plate";
    private static final String COLUMN_MARK = "mark";
    private static final String COLUMN_FUEL = "fuelType";
    private static final String COLUMN_AXES = "axesNumber";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_OBSERVATION ="observation";
    private static final String COLUMN_USERID = "userId";


    private SQLiteDatabase db, db2;


    public static final String TABLE_DROP_VEHICLE = "DROP TABLE IF EXISTS "+TABLE_NAME;


    public static final String TABLE_CREATE_VEHICLE =
            "CREATE TABLE vehicle ("+
                    "idVehicle integer not null,  " +
                    "typeVehicle text not null,"+
                    "nameVehicle text not null unique,"+
                    "plate text not null, "+
                    "mark text not null, "+
                    "fuelType text not null, "+
                    "status text, "+
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

    public void insertVehicle(Vehicle v){
        ContentValues values = new ContentValues();
    try {
        values.put(COLUMN_TYPEVEHICLE, v.getTypeVehicle());
        values.put(COLUMN_NAMEVEHICLE, v.getNameVehicle());
        values.put(COLUMN_plate, v.getPlate());
        values.put(COLUMN_MARK, v.getMark());
        values.put(COLUMN_FUEL, v.getFuelType());

        values.put(COLUMN_AXES, v.getAxesNumber());
        values.put(COLUMN_CAPACITY, v.getCapacity());

        values.put(COLUMN_STATUS, v.getStatus());
        values.put(COLUMN_OBSERVATION, v.getObservation());
        values.put(COLUMN_USERID, v.getUserId());

        db.insert(TABLE_NAME, null, values);
        System.out.println("insert");
    }catch (Exception e){
        e.printStackTrace();
    }

        db.close();
    }


    public long delete(String name_x){
        String query = "DELETE FROM " +TABLE_NAME+ " WHERE nameVehicle = '" +name_x+ "';";
        try{
            db2.execSQL(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public String selectTypeByName(String name){
        String query = "SELECT  typeVehicle, nameVehicle FROM "+TABLE_NAME;
        Cursor cursor = db2.rawQuery(query, null);
        String type="not found";
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){

                if(cursor.getString(1).equals(name)) {
                    type = cursor.getString(0);
                    System.out.println(type);

                }
            }
        }
        return type;
    }

    public int selectIdByName(String name, int idUser){
        System.out.println("vdao => "+name);
        String query = "SELECT  idVehicle, nameVehicle, userId FROM "+TABLE_NAME;
        Cursor cursor = db2.rawQuery(query, null);
        int id = -1;
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                if(cursor.getString(1).equals(name.trim()) && idUser == cursor.getInt(2)) {
                    id = cursor.getInt(0);
                    break;
                }
            }
        }
        return id;
    }

    public ArrayList<Vehicle> selectVehicles(int userId){
        String query = "SELECT userId, typeVehicle, nameVehicle, plate, mark, status, axesNumber, capacity, observation, fuelType FROM "+TABLE_NAME;

        Cursor cursor = db2.rawQuery(query, null);

                    ArrayList<Vehicle> listVehicles = new ArrayList<Vehicle>();

        String name,type, plate, mark, status, observation, fuel;
        int axesNumber, capacity;

        int user;
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                user = cursor.getInt(0);
                System.out.println("alo alo" + cursor.getString(2));
                if(userId==user){
                    name = cursor.getString(2);
                    type = cursor.getString(1);
                    plate = cursor.getString(3);
                    mark = cursor.getString(4);
                    status = cursor.getString(5);
                    axesNumber = cursor.getInt(6);
                    capacity = cursor.getInt(7);
                    observation = cursor.getString(8);
                    fuel= cursor.getString(9);

                    Vehicle v = new Vehicle(name, type, plate, mark, status, axesNumber, capacity, observation, fuel);
                    listVehicles.add(v);
                }
            }
        }
        return listVehicles;
    }

    public ArrayList<Vehicle> selectVehicleById(int vehicleId){
        String query = "SELECT  idVehicle, typeVehicle, nameVehicle, plate, mark, status, axesNumber, capacity, observation, fuelType FROM "+TABLE_NAME;

        Cursor cursor = db2.rawQuery(query, null);

        ArrayList<Vehicle> listVehicles = new ArrayList<Vehicle>();

        String name,type, plate, mark, status, observation, fuel;
        int axesNumber, capacity;

        int vehicle;
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                vehicle = cursor.getInt(0);

                if(vehicleId==vehicle){
                    name = cursor.getString(2);
                    type = cursor.getString(1);
                    plate = cursor.getString(3);
                    mark = cursor.getString(4);
                    status = cursor.getString(5);
                    axesNumber = cursor.getInt(6);
                    capacity = cursor.getInt(7);
                    observation = cursor.getString(8);
                    fuel = cursor.getString(9);

                    Vehicle v = new Vehicle(name, type, plate, mark, status, axesNumber, capacity, observation, fuel);
                    listVehicles.add(v);
                }
            }
        }
        return listVehicles;
    }

    public ArrayList<Vehicle> selectVehiclesByStatusM(int userId){
        String query = "SELECT userId, typeVehicle, nameVehicle, plate, mark, status FROM "+TABLE_NAME;

        Cursor cursor = db2.rawQuery(query, null);

        ArrayList<Vehicle> listVehicles = new ArrayList<Vehicle>();

        String name,type, plate, mark, status;

        int user = -1;
        if(cursor.moveToFirst()){
            // System.out.println("hum");
            user = cursor.getInt(0);
            while (cursor.moveToNext()){
                if (userId == user) {
                    System.out.println(cursor.getString(5));

                    if(cursor.getString(5)!=null && cursor.getString(5).equals("Veículo em manutenção") ) {
                        user = cursor.getInt(0);
                        //  System.out.println(userId +" "+ user);
                        System.out.println(cursor.getString(5));
                        System.out.println("dois");

                        name = cursor.getString(2);
                        type = cursor.getString(1);
                        plate = cursor.getString(3);
                        mark = cursor.getString(4);
                        status = cursor.getString(5);

                        Vehicle v = new Vehicle(name, type, plate, mark, status);
                        listVehicles.add(v);
                    }
                } else {
                    break;
                }
            }

        }
        return listVehicles;
    }

    public ArrayList<Vehicle> selectVehiclesByStatusT(int userId){
        String query = "SELECT userId, typeVehicle, nameVehicle, plate, mark, status FROM "+TABLE_NAME;

        Cursor cursor = db2.rawQuery(query, null);

        ArrayList<Vehicle> listVehicles = new ArrayList<Vehicle>();

        String name,type, plate, mark, status;

        int user = -1;
        if(cursor.moveToFirst()){
            // System.out.println("hum");
            user = cursor.getInt(0);
            while (cursor.moveToNext()){
                if (userId == user) {
                    System.out.println(cursor.getString(5));

                    if(cursor.getString(5)!=null && cursor.getString(5).equals("Veículo em viagem") ) {
                        user = cursor.getInt(0);
                        //  System.out.println(userId +" "+ user);
                        System.out.println(cursor.getString(5));
                        System.out.println("dois");

                        name = cursor.getString(2);
                        type = cursor.getString(1);
                        plate = cursor.getString(3);
                        mark = cursor.getString(4);
                        status = cursor.getString(5);

                        Vehicle v = new Vehicle(name, type, plate, mark, status);
                        listVehicles.add(v);
                    }
                } else {
                    break;
                }
            }

        }
        return listVehicles;
    }

    public ArrayList<Vehicle> selectVehiclesByStatusEnterprise(int userId){
        String query = "SELECT userId, typeVehicle, nameVehicle, plate, mark, status FROM "+TABLE_NAME;

        Cursor cursor = db2.rawQuery(query, null);

        ArrayList<Vehicle> listVehicles = new ArrayList<Vehicle>();

        String name,type, plate, mark, status;

        int user = -1;
        if(cursor.moveToFirst()){
           // System.out.println("hum");
            user = cursor.getInt(0);
            while (cursor.moveToNext()){
                if (userId == user) {
                    System.out.println(cursor.getString(5));

                    if(cursor.getString(5)!=null && cursor.getString(5).equals("Veículo na empresa") ) {
                        user = cursor.getInt(0);
                        //  System.out.println(userId +" "+ user);
                        System.out.println(cursor.getString(5));
                        System.out.println("dois");

                        name = cursor.getString(2);
                        type = cursor.getString(1);
                        plate = cursor.getString(3);
                        mark = cursor.getString(4);
                        status = cursor.getString(5);

                        Vehicle v = new Vehicle(name, type, plate, mark, status);
                        listVehicles.add(v);
                    }
                } else {
                    break;
                }
            }

        }
        return listVehicles;
    }



    public void update(String vehicle, String statuss){

        String query = "UPDATE " +TABLE_NAME+ " SET status = '" +statuss+ "'"+
                " WHERE nameVehicle = '" +vehicle+ "';";
        try {
            db2.execSQL(query);
        } catch (Exception e){

        }
        db2.close();

    }



    public void updateVehicle(Vehicle v){

        String query = "UPDATE " +TABLE_NAME+
                " SET " +
                "typeVehicle = '" +v.getTypeVehicle()+ "', "+
                "nameVehicle = '" +v.getNameVehicle()+ "', "+
                "plate = '" + v.getPlate()+ "', "+
                "mark = '" + v.getMark()+ "',  "+
                "fuelType = '" + v.getFuelType()+ "', "+
                "axesNumber = '"+v.getAxesNumber()+ "', "+
                "capacity = '" +v.getCapacity()+ "', "+
                "observation = '" +v.getObservation()+ "' "+

                " WHERE idVehicle = '" +v.getId()+ "';";

        try {
            db2.execSQL(query);
            System.out.println("updated");
        } catch (Exception e){

        }
        db2.close();

    }
}
