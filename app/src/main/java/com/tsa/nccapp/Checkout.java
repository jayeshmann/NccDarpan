package com.tsa.nccapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import com.tsa.nccapp.adapter.CheckoutAdapter;

public class Checkout extends AppCompatActivity {

    RecyclerView recycler_test_series;
    TextView text_one, text_test, text_series, text_rupees;
    TextView text_subtotal, total_amt, text_tax, text_total_tax, text_total_amt, text_eighty;
    CheckoutAdapter checkoutAdapter;
    LinearLayoutManager layoutManager;
    Button btn_checkout;
    RadioGroup radio_certificate;
    RadioButton btn_certA, btn_certB, btn_certC;
    int welcome = 0;
    int Ncc = 1;
    int Chapter = 2;
    int Solved = 3;
    ImageView header_back;


        @Override
         protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        recycler_test_series = (RecyclerView) findViewById(R.id.recycler_test_series);

        text_one = (TextView) findViewById(R.id.text_one);
//        text_ncctext_ncc = (TextView)findViewById(R.id.text_ncctext_ncc);
        text_test = (TextView) findViewById(R.id.text_test);
        text_series = (TextView) findViewById(R.id.text_series);

        text_rupees = (TextView) findViewById(R.id.text_rupees);
        text_subtotal = (TextView) findViewById(R.id.text_subtotal);
        total_amt = (TextView) findViewById(R.id.total_amt);
        text_tax = (TextView) findViewById(R.id.text_tax);
        text_total_tax = (TextView) findViewById(R.id.text_total_tax);
        text_total_amt = (TextView) findViewById(R.id.text_total_amt);
        text_eighty = (TextView) findViewById(R.id.text_eighty);

        btn_checkout = (Button) findViewById(R.id.btn_checkout);
//        radio_certificate = (RadioGroup) findViewById(R.id.radio_certificate);
//        btn_certA = (RadioButton) findViewById(R.id.btn_certA);
//        btn_certB = (RadioButton) findViewById(R.id.btn_certB);
//        btn_certC = (RadioButton) findViewById(R.id.btn_certC);

        header_back = (ImageView) findViewById(R.id.header_back);
        if (welcome == 0) {
            header_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent welcome = new Intent(Checkout.this, Product.class);
                    startActivity(welcome);
                }
            });

        } else if (Ncc == 1) {
            header_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ncc = new Intent(Checkout.this, Product.class);
                    startActivity(ncc);
                }
            });
        } else if (Chapter == 2) {
            header_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent chapter = new Intent(Checkout.this, Product.class);
                    startActivity(chapter);
                }
            });
        } else if (Solved == 3) {
            header_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent chapter = new Intent(Checkout.this, Product.class);
                    startActivity(chapter);
                }
            });
        }


        layoutManager = new LinearLayoutManager(this);
        recycler_test_series.setLayoutManager(layoutManager);
        checkoutAdapter = new CheckoutAdapter(Checkout.this);

        recycler_test_series.setAdapter(checkoutAdapter);
        recycler_test_series.setFocusable(false);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (radio_certificate.equals("")) {
//                    Toast.makeText(Checkout.this, "Please select Certificate", Toast.LENGTH_SHORT).show();
//                }

                Intent i = new Intent(Checkout.this,View_product_Activity.class);
                               startActivity(i);
                               finish();



            }
        });
    }
}

