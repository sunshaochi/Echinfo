package com.beyonditsm.echinfo.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.ExAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.leaf.library.widget.MyExpandableListView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 失信榜单详情列表
 * Created by gxy on 2016/4/13.
 */
public class DishonestylistAct extends BaseActivity {

    @ViewInject(R.id.list)
    private MyExpandableListView listView;
    private List<String> list;
    private List<List<String>> childlist;
    private ExAdapter adapter;
    @Override
    public void setLayout() {
        setContentView(R.layout.dishonestylist_detail);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("失信榜单详情 2016年2月");
        list=new ArrayList<>();
        childlist=new ArrayList<>();
        list.add("失信行业TOP10");
        list.add("失信企业TOP10");
        list.add("失信个人TOP10");
        list.add("失信企业地域TOP10");
        list.add("欠薪公司TOP10");
        list.add("投资黑名单TOP10");
        list.add("黑中介TOP10");
        list.add("旅游类黑名单TOP10");
        list.add("保险类黑名单TOP10");
        list.add("医药类黑名单TOP10");
        childlist.add(list);
        adapter=new ExAdapter(DishonestylistAct.this,list,childlist);
        listView.setAdapter(adapter);
        listView.setChildDivider(new ColorDrawable(Color.WHITE));
        //item点击事件
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0 || groupPosition == 3) {
                    listView.setClickable(false);
                } else {
                    openActivity(DishonestyDetailAct.class);
                }
                return true;
            }
        });

    }
}
