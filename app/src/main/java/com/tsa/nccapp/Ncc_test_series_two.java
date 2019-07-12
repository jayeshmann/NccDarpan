package com.tsa.nccapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Ncc_test_series_two extends AppCompatActivity {

    RadioGroup radio_certificate;
    RadioButton btn_certA,btn_certB,btn_certC;

    Button mcq_buy_now2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncc_test_series_two);

        radio_certificate = (RadioGroup) findViewById(R.id.radio_certificate);
        btn_certA = (RadioButton)findViewById(R.id.btn_certA);
        btn_certB = (RadioButton)findViewById(R.id.btn_certB);
        btn_certC = (RadioButton)findViewById(R.id.btn_certC);

        mcq_buy_now2 = (Button)findViewById(R.id.mcq_buy_now2);

        mcq_buy_now2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mcq2 = new Intent(Ncc_test_series_two.this,Checkout.class);
                startActivity(mcq2);
            }
        });
    }
}
