package com.tsa.nccapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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
import com.tsa.nccapp.database.NCCDarpanDB;
import com.tsa.nccapp.models.OtherExamModel;
import com.tsa.nccapp.models.OtherExamResultModel;
import com.tsa.nccapp.models.ReportModel;
import com.tsa.nccapp.models.ResultModel;
import com.tsa.nccapp.models.SubjectListModel;
import com.tsa.nccapp.utils.GLOBAL;
import com.tsa.nccapp.utils.TimeConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class OtherExamActivity extends CustomActivity {

    ////////////////////////////////////////////////////
    private TextView timeLeftTv;
    ////////////////////////////////////////////////////
    private TextView optA;
    private TextView optB;
    private TextView optC;
    private TextView optD;
    private ArrayList<SubjectListModel> subjectListModels;
    /////////////////////////////////////////////////////
    private TextView selA;
    private TextView selB;
    private TextView selC;
    private TextView selD;
    /////////////////////////////////////////////////////
    private TextView textA;
    private TextView textB;
    private TextView textC;
    private TextView textD;
    //////////////////////////////////////////////////////
    private ImageView pre;
    private ImageView next;
    //////////////////////////////////////////////////////
    private LinearLayout layA;
    private LinearLayout layB;
    private LinearLayout layC;
    private LinearLayout layD;
    ///////////////////////////
    private Context context;
    ///////////////////////////
    private static ArrayList<OtherExamModel> otherExamModels;
    //////////////////////////////////////////////////////////////////////////
    public MyDialogue.DialogOnClickListener myListener;
    MyDialogue myDialogue;

    private int count = 1;

    private Bundle bundle;
    private String testID;
    private String testName;
    private String examDuration;

    private CountDownTimer MyCountDownTimer;
    private String timeLeft = "";
    private TextView question;
    private TextView subject;
    private int totalQ;

    private boolean isSubmitted;

    private static OtherExamModel currentExamModel;

    float totalNegMarks = 0;
    float negMarks=0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_exam);

        otherExamModels = new ArrayList<>();
        init();
        getSubject();

        MobileAds.initialize(this,
                getResources().getString(R.string.add_mob_id));

        MyCountDownTimer = new CountDownTimer(Integer.parseInt(examDuration) * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 5 * 60 * 1000)
                    timeLeftTv.setTextColor(getResources().getColor(R.color.red));
                timeLeftTv.setText(TimeConverter.getDate(millisUntilFinished));
                timeLeft = "" + millisUntilFinished;

                if (timeLeft.equals("0")) {
                    //submitExam(null);
                    getAllQus();
                }
            }

            public void onFinish() {
                timeLeftTv.setText("00:00");
            }
        };
    }

    public void init() {
        context = OtherExamActivity.this;
        ///////////////////////////////////////////

        //////////////////////////////////////////
        timeLeftTv = findViewById(R.id.time_left);
        question = findViewById(R.id.question);
        subject = findViewById(R.id.subject);
        ///////////////////////////////////////////
        optA = findViewById(R.id.opt_a);
        optB = findViewById(R.id.opt_b);
        optC = findViewById(R.id.opt_c);
        optD = findViewById(R.id.opt_d);
        ////////////////////////////////////
        layA = findViewById(R.id.lay_a);
        layB = findViewById(R.id.lay_b);
        layC = findViewById(R.id.lay_c);
        layD = findViewById(R.id.lay_d);
        /////////////////////////////////////
        selA = findViewById(R.id.sel_a);
        selB = findViewById(R.id.sel_b);
        selC = findViewById(R.id.sel_c);
        selD = findViewById(R.id.sel_d);
        //////////////////////////////////////
        textA = findViewById(R.id.opt_a_text);
        textB = findViewById(R.id.opt_b_text);
        textC = findViewById(R.id.opt_c_text);
        textD = findViewById(R.id.opt_d_text);
        //////////////////////////////////////
        bundle = getIntent().getExtras();
        ////////////////////////////////////////////////////////////////////
        if (bundle != null) {
            testID = bundle.getString("id");
            testName = bundle.getString("moc_no");
            examDuration = bundle.getString("exm_duration");
            negMarks= Float.parseFloat(bundle.getString("neg_marks"));
            Log.e("NegMarks",""+negMarks);
        }
        deleteTable();
        ////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////
        pre = findViewById(R.id.pre);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count <= 1)
                    count = totalQ;
                else
                    count = count - 1;
                //////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////
                getQusByID(count);
                //////////////////////////////////////////////////////////
            }
        });
        /////////////////////////////////////////////////////////////////////
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (count >= totalQ) {
                    count = 1;
                } else
                    count = count + 1;
                /////////////////////////////////////////////////////////

                ///////////////////////////////////////////////////////////
                getQusByID(count);
                ///////////////////////////////////////////////////////////
            }
        });
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

        /////////////////////////////////////////////////////////////////////////
        currentExamModel.setSelectedOpt("A");
        updateData(currentExamModel);
        //////////////////////////////////////////////////////////////////////////
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

        /////////////////////////////////////////////////////////////////////////
        OtherExamActivity.currentExamModel.setSelectedOpt("B");
        updateData(OtherExamActivity.currentExamModel);
        //////////////////////////////////////////////////////////////////////////

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


        /////////////////////////////////////////////////////////////////////////
        OtherExamActivity.currentExamModel.setSelectedOpt("C");
        updateData(OtherExamActivity.currentExamModel);
        //////////////////////////////////////////////////////////////////////////
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

        /////////////////////////////////////////////////////////////////////////
        OtherExamActivity.currentExamModel.setSelectedOpt("D");
        updateData(OtherExamActivity.currentExamModel);
        //////////////////////////////////////////////////////////////////////////
    }

    public void getQuestions(final String subID, final String subName) {
        //Showing the progress dialog
        final ProgressDialog progress = new ProgressDialog(context);
        progress.show();
        //otherExamModels.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "examAPI.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {

                            JSONObject json = new JSONObject(s);
                            JSONArray chapList1 = json.getJSONArray("data");

                            for (int i = 0; i < chapList1.length(); i++) {

                                JSONObject localJson = chapList1.getJSONObject(i);
                                final OtherExamModel otherExamModel = new OtherExamModel();
                                ////////////////////////////////////////////////////////////////////
                                otherExamModel.setQid(localJson.getString("qid"));

                                otherExamModel.setEl(localJson.getString("el"));
                                otherExamModel.setEq(localJson.getString("eq"));
                                ////////////////////////////////////////////////////////////////////
                                otherExamModel.setEqo1(localJson.getString("eqo1"));
                                otherExamModel.setEqo2(localJson.getString("eqo2"));
                                otherExamModel.setEqo3(localJson.getString("eqo3"));
                                otherExamModel.setEqo4(localJson.getString("eqo4"));
                                ////////////////////////////////////////////////////////////////////
                                otherExamModel.setOq(localJson.getString("oq"));
                                otherExamModel.setAns(localJson.getString("ans"));
                                ////////////////////////////////////////////////////////////////////
                                otherExamModel.setOqo1(localJson.getString("oqo1"));
                                otherExamModel.setOqo2(localJson.getString("oqo2"));
                                otherExamModel.setOqo3(localJson.getString("oqo3"));
                                otherExamModel.setOqo4(localJson.getString("oqo4"));
                                ////////////////////////////////////////////////////////////////////
                                otherExamModel.setQimg(localJson.getString("qimg"));
                                otherExamModel.setOpimg1(localJson.getString("opimg1"));
                                otherExamModel.setOpimg2(localJson.getString("opimg2"));
                                otherExamModel.setOpimg3(localJson.getString("opimg3"));
                                otherExamModel.setOpimg4(localJson.getString("opimg4"));
                                ////////////////////////////////////////////////////////////////////
                                otherExamModel.setSubID(subID);
                                otherExamModel.setSubjectName(subName);
                                otherExamModel.setSn("NONE");
                                otherExamModel.setQuestionID("" + (count));
                                otherExamModel.setSelectedOpt("E");
                                ////////////////////////////////////////////////////////////////////
                                count++;
                                ////////////////////////////////////////////////////////////////////
                                otherExamModels.add(otherExamModel);
                                ////////initialize Last selected////////////////////////////////////
                                ////////////////////////////////////////////////////////////////////
                                insertToDB(otherExamModel);
                                ////////////////////////////////////////////////////////////////////
                            }

                            getAllQus();
                            MyCountDownTimer.start();
                            /////////////////////////////////////////////////////////////////////////////////////
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
                params.put("exam_id", testID);
                params.put("subject_id", subID);
                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    public void getSubject() {
        //Showing the progress dialog
        final ProgressDialog progress = new ProgressDialog(context);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "examAPI.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            subjectListModels = new ArrayList<>();
                            JSONObject json = new JSONObject(s);
                            JSONArray chapList1 = json.getJSONArray("data");

                            for (int i = 0; i < chapList1.length(); i++) {
                                JSONObject localJson = chapList1.getJSONObject(i);
                                SubjectListModel subjectListModel = new SubjectListModel();
                                subjectListModel.setSubId(localJson.getString("sub_id"));
                                subjectListModel.setSubName(localJson.getString("sub_name"));
                                subjectListModels.add(subjectListModel);
                            }

                            loadExamData(subjectListModels);
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
                params.put("exam_id", testID);
                params.put("status", "subject");

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void loadExamData(ArrayList<SubjectListModel> subjectListModels) {
        /////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < subjectListModels.size(); i++) {
            getQuestions(subjectListModels.get(i).getSubId(), subjectListModels.get(i).getSubName());
        }
        //////////////////////////////////////////////////////////////////////////
    }

    public void selectOpt() {
        //binding.qNo.setText((count+1) + "/" + maxSize);
        switch (currentExamModel.getSelectedOpt()) {
            case "A":
                selA(null);
                break;
            case "B":
                selB(null);
                break;
            case "C":
                selC(null);
                break;
            case "D":
                selD(null);
                break;
            case "E":
                noSel();
                break;
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

      /*  if (count < maxSize)
            selectedOpt[count] = 0;*/
    }

    public void submitExam(View view) {

        myListener = new MyDialogue.DialogOnClickListener() {
            @Override
            public void onOkClick() {
                isSubmitted = true;
                getAllQus();
            }

            @Override
            public void onCancleClick() {
                myDialogue.dismiss();
            }
        };

        myDialogue = new MyDialogue(context, myListener, "Your Examination Will Be Submitted", true, true);
        myDialogue.show();

        //
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(OtherExamActivity.this, Main2Activity.class));
        finish();

       /* myListener = new MyDialogue.DialogOnClickListener() {
            @Override
            public void onOkClick() {
                startActivity(new Intent(OtherExamActivity.this, Main2Activity.class));
                finish();
            }

            @Override
            public void onCancleClick() {
                myDialogue.dismiss();
            }
        };

        myDialogue = new MyDialogue(OtherExamActivity.this, myListener, "Your Examination Will Be Submitted\n\n"
                + "Do you really want to Exit ?", true, true);
        myDialogue.show();*/
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

    private void setQuestion() {
        question.setText(currentExamModel.getQuestionID() + ":-" + currentExamModel.getOq());
        textA.setText("" + currentExamModel.getOqo1());
        textB.setText("" + currentExamModel.getOqo2());
        textC.setText("" + currentExamModel.getOqo3());
        textD.setText("" + currentExamModel.getOqo4());
        subject.setText(currentExamModel.getSubjectName());
    }


    public void insertToDB(final OtherExamModel otherExamModel) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                NCCDarpanDB
                        .getInstance(context)
                        .getNccDarpanDao()
                        .insertExamQuestions(otherExamModel);
                return 0;
            }
        }.execute();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void deleteTable() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                NCCDarpanDB
                        .getInstance(context)
                        .getNccDarpanDao()
                        .deleteTable();
                return 0;
            }
        }.execute();
    }

    public void updateData(final OtherExamModel otherExamModel) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                NCCDarpanDB
                        .getInstance(context)
                        .getNccDarpanDao()
                        .updateExamQuestions(otherExamModel);
                return 0;
            }
        }.execute();
    }

    public void getQusByID(final int qID) {
        new AsyncTask<OtherExamModel, OtherExamModel, OtherExamModel>() {
            @Override
            protected OtherExamModel doInBackground(OtherExamModel... params) {
                ArrayList<OtherExamModel> otherExamModels;
                otherExamModels = (ArrayList<OtherExamModel>) NCCDarpanDB
                        .getInstance(context)
                        .getNccDarpanDao()
                        .getByQID(qID);

                if (otherExamModels != null && otherExamModels.size() != 0)
                    return otherExamModels.get(0);
                else
                    return null;
            }

            @Override
            protected void onPostExecute(OtherExamModel otherExamModel) {
                super.onPostExecute(otherExamModel);
                OtherExamActivity.currentExamModel = otherExamModel;
                setQuestion();
                selectOpt();
            }
        }.execute();
    }

    public void getAllQus() {
        new AsyncTask<ArrayList<OtherExamModel>, ArrayList<OtherExamModel>, ArrayList<OtherExamModel>>() {
            @Override
            protected ArrayList<OtherExamModel> doInBackground(ArrayList<OtherExamModel>... params) {
                ArrayList<OtherExamModel> otherExamModels;
                otherExamModels = (ArrayList<OtherExamModel>) NCCDarpanDB
                        .getInstance(context)
                        .getNccDarpanDao()
                        .loadAllQustions();
                return otherExamModels;
            }

            @Override
            protected void onPostExecute(ArrayList<OtherExamModel> otherExamModels) {
                //////////////////////////////////////////////////////////////////
                super.onPostExecute(otherExamModels);
                if (!isSubmitted) {
                    currentExamModel = otherExamModels.get(0);
                    setQuestion();
                    totalQ = otherExamModels.size();
                } else {
                    generateResult(otherExamModels);
                }
                //////////////////////////////////////////////////////////////////

            }
        }.execute();
    }

    private void generateResult(ArrayList<OtherExamModel> allQuestionsResult) {
        int sub[] = new int[10];
        int totalQ[] = new int[10];
        GLOBAL.otherExamResultModels.clear();
        GLOBAL.reportModels = new ArrayList<>();

        for (int i = 0; i < allQuestionsResult.size(); i++) {
            OtherExamModel localOtherModel = allQuestionsResult.get(i);
            ////////////////////////////////////////////////////////////////////////////////////////
            ReportModel reportModel = new ReportModel();
            reportModel.setmAnswer(localOtherModel.getAns());
            reportModel.setmQuestion(localOtherModel.getAns());
            reportModel.setmOptionA(localOtherModel.getOqo1());
            reportModel.setmOptionB(localOtherModel.getOqo2());
            reportModel.setmOptionC(localOtherModel.getOqo3());
            reportModel.setmOptionD(localOtherModel.getOqo4());
            reportModel.setmQuestion(localOtherModel.getOq());
            reportModel.setmAttempted(localOtherModel.getSelectedOpt());
            reportModel.setmQStatus("INCORRECT");

            ////////////////////////////////////////////////////////////////////////////////////////
            if (localOtherModel.getSubID().equals(subjectListModels.get(0).getSubId())) {
                if (localOtherModel.getSelectedOpt().equalsIgnoreCase(localOtherModel.getAns())) {
                    sub[0] = sub[0] + 1;
                    reportModel.setmQStatus("CORRECT");
                }
                totalQ[0] = totalQ[0] + 1;
            } else if (allQuestionsResult.get(i).getSubID().equals(subjectListModels.get(1).getSubId())) {
                if (localOtherModel.getSelectedOpt().equalsIgnoreCase(localOtherModel.getAns())) {
                    sub[1] = sub[1] + 1;
                    reportModel.setmQStatus("CORRECT");
                }
                totalQ[1] = totalQ[1] + 1;
            } else if (allQuestionsResult.get(i).getSubID().equals(subjectListModels.get(2).getSubId())) {
                if (localOtherModel.getSelectedOpt().equalsIgnoreCase(localOtherModel.getAns())) {
                    sub[2] = sub[2] + 1;
                    reportModel.setmQStatus("CORRECT");
                }
                totalQ[2] = totalQ[2] + 1;
            }

            if (localOtherModel.getSelectedOpt().equals("E")) {
                reportModel.setmQStatus("UNATTEMPTED");
            }
            if (reportModel.getmQStatus().equals("INCORRECT")) {
                totalNegMarks = totalNegMarks + 1;
            }
            ///////////////////////////ADDING DATA/////////////////////
            GLOBAL.reportModels.add(reportModel);
            //////////////////////////////////////////////////////////
        }
        totalNegMarks=totalNegMarks*negMarks;
        Log.e("totalNegMarks",""+totalNegMarks);

        ///////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < subjectListModels.size(); i++) {
            OtherExamResultModel otherExamResultModel = new OtherExamResultModel();
            otherExamResultModel.setSubjectName(subjectListModels.get(i).getSubName());
            otherExamResultModel.setNumberOfCurrQus(sub[i]);
            otherExamResultModel.setNumberOfQus(totalQ[i]);
            GLOBAL.otherExamResultModels.add(otherExamResultModel);
        }
        ///////////////////////////////////////////////////////////////////////////////
        Intent intent = new Intent(context, OthersResultActivity.class);
        intent.putExtra("exam_name", testName);
        intent.putExtra("neg_marks",totalNegMarks);
        startActivity(intent);
        finish();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

}

