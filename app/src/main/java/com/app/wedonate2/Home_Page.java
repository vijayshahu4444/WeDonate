package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home_Page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String phone;
    String phone2;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ListView myListView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<User> list;
    ArrayAdapter<User> adapter;
    User user;






    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

        Button button = findViewById(R.id.make_request_button);


        //Hook
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        user =new User();
        myListView = findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Blood_Request");


         list = new ArrayList<>();
        adapter = new ArrayAdapter<User>(this,R.layout.user_info,R.id.userInfo, list);




        myListView.setAdapter(adapter);



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds: snapshot.getChildren()){

                    user = ds.getValue(User.class);
                    list.add(user);
                }

               myListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








        //Tool Bar
        setSupportActionBar(toolbar);

        //Navigation Drow menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //to make menu clickable
        navigationView.setNavigationItemSelectedListener(this);
        //to set the default home to navigation
        navigationView.setCheckedItem(R.id.nav_home);


        if (getIntent().getStringExtra("mobile") != null) {
            phone = getIntent().getStringExtra("mobile");

        } else {
            phone2 = getIntent().getStringExtra("mobile_ret");
        }


        System.out.println("Mobile " + phone);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = (new Intent(Home_Page.this, T_Make_Request.class));
                intent.putExtra("mobile", phone);
                intent.putExtra("mobile2", phone2);
                startActivity(intent);
            }
        });


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_button) {
                    //Open Search
                    startActivity(new Intent(Home_Page.this, SearchActivity.class));
                }

                return false;
            }
        });
    }

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    public void onBackPressed() {

        // this code is not working insted of clossing drawer it closed the app
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else

            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_admin_bb:
                Intent intent = new Intent(Home_Page.this, Blood_Bank.class);
                startActivity(intent);
                break;
            case R.id.nav_admin_chat:
                Intent intent1 = new Intent(Home_Page.this, Chat.class);
                startActivity(intent1);
                break;
            case R.id.nav_logout:
                Intent intent2 = new Intent(Home_Page.this, Login_form.class);
                startActivity(intent2);
                break;
            case R.id.nav_donor_find:
                Intent intent3 = new Intent(Home_Page.this, FindDonor.class);
                startActivity(intent3);
                break;

            case R.id.nav_blood_bank:
                Intent intent4 = new Intent(Home_Page.this, FindBloodBank.class);
                startActivity(intent4);
                break;
        }

        //if any actinon is selected close the drower
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}



