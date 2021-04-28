package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
    EditText fname, lname, phonenum, createPass, confPass, email;
    CheckBox DonorAgree;
    Button btn_Submit;
    FirebaseAuth fauth;
    String text;
    String Gender;
    RadioButton male, female;
    RadioGroup radioGroup;
    String value;
    SpinnerHelper helper;
    private static String text2;


    String Male;
    String Female;


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
        male = findViewById(R.id.RbtnM);
        female = findViewById(R.id.RbtnF);
        DonorAgree = findViewById(R.id.DonorAgree);
        radioGroup = findViewById(R.id.radiogroup);
        helper = new SpinnerHelper();


        Spinner spinner = findViewById(R.id.spinner_bloodgrp2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Blood, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                text2 = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });








        fauth = FirebaseAuth.getInstance();

        if (fauth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Home_Page.class));
            finish();
        }


        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 value = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();


                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        Toast.makeText(Signup_form.this, value, Toast.LENGTH_SHORT).show();
                    }
                });

                //Get all the values from text feilds
                System.out.println("The group" + value);

                String Fname = fname.getText().toString().trim();
                String Lname = lname.getText().toString().trim();
                String number = phonenum.getText().toString().trim();
                String crpass = createPass.getText().toString().trim();
                String conpass = confPass.getText().toString().trim();
                String Email = email.getText().toString().trim();


                if (TextUtils.isEmpty(Fname)) {

                    fname.setError("Please Enter your name");
                    return;
                } else if (TextUtils.isEmpty(Lname)) {

                    lname.setError("Please Enter your name");
                    return;
                } else if (TextUtils.isEmpty(Email)) {

                    lname.setError("Email is require");
                    return;
                } else if (TextUtils.isEmpty(number)) {

                    phonenum.setError("Enter your Mobile Number");
                    return;
                } else if (number.length() < 10) {
                    createPass.setError("Enter the correct number");
                    return;
                } else if (TextUtils.isEmpty(crpass)) {
                    createPass.setError("Please Enter the Password");
                    return;
                } else if (crpass.length() < 6) {
                    createPass.setError("Password length should be Six digits");
                    return;
                } else if (!crpass.contentEquals(conpass)) {
                    confPass.setError("Password is not Match");
                    return;
                }  else {
                    if (DonorAgree.isChecked()) {
                        text = "1";
                    } else {
                        text = "0";
                    }

                    validateDetails(Fname, Lname, number, crpass, Email);
                }


            }

            private void validateDetails(String fname, String lname, String number, String crpass, String Email) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.child("Users").child(number).exists()) {
                            HashMap<String, Object> userdatamap = new HashMap<>();
                            userdatamap.put("Phone", number);
                            userdatamap.put("fname", fname);
                            userdatamap.put("lname", lname);
                            userdatamap.put("passwod", crpass);
                            userdatamap.put("donaragree", text);
                            userdatamap.put("email", Email);
                            userdatamap.put("Gender",value);
                            userdatamap.put("Blood_Group",text2);



                            reference.child("Users").child(number).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(Signup_form.this, Donar_Detail.class);
                                        intent.putExtra("sinup", "111");
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















