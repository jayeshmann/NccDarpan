package com.tsa.nccapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;


public class IncentiveBenefitFragment extends Fragment {
View view;
    private int itemArr[]=new int[]{R.id.item1,R.id.item2,R.id.item3,R.id.item4};
    private TextView[] textViewArr=new TextView[4];

    private String[] title=new String[]{"SCHOLARSHIPS","INCENTIVES",
            "EMPLOYMENT","HONOURS AND AWARDS"};

    private View[] viewArr=new View[4];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_incentive, content_frame, false);
        init();
        return view;
    }

    private void init() {

        for(int i=0;i<4;i++)
        {
            viewArr[i]=view.findViewById(itemArr[i]);
            textViewArr[i]=viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);
        }

        textViewArr[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        ((NCCActivity)getActivity()).changeFragment(new ScholarshipsFragment(), null, "NCC Scholarships", true);
            }
        });

        textViewArr[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new IncentivesFragment(), null, "Incentives", true);
            }
        });

        textViewArr[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new EmploymentFragment(), null, "Employment", true);
            }
        });

        textViewArr[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity)getActivity()).changeFragment(new HonoursFragment(), null, "HONOURS AND AWARDS", true);
            }
        });


        /////////////////////////////////////////////////////////
    }


}