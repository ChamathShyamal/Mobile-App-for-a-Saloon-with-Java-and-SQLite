package com.example.salonsd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class adminMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
    }

    public void onCreateAdmin (View view){
        Intent intent = new Intent(this, adminRegister.class);
        startActivity(intent);
    }
    public void onAdminViewAppointment (View view){
        Intent intent = new Intent(this, viewAppointment.class);
        startActivity(intent);
    }
    public void onAdminAddAppointment (View view){
        Intent intent = new Intent(this, adminAppointment.class);
        startActivity(intent);
    }
    public void onAdminAddBill (View view) {
        Intent intent = new Intent(this, finishBillingAppointments.class);
        startActivity(intent);
    }
    public void onViewBillHistory (View view) {
        Intent intent = new Intent(this, viewFinishedAppointmentsHistory.class);
        startActivity(intent);
    }
}