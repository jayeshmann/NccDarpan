package com.tsa.nccapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;


public class TSCFragment extends Fragment {
View view;

    private String[] title=new String[]{"GIRLS","BOYS"};


    private String[] path=new String[]{
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/GIRLS/INTRODUCTION.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/GIRLS/OBSTACLE_COURSE.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/GIRLS/HEALTH_AND_HYGIENE.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/GIRLS/LINE_AREA.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/GIRLS/MAP_READING.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/GIRLS/TENT_PITCHING.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/GIRLS/INTER_DTE_SHOOTING.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/GIRLS/JUDGING_DISTANCE_AND_FEILD_SIGNALS.docx",

            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/BOYS/INTRODUCTION.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/BOYS/OBSTACLE_COMPETITION.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/BOYS/MAP_READING.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/BOYS/LINE_AREA.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/BOYS/TENT_PITCHING.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/BOYS/HEALTH_AND_HYGIENE.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/BOYS/JUDGING_DISTANCE_AND_FIELD_SIGNAL.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/BOYS/INTER_DTE_SHOOTING.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/TSC/BOYS/INTER_DTE_SHOOTING_SD.docx"
    };
    private int count=0;
    private LinearLayout parent;
    private LinearLayout include;

    private String url=GLOBAL.baseURL+"content/";

    private String[] subTitle=new String[]{
            "INTRODUCTION",
            "OBSTACLE COURSE",
            "HEALTH AND HYGIENE",
            "LINE AREA",
            "MAP READING",
            "TENT PITCHING",
            "INTER DTE_SHOOTING",
            "JUDGING DISTANCE AND FEILD SIGNALS",

            "INTRODUCTION",
            "OBSTACLE COMPETITION",
            "MAP READING",
            "LINE AREA",
            "TENT PITCHING",
            "HEALTH AND HYGIENE",
            "JUDGING DISTANCE AND FIELD SIGNAL",
            "INTER DTE SHOOTING JD",
            "INTER DTE SHOOTING SD"
    };

    private ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_incentive_sub, content_frame, false);

        init();

        return view;
    }

    private void init() {

        parent=view.findViewById(R.id.parent);

        scrollView=view.findViewById(R.id.scrollView);

        scrollView.scrollTo(GLOBAL.xD,GLOBAL.yD);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                int scrollX = scrollView.getScrollX();
                // DO SOMETHING WITH THE SCROLL COORDINATES
                GLOBAL.xD=scrollX;
                GLOBAL.yD=scrollY;
            }
        });

    for(int i=0;i<subTitle.length;i++)
        {
            include = (LinearLayout) View.inflate(getActivity(),
                    R.layout.onlytext_layout, null);

            if(i==0||i==8){
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
        tv.setPadding(20,15,0,5);
    }

    @Override
    public void onStop() {
        super.onStop();
        count=0;
        if (!GLOBAL.lastVisitedIndex.empty()) {
            GLOBAL.lastVisitedIndex.pop();
        }
    }

}