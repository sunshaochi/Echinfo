package com.beyonditsm.echinfo.activity;

import android.os.Bundle;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.JiucuoAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.view.MyGridView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by bitch-1 on 2016/4/5.
 * 纠错界面
 */
public class ErrorAct extends BaseActivity {

    @ViewInject(R.id.gvHome)
    private MyGridView gvHome;//纠正里面标签

    @Override
    public void setLayout() {
        setContentView(R.layout.act_error);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("纠正");
        gvHome.setAdapter(new JiucuoAdapter(ErrorAct.this));

    }
}
