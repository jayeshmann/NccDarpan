package com.tsa.nccapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class ContactUsActivity extends AppCompatActivity {

    TextView userName;
    TextView email;
    TextView frg_title;
    Spinner subject;
    EditText msg;
    Button clear;
    Button send;
    Context context;
    MyDialogue myDialogue;
    MyDialogue.DialogOnClickListener dialogOnClickListener;
    MyDialogue.DialogOnClickListener dialogOnClickListener1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        context=ContactUsActivity.this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(ContactUsActivity.this, R.color.light_vilate));
        }

        dialogOnClickListener=new MyDialogue.DialogOnClickListener() {
            @Override
            public void onOkClick() {
                startActivity(new Intent(ContactUsActivity.this,Main2Activity.class));
                finish();
            }

            @Override
            public void onCancleClick() {
              //myDialogue.dismiss();
            }
        };

        dialogOnClickListener1=new MyDialogue.DialogOnClickListener() {
            @Override
            public void onOkClick() {
                myDialogue.dismiss();
            }

            @Override
            public void onCancleClick() {
                //myDialogue.dismiss();
            }
        };

        init();
    }

    private void init() {

        userName=findViewById(R.id.user_name);
        userName.setText(GLOBAL.globalUserModel.getUsername());
        email=findViewById(R.id.user_email);
        email.setText(GLOBAL.globalUserModel.getEmail());
        send=findViewById(R.id.send);
        clear=findViewById(R.id.clear);
        frg_title=findViewById(R.id.frg_title);
        frg_title.setText("Query/Feedback");
         subject=findViewById(R.id.subject);
         msg=findViewById(R.id.msg);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg.setText("");
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((""+msg.getText()).trim().equals("")) {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    contactUs("" + msg.getText(), "" + subject.getSelectedItem());
                }
            }
        });
    }


    public void contactUs(final String msg,final String subject) {
        //Showing the progress dialog
        final ProgressDialog progress=new ProgressDialog(context);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL+"contact_us_api.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject json=new JSONObject(s);
                            Log.e("json",json.toString());
                            String msg=json.getString("status");
                            if(msg.equals("0")) {
                                myDialogue=new MyDialogue(ContactUsActivity.this,dialogOnClickListener,"Your Query Successfully Submited\n\nWe will Reply Soon",true,false);
                                myDialogue.show();
                            }
                            else
                            {
                                myDialogue=new MyDialogue(ContactUsActivity.this,dialogOnClickListener1,"Some Issues in Sending\n\n Please Try Again",true,false);
                                myDialogue.show();
                            }
                            //JSONArray chapList1=json.getJSONArray("1");

                            progress.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error",e.toString());
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
                params.put("user_email", GLOBAL.globalUserModel.getEmail());
                params.put("user_name", GLOBAL.globalUserModel.getUsername());
                params.put("message", msg);
                params.put("subject", subject);

                Log.e("param",params.toString());
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
        startActivity(new Intent(ContactUsActivity.this,Main2Activity.class));
        finish();
    }
}
