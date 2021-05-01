package com.app.wedonate2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Receipt extends AppCompatActivity {

    Button yesbtn,nobtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        yesbtn = findViewById(R.id.btn_yes);
        nobtn = findViewById(R.id.btn_no);

        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printPDF();
            }
        });
    }

    private void printPDF() {

        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(250,350,1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        paint.setTextSize(15.5f);
        paint.setColor(Color.rgb(0,50,250));
        canvas.drawText("WeDonate",20,20,paint);

        paint.setTextSize(8.5f);
        paint.setColor(Color.rgb(0,50,250));
        canvas.drawText("Certified that ",20,40,paint);
        canvas.drawText("These Reciept ",20,55,paint);


    }
}