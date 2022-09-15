package com.ellocartuser.utils;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ellocartuser.R;

public class WebViewActivity extends AppCompatActivity {

    ImageView imageback;
    TextView current;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

         webView = (WebView) findViewById(R.id.webLinksWebView);
        imageback = findViewById(R.id.imageback);
        current = findViewById(R.id.current);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             onBackPressed();
            }
        });

        if(getIntent().getStringExtra("name")!=null){
            current.setText(getIntent().getStringExtra("name"));
        }

        // Enable Javascript
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCacheMaxSize(100 * 1000 * 1000);

        if(getIntent().getStringExtra("url")!=null){
            webView.loadUrl(getIntent().getStringExtra("url"));
        }

   //     setContentView(webView );
//
    }
}