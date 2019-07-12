package com.tsa.nccapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ncctestseriesone extends AppCompatActivity {

    Button mcq_buy_now1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncctestseriesone);

        mcq_buy_now1 = (Button)findViewById(R.id.mcq_buy_now1 );
        mcq_buy_now1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mcq = new Intent(ncctestseriesone.this,Checkout.class);
                startActivity(mcq);

            }
        });


    }
}
