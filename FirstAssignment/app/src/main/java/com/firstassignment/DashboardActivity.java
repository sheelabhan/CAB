package com.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {
    Button btnArea;
    Button btnPalindrome;
    Button btnReverse;
    Button btnSwap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnArea=findViewById(R.id.btnArea);
        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (DashboardActivity.this, CircleActivity.class);
                startActivity(intent);
            }
        });
        btnSwap=findViewById(R.id.btnSwap);
        btnSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (DashboardActivity.this, SwapActivity.class);
                startActivity(intent);
            }
        });
        btnPalindrome=findViewById(R.id.btnPalindrome);
        btnPalindrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (DashboardActivity.this, PalindromeActivity.class);
                startActivity(intent);
            }

        });
        btnReverse=findViewById(R.id.btnReverse);
        btnReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (DashboardActivity.this, ReverseActivity.class);
                startActivity(intent);
            }
        });



    }
}
