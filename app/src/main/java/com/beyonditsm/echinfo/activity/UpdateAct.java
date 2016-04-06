package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;

/**
 * 修改资料
 * Created by wangbin on 16/4/6.
 */
public class UpdateAct extends BaseActivity {

    @Override
    public void setLayout() {
        setContentView(R.layout.act_update);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("修改资料");
        setRight("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
