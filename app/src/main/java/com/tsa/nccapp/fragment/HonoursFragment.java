package com.tsa.nccapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;


public class HonoursFragment extends Fragment {
View view;

    private String[] title=new String[]{"INTRODUCTION","AWARDS"};

    private String[] subTitle=new String[]{"INTRODUCTION","RAKSHA MANTRI PADAK","RAKSHA MANTRI COMMENDATION CARD",
            "DEFENCE SECRETARY'S COMMENDATION CARD","DG NCC COMMENDATION CARD"};

    private View[] viewArr=new View[4];

    private LinearLayout parent;
    private LinearLayout include;

    private String url=GLOBAL.baseURL+"content/INCENTIVES_AND_BENEFITS/";

    private String[] path=new String[]{"HONOURS_AND_AWARDS/INTRODUCTION.docx",
            "HONOURS_AND_AWARDS/RAKSHA_MANTRI_PADAK.docx",
            "HONOURS_AND_AWARDS/RAKSHA_MANTRI_COMMENDATION_CARD.docx",
            "HONOURS_AND_AWARDS/DEFENCE_SECRETARYS_COMMENDATION_CARD.docx",
            "HONOURS_AND_AWARDS/DG_NCC_COMMENDATION_CARD.docx"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_incentive_sub, content_frame, false);
        init();
        return view;
    }

    private void init() {

    parent=(LinearLayout)view.findViewById(R.id.parent);

    for(int i=0;i<subTitle.length;i++)
        {
            include = (LinearLayout) View.inflate(getActivity(),
                    R.layout.onlytext_layout, null);

            if(i==0||i==1){
                TextView option1=new TextView(getActivity());
                parent.addView(option1);
                createTV(option1,title[i]);
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

}