package com.beyonditsm.echinfo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.FlipAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.db.UserDao;
import com.beyonditsm.echinfo.entity.CompanyEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.GeneralUtils;
import com.beyonditsm.echinfo.util.MyLogUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.beyonditsm.echinfo.view.flip.FlipViewController;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tandong.sa.json.Gson;
import com.tandong.sa.json.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 主页面
 */
public class MainAct extends BaseActivity {
    @ViewInject(R.id.rl_gz)
    private RelativeLayout rlgz;//我的关注
    @ViewInject(R.id.flFollow)
    private FrameLayout flFollow;//我的关注
    @ViewInject(R.id.top)
    private View top;//我的关注线
    @ViewInject(R.id.down)
    private View down;//我的关注线
    @ViewInject(R.id.fcView)
    private FlipViewController fcView;
    private int mCurrPos;
    String name = null;
    SharedPreferences sp;

    private GeneralUtils generalUtils;
    public static final String ISLOADING = "com.Main.isloading";
    private boolean isStart = false;
    private boolean isUploading = false;
    /**
     * 按两次退出键时间小于2秒退出
     */
    private final static long WAITTIME = 2000;
    private long touchTime = 0;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        name = sp.getString("screen_name", "");
        generalUtils = new GeneralUtils();

        titleList.add("1");
        titleList.add("2");
        titleList.add("3");
        titleList.add("4");

        initHotComlist();
        initMyFollow();//初始化我的关注
        initBadCre();//初始化失信榜单

        fcView.setAdapter(new FlipAdapter(this));
//        generalUtils.toVersion(MainAct.this, EchinfoUtils.getAppVer(MainAct.this), 0);


    }

    /**
     * 初始化我的关注
     */
    private void initMyFollow(){
        if (UserDao.getUser() != null) {
            findgzPortsMsg(1, 5);
        } else {
            rlgz.setVisibility(View.GONE);
            flFollow.setVisibility(View.GONE);
            top.setVisibility(View.GONE);
            down.setVisibility(View.GONE);
        }

    }
    /**
     * 先获取热门企业列表
     */
    private void initHotComlist() {
        RequestManager.getCommManager().hotEnterprise(new CallBack() {
            @Override
            public void onSucess(String result) {
                Gson gson = new Gson();
                JSONObject json = null;
                try {
                    json = new JSONObject(result);
                    JSONObject data = json.getJSONObject("data");
                    JSONArray rows = data.getJSONArray("rows");
                    if (rows.length() > 0) {
                        list = gson.fromJson(rows.toString(), new TypeToken<List<CompanyEntity>>() {
                        }.getType());
                        if (list.size() > 0) {
                            datas.addAll(list);
                        }
                        initHotCom();//初始化热门
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                initHotCom();

            }

            @Override
            public void onError(String error) {

            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        fcView.onPause();
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= WAITTIME) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            touchTime = currentTime;
        } else {
            finish();
        }
    }

    @OnClick({R.id.rl_gz, R.id.rl_qy, R.id.rl_sx, R.id.ivMine, R.id.searchView,
            R.id.llEnter, R.id.llLegal, R.id.llBadCre, R.id.ivMsg})
    public void onClick(View v) {
        Bundle bundle = null;
        switch (v.getId()) {
            case R.id.rl_gz://我的关注
                if (UserDao.getUser() != null) {
                    openActivity(MyFollowAct.class);
                } else {
                    openActivity(LoginAct.class);
                }
                break;
            case R.id.rl_qy://热门企业
                openActivity(ReqyAct.class);
                break;
            case R.id.llEnter://查企业
                bundle = new Bundle();
                bundle.putInt("search_type", 0);
                openActivity(SearchAct.class, bundle);
                break;
            case R.id.llLegal://查法人
                bundle = new Bundle();
                bundle.putInt("search_type", 1);
                openActivity(SearchAct.class, bundle);
                break;
            case R.id.llBadCre://查失信
                bundle = new Bundle();
                bundle.putInt("search_type", 2);
                openActivity(SearchAct.class, bundle);
                break;
            case R.id.ivMine:
                if (UserDao.getUser() != null || !TextUtils.isEmpty(name)) {
                    MyLogUtils.degug(name);
                    openActivity(MineAct.class);
                } else {
                    openActivity(LoginAct.class);
                }
                break;
            case R.id.rl_sx://失信榜单
                openActivity(BadCreditAct.class);
                break;
            case R.id.searchView://搜索
                openActivity(SearchAct.class);
                break;
            case R.id.ivMsg:
                openActivity(MsgAct.class);
                break;
        }

    }

    private LinearLayout llFollow;
    private ViewFlipper followVf;
    private List<String> titleList = new ArrayList<>();

    //初始化关注
    private void initFlipView() {
        FrameLayout main_notice = (FrameLayout) findViewById(R.id.flFollow);
        llFollow = (LinearLayout) getLayoutInflater().inflate(
                R.layout.layout_main_flip, null);
        followVf = ((ViewFlipper) this.llFollow
                .findViewById(R.id.homepage_vf));
        main_notice.addView(llFollow);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        moveNext();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 8000);
    }

    private void moveNext() {
        setView(this.mCurrPos, this.mCurrPos + 1);
        this.followVf.setInAnimation(this, R.anim.in_bottomtop);
        this.followVf.setOutAnimation(this, R.anim.out_bottomtop);
        this.followVf.showNext();
    }

    private void setView(int curr, int next) {
        View noticeView = getLayoutInflater().inflate(R.layout.layout_follow,
                null);
        TextView tvComName = (TextView) noticeView.findViewById(R.id.tvComName);
        TextView tvName = (TextView) noticeView.findViewById(R.id.tvName);
        TextView tvState = (TextView) noticeView.findViewById(R.id.tvState);

        if ((curr < next) && (next > (list.size() - 1))) {
            next = 0;
        } else if ((curr > next) && (next < 0)) {
            next = list.size() - 1;
        }
        tvComName.setText(list.get(next).getCompanyName());
        tvName.setText("公司法人：" + list.get(next).getRepPersion());
        tvState.setText(list.get(next).getRecordStatus());
        final int finalNext = next;
        noticeView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(MainAct.this, CompanyxqAct.class);
                intent.putExtra(CompanyxqAct.ID, list.get(finalNext).getCompanyId());
                startActivity(intent);

            }
        });
        if (followVf.getChildCount() > 1) {
            followVf.removeViewAt(0);
        }
        followVf.addView(noticeView, followVf.getChildCount());
        mCurrPos = next;
    }

//    private List<CompanyEntity> list=new ArrayList<>();

    /**
     * 我的关注
     *
     * @param page
     * @param rows
     */
    public void findgzPortsMsg(final int page, int rows) {
        RequestManager.getCommManager().findgzPortsMsg(page, rows, new CallBack() {
            @Override
            public void onSucess(String result) {
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
                        rlgz.setVisibility(View.VISIBLE);
                        flFollow.setVisibility(View.VISIBLE);
                        top.setVisibility(View.VISIBLE);
                        down.setVisibility(View.VISIBLE);
                        initFlipView();
                    } else {
                        rlgz.setVisibility(View.GONE);
                        flFollow.setVisibility(View.GONE);
                        top.setVisibility(View.GONE);
                        down.setVisibility(View.GONE);
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

    private LinearLayout llHot;
    private ViewFlipper followHot;

    private List<CompanyEntity> list;
    private List<CompanyEntity> datas = new ArrayList<>();

    //初始化热门企业
    private void initHotCom() {
        FrameLayout main_notice = (FrameLayout) findViewById(R.id.flHotCom);
        llHot = (LinearLayout) getLayoutInflater().inflate(
                R.layout.layout_main_flip, null);
        followHot = ((ViewFlipper) this.llHot
                .findViewById(R.id.homepage_vf));
        main_notice.addView(llHot);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        moveHotNext();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 8000);
    }


    private void moveHotNext() {
        setHotView(this.mCurrPos, this.mCurrPos + 1);
        this.followHot.setInAnimation(this, R.anim.in_bottomtop);
        this.followHot.setOutAnimation(this, R.anim.out_bottomtop);
        this.followHot.showNext();
    }

    private void setHotView(final int curr, int next) {

        View noticeView = getLayoutInflater().inflate(R.layout.layou_main_hot,
                null);

        TextView tvComName = (TextView) noticeView.findViewById(R.id.tv_companyname);
        TextView tvrensu = (TextView) noticeView.findViewById(R.id.tv_rs);
        RatingBar rb_hot = (RatingBar) noticeView.findViewById(R.id.rb_hot);

        tvComName.setText(datas.get(curr).getCompanyName());
        tvrensu.setText(datas.get(curr).getFocus());
        rb_hot.setRating(Float.parseFloat(datas.get(curr).getLevel()));


        if ((curr < next) && (next > (datas.size() - 1))) {
            next = 0;
        } else if ((curr > next) && (next < 0)) {
            next = datas.size() - 1;
        }
        noticeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainAct.this, CompanyxqAct.class);
                intent.putExtra(CompanyxqAct.ID, datas.get(curr).getId());
                startActivity(intent);
            }
        });
        if (followHot.getChildCount() > 1) {
            followHot.removeViewAt(0);
        }
        followHot.addView(noticeView, followHot.getChildCount());
        mCurrPos = next;
    }


    private LinearLayout llBadCre;
    private ViewFlipper followBad;

    //初始化失信榜单
    private void initBadCre() {
        FrameLayout main_notice = (FrameLayout) findViewById(R.id.flBadCredit);
        llBadCre = (LinearLayout) getLayoutInflater().inflate(
                R.layout.layout_main_flip, null);
        followBad = ((ViewFlipper) this.llBadCre
                .findViewById(R.id.homepage_vf));
        main_notice.addView(llBadCre);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        moveBadCreNext();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 5000);
    }


    private void moveBadCreNext() {
        setBadCretView(this.mCurrPos, this.mCurrPos + 1);
        this.followBad.setInAnimation(this, R.anim.in_bottomtop);
        this.followBad.setOutAnimation(this, R.anim.out_bottomtop);
        this.followBad.showNext();
    }

    private void setBadCretView(int curr, int next) {
        View noticeView = getLayoutInflater().inflate(R.layout.layout_main_badcre,
                null);
        if ((curr < next) && (next > (titleList.size() - 1))) {
            next = 0;
        } else if ((curr > next) && (next < 0)) {
            next = titleList.size() - 1;
        }
        noticeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                openActivity(DishonestylistAct.class);
//                openActivity(DishonestyDetailAct.class);
            }
        });
        if (followBad.getChildCount() > 1) {
            followBad.removeViewAt(0);
        }
        followBad.addView(noticeView, followBad.getChildCount());
        mCurrPos = next;
    }

    @Override
    protected void onResume() {
        super.onResume();
        name = sp.getString("screen_name", "");
        fcView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (receiver == null) {
            receiver = new MyBroadCastReceiver();
            registerReceiver(receiver,new IntentFilter(MAIN_RECEIVER));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }

    }


    private MyBroadCastReceiver receiver;
    public static final String MAIN_RECEIVER = "com.main.receiver";

    private class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            initMyFollow();
        }
    }

//    private MyBroadCastUnlogin broadCastUnlogin;
//    public static final String UNLOGIN = "unLogin";
//
//    private class MyBroadCastUnlogin extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (UserDao.getUser() != null) {
//                findgzPortsMsg(1, 5);
//            } else {
//                rlgz.setVisibility(View.GONE);
//                flFollow.setVisibility(View.GONE);
//                top.setVisibility(View.GONE);
//                down.setVisibility(View.GONE);
//            }
//        }
//    }
}
