package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Signup_form extends AppCompatActivity {
    //Variables
       EditText fname, lname,phonenum, createPass, confPass, email ;
       RadioButton RbtnM, RbtnF;
       CheckBox DonorAgree;
       Button btn_Submit;
       FirebaseAuth fauth;
    String text;




    // Create Databse refrance
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        //geting the databse refrance
        reference = FirebaseDatabase.getInstance().getReference();

        //get all the variable from the xml sinup file
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        btn_Submit = findViewById(R.id.btn_submit);
        email = findViewById(R.id.email);
        phonenum = findViewById(R.id.phonenum);
        createPass = findViewById(R.id.createPass);
        confPass = findViewById(R.id.confPass);
        RbtnM = findViewById(R.id.RbtnM);
        RbtnF = findViewById(R.id.RbtnF);
        DonorAgree = findViewById(R.id.DonorAgree);
        fauth = FirebaseAuth.getInstance();

        if(fauth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),Home_Page.class));
            finish();
        }


        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get all the values from text feilds


                String Fname = fname.getText().toString().trim();
                String Lname = lname.getText().toString().trim();
                String number = phonenum.getText().toString().trim();
                String crpass = createPass.getText().toString().trim();
                String conpass = confPass.getText().toString().trim();
                String Email = email.getText().toString().trim();

                if(TextUtils.isEmpty(Fname)){

                    fname.setError("Please Enter your name");
                    return;
                }
               else if(TextUtils.isEmpty(Lname)){

                    lname.setError("Please Enter your name");
                    return;
                }
                else if(TextUtils.isEmpty(Email)){

                    lname.setError("Email is require");
                    return;
                }
                else if(TextUtils.isEmpty(number)){

                    phonenum.setError("Enter your Mobile Number");
                    return;
                }
                else if(number.length() < 10){
                    createPass.setError("Enter the correct number");
                    return;
                }
                else if(TextUtils.isEmpty(crpass)){
                    createPass.setError("Please Enter the Password");
                    return;
                }
                else if(crpass.length() < 6){
                    createPass.setError("Password length should be Six digits");
                    return;
                }
                else if(!crpass.contentEquals(conpass))
                {
                    confPass.setError("Password is not Match");
                    return;
                }




              else{
                  if(DonorAgree.isChecked()){
                      text = "1";
                  }
                  else {
                          text = "0";
                      }

                    validateDetails(Fname,Lname,number,crpass);
                }



    }

    private void validateDetails(String fname, String lname, String number, String crpass) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.child("Users").child(number).exists()){
                    HashMap<String,Object>userdatamap =new HashMap<>();
                    userdatamap.put("Phone",number);
                    userdatamap.put("fname",fname);
                    userdatamap.put("lname",lname);
                    userdatamap.put("passwod",crpass);
                    userdatamap.put("donaragree",text);
                    reference.child("Users").child(number).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(Signup_form.this,Login_form.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Signup_form.this, "Failed to register", Toast.LENGTH_SHORT).show();

            }
        });

    }
        });
    }
    }















