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

import com.beyonditsm.echinfo.AppManager;
import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.db.UserDao;
import com.beyonditsm.echinfo.entity.ResultData;
import com.beyonditsm.echinfo.entity.UserDataEntity;
import com.beyonditsm.echinfo.entity.UserEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.GsonUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 注册
 * Created by wangbin on 16/4/5.
 */
public class RegAct extends BaseActivity {
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
        setContentView(R.layout.act_reg);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        assignViews();
        setTopTitle("注册");
    }


    @OnClick({R.id.tvCode, R.id.tvFPwd,R.id.tvService})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCode://获取验证码
                phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    MyToastUtils.showShortToast(getApplicationContext(), "请输入手机号");
                    return;
                }
                if (phone.length() != 11) {
                    MyToastUtils.showShortToast(getApplicationContext(), "请输入正确的手机号码");
                    return;
                }
                if(!EchinfoUtils.checkPwd(phone)){
                    MyToastUtils.showShortToast(getApplicationContext(), "请输入正确的手机号码");
                    return;
                }
                if(!EchinfoUtils.checkCellPhone(phone)){
                    MyToastUtils.showShortToast(getApplicationContext(), "请输入正确的手机号码");
                    return;
                }
                sendSms(phone, "true");

                break;
            case R.id.tvFPwd://注册完成
                if (isValidate()) {
                    toRegister(phone,code,pwd);
//                    llForg.setVisibility(View.GONE);
//                    llSucess.setVisibility(View.VISIBLE);
//                    setTopTitle("注册完成");
                }
                break;
            case R.id.tvService://服务协议
                openActivity(WebAct.class);
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
        if(phone.length()!=11){
            MyToastUtils.showShortToast(getApplicationContext(), "请输入正确的手机号");
            etPhone.requestFocus();
            etPhone.setSelection(etPhone.length());
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
        if(pwd.length()<6){
            MyToastUtils.showShortToast(getApplicationContext(),"请输入至少6位密码");
            etPwd.requestFocus();
            etPwd.setSelection(pwd.length());
            return false;
        }
        if(pwd.length()>20){
            MyToastUtils.showShortToast(getApplicationContext(), "请输入不超过20位密码");
            etPwd.requestFocus();
            etPwd.setSelection(pwd.length());
            return false;
        }
        if(!EchinfoUtils.checkPwd(pwd)){
            MyToastUtils.showShortToast(getApplicationContext(), "请输入6-20位字母或数字");
            return false;
        }
        return true;
    }

    /**
     * 倒计时
     *
     * @author wangbin
     *
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
     * @param phoneNum
     * @param isRegister
     */
    private void sendSms(String phoneNum,String isRegister) {
        RequestManager.getCommManager().toSendSms(phoneNum, isRegister, new CallBack() {
            @Override
            public void onSucess(String result) {
                i = 60;
                tvCode.setEnabled(false);
                timer = new Timer();
                myTask = new MyTimerTask();
                timer.schedule(myTask, 0, 1000);
                MyToastUtils.showShortToast(getApplicationContext(), getResources().getString(R.string.sms_sucess));
            }

            @Override
            public void onError(String error) {
                MyToastUtils.showShortToast(RegAct.this,error);
            }
        });
    }
    /**
     * 注册
     * @param phonenumber 手机号
     * @param captcha 短信
     * @param password 密码
     */
    private void toRegister(String phonenumber,String captcha,String password){
        RequestManager.getCommManager().toRegister( phonenumber, captcha, password, new CallBack() {

            @Override
            public void onSucess(String result) {
                ResultData<UserDataEntity> rd = (ResultData<UserDataEntity>) GsonUtils.json(result, UserDataEntity.class);
                UserDataEntity userEntitty = rd.getData();
                UserEntity user = userEntitty.getUser();
                UserDao.saveUser(user);
//                openActivity(MineAct.class);
                MyToastUtils.showShortToast(getApplicationContext(), rd.getMessage());
                AppManager.getAppManager().finishActivity(LoginAct.class);
                finish();
            }

            @Override
            public void onError(String error) {
                MyToastUtils.showShortToast(getApplicationContext(), error);
            }
        });
    }
}
