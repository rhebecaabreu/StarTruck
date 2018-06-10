package com.example.progmobile.startruck.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.progmobile.startruck.model.bean.User;

public class UserDAO {
   // private static final int DATABASE_VERSION = 9;
   // private static final String DATABASE_NAME = "StarTruck.db";

    private static final String TABLE_NAME = "user";

    private static final String COLUMN_ID = "idUser";
    private static final String COLUMN_FIRST_NAME = "firstName";
    private static final String COLUMN_LAST_NAME = "lastName";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_FUNCTION = "function" ;
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    private SQLiteDatabase db = null, db2;


    public static final String TABLE_DROP_USER = "DROP TABLE IF EXISTS "+TABLE_NAME;


    public static final String TABLE_CREATE_USER =
        "CREATE TABLE user ("+
                "idUser integer not null,  " +
                "firstName text not null, "+
                "lastName text not null, "+
                "email text not null, "+
                "function text not null, "+
                "username text not null unique, "+
                "password text not null, "+
                "PRIMARY KEY(idUser))";

    private static UserDAO instance;

    private static UserDAO getInstance(Context context){
       if(instance == null)
           instance = new UserDAO(context);
       return instance;
    }


    public UserDAO(Context context){
        BDHelper bdHelper = BDHelper.getInstance(context);
        db = bdHelper.getWritableDatabase();
        db2= bdHelper.getReadableDatabase();
    }



    public void insert(User u){
        ContentValues values = new ContentValues();

        values.put(COLUMN_FIRST_NAME, u.getFirstName());
        values.put(COLUMN_LAST_NAME, u.getLastName());
        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_FUNCTION, u.getFunction());
        values.put(COLUMN_USERNAME, u.getUsername());
        values.put(COLUMN_PASSWORD, u.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int searchUserID(String username){
        String query = "SELECT username, idUser FROM " + TABLE_NAME;
        Cursor cursor = db2.rawQuery(query, null);
        String a;
        int b;

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(username)) {
                    b = cursor.getInt(1);
                    return b;
                }
            } while (cursor.moveToNext());
        }
        return -1;
    }

    public String searchFullName(String username){
        String query = "SELECT username, firstName, lastName FROM " + TABLE_NAME;
        Cursor cursor = db2.rawQuery(query, null);
        String a;
        String fullName = "Nome não encontrado";

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(username)) {
                    fullName= cursor.getString(1) + " "+ cursor.getString(2);
                    return fullName;
                }
            } while (cursor.moveToNext());
        }
        return fullName;
    }


    public boolean checkUsernamePassword(String username, String password){
        String query = "SELECT username, password FROM " + TABLE_NAME;
        Cursor cursor = db2.rawQuery(query, null);
        String a, b;

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(username)) {
                    b = cursor.getString(1);
                    if(b.equals(password)){
                        return true;
                    }
                }
            } while (cursor.moveToNext());
        }
        return false;
    }

    public void update(){

    }


}

