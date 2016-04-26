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
 * 查看法院判决书
 * Created by gxy on 2016/4/25.
 */
public class CourtAct extends BaseActivity {
    @ViewInject(R.id.title)
    private TextView title;
    @ViewInject(R.id.time)
    private TextView time;
    @ViewInject(R.id.content)
    private TextView content;
    @ViewInject(R.id.iv_cd)
    private ImageView iv_cd;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_court);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("法院判决书");
        iv_cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareDialog dialog = new ShareDialog(CourtAct.this).builder();
                dialog.setContent("分享", "法院判决书", "");
                dialog.show();
            }
        });
    }
}
