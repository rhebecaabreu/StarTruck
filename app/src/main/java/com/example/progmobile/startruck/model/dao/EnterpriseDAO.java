package com.example.progmobile.startruck.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.progmobile.startruck.model.bean.Enterprise;
import com.example.progmobile.startruck.model.bean.User;

import java.util.ArrayList;

public class EnterpriseDAO {

   // private static final int DATABASE_VERSION = 9;
  //  private static final String DATABASE_NAME = "StarTruck.db";

    private static final String TABLE_NAME = "enterprise";

    private static final String COLUMN_ID = "idEnterprise";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CNPJ = "cnpj";
    private static final String COLUMN_ADRESS = "adress";
    private static final String COLUMN_PHONE = "phone" ;
    private static final String COLUMN_ID_USER = "userId" ;

    SQLiteDatabase db, db2;

    public static final String TABLE_CREATE_ENTERPRISE =
            "CREATE TABLE enterprise ("+
                    "idEnterprise integer not null, " +
                    "name text not null, "+
                    "cnpj text not null unique, "+
                    "adress text not null, "+
                    "phone text not null, "+
                    "PRIMARY KEY(idEnterprise))";

    public static final String TABLE_DROP_ENTERPRISE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    private static EnterpriseDAO instance;

    private static EnterpriseDAO getInstance(Context context){
        if(instance == null)
            instance = new EnterpriseDAO(context);
        return instance;
    }

    public EnterpriseDAO(Context context){
        BDHelper bdHelper = BDHelper.getInstance(context);
        db = bdHelper.getWritableDatabase();
        db2 = bdHelper.getReadableDatabase();
    }


    public void insert(Enterprise e){
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, e.getName());
        values.put(COLUMN_CNPJ, e.getCpnj());
        values.put(COLUMN_ADRESS, e.getAdress());
        values.put(COLUMN_PHONE, e.getPhone());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public ArrayList<Enterprise> selectAllEnterprises(){
        String query ="SELECT name FROM "+TABLE_NAME;
        ArrayList<Enterprise> list= new ArrayList<Enterprise>();
        Cursor cursor = db2.rawQuery(query, null);
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
