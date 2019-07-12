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
public class Ano_new_fragment extends Fragment {


    View view;

    private String[] subTitle = new String[]{"ANO Hand Book 1", "APPLICATION FOR ANO", "APPLICATION FOR CARE TAKER", "ACR FORM ANOs"};

    private LinearLayout parent;
    private LinearLayout include;

    private int count = 0;


    private String[] path = new String[]{
            "ANO/ANO_Hand_Book_1.pdf",
            "ANO/APPLICATION_FOR_ANO.docx",
            "ANO/APPLICATION_FOR_CARE_TAKER.docx",
            "ANO/Microsoft_Word%20_ACR_FORM__ANOs.pdf",

     };


    private String url = GLOBAL.baseURL + "content//DOWNLOADS/";


    public Ano_new_fragment() {
        // Required empty public constructor
    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ano_new_fragment, content_frame, false);
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
