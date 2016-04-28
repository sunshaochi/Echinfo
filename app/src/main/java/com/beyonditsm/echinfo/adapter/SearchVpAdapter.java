package com.beyonditsm.echinfo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wangbin on 16/4/7.
 */
public class SearchVpAdapter extends FragmentPagerAdapter{

    private final String titles[]={"企业","法人/股东","失信"};
    List<Fragment> fragments;
    public SearchVpAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
//        Bundle bundle=new Bundle();
//        bundle.putInt("position",position);
//        SearchFragment searchFragment=new SearchFragment();
//        searchFragment.setArguments(bundle);
        return fragments.get(position);
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
