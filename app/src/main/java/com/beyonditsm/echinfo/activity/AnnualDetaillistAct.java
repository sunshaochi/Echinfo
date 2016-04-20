package com.beyonditsm.echinfo.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.AnnualExAdapter;
import com.beyonditsm.echinfo.adapter.ExAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.leaf.library.widget.MyExpandableListView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 年报详情列表
 * Created by gxy on 2016/4/19.
 */
public class AnnualDetaillistAct extends BaseActivity {

    @ViewInject(R.id.list)
    private ExpandableListView listView;
    private List<String> list;
    private List<List<String>> childlist;
    private AnnualExAdapter adapter;
    @Override
    public void setLayout() {
        setContentView(R.layout.annual_detail_list);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("2015年年报详情");
        setRight("纠错", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ErrorAct.class);
            }
        });
        list=new ArrayList<>();
        childlist=new ArrayList<>();
        list.add("企业基本信息");
        list.add("网站或网店信息");
        list.add("股东信息");
        list.add("对外投资");
        list.add("企业资产状况信息");
        list.add("对外提供保证担保信息");
        list.add("股权变更信息");
        list.add("修改记录");
        childlist.add(list);
        adapter=new AnnualExAdapter(AnnualDetaillistAct.this,list,childlist);
        listView.setAdapter(adapter);
        listView.setChildDivider(new ColorDrawable(Color.WHITE));
        //item点击事件
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                if (groupPosition == 0 || groupPosition == 3) {
//                    listView.setClickable(false);
//                } else {
//                    openActivity(DishonestyDetailAct.class);
//                }
                return true;
            }
        });

    }
}
