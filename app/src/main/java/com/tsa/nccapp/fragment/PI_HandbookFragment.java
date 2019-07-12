package com.tsa.nccapp.fragment;

import android.content.Context;
import android.net.Uri;
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


public class PI_HandbookFragment extends Fragment {
    View view;

    private String[] subTitle = new String[]{"PI HANDBOOK 1"};

    private LinearLayout parent;
    private LinearLayout include;

    private int count = 0;


    private String[] path = new String[]{
            "PI%20HANDBOOK/pioc_handbook_1.pdf",


    };


    private String url = GLOBAL.baseURL + "content//DOWNLOADS/";


    public PI_HandbookFragment() {
        // Required empty public constructor
    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pi__handbook, content_frame, false);
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
