package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
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
    private int TYPE;//0服务协议

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
        }
    }
}
