package com.tsa.nccapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.tsa.nccapp.fragment.ANo_threeFragment;
import com.tsa.nccapp.fragment.Cadet_FormFragment;
import com.tsa.nccapp.fragment.Staff_Form_Fragment;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Signup_oneActivity extends AppCompatActivity {
    Spinner spinner_cadet;
    TextView joined_ncc, others, text_join_ncc;
    String[] Cadet_list = {"Join As","Cadet", "ANO", "Staff"};
    Fragment fragment = null;
    TextView join_as_Cadet,join_ano,join_staff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_one);

        spinner_cadet = (Spinner) findViewById(R.id.spinner_cadet);
        joined_ncc = (TextView) findViewById(R.id.joined_ncc);

        text_join_ncc = (TextView) findViewById(R.id.text_join_ncc);
        others = (TextView) findViewById(R.id.others);
        join_as_Cadet = (TextView)findViewById(R.id.join_as_Cadet);
//        join_ano = (TextView)findViewById(R.id.join_ano);
//        join_staff = (TextView)findViewById(R.id.join_staff);
//
//        join_as_Cadet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (fragment != null) {
//                            FragmentManager fragmentManager = getSupportFragmentManager();
//                            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//                        }
//            }
//        });


        ArrayList<String> CadetName = new ArrayList<>();
        Collections.addAll(CadetName, getResources().getStringArray(R.array.CadetName));
        ArrayAdapter<String> CadetAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item2, CadetName);
        spinner_cadet.setAdapter(CadetAdapter);


        List<String> categories = new ArrayList<String>();
        categories.add("Cadet");
        categories.add("ANO");
        categories.add("Staff");


        spinner_cadet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        fragment = new Cadet_FormFragment();

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                        }
                        break;
                    case 1:
                        fragment = new ANo_threeFragment();
                        if (fragment!=null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                        }
                        break;

                    case 2:
                        fragment =new  Staff_Form_Fragment();
                        if (fragment!=null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                            break;
                        }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")

            @Override
            public void onClick(View v) {
                others.setTextColor(R.color.black);
                joined_ncc.setTextColor(R.color.gray);

                Intent others = new Intent(Signup_oneActivity.this, Signup_twoActivity.class);
                others.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(others);
                finish();
            }
        });






    }
}

