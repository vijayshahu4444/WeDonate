package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Home_Page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String phone;
    String phone2;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ListView myListView;
    FirebaseDatabase database;
    DatabaseReference ref, ref2, ref3;
    ArrayList<User> list;
    Session sessionManager;
    ArrayAdapter<User> adapter;
    User user;
    ToggleButton smipleSwitch;
    TextView textView;
    Button yes, no;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

        Button button = findViewById(R.id.make_request_button);
        yes = findViewById(R.id.yestext);
        no = findViewById(R.id.notext);
        //Hook
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        phone = getIntent().getStringExtra("mobile");
        textView = findViewById(R.id.you_are);

        user = new User();
        myListView = findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Blood_Request");
        ref2 = database.getReference().child("DonarDetail");


        list = new ArrayList<User>();
        ListAdapter adapter = new ListAdapter(getApplicationContext(), 0, list);
//        adapter = new ArrayAdapter<User>(this, R.layout.user_info, R.id.userInfo,list);

        myListView.setAdapter(adapter);

        yes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(Home_Page.this);
                            builder.setTitle("Welcome");
                            builder.setMessage("Do you wish to become donar!");
                            builder.setCancelable(false);
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = (new Intent(Home_Page.this, Donar_Detail.class));
                                    intent.putExtra("mobile", phone);
                                    startActivity(intent);
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    no.setVisibility(View.VISIBLE);
                                    yes.setVisibility(View.INVISIBLE);
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        } else {


                            AlertDialog.Builder builder = new AlertDialog.Builder(Home_Page.this);
                            builder.setTitle("Welcome");
                            builder.setMessage("Do  you wants to recieve donation requests?");
                            builder.setCancelable(false);
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                sessionManager = new Session(getApplicationContext());

                                                HashMap<String, String> userDetails2 = sessionManager.getUserDetailFromSesion();
                                                String a = userDetails2.get(Session.KEY_BLOOD);
                                                String b = userDetails2.get(Session.KEY_CITY);
                                                String c = userDetails2.get(Session.KEY_DONATE);

                                                HashMap<String, Object> orderMap = new HashMap<>();
                                                orderMap.put("WillDonate", "YES");
                                                orderMap.put("Code",b+a+"YES");

                                                ref2.child(phone).updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(Home_Page.this, "You will recieve donation requests", Toast.LENGTH_SHORT).show();
                                                        textView.setText("You are a donar!");


                                                    }
                                                });


                                                no.setVisibility(View.VISIBLE);
                                                yes.setVisibility(View.INVISIBLE);


                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Home_Page.this);
                builder.setTitle("Welcome");
                builder.setMessage("Do you wish not to recieve donation request?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    yes.setVisibility(View.VISIBLE);
                                    no.setVisibility(View.INVISIBLE);
                                    ref2.child(phone).child("WillDonate").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            sessionManager = new Session(getApplicationContext());

                                            HashMap<String, String> userDetails2 = sessionManager.getUserDetailFromSesion();
                                            String a = userDetails2.get(Session.KEY_BLOOD);
                                            String b = userDetails2.get(Session.KEY_CITY);
                                            String c = userDetails2.get(Session.KEY_DONATE);
                                            HashMap<String, Object> orderMap = new HashMap<>();
                                            orderMap.put("WillDonate", "NO");
                                            orderMap.put("Code",b+a+"NO");
                                            ref2.child(phone).updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(Home_Page.this, "You will not recieve donation requests", Toast.LENGTH_SHORT).show();
                                                    textView.setText("You are not a donar! Become one");


                                                }
                                            });
                                        }
                                    });

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(Home_Page.this, "this is test2", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }

        });


        ref2.child(phone).child("WillDonate").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.getValue(String.class);
                    if (name.equals("YES")) {
                        textView.setText("You are a donar!");
                        yes.setVisibility(View.INVISIBLE);

                    } else {
                        textView.setText("You are not a donar! Become  one");
                        no.setVisibility(View.GONE);
                    }


                } else {
                    textView.setText("Do you wish to become donar?");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    System.out.println("check here" + ds);
                    user = ds.getValue(User.class);
                    list.add(user);
                    adapter.notifyDataSetChanged();
                }


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
            case R.id.payment:
                Intent intent5 = new Intent(Home_Page.this,Payment.class);
                startActivity(intent5);
                break;


        }

        //if any actinon is selected close the drower
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    smipleSwitch.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}



