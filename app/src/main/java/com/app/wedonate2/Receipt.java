package com.app.wedonate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Receipt extends AppCompatActivity {

    String name;
    Session session;
    Button yesbtn, nobtn;
    private File pdfFile;
    private String Name = null;
    private static final int PERMISSION_REQUEST_CODE = 200;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm a");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        yesbtn = findViewById(R.id.btn_yes);
        nobtn = findViewById(R.id.btn_no);
        session = new Session(getApplicationContext());
        HashMap<String, String> userDetail = session.getUserDetailFromSesion();
        name = userDetail.get(Session.KEY_NAME);


        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(name);
                ActivityCompat.requestPermissions(Receipt.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
                printPDF();
            }
        });

        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Receipt.this,"Oops you not donated the blood.!",Toast.LENGTH_LONG).show();
            }
        });


    }


    private void printPDF() {
//        File docsfolder = new File(Environment.getExternalStorageDirectory()+"/Blood_Donation");
//        if(!docsfolder.exists()){
//            docsfolder.mkdir();
//            Toast.makeText(this, "Created file path", Toast.LENGTH_SHORT).show();
//        }


        File file = new File(this.getExternalFilesDir("/"),"WeDonate_Certificate.pdf");


        String pdfname = "blooddonate"+".pdf";
        pdfFile = new File(file.getAbsolutePath(),pdfname);
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        Paint forLine = new Paint();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(250, 350, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        paint.setTextSize(15.5f);
        paint.setColor(Color.rgb(0, 50, 250));
        canvas.drawText("WeDonate", 20, 20, paint);

        paint.setTextSize(8.5f);
        paint.setColor(Color.rgb(0, 50, 250));
        canvas.drawText("Certificate of ", 20, 40, paint);
        canvas.drawText("Congratulations ", 20, 55, paint);

        forLine.setStyle(Paint.Style.STROKE);
        forLine.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));
        forLine.setStrokeWidth(2);
        canvas.drawLine(20, 65, 230, 65, forLine);


        canvas.drawText("Donor Name:" + name, 20, 80, paint);
        canvas.drawLine(20, 90, 230, 90, forLine);

        canvas.drawText("Thank you for donating blood. You have played a very important role in our mission to save lives. Is it going too far to say you are a hero? We don’t think it is! So wear your invisible cape with pride, and start perfecting your catchphrase. The world needs more heroes like you!", 20, 105, paint);

        canvas.drawLine(20, 210, 230, 210, forLine);
        canvas.drawText("Date:" + dateFormat.format(new Date().getTime()), 20, 260, paint);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(12f);
        canvas.drawText("Thank you", canvas.getWidth() / 2, 320, paint);

        pdfDocument.finishPage(page);




        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(Receipt.this, "Certificate added in your Storage", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();





    }





}