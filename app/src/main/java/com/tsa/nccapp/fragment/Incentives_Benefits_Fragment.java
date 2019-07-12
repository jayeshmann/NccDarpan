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

/**
 * A simple {@link Fragment} subclass.
 */
public class Incentives_Benefits_Fragment extends Fragment {

    View view;

    private String url = GLOBAL.baseURL + "content/DOWNLOADS/";


    private int itemArr[] = new int[]{R.id.item1};

    private TextView[] textViewArr = new TextView[1];

    private String[] title = new String[]{"SCHOLARSHIPS"};

    private View[] viewArr = new View[1];


    public Incentives_Benefits_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_incentives__benefits, content_frame, false);
        init();

        return view;
    }

    private void init() {

        ///////////////////////////////////////////////////////////
        for (int i = 0; i < 1; i++) {
            viewArr[i] = view.findViewById(itemArr[i]);
            textViewArr[i] = viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);

        }


        textViewArr[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NCCActivity) getActivity()).changeFragment(new Scholarships_Fragment(), null, "SCHOLARSHIPS", true);
            }
        });


    }

}
