package com.beyonditsm.echinfo.activity;

import android.os.Bundle;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;

/**
 * Created by bitch-1 on 2016/4/7.
 * 诉讼详情界面
 */
public class LitigtinfoAct extends BaseActivity {
    @Override
    public void setLayout() {
        setContentView(R.layout.act_litigtinfo);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("诉讼详情");

    }
}
