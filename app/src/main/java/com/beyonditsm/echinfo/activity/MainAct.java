package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.lidroid.xutils.view.annotation.event.OnClick;


/**
 * 主页面
 */
public class MainAct extends BaseActivity {


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init(Bundle savedInstanceState) {
    }

    @OnClick({R.id.rl_gz, R.id.rl_qy, R.id.rl_sx, R.id.ivMine, R.id.searchView, R.id.ll_qyxq,
            R.id.llEnter, R.id.llLegal, R.id.llBadCre})
    public void onClick(View v) {
        Bundle bundle=null;
        switch (v.getId()) {
            case R.id.rl_gz:
                openActivity(MyFollowAct.class);
                break;
            case R.id.rl_qy:

                break;
            case R.id.llEnter://查企业
                bundle=new Bundle();
                bundle.putInt("search_type",0);
                openActivity(SearchAct.class,bundle);
                break;
            case R.id.llLegal://查法人
                bundle=new Bundle();
                bundle.putInt("search_type",1);
                openActivity(SearchAct.class,bundle);
                break;
            case R.id.llBadCre://查失信
                bundle=new Bundle();
                bundle.putInt("search_type",2);
                openActivity(SearchAct.class,bundle);
                break;
            case R.id.ivMine:
                openActivity(LoginAct.class);
                break;
            case R.id.rl_sx://失信榜单
                openActivity(BadCreditAct.class);
                break;
            case R.id.searchView://搜索
                openActivity(SearchAct.class);
                break;
            case R.id.ll_qyxq:
                openActivity(CompanyxqAct.class);
                break;

        }

    }


}
