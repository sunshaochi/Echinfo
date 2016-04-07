package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.OccuAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.event.OccuEvent;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.tandong.sa.eventbus.EventBus;

/**
 * 职业选择
 * Created by wangbin on 16/4/6.
 */
public class OccupationAct extends BaseActivity {
    @ViewInject(R.id.lv)
    private ListView lv;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_occupation);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("职业");
        lv.setAdapter(new OccuAdapter(this));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(new OccuEvent("客户服务助理"));
                finish();
            }
        });
    }


}
