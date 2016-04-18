package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.LawsuitMsgEntity;
import com.beyonditsm.echinfo.entity.ResultData;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.GsonUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by bitch-1 on 2016/4/7.
 * 诉讼详情界面
 */
public class LitigtinfoAct extends BaseActivity {

    @ViewInject(R.id.bzxname)
    private TextView bzxname;//被执行人姓名/名称
    @ViewInject(R.id.idcard)
    private TextView idcard;//身份证号码/组织机构代码
    @ViewInject(R.id.zxfy)
    private TextView zxfy;//执行法院
    @ViewInject(R.id.latime)
    private TextView latime;//立案时间
    @ViewInject(R.id.ahid)
    private TextView ahid;//案号
    @ViewInject(R.id.zxbd)
    private TextView zxbd;//执行标的
    private String id;
    private LawsuitMsgEntity entity;

    @Override
    public void setLayout() {
        setContentView(R.layout.act_litigtinfo);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("诉讼详情");
        id=getIntent().getStringExtra("id");
        if(!TextUtils.isEmpty(id)){
           findLawsuitMsgById(id);
        }
    }

    /**
     * 查询诉讼详情信息
     * @param id
     */
    private void findLawsuitMsgById(String id){
        RequestManager.getCommManager().findLawsuitMsgById(id, new CallBack() {
            @Override
            public void onSucess(String result) {
                ResultData<LawsuitMsgEntity> rd = (ResultData<LawsuitMsgEntity>) GsonUtils.json(result, LawsuitMsgEntity.class);
                entity = rd.getData();
                bzxname.setText(entity.getName());
                idcard.setText(entity.getCardNo());
                zxfy.setText(entity.getCourt());
                latime.setText(entity.getRegistrineTime());
                ahid.setText(entity.getCaseNo());
                zxbd.setText(entity.getZxbd());
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
