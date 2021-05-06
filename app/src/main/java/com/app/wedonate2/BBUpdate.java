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

public class BBUpdate extends AppCompatActivity {

    private static String text, text2;
    Button btn_insert, btn_delete;
    DatabaseReference reference;
    String child;

    DatabaseReference dbr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_b_update);
        btn_insert = findViewById(R.id.btnInsert);
        btn_delete = findViewById(R.id.btnDelete);
        reference = FirebaseDatabase.getInstance().getReference();
        dbr = FirebaseDatabase.getInstance().getReference("Storage");

        Spinner spinner = findViewById(R.id.spinner_bloodgrpBB);
        Spinner spinner2 = findViewById(R.id.spinner_packets);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Blood, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.packets, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                text = parent.getItemAtPosition(position).toString();
                if (text.equals("A +")) {
                    child = "B_a_P";
                } else if (text.equals("A -")) {
                    child = "B_a_N";
                }else if(text.equals("B +")){
                    child = "B_b_P";
                }else if(text.equals("B -")){
                    child = "B_b_N";
                }else if(text.equals("AB +")){
                    child = "B_ab_P";
                }else if(text.equals("AB -")){
                    child = "B_ab_N";
                }else if(text.equals("O +")){
                    child = "B_o_P";
                }else if(text.equals("O -")){
                    child = "B_o_N";
                }
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



        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.child("Storage").child(child).exists()) {
                            HashMap<String, Object> userdatamap = new HashMap<>();
                            userdatamap.put("Blood_Group", text);
                            userdatamap.put("Packets", text2);
                            reference.child("Storage").child(child).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(BBUpdate.this, Blood_Bank.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }else{
                            reference.child("Storage").child(child).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                    String  a = (snapshot2.child("Packets").getValue().toString());

                                    int b = Integer.parseInt(a);
                                    int c = Integer.parseInt(text2);
                                    int d=b+c;
                                    String e = String.valueOf(d);

                                    HashMap<String, Object> userdatamap = new HashMap<>();
                                    userdatamap.put("Blood_Group", text);
                                    userdatamap.put("Packets", e);
                                    reference.child("Storage").child(child).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(BBUpdate.this, "Value added Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(BBUpdate.this, "Failed to register", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child("Storage").child(child).exists()){
                        reference.child("Storage").child(child).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    String  a = (snapshot.child("Packets").getValue().toString());
                                    int d = 0;
                                    int b = Integer.parseInt(a);
                                    int c = Integer.parseInt(text2);
                                    if(b>=c){
                                        d=b-c;
                                        String e = String.valueOf(d);

                                        HashMap<String, Object> userdatamap = new HashMap<>();
                                        userdatamap.put("Blood_Group", text);
                                        userdatamap.put("Packets", e);
                                        reference.child("Storage").child(child).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(BBUpdate.this, "Value subtracted Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }else{
                                        Toast.makeText(BBUpdate.this, "Theres not enough packets to delete, please choose a lesser value", Toast.LENGTH_SHORT).show();
                                    }


                                }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        }else {
                            Toast.makeText(BBUpdate.this, "You dont have data in database, Please insert first", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}