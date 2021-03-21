package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class T_Make_Request extends AppCompatActivity  {
    Button submit_req;
    DatabaseReference reference;
    Spinner spinner_bloodgrp;
    Spinner spinner_city;
   public String text;
    SpinnerHelper helper;
    String phone;




    @SuppressLint({"CutPasteId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make__request);
        submit_req = findViewById(R.id.submit_req);
        helper = new SpinnerHelper();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();





        if(getIntent().getStringExtra("mobile")!= null){
            phone = getIntent().getStringExtra("mobile");

        }
        else {
            phone = getIntent().getStringExtra("mobile2");
        }






        Spinner spinner = findViewById(R.id.spinner_bloodgrp);
        Spinner spinner2 = findViewById(R.id.spinner_city);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Blood, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.city, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);














          spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              private String text;

              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 String text = parent.getItemAtPosition(position).toString();

                  submit_req.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          //push reqest into the firebase


                          reference.addListenerForSingleValueEvent(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot snapshot) {
                                  if(!snapshot.child("Blood_Request").child(phone).exists()){
                                      HashMap<String,Object> userdatamap =new HashMap<>();
                                      userdatamap.put("Blood Group",text);
                                      reference.child("Blood_Request").child(phone).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                          @Override
                                          public void onComplete(@NonNull Task<Void> task) {
                                              if(task.isSuccessful()){
                                                  Intent intent = new Intent(T_Make_Request.this,Home_Page.class);
                                                  intent.putExtra("mobile_ret",phone);
                                                  startActivity(intent);
                                              }
                                          }
                                      });
                                  }

                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError error) {
                                  Toast.makeText(T_Make_Request.this, "Failed to register", Toast.LENGTH_SHORT).show();

                              }
                          });




                      }
                  });



              }

              @Override
              public void onNothingSelected(AdapterView<?> parent) {


              }
          });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private String text;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String text = parent.getItemAtPosition(position).toString();

                submit_req.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //push reqest into the firebase


                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(!snapshot.child("Blood_Request").child(phone).exists()){
                                    HashMap<String,Object> userdatamap =new HashMap<>();
                                    userdatamap.put("Blood Group",text);
                                    reference.child("Blood_Request").child(phone).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Intent intent = new Intent(T_Make_Request.this,Home_Page.class);
                                                intent.putExtra("mobile_ret",phone);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(T_Make_Request.this, "Failed to register", Toast.LENGTH_SHORT).show();

                            }
                        });




                    }
                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


    }



}
