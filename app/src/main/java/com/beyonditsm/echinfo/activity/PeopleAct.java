package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by bitch-1 on 2016/4/7.
 * 主要成员界面
 */
public class PeopleAct extends BaseActivity{
    @ViewInject(R.id.pp_lv)
    private ListView pp_lv;

    private int page=1;
    private int rows=10;
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
        findAnnualPortsMsg("12",page,rows);
//        pp_lv.setAdapter(new PeopleAdapter(PeopleAct.this));

    }

    private void findAnnualPortsMsg(String companyId,int page,int rows){

        RequestManager.getCommManager().findAnnualPortsMsg(companyId, page, rows, new CallBack() {
            @Override
            public void onSucess(String result) {

            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
