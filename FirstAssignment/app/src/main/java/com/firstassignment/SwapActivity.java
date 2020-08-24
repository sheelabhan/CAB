package com.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SwapActivity extends AppCompatActivity {
    private EditText etFirst,etSecond;
    private Button btnSwap;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        etFirst= findViewById(R.id.etFirst);
        etSecond=findViewById(R.id.etSecond);
        btnSwap=findViewById(R.id.btnSwap);
        tvResult=findViewById(R.id.tvResult);
        btnSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etFirst.getText())) {
                    etFirst.setError("Please enter no");
                    etFirst.requestFocus();
                    return;
                } else {
                    if (TextUtils.isEmpty(etSecond.getText())) {
                        etSecond.setError("Please enter no");
                        etSecond.requestFocus();
                        return;
                    }

                }

                int First, Second, Result;
                First = Integer.parseInt(etFirst.getText().toString());
                Second = Integer.parseInt(etSecond.getText().toString());

                Swap swap= new Swap();
                swap.setA(Integer.parseInt(etFirst.getText().toString()));
                swap.setB(Integer.parseInt(etSecond.getText().toString()));
                tvResult.setText("Before swap:" + "A = " + Integer.parseInt(etFirst.getText().toString())
                + "B = " + Integer.parseInt(etSecond.getText().toString()));
                tvResult.setText(swap.swapnumber());
            }
        });
    }
}
