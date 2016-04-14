package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.db.UserDao;
import com.beyonditsm.echinfo.entity.BillImageEntity;
import com.beyonditsm.echinfo.entity.UserEntity;
import com.beyonditsm.echinfo.event.OccuEvent;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.IEchinfoUrl;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.GsonUtils;
import com.beyonditsm.echinfo.util.MyBitmapUtils;
import com.beyonditsm.echinfo.util.MyLogUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.beyonditsm.echinfo.view.CircleImageView;
import com.beyonditsm.echinfo.view.MySelfSheetDialog;
import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tandong.sa.eventbus.EventBus;
import com.tandong.sa.zUImageLoader.core.DisplayImageOptions;
import com.tandong.sa.zUImageLoader.core.ImageLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 修改资料
 * Created by wangbin on 16/4/6.
 */
public class UpdateAct extends BaseActivity {
    @ViewInject(R.id.tvOccu)
    private TextView tvOccu;//职业
    @ViewInject(R.id.civHead)
    private CircleImageView civHead;

    @ViewInject(R.id.phone)
    private TextView tvPhone;
    @ViewInject(R.id.etname)
    private EditText etname;
    @ViewInject(R.id.company)
    private EditText company;

    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.mine_head) // 设置图片下载期间显示的图片
            .showImageForEmptyUri(R.mipmap.mine_head) // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.mipmap.mine_head) // 设置图片加载或解码过程中发生错误显示的图片
            .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
            .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
            .build(); // 创建配置过得DisplayImageOption对象


    public static final int PHOTOZOOM = 0;
    public static final int PHOTOTAKE = 1;
    public static final int IMAGE_COMPLETE = 2; // 结果
    public static final int CROPREQCODE = 3; // 截取
    private String photoSavePath;
    private String photoSaveName;

    String appHome = Environment.getExternalStorageDirectory().getAbsolutePath() + "/park_tx";

    private String path;// 图片全路径
    Uri imageUri = null;

    private UserEntity userentity;

    @Override
    public void setLayout() {
        setContentView(R.layout.act_update);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("修改资料");
        userentity = UserDao.getUser();
        if (userentity != null) {
            setUserInfo(userentity);
            MyLogUtils.degug(userentity.toString());
        }
        EventBus.getDefault().register(this);
        setRight("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserEntity user=saveUserInfo();
                saveUser(user);
            }
        });
        photoSavePath = Environment.getExternalStorageDirectory().getPath() + "/ClipHeadPhoto/cache/";
        File tempFile = new File(photoSavePath);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
//        File file = new File(Environment.getExternalStorageDirectory(), "ClipHeadPhoto/cache");
//
//        if (!file.exists())
//            file.mkdirs();
//        photoSavePath = Environment.getExternalStorageDirectory() + "/ClipHeadPhoto/cache/";
    }

    //用户信息不为空显示用户信息
    private void setUserInfo(UserEntity userEntitty) {
        if (userEntitty != null) {
            ImageLoader.getInstance().displayImage(IEchinfoUrl.BASE_URL+userEntitty.getIcon(),civHead,options);
            if (!TextUtils.isEmpty(userEntitty.getUsername())) {
                tvPhone.setText(userEntitty.getUsername());
            }
            if (!TextUtils.isEmpty(userEntitty.getName())) {
                etname.setText(userEntitty.getName());
            }
            if (!TextUtils.isEmpty(userEntitty.getCompanyName())) {
                company.setText(userEntitty.getCompanyName());
            }
            if (!TextUtils.isEmpty(userEntitty.getJob())) {
                tvOccu.setText(userEntitty.getJob());
            }
        }
    }

    //保存用户信息到一个实体中
    private UserEntity saveUserInfo(){
        String tvname=etname.getText().toString().trim();
        String tvcompany=company.getText().toString().trim();
        String occu=tvOccu.getText().toString().trim();
        if(!TextUtils.isEmpty(tvname)){
            userentity.setName(tvname);
        }
        if(!TextUtils.isEmpty(tvcompany)){
            userentity.setCompanyName(tvcompany);
        }
        if(!TextUtils.isEmpty(occu)){
            userentity.setJob(occu);
        }
        MyLogUtils.degug("更新："+userentity.toString());
        return userentity;
    }

    /**
     * 保存用户信息
     * @param entity
     */
    private void saveUser(final UserEntity entity){
        RequestManager.getCommManager().modifyUserInfo(entity, new CallBack() {
            @Override
            public void onSucess(String result) {
                UserDao.updateUser(entity);
                MyToastUtils.showShortToast(getApplicationContext(), "修改信息成功");
                finish();
            }

            @Override
            public void onError(String error) {

            }
        });
    }
    @OnClick({R.id.rlOccu, R.id.civHead})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlOccu:
                openActivity(OccupationAct.class);
                break;
            case R.id.civHead://头像
                MySelfSheetDialog dialog = new MySelfSheetDialog(UpdateAct.this);
                dialog.builder().setTitle("设置头像").addSheetItem("拍照", MySelfSheetDialog.SheetItemColor.Blue, new MySelfSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        photoSaveName = String.valueOf(System.currentTimeMillis()) + ".png";
//                        Uri imageUri = null;
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        imageUri = Uri.fromFile(new File(photoSavePath, photoSaveName));
//                        openCameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(openCameraIntent, PHOTOTAKE);
                    }
                }).addSheetItem("从手机相册选取", MySelfSheetDialog.SheetItemColor.Blue, new MySelfSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(openAlbumIntent, PHOTOZOOM);
                    }
                }).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(OccuEvent event) {
        tvOccu.setText(event.occuText);
    }


    /**
     * 返回的Path
     */
    private String temppath;

    /**
     * 图片选择及拍照结果
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        Uri uri = null;
        switch (requestCode) {
            case PHOTOZOOM:// 相册
                if (data == null) {
                    return;
                }
                uri = data.getData();
                Bitmap userbitmap = MyBitmapUtils.decodeUriAsBitmap(UpdateAct.this, uri);
                File user_head = MyBitmapUtils.saveBitmap(userbitmap, "user_head.png");
                Intent intent3 = new Intent(UpdateAct.this, ClipActivity.class);
                intent3.putExtra("path", Environment.getExternalStorageDirectory() + "/" + "user_head.png");
                startActivityForResult(intent3, IMAGE_COMPLETE);
                break;
            case PHOTOTAKE:// 拍照
                path = photoSavePath + photoSaveName;
                Intent intent2 = new Intent(UpdateAct.this, ClipActivity.class);
                intent2.putExtra("path", path);
                startActivityForResult(intent2, IMAGE_COMPLETE);
                break;

            case IMAGE_COMPLETE:// 完成
                temppath  = data.getStringExtra("path");
                civHead.setImageBitmap(MyBitmapUtils.LoadBigImg(temppath,200,200));
                updateUserIcon(temppath);
//                uploadFile(appHome + "/tx.png");
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 上传图片
     *
     * @param file
     */
    private void updateUserIcon(final String file) {
        Map<String, FileBody> fileMaps = new HashMap<String, FileBody>();
        FileBody fb = new FileBody(new File(file));
        fileMaps.put("file", fb);
        RequestManager.getCommManager().updateUserIcon(fileMaps, new CallBack() {
            @Override
            public void onSucess(String result) {
                BillImageEntity billImageEntity = GsonUtils.json2Bean(result, BillImageEntity.class);
                userentity.setIcon(billImageEntity.getIconFilePath());
                UserDao.updateUser(userentity);
                ImageLoader.getInstance().displayImage(IEchinfoUrl.BASE_URL + billImageEntity.getIconFilePath(), civHead, options);
                sendBroadcast(new Intent(MineAct.USER_INFO));
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
