package com.tsa.nccapp.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tsa.nccapp.R;
import com.tsa.nccapp.utils.GLOBAL;

public class SocialFragment extends Fragment {
    private View view;
    private TextView item1;
    private ScrollView scrollView;
    private FloatingActionButton floatingActionButton;

    private int[] tIDs=new int[]{R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4,R.id.tv5,R.id.tv6,R.id.tv7,R.id.tv8,R.id.tv9,R.id.tv10,
            R.id.tv11,R.id.tv12,R.id.tv13,R.id.tv14,R.id.tv15,R.id.tv16,R.id.tv17,R.id.tv18,R.id.tv19,R.id.tv20
    ,R.id.tv21,R.id.tv22,R.id.tv23,R.id.tv24,R.id.tv25,R.id.tv26,R.id.tv27,R.id.tv28,R.id.tv29,R.id.tv30
    ,R.id.tv31,R.id.tv32,R.id.tv33,R.id.tv34,R.id.tv35,R.id.tv36,R.id.tv37,R.id.tv38,R.id.tv39,R.id.tv40,
      R.id.tv41,R.id.tv42,R.id.tv43,R.id.tv44,R.id.tv45,R.id.tv46,R.id.tv47,R.id.tv48,R.id.tv49,R.id.tv50,
            R.id.tv51};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.social_fragment, content_frame, false);
        init();
        return view;

    }


    private void init() {

        item1=view.findViewById(R.id.att_tv);
        item1.setText(Html.fromHtml(getString(R.string.social)));
        item1.setTextColor(getResources().getColor(GLOBAL.textColor));
        item1.setBackgroundColor(getResources().getColor(GLOBAL.BGColor));
        item1.setTextSize(GLOBAL.textSize);

        for(int i=0;i<tIDs.length;i++)
        {
            TextView tempTV=view.findViewById(tIDs[i]);
            tempTV.setTextColor(getResources().getColor(GLOBAL.textColor));
            tempTV.setBackgroundColor(getResources().getColor(GLOBAL.BGColor));
            tempTV.setTextSize(GLOBAL.textSize);

        }

        scrollView=view.findViewById(R.id.scrollView);
        floatingActionButton=view.findViewById(R.id.buttons);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.scrollTo(0,0);
            }
        });

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY(); // For ScrollView
                int scrollX = scrollView.getScrollX(); // For HorizontalScrollView
                // DO SOMETHING WITH THE SCROLL COORDINATES

                if(scrollY<120) {
                    floatingActionButton.animate()
                            .setDuration(1500)
                            .scaleX(0.0f)
                            .scaleY(0.0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    floatingActionButton.setVisibility(View.GONE);
                                }
                            });
                }
                else
                {
                    floatingActionButton.animate()
                            .setDuration(1500)
                            .scaleX(1.0f)
                            .scaleY(1.0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    floatingActionButton.setVisibility(View.VISIBLE);
                                }
                            });
                }

            }
        });

        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
    }


}