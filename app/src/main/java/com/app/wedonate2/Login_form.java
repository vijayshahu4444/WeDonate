package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_form extends AppCompatActivity {
    //Variables
    EditText mphonne,mPassword ;
    Button mLogin_btn;
    ProgressBar progressBar;
    FirebaseAuth fauth;
    DatabaseReference reference;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        mphonne = findViewById(R.id.l_phone);
        mPassword = findViewById(R.id.l_pass);
        mLogin_btn = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);
        Button b_reg = findViewById(R.id.btn_register);
        fauth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mphonne.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(phone)){

                    mphonne.setError("Email is require");
                    return;
                }
                else if(TextUtils.isEmpty(password)){

                    mPassword.setError("Password is Require");
                    return;
                }
                else {


                    progressBar.setVisibility(View.VISIBLE);
                    //Authenticate User
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.child("Users").child(phone).exists()){
                                UserRegister userRegister = snapshot.child("Users").child(phone).getValue(UserRegister.class);

                                if(userRegister.getPhone().equals(phone)){
                                    if(userRegister.getPasswod().equals(password)){
                                        Toast.makeText(Login_form.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login_form.this,Home_Page.class));
                                        finish();
                                    }else{
                                        Toast.makeText(Login_form.this, "Credential mismatch", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                }
                            }else{
                                Toast.makeText(Login_form.this, "mobile no is not reigistered", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Login_form.this, "database error", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });


        b_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_form.this,Signup_form.class));
            }
        });
    }


}