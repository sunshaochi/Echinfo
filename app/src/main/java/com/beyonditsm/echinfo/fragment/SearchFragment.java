package com.beyonditsm.echinfo.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.beyonditsm.echinfo.entity.CompanyEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshBase;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.tandong.sa.json.Gson;
import com.tandong.sa.json.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbin on 16/4/7.
 */
public class SearchFragment extends BaseFragment {
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;

    private int rows = 10;
    private int currentPage = 1;
    private Gson gson = new Gson();

    private int position;//0企业，1法人/股东 2、失信

    private EnterPAdapter enterPAdapter;//搜索公司适配器

    private String searchData;

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frg_search, null);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        position = getArguments().getInt("position", 0);

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
                currentPage = 1;
                searchData(searchData, currentPage);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage++;
                searchData(searchData, currentPage);
            }
        });

        switch (position) {
            case 0:
//                searchCompany("阿里",currentPage);
                break;
            case 1:
                plv.getRefreshableView().setAdapter(new LegalAdapter(getContext()));
                break;
            case 2:
                plv.getRefreshableView().setAdapter(new BadCAdaper(getContext()));
                break;
        }
    }

    private List<CompanyEntity> comList = new ArrayList<>();

    /**
     * 查询公司
     *
     * @param company
     * @param currentP
     */
    private void searchCompany(final String company, final int currentP) {
        RequestManager.getCommManager().searchCompany(company, currentP, rows, new CallBack() {
            @Override
            public void onSucess(String result) {
                plv.onPullUpRefreshComplete();
                plv.onPullDownRefreshComplete();
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject data = json.getJSONObject("data");
                    JSONArray rows = data.getJSONArray("rows");
                    List<CompanyEntity> datas = gson.fromJson(rows.toString(), new TypeToken<List<CompanyEntity>>() {
                    }.getType());
                    if (datas.size() == 0) {
                        if (currentP == 1) {
                            MyToastUtils.showShortToast(getContext(), "没有查到任何公司信息");
                        } else {
                            plv.setHasMoreData(false);
                        }
                        return;
                    }
                    if (currentP == 1) {
                        comList.clear();
                    }
                    comList.addAll(datas);
                    if (enterPAdapter == null) {
                        enterPAdapter = new EnterPAdapter(getContext(), comList);
                        plv.getRefreshableView().setAdapter(enterPAdapter);
                    } else {
                        enterPAdapter.notifyDataChange(comList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (receiver == null) {
            receiver = new MyBroadCastReceiver();
            getActivity().registerReceiver(receiver, new IntentFilter(SEARCH_RECEIVER));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            getActivity().unregisterReceiver(receiver);
            receiver = null;
        }
    }

    private MyBroadCastReceiver receiver;
    public final static String SEARCH_RECEIVER = "com.search.receiver";

    /**
     * 接收参数
     */
    class MyBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            searchData = intent.getStringExtra("search");
            currentPage = 1;
            searchData(searchData, currentPage);

        }
    }

    private void searchData(final String searchContent, final int currentP) {
        switch (position) {
            case 0:
                searchCompany(searchContent, currentP);
                break;
        }
    }

}
