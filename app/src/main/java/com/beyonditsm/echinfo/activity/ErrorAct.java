package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.JiucuoAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.List;
import java.util.Map;

/**
 * Created by bitch-1 on 2016/4/5.
 * 纠错界面
 */
public class ErrorAct extends BaseActivity {

//    工商信息", "企业图谱", "行业分析", "失业信息", "诉讼信息",
//            "对外投资","股东信息","企业咨询","年报信息","分子机构","主要成员","变更记录"

//    @ViewInject(R.id.ck_gsxx)
//    private CheckBox ck_gsxx;
//    @ViewInject(R.id.ck_qytp)
//    private CheckBox ck_qytp;
//    @ViewInject(R.id.ck_hyfx)
//    private CheckBox ck_hyfx;
//    @ViewInject(R.id.ck_sxxx)
//    private CheckBox ck_sxxx;
//    @ViewInject(R.id.ck_ssxx)
//    private CheckBox ck_ssxx;
//    @ViewInject(R.id.ck_dwtz)
//    private CheckBox ck_dwtz;
//    @ViewInject(R.id.ck_gdxx)
//    private CheckBox ck_gdxx;
//    @ViewInject(R.id.ck_qyzx)
//    private CheckBox ck_qyzx;
//    @ViewInject(R.id.ck_nbxx)
//    private CheckBox ck_nbxx;
//    @ViewInject(R.id.ck_fzjg)
//    private CheckBox ck_fzjg;
//    @ViewInject(R.id.ck_zycy)
//    private CheckBox ck_zycy;
//    @ViewInject(R.id.ck_bgjl)
//    private CheckBox ck_bgjl;

    @ViewInject(R.id.et_cw)
    private EditText et_cw;
    @ViewInject(R.id.et_sj)
    private EditText et_sj;

    @ViewInject(R.id.gvHome)
    private com.beyonditsm.echinfo.view.MyGridView gvHome;

//    private CheckBox[] cbs;
//    private List<String> selector ;//用来封装被选中的text

    private String cw;//自己输入的错误
    private String phone;//输入手机号

    private Map<Boolean,String>map;

    private List<String>list;
    private JiucuoAdapter adapter;

    @Override
    public void setLayout() {
        setContentView(R.layout.act_error);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("纠错");
        adapter=new JiucuoAdapter(getApplicationContext());
        gvHome.setAdapter(adapter);



    }
    @OnClick({R.id.tv_tj})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_tj:

                break;

        }


    }


}



