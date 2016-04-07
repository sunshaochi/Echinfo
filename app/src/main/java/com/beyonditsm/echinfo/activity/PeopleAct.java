package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.PeopleAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by bitch-1 on 2016/4/7.
 * 主要成员界面
 */
public class PeopleAct extends BaseActivity{
    @ViewInject(R.id.pp_lv)
    private ListView pp_lv;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_people);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("主要成员");
        setRight("纠错", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ErrorAct.class);
            }
        });
        pp_lv.setAdapter(new PeopleAdapter(PeopleAct.this));

    }
}
