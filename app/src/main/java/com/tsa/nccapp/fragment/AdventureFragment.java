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

public class AdventureFragment extends Fragment {
    private View view;
    private TextView item1;
    private ScrollView scrollView;
    private FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about, content_frame, false);
        init();
        return view;

    }


    private void init() {

        item1=view.findViewById(R.id.att_tv);
        item1.setText(Html.fromHtml(getString(R.string.advanture)));
        item1.setTextColor(getResources().getColor(GLOBAL.textColor));
        item1.setBackgroundColor(getResources().getColor(GLOBAL.BGColor));
        item1.setTextSize(GLOBAL.textSize);

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