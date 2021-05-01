package com.app.wedonate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminBB extends AppCompatActivity {
    Button button;
    EditText user,pass;

    String text = "admin";
    String text2 = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_b_b);

        user= findViewById(R.id.adminUser);
        pass = findViewById(R.id.adminPass);
        button = findViewById(R.id.loginBTN);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = user.getText().toString().trim();
                String Password = pass.getText().toString().trim();

                if (TextUtils.isEmpty(Username)) {

                    user.setError("Please Enter Username");
                    return;
                }else if (TextUtils.isEmpty(Password)){
                    pass.setError("Please Enter Password");
                    return;
                }else if (!Username.contentEquals(text)){
                    user.setError("Password Not Match");
                    pass.setError("Password Not Match");
                    return;
                }else if (!Username.contentEquals(text2)){
                    user.setError("Password Not Match");
                    pass.setError("Password Not Match");
                    return;
                }else {
                    Toast.makeText(AdminBB.this,"Login Successfull",Toast.LENGTH_LONG);
                    Intent intent = new Intent(AdminBB.this,Blood_Bank.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}