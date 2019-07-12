package com.tsa.nccapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChapterWisw_QuestionActivity extends AppCompatActivity {
    Button testseries_buy_now;
    ImageView header_back;
    RadioGroup radio_certificate;
    RadioButton btn_certA, btn_certB,btn_certC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_wisw__question);
        radio_certificate = (RadioGroup) findViewById(R.id.radio_certificate);
        btn_certA = (RadioButton)findViewById(R.id.btn_certA);
        btn_certB = (RadioButton)findViewById(R.id.btn_certB);
        btn_certC = (RadioButton)findViewById(R.id.btn_certC);


        testseries_buy_now = (Button)findViewById(R.id.testseries_buy_now);
        testseries_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radio_certificate.getCheckedRadioButtonId() == -1) {

                    Toast.makeText(getApplicationContext(), "Please Select Atleast One certificate", Toast.LENGTH_LONG).show();
                } else {

                    startActivity(new Intent(ChapterWisw_QuestionActivity.this, Checkout.class));

                }
            }
        });


        header_back = (ImageView)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back =new Intent(ChapterWisw_QuestionActivity.this,Product.class);
                startActivity(back);
            }
        });

    }
}
