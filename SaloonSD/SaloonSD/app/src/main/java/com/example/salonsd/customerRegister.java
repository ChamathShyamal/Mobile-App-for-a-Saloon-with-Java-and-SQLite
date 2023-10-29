package com.example.salonsd;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class customerRegister extends AppCompatActivity {
    private EditText nameET;
    private EditText userNameET;
    private EditText confirmPasswordET;
    private EditText emailET;
    private EditText telET;
    private EditText passwordET;

    DBHelper dbHelper= new DBHelper(this);

    @Override
    public void onBackPressed(){

        if (checkAllFields()){
            //If all fields are empty, allow to go back
            customerRegister.super.onBackPressed();

        }else {
            //If fields are filled, it is better to get the user's confirmation by asking
            // whether to go back or not
            AlertDialog.Builder alert =new AlertDialog.Builder(customerRegister.this);
            alert.setMessage("Do You Want To Navigate Back?")
                    .setCancelable(true)
                    .setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    customerRegister.super.onBackPressed();
                                }
                            })
                    .setNegativeButton("NOPE",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(),"Alright! Continue Filling",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                    );
            AlertDialog dialog=alert.create();
            dialog.show();
        }
    }

    private boolean checkAllFields(){

        String name = nameET.getText().toString();
        String uname = userNameET.getText().toString();
        String password = passwordET.getText().toString();
        String confirmPassword = confirmPasswordET.getText().toString();
        String tel = telET.getText().toString();
        String email = emailET.getText().toString();

        if(name.equals("") && uname.equals("") && password.equals("") && confirmPassword.equals("")
                && tel.equals("") && email.equals("") ){

            return true;

        }else {

            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);

        nameET = (EditText) findViewById(R.id.txtCustomerName);
        userNameET = (EditText) findViewById(R.id.txtUserName);
        passwordET = (EditText) findViewById(R.id.txtCustomerPassword);
        confirmPasswordET  = (EditText) findViewById(R.id.txtCustomerConfirmPassword);
        emailET = (EditText) findViewById(R.id.txtCustomerEmail);
        telET = (EditText) findViewById(R.id.txtCustomerTelephone);

        dbHelper.openDB();
    }

    public void onCustomerRegister(View view) {
        String name = nameET.getText().toString();
        String uname = userNameET.getText().toString();
        String password = passwordET.getText().toString();
        String confirmPassword = confirmPasswordET.getText().toString();
        String tel = telET.getText().toString();
        String email = emailET.getText().toString();

        String emailValidation = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Customer customer = new Customer(name, uname, password, tel, email);

        if (name.equals("") && uname.equals("") && password.equals("") && confirmPassword.equals("")
                && tel.equals("") && email.equals("")) {

            Toast.makeText(customerRegister.this, "Error! Fields Can't Be Blank!", Toast.LENGTH_LONG).show();

        } else if(name.equals("")){

            Toast.makeText(customerRegister.this, "Error! Fields Can't Be Blank!", Toast.LENGTH_LONG).show();

        } else if(uname.equals("")){

            Toast.makeText(customerRegister.this, "Error! Fields Can't Be Blank!", Toast.LENGTH_LONG).show();

        } else if(password.equals("")){

            Toast.makeText(customerRegister.this, "Error! Fields Can't Be Blank!", Toast.LENGTH_LONG).show();

        } else if(confirmPassword.equals("")){

            Toast.makeText(customerRegister.this, "Error! Fields Can't Be Blank!", Toast.LENGTH_LONG).show();

        } else if(tel.equals("")){

            Toast.makeText(customerRegister.this, "Error! Fields Can't Be Blank!", Toast.LENGTH_LONG).show();

        } else if(email.equals("")){

            Toast.makeText(customerRegister.this, "Error! Fields Can't Be Blank!", Toast.LENGTH_LONG).show();

        } else if (!password.equals(confirmPassword)){

            Toast.makeText(customerRegister.this, "Error! Password & Confirm Password Should Match!", Toast.LENGTH_LONG).show();

        } else if(!email.matches(emailValidation)){

            Toast.makeText(customerRegister.this, "Error! Invalid Email Format!", Toast.LENGTH_LONG).show();

        } else {

            //call dbHelper checkAdminExists function to check user entered username exists or not
            Boolean result = dbHelper.checkCustomerExists(uname);

            //call dbHelper CustomerRegister function to insert data
            if (result==true) {

                Toast.makeText(getApplicationContext(), "Error! Entered Username Already Exists! Try Another!",Toast.LENGTH_LONG).show();

            }else if (dbHelper.CustomerRegister(customer)) {

                Toast.makeText(getApplicationContext(), "Successful! User Account Created", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(getApplicationContext(), "Error! Failed To Create The Account! Try Again!", Toast.LENGTH_LONG).show();
            }
        }
    }
}