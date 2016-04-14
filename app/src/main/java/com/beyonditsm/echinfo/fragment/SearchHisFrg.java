package com.beyonditsm.echinfo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseFragment;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 搜索历史frgment
 * Created by wangbin on 16/4/8.
 */
public class SearchHisFrg extends BaseFragment{
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;
    @ViewInject(R.id.llNoHis)
    private LinearLayout llNoHis;
    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frg_search,null);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        llNoHis.setVisibility(View.VISIBLE);
    }
}
