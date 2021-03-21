package com.app.wedonate2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Home_Page extends AppCompatActivity {
    String phone;
    String phone2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        Button button = findViewById(R.id.make_request_button);
        Toolbar toolbar =   findViewById(R.id.toolbar);

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
}