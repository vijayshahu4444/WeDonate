package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FindDonor extends AppCompatActivity {
    Spinner spinner_bloodgrp;
    Spinner spinner_city;
    private static String text, text2;
    Button search_donor_btn;
    ListView myListView2;
    Find_Blood_Model_Class user2=new Find_Blood_Model_Class();
    FirebaseDatabase database;
    Query query;
    DatabaseReference ref, ref2;
    ArrayList<Find_Blood_Model_Class> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_donor);

        search_donor_btn = findViewById(R.id.search_donor_btn);
        myListView2 = findViewById(R.id.listview2);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("DonarDetail");
        ref2=database.getReference();
        list2 = new ArrayList<Find_Blood_Model_Class>();
        ListAdapterFindDonar adapter3 = new ListAdapterFindDonar(getApplicationContext(), 1, list2);
        myListView2.setAdapter(adapter3);



        Spinner spinner = findViewById(R.id.spinner_Bloodgrp_donor);
        Spinner spinner2 = findViewById(R.id.spinner_city_donor);
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

        search_donor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                query = FirebaseDatabase.getInstance().getReference("DonarDetail").orderByChild("Code").equalTo(text2+text+"YES");



                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Toast.makeText(FindDonor.this, "found it", Toast.LENGTH_SHORT).show();



                                      for (DataSnapshot ds : snapshot.getChildren()){
                                          System.out.println("check here"+ds);
                                          list2.add( ds.getValue(Find_Blood_Model_Class.class));
                                      }







                        adapter3.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


    }
}