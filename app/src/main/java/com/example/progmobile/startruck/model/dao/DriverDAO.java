package com.example.progmobile.startruck.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.progmobile.startruck.model.bean.Driver;
import com.example.progmobile.startruck.model.bean.User;

import java.util.ArrayList;

public class DriverDAO {

    private static final String TABLE_NAME = "driver";

    private static final String COLUMN_ID = "idDriver";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CPF = "cpf";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone" ;
    private static final String COLUMN_RG = "rg";
    private static final String COLUMN_ID_USER = "userId" ;


    SQLiteDatabase db, db2;

    public static final String TABLE_CREATE_DRIVER =
            "CREATE TABLE driver ("+
                    "idDriver integer not null, " +
                    "name text not null unique, "+
                    "cpf text not null, "+
                    "email text not null, "+
                    "rg text not null, "+
                    "phone text not null unique, " +
                    "userId integer not null, " +
                    "FOREIGN KEY (userId) REFERENCES user(idUser) ON UPDATE NO ACTION ON DELETE CASCADE,"+
                    "PRIMARY KEY(idDriver))";

    public static final String TABLE_DROP_DRIVER = "DROP TABLE IF EXISTS "+TABLE_NAME;

    private static DriverDAO instance;

    private static DriverDAO getInstance(Context context){
        if(instance == null)
            instance = new DriverDAO(context);
        return instance;
    }


    public DriverDAO(Context context){
        BDHelper bdHelper = BDHelper.getInstance(context);
        db = bdHelper.getWritableDatabase();
        db2 = bdHelper.getReadableDatabase();
    }

    public void insert(Driver d){
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, d.getName());
        values.put(COLUMN_CPF, d.getCPF());
        values.put(COLUMN_EMAIL, d.getEmail());
        values.put(COLUMN_RG, d.getRG());
        values.put(COLUMN_PHONE, d.getPhone());
        values.put(COLUMN_ID_USER, d.getUserId());


        db.insert(TABLE_NAME, null, values);
    }


    public ArrayList<Driver> selectDrivers(int userId){
        String query = "SELECT userId, name, cpf, email, rg, phone FROM "+TABLE_NAME;

        Cursor cursor = db2.rawQuery(query, null);

        ArrayList<Driver> listDrivers = new ArrayList<Driver>();

        String name,cpf;

        int user;
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                user = cursor.getInt(0);
                if(userId==user){
                    name = cursor.getString(1);
                    cpf = cursor.getString(2);

                    Driver d = new Driver(name, cpf);
                    listDrivers.add(d);

                }

            }


        }

        return listDrivers;

    }

    public int searchDriverID(String Nome){
        String query = "SELECT name, idDriver FROM " + TABLE_NAME;
        Cursor cursor = db2.rawQuery(query, null);
        String a;
        int b;

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(Nome)) {
                    b = cursor.getInt(1);
                    return b;
                }
            } while (cursor.moveToNext());
        }
        return -1;
    }


    public long delete(String name_x){
        String query = "DELETE FROM " +TABLE_NAME+ " WHERE name = '" +name_x+ "';";
        try{
            db2.execSQL(query);
            System.out.println("Deu bom pra excluir");

        }catch (Exception e){
            System.out.println("Deu ruim pra excluir");
            e.printStackTrace();
        }

        return 0;
    }

    public void update(){

    }


}





