package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 意见反馈
 * Created by wangbin on 16/4/5.
 */
public class FeedBackAct extends BaseActivity {
    @ViewInject(R.id.etFb)
    private EditText etFb;
    @ViewInject(R.id.etFbPhone)
    private EditText etFbPhone;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_feed_back);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("意见反馈");
        setRight("提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
