package com.sheela.databasename;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {
    private Button btnAddWord, btnAddMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnAddWord=findViewById(R.id.btnAddWord);
        btnAddMeaning=findViewById(R.id.btnAddMeaning);
        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=  new Intent(DashboardActivity.this, AddWordActivity.class);
                startActivity(intent);
            }
        });


    }
}
