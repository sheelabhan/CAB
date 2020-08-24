package com.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import android.widget.Button;
import android.widget.TextView;


public class PalindromeActivity extends AppCompatActivity {
    private EditText etNo;
    private Button btnCalculate;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palindrome);
        //binding
        etNo = findViewById(R.id.etNo);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etNo.getText()))
                {
                    etNo.setError("Please enter no");
                    etNo.requestFocus();
                    return;
                }
                int no, result;
                no = Integer.parseInt(etNo.getText().toString());
               Palindrome palindrome= new Palindrome();
               result= palindrome.reverse(no);
              if(result ==no)
              {
                 tvResult.setText(Integer.toString(no)+ ": it is palindrome" );

              }
              else{
                  tvResult.setText( Integer.toString(no)+ ": it is  not palindrome");
              }

            }
        });

    }
}
