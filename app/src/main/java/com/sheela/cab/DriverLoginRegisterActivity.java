package com.sheela.cab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginRegisterActivity extends AppCompatActivity {
    private Button DriverLoginButton, DriverRegisterButton;
    private TextView DriverRegisterLink, DriverStatus;
    private EditText EmailDriver, PasswordDriver;
    private FirebaseAuth  mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);
        mAuth=FirebaseAuth.getInstance();
        DriverLoginButton= findViewById(R.id.driver_login_btn);
        DriverRegisterButton=findViewById(R.id.driver_register_btn);
        DriverRegisterLink=findViewById(R.id.driver_register_link);
       DriverStatus=findViewById(R.id.driver_status);
       EmailDriver=findViewById(R.id.email_driver);
       PasswordDriver=findViewById(R.id.password_driver);
       loadingBar= new ProgressDialog(this);
        DriverRegisterButton.setVisibility(View.INVISIBLE);
        DriverRegisterButton.setEnabled(false);
        DriverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DriverLoginButton.setVisibility( view.INVISIBLE);
                DriverRegisterButton.setVisibility(view.INVISIBLE);
               DriverStatus.setText("Register Customer");
                DriverRegisterButton.setVisibility(view.VISIBLE);
                DriverRegisterButton.setEnabled(true);
            }
        });
        DriverRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= EmailDriver.getText().toString();
                String password= PasswordDriver.getText().toString();
                RegisterDriver(email,password);
            }
        });
        DriverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= EmailDriver.getText().toString();
                String password= PasswordDriver.getText().toString();
               SignInDriver( email,password);
            }
        });
    }

    private void SignInDriver(String email, String password) {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write email...",
                    Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write password...",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Driver Login");
            loadingBar.setMessage("Please wait, while we are login your credentials...");
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(DriverLoginRegisterActivity.this, " Driver Login Successfully", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                        Intent intent= new Intent( DriverLoginRegisterActivity.this, DriversMapActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(DriverLoginRegisterActivity.this,
                                "Login is unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }


    private void RegisterDriver(String email, String password) {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write email...",
                    Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write password...",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Driver Registeration");
            loadingBar.setMessage("Please wait, while we are register your data...");
            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(DriverLoginRegisterActivity.this, " Driver Register Successfully", Toast.LENGTH_SHORT).show();
                   loadingBar.dismiss();
                        Intent intent= new Intent( DriverLoginRegisterActivity.this, DriversMapActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(DriverLoginRegisterActivity.this,
                                "Register is unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }
}