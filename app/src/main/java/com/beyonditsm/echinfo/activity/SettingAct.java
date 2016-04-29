package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.widget.ShareDialog;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.UMSsoHandler;

/**
 * 设置
 * Created by wangbin on 16/4/5.
 */
public class SettingAct extends BaseActivity {

    // 首先在您的Activity中添加如下成员变量
    UMSocialService mController;

    @Override
    public void setLayout() {
        setContentView(R.layout.act_setting);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("设置");
        mController = UMServiceFactory.getUMSocialService("com.umeng.share");
        mController.getConfig().closeToast();
    }

    @OnClick({R.id.rlRecomm,R.id.rlUPwd,R.id.rlAbout})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rlRecomm://推荐好友
                ShareDialog dialog=new ShareDialog(SettingAct.this).builder();
                dialog.show();
                break;
            case R.id.rlUPwd://修改密码
                openActivity(UpdatePwdAct.class);
                break;
            case R.id.rlAbout://关于我们
                openActivity(AboutAct.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**使用SSO授权必须添加如下代码 */
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode) ;
        if(ssoHandler != null){
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
