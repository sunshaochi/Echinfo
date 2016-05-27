package com.beyonditsm.echinfo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 *
 * 忘记密码
 * Created by wangbin on 16/4/5.
 */
public class ForgetAct extends BaseActivity {

    private LinearLayout llForg;
    private EditText etPhone;
    private EditText etCode;
    private TextView tvCode;
    private EditText etPwd;
    private TextView tvFPwd;
    private LinearLayout llSucess;

    private String phone, code, pwd;
    private int i = 60;
    private Timer timer;
    private MyTimerTask myTask;


    private void assignViews() {
        llForg = (LinearLayout) findViewById(R.id.llForg);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etCode = (EditText) findViewById(R.id.etCode);
        tvCode = (TextView) findViewById(R.id.tvCode);
        etPwd = (EditText) findViewById(R.id.etPwd);
        tvFPwd = (TextView) findViewById(R.id.tvFPwd);
        llSucess = (LinearLayout) findViewById(R.id.llSucess);
    }


    @Override
    public void setLayout() {
        setContentView(R.layout.act_forget);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        assignViews();
        setTopTitle("忘记密码");

    }

    @OnClick({R.id.tvCode, R.id.tvFPwd})
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tvFPwd://重置密码
//                if (isValidate()) {
//                    toFindPwd(phone, code, pwd);
//
//                }
//                break;
            case R.id.tvCode://获取验证码
                phone = etPhone.getText().toString().trim();
                code = etCode.getText().toString().trim();
                pwd = etPwd.getText().toString().trim();

                if (TextUtils.isEmpty(phone)) {
                    MyToastUtils.showShortToast(getApplicationContext(), "请输入手机号");
                    return;
                }
                if (phone.length() != 11) {
                    MyToastUtils.showShortToast(getApplicationContext(), "请输入正确的手机号码");
                    return;
                }
                sendSms(phone, "false");
                break;
            case R.id.tvFPwd://重置密码
                if (isValidate()) {
                    forgetPwd(phone, code, pwd);

                }
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
        code = etCode.getText().toString().trim();
        pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入手机号");
            etPhone.requestFocus();
            return false;
        }
        if (phone.length() != 11) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入正确的手机号");
            etPhone.requestFocus();
            etPhone.setSelection(etPhone.length());
            return false;
        }
        if (pwd.length() < 6) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入至少6位密码");
            etPwd.requestFocus();
            etPwd.setSelection(pwd.length());
            return false;
        }
        if (pwd.length() > 20) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入不超过20位密码");
            etPwd.requestFocus();
            etPwd.setSelection(pwd.length());
            return false;
        }
        if (!EchinfoUtils.checkPwd(pwd)) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入6-20位字母或数字");
            return false;
        }

        if (TextUtils.isEmpty(code)) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入验证码");
            etCode.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入密码");
            etPwd.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * 发送验证码
     * @param phoneNum
     * @param isRegister
     */
//    private void sendSms(String phoneNum,String isRegister) {
//        RequestManager.getCommManager().toSendSms(phoneNum, isRegister, new CallBack() {
//            @Override
//            public void onSucess(String result) {
//                tvCode.setEnabled(false);
//                i = 60;
//                timer = new Timer();
//                myTask = new MyTimerTask();
//                timer.schedule(myTask, 0, 1000);
//                MyToastUtils.showShortToast(getApplicationContext(),"获取验证码成功");
//
//            }
//
//            @Override
//            public void onError(String error) {
//                MyToastUtils.showShortToast(getApplicationContext(), error);
//
//            }
//        }) ;
//    }


    /**
     * 找回密码
     * @param phoneNumber
     * @param captcha
     * @param newPassword
     */
//    private void toFindPwd(String phoneNumber, String captcha, String newPassword){
//        RequestManager.getCommManager().forgetPwd(phoneNumber, captcha, newPassword, new CallBack() {
//            @Override
//            public void onSucess(String result) {
//                llForg.setVisibility(View.GONE);
//                llSucess.setVisibility(View.VISIBLE);
//                MyToastUtils.showShortToast(getApplicationContext(),"重置成功");
//            }
//
//            @Override
//            public void onError(String error) {
//                MyToastUtils.showShortToast(getApplicationContext(),error);
//            }
//        });
//    }

    /**
     * 倒计时
     *
     * @author wangbin
     */
    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            handler.sendEmptyMessage(i--);
        }

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                tvCode.setEnabled(true);
                tvCode.setText("重新发送");
                timer.cancel();
                myTask.cancel();
            } else {
                tvCode.setText(msg.what + "秒");
            }
        }

    };

    /**
     * 发送验证码
     *
     * @param phoneNum
     * @param isRegister
     */
    private void sendSms(String phoneNum, String isRegister) {
        RequestManager.getCommManager().toSendSms(phoneNum, isRegister, new CallBack() {
            @Override
            public void onSucess(String result) {
                i = 60;
                tvCode.setEnabled(false);
                timer = new Timer();
                myTask = new MyTimerTask();
                timer.schedule(myTask, 0, 1000);
            }

            @Override
            public void onError(String error) {
                MyToastUtils.showShortToast(ForgetAct.this, error);
            }
        });
    }

    /**
     * 重置密码
     *
     * @param phoneNumber
     * @param captcha
     * @param newpwd
     */
    private void forgetPwd(String phoneNumber, String captcha, String newpwd) {
        RequestManager.getCommManager().forgetPwd(phoneNumber, captcha, newpwd, new CallBack() {
            @Override
            public void onSucess(String result) {
                setTopTitle("重置密码成功");
                llForg.setVisibility(View.GONE);
                llSucess.setVisibility(View.VISIBLE);
                MyToastUtils.showShortToast(getApplicationContext(), "找回密码成功");
                finish();
            }

            @Override
            public void onError(String error) {
                MyToastUtils.showShortToast(getApplication(), error);
            }
        });
    }

}
