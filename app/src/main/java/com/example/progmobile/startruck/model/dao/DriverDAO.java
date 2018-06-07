package com.example.progmobile.startruck.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.progmobile.startruck.model.bean.Driver;
import com.example.progmobile.startruck.model.bean.User;

public class DriverDAO extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "starTruck.db";

    private static final String TABLE_NAME = "driver";

    private static final String COLUMN_ID = "idDriver";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_CPF = "CPF";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "Phone" ;
    private static final String COLUMN_RG = "RG";


    SQLiteDatabase bd;

    private static final String TABLE_CREATE =
            "CREATE TABLE driver ("+
                    "idDriver integer not null, " +
                    "Name text not null, "+
                    "CPF text not null, "+
                    "email text not null, "+
                    "RG text not null, "+
                    "Phone text not null unique, "+
                    "PRIMARY KEY(idDriver))";

    public DriverDAO(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.bd = db;

        bd.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        this.bd = sqLiteDatabase;
        bd.execSQL(query);
        this.onCreate(bd);
    }


    public void insert(Driver d){
        bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, d.getName());
        values.put(COLUMN_CPF, d.getCPF());
        values.put(COLUMN_EMAIL, d.getEmail());
        values.put(COLUMN_RG, d.getRG());
        values.put(COLUMN_PHONE, d.getPhone());


        bd.insert(TABLE_NAME, null, values);
        bd.close();
    }

    public int searchDriverID(String Nome){
        bd = this.getWritableDatabase();
        String query = "SELECT Name, idDriver FROM " + TABLE_NAME;
        Cursor cursor = bd.rawQuery(query, null);
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

    public void update(){

    }


}





