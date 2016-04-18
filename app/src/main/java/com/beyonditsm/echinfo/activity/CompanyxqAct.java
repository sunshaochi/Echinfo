package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.CompanyAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.view.MyGridView;
import com.beyonditsm.echinfo.view.MySelfSheetDialog;
import com.beyonditsm.echinfo.widget.ShareDialog;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * Created by bitch-1 on 2016/4/6.
 * 企业详情界面
 *
 */
public class CompanyxqAct extends BaseActivity {
    @ViewInject(R.id.gvqy)
    private MyGridView gvqy;//企业详情界面里面的gridview
    @ViewInject(R.id.gxtime)
    private TextView gxtime;//更新时间
    @ViewInject(R.id.xc)
    private TextView xc;//续存
    @ViewInject(R.id.rb_dpxx)
    private RatingBar ratingBar;//星星数
    @ViewInject(R.id.tvAttention)
    private TextView guanzhu;//点击关注
    @ViewInject(R.id.looknum)
    private TextView looknum;//浏览数
    @ViewInject(R.id.guanzhunum)
    private TextView guanzhunum;//关注数
    @ViewInject(R.id.dbname)
    private TextView dbname;//法定代表人
    @ViewInject(R.id.zczj)
    private TextView zczj;//注册资金
    @ViewInject(R.id.cltime)
    private TextView cltime;//成立时间
    @ViewInject(R.id.location)
    private TextView locatime;//公司位置

    @Override
    public void setLayout() {
        setContentView(R.layout.act_companyxq);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("华东控股集团有限公司");
        gvqy.setAdapter(new CompanyAdapter(CompanyxqAct.this));
        gvqy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //            private final String TITLES[] = {"工商信息", "企业图谱", "行业分析", "失业信息", "诉讼信息",
//                    "对外投资","股东信息","企业资讯","年报信息","分子机构","主要成员","变更记录"
//            };
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://
                        openActivity(BusinessinfoAct.class);
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:
                        openActivity(DishonestyInfoAct.class);
                        break;
                    case 4://诉讼信息
                        openActivity(LitigationAct.class);
                        break;
                    case 5://对外投资
                        openActivity(InvestmentAct.class);

                        break;
                    case 6://股东信息
                        openActivity(GudonginfoAct.class);

                        break;
                    case 7://企业资讯
                        openActivity(InformationAct.class);
                        break;
                    case 8:

                        break;
                    case 9://分支机构
                        openActivity(PamentAct.class);
                        break;
                    case 10://主要成员
                        openActivity(PeopleAct.class);
                        break;
                    case 11://变更记录
                        openActivity(ChangecodeAct.class);
                        break;


                }
            }
        });


    }


    @OnClick({R.id.rl_cd,R.id.rlMap,R.id.tvAttention})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_cd:
                View view = findViewById(R.id.iv_cd);
                showPopupWindow(view);
                break;
            case R.id.rlMap:
                openActivity(MapAct.class);
                break;
            case R.id.tvAttention://关注

                break;
        }


    }

    private void showPopupWindow(View parent) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.popuwindows_dialog, null);
        // 实例化popupWindow
         final PopupWindow popupWindow = new PopupWindow(layout, 300, 800);
        //控制键盘是否可以获得焦点
        popupWindow.setFocusable(true);
        //设置popupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        WindowManager manager = (WindowManager) getSystemService(CompanyxqAct.this.WINDOW_SERVICE);
        @SuppressWarnings("deprecation")
        //获取xoff
                int xpos = manager.getDefaultDisplay().getWidth() / 2 - popupWindow.getWidth() / 2;
        //xoff,yoff基于anchor的左下角进行偏移。
        popupWindow.showAsDropDown(parent, xpos, 0);
        LinearLayout ll_share = (LinearLayout) layout.findViewById(R.id.ll_share);//分享
        LinearLayout ll_dh = (LinearLayout) layout.findViewById(R.id.ll_dh);//电话
        LinearLayout ll_jc = (LinearLayout) layout.findViewById(R.id.ll_jc);//纠错

        ll_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                ShareDialog dialog=new ShareDialog(CompanyxqAct.this).builder();
                dialog.show();
            }
        });

        ll_dh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                MySelfSheetDialog dialog = new MySelfSheetDialog(CompanyxqAct.this);
                dialog.builder().addSheetItem("7-830940-7009",MySelfSheetDialog.SheetItemColor.Blue, new MySelfSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        Intent phoneintent=new Intent("android.intent.action.CALL", Uri.parse("tel:" + "7-830940-7009"));
                        startActivity(phoneintent);

                    }
                }).addSheetItem("7-830940-7009",MySelfSheetDialog.SheetItemColor.Blue, new MySelfSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        Intent phoneintent=new Intent();
                        phoneintent.setAction(Intent.ACTION_CALL);
                        phoneintent.setData(Uri.parse("tel:7-830940-7009"));
                        startActivity(phoneintent);

                    }
                }).show();

            }
        });
        ll_jc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ErrorAct.class);
            }
        });


    }
}
