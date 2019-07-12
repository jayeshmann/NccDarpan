package com.tsa.nccapp;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import com.tsa.nccapp.custom.MyWebViewClient;
import com.tsa.nccapp.utils.GLOBAL;

public class SyllabusActivity extends AppCompatActivity {

    private String url=GLOBAL.baseURL+"/content/syllabus.pdf";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(SyllabusActivity.this, R.color.light_vilate));
        }

        init();
    }


    private void init() {

        webView=findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        String myUrl = "http://docs.google.com/gview?embedded=true&url=" + url;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(myUrl);

        /////////////////////////////////////////////////////////
    }
}
