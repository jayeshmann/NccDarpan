package com.tsa.nccapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.tsa.nccapp.custom.CustomActivity;
import com.tsa.nccapp.models.OtherExamResultModel;
import com.tsa.nccapp.utils.GLOBAL;

public class OthersResultActivity extends CustomActivity {

    int progress = 0;
    private Bundle bundle;

    private TextView totalMarksTv;
    DonutProgress donutProgress;

    private TextView remarksTv;
    private TextView testNameTv;

    int subjectTv[] = new int[]{R.id.subject1_tv, R.id.subject2_tv, R.id.subject3_tv};
    int marksTv[] = new int[]{R.id.subject1_marks, R.id.subject2_marks, R.id.subject3_marks,R.id.total_q_marks,R.id.neg_q_marks};

    String testName = "";

    private Context context;
    private int totalMarks = 0;
    private int totalQuestion = 0;
    private float totalNegMarks=0f;
    private String remarks[] = new String[]{"Need More Practice", "Average", "Good",
            "Very Good","Excellent"};
    private int colors[] = new int[]{R.color.red, R.color.yellow, R.color.Green,
            R.color.Vilate};

    private String totalPositiveMarks;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_result);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void init() {
        context = OthersResultActivity.this;
        totalMarksTv = findViewById(R.id.total_marks);
        remarksTv = findViewById(R.id.remarks);
        donutProgress = findViewById(R.id.donut_progress);
        bundle=getIntent().getExtras();

        if(bundle!=null)
        {
             testName=bundle.getString("exam_name");
             totalNegMarks=bundle.getFloat("neg_marks");
             Log.e("TotalNegMarks",""+totalNegMarks);
        }


        testNameTv = findViewById(R.id.frg_title);
        testNameTv.setText(testName);
        remarksTv = findViewById(R.id.remarks);

        calculateResult();

        float percent = 0;
        if (totalQuestion != 0) {
            percent = (totalMarks-(totalNegMarks)) * 100 / totalQuestion;
        }


        donutProgress.setProgress(percent);

        if (percent < 40) {
            remarksTv.setText(remarks[0]);
            remarksTv.setTextColor(colors[0]);
        } else if (percent >= 40 && percent < 60) {
            remarksTv.setText(remarks[1]);
            remarksTv.setTextColor(colors[1]);
        } else if (percent >= 60 && percent < 70) {
            remarksTv.setText(remarks[2]);
            remarksTv.setTextColor(colors[2]);
        }
        else if (percent >= 70 && percent < 80) {
            remarksTv.setText(remarks[3]);
            remarksTv.setTextColor(colors[3]);
        }
        else if (percent >= 80) {
            remarksTv.setText(remarks[4]);
            remarksTv.setTextColor(colors[4]);
        }
    }

    @Override

    public void onBackPressed() {
        super.onBackPressed();
        GLOBAL.otherExamResultModels.clear();
        goBack(null);
    }

    public void review(View view) {
        Intent intent = new Intent(OthersResultActivity.this, ReportListActivity.class);
        intent.putExtra("moc_no", testName);
        startActivity(intent);
        finish();
    }

    public void goBack(View view) {
        startActivity(new Intent(OthersResultActivity.this, Main2Activity.class));
        finish();
    }

    public void calculateResult() {
        for (int i = 0; i < GLOBAL.otherExamResultModels.size(); i++) {
            OtherExamResultModel localExamModel = GLOBAL.otherExamResultModels.get(i);

            if (localExamModel != null&&i<this.subjectTv.length) {
                TextView subjectTv = null;
                subjectTv = findViewById(this.subjectTv[i]);
                subjectTv.setText(localExamModel.getSubjectName());
                TextView marksTv = null;
                marksTv = findViewById(this.marksTv[i]);
                marksTv.setText("" + localExamModel.getNumberOfCurrQus() + "/" + localExamModel.getNumberOfQus());
            }

            //////////////////////////////////////////////////////////////////////////////////
            totalMarks = totalMarks + localExamModel.getNumberOfCurrQus();
            totalQuestion = totalQuestion + localExamModel.getNumberOfQus();
        }

        totalMarksTv.setText(""+(totalMarks-(totalNegMarks)) + "/" + totalQuestion);
        //////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////
        TextView totalNegMarksTv=findViewById(marksTv[4]);
        totalNegMarksTv.setText(""+totalNegMarks);
        //////////////////////////////////////////////////////////////////////////////
        TextView positivelMarks=findViewById(marksTv[3]);
        //////////////////////////////////////////////////////////////////////////////
        positivelMarks.setText(""+totalMarks);
        //////////////////////////////////////////////////////////////////////////////
    }
}