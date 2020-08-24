package com.sheela.cab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerLoginRegisterActivity extends AppCompatActivity {
   private Button CustomerLoginButton, CustomerRegisterButton;
   private TextView CustomerRegisterLink, CustomerStatus;
    private EditText EmailCustomer, PasswordCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);
        CustomerLoginButton= findViewById(R.id.customer_login_btn);
        CustomerRegisterButton=findViewById(R.id.customer_register_btn);
        CustomerRegisterLink=findViewById(R.id.register_customer_link);
        CustomerStatus=findViewById(R.id.customer_status);
        EmailCustomer=findViewById(R.id.email_customer);
        PasswordCustomer=findViewById(R.id.password_customer);
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
    }
}