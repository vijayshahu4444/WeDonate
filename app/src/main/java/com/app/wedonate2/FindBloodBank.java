package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FindBloodBank extends AppCompatActivity {
    String text;
    Button button;
    FirebaseDatabase database;
    Query query;
    DatabaseReference ref;
    ArrayList<Find_Blood_Model_Class> list2;
    ListView myListView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_blood_bank);
        Spinner spinner2 = findViewById(R.id.spinner_cityBS);
        button = findViewById(R.id.search);
        myListView2 = findViewById(R.id.listViewSearchBB);
        list2 = new ArrayList<Find_Blood_Model_Class>();
        blood_bank_adapter adapter3 = new blood_bank_adapter(getApplicationContext(), 1, list2);
        myListView2.setAdapter(adapter3);
        ref = FirebaseDatabase.getInstance().getReference("Blood_Bank");


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list2.clear();
                query = FirebaseDatabase.getInstance().getReference("Blood_Bank").orderByChild("Pune");

                ref.child(text).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        System.out.println("check here" + snapshot);
                        list2.add(snapshot.getValue(Find_Blood_Model_Class.class));
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