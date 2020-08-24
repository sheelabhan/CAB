package com.apsara.topic1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.apsara.topic1.Calculate;
import com.apsara.topic1.R;

public class MainActivity extends AppCompatActivity {
    Button calculate;
    EditText num1,num2;
    RadioButton rdAdd, rdSub,rdMul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1=(EditText) findViewById(R.id.etFirst);
        num2=(EditText) findViewById(R.id.etSecond);
        rdAdd=(RadioButton) findViewById(R.id.rdAdd);
        rdSub=(RadioButton) findViewById(R.id.rdSub);
        rdMul=(RadioButton) findViewById(R.id.rdMul);
        calculate=(Button) findViewById(R.id.btnCalculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int first,second,result;
                first=Integer.parseInt(num1.getText().toString());
                second=Integer.parseInt(num2.getText().toString());
                if(rdAdd.isChecked()){
                    Calculate cal=new Calculate(first,second);
                    result=cal.add();
                    Toast.makeText(MainActivity.this, "total sum is"+result, Toast.LENGTH_LONG).show();
                }else if  (rdSub.isChecked()){
                    Calculate cal=new Calculate(first,second);
                    result=cal.sub();
                    Toast.makeText(MainActivity.this, "total subtraction is"+result, Toast.LENGTH_LONG).show();


                }
                }

        });
    }
}
