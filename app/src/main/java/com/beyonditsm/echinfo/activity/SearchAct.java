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
import com.beyonditsm.echinfo.fragment.BadCreFragment;
import com.beyonditsm.echinfo.fragment.EnterFragment;
import com.beyonditsm.echinfo.fragment.LegalFragment;
import com.beyonditsm.echinfo.fragment.MyFollowFrg;
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

    private EnterFragment searchFragment1=new EnterFragment();
    private LegalFragment searchFragment2=new LegalFragment();
    private BadCreFragment searchFragment3=new BadCreFragment();
    List<Fragment> fragments=new ArrayList<>() ;


    boolean[] fragmentsUpdateFlag = { false, false, false };
    public static String searchContent,searchAddress;
    private int currentPosition;

    private boolean isShowCountry;

    private SearchVpAdapter adapter;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_search);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        SEARCH_TYPE=getIntent().getIntExtra("search_type",0);

        fragments.add(searchFragment1);
        fragments.add(searchFragment2);
        fragments.add(searchFragment3);
        ceSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s.toString())){
                    llNoHis.setVisibility(View.VISIBLE);
                    llS.setVisibility(View.INVISIBLE);
                }else if(s.length()>=2){
                    llNoHis.setVisibility(View.GONE);
                    llS.setVisibility(View.VISIBLE);

                    searchContent=s.toString();
                    searchAddress=tvCountry.getText().toString().trim();

                    sendBroadCast(currentPosition);
//                    Intent intent=new Intent(SearchFragment.SEARCH_RECEIVER);
//                    intent.putExtra("search",s.toString());
//                    intent.putExtra("address",tvCountry.getText().toString().trim());
//                    sendBroadcast(intent);
                }

            }
        });
        //企业、法人、失信
        adapter=new SearchVpAdapter(getSupportFragmentManager(),fragments);
        vp.setAdapter(adapter);
        vp.setCurrentItem(SEARCH_TYPE);
        tabS.setViewPager(vp);
        tabS.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition=position;
                sendBroadCast(currentPosition);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        //选择城市
        countryAdapter=new CountryAdapter(this, getCountrys(getResources().getStringArray(R.array.country)));
        lvCountry.setAdapter(countryAdapter);
        lvCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvCountry.setVisibility(View.GONE);
                tvCountry.setText(countryAdapter.getItem(position).toString());

                searchAddress=tvCountry.getText().toString().trim();
                sendBroadCast(currentPosition);
//                Intent intent=new Intent(SearchFragment.SEARCH_RECEIVER);
//                intent.putExtra("search", ceSearch.getText().toString());
//                intent.putExtra("address", tvCountry.getText().toString().trim());
//                sendBroadcast(intent);
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

    private void sendBroadCast(int position){
        Intent intent;
         switch (position){
             case 0:
                 intent=new Intent(EnterFragment.Enter_RECEIVER);
                 intent.putExtra("search",searchContent);
                 intent.putExtra("address",searchAddress);
                 sendBroadcast(intent);
                 break;
             case 1:
                 intent=new Intent(LegalFragment.LEGAL_RECEIVER);
                 intent.putExtra("search",searchContent);
                 intent.putExtra("address",searchAddress);
                 sendBroadcast(intent);
                 break;
             case 2:
                 intent=new Intent(BadCreFragment.BADCRE_RECEIVER);
                 intent.putExtra("search",searchContent);
                 intent.putExtra("address",searchAddress);
                 sendBroadcast(intent);
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
    public void setText(String string,int type){
        ceSearch.setText(string);
        ceSearch.setSelection(string.length());
        SEARCH_TYPE=type;
        vp.setCurrentItem(SEARCH_TYPE);
    }


//    Handler mainHandler = new Handler() {
//
//        /*
//         * （非 Javadoc）
//         *
//         * @see android.os.Handler#handleMessage(android.os.Message)
//         */
//        @Override
//        public void handleMessage(Message msg) {
//            // TODO 自动生成的方法存根
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0:
//                    fragments[msg.what]=searchFragment1;
//                    break;
//                case 1:
//                    fragments[msg.what]=searchFragment2;
//                    break;
//                case 2:
//                    fragments[msg.what]=searchFragment3;
//                    break;
//            }
////            fragments[msg.what]=saveFragments[msg.what];
//            fragmentsUpdateFlag[msg.what]=true;
//            adapter.notifyDataSetChanged();
//        }
//    };


//    class SearchVpAdapter extends FragmentPagerAdapter{
//        FragmentManager fm;
//        private final String titles[]={"企业","法人/股东","失信"};
//        SearchVpAdapter(FragmentManager fm) {
//            super(fm);
//            this.fm = fm;
//        }
//
//        @Override
//        public int getCount() {
//            return fragments.length;
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            Fragment fragment = fragments[position % fragments.length];
////            Log.i(Common.TAG, "getItem:position=" + position + ",fragment:"
////                    + fragment.getClass().getName() + ",fragment.tag="
////                    + fragment.getTag());
//            return fragment;
//        }
//
//        @Override
//        public int getItemPosition(Object object) {
//            return POSITION_NONE;
//        }
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return titles[position];
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            //得到缓存的fragment
//            Fragment fragment = (Fragment) super.instantiateItem(container,
//                    position);
//            //得到tag，这点很重要
//            String fragmentTag = fragment.getTag();
//
//            if (fragmentsUpdateFlag[position % fragmentsUpdateFlag.length]) {
//                //如果这个fragment需要更新
//
//                FragmentTransaction ft = fm.beginTransaction();
//                //移除旧的fragment
//                ft.remove(fragment);
//                //换成新的fragment
//                fragment = fragments[position % fragments.length];
//                //添加新fragment时必须用前面获得的tag，这点很重要
//                ft.add(container.getId(), fragment, fragmentTag);
//                ft.attach(fragment);
//                ft.commit();
//
//                //复位更新标志
//                fragmentsUpdateFlag[position % fragmentsUpdateFlag.length] = false;
//            }
//
//            return fragment;
//        }
//    }

}
