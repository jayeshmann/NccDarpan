package com.tsa.nccapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.MobileAds;
import com.tsa.nccapp.custom.CustomActivity;
import com.tsa.nccapp.custom.MyDialogue;
import com.tsa.nccapp.database.DatabaseHandler;
import com.tsa.nccapp.databinding.ActivityExamBinding;
import com.tsa.nccapp.databinding.FragmentExam1Binding;
import com.tsa.nccapp.models.MockQModel;
import com.tsa.nccapp.models.ReportModel;
import com.tsa.nccapp.models.ResultModel;
import com.tsa.nccapp.utils.GLOBAL;
import com.tsa.nccapp.utils.TimeConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

public class ExamActivity extends CustomActivity {
    ///////////////////////////////////////////////////
    public MyDialogue.DialogOnClickListener myListener;
    private MyDialogue myDialogue;
    private TextView timeLeftTv;
    ////////////////////////////
    private TextView optA;
    private TextView optB;
    private TextView optC;
    private TextView optD;
    //////////////////////
    private TextView selA;
    private TextView selB;
    private TextView selC;
    private TextView selD;
    //////////////////////
    private ImageView pre;
    private ImageView next;
    //////////////////////////
    private LinearLayout layA;
    private LinearLayout layB;
    private LinearLayout layC;
    private LinearLayout layD;
    ///////////////////////////
    private Context context;
    private ArrayList<MockQModel> mockQModels;
    private int count = 0;
    ActivityExamBinding binding;
    //////////////////////////////////////////
    private Bundle bundle;
    private String testID;
    private String testName;
    /////////////////////////////////////////

    /////////////////////////////////////////
    private CountDownTimer MyCountDownTimer;
    private String timeLeft = "";
    ////////////////////////////////////////

    ////////////////////////////////////////
    int selectedOpt[];
    private int maxSize = 0;
    int marks=0;
    ////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_exam);

        mockQModels = new ArrayList<>();
        init();
        getQuestions(testID);

        MobileAds.initialize(this,
                getResources().getString(R.string.add_mob_id));

        MyCountDownTimer = new CountDownTimer(60000 * 25, 1000) {
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 5 * 60 * 1000)
                    timeLeftTv.setTextColor(getResources().getColor(R.color.red));
                timeLeftTv.setText(TimeConverter.getDate(millisUntilFinished));
                timeLeft = "" + millisUntilFinished;

                if(timeLeft.equals("0"))
                {
                    submitExam(null);
                }
            }
            public void onFinish() {
                timeLeftTv.setText("00:00");
            }
        };
    }

    public void init() {
        context = ExamActivity.this;
        timeLeftTv = findViewById(R.id.time_left);
        /////////////////////////////////////////
        optA = findViewById(R.id.opt_a);
        optB = findViewById(R.id.opt_b);
        optC = findViewById(R.id.opt_c);
        optD = findViewById(R.id.opt_d);
        /////////////////////////////////////////////////
        layA = findViewById(R.id.lay_a);
        layB = findViewById(R.id.lay_b);
        layC = findViewById(R.id.lay_c);
        layD = findViewById(R.id.lay_d);
        ////////////////////////////////////////////

        selA = findViewById(R.id.sel_a);

        selB = findViewById(R.id.sel_b);

        selC = findViewById(R.id.sel_c);

        selD = findViewById(R.id.sel_d);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            testID = bundle.getString("id");
            testName = bundle.getString("moc_no");
        }

        /////////////////////////////////////////////////////////////////
        pre = findViewById(R.id.pre);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count <= 0)
                    count = maxSize - 1;
                else
                    count = count - 1;

                /////////////
                 selectOpt();
                /////////////
            }
        });
//////////////////////////////////////////////////////////////////////
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count >= maxSize-1)
                    count = 0;
                else
                    count = count + 1;

                ////////////
                selectOpt();
                ////////////
            }
        });
        //////////////////////////////////////////////////////////////
    }

    public void selA(View view) {
        optA.setBackground(getResources().getDrawable(R.drawable.option_bg1));
        optB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        optC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        optD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        selB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selA.setBackground(getResources().getDrawable(R.drawable.option_bg1));
        selC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        layB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layA.setBackground(getResources().getDrawable(R.drawable.option_bg2));
        layC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        selectedOpt[count] = 1;
    }

    public void selB(View view) {
        optB.setBackground(getResources().getDrawable(R.drawable.option_bg1));
        optA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        optC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        optD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        selB.setBackground(getResources().getDrawable(R.drawable.option_bg1));
        selA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        layB.setBackground(getResources().getDrawable(R.drawable.option_bg2));
        layA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        selectedOpt[count] = 2;

    }

    public void selC(View view) {
        optC.setBackground(getResources().getDrawable(R.drawable.option_bg1));
        optA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        optB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        optD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        selB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selC.setBackground(getResources().getDrawable(R.drawable.option_bg1));
        selD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        layB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layC.setBackground(getResources().getDrawable(R.drawable.option_bg2));
        layD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        selectedOpt[count] = 3;

    }

    public void selD(View view) {
        optD.setBackground(getResources().getDrawable(R.drawable.option_bg1));
        optA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        optB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        optC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        selB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selD.setBackground(getResources().getDrawable(R.drawable.option_bg1));

        layB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layD.setBackground(getResources().getDrawable(R.drawable.option_bg2));

        selectedOpt[count] = 4;

    }

    public void getQuestions(final String moduleID) {
        //Showing the progress dialog
        final ProgressDialog progress = new ProgressDialog(context);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "sanya_shakti_api.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {

                            JSONObject json = new JSONObject(s);
                            JSONArray chapList1 = json.getJSONArray(moduleID);

                            for (int i = 0; i < chapList1.length(); i++) {

                                JSONObject localJson = chapList1.getJSONObject(i);
                                MockQModel mockQModel = new MockQModel();
                                mockQModel.setId(localJson.getString("id"));
                                mockQModel.setAnswer(localJson.getString("mock_id"));

                                mockQModel.setQuestion(localJson.getString("question"));
                                mockQModel.setOptionA(localJson.getString("option_a"));
                                mockQModel.setOptionB(localJson.getString("option_b"));

                                mockQModel.setOptionC(localJson.getString("option_c"));
                                mockQModel.setOptionD(localJson.getString("option_d"));

                                mockQModel.setAnswer(localJson.getString("answer"));

                                mockQModel.setStatus(localJson.getString("status"));

                                mockQModel.setIsDeleted(localJson.getString("is_deleted"));
                                mockQModel.setCreatedDate(localJson.getString("created_date"));

                                mockQModels.add(mockQModel);

                            }
                            maxSize = mockQModels.size();
                           binding.setQuestionModel(mockQModels.get(0));

                            selectedOpt = new int[maxSize];
                            binding.qNo.setText((count + 1) + "/" + mockQModels.size());
                            MyCountDownTimer.start();

                            progress.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Showing toast
                        Toast.makeText(context, "Some issue in loading" + volleyError, Toast.LENGTH_LONG).show();
                        progress.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("mock_id", moduleID);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    public void selectOpt() {
        binding.setQuestionModel(mockQModels.get(count));
        binding.qNo.setText((count+1) + "/" + maxSize);
        if (count < maxSize) {
            switch (selectedOpt[count]) {
                case 1:
                    selA(null);
                    break;
                case 2:
                    selB(null);
                    break;
                case 3:
                    selC(null);
                    break;
                case 4:
                    selD(null);
                    break;
                case 0:
                    noSel();
                    break;
            }
        }

    }

    private void noSel() {
        optA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        optB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        optC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        optD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        selB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        selD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        layB.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layA.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layC.setBackground(getResources().getDrawable(R.drawable.empty_boarder));
        layD.setBackground(getResources().getDrawable(R.drawable.empty_boarder));

        if (count < maxSize)
            selectedOpt[count] = 0;
    }

    public void submitExam(View view) {
        marks=0;
        String atempted[] = new String[]{"NONE", "A", "B", "C", "D"};
        ArrayList<ReportModel> reportModels=new ArrayList<>();

        String status;

        for (int i = 0; i < maxSize; i++) {
            if(atempted[selectedOpt[i]].equals("NONE"))
            {
                status="UNATTEMPTED";
            }
            else if (mockQModels.get(i).getAnswer().equalsIgnoreCase(atempted[selectedOpt[i]])) {
                marks++;
                status="CORRECT";
            }
            else
            {
                status="INCORRECT";
            }


            ReportModel reportModel=new ReportModel(mockQModels.get(i),atempted[selectedOpt[i]],status);

            reportModels.add(reportModel);

        }

        GLOBAL.reportModels=reportModels;

        DatabaseHandler databaseHandler = new DatabaseHandler(ExamActivity.this);
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        ResultModel resultModel = new ResultModel();

        resultModel.setExamDate(formattedDate);
        resultModel.setNoOFCurrect("" + marks);
        resultModel.setMarksOpt("" + marks);
        resultModel.setNoOfQuestions("" + maxSize);

        databaseHandler.addQuestions(resultModel, ExamActivity.this);

        myListener = new MyDialogue.DialogOnClickListener() {
            @Override
            public void onOkClick() {
                submitExam(null);
                Intent intent=new Intent(ExamActivity.this, ResultActivity.class);
                intent.putExtra("moc_no",testName);

                intent.putExtra("totalQ",maxSize);
                intent.putExtra("correct",marks);
                intent.putExtra("incorrect",maxSize-marks);
                intent.putExtra("testName",testName);

                startActivity(intent);
                finish();
            }

            @Override
            public void onCancleClick() {
                myDialogue.dismiss();
            }
        };

        myDialogue = new MyDialogue(ExamActivity.this, myListener, "Your Examination Will Be Submitted", true, true);
        myDialogue.show();
    }

    @Override
    public void onBackPressed() {

        myListener = new MyDialogue.DialogOnClickListener() {
            @Override
            public void onOkClick() {
                startActivity(new Intent(ExamActivity.this, MockTestListActivity.class));
                finish();
            }

            @Override
            public void onCancleClick() {
                myDialogue.dismiss();
            }
        };

        myDialogue = new MyDialogue(ExamActivity.this, myListener, "Your Examination Will Be Submitted\n\n"
                + "Do you really want to Exit ?", true, true);
        myDialogue.show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

