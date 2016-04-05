package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 我的
 * Created by wangbin on 16/4/5.
 */
public class MineAct extends BaseActivity {

    @Override
    public void setLayout() {
        setContentView(R.layout.act_mine);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("我的");
    }

    @OnClick({R.id.ivSet, R.id.rlHead, R.id.cvFollow, R.id.cvMsg, R.id.cvFeedBack, R.id.cvPro})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSet://设置

                break;
            case R.id.rlHead://用户资料

                break;
            case R.id.cvFollow://我的关注
                openActivity(MyFollowAct.class);
                break;
            case R.id.cvMsg://系统消息

                break;
            case R.id.cvFeedBack://意见反馈
                openActivity(FeedBackAct.class);
                break;
            case R.id.cvPro://常见问题

                break;
        }
    }
}
