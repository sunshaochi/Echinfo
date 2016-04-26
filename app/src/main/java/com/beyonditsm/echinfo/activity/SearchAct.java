package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.CountryAdapter;
import com.beyonditsm.echinfo.adapter.SVpsAdapter;
import com.beyonditsm.echinfo.adapter.SearchVpAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.fragment.MyFollowFrg;
import com.beyonditsm.echinfo.fragment.SearchFragment;
import com.beyonditsm.echinfo.fragment.SearchHisFrg;
import com.beyonditsm.echinfo.view.ClearEditText;
import com.beyonditsm.echinfo.view.PagerSlidingTabStrip;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索
 * Created by wangbin on 16/4/6.
 */
public class SearchAct extends BaseActivity {
    @ViewInject(R.id.ceSearch)
    private ClearEditText ceSearch;//输入框
    @ViewInject(R.id.vp)
    private ViewPager vp;//下面查询
    @ViewInject(R.id.lvCountry)
    private ListView lvCountry;//城市列表
    @ViewInject(R.id.tvCountry)
    private TextView tvCountry;
    @ViewInject(R.id.llNoHis)
    private LinearLayout llNoHis;//无历史记录
    @ViewInject(R.id.llS)
    private LinearLayout llS;//搜索出结果
    private CountryAdapter countryAdapter;

    @ViewInject(R.id.vpS)
    private ViewPager vpS;//搜索记录，我的关注

    @ViewInject(R.id.tabsSearch)
    private PagerSlidingTabStrip tabsSearch;
    @ViewInject(R.id.tabS)
    private PagerSlidingTabStrip tabS;//企业、法人、失信



    private int SEARCH_TYPE;//0查企业 1、查股东 2、查失信

    private List<Fragment>  frgList=new ArrayList<>();

    private boolean isShowCountry;

    @Override
    public void setLayout() {
        setContentView(R.layout.act_search);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        SEARCH_TYPE=getIntent().getIntExtra("search_type",0);
        ceSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s.toString())){
                    llNoHis.setVisibility(View.VISIBLE);
                    llS.setVisibility(View.INVISIBLE);
                }else if(s.length()>=2){
                    llNoHis.setVisibility(View.GONE);
                    llS.setVisibility(View.VISIBLE);
                    Intent intent=new Intent(SearchFragment.SEARCH_RECEIVER);
                    intent.putExtra("search",s.toString());
                    intent.putExtra("address",tvCountry.getText().toString().trim());
                    sendBroadcast(intent);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        //企业、法人、失信
        vp.setAdapter(new SearchVpAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(SEARCH_TYPE);
        tabS.setViewPager(vp);


        //选择城市
        countryAdapter=new CountryAdapter(this, getCountrys(getResources().getStringArray(R.array.country)));
        lvCountry.setAdapter(countryAdapter);
        lvCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvCountry.setVisibility(View.GONE);
                tvCountry.setText(countryAdapter.getItem(position).toString());
                Intent intent=new Intent(SearchFragment.SEARCH_RECEIVER);
                intent.putExtra("search", ceSearch.getText().toString());
                intent.putExtra("address", tvCountry.getText().toString().trim());
                sendBroadcast(intent);
            }
        });

        frgList.add(new SearchHisFrg());
        frgList.add(new MyFollowFrg());
        vpS.setAdapter(new SVpsAdapter(getSupportFragmentManager(), frgList));
        vpS.setCurrentItem(0);
        tabsSearch.setViewPager(vpS);

    }

    /**
     * 点击事件
     *
     * @param v
     */
    @OnClick({ R.id.rlRegion})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlRegion://搜索范围
                if(isShowCountry){
                    lvCountry.setVisibility(View.GONE);
                    isShowCountry=false;
                }else{
                    lvCountry.setVisibility(View.VISIBLE);
                    isShowCountry=true;
                }

                break;
        }
    }


    /**
     * 获取che
     * @paraountrys
     * @return
     */
    private List<String> getCountrys(String[] countrys){
        List<String> counList=new ArrayList<>();
        for(int i=0;i<countrys.length;i++){
            counList.add(countrys[i]);
        }
        return counList;
    }
}
