package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

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
        gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            private final String TITLES[] = {"工商信息", "企业图谱", "行业分析", "失业信息", "诉讼信息",
//            "对外投资","股东信息","企业咨询","年报信息","分子机构","主要成员","变更记录"
//        };
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:

                }

            }
        });

    }
}
