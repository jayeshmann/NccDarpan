package com.tsa.nccapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tsa.nccapp.custom.CustomActivity;
import com.tsa.nccapp.custom.CustomVolleyRequest;
import com.tsa.nccapp.custom.RightDrawableOnTouchListener;
import com.tsa.nccapp.utils.GLOBAL;
import com.tsa.nccapp.validation.Validation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

public class MyProfileActivity extends CustomActivity {
    private int mYear, mMonth, mDay;
    private TextView userEmailTV;
    private TextView nameTV;
    private TextView genderTV;
    private TextView dobTV;
    private EditText phoneNumberTV;
    private TextView fullNameTV;
    private Context context;
    private Button save;

    private NetworkImageView userImage;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile2);

        context=MyProfileActivity.this;

        //Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH+1);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {

        userImage=findViewById(R.id.user_profile_photo);

        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(GLOBAL.photoURL, ImageLoader.getImageListener(userImage,
                R.drawable.user, android.R.drawable
                        .ic_dialog_alert));
        userImage.setImageUrl(GLOBAL.photoURL, imageLoader);

        userEmailTV = findViewById(R.id.user_email);
        userEmailTV.setText(GLOBAL.globalUserModel.getEmail());

        nameTV = findViewById(R.id.user_name);
        nameTV.setText(GLOBAL.globalUserModel.getUsername());

        genderTV = findViewById(R.id.user_gender);
        genderTV.setText(GLOBAL.globalUserModel.getGender());
        genderTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (genderTV.getText().toString().equalsIgnoreCase("Male"))
                    genderTV.setText("Female");
                else
                    genderTV.setText("Male");
            }
        });


        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidation())
                    updateProfile(GLOBAL.globalUserModel.getName(),""+phoneNumberTV.getText(),""+dobTV.getText(),GLOBAL.globalUserModel.getPassword(),""+genderTV.getText());
            }
        });


        dobTV = findViewById(R.id.user_dob);
        dobTV.setText(GLOBAL.globalUserModel.getDob());

        dobTV.setOnTouchListener(new RightDrawableOnTouchListener(dobTV) {
            @Override
            public boolean onDrawableTouch(final MotionEvent event) {
                return onClickSearch(event,2);
            }
        });


        phoneNumberTV = findViewById(R.id.user_phone);
        phoneNumberTV.setText(GLOBAL.globalUserModel.getMobile());

        phoneNumberTV.setOnTouchListener(new RightDrawableOnTouchListener(phoneNumberTV) {
            @Override
            public boolean onDrawableTouch(final MotionEvent event) {
                return onClickSearch(event,1);
            }
        });


        fullNameTV = findViewById(R.id.user_fullname);
        fullNameTV.setText(GLOBAL.globalUserModel.getName());
    }

    private boolean onClickSearch(final MotionEvent event,int flag) {
        // do something
        event.setAction(MotionEvent.ACTION_CANCEL);

        if (flag == 1) {
                phoneNumberTV.setEnabled(true);
        } else if (flag == 2) {
            new DatePickerDialog(MyProfileActivity.this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            dobTV.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                        }
                    }, mYear, mMonth, mDay).show();
        } else if (flag == 3) {

            if (genderTV.getText().toString().equalsIgnoreCase("Male"))
                genderTV.setText("Female");
            else
                genderTV.setText("Male");
        }

        return false;
    }

    public void updateProfile(final String name,final String phone,
                              final String dob,final String pwd,final String gender) {
        //Showing the progress dialog
        final ProgressDialog progress=new ProgressDialog(context);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL+"update_profile_api.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject json=new JSONObject(s);
                            Log.e("json",json.toString());
                            String msg=json.getString("status");
                            if(msg.equals("0")) {
                                Toast.makeText(context, "Successfully Updated", Toast.LENGTH_LONG).show();
                                GLOBAL.globalUserModel.setGender(""+genderTV.getText());
                                GLOBAL.globalUserModel.setMobile(""+phoneNumberTV.getText());
                                GLOBAL.globalUserModel.setDob(""+dobTV.getText());
                            }
                            else
                                Toast.makeText(context, "Some Issues in Updation", Toast.LENGTH_LONG).show();
                            //JSONArray chapList1=json.getJSONArray("1");

                            progress.dismiss();
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
                        // progress.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("name", ""+name);
                params.put("mobile", ""+phone);
                params.put("dob",""+ dob);
                params.put("password",""+pwd);
                params.put("gender", ""+gender);
                params.put("user_id", ""+GLOBAL.globalUserModel.getId());

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

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.isPhoneNumber(phoneNumberTV, true)) ret = false;
        return ret;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyProfileActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();
    }

}
