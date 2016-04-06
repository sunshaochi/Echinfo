package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.CompanyAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.view.MyGridView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * Created by bitch-1 on 2016/4/6.
 * 企业详情界面
 */
public class CompanyxqAct extends BaseActivity {
    @ViewInject(R.id.gvqy)
    private MyGridView gvqy;//企业详情界面里面的gridview

    @Override
    public void setLayout() {
        setContentView(R.layout.companyxq);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("华东控股集团有限公司");
        gvqy.setAdapter(new CompanyAdapter(CompanyxqAct.this));
    }



    @OnClick({R.id.iv_cd})
    public void Onclick(View v) {
       switch (v.getId()){
           case R.id.iv_cd:


       }




    }

}
