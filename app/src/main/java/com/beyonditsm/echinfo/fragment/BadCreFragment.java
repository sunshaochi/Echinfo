package com.beyonditsm.echinfo.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.activity.DisinfodetailAct;
import com.beyonditsm.echinfo.adapter.BadCAdaper;
import com.beyonditsm.echinfo.base.BaseFragment;
import com.beyonditsm.echinfo.db.SearchDao;
import com.beyonditsm.echinfo.entity.BadCreditEntity;
import com.beyonditsm.echinfo.entity.SearchEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyLogUtils;
import com.beyonditsm.echinfo.view.LoadingView;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshBase;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.tandong.sa.json.Gson;
import com.tandong.sa.json.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 失信fragment
 * Created by wangbin on 16/4/28.
 */
public class BadCreFragment extends BaseFragment{
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;
    private Gson gson = new Gson();
    private int rows = 10;
    private int currentPage = 1;

    private String searchData;
    private String address;
    @ViewInject(R.id.loadingView)
    private LoadingView loadView;
    private List<BadCreditEntity> badCreditEntityList;
    private BadCAdaper badCAdaper;
    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frg_my_floww, null);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        loadView.setNoContentTxt("暂无结果");
        plv.setPullRefreshEnabled(false);//下拉刷新
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
                findCourtitemList(searchData, address);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage++;
                findCourtitemList(searchData, address);
            }
        });

        loadView.setOnRetryListener(new LoadingView.OnRetryListener() {
            @Override
            public void OnRetry() {
                findCourtitemList(searchData, address);
            }
        });
        plv.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                SearchEntity se = new SearchEntity();
                se.setType(2);
                se.setContent(searchData);
                se.setCountry(address);
                se.setTime(EchinfoUtils.getCurrentTime());
                SearchDao.addSearch(se);
                getActivity().sendBroadcast(new Intent(SearchHisFrg.SEARCH_HISTORY));
               Intent intent = new Intent(getActivity(), DisinfodetailAct.class);
                intent.putExtra("entity", badCreditEntityList.get(i));
                getActivity().startActivity(intent);
            }
        });

//        if(!TextUtils.isEmpty(SearchAct.searchContent)&&!TextUtils.isEmpty(SearchAct.searchAddress)){
//            currentPage=1;
//            searchData=SearchAct.searchContent;
//            address=SearchAct.searchAddress;
//            findCourtitemList(SearchAct.searchContent, SearchAct.searchAddress);
//        }
    }

    /**
     * 失信列表(公司)
     * @param iname
     */
    private void findCourtitemList(String iname,String address){
        MyLogUtils.degug(iname);
        RequestManager.getCommManager().findCourtitemList(iname,address, new CallBack() {
            @Override
            public void onSucess(String result) {
                loadView.loadComplete();
                plv.onPullUpRefreshComplete();
                plv.onPullDownRefreshComplete();
                Gson gson = new Gson();
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject data = json.getJSONObject("data");
                    JSONArray rows = data.getJSONArray("rows");
                    if (rows.length() > 0) {
                        badCreditEntityList = gson.fromJson(rows.toString(),
                                new TypeToken<List<BadCreditEntity>>() {
                                }.getType());
//                        badCAdaper=new BadCAdaper(getContext(),badCreditEntityList);
//                        plv.getRefreshableView().setAdapter(badCAdaper);
                        if(badCAdaper==null){
                            badCAdaper=new BadCAdaper(getContext(),badCreditEntityList);
                            plv.getRefreshableView().setAdapter(badCAdaper);
                        }else{
                            badCAdaper.notifyDataChanged(badCreditEntityList);
                        }

                    }else {
                        loadView.noContent();
//                        MyToastUtils.showShortToast(getContext(), "没有查到任何公司信息");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                loadView.loadError();
                plv.onPullUpRefreshComplete();
                plv.onPullDownRefreshComplete();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (receiver == null) {
            receiver = new MyBroadCastReceiver();
            getActivity().registerReceiver(receiver, new IntentFilter(BADCRE_RECEIVER));
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
    public final static String BADCRE_RECEIVER = "com.badcrre.receiver";

    /**
     * 接收参数
     */
    class MyBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            searchData = intent.getStringExtra("search");
            address=intent.getStringExtra("address");
            currentPage = 1;
            if(!TextUtils.isEmpty(searchData)&&!TextUtils.isEmpty(address))
            findCourtitemList(searchData, address);

        }
    }
}
