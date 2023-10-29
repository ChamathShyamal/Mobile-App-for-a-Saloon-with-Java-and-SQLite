package com.example.salonsd;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class viewAppointment extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);
        dbHelper = new DBHelper(this);//create db object
        dbHelper.openDB();

        ArrayList<Appointment> appointmentsList = dbHelper.getAllAppointment();

        for (Appointment appointment:appointmentsList) {
            TableLayout tbl = findViewById(R.id.appointmentTable);

            TableRow r1 = new TableRow(this);

            TextView tv1 = new TextView(this);
            tv1.setText(appointment.getUserName());
            r1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                               TableLayout.LayoutParams.WRAP_CONTENT));
            tv1.setWidth(300);

            TextView tv2 = new TextView(this);
            tv2.setText(String.valueOf(appointment.getDate()));
            tv2.setWidth(260);

            TextView tv3 = new TextView(this);
            tv3.setText(appointment.getTime());
            tv3.setWidth(300);

            TextView tv4 = new TextView(this);
            tv4.setText(appointment.getTimeSlot());
            tv4.setWidth(300);

            r1.addView(tv1);
            r1.addView(tv2);
            r1.addView(tv3);
            r1.addView(tv4);

            tbl.addView(r1, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}