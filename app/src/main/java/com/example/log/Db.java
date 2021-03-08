package com.example.log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.Spinner;

import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {

    public static final String DbName= "Login.db";

    public Db( Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDb) {
        MyDb.execSQL("create Table users(username TEXT primary key, password TEXT)");
        //String qry = "create table users {id integer primary key autoincrement, username text, password text}";
        //MyDb.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDb, int oldVersion, int newVersion) {
         MyDb.execSQL("drop Table if exists users");
         //onCreate(MyDb);
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long results = MyDb.insert("users", null, contentValues);
        if (results == -1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("Select * from users where username = ?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }




}
