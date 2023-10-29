package com.example.salonsd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class viewFinishedAppointmentsHistory extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_finished_appointments_history);

        dbHelper = new DBHelper(this);
        dbHelper.openDB();

        ArrayList<FinishAppointments> transactionHistoryList = dbHelper.getAllTransactionHistory();

        for (FinishAppointments finishAppointments : transactionHistoryList) {

            TableLayout tbl = findViewById(R.id.transactionTable);

            TableRow r1 = new TableRow(this);

            TextView tv1 = new TextView(this);
            tv1.setText(finishAppointments.getAdminsName());
            r1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            tv1.setWidth(125);

            TextView tv2 = new TextView(this);
            tv2.setText(finishAppointments.getCustomersName());
            tv2.setWidth(210);

            TextView tv3 = new TextView(this);
            tv3.setText(finishAppointments.getCustomersTelephone());
            tv3.setWidth(230);

            TextView tv4 = new TextView(this);
            tv4.setText(finishAppointments.getTransactionBillTotal());
            tv4.setWidth(230);

            TextView tv5 = new TextView(this);
            tv5.setText(String.valueOf(finishAppointments.getTransactionDate()));
            tv5.setWidth(175);

            TextView tv6 = new TextView(this);
            tv6.setText(String.valueOf(finishAppointments.getTransactionTime()));
            tv6.setWidth(115);

            r1.addView(tv1);
            r1.addView(tv2);
            r1.addView(tv3);
            r1.addView(tv4);
            r1.addView(tv5);
            r1.addView(tv6);

            tbl.addView(r1, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}