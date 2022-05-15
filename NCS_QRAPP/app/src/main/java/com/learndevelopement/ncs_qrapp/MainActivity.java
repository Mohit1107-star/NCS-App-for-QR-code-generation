package com.learndevelopement.ncs_qrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    EditText qrValue;
    Button generateBtn,scanBtn;
    ImageView qrImage;
    TextView txtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrValue = findViewById(R.id.qrInput);
        generateBtn = findViewById(R.id.generateBtn);
        scanBtn = findViewById(R.id.scanBtn);
        qrImage = findViewById(R.id.qrPlaceHolder);
        txtView = findViewById(R.id.shortUrlView);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = qrValue.getText().toString();
                if(data.isEmpty() || data =="Enter Value"){
                    qrValue.setError("Incorrect URL,Please provide valid URL");
                }
                else{
                    int smallerDimension = 100;
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, smallerDimension);
                    qrgEncoder.setColorWhite(Color.WHITE);
                    try {
                        // Getting QR-Code as Bitmap
                        Bitmap bitmap = qrgEncoder.getBitmap();
                        // Setting Bitmap to ImageView
                        qrImage.setImageBitmap(bitmap);
                        txtView.setText(data);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}