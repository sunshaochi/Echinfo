package com.beyonditsm.echinfo.activity;

import android.graphics.Color;
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
import com.beyonditsm.echinfo.fragment.SearchHisFrg;
import com.beyonditsm.echinfo.view.ClearEditText;
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

    @ViewInject(R.id.tv1)
    private TextView tv1;
    @ViewInject(R.id.view1)
    private View view1;
    @ViewInject(R.id.tv2)
    private TextView tv2;
    @ViewInject(R.id.view2)
    private View view2;
    @ViewInject(R.id.tv3)
    private TextView tv3;
    @ViewInject(R.id.view3)
    private View view3;

    @ViewInject(R.id.tv4)
    private TextView tv4;
    @ViewInject(R.id.view4)
    private View view4;
    @ViewInject(R.id.tv5)
    private TextView tv5;
    @ViewInject(R.id.view5)
    private View view5;


    private int SEARCH_TYPE;//0查企业 1、查股东 2、查失信

    private List<Fragment>  frgList=new ArrayList<>();

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

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)){
                    llNoHis.setVisibility(View.GONE);
                    llS.setVisibility(View.VISIBLE);
                }else{
                    llNoHis.setVisibility(View.VISIBLE);
                    llS.setVisibility(View.GONE);
                }

            }
        });
        vp.setAdapter(new SearchVpAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(SEARCH_TYPE);
        setSelection(SEARCH_TYPE);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        countryAdapter=new CountryAdapter(this, getCountrys(getResources().getStringArray(R.array.country)));
        lvCountry.setAdapter(countryAdapter);
        lvCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvCountry.setVisibility(View.GONE);
                tvCountry.setText(countryAdapter.getItem(position).toString());
            }
        });

        frgList.add(new SearchHisFrg());
        frgList.add(new MyFollowFrg());
        vpS.setAdapter(new SVpsAdapter(getSupportFragmentManager(), frgList));
        vpS.setCurrentItem(0);

    }

    /**
     * 点击事件
     *
     * @param v
     */
    @OnClick({R.id.rlEnter, R.id.rlLegal, R.id.rlBadCre, R.id.rlRegion,R.id.rlSearchH,R.id.rlMyFollow})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlEnter:
                setSelection(0);
                vp.setCurrentItem(0);
                break;
            case R.id.rlLegal:
                setSelection(1);
                vp.setCurrentItem(1);
                break;
            case R.id.rlBadCre:
                setSelection(2);
                vp.setCurrentItem(2);
                break;
            case R.id.rlSearchH://搜索历史
                setSelection(3);
                break;
            case R.id.rlMyFollow://我的关注
                setSelection(4);
                break;
            case R.id.rlRegion://搜索范围
                lvCountry.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setSelection(int position) {
        switch (position) {
            case 0:
                tv1.setTextColor(Color.parseColor("#00bbfb"));
                view1.setVisibility(View.VISIBLE);
                tv2.setTextColor(Color.parseColor("#3c3c3c"));
                view2.setVisibility(View.GONE);
                tv3.setTextColor(Color.parseColor("#3c3c3c"));
                view3.setVisibility(View.GONE);
                break;
            case 1:
                tv1.setTextColor(Color.parseColor("#3c3c3c"));
                view1.setVisibility(View.GONE);
                tv2.setTextColor(Color.parseColor("#00bbfb"));
                view2.setVisibility(View.VISIBLE);
                tv3.setTextColor(Color.parseColor("#3c3c3c"));
                view3.setVisibility(View.GONE);
                break;
            case 2:
                tv1.setTextColor(Color.parseColor("#3c3c3c"));
                view1.setVisibility(View.GONE);
                tv2.setTextColor(Color.parseColor("#3c3c3c"));
                view2.setVisibility(View.GONE);
                tv3.setTextColor(Color.parseColor("#00bbfb"));
                view3.setVisibility(View.VISIBLE);
                break;
            case 3:
                tv4.setTextColor(Color.parseColor("#00bbfb"));
                view4.setVisibility(View.VISIBLE);
                tv5.setTextColor(Color.parseColor("#3c3c3c"));
                view5.setVisibility(View.GONE);
                break;
            case 4:
                tv4.setTextColor(Color.parseColor("#3c3c3c"));
                view4.setVisibility(View.GONE);
                tv5.setTextColor(Color.parseColor("#00bbfb"));
                view5.setVisibility(View.VISIBLE);
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
