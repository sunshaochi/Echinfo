package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * WebView
 * Created by wangbin on 16/4/5.
 */
public class WebAct extends BaseActivity {
    @ViewInject(R.id.wv)
    private WebView wv;
    public static final String WEB_TYPE = "web_type";
    private int TYPE;//0服务协议 1企业图谱 2行业分析

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
                setRight("纠错", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivity(ErrorAct.class);
                    }
                });
                break;
        }
    }
}
