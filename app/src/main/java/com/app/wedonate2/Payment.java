package com.app.wedonate2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;

public class Payment extends AppCompatActivity implements PaymentResultListener {

    Button send;
    TextView paytext;
    EditText number;
    String text;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        send = (Button) findViewById(R.id.send);
        number =findViewById(R.id.mobilePay);



        paytext = findViewById(R.id.paytext);

        //This payment method id used by the Razorpay
        Checkout.preload(getApplicationContext());

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text =number.getText().toString().trim();

                String phone = number.getText().toString().trim();


                if (TextUtils.isEmpty(phone)) {

                    number.setError("Enter your Mobile Number ");
                    return;
                }else if (phone.length() < 10) {
                    number.setError("Enter the correct number");
                    return;
                }else {

                    makepayment();
                }



            }
        });



    }

    private void makepayment() {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_4W3AzNuZZuRpdA");



        checkout.setImage(R.drawable.logo);


        final Activity activity = this;


        try {
            JSONObject options = new JSONObject();

            options.put("name", "WeDonate");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
           // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "50000");//pass amount in currency subunits 500/100 they will divide by 100 so for ammount multiply by 100 then put value
            options.put("prefill.email", "WeDonateDonor@gmail.com");
            //options.put("prefill.contact","9766029396");
            options.put("prefill.contact",text);
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        paytext.setText("Successful Payment ID:"+s);
    }

    @Override
    public void onPaymentError(int i, String s) {

        paytext.setText("Payment Fail and cause is:"+s);

    }



}