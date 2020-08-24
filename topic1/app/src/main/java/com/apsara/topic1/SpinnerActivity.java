package com.apsara.topic1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class SpinnerActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
 private Spinner spincountry;
 private TextView tvDOB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        tvDOB=findViewById(R.id.tvDOB);
        tvDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();
            }
        });

        spincountry=findViewById(R.id.spincountry);

        String countries[]={"Nepal","India","China","UK","USA"};
        ArrayAdapter adapter= new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                countries
        );

        spincountry.setAdapter(adapter);


        ArrayAdapter setadapter= new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                countries
        );

        spincountry.setAdapter(adapter);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date="Month/Day/Year : "+ month +"/"+ dayOfMonth +"/"+year;
        tvDOB.setText(date);

    }

    private void loadDatePicker(){
        final Calendar c= Calendar.getInstance().getInstance();
        int year= c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day =c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(
                this, this, year,month,day
        );
        datePickerDialog.show();
    }
}
