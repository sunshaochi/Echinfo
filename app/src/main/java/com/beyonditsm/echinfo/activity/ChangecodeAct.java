package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;

/**
 * Created by bitch-1 on 2016/4/7.
 * 变更记录
 */
public class ChangecodeAct extends BaseActivity {
    @Override
    public void setLayout() {
        setContentView(R.layout.act_changecode);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("变更记录");
        setRight("纠错", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ErrorAct.class);
            }
        });

        findAnnualPortsMsgTest("12",1,10);
    }

    /**
     * 变更查询
     * @param id
     * @param page
     * @param rows
     */
    private void findAnnualPortsMsgTest(String id,int page,int rows){
        RequestManager.getCommManager().findAnnualPortsMsgTest(id, page, rows, new CallBack() {
            @Override
            public void onSucess(String result) {

            }

            @Override
            public void onError(String error) {

            }
        });
    }

}
