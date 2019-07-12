package com.tsa.nccapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tsa.nccapp.Network.NetworkCheck;
import com.tsa.nccapp.adapter.QuestionsRecyclerAdapter;
import com.tsa.nccapp.models.FAQModel;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class TestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtDesignation;
    TextView txtStats;
    private Context context;
    private ArrayList<FAQModel> faqModelArrayList;

    QuestionsRecyclerAdapter recyclerAdapter;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        context=TestActivity.this;

        faqModelArrayList=new ArrayList<>();
        coordinatorLayout=findViewById(R.id.main_content);

        recyclerView = findViewById(R.id.recyclerQuestions);
        txtDesignation = findViewById(R.id.txtDesignation);

        txtDesignation.setText(getDesignationText());
        txtStats.setText(getStatsText());

        if(NetworkCheck.checkInternet(coordinatorLayout,context))
        {
            getFaqs();
        }

    }

    public void getFaqs() {
        //Showing the progress dialog
        final ProgressDialog progress = new ProgressDialog(context);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "api_faq.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject json = new JSONObject(s);
                            Log.e("json", json.toString());
                            String msg = json.getString("status");
                            if (msg.equals("0")) {
                                JSONArray jsonArray=json.getJSONArray("data");
                                for(int i=0;i<jsonArray.length();i++)
                                {
                                    FAQModel faqModel=new FAQModel();
                                    faqModel.setFaqID(jsonArray.getJSONObject(i).getString("id"));
                                    faqModel.setQuestion(jsonArray.getJSONObject(i).getString("question"));
                                    faqModel.setAns(jsonArray.getJSONObject(i).getString("answer"));
                                    faqModelArrayList.add(faqModel);
                                }

                                /////////////////////////////////////////////////////////////////////////////
                                recyclerAdapter = new QuestionsRecyclerAdapter(context,faqModelArrayList);
                                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                                recyclerView.setAdapter(recyclerAdapter);
                                recyclerAdapter.notifyDataSetChanged();
                                ////////////////////////////////////////////////////////////////////////////
                            } else {
                                Toast.makeText(context, "No Data Available", Toast.LENGTH_SHORT).show();
                            }
                            progress.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error", e.toString());
                            progress.dismiss();
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

                Log.e("param", params.toString());

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    public SpannableString getDesignationText() {
        String text = txtDesignation.getText().toString();

        SpannableString s = new SpannableString(text);

        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), text.indexOf("@"), text.length(), 0);

        return s;
    }

    public SpannableString getStatsText() {
        String text = txtStats.getText().toString();

        SpannableString s = new SpannableString(text);

        s.setSpan(new RelativeSizeSpan(1.3f), 0, text.indexOf("A"), 0);
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.indexOf("A"), 0);

        s.setSpan(new RelativeSizeSpan(1.3f), text.indexOf("1"), text.indexOf("Q"), 0);
        s.setSpan(new ForegroundColorSpan(Color.WHITE), text.indexOf("1"), text.indexOf("Q"), 0);

        return s;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(context, Main2Activity.class));
        finish();
    }

}
