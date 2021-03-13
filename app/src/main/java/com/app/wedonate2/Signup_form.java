package com.app.wedonate2;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_form extends AppCompatActivity {
    //Variables
       EditText fname, lname,phonenum, createPass, confPass ;
       RadioButton RbtnM, RbtnF;
       CheckBox DonorAgree;
       Button btn_register;

       //Create Databse refrance
    FirebaseDatabase rootNode;
    DatabaseReference databaseRegistraion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        //geting the databse refrance

        databaseRegistraion = FirebaseDatabase.getInstance().getReference("User");


        //get all the variable from the xml sinup file
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        phonenum = (EditText) findViewById(R.id.phonenum);
        createPass = (EditText) findViewById(R.id.createPass);
        confPass = (EditText) findViewById(R.id.confPass);
        RbtnM = (RadioButton) findViewById(R.id.RbtnM);
        RbtnF = (RadioButton) findViewById(R.id.RbtnF);
        DonorAgree = (CheckBox) findViewById(R.id.DonorAgree);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //after cliccking submit it should gos to the database
                addRegister();

            }
        });


    }
    private void addRegister (){
        String Fname = fname.getText().toString().trim();
        String Lname = lname.getText().toString().trim();
        String number = phonenum.getText().toString().trim();
        String crpass = createPass.getText().toString().trim();
        String conpass = confPass.getText().toString().trim();

        if(!TextUtils.isEmpty(Fname)){
            //pushing the value into the firebase
            String id =  databaseRegistraion.push().getKey();


        }else {
            Toast.makeText(this, "Please Enter the name", Toast.LENGTH_LONG).show();
        }

        if(!TextUtils.isEmpty(Lname)){

        }else {
            Toast.makeText(this, "Please Enter the name", Toast.LENGTH_LONG).show();
        }

        if(!TextUtils.isEmpty(number)){

        }else {
            Toast.makeText(this, "Please Enter the Number", Toast.LENGTH_LONG).show();
        }

        if(!TextUtils.isEmpty(crpass) && crpass == conpass){

        }else {
            Toast.makeText(this, "Please Re-Enter the Password", Toast.LENGTH_LONG).show();
        }

    }
}