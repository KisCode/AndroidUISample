package demo.kiscode.webview.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;

import demo.kiscode.webview.R;

public class WebviewFragment extends Fragment {

    private static final String KEY_URL = "KEY_URL";
    private WebView webview;
    private ContentLoadingProgressBar pbar;

    public static WebviewFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_URL, url);
        WebviewFragment fragment = new WebviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWebView(view);
        initView(view);
        loadData();
    }

    private void loadData() {
        String url = getArguments().getString(KEY_URL);
        webview.loadUrl(url);
    }

    private void initWebView(View view) {
        webview = view.findViewById(R.id.webview_fragment);
        initWebviewSettings();
        initWebviewClient();
        initWebChromeClient();
    }

    private void initWebviewClient() {
        WebViewClient webViewClient = new WebViewClient() {
            private static final String TAG = "WebClientD";

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //是否拦截 包括页面资源、异步请求等等
                Log.i(TAG, "shouldOverrideUrlLoading " + url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (pbar.getVisibility() == View.GONE) {
                    pbar.setVisibility(View.VISIBLE);
                }
                //网页开始加载
                Log.i(TAG, "onPageStarted " + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //网页加载完毕
                Log.i(TAG, "onPageFinished " + url);
            }
        };
        webview.setWebViewClient(webViewClient);
    }


    private void initWebChromeClient() {
        WebChromeClient webChromeClient = new WebChromeClient() {
            private static final String TAG = "WebClientChrome";

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pbar.setProgress(newProgress);
                //网页加载进度更新
                //优化用户体验 建议 newProgress > 80时即可隐藏进度条，避免不消失导致用户焦虑
                Log.i(TAG, "onProgressChanged " + newProgress);

                if (newProgress > 80) {
                    pbar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //接收到网页标题
                Log.i(TAG, "onReceivedTitle " + title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                //接收到网页icon
                Log.i(TAG, "onReceivedIcon ");
            }
        };
        webview.setWebChromeClient(webChromeClient);
    }

    private void initWebviewSettings() {
        WebSettings webSettings = webview.getSettings();
        // 启用javaScript
        webSettings.setJavaScriptEnabled(true);
        //5.0以上开启混合模式加载
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //允许SessionStorage/LocalStorage存储
        webSettings.setDomStorageEnabled(true);
        //禁用放缩
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(false);
        //禁用文字缩放
//        webSettings.setTextZoom(100);
        //允许缓存，设置缓存位置
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(getContext().getDir("appcache", 0).getPath());
        //允许WebView使用File协议
        webSettings.setAllowFileAccess(true);
        //设置User-Agent
        webSettings.setUserAgentString(webSettings.getUserAgentString());
        //自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
    }

    private void initView(View view) {
        pbar = view.findViewById(R.id.progress);
    }

}