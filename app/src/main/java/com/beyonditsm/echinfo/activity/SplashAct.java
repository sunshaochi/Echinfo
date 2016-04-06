package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.os.Handler;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;

/**
 * 闪屏页面
 * Created by wangbin on 16/4/5.
 */
public class SplashAct extends BaseActivity {
    @Override
    public void setLayout() {
        setContentView(R.layout.act_splash);
    }

    @Override
    public void init(Bundle savedInstanceState) {
       new  Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               openActivity(MainAct.class);
               finish();
           }
       },0);
    }
}
