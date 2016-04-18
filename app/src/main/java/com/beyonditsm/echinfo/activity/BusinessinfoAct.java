package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
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

    @Override
    public void setLayout() {
        setContentView(R.layout.bunisnessinfo);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("工商信息");
        setRight("纠错", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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

            }

            @Override
            public void onError(String error) {

            }
        });
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
