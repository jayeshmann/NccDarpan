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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.tsa.nccapp.Network.NetworkCheck;
import com.tsa.nccapp.adapter.ChaptersListAdapter;
import com.tsa.nccapp.models.ChapterListModel;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ChapterListActivity extends AppCompatActivity {

    private ArrayList<ChapterListModel> chapterListModels;
    private ChaptersListAdapter chaptersListAdapter;
    private RecyclerView chapList;
    private TextView frg_title;
    private Bundle bundle;
    private String subjectID;
    private String subjectName;
    private AdView mAdView1;
    private LinearLayout mainRoot;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);

        MobileAds.initialize(this, getResources().getString(R.string.add_mob_id));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(ChapterListActivity.this, R.color.light_vilate));
        }

        context=ChapterListActivity.this;
        mainRoot = findViewById(R.id.main_root);
        mAdView1 = findViewById(R.id.adView);

        AdRequest adRequest1 = new AdRequest.Builder().build();
        if (mAdView1 != null)
            mAdView1.loadAd(adRequest1);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            subjectID = bundle.getString("subjectID");
            subjectName = bundle.getString("sub_name");
        }

        MobileAds.initialize(this,
                getResources().getString(R.string.interstitial_id));


        frg_title = findViewById(R.id.frg_title);
        frg_title.setText(subjectName);

        chapterListModels = new ArrayList<>();
        chapList = findViewById(R.id.chapter_list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        chapList.setLayoutManager(mLayoutManager);
        chapList.setItemAnimator(new DefaultItemAnimator());

        if (NetworkCheck.checkInternet(mainRoot, context))
            getChapters(subjectID);
    }


    /////////////////////////////////////////GETIING CHAPTERS LIST//////////////////////////////////////////////////////////
    public void getChapters(final String subjectID) {
        //Showing the progress dialog
        final ProgressDialog progress = new ProgressDialog(ChapterListActivity.this);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "sanya_shakti_api.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {

                            JSONObject json = new JSONObject(s);
                            JSONArray chapList1 = json.getJSONArray(subjectID);

                            for (int i = 0; i < chapList1.length(); i++) {

                                JSONObject localJson = chapList1.getJSONObject(i);
                                ChapterListModel chapterListModel = new ChapterListModel();
                                chapterListModel.setId(localJson.getString("id"));
                                chapterListModel.setSubjectId(localJson.getString("subject_id"));

                                chapterListModel.setChapter(localJson.getString("chapter"));
                                chapterListModel.setChapterName(localJson.getString("chapter_name"));
                                chapterListModel.setStatus(localJson.getString("status"));

                                chapterListModel.setSubjectName(localJson.getString("sub_name"));


                                chapterListModel.setIsDeleted(localJson.getString("is_deleted"));
                                chapterListModel.setCreatedDate(localJson.getString("created_date"));

                                chapterListModels.add(chapterListModel);
                            }
                            progress.dismiss();
                            chaptersListAdapter = new ChaptersListAdapter(chapterListModels, ChapterListActivity.this);
                            chapList.setAdapter(chaptersListAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Showing toast
                        Toast.makeText(ChapterListActivity.this, "Some issue in loading" + volleyError, Toast.LENGTH_LONG).show();
                        progress.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("subject_id", subjectID);
                params.put("chapter_id", "");

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(ChapterListActivity.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChapterListActivity.this, CategoryListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GLOBAL.scrollAmount = ((LinearLayoutManager) chapList.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        (chapList.getLayoutManager()).scrollToPosition(GLOBAL.scrollAmount);
    }
}
