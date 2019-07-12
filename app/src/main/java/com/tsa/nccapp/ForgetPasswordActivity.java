package com.tsa.nccapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tsa.nccapp.custom.MyDialogue;
import com.tsa.nccapp.utils.GLOBAL;
import com.tsa.nccapp.validation.Validation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tsa.nccapp.custom.MyDialogue;
import com.tsa.nccapp.utils.GLOBAL;
import com.tsa.nccapp.validation.Validation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

    public class ForgetPasswordActivity extends AppCompatActivity {

        private Context context;
        private EditText email;
        public MyDialogue.DialogOnClickListener myListener;
        MyDialogue myDialogue;
        private String satus="";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forget_password);

            context=ForgetPasswordActivity.this;
            email=findViewById(R.id.email);

            myListener = new MyDialogue.DialogOnClickListener() {
                @Override
                public void onOkClick() {

                    if(satus=="0"){
                        startActivity(new Intent(context,LoginActivity2.class));
                        finish();}
                    else
                    {
                        myDialogue.dismiss();
                    }
                }

                @Override
                public void onCancleClick() {
                    myDialogue.dismiss();
                }
            };

        }


        public void forgotPassword(final String email) {
            //Showing the progress dialog
            final ProgressDialog progress=new ProgressDialog(context);
            progress.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,  GLOBAL.baseURL + "forgot_password.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            try {
                                JSONObject json=new JSONObject(s);
                                Log.d("Login", s.toString());
                                satus=json.getString("status");
                                String message=json.getString("message");

                                if(satus.equals("0"))
                                {
                                    myDialogue = new MyDialogue(context, myListener, message, true, false);
                                    myDialogue.show();
                                    myDialogue.setCancelable(false);
                                }

                                if(satus.equals("1"))
                                {
                                    myDialogue = new MyDialogue(context, myListener, message, true, false);
                                    myDialogue.show();
                                }
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
                    params.put("email", email);

                    //returning parameters
                    return params;
                }
            };

            //Creating a Request Queue
            RequestQueue requestQueue = Volley.newRequestQueue(context);

            //Adding request to the queue
            requestQueue.add(stringRequest);
        }

        private boolean checkValidation() {
            boolean ret = true;

            if (!Validation.isEmailAddress(email, true)) ret = false;
            return ret;
        }

        public void register(View view) {
            if (checkValidation())
            {

                String email=""+this.email.getText();

                forgotPassword(email);
            }
        }

        @Override
        public void onBackPressed() {
            Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity2.class);
            startActivity(intent);
            finish();
        }
    }
