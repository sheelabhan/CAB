package com.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
private EditText etFirst,etSecond;
private Button btnCalculate;
private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding
        etFirst=findViewById(R.id.etFirst);
        etSecond=findViewById(R.id.etSecond);
        btnCalculate=findViewById(R.id.btnCalculate);
        tvResult=findViewById(R.id.tvResult);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            //validation first and second no
            public void onClick(View v) {
                //validation first no and second no
                if(TextUtils.isEmpty(etFirst.getText()))
                {
                    etFirst.setError("Please enter first no");
                    etFirst.requestFocus();
                    return;

                }
               else  if(TextUtils.isEmpty(etSecond.getText())) {
                    etSecond.setError("Please enter second no");
                    etSecond.requestFocus();
                    return;

                }
                int first,second, result;
                first=Integer.parseInt(etFirst.getText().toString());
                second=Integer.parseInt(etSecond.getText().toString());

                Arthematic arthematic= new Arthematic(first,second);
                result=arthematic.multiply();
                tvResult.setText(Integer.toString(result));
            }
        });
    }
}
