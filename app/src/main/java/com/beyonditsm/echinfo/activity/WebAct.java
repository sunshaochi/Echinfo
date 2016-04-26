package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.http.IEchinfoUrl;
import com.beyonditsm.echinfo.util.MyLogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * WebView
 * Created by wangbin on 16/4/5.
 */
public class WebAct extends BaseActivity {
    @ViewInject(R.id.wv)
    private WebView wv;
    public static final String WEB_TYPE = "web_type";
    private int TYPE;//0服务协议 1企业图谱 2行业分析 3常见问题
    String url=null;
    public static final String ID="id";
    String id=null;

    @Override
    public void setLayout() {
        setContentView(R.layout.act_web);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        TYPE = getIntent().getIntExtra(WEB_TYPE, 0);
        switch (TYPE) {
            case 0:
                setTopTitle("服务协议");
                break;
            case 1:
                setTopTitle("企业图谱");
                setRight("纠错", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivity(ErrorAct.class);
                    }
                });
                break;
            case 2:
                setTopTitle("行业分析");
                id=getIntent().getStringExtra(ID);
                url=IEchinfoUrl.ANALY_URL+"?companyId="+id;
                setRight("纠错", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivity(ErrorAct.class);
                    }
                });
                break;
            case 3:
                setTopTitle("常见问题");
                url=IEchinfoUrl.PROBLEM_URL;
                break;
        }
        MyLogUtils.degug("h5:"+url);
        WebSettings webSettings = wv.getSettings();
        webSettings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        wv.requestFocusFromTouch();
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (wv.canGoBack()) {
            wv.goBack();
        }
    }
}
