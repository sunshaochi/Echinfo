package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.widget.ShareDialog;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by bitch-1 on 2016/4/6.
 */
public class DetailsAct extends BaseActivity {
    @ViewInject(R.id.iv_cd)
    private ImageView ivcd;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_details);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("详情");
        ivcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareDialog dialog=new ShareDialog(DetailsAct.this).builder();
                dialog.setContent("企业资讯","enen","");
                dialog.show();
            }
        });
    }

}
