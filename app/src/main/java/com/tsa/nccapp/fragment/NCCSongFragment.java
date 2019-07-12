package com.tsa.nccapp.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.media.MediaPlayer;
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

public class NCCSongFragment extends Fragment{

    private View view;
    private TextView itemTV1;
    private ScrollView scrollView;
    private FloatingActionButton floatingActionButton;
    private MediaPlayer mPlayer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song, content_frame, false);
      /*  mPlayer = MediaPlayer.create(getActivity(), R.raw.ncc);
        mPlayer.start();*/
        init();
        return view;

    }

    private void init() {
        itemTV1=view.findViewById(R.id.song_tv);
        itemTV1.setText(Html.fromHtml(getString(R.string.song)));

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

    }

}
