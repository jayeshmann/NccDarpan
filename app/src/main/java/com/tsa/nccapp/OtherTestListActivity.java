package com.tsa.nccapp;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tsa.nccapp.adapter.MockTestListAdapter;
import com.tsa.nccapp.adapter.OtherExamListAdapter;
import com.tsa.nccapp.custom.CustomActivity;
import com.tsa.nccapp.models.OtherExamCatModet;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class OtherTestListActivity extends CustomActivity {

    private ArrayList<OtherExamCatModet> otherExamCatModets;
    private OtherExamListAdapter otherExamListAdapter;
    private RecyclerView chapList;
    private Context context;
    TextView frg_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_test_list);
        init();
        getMockTest();
    }

    private void init() {

        context=OtherTestListActivity.this;
        otherExamCatModets=new ArrayList<>();
        chapList=findViewById(R.id.chapter_list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        chapList.setLayoutManager(mLayoutManager);
        chapList.setItemAnimator(new DefaultItemAnimator());

        frg_title=findViewById(R.id.frg_title);
        frg_title.setText("Sample Tests");
    }

    public void getMockTest() {
        //Showing the progress dialog
        final ProgressDialog progress=new ProgressDialog(context);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL+"examAPI.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {

                            JSONObject json=new JSONObject(s);
                            JSONArray chapList1=json.getJSONArray("data");

                            for(int i=0;i<chapList1.length();i++)
                            {
                                JSONObject localJson=chapList1.getJSONObject(i);
                                OtherExamCatModet otherExamCatModel=new OtherExamCatModet();
                                otherExamCatModel.setExamId(localJson.getString("exam_id"));
                                otherExamCatModel.setExamName(localJson.getString("exam_name"));

                                otherExamCatModel.setExamMarks(localJson.getString("exam_marks"));

                                otherExamCatModel.setExamDate(localJson.getString("exam_date"));
                                otherExamCatModel.setExamDuration(localJson.getString("exam_duration"));
                                otherExamCatModel.setmNegMarking(localJson.getString("neg_marking"));

                                otherExamCatModets.add(otherExamCatModel);
                            }
                            progress.dismiss();
                            otherExamListAdapter=new OtherExamListAdapter(otherExamCatModets,context);
                            chapList.setAdapter(otherExamListAdapter);
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
                params.put("exam_id", "");

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OtherTestListActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();
    }
}
