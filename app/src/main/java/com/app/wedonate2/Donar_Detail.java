package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Donar_Detail extends AppCompatActivity {
    SpinnerHelper helper;
    private static String text, text2;
    EditText name, mobno, address;
    Button sbmt;
    FirebaseDatabase database;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar__detail);
        name = findViewById(R.id.fname);
        mobno = findViewById(R.id.phonenum);
        String phone = getIntent().getStringExtra("mobile");
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("DonarDetail").child(phone);
        String a = getIntent().getStringExtra("sinup");

        address = findViewById(R.id.address2);
        sbmt = findViewById(R.id.submit);
        Spinner spinner = findViewById(R.id.spinner_bloodgrp);
        Spinner spinner2 = findViewById(R.id.spinner_city);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Blood, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                text = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                text2 = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        if(a.equals("111")){
            sbmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, Object> orderMap = new HashMap<>();
                    orderMap.put("Name", name.getText().toString());
                    orderMap.put("Phone", mobno.getText().toString());
                    orderMap.put("Address", address.getText().toString());
                    orderMap.put("Blood", text);
                    orderMap.put("City", text2);
                    orderMap.put("Code",text2+text+"YES");
                    orderMap.put("WillDonate","YES");
                    ref.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent intent = new Intent(Donar_Detail.this,Login_form.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }else {
            sbmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, Object> orderMap = new HashMap<>();
                    orderMap.put("Name", name.getText().toString());
                    orderMap.put("Phone", mobno.getText().toString());
                    orderMap.put("Address", address.getText().toString());
                    orderMap.put("Blood", text);
                    orderMap.put("City", text2);
                    orderMap.put("Code",text2+text+"YES");
                    orderMap.put("WillDonate","YES");
                    ref.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent intent = new Intent(Donar_Detail.this,Home_Page.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }






    }
}