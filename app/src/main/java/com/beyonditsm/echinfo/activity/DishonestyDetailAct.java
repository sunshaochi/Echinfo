package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 失信榜单详情
 * Created by gxy on 2016/4/8.
 */
public class DishonestyDetailAct extends BaseActivity {
    @ViewInject(R.id.company)
    private TextView company;//公司名
    @ViewInject(R.id.province)
    private TextView province;//省份
    @ViewInject(R.id.yjid)
    private TextView zxid;//执行依据文号
    @ViewInject(R.id.zxfa)
    private TextView zxfa;//执行法案
    @ViewInject(R.id.ahid)
    private TextView aid;//案号
    @ViewInject(R.id.zxyj)
    private TextView zxyj;//执行依据单位
    @ViewInject(R.id.lxcon)
    private TextView lxcon;//履行情况
    @ViewInject(R.id.pay)
    private TextView pay;//文书确定义务
    @ViewInject(R.id.detail)
    private TextView detail;//失信行为详情
    @ViewInject(R.id.time)
    private TextView time;//发布时间
    @Override
    public void setLayout() {
        setContentView(R.layout.dishonesty_detail);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("失信榜单详情");
    }
}
