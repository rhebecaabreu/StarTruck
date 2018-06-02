package com.example.progmobile.startruck.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.progmobile.startruck.model.bean.Enterprise;
import com.example.progmobile.startruck.model.bean.User;

import java.util.ArrayList;

public class EnterpriseDAO extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "starTruck.db";

    private static final String TABLE_NAME = "enterprise";

    private static final String COLUMN_ID = "idEnterprise";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CNPJ = "cnpj";
    private static final String COLUMN_ADRESS = "adress";
    private static final String COLUMN_PHONE = "phone" ;
    private static final String COLUMN_ID_USER = "userId" ;

    SQLiteDatabase bd;

    private static final String TABLE_CREATE =
            "CREATE TABLE enterprise ("+
                    "idEnterprise integer not null, " +
                    "name text not null, "+
                    "cnpj text not null unique, "+
                    "adress text not null, "+
                    "phone text not null, "+
                    "userId text not null, "+
                    "FOREIGN KEY (userId) REFERENCES user(idUser), "+
                    "PRIMARY KEY(idEnterprise))";

    public EnterpriseDAO(Context context){
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

    public void insert(Enterprise e){
        bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, e.getName());
        values.put(COLUMN_CNPJ, e.getCpnj());
        values.put(COLUMN_ADRESS, e.getAdress());
        values.put(COLUMN_PHONE, e.getPhone());
        values.put(COLUMN_ID_USER, e.getIdUser());

        bd.insert(TABLE_NAME, null, values);
        bd.close();
    }


    public ArrayList<Enterprise> selectAllEnterprises(){
        bd=this.getReadableDatabase();
        String query ="SELECT name FROM "+TABLE_NAME;
        ArrayList<Enterprise> list= new ArrayList<Enterprise>();
        Cursor cursor = bd.rawQuery(query, null);
        String name;

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                name = cursor.getString(0);
                Enterprise e = new Enterprise(name);
                list.add(e);
            }
        }
        return list;
    }
}
