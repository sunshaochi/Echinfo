package com.beyonditsm.echinfo.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.BadCAdaper;
import com.beyonditsm.echinfo.adapter.EnterPAdapter;
import com.beyonditsm.echinfo.adapter.LegalAdapter;
import com.beyonditsm.echinfo.base.BaseFragment;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshBase;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by wangbin on 16/4/7.
 */
public class SearchFragment extends BaseFragment {
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;

    private  int position;

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frg_search, null);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        position=getArguments().getInt("position",0);

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

        switch (position){
            case 0:
                plv.getRefreshableView().setAdapter(new EnterPAdapter(getContext()));
                break;
            case 1:
                plv.getRefreshableView().setAdapter(new LegalAdapter(getContext()));
                break;
            case 2:
                plv.getRefreshableView().setAdapter(new BadCAdaper(getContext()));
                break;
        }
    }
}
