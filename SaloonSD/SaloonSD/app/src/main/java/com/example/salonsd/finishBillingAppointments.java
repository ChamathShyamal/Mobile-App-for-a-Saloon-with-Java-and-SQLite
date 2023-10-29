package com.example.salonsd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

public class finishBillingAppointments extends AppCompatActivity {

    private EditText billAdminName;
    private EditText billCustomerName;
    private EditText billCustomerTelephone;
    private EditText billDate;
    private EditText billTime;
    Button btnBillDate;
    Button btnBillTime;
    String [] billTotal={"Baby-Rs.150","Boys-Rs.250","Men-Rs.300"};
    int year,month, day;
    int hour, minute;

    DBHelper dbHelper= new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_billing_appointments);

        billAdminName = (EditText) findViewById(R.id.txtAdminName);
        billCustomerName = (EditText) findViewById(R.id.txtCustomerBillName);
        billCustomerTelephone = (EditText) findViewById(R.id.txtCustomerBillTelephone);

        btnBillDate = (Button) findViewById(R.id.btnTransactionDate);
        btnBillTime = (Button) findViewById(R.id.btnTransactionTime);
        billDate = (EditText) findViewById(R.id.txtTransactionDate);
        billTime = (EditText) findViewById(R.id.txtTransactionTime);

        Spinner sp = (Spinner) findViewById(R.id.spinnerBillTotal);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,billTotal);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(aa);

        //Coding for date picker
        btnBillDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Show current date
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                //Launch date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(finishBillingAppointments.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        //To display in Edit Text
                        billDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //For time picker
        btnBillTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Show current time
                final Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);

                //Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(finishBillingAppointments.this,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        //To display in Edit Text
                        billTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        dbHelper.openDB();
    }

    public void onBtnFinishAppointment(View view) {

        String adminsName = billAdminName.getText().toString();
        String customersName = billCustomerName.getText().toString();
        String customersTelephone = billCustomerTelephone.getText().toString();
        String transactionTime = billDate.getText().toString();
        String transactionDate = billTime.getText().toString();

        Spinner sp = (Spinner) findViewById(R.id.spinnerBillTotal);
        TextView tv = (TextView) sp.getSelectedView();
        String transactionBillTotal = tv.getText().toString();

        FinishAppointments finishAppointments = new FinishAppointments(adminsName, customersName,
                customersTelephone, transactionTime, transactionDate, transactionBillTotal);

        if (adminsName.equals("") || customersName.equals("") || customersTelephone.equals("") ||
                transactionTime.equals("") || transactionDate.equals("")) {

            Toast.makeText(finishBillingAppointments.this, "Error! Fields Can't Be Blank!", Toast.LENGTH_LONG).show();

        } else {
            //call dbHelper finishTransactionWithBill function to insert data
            if (dbHelper.finishTransactionWithBill(finishAppointments)) {

                Toast.makeText(getApplicationContext(), "Successful! Finished Appointment With The Bill Amount", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Error! Failed To Finish The Appointment!", Toast.LENGTH_LONG).show();
            }
        }
    }
}