package com.example.salonsd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper {

        private Context con;
        private SQLiteDatabase db;
        private DBConnector dbCon;
        private DBHelper dbHelper;

        DBHelper (Context con){ this.con = con; }

        public DBHelper openDB(){
            dbCon=new DBConnector(con);
            db= dbCon.getWritableDatabase();
            return this;
        }

    public boolean checkForAdmin(String username, String password){

        try {

            Cursor cursor =db.rawQuery("select * from admins where username=? and password=?",
                    new String[]{username,password});

            if (cursor.moveToFirst()){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean AdminRegister (Admin admin) {

        try {
            ContentValues cv = new ContentValues();
            cv.put("name", admin.getName());
            cv.put("username", admin.getUname());
            cv.put("password", admin.getPassword());
            cv.put("tel", admin.getTel());
            cv.put("email", admin.getEmail());

            db.insert("admins", null, cv);

            return true;
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public boolean checkAdminExists(String uname){

        try {

            Cursor cursor =db.rawQuery("select * from admins where username=?",
                    new String[]{uname});
            if (cursor.moveToFirst()){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean addAppointments (Appointment appointment) {

        try {

            ContentValues cv1 = new ContentValues();

            cv1.put("username", appointment.getUserName());
            cv1.put("date", appointment.getDate());
            cv1.put("time", appointment.getTime());
            cv1.put("timeslot", appointment.getTimeSlot());

            db.insert("appointments", null, cv1);

            return true;
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public ArrayList<Appointment> getAllAppointment(){
        ArrayList<Appointment> appointmentsList = new ArrayList<Appointment>();
        try {
            Cursor cursor =db.rawQuery("select * from appointments ",null);
            if (cursor.moveToFirst()){

                do {
                    Appointment appointment = new Appointment();
                    appointment.setUserName(cursor.getString(0));//column index is not
                    appointment.setDate(cursor.getString(1));
                    appointment.setTime(cursor.getString(2));
                    appointment.setTimeSlot(cursor.getString(3));

                    appointmentsList.add(appointment);
                }while ((cursor.moveToNext()));

            }
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();

        }
        return appointmentsList;
    }

    public boolean deleteAppointment(String username){
        //select * from users where username =<> and password =<>
        try {

            Cursor cursor =db.rawQuery("delete from appointments where username=?",
                    new String[]{username});
            if (cursor.moveToFirst()){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public boolean updateAppointments (Appointment appointment) {
        try {

            ContentValues cv2 = new ContentValues();

           cv2.put("username", appointment.getUserName());
            cv2.put("date", appointment.getDate());
            cv2.put("time", appointment.getTime());
            cv2.put("timeslot", appointment.getTimeSlot());

            db.update("appointments", cv2,"username=?",new String[]{appointment.getUserName()});

            return true;
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean finishTransactionWithBill(FinishAppointments finishAppointments) {

        try {

            ContentValues cv3 = new ContentValues();

            cv3.put("adminName", finishAppointments.getAdminsName());
            cv3.put("customerName", finishAppointments.getCustomersName());
            cv3.put("customerTelephone", finishAppointments.getCustomersTelephone());
            cv3.put("totalBill", finishAppointments.getTransactionBillTotal());
            cv3.put("time", finishAppointments.getTransactionTime());
            cv3.put("date", finishAppointments.getTransactionDate());

            db.insert("finishAppointments", null, cv3);

            return true;
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public ArrayList<FinishAppointments> getAllTransactionHistory(){
        ArrayList<FinishAppointments> transactionHistoryList = new ArrayList<FinishAppointments>();
        try {
            Cursor cursor =db.rawQuery("select * from finishAppointments ",null);
            if (cursor.moveToFirst()){

                do {
                    FinishAppointments finishAppointments = new FinishAppointments();
                    finishAppointments.setAdminsName(cursor.getString(0));
                    finishAppointments.setCustomersName(cursor.getString(1));
                    finishAppointments.setCustomersTelephone(cursor.getString(2));
                    finishAppointments.setTransactionBillTotal(cursor.getString(3));
                    finishAppointments.setTransactionDate(cursor.getString(4));
                    finishAppointments.setTransactionTime(cursor.getString(5));

                    transactionHistoryList.add(finishAppointments);

                }while ((cursor.moveToNext()));
            }

        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return transactionHistoryList;
    }

    public boolean checkForCustomer(String username, String password){

        try {

            Cursor cursor =db.rawQuery("select * from customers where username=? and password=?",
                    new String[]{username,password});
            if (cursor.moveToFirst()){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean CustomerRegister (Customer customer) {

        try {
            ContentValues cv = new ContentValues();
            cv.put("name", customer.getName());
            cv.put("username", customer.getUname());
            cv.put("password", customer.getPassword());
            cv.put("tel", customer.getTel());
            cv.put("email", customer.getEmail());

            db.insert("customers", null, cv);

            return true;
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean checkCustomerExists(String uname){

        try {

            Cursor cursor =db.rawQuery("select * from customers where username=?",
                    new String[]{uname});
            if (cursor.moveToFirst()){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }
}

