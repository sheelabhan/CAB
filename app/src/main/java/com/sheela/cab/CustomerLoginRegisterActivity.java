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
import com.google.firebase.database.DatabaseReference;

public class CustomerLoginRegisterActivity extends AppCompatActivity {
   private Button CustomerLoginButton, CustomerRegisterButton;
   private TextView CustomerRegisterLink, CustomerStatus;
    private EditText EmailCustomer, PasswordCustomer;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private DatabaseReference CustomerDatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);
        mAuth=FirebaseAuth.getInstance();
        CustomerLoginButton= findViewById(R.id.customer_login_btn);
        CustomerRegisterButton=findViewById(R.id.customer_register_btn);
        CustomerRegisterLink=findViewById(R.id.register_customer_link);
        CustomerStatus=findViewById(R.id.customer_status);
        EmailCustomer=findViewById(R.id.email_customer);
        PasswordCustomer=findViewById(R.id.password_customer);
        loadingBar= new ProgressDialog(this);
        CustomerRegisterButton.setVisibility(View.INVISIBLE);
        CustomerRegisterButton.setEnabled(false);
        CustomerRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerLoginButton.setVisibility( view.INVISIBLE);
                CustomerRegisterLink.setVisibility(view.INVISIBLE);
                CustomerStatus.setText("Register Customer");
                CustomerRegisterButton.setVisibility(view.VISIBLE);
                CustomerRegisterButton.setEnabled(true);
            }
        });
       CustomerRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= EmailCustomer.getText().toString();
                String password= PasswordCustomer.getText().toString();
                RegisterCustomer(email,password);
            }
        });
       CustomerLoginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String email= EmailCustomer.getText().toString();
               String password= PasswordCustomer.getText().toString();
               SignInCustomer(email,password);

           }
       });
    }

    private void SignInCustomer(String email, String password) {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write email...",
                    Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write password...",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Customer Login");
            loadingBar.setMessage("Please wait, while we are checking your credentials...");
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Login Successfully", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                        Intent intent = new Intent (CustomerLoginRegisterActivity.this, CustomersMapActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(CustomerLoginRegisterActivity.this,
                                "Login is unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }
//register
    private void RegisterCustomer(String email, String password) {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write email...",
                    Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write password...",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Customer Registeration");
            loadingBar.setMessage("Please wait, while we are register your data...");
            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Register Successfully", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                    }
                    else{
                        Toast.makeText(CustomerLoginRegisterActivity.this,
                                "Register is unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }
}