package com.indian.digitalservices.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.indian.digitalservices.R;
import com.indian.digitalservices.Utils;
import com.jgabrielfreitas.core.BlurImageView;

public class SelectOptionActivity extends AppCompatActivity {

    BlurImageView blurImageView;
    LinearLayout linear_Aadhar , linear_PassPort , linear_Pan , linear_Driving , linear_Voter , linear_Rasan ,linear_Bus , linear_Flight,
    linear_Railways , linear_IncomeTax , linear_GST , linear_Police , linear_News;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.darkenStatusBar(this, R.color.green);
        setContentView(R.layout.activity_select_option);

        blurImageView = (BlurImageView)findViewById(R.id.BlurImageView);

        linear_Aadhar = findViewById(R.id.linear_adhar);
        linear_PassPort = findViewById(R.id.linear_passport);
        linear_Pan = findViewById(R.id.linear_pan);
        linear_Driving = findViewById(R.id.linear_driving);
        linear_Voter = findViewById(R.id.linear_voter);
        linear_Rasan = findViewById(R.id.linear_rasan);
        linear_Bus = findViewById(R.id.linear_bus);
        linear_Flight = findViewById(R.id.linear_flight);
        linear_Railways = findViewById(R.id.linear_train);
        linear_IncomeTax = findViewById(R.id.linear_incometax);
        linear_GST = findViewById(R.id.linear_gst);
        linear_Police = findViewById(R.id.linear_police);
        linear_News = findViewById(R.id.linear_news);

        blurImageView.setBlur(2);

        linear_Aadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://uidai.gov.in");
                startActivity(i);
            }
        });

        linear_PassPort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://portal2.passportindia.gov.in/AppOnlineProject/welcomeLink#");
                startActivity(i);
            }
        });

        linear_Pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://www.onlineservices.nsdl.com/paam/endUserRegisterContact.html");
                startActivity(i);
            }
        });

        linear_Driving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://sarathi.parivahan.gov.in/sarathiservice/stateSelection.do");
                startActivity(i);
            }
        });

        linear_Voter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://www.nvsp.in/");
                startActivity(i);
            }
        });

        linear_Rasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://fcs.up.gov.in/FoodPortal-en.aspx");
                startActivity(i);
            }
        });

        linear_Bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://www.upsrtconline.co.in/#");
                startActivity(i);
            }
        });

        linear_Flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://www.air.irctc.co.in/");
                startActivity(i);
            }
        });

        linear_Railways.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://www.irctc.co.in/nget/train-search");
                startActivity(i);
            }
        });

        linear_IncomeTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://www.incometaxindia.gov.in");
                startActivity(i);
            }
        });

        linear_GST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://www.gst.gov.in/");
                startActivity(i);
            }
        });

        linear_Police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,MainActivity.class);
                i.putExtra("url" , "https://uppolice.gov.in/");
                startActivity(i);
            }
        });

        linear_News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectOptionActivity.this,NewsFeeds.class);
                startActivity(i);
            }
        });
    }
}
