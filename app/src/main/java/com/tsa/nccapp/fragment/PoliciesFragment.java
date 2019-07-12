package com.tsa.nccapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;

public class PoliciesFragment extends Fragment{

    private View view;

    private View[] viewArr=new View[11];

    private String[] title=new String[]{"ANO POLICY","Extension Of Service","Honorary Rank",
            "Messing ALLOWANCE","Policy Letter Care Taker",
            "Selection Appointment Commission","Incidental Allowances","Messing Allowances"
            ,"Rate Amenity Grant","Refreshment Allowances","Revised SNCCO 2017"};

    private int itemArr[]=new int[]{R.id.item1,R.id.item2,R.id.item3,R.id.item4,R.id.item5,R.id.item6,
            R.id.item7,R.id.item8,R.id.item9,R.id.item10,R.id.item11};

    private TextView[] textViewArr=new TextView[11];

    private String url=GLOBAL.baseURL+"content/POLICIES/";

    private ScrollView scrollView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_poloicies, content_frame, false);

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


        for(int i=0;i<11;i++)
        {
            viewArr[i]=view.findViewById(itemArr[i]);
            textViewArr[i]=viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);
        }

        textViewArr[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"ANO/ano_POLICY.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "ANO POLICY", true);
            }
        });

        textViewArr[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"ANO/Extension_of_Service.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Extension of Service", true);
            }
        });

        textViewArr[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"ANO/hounorary_rank.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Honorary Rank", true);


            }
        });

        textViewArr[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"ANO/messing_ano_pct_for_web.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Messing ANO", true);


            }
        });

        textViewArr[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"ANO/Policy_letter_care_taker.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Policy Letter Caretaker", true);

            }
        });


        textViewArr[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"ANO/selection_appointment_commission.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Selection Appointment Commission", true);

            }
        });


        textViewArr[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"CADET/incidental_cadets_atc_coc.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Incidental Cadets atc COC", true);

            }
        });


        textViewArr[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"CADET/messing_cadets_ano_expedition.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Messing Cadets ANO  Expedition", true);


            }
        });


        textViewArr[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"CADET/rate_amenity_grant.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Rate Amenity Grant", true);


            }
        });


        textViewArr[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"CADET/refreshment_cadets_NER_JK.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Refreshment Cadets", true);

            }
        });


        textViewArr[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"CADET/Revised_SNCCO_2017.pdf";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "Revised SNCCO 2017", true);

            }
        });

    }

}
