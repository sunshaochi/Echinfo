package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.widget.ShareDialog;
import com.lidroid.xutils.view.annotation.ViewInject;

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

    @Override
    public void setLayout() {
        setContentView(R.layout.dishonestyinfo_detail);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("失信信息");
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareDialog dialog = new ShareDialog(DisinfodetailAct.this).builder();
                dialog.setContent("失信信息", "失信信息", "");
                dialog.show();
            }
        });
    }
}
