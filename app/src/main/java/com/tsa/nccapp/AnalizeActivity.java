package com.tsa.nccapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.tsa.nccapp.adapter.ResultAdapter;
import com.tsa.nccapp.database.DatabaseHandler;

public class AnalizeActivity extends AppCompatActivity {
private RecyclerView resultList;
private ResultAdapter resultAdapter;
private DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analize);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(AnalizeActivity.this, R.color.light_vilate));
        }

        databaseHandler=new DatabaseHandler(AnalizeActivity.this);
        resultList=findViewById(R.id.result_list);
        resultAdapter=new ResultAdapter(databaseHandler.getAllResults(),AnalizeActivity.this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        resultList.setLayoutManager(mLayoutManager);
        resultList.setItemAnimator(new DefaultItemAnimator());
        resultList.setAdapter(resultAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AnalizeActivity.this,Main2Activity.class));
        finish();
    }
}
