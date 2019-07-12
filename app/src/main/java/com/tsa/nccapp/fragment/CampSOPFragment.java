package com.tsa.nccapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.tsa.nccapp.R;
import com.tsa.nccapp.custom.MyWebViewClient;
import com.tsa.nccapp.utils.GLOBAL;


public class CampSOPFragment extends Fragment {
    private View view;
    private WebView webView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            content_frame, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.camp_sop_song, content_frame, false);
        init();
        return view;
    }

    private void init() {

        webView=(WebView)view.findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());

        String myPdfUrl=GLOBAL.url;
       // String myPdfUrl =GLOBAL.url "https://www.tsassessors.com/sanya-shakti/content/TRAINING/CAMP/CAMP_SOP.docx";
        String url = "http://docs.google.com/gview?embedded=true&url=" + myPdfUrl;
        //Log.i("TAG", "Opening PDF: " + url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        /////////////////////////////////////////////////////////
    }
}

