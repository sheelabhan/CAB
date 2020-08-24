package com.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReverseActivity extends AppCompatActivity {
private EditText etNo;
private Button btnReverse;
private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse);
        //binding
        etNo=findViewById(R.id.etNo);
        btnReverse= findViewById(R.id.btnReverse);
        tvResult=findViewById(R.id.tvResult);
        btnReverse.setOnClickListener(new View.OnClickListener() {
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
                Reverse reverse= new Reverse();
                result= reverse.reverse(no);
                tvResult.setText("Reverse no is" + Integer.toString(result));
            }

        });
    }
}
