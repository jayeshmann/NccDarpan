package com.tsa.nccapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tsa.nccapp.adapter.Exam_ViewAdapter;
import com.tsa.nccapp.models.Exam_listModel;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Exam_CardActivity extends AppCompatActivity {
    RecyclerView recycler_exam;
    Exam_ViewAdapter exam_viewAdapter;
    LinearLayoutManager layoutManager;
    ArrayList<Exam_listModel> exam_list = new ArrayList<>();
    TextView text_exam_details;
    String prod_id, subprod_id, exam_cert;
    ImageView header_back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam__card);
        header_back = (ImageView)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back =new Intent(Exam_CardActivity.this,View_product_Activity.class);
                startActivity(back);
            }
        });


        text_exam_details = (TextView)findViewById(R.id.text_exam_details);

        String prod_id = getIntent().getStringExtra("prod_id");
        String subprod_id = getIntent().getStringExtra("subprod_id");
        String exam_cert = getIntent().getStringExtra("exam_cert");
        Log.d("prod_id", "prod_id");
        Log.d("subprod_id","subprod_id");
        Log.d("exam_cert","exam_cert");


        recycler_exam = (RecyclerView)findViewById(R.id.recycler_exam);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycler_exam.setLayoutManager(layoutManager);
     //   APICallMethod();
//        exam_viewAdapter=new Exam_ViewAdapter(this,exam_list);
//        recycler_exam.setAdapter(exam_viewAdapter);
//        recycler_exam.setFocusable(false);

        exam_list(prod_id, subprod_id, exam_cert);

    }

    private void exam_list(String prod_id, String subprod_id, String exam_cert) {


            final ProgressDialog progress = new ProgressDialog(Exam_CardActivity.this);
            progress.setTitle("progress");
              progress.show();
              progress.dismiss();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "userExamList.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {

                            Log.d("productData", s.toString());
                            try {
                                JSONObject json = new JSONObject(s);
                                int status = json.getInt("status");

                                if (status == 0) {

                                    JSONArray jsonArray = json.getJSONArray("examData");

                                    ArrayList<Exam_listModel> exam_listModels = new ArrayList<>();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        Exam_listModel examlist = new Exam_listModel();

                                        JSONObject localJson = jsonArray.getJSONObject(i);

                                        examlist.setExam_id(localJson.getString("exam_id"));
                                        examlist.setExam_format_id(localJson.getString("exam_format_id"));
                                        examlist.setExam_name(localJson.getString("exam_name"));
                                        examlist.setExam_cert(localJson.getString("exam_cert"));
                                        examlist.setExam_hours(localJson.getString("exam_hours"));
                                        examlist.setExam_minutes(localJson.getString("exam_minutes"));
                                        examlist.setExam_format(localJson.getString("exam_format"));
                                        examlist.setTotal_question(localJson.getString("total_question"));

                                        exam_list.add(examlist);

                                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                        exam_viewAdapter=new Exam_ViewAdapter(Exam_CardActivity.this,exam_list);
                                        recycler_exam.setAdapter(exam_viewAdapter);
                                        recycler_exam.setLayoutManager(mLayoutManager);
                                        recycler_exam.setItemAnimator(new DefaultItemAnimator());
                                        recycler_exam.setFocusable(false);

                                    }


                                } else {

                                    Log.d("Login", s.toString());
                                    progress.dismiss();
                                    Toast.makeText(getApplicationContext(), "YOU HAVE ONE ERROR", Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                                progress.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(getApplicationContext(), "Some issue in loading" + volleyError, Toast.LENGTH_LONG).show();
                            progress.dismiss();

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String>();


                    params.put("prod_id", "5");
                    params.put("subprod_id", "5");
                    params.put("exam_cert", "B");

//                    params.put("user_type",user_type);user_type
                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(Exam_CardActivity.this);
            requestQueue.add(stringRequest);
        }

    }

