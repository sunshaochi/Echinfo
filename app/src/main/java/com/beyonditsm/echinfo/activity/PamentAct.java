package com.beyonditsm.echinfo.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.FollowAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshBase;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by bitch-1 on 2016/4/5.
 * 分之机构界面
 */
public class PamentAct extends BaseActivity {
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_my_follow);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("分之机构");
        setRight("纠错",new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ErrorAct.class);
            }
        });
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
        plv.getRefreshableView().setAdapter(new FollowAdapter(this));
    }

}
