package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class Home_Page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String phone;
    String phone2;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        Button button = findViewById(R.id.make_request_button);





        //Hook
        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView  = findViewById(R.id.nav_view);
        toolbar =   findViewById(R.id.toolbar);

        //Tool Bar
        setSupportActionBar(toolbar);

        //Navigation Drow menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //to make menu clickable
        navigationView.setNavigationItemSelectedListener(this);


        if(getIntent().getStringExtra("mobile")!= null){
             phone = getIntent().getStringExtra("mobile");

        }
        else {
             phone2 = getIntent().getStringExtra("mobile_ret");
        }



        System.out.println("Mobile "+phone);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = (new Intent(Home_Page.this,T_Make_Request.class));
                intent.putExtra("mobile",phone);
                intent.putExtra("mobile2",phone2);
                startActivity(intent);
            }
        });


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()== R.id.search_button){
                    //Open Search
                    startActivity(new Intent(Home_Page.this, SearchActivity.class));
                }

                return false;
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}



