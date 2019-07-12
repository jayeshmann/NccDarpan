package com.tsa.nccapp.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tsa.nccapp.NCCActivity;
import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class Training_Fragment extends Fragment {

    View view;


    private int itemArr[] = new int[]{R.id.item1, R.id.item2};

    private TextView[] textViewArr = new TextView[2];

    private String[] title = new String[]{"Training Philosophy 2017","Trg Directive 2017-18"};

    private View[] viewArr = new View[2];

    private String url = GLOBAL.baseURL+"content/DOWNLOADS/";
    private ScrollView scrollView;

    public Training_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_training, content_frame, false);
        init();
        scrollView.scrollTo(GLOBAL.xD,GLOBAL.yD);

        return view;
    }

    private void init() {

        scrollView=view.findViewById(R.id.scrollView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
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
        }

        ///////////////////////////////////////////////////////////
        for (int i = 0; i < 2; i++) {
            viewArr[i] = view.findViewById(itemArr[i]);
            textViewArr[i] = viewArr[i].findViewById(R.id.title);
            textViewArr[i].setText(title[i]);

        }




        textViewArr[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"TRAINING/Training_Philosophy_2017.pdf";
                ((NCCActivity) getActivity()).changeFragment(new CampSOPFragment(), null, "Philosophy", true);
            }
        });


        textViewArr[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.url=url+"TRAINING/Trg_Directive_2017_18.pdf";
                ((NCCActivity) getActivity()).changeFragment(new CampSOPFragment(), null, "Philosophy", true);
            }
        });


    }

}