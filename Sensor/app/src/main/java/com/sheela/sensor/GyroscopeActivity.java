package com.sheela.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GyroscopeActivity extends AppCompatActivity {
   private SensorManager sensorManager;
   private TextView tvGyro;
   private EditText etFirst, etSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        setTitle("Gyroscope  Title");
        tvGyro=findViewById(R.id.tvGyro);
        etFirst=findViewById(R.id.etFirst);
        etSecond=findViewById(R.id.etSecond);
        sensorGyro();

    }
    private void sensorGyro(){
   sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor= sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SensorEventListener gyroListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1] < 0) {
                    add();
                } else if (event.values[1] > 0) {
                    sub();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor != null) {
            sensorManager.registerListener(gyroListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "no sensor found", Toast.LENGTH_SHORT).show();
        }
    }

    private void add(){
        int Sum = Integer.parseInt(etFirst.getText().toString())+Integer.parseInt(etSecond.getText().toString());
        Toast.makeText(this, "Sum is :" + Sum, Toast.LENGTH_SHORT).show();
    }

    private void sub(){
        int Sub = Integer.parseInt(etFirst.getText().toString())- Integer.parseInt(etSecond.getText().toString());
        Toast.makeText(this, "Sum is :" + Sub, Toast.LENGTH_SHORT).show();
    }
}
