package com.example.salonsd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class veryFirstHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_very_first_home);
    }
    public void onAdminBtn(View view){
        Intent intent = new Intent(this,adminLogin.class);
        startActivity(intent);
    }
    public void onCustomerBtn(View view){
        Intent intent = new Intent(this,customerLogin.class);
        startActivity(intent);
    }
}