package com.beyonditsm.echinfo.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseFragment;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 *我的关注
 * Created by wangbin on 16/4/8.
 */
public class MyFollowFrg extends BaseFragment{
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;
    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frg_search,null);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        plv.setPullRefreshEnabled(true);//下拉刷新
        plv.setScrollLoadEnabled(true);//滑动加载
        plv.setPullLoadEnabled(false);//上拉刷新
        plv.setHasMoreData(true);//是否有更多数据
        plv.getRefreshableView().setVerticalScrollBarEnabled(false);//设置右侧滑动
        plv.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
        plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());

        plv.getRefreshableView().setDivider(null);
     //   plv.getRefreshableView().setAdapter(new FollowAdapter(getContext()));
    }
}
