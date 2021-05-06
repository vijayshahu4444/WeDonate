package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Blood_Bank extends AppCompatActivity {

    Button button;
    ArrayList<Blood_Pakcage_Helper> list2;
    ListView myListView2;
    DatabaseReference ref;
    Blood_Pakcage_Helper user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);
        button = findViewById(R.id.btnUpdate);
        myListView2 = findViewById(R.id.listViewBloodBanck);
        list2 = new ArrayList<Blood_Pakcage_Helper>();
        blood_package_adapter adapter3 = new blood_package_adapter(getApplicationContext(), 1, list2);
        myListView2.setAdapter(adapter3);
        ref = FirebaseDatabase.getInstance().getReference("Storage");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = (new Intent(Blood_Bank.this,BBUpdate.class));
                startActivity(intent);

            }
        });


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    System.out.println("check here" + ds);
                    user = ds.getValue(Blood_Pakcage_Helper.class);
                    list2.add(user);
                    adapter3.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}