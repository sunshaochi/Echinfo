package com.beyonditsm.echinfo.activity;

import android.os.Bundle;

import com.beyonditsm.echinfo.AppManager;
import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;

/**
 * 注册成功界面
 * Created by bitch-1 on 2016/5/27.
 */
public class RegsucssAct extends BaseActivity {
    @Override    public void setLayout() {
        AppManager.getAppManager().finishActivity(RegAct.class);
        AppManager.getAppManager().finishActivity(LoginAct.class);
        setContentView(R.layout.act_regsucss);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("注册完成");

    }
}
