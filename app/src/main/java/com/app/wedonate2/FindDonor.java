package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FindDonor extends AppCompatActivity {
    Spinner spinner_bloodgrp;
    Spinner spinner_city;
    private static String text,text2;
    Button search_donor_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_donor);

        search_donor_btn = findViewById(R.id.search_donor_btn);



        Spinner spinner = findViewById(R.id.spinner_Bloodgrp_donor);
        Spinner spinner2 = findViewById(R.id.spinner_city_donor);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Blood, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.city, android.R.layout.simple_spinner_item);
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

        search_donor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






            }
        });


    }
}