package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.JiucuoAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.HashMap;
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

    @ViewInject(R.id.et_cw)//输入的错误内容；
    private EditText et_cw;
    @ViewInject(R.id.et_sj)//输入的手机号；
    private EditText et_sj;

    private String connects;

    @ViewInject(R.id.gvHome)
    private com.beyonditsm.echinfo.view.MyGridView gvHome;
    private Map<Integer,String>datamap;

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
        datamap=new HashMap<>();
        setTopTitle("纠错");
        adapter=new JiucuoAdapter(getApplicationContext(),this);
        gvHome.setAdapter(adapter);

    }
    @OnClick({R.id.tv_tj})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_tj://提交
                if(isValidate())
                    RequestManager.getCommManager().comJiucuo(connects, cw, phone, new CallBack() {
                        @Override
                        public void onSucess(String result) {
                            MyToastUtils.showShortToast(ErrorAct.this,"提交成功");
                            finish();
                        }

                        @Override
                        public void onError(String error) {
                            MyToastUtils.showShortToast(ErrorAct.this,error);
                        }
                    });
                break;

        }


    }


    public void setData(Map<Integer,String>datamap){
        this.datamap=datamap;

    }


    /**
     * 是否可提交
     *
     * @return
     */
    private boolean isValidate() {
        cw=et_cw.getText().toString();
        phone = et_sj.getText().toString().trim();
        connects="";//被点击的选项内容
        for(int key:datamap.keySet()){
            connects=connects.concat(datamap.get(key).toString()+"");

        }

        if (TextUtils.isEmpty(phone)) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入手机号");
            et_sj.requestFocus();
            return false;
        }
        if(phone.length()!=11){
            MyToastUtils.showShortToast(getApplicationContext(), "请输入正确的手机号");
            et_sj.requestFocus();
            et_sj.setSelection(et_sj.length());
            return false;
        }
        if(TextUtils.isEmpty(connects)&&TextUtils.isEmpty(cw)){
            MyToastUtils.showShortToast(getApplicationContext(),"请选择或输入错误内容");
            return false;
        }
        return true;
    }


}



