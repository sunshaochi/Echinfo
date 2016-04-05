package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 登陆
 * Created by wangbin on 16/4/3.
 */
public class LoginAct extends BaseActivity {

    private RelativeLayout rlBack;
    private TextView tvReg;
    private EditText etPhone;
    private EditText etPwd;
    private TextView tvFog;
    private LinearLayout llWeixin;
    private LinearLayout llQq;
    private LinearLayout llWeibo;

    private String phone, pwd;

    private void assignViews() {
        rlBack = (RelativeLayout) findViewById(R.id.rlBack);
        tvReg = (TextView) findViewById(R.id.tvReg);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etPwd = (EditText) findViewById(R.id.etPwd);
        tvFog = (TextView) findViewById(R.id.tvFog);
        llWeixin = (LinearLayout) findViewById(R.id.llWeixin);
        llQq = (LinearLayout) findViewById(R.id.llQq);
        llWeibo = (LinearLayout) findViewById(R.id.llWeibo);
    }


    @Override
    public void setLayout() {
        setContentView(R.layout.act_login);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        assignViews();
    }

    @OnClick({R.id.tvReg, R.id.tvLogin, R.id.tvFog, R.id.rlBack})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlBack:
                finish();
                break;
            case R.id.tvLogin://登陆
                if (isValidate()) {
                    openActivity(MineAct.class);
                }
                break;
            case R.id.tvReg://注册
                openActivity(RegAct.class);
                break;
            case R.id.tvFog://忘记密码
                openActivity(ForgetAct.class);
                break;
        }
    }


    /**
     * 是否可用
     *
     * @return
     */
    private boolean isValidate() {
        phone = etPhone.getText().toString().trim();
        pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入手机号");
            etPhone.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(pwd)) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入密码");
            etPwd.requestFocus();
            return false;
        }
        return true;
    }
}
