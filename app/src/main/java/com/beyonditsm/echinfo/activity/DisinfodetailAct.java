package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.BadCreditEntity;
import com.beyonditsm.echinfo.widget.ShareDialog;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 失信榜单详情
 * Created by gxy on 2016/4/18.
 */
public class DisinfodetailAct extends BaseActivity {
    @ViewInject(R.id.iv_cd)
    private ImageView ivShare;
    @ViewInject(R.id.fycps)
    private LinearLayout fycps;//查看法院裁判书
    @ViewInject(R.id.bzxr)
    private TextView bzxr;//被执行人
    @ViewInject(R.id.bzxrId)
    private TextView bzxrId;//被执行人证件号
    @ViewInject(R.id.pc)
    private TextView pc;//赔偿
    @ViewInject(R.id.lxqk)
    private TextView lxqk;//被执行的履行情况失信被执行人行为具体情形
    @ViewInject(R.id.jtqk)
    private TextView jtqk;//失信被执行人行为具体情形
    @ViewInject(R.id.zxfy)
    private TextView zxfy;//执行法院
    @ViewInject(R.id.province)
    private TextView province;//省份
    @ViewInject(R.id.yjwh)
    private TextView yjwh;//执行依据文号
    @ViewInject(R.id.latime)
    private TextView latime;//立案时间
    @ViewInject(R.id.anhaoid)
    private TextView anhaoid;//案号
    @ViewInject(R.id.zxyjdw)
    private TextView zxyjdw;//做出执行依据单位
    @ViewInject(R.id.time)
    private TextView time;//发布时间

    private BadCreditEntity entity;

    @Override
    public void setLayout() {
        setContentView(R.layout.dishonestyinfo_detail);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("失信信息");
        entity=getIntent().getParcelableExtra("entity");
        setInfo(entity);
    }
    private void setInfo(BadCreditEntity entity){
        if(entity!=null){
            if(!TextUtils.isEmpty(entity.getIname()))
                bzxr.setText(entity.getIname());
            if(!TextUtils.isEmpty(entity.getCardnum()))
                bzxrId.setText(entity.getCardnum());
            if(!TextUtils.isEmpty(entity.getDuty()))
                pc.setText(entity.getDuty());
            if(!TextUtils.isEmpty(entity.getPerformance()))
                lxqk.setText(entity.getPerformance());
            if(!TextUtils.isEmpty(entity.getDisrupttypename()))
                jtqk.setText(entity.getDisrupttypename());
            if(!TextUtils.isEmpty(entity.getCourtname()))
                zxfy.setText(entity.getCourtname());
            if(!TextUtils.isEmpty(entity.getAreaname()))
                province.setText(entity.getAreaname());
            if(!TextUtils.isEmpty(entity.getGistid()))
                yjwh.setText(entity.getGistid());
            if(!TextUtils.isEmpty(entity.getRegdate()))
                latime.setText(entity.getRegdate());
            if(!TextUtils.isEmpty(entity.getCasecode()))
                anhaoid.setText(entity.getCasecode());
            if(!TextUtils.isEmpty(entity.getGistunit()))
                zxyjdw.setText(entity.getGistunit());
            if(!TextUtils.isEmpty(entity.getPublishdate()))
                time.setText(entity.getPublishdate());
        }
    }
    @OnClick({R.id.iv_cd,R.id.fycps})
    public void todo(View v){
        switch (v.getId()){
            case R.id.iv_cd:
                ShareDialog dialog = new ShareDialog(DisinfodetailAct.this).builder();
                dialog.setContent("失信信息", "失信信息", "");
                dialog.show();
                break;
//            case R.id.fycps:
//                Intent intent=new Intent(DisinfodetailAct.this,CourtAct.class);
//                startActivity(intent);
//                break;
        }
    }
}
