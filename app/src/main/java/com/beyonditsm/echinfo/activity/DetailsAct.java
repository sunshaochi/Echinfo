package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.AnnualEntity;
import com.beyonditsm.echinfo.entity.ResultData;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.GsonUtils;
import com.beyonditsm.echinfo.widget.ShareDialog;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by bitch-1 on 2016/4/6.
 * 企业资讯详情
 */
public class DetailsAct extends BaseActivity {
    @ViewInject(R.id.iv_cd)
    private ImageView ivcd;
    @ViewInject(R.id.title)
    private TextView title;
    @ViewInject(R.id.come)
    private TextView come;
    @ViewInject(R.id.time)
    private TextView time;
    @ViewInject(R.id.content)
    private TextView content;
    private String id=null;
    private AnnualEntity entity;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_details);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("详情");
        id=getIntent().getStringExtra("id");
        ivcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareDialog dialog = new ShareDialog(DetailsAct.this).builder();
                dialog.setContent("企业资讯", "enen", "");
                dialog.show();
            }
        });
        findEnterpriseNewsById(id);
    }

    /**
     * 根据id查询企业资讯详情
     * @param id
     */
    private void findEnterpriseNewsById(String id){
        RequestManager.getCommManager().findEnterpriseNewsById(id, new CallBack() {
            @Override
            public void onSucess(String result) {
                ResultData<AnnualEntity> rd = (ResultData<AnnualEntity>) GsonUtils.json(result, AnnualEntity.class);
                entity = rd.getData();
                title.setText(entity.getNewsName());
                come.setText("来源："+entity.getNewsFrom());
                time.setText(entity.getNewsTime());
                content.setText(entity.getContent());
            }

            @Override
            public void onError(String error) {

            }
        });
    }

}
