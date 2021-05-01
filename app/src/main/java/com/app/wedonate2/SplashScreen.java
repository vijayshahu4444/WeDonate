package com.app.wedonate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    TextView appname;
    LottieAnimationView lottie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        appname = findViewById(R.id.aapName);
        lottie = findViewById(R.id.lottie);


        appname.animate().translationY(-1400).setDuration(3000).setDuration(2100);
        lottie.animate().translationX(3000).setDuration(3000).setDuration(3900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(),Login_form.class);
                startActivity(intent);
                finish();

            }
        },4000);




    }
}