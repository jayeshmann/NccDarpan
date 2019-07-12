package com.tsa.nccapp.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.tsa.nccapp.LoginActivity2;
import com.tsa.nccapp.R;
import com.tsa.nccapp.Signup_twoActivity;
import com.tsa.nccapp.models.CityModel;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ANo_threeFragment extends Fragment {
    EditText ano_name,ano_email,ano_mobile,ano_unit,ano_group,ano_directorate;
    TextView Ano_Institute_year;
   private Spinner state_ano,district_ano;
    Button btn_register_ano;
    String MobilePattern;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ArrayList<CityModel> cityArray = new ArrayList<>();
    String [] Institute_list = {"1st year","2nd year","3rd year"};
    private ProgressDialog progress_dialog;
    private String progress_dialog_msg = "", tag_string_req = "string_req";
    private final int SHOW_PROG_DIALOG = 0, HIDE_PROG_DIALOG = 1, LOAD_QUESTION_SUCCESS = 2;



    public ANo_threeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ano_three, container, false);
        MobilePattern = "[0-9]{8,15}";

        // Edie text
        ano_name = (EditText)rootView.findViewById(R.id.ano_name);
        ano_email = (EditText)rootView.findViewById(R.id.ano_email);
        ano_mobile = (EditText)rootView.findViewById(R.id.ano_mobile);
        ano_unit = (EditText)rootView.findViewById(R.id.ano_unit);
        ano_group = (EditText)rootView.findViewById(R.id.ano_group);
        ano_directorate = (EditText)rootView.findViewById(R.id.ano_directorate);

       // Text view
//        Ano_Institute_year = (TextView)rootView.findViewById(R.id.Ano_Institute_year);
        // spinner
        state_ano = (Spinner)rootView.findViewById(R.id.state_ano);
        district_ano = (Spinner)rootView.findViewById(R.id.district_ano);

//        Ano_Institute_year.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Ano_Institute_year.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        try {
//                            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(
//                                    Context.INPUT_METHOD_SERVICE);
//                            View focusedView = getActivity().getCurrentFocus();
//
//                        } catch (Exception e) {
//
//                        }
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                        builder.setTitle("Select Institute");
//                        builder.setItems(Institute_list, new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialog, int position) {
//
//                                Ano_Institute_year.setText(Institute_list[position].toString());
//                                Log.d("<><>><>", "???????" + Ano_Institute_year.getText().toString().replaceAll(" Year", ""));
//
//                            }
//
//                        });
//                        Dialog dialog = builder.create();
//                        dialog.show();
//                    }
//                });
//
//
//            }
//        });
        ArrayList<String> stateArrayList = new ArrayList<>();
        Collections.addAll(stateArrayList, getResources().getStringArray(R.array.state));
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item1, stateArrayList);
        state_ano.setAdapter(stateAdapter);
        if (GLOBAL.cadetFormModel != null) {
            state_ano.setSelection(stateArrayList.indexOf(GLOBAL.cadetFormModel.getStateName()));
        }

        state_ano.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityList(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Button
        btn_register_ano = (Button)rootView.findViewById(R.id.btn_register_ano);

        btn_register_ano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ano_name.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter  name", Toast.LENGTH_SHORT).show();
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(ano_email.getText().toString()).matches() && !ano_email.getText().toString().equals("")) {

                    Toast.makeText(getContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                if (ano_mobile.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter Mobile number", Toast.LENGTH_SHORT).show();

                } else if (ano_unit.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter Unit ", Toast.LENGTH_SHORT).show();
                }
//                    else if (Ano_Institute_year.getText().toString().equals("Expiry Date")) {
//                        Toast.makeText(getContext(), "Please Enter year", Toast.LENGTH_LONG).show();
//                    }

                 else if (ano_group.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter Group", Toast.LENGTH_SHORT).show();
                } else if (ano_directorate.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "please enter Directorate", Toast.LENGTH_LONG).show();

                }
                else {

                    register_Ano();
                }
            }
        });

        return rootView;
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
                progress_dialog = new ProgressDialog(getActivity(), android.app.AlertDialog.THEME_HOLO_LIGHT);
            } else {
                progress_dialog = new ProgressDialog(getActivity());
            }
            progress_dialog.setMessage(progress_dialog_msg);
            progress_dialog.setCancelable(false);
            progress_dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // hide progress
    private void hideProgDialog() {
        try {
            if (progress_dialog != null && progress_dialog.isShowing())
                progress_dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, locatCityArray);
                                district_ano.setAdapter(dataAdapter);
                                if (GLOBAL.cadetFormModel != null) {
                                    district_ano.setSelection(cityArray.indexOf(GLOBAL.cadetFormModel.getDistrictName()));
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
                        Toast.makeText(getActivity(), "Some issue in loading", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<String, String>();
                params.put("state_id", "" + id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }



    private void register_Ano() {

//        mHandler.sendEmptyMessage(SHOW_PROG_DIALOG);
//        progress_dialog_msg = "loading...";
//        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.show();
//        progressDialog.setMessage("Please check your mail to activate your account");
//        progressDialog.dismiss();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set title
        alertDialogBuilder.setTitle("Alert !!!");

        // set dialog message
        alertDialogBuilder
                .setMessage("Please check your mail to verify your account")
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
//                        Toast.makeText(getActivity(), "OK button click ", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getActivity(),LoginActivity2.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);


                    }
                })
                .setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        Toast.makeText(getActivity(), "CANCEL button click ", Toast.LENGTH_SHORT).show();

                        dialog.cancel();

                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "membership_api.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Log.e("json", jsonObject.toString());
                    String status = jsonObject.getString("status");
                    String msg = jsonObject.getString("msg");

                    if (status.equals("0")) {
                        Toast.makeText(getActivity(), "Successfully Registered", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getActivity(), LoginActivity2.class));
                    } else
                        Toast.makeText(getActivity(), "Email or Mobile already exists", Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mHandler.sendEmptyMessage(HIDE_PROG_DIALOG);
                Toast.makeText(getActivity(), "Some issues in loading" + volleyError, Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("membership_status", "ncc");
//                params.put("rank", rank.getText().toString());
                params.put("name_of_cadet", ano_name.getText().toString());
                params.put("email", ano_email.getText().toString());
                params.put("mobile", ano_mobile.getText().toString());
//                params.put("father_name", father_name.getText().toString());
//                params.put("exam_type", exam_type.getText().toString());
                params.put("unit", ano_unit.getText().toString());
                params.put("instt_year",Ano_Institute_year.getText().toString());
                params.put("ncc_group", ano_group.getText().toString());
                params.put("directorate", ano_directorate.getText().toString());
                params.put("joinAs_id", String.valueOf(GLOBAL.ANO));
                Log.d("successfulllyregistered", "success");

                return params;


                //returning parameters

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(stringRequest);

    }

    }

