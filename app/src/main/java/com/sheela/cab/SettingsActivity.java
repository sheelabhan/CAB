package com.sheela.cab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {
    private String getType;
  private CircleImageView profileImageView;
  private EditText nameEditText, phoneEditText, drivercarName;
    ImageView closeButton, saveButton;
    private TextView profileChangeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getType= getIntent().getStringExtra("type");
        Toast.makeText(this, getType, Toast.LENGTH_SHORT).show();
        profileImageView=findViewById(R.id.profile_image);
        nameEditText=findViewById(R.id.name);
        phoneEditText=findViewById(R.id.phone_number);
        drivercarName=findViewById(R.id.driver_car_name);
        closeButton=findViewById(R.id.close_button);
        saveButton=findViewById(R.id.save_button);
        profileChangeBtn=findViewById(R.id.change_picture_btn);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getType.equals("Drivers"))
                {
                   startActivity( new Intent(SettingsActivity.this, DriversMapActivity.class));
                }
                else{
                    startActivity( new Intent(SettingsActivity.this, CustomersMapActivity.class));
                }
            }
        });
    }
}