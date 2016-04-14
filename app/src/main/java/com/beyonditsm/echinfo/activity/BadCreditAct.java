package com.beyonditsm.echinfo.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.BadCreAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshBase;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 不良信用查询系统
 * Created by wangbin on 16/4/6.
 */
public class BadCreditAct extends BaseActivity {
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_bad_credit);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("不良信用查询系统");
        plv.setPullRefreshEnabled(true);//下拉刷新
        plv.setScrollLoadEnabled(true);//滑动加载
        plv.setPullLoadEnabled(false);//上拉刷新
        plv.setHasMoreData(true);//是否有更多数据
        plv.getRefreshableView().setVerticalScrollBarEnabled(false);//设置右侧滑动
        plv.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
        plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());

        plv.getRefreshableView().setDivider(null);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            }
        });
        plv.getRefreshableView().setAdapter(new BadCreAdapter(this));
        plv.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                openActivity(DishonestyDetailAct.class);
                openActivity(DishonestylistAct.class);

            }
        });
    }
}
