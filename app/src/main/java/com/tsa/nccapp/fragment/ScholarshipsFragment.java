package com.tsa.nccapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;


public class ScholarshipsFragment extends Fragment {
    View view;

    private String[] title=new String[]{"SAHARA SCHOLARSHIP","CADETS WELFARE SOCIETY","CHIEF MINISTERS FUND"};

    private String[] subTitle=new String[]{"Non Professional",
            "Professional","INTRODUCTION","ELIGIBILITY",
            "INTRODUCTION & ELIGIBILITY"};

    private String[] path=new String[]{"SCHOLARSHIPS/SAHARA_SCHOLARSHIP/non_professional.doc",
            "SCHOLARSHIPS/SAHARA_SCHOLARSHIP/professional.doc",
            "SCHOLARSHIPS/CADETS_WELFARE_SOCIETY/INTRODUCTION.docx",
            "SCHOLARSHIPS/CADETS_WELFARE_SOCIETY/ELIGIBILITY.doc",
            "SCHOLARSHIPS/CHIEF_MINISTERS_FUND/INTRODUCTION_ELIGIBILITY.doc"};

    private View[] viewArr=new View[4];

    private LinearLayout parent;
    private LinearLayout include;

    private String url=GLOBAL.baseURL+"content/INCENTIVES_AND_BENEFITS/";

    private int count=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_incentive_sub, content_frame, false);
        init();
        return view;
    }

    private void init() {

        parent=view.findViewById(R.id.parent);

        for(int i=0;i<subTitle.length;i++)
        {
            include = (LinearLayout) View.inflate(getActivity(),
                    R.layout.onlytext_layout, null);

            if(i==0||i==2||i==4){
                TextView option1=new TextView(getActivity());
                parent.addView(option1);
                createTV(option1,title[count++]);
            }

            TextView tempTv=include.findViewById(R.id.title);
            tempTv.setText(subTitle[i]);
            parent.addView(include);

            final int finalI = i;
            tempTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GLOBAL.url=url+path[finalI];
                    ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, subTitle[finalI], true);
                }
            });

        }

        /////////////////////////////////////////////////////////
    }

    void createTV(TextView tv,String title)
    {
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv.setTextSize(25);
        tv.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        tv.setText(title);
        tv.setPadding(20,5,0,5);
    }

    @Override
    public void onStop() {
        super.onStop();
        count=0;
    }
}