package com.beyonditsm.echinfo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.lidroid.xutils.view.annotation.event.OnClick;


/**
 * 主页面
 */
public class MainAct extends BaseActivity {


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick({R.id.rl_gz,R.id.rl_qy,R.id.rl_sx})
    public void Onclick(View v) {
        switch(v.getId()){
            case R.id.rl_gz:
                openActivity(MyFollowAct.class);
               break;
            case R.id.rl_qy:

                break;

           }



    }


}
