package com.tsa.nccapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tsa.nccapp.models.CityModel;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

public class Signup_twoActivity extends AppCompatActivity {
    EditText cadet_name, your_mail,mobile_no,father_name,signup_cit;
    TextView signup_state,joined_ncc;
    Button register_two;
    TextView others;
//    String[] Exam_list = {"Bihar","Delhi", "Telangana", "Manipur","Utthrakhand","haryana","tamilnadu","Gujrat","Assam","uttar Pradesh","West Bengal","Sikkim","Andhra Pradesh","Madhya pradesh",
//   "Karnataka","Kerala","Rajasthan","Maharashtra","punjab",};
    String MobilePattern;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ArrayList<CityModel> cityArray = new ArrayList<>();

    private Spinner district;
    private ProgressDialog progress_dialog;
    private String progress_dialog_msg = "", tag_string_req = "string_req", account_balance;
    private final int SHOW_PROG_DIALOG = 0, HIDE_PROG_DIALOG = 1, LOAD_QUESTION_SUCCESS = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_two);
        MobilePattern = "[0-9]{8,15}";



        cadet_name = (EditText)findViewById(R.id.cadet_name);
        your_mail = (EditText)findViewById(R.id.your_mail);

        mobile_no = (EditText)findViewById(R.id.mobile_np);
        father_name = (EditText)findViewById(R.id.father_name);
//        signup_unit = (EditText)findViewById(R.id.signup_unit);
//        signup_group = (EditText)findViewById(R.id.signup_group);
//        signup_city = (EditText)findViewById(R.id.signup_city);
//        signup_directorate = (EditText)findViewById(R.id.signup_directorate);

        register_two = (Button)findViewById(R.id.register_two);

        joined_ncc = (TextView)findViewById(R.id.joined_ncc);
        others = (TextView)findViewById(R.id.others);


        Spinner state = findViewById(R.id.state_sp);
        ArrayList<String> stateArrayList = new ArrayList<>();
        Collections.addAll(stateArrayList, getResources().getStringArray(R.array.state));
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item1, stateArrayList);
        state.setAdapter(stateAdapter);
        if (GLOBAL.cadetFormModel != null) {
            state.setSelection(stateArrayList.indexOf(GLOBAL.cadetFormModel.getStateName()));
        }



        district = findViewById(R.id.district_sp);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityList(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        joined_ncc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                joined_ncc.setTextColor(R.color.black);
                others.setTextColor(R.color.gray);
                Intent joined_ncc = new Intent(Signup_twoActivity.this,Signup_oneActivity.class);
                joined_ncc.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(joined_ncc);


            }
        });

        register_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cadet_name.getText().toString().equals("")) {
                    Toast.makeText(Signup_twoActivity.this, "Please enter  name", Toast.LENGTH_SHORT).show();
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(your_mail.getText().toString()).matches()&&! your_mail.getText().toString().equals("")){

                    Toast.makeText(Signup_twoActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                if (mobile_no.getText().toString().equals("")){
                    Toast.makeText(Signup_twoActivity.this,"Please enter Mobile number",Toast.LENGTH_SHORT).show();
                }
//
                register_two();
            }

        });
    }

    Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case SHOW_PROG_DIALOG:
                    showProgDialog();
                    break;

                case HIDE_PROG_DIALOG:
                    hideProgDialog();
                    break;

                case LOAD_QUESTION_SUCCESS:

                    break;

                default:
                    break;
            }

            return false;
        }

    });


    @SuppressLint("InlinedApi")
    private void showProgDialog() {
        progress_dialog = null;
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                progress_dialog = new ProgressDialog(Signup_twoActivity.this, AlertDialog.THEME_HOLO_LIGHT);
            } else {
                progress_dialog = new ProgressDialog(this);
            }
            progress_dialog.setMessage(progress_dialog_msg);
            progress_dialog.setCancelable(false);
            progress_dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void hideProgDialog() {

        try {
            if (progress_dialog != null && progress_dialog.isShowing())
                progress_dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    private void () {
//        mHandler.sendEmptyMessage(SHOW_PROG_DIALOG);
//        progress_dialog_msg = "loading";
//    }

    @Override
    public void onBackPressed() {
        Intent signup_one = new Intent(Signup_twoActivity.this,LoginActivity2.class);
        startActivity(signup_one);
        finish();
    }

    private void cityList(final int id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://internationalskills.co.in/nccdarpan/API/districtListAPI.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject json = new JSONObject(s);
                            String status = json.getString("status");
                            if (status.equals("0")) {
                                Log.d("page", "" +"page" );
                                JSONArray jsonArray = json.getJSONArray("distList");
                                ArrayList<String> locatCityArray = new ArrayList<>();
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject localJson = jsonArray.getJSONObject(i);
                                    CityModel cityModel = new CityModel();
                                    cityModel.setId(localJson.getString("id"));
                                    cityModel.setDistrictName(localJson.getString("district_name"));
                                    locatCityArray.add(localJson.getString("district_name"));
                                    cityArray.add(cityModel);
                                }

                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, locatCityArray);
                                district.setAdapter(dataAdapter);
                                if (GLOBAL.cadetFormModel != null) {
                                    district.setSelection(cityArray.indexOf(GLOBAL.cadetFormModel.getDistrictName()));
                                }
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        mHandler.sendEmptyMessage(HIDE_PROG_DIALOG);
                        Toast.makeText(getApplicationContext(), "Some issue in loading", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<String, String>();
                params.put("state_id", "" + id);
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    private void register_two() {
//        mHandler.sendEmptyMessage(SHOW_PROG_DIALOG);
//        progress_dialog_msg = "loading";
//
//        final ProgressDialog progressDialog = new ProgressDialog(Signup_twoActivity.this);
//        progressDialog.show();
//        progressDialog.setMessage("Please Check your mail to verify your account");

//        @SuppressLint("RestrictedApi") final ContextThemeWrapper con = new ContextThemeWrapper(this, R.style.AlertS);
//        android.app.AlertDialog dialog = new AlertDialog.Builder(con)
//                .setMessage("Please Check your mail to verify your account")
//                .setCancelable(false)
//                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                })
//                .show();
        //progressDialog.dismiss();

//
        AlertDialog.Builder alert;
        if (Build.VERSION.SDK_INT >= 11) {
            alert = new AlertDialog.Builder(Signup_twoActivity.this, AlertDialog.THEME_HOLO_LIGHT);
        } else {
            alert = new AlertDialog.Builder(Signup_twoActivity.this);
        }

        alert.setMessage("Please Check Your Mail to Verify Your Account");


        alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println(dialog);
                dialog.dismiss();
            }
        });


        try {
            Dialog dialog = alert.create();
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.show();
        }    catch (Exception e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "membership_api.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Log.e("json", jsonObject.toString());
                    String status = jsonObject.getString("status");
                    String msg = jsonObject.getString("msg");

                    if (status.equals("0")) {
                        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Signup_twoActivity.this, LoginActivity2.class));

                    } if (status.equals("1")) {
                        Toast.makeText(getApplicationContext(), "Email or Mobile already exists", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e)

                {   e.printStackTrace();
                }


            }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(),"Some issues in loading" + volleyError, Toast.LENGTH_LONG).show();
            }


        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters

                params.put("name_of_cadet", cadet_name.getText().toString());
                params.put("email", your_mail.getText().toString());
                params.put("mobile", mobile_no.getText().toString());
                params.put("membership_status", "other");
                params.put("joinAs_id", String.valueOf(GLOBAL.OTHER));
//                params.put("unit", signup_unit.getText().toString());
//                params.put("ncc_group", signup_group.getText().toString());
//                params.put("directorate", signup_directorate.getText().toString());

                Log.d("successfulllyregistered","success");

                return  params;


            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Signup_twoActivity.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);

    }



}


