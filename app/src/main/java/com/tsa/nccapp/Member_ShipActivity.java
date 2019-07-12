package com.tsa.nccapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Member_ShipActivity extends AppCompatActivity {
    ImageView image_membership;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member__ship);

        image_membership = (ImageView)findViewById(R.id.image_membership);


    }
}
