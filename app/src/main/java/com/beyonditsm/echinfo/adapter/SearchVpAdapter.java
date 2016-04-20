package com.beyonditsm.echinfo.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.beyonditsm.echinfo.fragment.SearchFragment;

/**
 * Created by wangbin on 16/4/7.
 */
public class SearchVpAdapter extends FragmentPagerAdapter{

    private final String titles[]={"企业","法人/股东","失信"};

    public SearchVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        SearchFragment searchFragment=new SearchFragment();
        searchFragment.setArguments(bundle);
        return searchFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
