package com.example.salonsd;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnector extends SQLiteOpenHelper {

    //The required database have created here
    public DBConnector(Context context){
        super(context, "salonSD_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //All required database tables are here
        //along with the columns in each database table
        db.execSQL("create table admins (name text, username text, password text, tel text, email text)");
        db.execSQL("create table customers (name text, username text, password text, tel text, email text)");
        db.execSQL("create table appointments (username text, date text, time text,timeslot text )");
        db.execSQL("create table finishAppointments (adminName text, customerName text, customerTelephone text, totalBill text, time text, date text)");

        //add default user to the admins database table
        ContentValues cv = new ContentValues();
        cv.put("name", "default name");
        cv.put("username", "admin");
        cv.put("password", "admin");
        cv.put("tel", "salonSD");
        cv.put("email", "SD@gmail.com");

        db.insert("admins", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
}


