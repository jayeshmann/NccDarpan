package com.tsa.nccapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
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
import com.tsa.nccapp.Network.NetworkCheck;
import com.tsa.nccapp.adapter.CategoryListAdapter;
import com.tsa.nccapp.models.CatListModel;
import com.tsa.nccapp.models.SubjectList;
import com.tsa.nccapp.utils.GLOBAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CategoryListActivity extends AppCompatActivity {
    CategoryListAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<CatListModel> listDataHeader;
    HashMap<String, ArrayList<SubjectList>> listDataChild;
    private TextView frg_title;
    private LinearLayout mainRoot;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(CategoryListActivity.this, R.color.light_vilate));
        }

        context=CategoryListActivity.this;

        // get the listview
        expListView = findViewById(R.id.lvExp);
        mainRoot=findViewById(R.id.main_root);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d("onGroupClick:", "worked");
                parent.expandGroup(groupPosition);
                return false;
            }
        });

        frg_title = findViewById(R.id.frg_title);
        frg_title.setText("Category");

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        expListView.setIndicatorBounds(width - GetPixelFromDips(70), width - GetPixelFromDips(10));

        listDataChild = new HashMap<>();

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()

        {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,

                                        int groupPosition, long id) {
                //parent.expandGroup(groupPosition);

                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                Intent intent=new Intent(CategoryListActivity.this, ChapterListActivity.class);
                intent.putExtra("id",listDataChild.get(listDataHeader.get(groupPosition).getCategoryName()).get(childPosition).getSubjectId());
                intent.putExtra("c_no",listDataHeader.get(groupPosition).getCategoryId());
                intent.putExtra("sub_name",listDataChild.get(listDataHeader.get(groupPosition).getCategoryName()).get(childPosition).getSubjectName());
                intent.putExtra("subjectID",listDataChild.get(listDataHeader.get(groupPosition).getCategoryName()).get(childPosition).getSubjectId());

                startActivity(intent);
                finish();
                return false;
            }
        });

        if(NetworkCheck.checkInternet(mainRoot,context));
        getCategory();
    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    public void getCategory() {
        //Showing the progress dialog
        final ProgressDialog progress = new ProgressDialog(CategoryListActivity.this);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GLOBAL.baseURL + "sanya_shakti_api.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {

                            //JSONObject json = new JSONObject(s);
                            JSONArray chapList1 = new JSONArray(s);

                            ArrayList<CatListModel> catListModels = new ArrayList<>();

                            for (int i = 0; i < chapList1.length(); i++) {

                                CatListModel catListModel=new CatListModel();
                                JSONObject localJson=chapList1.getJSONObject(i);
                                catListModel.setCategoryName(localJson.getString("category_name"));
                                catListModel.setCategoryId(localJson.getString("category_id"));

                                JSONArray subject_list=localJson.getJSONArray("subject_list");

                                ArrayList<SubjectList> subjectListListView=new ArrayList<>();
                                for(int j=0;j<subject_list.length();j++)
                                {
                                    SubjectList subjectList=new SubjectList();
                                    JSONObject subjectJson=subject_list.getJSONObject(j);
                                    subjectList.setSubjectId(subjectJson.getString("subject_id"));
                                    subjectList.setSubjectName(subjectJson.getString("subject_name"));

                                    subjectListListView.add(subjectList);
                                }
                                catListModel.setSubjectList(subjectListListView);

                                catListModels.add(catListModel);

                                listDataChild.put(catListModel.getCategoryName(),subjectListListView);
                            }
                            listDataHeader=catListModels;

                            ///////////////////////////////////////////////////////////////////////////////
                            listAdapter = new CategoryListAdapter(CategoryListActivity.this, listDataHeader, listDataChild);

                            // setting list adapter
                            expListView.setAdapter(listAdapter);
                            ///////////////////////////////////////////////////////////////////////////////
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
                        Toast.makeText(CategoryListActivity.this, "Some issue in loading" + volleyError, Toast.LENGTH_LONG).show();
                        progress.dismiss();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("category_status", "yes");


                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(CategoryListActivity.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CategoryListActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
