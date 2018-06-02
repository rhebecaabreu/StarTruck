package com.example.progmobile.startruck.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.progmobile.startruck.model.bean.User;

public class UserDAO extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "starTruck.db";

    private static final String TABLE_NAME = "user";

    private static final String COLUMN_ID = "idUser";
    private static final String COLUMN_FIRST_NAME = "firstName";
    private static final String COLUMN_LAST_NAME = "lastName";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_FUNCTION = "function" ;
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    SQLiteDatabase bd;

    private static final String TABLE_CREATE =
        "CREATE TABLE user ("+
                "idUser integer not null, " +
                "firstName text not null, "+
                "lastName text not null, "+
                "email text not null, "+
                "function text not null, "+
                "username text not null unique, "+
                "password text not null, "+
                "PRIMARY KEY(idUser))";

    public UserDAO(Context context){
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


    public void insert(User u){
        bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FIRST_NAME, u.getFirstName());
        values.put(COLUMN_LAST_NAME, u.getLastName());
        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_FUNCTION, u.getFunction());
        values.put(COLUMN_USERNAME, u.getUsername());
        values.put(COLUMN_PASSWORD, u.getPassword());

        bd.insert(TABLE_NAME, null, values);
        bd.close();
    }

    public int searchUserID(String username){
        bd = this.getWritableDatabase();
        String query = "SELECT username, idUser FROM " + TABLE_NAME;
        Cursor cursor = bd.rawQuery(query, null);
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


    public boolean checkUsernamePassword(String username, String password){
        bd = this.getWritableDatabase();
        String query = "SELECT username, password FROM " + TABLE_NAME;
        Cursor cursor = bd.rawQuery(query, null);
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

