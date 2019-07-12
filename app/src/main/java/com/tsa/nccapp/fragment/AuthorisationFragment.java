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


public class AuthorisationFragment extends Fragment {
View view;

    private String url=GLOBAL.baseURL+"content/CADET_AUTHORISATION/";

    private int itemArr[]=new int[]{R.id.item1,R.id.item2,R.id.item3};
    private TextView[] textViewArr=new TextView[3];

    private String[] title=new String[]{"CAMP","INSTITUTIONAL","UNIFORM"};

    private View[] viewArr=new View[3];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_authorisation, content_frame, false);
        init();

        return view;

    }

    private void init() {

        for(int i=0;i<3;i++)
        {
            viewArr[i]=view.findViewById(itemArr[i]);
            textViewArr[i]=viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);
        }

        textViewArr[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"CAMP.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "CAMP", true);
            }
        });

        textViewArr[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"INSTITUTIONAL.docx";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "INSTITUTIONAL", true);
            }
        });

        textViewArr[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"UNIFORM.doc";
                ((NCCActivity)getActivity()).changeFragment(new CampSOPFragment(), null, "UNIFORM", true);


            }
        });

        /////////////////////////////////////////////////////////
    }


}