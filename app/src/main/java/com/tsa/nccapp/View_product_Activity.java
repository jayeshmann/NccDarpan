package com.tsa.nccapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.tsa.nccapp.adapter.Exam_ViewAdapter;
import com.tsa.nccapp.adapter.ViewProductAdapter;
import com.tsa.nccapp.models.Exam_listModel;
import com.tsa.nccapp.models.Product_view;
import com.tsa.nccapp.utils.GLOBAL;
import com.tsa.nccapp.utils.SaveSharedPrefernces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class View_product_Activity extends AppCompatActivity {
    ArrayList<Product_view> product_view;
    ArrayList<Exam_listModel> exam_listModels;
    RecyclerView recycler_sub_products;
    ViewProductAdapter viewProductAdapter;

    LinearLayoutManager layoutManager;
    TextView text_view_products;
    TextView text_view_produ;
    TextView text_user;
    private Boolean bool1, bool2;

    TextView btn_view_exams;
    LinearLayout linear_ncc_testseries;
    TextView text_user_product,text_exam_details;
    ImageView header_back;


    private Bundle bundle;
    String prod_id,subprod_id,exam_cert;
    String user_id;
    private Context context;
    String id;
    LinearLayout linear_products;


    SaveSharedPrefernces ssp;

    ArrayList<Product_view> productModels = new ArrayList<>();
    ArrayList<Product_view> product_Models = new ArrayList<>();
    ArrayList<Exam_listModel> exam_list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_product_);
        text_view_products = (TextView) findViewById(R.id.text_view_products);
        text_view_produ = (TextView) findViewById(R.id.text_view_produ);
        text_user = (TextView) findViewById(R.id.text_user);
        text_user_product = (TextView)findViewById(R.id.text_user_product);

        linear_ncc_testseries = (LinearLayout)findViewById(R.id.linear_ncc_testseries);

        text_exam_details = (TextView)findViewById(R.id.text_exam_details);

        header_back = (ImageView)findViewById(R.id.header_back);
        linear_products = (LinearLayout)findViewById(R.id.linear_products);

        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back =new Intent(View_product_Activity.this,Checkout.class);
                startActivity(back);
            }
        });


        recycler_sub_products = (RecyclerView) findViewById(R.id.recycler_sub_products);
        btn_view_exams = (TextView) findViewById(R.id.btn_view_exams);

        btn_view_exams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exam = new Intent(View_product_Activity.this, Exam_CardActivity.class);
                exam.putExtra("prod_id","prod_id");
                exam.putExtra("subprod_id","subprod_id");
                exam.putExtra("exam_cert","exam_cert");
                startActivity(exam);

                }
        });

        String id = getIntent().getStringExtra("id");
        Log.d("id", "id");

        View_products(user_id);

        text_view_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                recycler_sub_products.setVisibility(View.VISIBLE);
                linear_products.setVisibility(View.VISIBLE);

                }

        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        recycler_sub_products.setLayoutManager(layoutManager);

    }


    public void View_products(final String user_id) {

        final ProgressDialog progress = new ProgressDialog(View_product_Activity.this);
        progress.show();
        progress.dismiss();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "userProducts.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("productData", s.toString());
                        try {
                            JSONObject json = new JSONObject(s);
                            int status = json.getInt("status");


                            if (status == 0) {

                                JSONArray jsonArray = json.getJSONArray("productData");

                                ArrayList<Product_view> productModel = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Product_view product_view = new Product_view();
                                    JSONObject localJson = jsonArray.getJSONObject(i);

                                    product_view.setUser_id(localJson.getString("user_id"));
                                    product_view.setProduct_id(localJson.getString("product_id"));
                                    product_view.setProductName(localJson.getString("productName"));
                                    product_view.setProductDesc(localJson.getString("productDesc"));
                                    product_view.setTotal_subproduct(localJson.getString("total_subproduct"));

                                    productModel.add(product_view);


                                    JSONArray sub_product = localJson.getJSONArray("sub_product");
                                    for (int j = 0; j < sub_product.length(); j++) {
                                        JSONObject subproductobject = sub_product.getJSONObject(j);
                                        product_view.setSubproduct_id(subproductobject.getString("subproduct_id"));
                                        product_view.setSubProductName(subproductobject.getString("subProductName"));
                                        product_view.setCert_type(subproductobject.getString("cert_type"));
                                        product_view.setAmount_paid(subproductobject.getString("amount_paid"));
                                        product_view.setPurchased_date(subproductobject.getString("purchased_date"));
                                        product_view.setExpiry_date(subproductobject.getString("expiry_date"));
                                        product_view.setExpire_status(subproductobject.getString("expire_status"));

                                        product_Models.add(product_view);
                                    }

//                                            productModel.add(product_view);
//                                    productModel.addAll(product_Models);

//                                            viewProductAdapter= new ViewProductAdapter(product_view,View_product_Activity.this);
//                                            recycler_sub_products.setAdapter(viewProductAdapter);

                                    viewProductAdapter = new ViewProductAdapter(View_product_Activity.this, product_Models);
                                    recycler_sub_products.setAdapter(viewProductAdapter);
                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                                    recycler_sub_products.setLayoutManager(mLayoutManager);
                                    recycler_sub_products.setItemAnimator(new DefaultItemAnimator());
                                    recycler_sub_products.setFocusable(false);

                                }


                            } else {

                                Log.d("Login", s.toString());
                                progress.dismiss();
                                Toast.makeText(context, "YOU HAVE ONE ERROR", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progress.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "Some issue in loading" + volleyError, Toast.LENGTH_LONG).show();
                        progress.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();

                params.put("user_id", "username");
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(View_product_Activity.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {

        }
}



