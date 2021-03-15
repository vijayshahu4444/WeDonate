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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_form extends AppCompatActivity {
    //Variables
       EditText fname, lname,phonenum, createPass, confPass ;
       RadioButton RbtnM, RbtnF;
       CheckBox DonorAgree;
       Button btn_Submit;


      // Create Databse refrance
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        //geting the databse refrance
        reference = FirebaseDatabase.getInstance().getReference("User");

        //get all the variable from the xml sinup file
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        btn_Submit = findViewById(R.id.btn_submit);
        phonenum = findViewById(R.id.phonenum);
        createPass = findViewById(R.id.createPass);
        confPass = findViewById(R.id.confPass);
        RbtnM = findViewById(R.id.RbtnM);
        RbtnF = findViewById(R.id.RbtnF);
        DonorAgree = findViewById(R.id.DonorAgree);


        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get all the values from text feilds

                String Fname = fname.getText().toString().trim();
                String Lname = lname.getText().toString().trim();
                String number = phonenum.getText().toString().trim();
                String crpass = createPass.getText().toString().trim();
                String conpass = confPass.getText().toString().trim();



                if(TextUtils.isEmpty(number)){
                    Toast.makeText(Signup_form.this, "Mobile Number is Require", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(crpass)){
                    Toast.makeText(Signup_form.this, "Password is require", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(crpass.length() < 6){
                    Toast.makeText(Signup_form.this, "Password Must Six character Long", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!crpass.contentEquals(conpass))
                {
                    Toast.makeText(Signup_form.this, "Password not Mach", Toast.LENGTH_SHORT).show();
                    return;
                }




















               /* //calling a root node is firebase and refrance is databse refrance

                reference = FirebaseDatabase.getInstance().getReference("User");
                //pushing the value into database

                reference.push().setValue(Fname);
                reference.push().setValue(Lname);
                reference.push().setValue(number);
                if(!crpass.contentEquals(conpass))
                {
                    Toast.makeText(Signup_form.this, "Password not Mach", Toast.LENGTH_SHORT).show();
                }else{
                    reference.push().setValue(crpass);
                }

                Toast.makeText(Signup_form.this,"Sign up Successfully",Toast.LENGTH_LONG).show();*/

            }
        });

    }

}