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
import com.tsa.nccapp.models.MockTestModel;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class MockTestListActivity extends AppCompatActivity {

    private ArrayList<MockTestModel> mockTestModels;
    private MockTestListAdapter mockTestListAdapter;
    private RecyclerView chapList;
    private Context context;
    TextView frg_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_test_list);
        init();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(MockTestListActivity.this, R.color.light_vilate));
        }

        getMockTest();
    }

    private void init() {

        context=MockTestListActivity.this;
        mockTestModels=new ArrayList<>();
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL+"sanya_shakti_api.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {

                            JSONObject json=new JSONObject(s);
                            JSONArray chapList1=json.getJSONArray("Mocktest");

                            for(int i=0;i<chapList1.length();i++)
                            {
                                JSONObject localJson=chapList1.getJSONObject(i);
                                MockTestModel mockTestModel=new MockTestModel();
                                mockTestModel.setId(localJson.getString("id"));
                                mockTestModel.setTestName(localJson.getString("test_name"));

                                mockTestModel.setStatus(localJson.getString("status"));

                                mockTestModel.setIsDeleted(localJson.getString("is_deleted"));
                                mockTestModel.setCreatedDate(localJson.getString("created_date"));

                                mockTestModels.add(mockTestModel);
                            }
                            progress.dismiss();
                            mockTestListAdapter=new MockTestListAdapter(mockTestModels,context);
                            chapList.setAdapter(mockTestListAdapter);
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
                params.put("exam_type", "Mocktest");

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
        Intent intent = new Intent(MockTestListActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();
    }
}
