package com.beyonditsm.echinfo.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beyonditsm.echinfo.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * Created by gxy on 2016/4/7.
 */
public class ShareDialog implements OnClickListener {
    private Context context;
    private Dialog dialog;
    private Display display;
    private LinearLayout llweixin;
    private LinearLayout llweixincircle;
    private LinearLayout llweibo;
    private TextView tvclose;
    // 首先在您的Activity中添加如下成员变量
    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");


    private String title = "一企查";
    private String content = "一企查";
    String codeUrl="";

    public ShareDialog(Context context){
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        // 设置分享内容
        mController.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
        // 设置分享图片, 参数2为图片的url地址
        mController.setShareMedia(new UMImage(context,
                "http://www.umeng.com/images/pic/banner_module_social.png"));
    }

    public ShareDialog builder(){
// 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.share_dialog, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());

        // 获取自定义Dialog布局中的控件
        llweixin = (LinearLayout) view.findViewById(R.id.llweixin);
        llweixincircle = (LinearLayout) view.findViewById(R.id.llweixincircle);
        llweibo = (LinearLayout) view.findViewById(R.id.llweibo);
        tvclose = (TextView) view.findViewById(R.id.close);
        llweixin.setOnClickListener(this);
        llweixincircle.setOnClickListener(this);
        llweibo.setOnClickListener(this);
        tvclose.setOnClickListener(this);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        return this;
    }

    public ShareDialog setContent(String title,String content,String codeUrl){
        if ("".equals(title)) {
            this.title="一企查";
        } else {
            this.title=title;
        }
        if ("".equals(content)) {
            this.content="一企查";
        } else {
            this.content=content;
        }
        if ("".equals(codeUrl)) {
            this.codeUrl="";
        } else {
            this.codeUrl=codeUrl;
        }
        return this;
    }
    public ShareDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public ShareDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public void show() {
        dialog.show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llweixin:
                weixinShare();
                break;
            case R.id.llweixincircle:
                weixinCircleShare();
                break;
            case R.id.llweibo:
                weiboShare();
                break;
            case R.id.close:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 朋友圈分享
     */
    private void weixinCircleShare() {
        //appID和appScret需要修改
        String appID = "wx82a8d922ed04d3fb";
        String appSecret = "175ffde69beb52ac75ec781a1f11cc8b";
        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(context, appID, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        //设置微信朋友圈分享内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setShareContent(content + codeUrl );
        //设置朋友圈title
        circleMedia.setTitle(title);
        UMImage localImage = new UMImage(context, R.mipmap.ic_launcher);
        circleMedia.setShareImage(localImage);
        circleMedia.setTargetUrl(codeUrl );
        mController.setShareMedia(circleMedia);
        //分享到朋友圈
        mController.postShare(context, SHARE_MEDIA.WEIXIN_CIRCLE, new SocializeListeners.SnsPostListener() {
            @Override
            public void onStart() {
//                Toast.makeText(getApplicationContext(), "分享到微信朋友圈开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
                if (i == 200) {
                    Toast.makeText(context, "分享成功.", Toast.LENGTH_SHORT).show();
                } else {
                    String eMsg = "";
                    if (i == -101) {
                        eMsg = "没有授权";
                    }
                    Toast.makeText(context, "分享失败[" + i + "] " +
                            eMsg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 微信分享
     */
    private void weixinShare() {
        //appID和appScret需要修改
        String appID = "wx82a8d922ed04d3fb";
        String appSecret = "175ffde69beb52ac75ec781a1f11cc8b";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(context, appID, appSecret);
        wxHandler.addToSocialSDK();

        //设置分享内容
        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent.setShareContent(content);
        weixinContent.setTitle(title);
        UMImage localImage = new UMImage(context, R.mipmap.ic_launcher);
        weixinContent.setShareImage(localImage);
        weixinContent.setTargetUrl(codeUrl);
        mController.setShareMedia(weixinContent);
        //分享到微信
        mController.postShare(context, SHARE_MEDIA.WEIXIN, new SocializeListeners.SnsPostListener() {
            @Override
            public void onStart() {
//                Toast.makeText(getApplicationContext(), "分享到微信开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
                if (i == 200) {
                    Toast.makeText(context, "分享成功.", Toast.LENGTH_SHORT).show();
                } else {
                    String eMsg = "";
                    if (i == -101) {
                        eMsg = "没有授权";
                    }
                    Toast.makeText(context, "分享失败[" + i + "] " +
                            eMsg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 微博分享
     */
    private void weiboShare() {
        //设置新浪SSO handler(授权)
        //    mController.getConfig().setSsoHandler(new SinaSsoHandler());
        mController.getConfig().setDefaultShareLocation(false);

        //设置分享内容
        SinaShareContent sinaContent = new SinaShareContent();
        sinaContent.setShareContent(content + codeUrl );
        sinaContent.setShareImage(new UMImage(context, R.mipmap.ic_launcher));
        mController.setShareMedia(sinaContent);
        //分享到新浪微博
        mController.postShare(context, SHARE_MEDIA.SINA, new SocializeListeners.SnsPostListener() {
            @Override
            public void onStart() {
//                Toast.makeText(getApplicationContext(), "分享到微博开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
                if (i == 200) {
                    Toast.makeText(context, "分享成功.", Toast.LENGTH_SHORT).show();
                } else {
                    String eMsg = "";
                    if (i == -101) {
                        eMsg = "没有授权";
                    }
                    Toast.makeText(context, "分享失败[" + i + "] " +
                            eMsg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
