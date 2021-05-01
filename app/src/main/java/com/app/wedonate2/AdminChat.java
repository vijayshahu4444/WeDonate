package com.app.wedonate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminChat extends AppCompatActivity {

    Button button;
    EditText user,pass;

    String text = "admin";
    String text2 = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_chat);

        user= findViewById(R.id.adminUser2);
        pass = findViewById(R.id.adminPass2);
        button = findViewById(R.id.loginBTN2);

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
                    Toast.makeText(AdminChat.this,"Login Successfull",Toast.LENGTH_LONG);
                    Intent intent = new Intent(AdminChat.this,Chat.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}