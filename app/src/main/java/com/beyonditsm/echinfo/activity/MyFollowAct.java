package com.beyonditsm.echinfo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.FollowAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.CompanyEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
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
 * 我的关注
 * Created by wangbin on 16/4/5.
 */
public class MyFollowAct extends BaseActivity {
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;

    private List<CompanyEntity> list;
    private FollowAdapter adapter;
    @ViewInject(R.id.loadingView)
    private LoadingView loadView;
    private int page=1;
    private int rows=10;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_my_follow);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("我的关注");
        loadView.setNoContentTxt("暂无结果");
        plv.setPullRefreshEnabled(true);//下拉刷新
        plv.setScrollLoadEnabled(true);//滑动加载
        plv.setPullLoadEnabled(false);//上拉刷新
        plv.setHasMoreData(true);//是否有更多数据
        plv.getRefreshableView().setVerticalScrollBarEnabled(false);//设置右侧滑动
        plv.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
        plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());
        findgzPortsMsg(page, rows);
        plv.getRefreshableView().setDivider(null);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());
                page = 1;
                loadView.loading();
                findgzPortsMsg(page, rows);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                loadView.loading();
                findgzPortsMsg(page, rows);
            }
        });



        plv.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyFollowAct.this, CompanyxqAct.class);
                intent.putExtra(CompanyxqAct.ID, datas.get(position).getCompanyId());
                startActivity(intent);
            }
        });
        loadView.setOnRetryListener(new LoadingView.OnRetryListener() {
            @Override
            public void OnRetry() {
                findgzPortsMsg(page, rows);
            }
        });
    }

    private List<CompanyEntity> datas=new ArrayList<>();
    /**
     * 我的关注
     * @param page
     * @param rows
     */
    public void findgzPortsMsg(final int page,int rows){
        RequestManager.getCommManager().findgzPortsMsg(page, rows, new CallBack() {
            @Override
            public void onSucess(String result) {
                loadView.loadComplete();
                plv.onPullUpRefreshComplete();
                plv.onPullDownRefreshComplete();
                Gson gson = new Gson();
                JSONObject json = null;
                try {
                    json = new JSONObject(result);
                    JSONObject data = json.getJSONObject("data");
                    JSONArray rows = data.getJSONArray("rows");
                    if (rows.length() > 0) {
                        list = gson.fromJson(rows.toString(),
                                new TypeToken<List<CompanyEntity>>() {
                                }.getType());
                        if (list.size() > 0) {
                            if (page == 1) {
                                datas.clear();
                            }
                            datas.addAll(list);
                            if (datas != null && datas.size() > 0) {
                                if (adapter == null) {
                                    adapter = new FollowAdapter(MyFollowAct.this, datas,0);
                                    plv.getRefreshableView().setAdapter(adapter);
                                } else {
                                    adapter.notify(datas);
                                }
                            } else {

                            }
                        } else {
                            if(page==1) {
                                loadView.noContent();
                            }else {
                                plv.setHasMoreData(false);
                            }
                        }

                    } else {
                        if(page==1) {
                            loadView.noContent();
                            MyToastUtils.showShortToast(MyFollowAct.this, "暂无数据");
                        }else {
                            plv.setHasMoreData(false);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                plv.onPullUpRefreshComplete();
                plv.onPullDownRefreshComplete();
                loadView.loadError();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (receiverfollow == null) {
            receiverfollow = new MyBroadCastReceiverHot();
            registerReceiver(receiverfollow,new IntentFilter(MAIN_RECEIVER_FOLLOW));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiverfollow != null) {
            unregisterReceiver(receiverfollow);
        }

    }
    private MyBroadCastReceiverHot receiverfollow;
    public static final String MAIN_RECEIVER_FOLLOW = "com.follow.receiver";

    private class MyBroadCastReceiverHot extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            findgzPortsMsg(page,rows);
        }
    }

}
