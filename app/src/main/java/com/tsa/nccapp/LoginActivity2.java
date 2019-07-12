package com.tsa.nccapp;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.tsa.nccapp.Network.NetworkCheck;
import com.tsa.nccapp.custom.CustomActivity;
import com.tsa.nccapp.custom.RightDrawableOnTouchListener;
import com.tsa.nccapp.models.LoginModel;
import com.tsa.nccapp.models.UserModel;
import com.tsa.nccapp.utils.GLOBAL;
import com.tsa.nccapp.utils.SaveSharedPrefernces;
import com.tsa.nccapp.validation.Validation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import static com.tsa.nccapp.utils.GLOBAL.user_type;

/********************************************************
 * A login screen that offers login via email/password. *
 ********************************************************/
public class LoginActivity2 extends CustomActivity {
    private static final int RC_SIGN_IN = 1;
    private static  Context context;

    private EditText username;
    private EditText password;

    private boolean pwdFlag = false;
    private GoogleSignInClient mGoogleSignInClient;
    SharedPreferences sharedPref;
    LinearLayout mainRoot;
    RadioGroup radioGroup;
    String user_type;
    SaveSharedPrefernces ssp;
    RadioButton login_ncc,login_others;
    private String Accounnt_type;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        context = LoginActivity2.this;
        SaveSharedPrefernces ssp;
        sharedPref = context.getSharedPreferences(
                "login", Context.MODE_PRIVATE);

        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        init();

        login_ncc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (GLOBAL.user_type.equals("ncc")){
                    Accounnt_type = "ncc";

                }
            }
        });

        login_others.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (GLOBAL.user_type.equals("others")){
                    Accounnt_type = "others";
                }
            }
        });
//            if (GLOBAL.user_type =="ncc"){
//                Accounnt_type="ncc";
//            }
//            else {
//                Accounnt_type="others";
//            }
    }


    /////////////////////////
    private void init() {
        username = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        mainRoot=findViewById(R.id.main_root);

        login_ncc = (RadioButton)findViewById(R.id.login_ncc);
        login_others = (RadioButton)findViewById(R.id.login_others);
        radioGroup = (RadioGroup)findViewById(R.id.login_radiogrp);
        radioGroup.clearCheck();

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetworkCheck.checkInternet(mainRoot,context))
                    signIn();
            }
        });

        password.setOnTouchListener(new RightDrawableOnTouchListener(password) {
            @Override
            public boolean onDrawableTouch(final MotionEvent event) {
                return onClickSearch(event);
            }

        });
    }

    //////////////////////////////////////////
    public void goToDashboard(View view) {
        if (checkValidation()) {
            if (NetworkCheck.checkInternet(mainRoot,context)) {
                login(username.getText().toString(), password.getText().toString());


            }
        }
    }

    ///////////////////////////////////////////
    public void goRegistration(View view) {
        if (NetworkCheck.checkInternet(mainRoot,context)) {
            startActivity(new Intent(LoginActivity2.this, Signup_oneActivity.class));
            finish();
        }
    }



    ////////////////////////////////////////////////////////////////
    ///
     public void login(final String username, final String password) {
        //Showing the progress dialog
        final ProgressDialog progress = new ProgressDialog(LoginActivity2.this);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  GLOBAL.baseURL+ "loginUser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("Login", s.toString());
                        try {
                            JSONObject json = new JSONObject(s);
                            int status = json.getInt("status");
                            String msg = json.getString("msg");
                            if(status==0) {
                                GLOBAL.username=username;
                                GLOBAL.password =password;
                                JSONArray jsonArray = json.getJSONArray("userData");

                                if (json.getJSONArray("userData") == null){

                                    Toast.makeText(LoginActivity2.this, "this cadet login successfully", Toast.LENGTH_SHORT).show();
                                }


                                ArrayList<LoginModel> loginModels = new ArrayList<>();

                                for (int i = 0; i < jsonArray.length(); i++)  {

                                    LoginModel loginModel = new LoginModel();

                                    JSONObject localJson = jsonArray.getJSONObject(i);

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
                                    loginModel.setId(localJson.getString("id"));



                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString("username", username);
                                    editor.putString("password", password);
                                    editor.putString("user_type",Accounnt_type);
                                    editor.apply();

                                    if (status==0){
                                        Intent intent = new Intent(LoginActivity2.this, Main2Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Intent intent = new Intent(LoginActivity2.this, Signup_oneActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            } else {

                                Log.d("Login", s.toString());
                                progress.dismiss();
                                Toast.makeText(context, "Invalid User Name or Password", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progress.dismiss();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "Some issue in loading" + volleyError, Toast.LENGTH_LONG).show();
                        progress.dismiss();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                params.put("username", username);
                params.put("password", password);
                params.put("status", "Login");
                params.put("user_type","ncc");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity2.this);
        requestQueue.add(stringRequest);
    }

    ////////////////////////////////////////////////////////////

    private boolean onClickSearch(final MotionEvent event) {
        // do something
        pwdFlag = !pwdFlag;
        if (pwdFlag) {
            password.setInputType(InputType.TYPE_CLASS_TEXT);
            password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off_eye, 0);
        } else {
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_20, 0);
        }

        event.setAction(MotionEvent.ACTION_CANCEL);
        return false;

    }

    ///////////////////////////////////////
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(password)) ret = false;
        if (!Validation.hasText(username)) ret = false;
        return ret;

    }

    ///////////////////////////////////
    public void goHome(View view) {
        if(NetworkCheck.checkInternet(mainRoot,context)) {
            GLOBAL.globalLoginModel = new LoginModel();
            Log.e("json", GLOBAL.globalLoginModel.toString());
            GLOBAL.globalLoginModel.setName_of_cadet("Guest User");
            GLOBAL.globalLoginModel.setUsername("Guest User");
            /*GLOBAL.loginType=GLOBAL.GUESTLOGIN;*/
            startActivity(new Intent(LoginActivity2.this, Main2Activity.class));
            finish();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    public void goFargotPwd(View view) {
        if (NetworkCheck.checkInternet(mainRoot,context)){
            startActivity(new Intent(context, ForgetPasswordActivity.class));
            finish();}
    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        LoginActivity2.super.onBackPressed();
                    }
                }).create().show();
    }

}




