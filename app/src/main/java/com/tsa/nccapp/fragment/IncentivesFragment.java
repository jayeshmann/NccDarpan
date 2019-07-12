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


public class IncentivesFragment extends Fragment {
    View view;

    private String[] title=new String[]{"CENTRAL GOVERNMENT","STATE GOVT"};

    private String[] subTitle=new String[]{"ARMY","NAVY","AIR FORCE",
            "ANDHRA PRADESH AND TELANGANA",
            "ARUNANCHAL PRADESH",
            "ASSAM",
            "BIHAR AND JHARKHAND",
            "GUJRAT",
            "HARYANA",
            "HIMACHAL PRADESH",
            "JAMMU AND KASHMIR",
            "KARNATAKA AND GOA",
            "KERALA AND LAKSHADWEEP",
            "MADHYA PRADESH",
            "MAHARASHTRA",
            "MANIPUR",
            "MEGHALAYA",
            "NAGALAND",
            "ORISSA",
            "PONDICHERRY",
            "PUNJAB",
            "TAMIL NADU AND ANDAMAN NICOBAR ISLANDS",
            "TRIPURA",
            "UTTAR PRADESH",
            "UTTARAKHAND",
            "WEST BENGAL AND SIKKIM"};

        private LinearLayout parent;
        private LinearLayout include;

    private int count=0;

    private String[] path=new String[]{
            "INCENTIVES/CENTRAL_GOVERNMENT/ARMY.docx",
            "INCENTIVES/CENTRAL_GOVERNMENT/NAVY.docx",
            "INCENTIVES/CENTRAL_GOVERNMENT/AIR_FORCE.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/ANDHRA_PRADESH_AND_TELANGANA.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/ARUNANCHAL_PRADESH.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/ASSAM.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/BIHAR_AND_JHARKHAND.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/GUJRAT.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/HARYANA.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/HIMACHAL_PRADESH.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/JAMMU_AND_KASHMIR.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/KARNATAKA_AND_GOA.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/KERALA_AND_LAKSHADWEEP.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/MADHYA_PRADESH.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/MAHARASHTRA.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/MANIPUR.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/MEGHALAYA.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/NAGALAND.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/ORISSA.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/PONDICHERRY.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/PUNJAB.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/TAMIL_NADU_AND_ANDAMAN_NICOBAR_ISLANDS.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/TRIPURA.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/UTTAR_PRADESH.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/UTTARAKHAND.docx",
            "INCENTIVES_BY_ALL_STATE_GOVERNMENTS/WEST_BENGAL_AND_SIKKIM.docx"};

    private String url=GLOBAL.baseURL+"content/INCENTIVES_AND_BENEFITS/";

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

            if(i==0||i==3){
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
        tv.setTextSize(22);
        tv.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        tv.setText(title);
        tv.setPadding(40,5,0,5);
    }

    @Override
    public void onStop() {
        super.onStop();
        count=0;
    }
}