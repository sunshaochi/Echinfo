package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 设置
 * Created by wangbin on 16/4/5.
 */
public class SettingAct extends BaseActivity {

    @Override
    public void setLayout() {
        setContentView(R.layout.act_setting);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("设置");
    }

    @OnClick({R.id.rlRecomm})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rlRecomm://推荐好友

                break;
        }
    }
}
