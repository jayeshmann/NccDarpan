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

/**
 * A simple {@link Fragment} subclass.
 */
public class Enrolment_Fragment extends Fragment {


    View view;

    private String[] subTitle = new String[]{"Enrolment SD SW-1", "NCC_Nomination Form JD JW SD SW"};

    private LinearLayout parent;
    private LinearLayout include;

    private int count = 0;


    private String[] path = new String[]{
            "ENROLMENT/Enrolment_SD_SW-1",
            "ENROLMENT/NCC_Nomination_Form_JD_JW_SD_SW",
    };

    private String url = GLOBAL.baseURL + "content//DOWNLOADS/";

    public Enrolment_Fragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_enrolment, content_frame, false);
        init();
        return view;
    }

    private void init() {

        parent = view.findViewById(R.id.parent);

        for (int i = 0; i < subTitle.length; i++) {
            include = (LinearLayout) View.inflate(getActivity(),
                    R.layout.onlytext_layout, null);


            TextView tempTv = include.findViewById(R.id.title);
            tempTv.setText(subTitle[i]);
            parent.addView(include);

            final int finalI = i;
            tempTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GLOBAL.url = url + path[finalI];
                    ((NCCActivity) getActivity()).changeFragment(new CampSOPFragment(), null, subTitle[finalI], true);
                }
            });
        }

    }

}

