package com.sheela.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
  private Button tvSenssor, tvAccelerometer,tvGyroscope,tvproximity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tvSenssor=findViewById(R.id.tvSenssor);
        tvAccelerometer=findViewById(R.id.tvAccelerometer);
        tvGyroscope=findViewById(R.id.tvGyroscope);
        tvproximity=findViewById(R.id.tvproximity);
        tvSenssor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DashboardActivity.this, SensorActivity.class );
                startActivity(intent);
            }
        });
        tvAccelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DashboardActivity.this, AccelerometerSensorActivity.class );
                startActivity(intent);
            }
        });
        tvGyroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DashboardActivity.this, GyroscopeActivity.class );
                startActivity(intent);
            }
        });
        tvproximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DashboardActivity.this,ProximityActivity.class );
                startActivity(intent);
            }
        });
    }
}
