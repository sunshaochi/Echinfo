package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.event.OccuEvent;
import com.beyonditsm.echinfo.util.MyBitmapUtils;
import com.beyonditsm.echinfo.view.CircleImageView;
import com.beyonditsm.echinfo.view.MySelfSheetDialog;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tandong.sa.eventbus.EventBus;

import java.io.File;

/**
 * 修改资料
 * Created by wangbin on 16/4/6.
 */
public class UpdateAct extends BaseActivity {
    @ViewInject(R.id.tvOccu)
    private TextView tvOccu;//职业
    @ViewInject(R.id.civHead)
    private CircleImageView civHead;

    public static final int PHOTOZOOM = 0;
    public static final int PHOTOTAKE = 1;
    public static final int IMAGE_COMPLETE = 2; // 结果
    public static final int CROPREQCODE = 3; // 截取
    private String photoSavePath;
    private String photoSaveName;

    String appHome = Environment.getExternalStorageDirectory().getAbsolutePath() + "/park_tx";

    private String path;// 图片全路径
    Uri imageUri = null;

    @Override
    public void setLayout() {
        setContentView(R.layout.act_update);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("修改资料");
        EventBus.getDefault().register(this);
        setRight("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        File file = new File(Environment.getExternalStorageDirectory(), "ClipHeadPhoto/cache");

        if (!file.exists())
            file.mkdirs();
        photoSavePath = Environment.getExternalStorageDirectory() + "/ClipHeadPhoto/cache/";
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
                        Uri imageUri = null;
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        imageUri = Uri.fromFile(new File(photoSavePath, photoSaveName));
                        openCameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
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
//                uploadFile(appHome + "/tx.png");
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
