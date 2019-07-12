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


public class RDCFragment extends Fragment {
View view;

    private String[] title=new String[]{"LINE AREA","FLAG AREA","GUARD OF HONOUR","RD PARADE",
            "INTER DTE MARCH PAST","NIAP","CULTURAL COMPETITIONS","BC & YEP",
    "DISCIPLINE","R & V"};

    private ScrollView scrollView=null;


    private String[] path=new String[]{
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/LINE_AREA/INTRODUCTION.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/LINE_AREA/LINE_AREA.docx",

            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/FLAG_AREA/INTRODUCTION_OF_FLAG_AREA.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/FLAG_AREA/FLAG_AREA.docx",

            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/GUARD_OF_HONOUR/INTRODUCTION_OF_GUARD_OF_HONOUR.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/GUARD_OF_HONOUR/GUARD_OF_HONOUR.docx",

             "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/RD_PARADE/INTRODUCTION_OF_RD_PARADE.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/RD_PARADE/RD_PARADE.docx",

            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/INTER_DTE_MARCH_PAST/INTER_DTE_MARCH_PAST.docx",

            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/NIAP/NIAP.docx",

            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/CULTURAL_COMPETITIONS/INTRODUCTION.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/CULTURAL_COMPETITIONS/BALLET.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/CULTURAL_COMPETITIONS/GROUP_DANCE.docx",
            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/CULTURAL_COMPETITIONS/GROUP_SONG.docx",

            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/BC_YEP/BC_AND_YEP.docx",

            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/DISCIPLINE/DISCIPLINE.docx",

            "CENTRALLY_ORGANISED_CAMPS/ARMY_WING/RDC/R_V/R_V.docx"
    };
    private int count=0;
    private LinearLayout parent;
    private LinearLayout include;

    private String url="https://www.tsassessors.com/sanya-shakti/content/";

    private String[] subTitle=new String[]{
            "INTRODUCTION",
            "LINE AREA",
            "INTRODUCTION",
            "FLAG AREA",
            "INTRODUCTION",
            "GUARD OF HONOUR",
            "INTRODUCTION",
            "RD PARADE",
            "INTER DTE MARCH PAST DURING PM's RALLY",
            "NIAP",
            "INTRODUCTION",
            "BALLET",
            "GROUP DANCE",
            "GROUP SONG",
            "BC AND YEP",
            "DISCIPLINE",
            "R & V"
    };

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

            if(i==0||i==2||i==4||i==6||i==8||i==9||i==10||i==14||i==15||1==16||i==17){
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
    }
}