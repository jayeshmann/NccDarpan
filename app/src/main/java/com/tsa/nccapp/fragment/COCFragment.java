package com.tsa.nccapp.fragment;

import android.annotation.SuppressLint;
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


public class COCFragment extends Fragment {
View view;

    private int itemArr[]=new int[]{R.id.item1,R.id.item2,R.id.item3,R.id.item4,R.id.item5,R.id.item6,R.id.item7,
            R.id.item8,R.id.item9,R.id.item10,R.id.item11,R.id.item12,R.id.item13};
    private TextView[] textViewArr=new TextView[13];

    private LinearLayout[] rootArr=new LinearLayout[13];

    private String[] title=new String[]{"Republic Day Camp","THAL SAINIK CAMP","Youth Exchange Programme","NATIONAL INTEGRATION CAMP","GV MAVALANKAR SPORTS SHOOTING",
            "SERVICE SHOOTING","INTRODUCTION OF COMPETITIONS","AINSC","BOAT REGATTA","BOAT RIGGING"
    ,"MENU","SHIP MODELLING","INTRODUCTION AND RULES"};

    private ScrollView scrollView;

    private String url=GLOBAL.baseURL+"content/";

    String[] path=new String[]{
            "CENTRALLY_ORGANISED_CAMPS/NAVAL_WING/INTRODUCTION_OF_COMPETITIONS.docx",
            "CENTRALLY_ORGANISED_CAMPS/NAVAL_WING/AINSC.docx",
            "CENTRALLY_ORGANISED_CAMPS/NAVAL_WING/BOAT_REGATTA.docx" ,
            "CENTRALLY_ORGANISED_CAMPS/NAVAL_WING/BOAT_RIGGING.docx",
            "CENTRALLY_ORGANISED_CAMPS/NAVAL_WING/MENU.docx",
            "CENTRALLY_ORGANISED_CAMPS/NAVAL_WING/SHIP_MODELLING.docx",
            "CENTRALLY_ORGANISED_CAMPS/AIR_WING/INTRODUCTION_AND_RULES.docx"};

    private View[] viewArr=new View[13];

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_coc, content_frame, false);

        init();
        scrollView.scrollTo(GLOBAL.xD,GLOBAL.yD);
        return view;

    }

    private void init() {
        scrollView=view.findViewById(R.id.scrollView);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY(); // For ScrollView
                int scrollX = scrollView.getScrollX(); // For HorizontalScrollView
                // DO SOMETHING WITH THE SCROLL COORDINATES
                GLOBAL.xD=scrollX;
                GLOBAL.yD=scrollY;
            }
        });

        for(int i=0;i<13;i++)
        {
            viewArr[i]=view.findViewById(itemArr[i]);
            textViewArr[i]=viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);
            rootArr[i]=viewArr[i].findViewById(R.id.root1);

            if(i>5) {
                final int finalI = i-6;
                textViewArr[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GLOBAL.url=url+path[finalI];
                        GLOBAL.lastVisitedIndex.push(finalI);
                        ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, title[finalI+6], true);
                    }
                });
            }

            textViewArr[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GLOBAL.lastVisitedIndex.push(0);
                    ((NCCActivity) getActivity()).changeFragment(new RDCFragment(), null,title[0], true);
                }
            });
        }

        textViewArr[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.lastVisitedIndex.push(1);;
                ((NCCActivity) getActivity()).changeFragment(new TSCFragment(), null,title[1], true);
            }
        });

        textViewArr[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.lastVisitedIndex.push(2);;
                GLOBAL.url=url+"CENTRALLY_ORGANISED_CAMPS/ARMY_WING/YEP/YEP.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, title[2], true);
            }
        });

        textViewArr[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.lastVisitedIndex.push(3);;
                ((NCCActivity) getActivity()).changeFragment(new NICFragment(), null,title[3], true);
            }
        });

        textViewArr[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.lastVisitedIndex.push(4);;
                GLOBAL.url=url+"CENTRALLY_ORGANISED_CAMPS/ARMY_WING/GV_MAVALANKAR_SPORTS_SHOOTING/GV_MAVALANKR_SPORTS_SHOOTING.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, title[4], true);
            }
        });

        textViewArr[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.lastVisitedIndex.push(5);;
                GLOBAL.url=url+"CENTRALLY_ORGANISED_CAMPS/ARMY_WING/SERVICE_SHOOTING/SERVICE_SHOOTING_FOR_ALL_WINGS.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, title[5], true);
            }
        });

        if(GLOBAL.lastVisitedIndex.empty())
        {
            GLOBAL.lastVisitedIndex.push(0);
        }
        viewArr[GLOBAL.lastVisitedIndex.peek()].setPadding(5,5,5,5);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        params.setMargins(0,0,0,0);
        rootArr[GLOBAL.lastVisitedIndex.peek()].setLayoutParams(params);
        rootArr[GLOBAL.lastVisitedIndex.peek()].setBackgroundResource(R.drawable.shadow_194355);


    }
    /////////////////////////////////////////////////////////
    }
