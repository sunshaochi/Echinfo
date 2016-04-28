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
import android.widget.AdapterView;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.activity.CompanyxqAct;
import com.beyonditsm.echinfo.activity.DisinfodetailAct;
import com.beyonditsm.echinfo.adapter.BadCAdaper;
import com.beyonditsm.echinfo.adapter.EnterPAdapter;
import com.beyonditsm.echinfo.adapter.LegalAdapter;
import com.beyonditsm.echinfo.base.BaseFragment;
import com.beyonditsm.echinfo.db.SearchDao;
import com.beyonditsm.echinfo.entity.BadCreditEntity;
import com.beyonditsm.echinfo.entity.CompanyEntity;
import com.beyonditsm.echinfo.entity.SearchEntity;
import com.beyonditsm.echinfo.entity.StockMsg;
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
    private String address;
    @ViewInject(R.id.loadingView)
    private LoadingView loadView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frg_my_floww, null);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        position = getArguments().getInt("position", 0);
        loadView.setNoContentTxt("暂无结果");
        if (enterPAdapter == null) {
            enterPAdapter = new EnterPAdapter(getContext());
            plv.getRefreshableView().setAdapter(enterPAdapter);
        }
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
                loadView.loading();
                searchData(searchData, address, currentPage);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage++;
                loadView.loading();
                searchData(searchData, address, currentPage);
            }
        });

        loadView.setOnRetryListener(new LoadingView.OnRetryListener() {
            @Override
            public void OnRetry() {
                searchData(searchData, address, currentPage);
            }
        });
        plv.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent=new Intent(getActivity(), CompanyxqAct.class);
                SearchEntity se=new SearchEntity();
                switch (position){
                    case 0:
                        se.setType(0);
                        intent.putExtra(CompanyxqAct.ID,comList.get(i).getId());
                        break;
                    case 1:
                        se.setType(1);
                        intent.putExtra(CompanyxqAct.ID,stockMsgList.get(i).getId());
                        break;
                    case 2:
                        se.setType(2);
                        intent=new Intent(getActivity(), DisinfodetailAct.class);
                        intent.putExtra("entity",badCreditEntityList.get(i));
//                        intent.putExtra(CompanyxqAct.ID,badCreditEntityList.get(i).get);
                        break;
                }
                se.setContent(searchData);
                se.setCountry("全国");
                se.setTime(EchinfoUtils.getCurrentTime());
                SearchDao.addSearch(se);

                getActivity().startActivity(intent);
            }
        });
        switch (position) {
            case 0:
//                searchCompany("阿里",currentPage);
                break;
            case 1:
//                plv.getRefreshableView().setAdapter(new LegalAdapter(getContext()));
                break;
            case 2:
//                plv.getRefreshableView().setAdapter(new BadCAdaper(getContext()));
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
    private void searchCompany(final String company,String address, final int currentP) {
        RequestManager.getCommManager().searchCompany(company,address, currentP, rows, new CallBack() {
            @Override
            public void onSucess(String result) {
                loadView.loadComplete();
                plv.onPullUpRefreshComplete();
                plv.onPullDownRefreshComplete();
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject data = json.getJSONObject("data");
                    JSONArray rows = data.getJSONArray("rows");
                    List<CompanyEntity> datas = gson.fromJson(rows.toString(), new TypeToken<List<CompanyEntity>>() {}.getType());
                    if (currentP == 1) {
                        comList.clear();
                    }
                    comList.addAll(datas);
//                    if (enterPAdapter == null) {
//                        enterPAdapter = new EnterPAdapter(getContext(), comList);
//                        plv.getRefreshableView().setAdapter(enterPAdapter);
//                    } else {
                    enterPAdapter.notifyDataChange(comList);
//                    }
                    if (datas.size() == 0) {
                        if(currentP==1) {
                            loadView.noContent();
                        }else {
                            plv.setHasMoreData(false);
                        }
                        return;
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

    private List<StockMsg> stockMsgList;
    /**
     * 查法人，查股东（公司）
     * @param name
     */
    private void findStockMsgByCompanyName(String name,String address){
        MyLogUtils.degug("name:" + name);
        RequestManager.getCommManager().findStockMsgByCompanyName(name,address, new CallBack() {
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
                        stockMsgList = gson.fromJson(rows.toString(),
                                new TypeToken<List<StockMsg>>() {
                                }.getType());
                        plv.getRefreshableView().setAdapter(new LegalAdapter(getActivity(), stockMsgList));

                    } else {
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

    private List<BadCreditEntity> badCreditEntityList;
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
                        plv.getRefreshableView().setAdapter(new BadCAdaper(getActivity(),badCreditEntityList));

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

    public void sendRequest(String search,String address){
        currentPage = 1;
        searchData(search,address, currentPage);
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
            address=intent.getStringExtra("address");
            currentPage = 1;
            searchData(searchData,address, currentPage);

        }
    }

    private void searchData(final String searchContent,String address, final int currentP) {
        switch (position) {
            case 0:
                searchCompany(searchContent,address, currentP);
                break;
            case 1:
                findStockMsgByCompanyName(searchContent,address);
                break;
            case 2:
                findCourtitemList(searchContent,address);
                break;
        }
    }

}
