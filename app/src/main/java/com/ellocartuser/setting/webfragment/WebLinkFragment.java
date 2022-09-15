package com.ellocartuser.setting.webfragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ellocartuser.R;


public class WebLinkFragment extends Fragment {

    private static final String ARG_WEB_URL = "web_url";
    private static final String ARG_nmae = "name";
ImageView imageback;
TextView current;
    private String mWebUrl,text;

    public WebLinkFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static WebLinkFragment newInstance(String webUrl, String text) {
        WebLinkFragment fragment = new WebLinkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_WEB_URL, webUrl);
        args.putString(ARG_nmae, text);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWebUrl = getArguments().getString(ARG_WEB_URL);
            text = getArguments().getString(ARG_nmae);

            /*
            switch (InternetHelper.getInstance(getActivity()).isOnline())
            {

                case WIFI_CONNETED:

                    if (!InternetHelper.isInternetAvailableInWifi())
                    {
                        AlertDialogHelper.showAlert(getContext(),"No Internet connection");

                    }

                    break;
                case MOBILE_CONNECTED:
                    break;
                case INTERNET_NOT_AVAIBLE:
                    AlertDialogHelper.showAlert(getContext(),"No Internet connection");
                    break;
            }
            */

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_link, container, false);

        WebView webView = (WebView) view.findViewById(R.id.webLinksWebView);
        imageback =  view.findViewById(R.id.imageback);
        current =  view.findViewById(R.id.current);
        if(text!=null) {
            current.setText(text);
        }
        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCacheMaxSize(100 * 1000 * 1000);

//
//imageback.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        Fragment acc=new Account();
//        replaceFragment(acc,"no");
//    }
//});
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
           //     Progress.start(getContext());
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
              //  Progress.stop();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
             //   Progress.stop();
            }

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

        });

        webView.loadUrl(mWebUrl);


        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void replaceFragment(Fragment fragment, String tag) {

        try {

            FragmentManager fragmentManager = getFragmentManager();
            Fragment lastFragment = fragmentManager.findFragmentByTag(tag);

            // if Visiable
            if ((lastFragment!=null) && (lastFragment.equals(fragment))) {
                return;
            }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();


        }catch (Exception e)
        {
            Log.e("MainActivity",e.getMessage());
        }

    }
}
