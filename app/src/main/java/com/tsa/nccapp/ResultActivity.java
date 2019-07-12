package com.tsa.nccapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.tsa.nccapp.custom.CustomActivity;

import java.util.Timer;

public class ResultActivity extends CustomActivity {

    DonutProgress donutProgress;
    int progress = 0;

    private Bundle bundle;

    int totalQ = 0;
    int correct = 0;
    int incorrect = 0;
    String testName = "";

    TextView totalQTv;
    TextView correctTv;
    TextView incorrectTv;
    TextView testNameTv;
    TextView remarksTv;
    TextView frg_title;

    private String remarks[]=new String[]{"Need More Practice","Average","Good","Excellent"};
    private int colors[]=new int[]{R.color.red,R.color.yellow,R.color.Green,R.color.Vilate};
    private AdView mAdView1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            totalQ = bundle.getInt("totalQ");
            correct = bundle.getInt("correct");
            incorrect = bundle.getInt("incorrect");
            testName = bundle.getString("moc_no");
        }

        init();

        MobileAds.initialize(this,
                getResources().getString(R.string.add_mob_id));

        mAdView1 =  findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        if(mAdView1!=null)
            mAdView1.loadAd(adRequest1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void init() {
        donutProgress = findViewById(R.id.donut_progress);
        frg_title=findViewById(R.id.frg_title);
        frg_title.setText(testName);

        int percent=0;
        if(totalQ!=0) {
             percent = correct * 100 / totalQ;
        }

        totalQTv = findViewById(R.id.no_q);
        totalQTv.setText(""+totalQ);

        correctTv = findViewById(R.id.correct);
        correctTv.setText(""+correct);

        incorrectTv = findViewById(R.id.wrong);
        incorrectTv.setText(""+incorrect);

        testNameTv = findViewById(R.id.test_name);
        testNameTv.setText(testName);

        remarksTv=findViewById(R.id.remarks);

        donutProgress.setProgress(percent);

        if(percent<40)
        {
            remarksTv.setText(remarks[0]);
            remarksTv.setTextColor(colors[0]);
        }
        else if(percent>=40&&percent<60)
        {
            remarksTv.setText(remarks[1]);
            remarksTv.setTextColor(colors[1]);
        }
        else if(percent>=60&&percent<80)
        {
            remarksTv.setText(remarks[2]);
            remarksTv.setTextColor(colors[2]);
        }
        else if(percent>=80)
        {
            remarksTv.setText(remarks[3]);
            remarksTv.setTextColor(colors[3]);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    public void shareMarks(View view) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");

        String shareBody = "In "+testName+" I Got "+correct+" Marks out of "
                +totalQ+"\n You Also Try \n"+"https://play.google.com/store/apps/details?id=com.tsa.nccapp";

        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void review(View view) {
        Intent intent=new Intent(ResultActivity.this,ReportListActivity.class);
        intent.putExtra("moc_no",testName);
        startActivity(intent);
        finish();
    }

    public void goBack(View view) {
        startActivity(new Intent(ResultActivity.this,Main2Activity.class));
        finish();
    }
}
