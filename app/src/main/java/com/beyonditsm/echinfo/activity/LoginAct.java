package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import java.util.Map;

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

    UMSocialService mController;

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
        mController = UMServiceFactory.getUMSocialService("com.umeng.login");
        addPlatform();
    }

    @OnClick({R.id.tvReg, R.id.tvLogin, R.id.tvFog, R.id.rlBack, R.id.llWeixin, R.id.llQq, R.id.llWeibo})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlBack:
                finish();
                break;
            case R.id.tvLogin://登陆
                if (isValidate()) {
                    toLogin(phone, pwd, "", "");
                }
                break;
            case R.id.tvReg://注册
                openActivity(RegAct.class);
                break;
            case R.id.tvFog://忘记密码
                openActivity(ForgetAct.class);
                break;
            case R.id.llWeixin://微信登录
                login(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.llQq://QQ登录
                login(SHARE_MEDIA.QQ);
                break;
            case R.id.llWeibo://微博登录
                //设置新浪SSO handler
                mController.getConfig().setSsoHandler(new SinaSsoHandler());
                login(SHARE_MEDIA.SINA);
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
        if (phone.length() != 11) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入正确的手机号");
            etPhone.requestFocus();
            etPhone.setSelection(etPhone.length());
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
     * 添加平台
     */
    private void addPlatform() {
        //参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(LoginAct.this, "1105241351",
                "gdYoPeGegmP0PzwC");
        qqSsoHandler.addToSocialSDK();
        // 添加微信平台
        String appId = "wx82a8d922ed04d3fb";
        String appSecret = "175ffde69beb52ac75ec781a1f11cc8b";
        UMWXHandler wxHandler = new UMWXHandler(LoginAct.this, appId, appSecret);
        wxHandler.addToSocialSDK();
    }

    /**
     * 授权。如果授权成功，则获取用户信息</br>
     */
    private void login(final SHARE_MEDIA platform) {
        mController.doOauthVerify(LoginAct.this, platform, new SocializeListeners.UMAuthListener() {

            @Override
            public void onStart(SHARE_MEDIA platform) {
                Toast.makeText(LoginAct.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
            }

            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {
                Toast.makeText(LoginAct.this, "onComplete", Toast.LENGTH_SHORT).show();
                String uid = value.getString("uid");
                if (!TextUtils.isEmpty(uid)) {
                    getUserInfo(platform);
                } else {
                    Toast.makeText(LoginAct.this, "授权失败...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
            }
        });
    }

    /**
     * 获取授权平台的用户信息</br>
     */
    private void getUserInfo(SHARE_MEDIA platform) {
        mController.getPlatformInfo(LoginAct.this, platform, new SocializeListeners.UMDataListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int status, Map<String, Object> info) {
                // String showText = "";
                // if (status == StatusCode.ST_CODE_SUCCESSED) {
                // showText = "用户名：" + info.get("screen_name").toString();
                // Log.d("#########", "##########" + info.toString());
                // } else {
                // showText = "获取用户信息失败";
                // }
                if (info != null) {
                    openActivity(MineAct.class);
                    Toast.makeText(LoginAct.this, info.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 注销本次登录</br>
     */
    private void logout(final SHARE_MEDIA platform) {
        mController.deleteOauth(LoginAct.this, platform, new SocializeListeners.SocializeClientListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int status, SocializeEntity entity) {
                String showText = "解除" + platform.toString() + "平台授权成功";
                if (status != StatusCode.ST_CODE_SUCCESSED) {
                    showText = "解除" + platform.toString() + "平台授权失败[" + status + "]";
                }
                Toast.makeText(LoginAct.this, showText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
                requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    private void toLogin(final String username, final String password, String yzm, String key) {
        RequestManager.getCommManager().toLogin(username, password, yzm, key, new CallBack() {

            @Override
            public void onSucess(String result) {
                openActivity(MineAct.class);
            }

            @Override
            public void onError(String error) {
                MyToastUtils.showShortToast(getApplicationContext(), error);
            }
        });
    }

}
