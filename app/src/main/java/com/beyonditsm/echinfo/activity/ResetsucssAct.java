package com.beyonditsm.echinfo.activity;

import android.os.Bundle;

import com.beyonditsm.echinfo.AppManager;
import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;

/**
 * 重置密码成功界面
 * Created by bitch-1 on 2016/5/27.
 */
public class ResetsucssAct extends BaseActivity {
    @Override
    public void setLayout() {
        AppManager.getAppManager().finishActivity(ForgetAct.class);
        setContentView(R.layout.act_resetsucss);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("重置密码完成");

    }
}
