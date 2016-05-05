package com.beyonditsm.echinfo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.db.UserDao;
import com.beyonditsm.echinfo.entity.UserEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.IEchinfoUrl;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.view.CircleImageView;
import com.beyonditsm.echinfo.view.MyAlertDialog;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tandong.sa.zUImageLoader.core.DisplayImageOptions;
import com.tandong.sa.zUImageLoader.core.ImageLoader;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;

/**
 * 我的
 * Created by wangbin on 16/4/5.
 */
public class MineAct extends BaseActivity {
    @ViewInject(R.id.civHead)
    private CircleImageView civHeadIcon;
    @ViewInject(R.id.name)
    private TextView tvUserName;
    @ViewInject(R.id.occur)
    private TextView occur;
    @ViewInject(R.id.company)
    private TextView company;
    private DisplayImageOptions civOptions = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.mine_head) // 设置图片下载期间显示的图片
            .showImageForEmptyUri(R.mipmap.mine_head) // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.mipmap.mine_head) // 设置图片加载或解码过程中发生错误显示的图片
            .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
            .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
            .build(); // 创建配置过得DisplayImageOption对象

    private MyReceiver myReceiver;
    private UMSocialService mController;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_mine);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("我的");
        mController = UMServiceFactory.getUMSocialService("com.umeng.login");
        getUserInfo(MineAct.this);
        UserEntity userEntity = UserDao.getUser();
        if (userEntity != null) {
            if (!TextUtils.isEmpty(userEntity.getUsername())) {
                tvUserName.setText(userEntity.getUsername());
            }
            if (!TextUtils.isEmpty(userEntity.getIcon())) {
                ImageLoader.getInstance().displayImage(IEchinfoUrl.BASE_URL + userEntity.getIcon(), civHeadIcon, civOptions);
            }
            if(!TextUtils.isEmpty(userEntity.getJob())){
                occur.setText(userEntity.getJob());
            }
            if(!TextUtils.isEmpty(userEntity.getCompanyName())){
                company.setText(userEntity.getCompanyName());
            }
        }
    }

    @OnClick({R.id.ivSet, R.id.rlHead, R.id.cvFollow, R.id.cvMsg, R.id.cvFeedBack, R.id.cvPro, R.id.tvExit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSet://设置
                openActivity(SettingAct.class);
                break;
            case R.id.rlHead://用户资料
                openActivity(UpdateAct.class);
                break;
            case R.id.cvFollow://我的关注
                openActivity(MyFollowAct.class);
                break;
            case R.id.cvMsg://系统消息
                openActivity(MsgAct.class);
                break;
            case R.id.cvFeedBack://意见反馈
                openActivity(FeedBackAct.class);
                break;
            case R.id.cvPro://常见问题
                Intent intent=new Intent(MineAct.this,WebAct.class);
                intent.putExtra(WebAct.WEB_TYPE,3);
                startActivity(intent);
                break;
            case R.id.tvExit://退出
                new MyAlertDialog(MineAct.this).builder().setTitle("提示").setMsg("确认退出吗？", true)
                        .setPositiveButton("退出", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                MyLogUtils.degug(SHARE_MEDIA.SINA.isCustomPlatform()+"新浪微博");
//                                MyLogUtils.degug(SHARE_MEDIA.QQ.isCustomPlatform()+"QQ");
//                                MyLogUtils.degug(SHARE_MEDIA.WEIXIN.isCustomPlatform()+"微信");
//                                if(!SHARE_MEDIA.SINA.isCustomPlatform()){
//                                    MyLogUtils.degug("新浪微博平台");
//                                    logout(SHARE_MEDIA.SINA);
//                                }
//                                else if(!SHARE_MEDIA.QQ.isCustomPlatform()){
//                                    MyLogUtils.degug("QQ平台");
//                                    logout(SHARE_MEDIA.QQ);
//                                }
//                                else if(!SHARE_MEDIA.WEIXIN.isCustomPlatform()){
//                                    MyLogUtils.degug("微信平台");
//                                    logout(SHARE_MEDIA.WEIXIN);
//
//                                }
//                                else {
                                    loginOut();
//                                }

                            }
                        }, true).setNegativeButton("取消",null).show();
                break;
        }
    }

    /**
     * 注销本次登录</br>
     */
    private void logout(final SHARE_MEDIA platform) {
        mController.deleteOauth(MineAct.this, platform, new SocializeListeners.SocializeClientListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int status, SocializeEntity entity) {
                String showText = "解除" + platform.toString() + "平台授权成功";
                if (status != StatusCode.ST_CODE_SUCCESSED) {
                    showText = "解除" + platform.toString() + "平台授权失败[" + status + "]";
                }else {
                    clearUserInfo(MineAct.this);
                }
                finish();
                Toast.makeText(MineAct.this, showText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 登出
     */
    private void loginOut(){
        RequestManager.getCommManager().unLogin("", new CallBack() {
            @Override
            public void onSucess(String result) {
                UserDao.deleteUser();
                sendBroadcast(new Intent(MainAct.MAIN_RECEIVER));
                finish();
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    public static final String USER_INFO = "com.update.user";
    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            UserEntity userEntity = UserDao.getUser();
            if (userEntity != null) {
                if (!TextUtils.isEmpty(userEntity.getName())) {
                    tvUserName.setText(userEntity.getName());
                }
                if (!TextUtils.isEmpty(userEntity.getIcon())) {
                    ImageLoader.getInstance().displayImage(IEchinfoUrl.BASE_URL + userEntity.getIcon(), civHeadIcon, civOptions);
                }
                if(!TextUtils.isEmpty(userEntity.getJob())){
                    occur.setText(userEntity.getJob());
                }
                if(!TextUtils.isEmpty(userEntity.getCompanyName())){
                    company.setText(userEntity.getCompanyName());
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (myReceiver==null) {
            myReceiver = new MyReceiver();
        }
        registerReceiver(myReceiver, new IntentFilter(USER_INFO));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReceiver!=null){
            unregisterReceiver(myReceiver);
        }
    }

    //获取保存在本地的用户信息
    private void getUserInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String name=sp.getString("screen_name","");
        String url=sp.getString("profile_image_url","");
        if (!TextUtils.isEmpty(name)) {
            tvUserName.setText(name);
        }
        if (!TextUtils.isEmpty(url)) {
            ImageLoader.getInstance().displayImage(url, civHeadIcon, civOptions);
        }
    }
    //清除保存在本地的用户信息
    private void clearUserInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        editor.commit();
    }
}
