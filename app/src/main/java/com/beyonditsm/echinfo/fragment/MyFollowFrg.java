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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.activity.CompanyxqAct;
import com.beyonditsm.echinfo.activity.LoginAct;
import com.beyonditsm.echinfo.adapter.FollowAdapter;
import com.beyonditsm.echinfo.base.BaseFragment;
import com.beyonditsm.echinfo.db.UserDao;
import com.beyonditsm.echinfo.entity.CompanyEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
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
 *我的关注
 * Created by wangbin on 16/4/8.
 */
public class MyFollowFrg extends BaseFragment{
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;
    private FollowAdapter adapter;
    private List<CompanyEntity> list;
    @ViewInject(R.id.rl_txdl)
    private LinearLayout rl_txdl;//提醒登录
    @ViewInject(R.id.tv_dl)
    private TextView tv_dl;

    private int page=1;
    private int rows=10;
    @ViewInject(R.id.loadingView)
    private LoadingView loadView;
    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frg_my_floww,null);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if(UserDao.getUser()==null){
            rl_txdl.setVisibility(View.VISIBLE);
            tv_dl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivity(LoginAct.class);
                }
            });

        }
        if(UserDao.getUser()!=null) {
            findgzPortsMsg(page, rows);
        }else {
            loadView.noContent();
        }
        loadView.setNoContentTxt("暂无结果");
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
                Intent intent = new Intent(getActivity(), CompanyxqAct.class);
                intent.putExtra(CompanyxqAct.ID, datas.get(position).getCompanyId());
                getActivity().startActivity(intent);
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
                                    adapter = new FollowAdapter(getActivity(), datas);
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
                        if (page == 1) {
                            loadView.noContent();
//                            MyToastUtils.showShortToast(getActivity(), "暂无数据");
                        } else {
                            plv.setHasMoreData(false);
                        }
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
            getActivity().registerReceiver(receiver,new IntentFilter(FRG_RECEIVER));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            getActivity().unregisterReceiver(receiver);
        }

    }


    private MyBroadCastReceiver receiver;
    public static final String FRG_RECEIVER = "com.followfrg.receiver";

    private class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            rl_txdl.setVisibility(View.GONE);
            findgzPortsMsg(page,rows);
        }
    }
}
