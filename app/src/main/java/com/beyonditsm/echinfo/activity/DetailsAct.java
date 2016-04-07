package com.beyonditsm.echinfo.activity;

import android.os.Bundle;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;

/**
 * Created by bitch-1 on 2016/4/6.
 */
public class DetailsAct extends BaseActivity {
    @Override
    public void setLayout() {
        setContentView(R.layout.act_details);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("详情");
    }
}
