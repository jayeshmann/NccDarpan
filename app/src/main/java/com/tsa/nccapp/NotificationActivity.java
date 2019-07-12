package com.tsa.nccapp;

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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.tsa.nccapp.adapter.NotificationAdapter;
import com.tsa.nccapp.models.NotificationModel;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView chapList;
    private TextView frg_title;
    private AdView mAdView1;
    private Context context;
    private NotificationAdapter notificationAdapter;
    private ArrayList<NotificationModel> notificationModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);

        MobileAds.initialize(this, getResources().getString(R.string.add_mob_id));
        notificationModelArrayList=new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(NotificationActivity.this, R.color.light_vilate));
        }

        context=NotificationActivity.this;
        mAdView1 =  findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        if(mAdView1!=null)
            mAdView1.loadAd(adRequest1);

        MobileAds.initialize(this,
                getResources().getString(R.string.interstitial_id));

        frg_title = findViewById(R.id.frg_title);
        frg_title.setText("Notification");

        chapList = findViewById(R.id.chapter_list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        chapList.setLayoutManager(mLayoutManager);
        chapList.setItemAnimator(new DefaultItemAnimator());

        getNotification();

    }

    ///////////////////////////////////
    public void getNotification() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "notification_api.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {

                            JSONObject json = new JSONObject(s);

                            String status = json.getString("status");


                            if (status.equals("0")) {
                                JSONArray notyList = json.getJSONArray("data");

                                for (int i = 0; i < notyList.length(); i++) {
                                    JSONObject localJson = notyList.getJSONObject(i);
                                    final NotificationModel notificationModel = new NotificationModel();
                                    notificationModel.setNid(localJson.getString("nid"));
                                    notificationModel.setName(localJson.getString("description"));

                                    notificationModel.setHead(localJson.getString("head"));
                                    notificationModel.setDate(localJson.getString("date"));
                                    notificationModel.setImgname(localJson.getString("imgname"));
                                    notificationModel.setUrl(localJson.getString("url"));

                                    notificationModel.setView("NO");
                                    notificationModel.setWish("NO");
                                    notificationModelArrayList.add(notificationModel);
                                }
                                notificationAdapter=new NotificationAdapter(notificationModelArrayList,NotificationActivity.this);
                                chapList.setAdapter(notificationAdapter);
                            }

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
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("status", "");

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NotificationActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();
    }

}
