package com.example.salonsd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class customerLogin extends AppCompatActivity {
    private EditText userNameET;
    private EditText passwordET;
    private Button loginBtn;
    private Button registerBtn;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        userNameET = (EditText) findViewById(R.id.txtUserName);
        passwordET = (EditText) findViewById(R.id.txtPassword);
        loginBtn = (Button) findViewById(R.id.btnLogin);
        registerBtn = (Button) findViewById(R.id.btnRegister);
        dbHelper = new DBHelper(this);
        dbHelper.openDB();

    }

    public void onLoginRegister (View view){
        Intent intent = new Intent(this, customerRegister.class);
        startActivity(intent);
    }

    public void onLogin(View view) {
        String username = userNameET.getText().toString();
        String password = passwordET.getText().toString();

        if(username.equals("") || password.equals("")){

            Toast.makeText(customerLogin.this, "Error! Fields Can't Be Blank!",Toast.LENGTH_LONG).show();
        }
        else{

            Boolean result = dbHelper.checkForCustomer(username,password);

            if(result==true){

                Toast.makeText(getApplicationContext(), "Successful! Login Granted",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this,customerAppointment.class);
                intent.putExtra("username",username);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Error! Please Enter Valid Username & Password!", Toast.LENGTH_LONG).show();
            }
        }
    }
}