package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.BusinessEntity;
import com.beyonditsm.echinfo.entity.ResultData;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.GsonUtils;
import com.leaf.library.widget.MyListView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 工商信息
 * Created by gxy on 2016/4/15.
 */
public class BusinessinfoAct extends BaseActivity{
    @ViewInject(R.id.money)
    private TextView money;//注册资本
    @ViewInject(R.id.people)
    private TextView people;//法定代表人
    @ViewInject(R.id.datecl)
    private TextView datecl;//成立日期
    @ViewInject(R.id.datefz)
    private TextView datefz;//发照日期
    @ViewInject(R.id.busireg)
    private TextView busireg;//工商注册号
    @ViewInject(R.id.orgId)
    private TextView orgId;//组织机构代码
    @ViewInject(R.id.listview)
    private MyListView listView;//曾用名
    @ViewInject(R.id.type)
    private TextView type;//企业类型
    @ViewInject(R.id.state)
    private TextView state;//经营状态
    @ViewInject(R.id.address)
    private TextView address;//公司地址
    @ViewInject(R.id.time)
    private TextView time;//营业期限
    @ViewInject(R.id.org)
    private TextView org;//登记机关
    @ViewInject(R.id.range)
    private TextView range;//经营范围
    private MyAdapter adapter;

    private String id=null;
    private BusinessEntity entity;

    @Override
    public void setLayout() {
        setContentView(R.layout.bunisnessinfo);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("工商信息");
        id=getIntent().getStringExtra("id");
        setRight("纠错", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ErrorAct.class);
            }
        });
        findVietinbanhInfoByCompanyId(id);
        adapter=new MyAdapter();
        listView.setDivider(null);
        listView.setAdapter(adapter);
    }

    /**
     *工商信息
     * @param companyId
     */
    private void findVietinbanhInfoByCompanyId(String companyId){
        RequestManager.getCommManager().findVietinbanhInfoByCompanyId(companyId, new CallBack() {
            @Override
            public void onSucess(String result) {
                ResultData<BusinessEntity> rd = (ResultData<BusinessEntity>) GsonUtils.json(result, BusinessEntity.class);
                entity = rd.getData();
                setInfo(entity);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void setInfo(BusinessEntity entity){
        if(entity!=null){
            if(!TextUtils.isEmpty(entity.getRegistCapital())) {
                money.setText(entity.getRegistCapital()+"万元");
            }else {
                money.setText("");
            }
            if(!TextUtils.isEmpty(entity.getLegalRepPersion())) {
                people.setText(entity.getLegalRepPersion());
            }else {
                people.setText("");
            }
            if(!TextUtils.isEmpty(entity.getCompanyCreatTime())) {
                datecl.setText(entity.getCompanyCreatTime());
            }else {
                datecl.setText("");
            }
            if(!TextUtils.isEmpty(entity.getCompanyCreatTime())) {
                datefz.setText(entity.getCompanyCreatTime());//发照日期
            }else {
                datefz.setText("");
            }
            if(!TextUtils.isEmpty(entity.getRegistNo())) {
                busireg.setText(entity.getRegistNo());
            }else {
                busireg.setText("");
            }
            if(!TextUtils.isEmpty(entity.getCreditNo())) {
                orgId.setText(entity.getCreditNo());
            }else {
                orgId.setText("");
            }
            if(!TextUtils.isEmpty(entity.getCompanyType())) {
                type.setText(entity.getCompanyType());
            }else {
                type.setText("");
            }
            if(!TextUtils.isEmpty(entity.getRecordStatus())) {
                state.setText(entity.getRecordStatus());
            }else {
                state.setText("");
            }
            if(!TextUtils.isEmpty(entity.getAddress())) {
                address.setText(entity.getAddress());
            }else {
                address.setText("");
            }
            if(!TextUtils.isEmpty(entity.getBusinessDeadline())) {
                time.setText(entity.getBusinessDeadline());
            }else {
                time.setText("");
            }
            if(!TextUtils.isEmpty(entity.getManageScope())) {
                org.setText(entity.getManageScope());
            }else {
                org.setText("");
            }
            if(!TextUtils.isEmpty(entity.getApproveMsg())) {
                range.setText(entity.getApproveMsg());
            }else {
                range.setText("");
            }
        }
    }
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView= LayoutInflater.from(BusinessinfoAct.this).inflate(R.layout.text_layout,null);
            TextView tv= (TextView) convertView.findViewById(R.id.tex);
            return convertView;
        }
    }
}
