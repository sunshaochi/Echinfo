package com.beyonditsm.echinfo.activity;

import android.os.Bundle;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;

/**
 * 关于我们
 * Created by gxy on 2016/4/29.
 */
public class AboutAct extends BaseActivity {
    @Override
    public void setLayout() {
        setContentView(R.layout.act_about);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("关于我们");
    }
}
