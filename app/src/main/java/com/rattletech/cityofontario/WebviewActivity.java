package com.rattletech.cityofontario;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class WebviewActivity extends AppCompatActivity {


    private LinearLayout mRelativeLayout;

    private WebView mWebView;
    private ProgressBar mProgressBar;
    public static final String KEY_TO_CHECK_WIFI = "refreshUI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // Get the widgets reference from XML layout
        mRelativeLayout = (LinearLayout) findViewById(R.id.rl);
        mWebView = (WebView) findViewById(R.id.webview_gateway_connect_successs);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);
        renderWebPage("http://www.google.com");

    }
    // Custom method to render a web page
    protected void renderWebPage(String urlToRender){
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                // Do something on page loading started
                // Visible the progressbar
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url){
                // Do something when page loading finished
               // Toast.makeText(GatewayConnectSuccess.this,"Page Loaded.", Toast.LENGTH_SHORT).show();
            }

        });

        /*
            WebView
                A View that displays web pages. This class is the basis upon which you can roll your
                own web browser or simply display some online content within your Activity. It uses
                the WebKit rendering engine to display web pages and includes methods to navigate
                forward and backward through a history, zoom in and out, perform text searches and more.

            WebChromeClient
                 WebChromeClient is called when something that might impact a browser UI happens,
                 for instance, progress updates and JavaScript alerts are sent here.
        */
        mWebView.setWebChromeClient(new WebChromeClient(){
            /*
                public void onProgressChanged (WebView view, int newProgress)
                    Tell the host application the current progress of loading a page.

                Parameters
                    view : The WebView that initiated the callback.
                    newProgress : Current page loading progress, represented by an integer
                        between 0 and 100.
            */
            public void onProgressChanged(WebView view, int newProgress){
                // Update the progress bar with page loading progress
                mProgressBar.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        // Enable the javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // Render the web page
        mWebView.loadUrl(urlToRender);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();


    }
}
