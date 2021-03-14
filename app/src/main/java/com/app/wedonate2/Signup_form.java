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
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        btn_Submit = findViewById(R.id.btn_submit);
        phonenum = (EditText) findViewById(R.id.phonenum);
        createPass = (EditText) findViewById(R.id.createPass);
        confPass = (EditText) findViewById(R.id.confPass);
        RbtnM = (RadioButton) findViewById(R.id.RbtnM);
        RbtnF = (RadioButton) findViewById(R.id.RbtnF);
        DonorAgree = (CheckBox) findViewById(R.id.DonorAgree);


        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //after cliccking submit it should gos to the database


                String Fname = fname.getText().toString().trim();
                String Lname = lname.getText().toString().trim();
                String number = phonenum.getText().toString().trim();
                String crpass = createPass.getText().toString().trim();
                String conpass = confPass.getText().toString().trim();

               /* if(!TextUtils.isEmpty(Fname)){
                    //pushing the value into the firebase
                    String id =  reference.push().getKey();
                    UserRegister User = new UserRegister();

                }else {
                    Toast.makeText(this, "Please Enter the name", Toast.LENGTH_LONG).show();
                }*/

                //calling a root node is firebase and refrance is databse refrance
                rootNode = FirebaseDatabase.getInstance();
                //user is the path of database
                reference = rootNode.getReference("user");
                //pushing the value into database
                reference.setValue("Database connectivity chk");




            }
        });



    }

}