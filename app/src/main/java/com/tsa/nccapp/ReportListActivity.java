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
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.tsa.nccapp.adapter.ChaptersListAdapter;
import com.tsa.nccapp.adapter.ReportAdapter;
import com.tsa.nccapp.custom.CustomActivity;
import com.tsa.nccapp.models.ChapterListModel;
import com.tsa.nccapp.models.ReportModel;
import com.tsa.nccapp.utils.GLOBAL;

import java.util.ArrayList;

public class ReportListActivity extends CustomActivity {

    private ArrayList<ReportModel> reportModelArrayList;
    private ReportAdapter reportAdapter;
    private RecyclerView reportList;
    private TextView frg_title;

    private String testName;
    private Bundle bundle;
    private AdView mAdView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);

        MobileAds.initialize(this,
                getResources().getString(R.string.add_mob_id));

        mAdView1 =  findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        if(mAdView1!=null)
            mAdView1.loadAd(adRequest1);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            testName = bundle.getString("moc_no");
        }

        frg_title = findViewById(R.id.frg_title);
        frg_title.setText(testName);

        reportList=findViewById(R.id.report_list);

        reportModelArrayList= GLOBAL.reportModels;

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        reportList.setLayoutManager(mLayoutManager);
        reportList.setItemAnimator(new DefaultItemAnimator());
        reportAdapter = new ReportAdapter(reportModelArrayList, ReportListActivity.this);
        reportList.setAdapter(reportAdapter);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(ReportListActivity.this,Main2Activity.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
