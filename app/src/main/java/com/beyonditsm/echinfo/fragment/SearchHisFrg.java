package com.beyonditsm.echinfo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseFragment;

/**
 * 搜索历史frgment
 * Created by wangbin on 16/4/8.
 */
public class SearchHisFrg extends BaseFragment{

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frg_search,null);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
