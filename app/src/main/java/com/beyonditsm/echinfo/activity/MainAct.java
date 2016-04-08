package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 主页面
 */
public class MainAct extends BaseActivity {
    @ViewInject(R.id.flFollow)
    private FrameLayout flFollow;//我的关注
    private int mCurrPos;
    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        titleList.add("1");
        titleList.add("2");
        titleList.add("3");
        titleList.add("4");
        initFlipView();//我的关注
        initHotCom();//初始化热门
        initBadCre();//初始化失信榜单
    }

    @OnClick({R.id.rl_gz, R.id.rl_qy, R.id.rl_sx, R.id.ivMine, R.id.searchView,
            R.id.llEnter, R.id.llLegal, R.id.llBadCre})
    public void onClick(View v) {
        Bundle bundle=null;
        switch (v.getId()) {
            case R.id.rl_gz://我的关注
                openActivity(MyFollowAct.class);
                break;
            case R.id.rl_qy:
                openActivity(ReqyAct.class);

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

        }

    }

    private LinearLayout llFollow;
    private ViewFlipper followVf;
    private List<String> titleList=new ArrayList<>();
    //初始化关注
    private void initFlipView() {
        FrameLayout main_notice = (FrameLayout)findViewById(R.id.flFollow);
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
        timer.schedule(task, 0, 4000);
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

        if ((curr < next) && (next > (titleList.size() - 1))) {
            next = 0;
        } else if ((curr > next) && (next < 0)) {
            next = titleList.size() - 1;
        }
        noticeView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                openActivity(CompanyxqAct.class);
            }
        });
        if (followVf.getChildCount() > 1) {
            followVf.removeViewAt(0);
        }
        followVf.addView(noticeView, followVf.getChildCount());
        mCurrPos = next;
    }


    private LinearLayout llHot;
    private ViewFlipper followHot;
    //初始化热门企业
    private void initHotCom() {
        FrameLayout main_notice = (FrameLayout)findViewById(R.id.flHotCom);
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
        timer.schedule(task, 0, 4000);
    }


    private void moveHotNext() {
        setHotView(this.mCurrPos, this.mCurrPos + 1);
        this.followHot.setInAnimation(this, R.anim.in_bottomtop);
        this.followHot.setOutAnimation(this, R.anim.out_bottomtop);
        this.followHot.showNext();
    }

    private void setHotView(int curr, int next) {
        View noticeView = getLayoutInflater().inflate(R.layout.layou_main_hot,
                null);
        if ((curr < next) && (next > (titleList.size() - 1))) {
            next = 0;
        } else if ((curr > next) && (next < 0)) {
            next = titleList.size() - 1;
        }
        noticeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                openActivity(CompanyxqAct.class);
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
        FrameLayout main_notice = (FrameLayout)findViewById(R.id.flBadCredit);
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
        timer.schedule(task, 0, 4000);
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
                openActivity(DishonestyDetailAct.class);
            }
        });
        if (followBad.getChildCount() > 1) {
            followBad.removeViewAt(0);
        }
        followBad.addView(noticeView, followBad.getChildCount());
        mCurrPos = next;
    }

}
