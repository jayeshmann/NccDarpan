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
import com.tsa.nccapp.LoginActivity2;
import com.tsa.nccapp.R;
import com.tsa.nccapp.Signup_oneActivity;
import com.tsa.nccapp.Signup_twoActivity;
import com.tsa.nccapp.models.CityModel;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Cadet_FormFragment extends Fragment {
    TextView  exam_type;
    EditText rejiment_no, rank, name_cadet, email, mobile, father_name, unit, group, directorate;
    Button register1;
    String[] Exam_list = {"Exam Type", "A", "B", "C"};
    String MobilePattern;
    TextView join_as;
    TextView cadet;
    TextView institute_year;
    ImageView arrow_down, arrow_up;
    LinearLayout linear_signup;
    String[] Cadet_list = {"Cadet", "ANO", "Staff"};
    private String user_type;
    String [] Institute_list = {"1 year","2 year","3 year"};
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private ProgressDialog progress_dialog;
    private String progress_dialog_msg = "", tag_string_req = "string_req";
    private final int SHOW_PROG_DIALOG = 0, HIDE_PROG_DIALOG = 1, LOAD_QUESTION_SUCCESS = 2;
    private String usertype;
    ArrayList<CityModel> cityArray = new ArrayList<>();

    public Cadet_FormFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cadet__form, container, false);

        MobilePattern = "[0-9]{8,15}";

//        exam_type = (TextView) rootView.findViewById(R.id.exam_type);

        rejiment_no = (EditText) rootView.findViewById(R.id.rejiment_no);
        rank = (EditText) rootView.findViewById(R.id.rank);
        name_cadet = (EditText) rootView.findViewById(R.id.name_cadet);
        email = (EditText) rootView.findViewById(R.id.email);
        mobile = (EditText) rootView.findViewById(R.id.mobile);
        father_name = (EditText) rootView.findViewById(R.id.father_name);
        unit = (EditText) rootView.findViewById(R.id.unit);
        group = (EditText) rootView.findViewById(R.id.group);
        directorate = (EditText) rootView.findViewById(R.id.directorate);
        register1 = (Button) rootView.findViewById(R.id.register1);
//        join_as = (TextView) rootView.findViewById(R.id.join_as);

        institute_year = (TextView)rootView.findViewById(R.id.Institute_year);

        institute_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    View focusedView = getActivity().getCurrentFocus();

                } catch (Exception e) {

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Institute");
                builder.setItems(Institute_list, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        institute_year.setText(Institute_list[position].toString());
                        Log.d("<><><><>", "???????" + institute_year.getText().toString().replaceAll(" Year", ""));

                    }

                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });



//             exam_type.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(
//                            Context.INPUT_METHOD_SERVICE);
//                    View focusedView = getActivity().getCurrentFocus();
//
//                } catch (Exception e) {
//
//                }
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setTitle("Select Exam");
//                builder.setItems(Exam_list, new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int position) {
//
//                        exam_type.setText(Exam_list[position].toString());
//
//                        Log.d("<><>><>", "???????" + exam_type.getText().toString().replaceAll("Exam Type", ""));
//
//                    }
//
//                });
//                Dialog dialog = builder.create();
//                dialog.show();
//
//            }
//        });
//


        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rejiment_no.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter  rejiment number", Toast.LENGTH_SHORT).show();
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() && !email.getText().toString().equals("")) {

                    Toast.makeText(getContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                if (mobile.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter Mobile number", Toast.LENGTH_SHORT).show();
                } else if (rank.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter Rank ", Toast.LENGTH_SHORT).show();
                } else if (name_cadet.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter Cadet name", Toast.LENGTH_SHORT).show();
                } else if (father_name.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "please enter Father name", Toast.LENGTH_LONG).show();
                } else if (unit.getText().toString().equals("Expiry Date")) {
                    Toast.makeText(getContext(), "Please Enter Unit", Toast.LENGTH_LONG).show();
                } else if (group.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter group", Toast.LENGTH_LONG).show();
                } else if (directorate.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please Directorate  ", Toast.LENGTH_LONG).show();

                }
                else {
                    register1();
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




       private void register1() {
//        mHandler.sendEmptyMessage(SHOW_PROG_DIALOG);
//        progress_dialog_msg = "loading...";

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
//                           Toast.makeText(getActivity(), "OK button click ", Toast.LENGTH_SHORT).show();show
                           Intent i = new Intent(getActivity(),LoginActivity2.class);
                           i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                           startActivity(i);

                       }
                   })
                   .setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
//                           Toast.makeText(getActivity(), "CANCEL button click ", Toast.LENGTH_SHORT).show();

                           dialog.cancel();
                           progress_dialog.dismiss();
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
                    String user_id = String.valueOf(jsonObject.get("user"));
                    GLOBAL.user_type=String.valueOf(1);

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
//                mHandler.sendEmptyMessage(HIDE_PROG_DIALOG);

                Toast.makeText(getActivity(), "Some issues in loading" + volleyError, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("membership_status", "ncc");
                params.put("regiment_number", rejiment_no.getText().toString());
                params.put("rank", rank.getText().toString());
                params.put("name_of_cadet", name_cadet.getText().toString());
                params.put("email", email.getText().toString());
                params.put("mobile", mobile.getText().toString());
                params.put("father_name", father_name.getText().toString());
//                params.put("exam_type", exam_type.getText().toString());
                params.put("unit", unit.getText().toString());
                params.put("ncc_group", group.getText().toString());
                params.put("directorate", directorate.getText().toString());
                params.put("instt_year",institute_year.getText().toString());
                params.put("joinAs_id", String.valueOf(GLOBAL.CADET));

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


