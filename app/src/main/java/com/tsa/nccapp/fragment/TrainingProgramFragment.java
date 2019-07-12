package com.tsa.nccapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.tsa.nccapp.R;
import com.tsa.nccapp.custom.MyWebViewClient;
import com.tsa.nccapp.utils.GLOBAL;

import lib.kingja.switchbutton.SwitchMultiButton;

public class TrainingProgramFragment extends Fragment {
    View view;
    private WebView webView;
    private String url=GLOBAL.baseURL+"content/TRAINING/INSTITUTIONAL/INSTITUTIONAL_TRAINING_PROGRAMME/";

    private String path[]=new String[]{"TRAINING_PROGRAMME_SENIOR_WING1.xlsx","TRG_PROGRAMME_JUNIOR_WING1.xls"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content_frame,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_directorates, content_frame, false);
        init();

        return view;

    }

    private void init() {
        webView=view.findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        //webView.setWebViewClient(new );
        showPage(0);
        SwitchMultiButton mSwitchMultiButton = view.findViewById(R.id.p_tab);
        mSwitchMultiButton.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                showPage(position);
            }
        });

    }

    private void showPage(int position)
    {
        GLOBAL.url=url+path[position];
        String myPdfUrl= GLOBAL.url;
        String url = "http://docs.google.com/gview?embedded=true&url=" + myPdfUrl;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

}
