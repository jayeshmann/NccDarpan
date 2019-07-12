package com.tsa.nccapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SOlved_sampleActivity extends AppCompatActivity {

     Button chapterwise_buy_now;
    ImageView header_back;

    RadioGroup radio_certificate;
    RadioButton btn_certA, btn_certB,btn_certC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solved_sample);
        radio_certificate = (RadioGroup) findViewById(R.id.radio_certificate);
        btn_certA = (RadioButton)findViewById(R.id.btn_certA);
        btn_certB = (RadioButton)findViewById(R.id.btn_certB);
        btn_certC = (RadioButton)findViewById(R.id.btn_certC);

        chapterwise_buy_now = (Button)findViewById(R.id.chapterwise_buy_now);
        chapterwise_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (radio_certificate.getCheckedRadioButtonId() == -1) {

                    Toast.makeText(getApplicationContext(), "Please Select Atleast One certificate", Toast.LENGTH_LONG).show();
                } else {

                    startActivity(new Intent(SOlved_sampleActivity.this, Checkout.class));

                }
            }
        });

        header_back = (ImageView)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back =new Intent(SOlved_sampleActivity.this,Product.class);
                startActivity(back);
            }
        });
    }
}
