package com.example.salonsd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class adminAppointment extends AppCompatActivity {

    Button btnDate;
    Button btnTime;
    EditText tvDate;
    EditText tvTime;
    EditText txtAdminUsername;
    int year,month, day;
    int hour, minute;
    DBHelper dbHelper= new DBHelper(this);
    String [] timeSlot={"9.00-9.30","9.30-10.00","10.00-10.30","10.30-11.00","11.00-11.30","11.30-12.00",
            "13.00-13.30","13.30-14.00","14.00-14.30","15.30-16.00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_appointment);

        txtAdminUsername = (EditText) findViewById(R.id.txtAdminUsername);
        btnDate = (Button) findViewById(R.id.btnDate);
        btnTime = (Button) findViewById(R.id.btnTime);
        tvDate = (EditText) findViewById(R.id.tvDate);
        tvTime = (EditText) findViewById(R.id.tvTime);

        Spinner sp = (Spinner) findViewById(R.id.spinnerTimeSlot);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,timeSlot);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(aa);


        //Coding for date picker
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show current date
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                //Launch date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(adminAppointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        //To display in Edit Text
                        tvDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //Coding for time picker
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Show current time
                final Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);

                //Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(adminAppointment.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        //To display in Edit Text
                        tvTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        dbHelper.openDB();
    }

    public void onAddAppointments(View view) {
        String userName = txtAdminUsername.getText().toString();
        String time = tvTime.getText().toString();
        String date = tvDate.getText().toString();

        Spinner sp = (Spinner) findViewById(R.id.spinnerTimeSlot);
        TextView tv = (TextView) sp.getSelectedView();
        String timeSlot = tv.getText().toString();

        Appointment appointment = new Appointment(userName, time, date, timeSlot);

        if (userName.equals("") || time.equals("") || date.equals("")) {

            Toast.makeText(adminAppointment.this, "Error! Fields Can't Be Blank!", Toast.LENGTH_LONG).show();
        }
        else {

            if (dbHelper.addAppointments(appointment)) {

                Toast.makeText(getApplicationContext(), "Successful! Appointment Added", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(getApplicationContext(), "Error! Failed To Add The Appointment!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onViewAppointments(View view){
        Intent intent = new Intent(this, viewAppointment.class);
        startActivity(intent);
    }

    public void onDeleteAppointment(View view){
        String username = txtAdminUsername.getText().toString();
        Boolean result1 = dbHelper.deleteAppointment(username);

        if(result1==true){
            Toast.makeText(getApplicationContext(), "Error! Failed To Delete The Appointment!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Successful! Appointment Deleted", Toast.LENGTH_LONG).show();
        }
    }

    public void onUpdateAppointment(View view){
        String username = txtAdminUsername.getText().toString();

        String time = tvTime.getText().toString();
        String date = tvDate.getText().toString();

        Spinner sp = (Spinner) findViewById(R.id.spinnerTimeSlot);
        TextView tv =(TextView) sp.getSelectedView();
        String timeSlot = tv.getText().toString();

        Appointment appointment = new Appointment(username,time, date,timeSlot);

        if (dbHelper.updateAppointments(appointment)) {
            Toast.makeText(getApplicationContext(), "Successful! Appointment Updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Error! Failed To Update The Appointment!", Toast.LENGTH_LONG).show();
        }
    }
}