package com.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CircleActivity extends AppCompatActivity {
private EditText etNo;
private Button btnCalculate;
private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

        //binding
        etNo = findViewById(R.id.etNo);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult=findViewById(R.id.tvresult);
       btnCalculate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(TextUtils.isEmpty(etNo.getText()))
               {
                   etNo.setError("Please enter radius no");
                   etNo.requestFocus();
                   return;
               }
               int No, result;
               No= Integer.parseInt(etNo.getText().toString());
               Circle circle= new Circle(No);
               result= (22/7)*No*No;
               tvResult.setText(Integer.toString(result));
           }
       });


        }
    }











