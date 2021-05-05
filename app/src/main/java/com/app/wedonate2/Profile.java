package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Profile extends AppCompatActivity {
    DatabaseReference reference,ref2;
    FirebaseDatabase database;
    TextView fname,email_id,l_name,phone_no,cityy,addresss,blood;
    Session session;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

reference= FirebaseDatabase.getInstance().getReference("Users");
        ref2= FirebaseDatabase.getInstance().getReference("DonarDetail");
        fname = findViewById(R.id.tv_name);
        email_id = findViewById(R.id.emailid);

        phone_no = findViewById(R.id.phonen);
        cityy = findViewById(R.id.cityt);
        addresss = findViewById(R.id.tv_address);
        blood = findViewById(R.id.bloodg);
        session = new Session(getApplicationContext());
        HashMap<String, String> userDetail = session.getUserDetailFromSesion();
        phone = userDetail.get(Session.KEY_Phone);




        reference.child(phone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name= snapshot.child("fname").getValue().toString();

                String lname= snapshot.child("lname").getValue().toString();
                String email= snapshot.child("email").getValue().toString();
                String phone= snapshot.child("Phone").getValue().toString();
                fname.setText(name+" "+lname);
                email_id.setText(email);
                phone_no.setText(phone);

                ref2.child(phone).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String city = snapshot.child("City").getValue().toString();
                        String address = snapshot.child("Address").getValue().toString();
                        String bloodd = snapshot.child("Blood").getValue().toString();
                        cityy.setText(city);
                        addresss.setText(address);
                        blood.setText(bloodd);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}