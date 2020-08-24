package com.sheela.assignmentone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView txtName, txtCheckin, txtCheckout, tvAdult,
            tvChildren, tvRoom, txtTotal, txtVAT, txtGrandTotal;
    private Spinner SpinCountry, SpinRoom;

    private Button btnCalculate;
    private EditText txxAdult, txxChildren, txxRoom, etPrice;


    int turn = 0;
    int day1, day2, month1, month2, year1, year2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.txtName);
        txtCheckin = findViewById(R.id.Checkin);
        txtCheckout = findViewById(R.id.Checkout);
        tvAdult = findViewById(R.id.tvAdult);
        tvChildren = findViewById(R.id.tvChildren);
        tvRoom = findViewById(R.id.tvRoom);
        txtTotal = findViewById(R.id.txtTotal);
        txtVAT = findViewById(R.id.txtVAT);
        txtGrandTotal = findViewById(R.id.txtGrandTotal);
        etPrice = findViewById(R.id.etPrice);
        SpinCountry = findViewById(R.id.SpinCountry);
        SpinRoom = findViewById(R.id.SpinRoom);
        btnCalculate = findViewById(R.id.btnCalculate);
        txxAdult = findViewById(R.id.txxAdult);
        txxChildren = findViewById(R.id.txxChildren);
        txxRoom = findViewById(R.id.txxRoom);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(txxAdult.getText())) {
                    txxAdult.setError("Please enter number of adult.");
                    return;
                }
                if (TextUtils.isEmpty(txxChildren.getText())) {
                    txxChildren.setError("Please enter number of children.");
                    return;
                }
                if (TextUtils.isEmpty(txxRoom.getText())) {
                    txxRoom.setError("Please enter number of rooms.");
                    return;
                }
                if(year1>year2)
                {
                    Toast.makeText(getApplicationContext(),"Invalid date for checkout",Toast.LENGTH_SHORT).show();
                }
                if(year1==year2 && month1>month2)
                {
                    Toast.makeText(getApplicationContext(),"Invalid date for checkout",Toast.LENGTH_SHORT).show();
                }
                if(year1==year2 && month1==month2 && day1>day2)
                {
                    Toast.makeText(getApplicationContext(),"Invalid date for checkout",Toast.LENGTH_SHORT).show();
                }
                btnCalculate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                   int leftYear,leftMonth,leftDay   ;
                leftYear=year2-year1;
            leftMonth=month2-month1;
                leftDay=day2-day1;




            }
        });


        String countries[] = {"Nepal", "India", "UK", "US"};
        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countries);


        SpinCountry.setAdapter(adapter);
        String rooms[] = {"Deluxe", "Platinum", "Presidental"};
        ArrayAdapter adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, rooms);





        SpinRoom.setAdapter(adapter1);
        SpinRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String type=SpinRoom.getSelectedItem().toString();

                if(type=="Deluxe") {
                    etPrice.setText("2000");
                }
                else if(type=="Platinum")
                {
                    etPrice.setText("4000");
                }
                else if(type=="Presidental")
                {
                    etPrice.setText("7000");
                }






}


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                etPrice.setText("0");
            }
        });

        txtCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn = 1;
                loadDatePicker();
            }
        });
        txtCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn = 2;
                loadDatePicker();
            }
        });
    }

    private void loadDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this, year, month, day);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        String date = "Month/Day/Year: " + month + "/" + dayOfMonth + "/" + "/" + year;

        if (turn == 1) {
            day1 = dayOfMonth;
            month1 = month;
            year1 = year;
            txtCheckin.setText(date);
        }
        if (turn == 2) {
            day2 = dayOfMonth;
            month1 = month;
            year2 = year;
            txtCheckout.setText(date);
        }
    }
}
