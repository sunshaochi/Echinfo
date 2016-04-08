package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by bitch-1 on 2016/4/7.
 * 诉讼详情界面
 */
public class LitigtinfoAct extends BaseActivity {

    @ViewInject(R.id.bzxname)
    private TextView bzxname;//被执行人姓名/名称
    @ViewInject(R.id.idcard)
    private TextView idcard;//身份证号码/组织机构代码
    @ViewInject(R.id.zxfy)
    private TextView zxfy;//执行法院
    @ViewInject(R.id.latime)
    private TextView latime;//立案时间
    @ViewInject(R.id.ahid)
    private TextView ahid;//案号
    @ViewInject(R.id.zxbd)
    private TextView zxbd;//执行标的

    @Override
    public void setLayout() {
        setContentView(R.layout.act_litigtinfo);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("诉讼详情");

    }
}
