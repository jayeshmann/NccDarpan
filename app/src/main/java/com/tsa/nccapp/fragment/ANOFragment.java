package com.tsa.nccapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;

import static com.tsa.nccapp.R.id.*;


public class ANOFragment extends Fragment {
View view;

    private String url=GLOBAL.baseURL+"content/";

    private int itemArr[]=new int[]{item1, item2, item3, item4, item5, item6, item7,
            item8};
    private TextView[] textViewArr=new TextView[8];

    private String[] title=new String[]{"CARE TAKER","APPLICATION FOR CARE TAKER",
            "Associate NCC officer","APPLICATION FOR ANO","APPLICATION FOR RAISING NCC","PROCEDURE FOR OPENING NCC",
            "RAISING BOY DIVISION","RAISING GIRL DIVISION"};

    private View[] viewArr=new View[8];

    AdView mAdView1;
    AdView mAdView2;
    AdView mAdView3;

    private ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ano, content_frame, false);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(getActivity(), getResources().getString(R.string.add_mob_id));
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

        mAdView1 =  view.findViewById(R.id.adView1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);

        mAdView2 = view.findViewById(R.id.adView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);

        mAdView3 = view.findViewById(R.id.adView3);
        AdRequest adRequest3 = new AdRequest.Builder().build();
        mAdView3.loadAd(adRequest3);

        for(int i=0;i<8;i++)
        {
            viewArr[i]=view.findViewById(itemArr[i]);
            textViewArr[i]=viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);
        }

        textViewArr[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"ANO/caretaker/caretaker.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "CARETAKER", true);
            }
        });

        textViewArr[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"ANO/caretaker/APPLICATION_FOR_CARE_TAKER.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "APPLICATION FOR CARE TAKER", true);
            }
        });


        textViewArr[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"ANO/ano.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Associate NCC officer", true);


            }
        });

        textViewArr[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"ANO/APPLICATION_FOR_ANO.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "DUTIES OF CARETAKER", true);

            }
        });


        textViewArr[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"INSTITUTION/APPLICATION_FOR_RAISING_NCC.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "APPLICATION FOR RAISING NCC", true);
            }
        });

        textViewArr[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"INSTITUTION/PROCEDURE_FOR_OPENING_NCC.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "PROCEDURE FOR OPENING NCC", true);
            }
        });

        textViewArr[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"INSTITUTION/RAISING_BOY_DIVISION.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "RAISING BOY DIVISION", true);
            }
        });

        textViewArr[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"INSTITUTION/RAISING_GIRL_DIVISION.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "RAISING GIRL DIVISION", true);
            }
        });

        /////////////////////////////////////////////////////////
    }


}