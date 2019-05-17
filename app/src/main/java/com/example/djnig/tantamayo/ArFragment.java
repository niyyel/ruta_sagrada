package com.example.djnig.tantamayo;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class ArFragment extends Fragment {

    WebView web;
    ProgressDialog mProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vx = inflater.inflate(R.layout.fragment_ar, container, false);

      //  mProgress = ProgressDialog.show(getActivity(), "Cargando pagina","Espere un momento...");
        web= (WebView)vx.findViewById(R.id.view_panoramic);

        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                /*if (mProgress.isShowing()) {
                    mProgress.dismiss();
                }*/
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            web.getSettings().setSafeBrowsingEnabled(false);
        }
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setJavaScriptEnabled( true );
        web.loadUrl("https://teliportme.com/embed/1575507?ar=-10&sfc=t&lp=lt&ls=d&lz=50&lo=1");
        return vx;
    }


}
