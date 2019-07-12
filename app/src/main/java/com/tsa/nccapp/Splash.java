package com.tsa.nccapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tsa.nccapp.custom.CustomActivity;
import com.tsa.nccapp.models.LoginModel;
import com.tsa.nccapp.models.UserModel;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends CustomActivity {

    // Set Duration of the Splash Screen
    long Delay = 4000;
    private Intent i;
    private Context context;
    private Bundle bundle;
    private String AnotherActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove the Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        context = Splash.this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                "login", Context.MODE_PRIVATE);

        bundle=getIntent().getExtras();

        if(sharedPref.getString("username","").equals(""))
        {
            i = new Intent(Splash.this,LoginActivity2.class);
        }
        else
        {
            login(sharedPref.getString("username",""),sharedPref.getString("password",""));
        }


        // Get the view from splash_activity.xml
        setContentView(R.layout.activitysplash);
        LinearLayout mLL = findViewById(R.id.splashscreen);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setRepeatCount(0);
        mLL.setAnimation(animation);
        Timer RunSplash = new Timer();

        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {

                if(i!=null)
                    startActivity(i);
                //overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right);
                finish();
            }
        };

        //Start the timer
        RunSplash.schedule(ShowSplash, Delay);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public void login(final String username, final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "loginUser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject json = new JSONObject(s);
                            Log.e("json",json.toString());
                            int status = json.getInt("status");
                            String msg = json.getString("msg");

                            if(status==0) {
                                GLOBAL.username = username;
                                GLOBAL.password = password;

                                JSONArray jsonArray = json.getJSONArray("userData");

                                if (json.getJSONArray("userData") == null) {

                                    Toast.makeText(Splash.this, "this cadet login successfully", Toast.LENGTH_SHORT).show();
                                }
                                ArrayList<LoginModel> loginModels = new ArrayList<>();

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    LoginModel loginModel = new LoginModel();

                                    JSONObject localJson = jsonArray.getJSONObject(i);

                                    if (json.getString("status").equals("0")) {
                                        loginModel.setName_of_cadet(localJson.getString("name_of_cadet"));
                                        loginModel.setEmail(localJson.getString("email"));
                                        loginModel.setMobile(localJson.getString("mobile"));
                                        loginModel.setRegiment_number(localJson.getString("regiment_number"));
                                        loginModel.setRank(localJson.getString("rank"));
                                        loginModel.setFather_name(localJson.getString("father_name"));
                                        loginModel.setExam_type(localJson.getString("exam_type"));
                                        loginModel.setUnit(localJson.getString("unit"));
                                        loginModel.setNcc_group(localJson.getString("ncc_group"));
                                        loginModel.setState(localJson.getString("state"));
                                        loginModel.setDistrict(localJson.getString("district"));
                                        loginModel.setTown(localJson.getString("town"));
                                        loginModel.setDirectorate(localJson.getString("directorate"));

                                        loginModels.add(loginModel);

                                        if (status==0) {

                                            startActivity(new Intent(Splash.this, Main2Activity.class));
                                            finish();

                                            if (status==1) {

                                                startActivity(new Intent(Splash.this, LoginActivity2.class));
                                                finish();

                                            } else {
                                                startActivity(new Intent(Splash.this, Main2Activity.class));
                                                finish();
                                            }
                                        } else {

                                            startActivity(new Intent(Splash.this, Main2Activity.class));
                                            finish();
                                        }
                                    } else {

                                        Toast.makeText(context, "Invalid User Name or Password", Toast.LENGTH_SHORT).show();
                                    }

                                }
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
                params.put("username", username);
                params.put("password", password);
                params.put("status", "Login");
                params.put("user_type","ncc");

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

}

