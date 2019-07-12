package com.tsa.nccapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
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
import com.tsa.nccapp.custom.ZoomTextView;
import com.tsa.nccapp.databinding.ActivityChaptersBinding;
import com.tsa.nccapp.interfaces.OnSwipeTouchListener;
import com.tsa.nccapp.models.ChapterQModel;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ChaptersActivity extends AppCompatActivity {

    ActivityChaptersBinding binding;
    int count = 0;
    private ZoomTextView answer;
    private Context context;
    private ArrayList<ChapterQModel> chapterQModels;
    private TextView chapterNumber;
    private TextView chapterName;
    private Bundle bundle;
    private String chapterID;
    private String subID;
    private String subName;
    private String chapName;
    private String chapNumber;
    private AdView mAdView;

    float x1, x2;
    float y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chapters);
        context = ChaptersActivity.this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(ChaptersActivity.this, R.color.light_vilate));
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        binding.scrollView.setOnTouchListener(new OnSwipeTouchListener(ChaptersActivity.this));

        MobileAds.initialize(this,
                getResources().getString(R.string.add_mob_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ////////////////////////////////////////////////////////////
        bundle = getIntent().getExtras();
        if (bundle != null) {
            chapterID = bundle.getString("id");
            subID = bundle.getString("sub_id");
            chapName = bundle.getString("c_name");
            chapNumber = bundle.getString("c_no");
            subName = bundle.getString("sub_name");
        }

        chapterQModels = new ArrayList<>();
        answer = findViewById(R.id.answer);
        chapterName = binding.chapterName;
        chapterName.setText(chapName);
        chapterNumber = binding.chapterNo;
        chapterNumber.setText(chapNumber);

        answer.setTextColor(getResources().getColor(GLOBAL.textColor));
        answer.setBackgroundColor(getResources().getColor(GLOBAL.BGColor));
        answer.setTextSize(GLOBAL.textSize);

        //binding.foot.setBackgroundResource(R.color.trans);
        binding.root.setBackgroundResource(GLOBAL.BGColor);

        if(NetworkCheck.checkInternet(binding.root,context))
        getQuestions(chapterID, subID);
    }

    public void next(View view) {
        count++;
        if (count == chapterQModels.size())
            count = 0;
        binding.questionSequence.setText(count + 1 + "/" + chapterQModels.size());
        answer.setText(Html.fromHtml(chapterQModels.get(count).getQuestion()));
    }

    public void pre(View view) {
        count--;
        if (count == -1)
            count = chapterQModels.size() - 1;
        binding.questionSequence.setText(count + 1 + "/" + chapterQModels.size());
        answer.setText(Html.fromHtml(chapterQModels.get(count).getQuestion()));
    }

    public void getQuestions(final String moduleID, final String subID) {
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
                                ChapterQModel chapterQModel = new ChapterQModel();
                                chapterQModel.setId(localJson.getString("id"));
                                chapterQModel.setSubjectId(localJson.getString("subject_id"));

                                chapterQModel.setChapterId(localJson.getString("chapter_id"));
                                chapterQModel.setQuestion(localJson.getString("question"));
                                chapterQModel.setStatus(localJson.getString("status"));

                                chapterQModel.setIsDeleted(localJson.getString("is_deleted"));
                                chapterQModel.setCreatedDate(localJson.getString("created_date"));

                                chapterQModels.add(chapterQModel);
                            }
                            answer.setText(Html.fromHtml(chapterQModels.get(count).getQuestion()));
                            binding.questionSequence.setText(count + 1 + "/" + chapterQModels.size());

                            progress.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
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

                //Adding parameters
                params.put("subject_id", subID);
                params.put("chapter_id", moduleID);

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
        Intent intent = new Intent(context, ChapterListActivity.class);
        intent.putExtra("subjectID", subID);
        intent.putExtra("c_name", chapName);
        intent.putExtra("sub_name", subName);
        startActivity(intent);
        finish();
    }


    // onTouchEvent () method gets called when User performs any touch event on screen
    // Method to handle touch event like left to right swap and right to left swap
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                //if left to right sweep event on screen
                if (x1 < x2) {
                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                    next(null);
                }

                // if right to left sweep event on screen
                if (x1 > x2) {
                    Toast.makeText(this, "right to left Swap Performed", Toast.LENGTH_LONG).show();

                    pre(null);
                }

                // if UP to Down sweep event on screen
                if (y1 < y2) {
                    Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
                }

                //if Down to UP sweep event on screen
                if (y1 > y2) {
                    Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        return false;
    }


}
