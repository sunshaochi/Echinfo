package com.beyonditsm.echinfo.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.CompanyAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.view.MyGridView;
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
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

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
                    case 10:

                        break;
                    case 11:

                        break;


                }
            }
        });


    }



    @OnClick({R.id.iv_cd})
    public void Onclick(View v) {
       switch (v.getId()){
           case R.id.iv_cd:
               View view=findViewById(R.id.iv_cd);
               showPopupWindow(view);
       }



    }

    private void showPopupWindow(View parent) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.popuwindows_dialog, null);
        // 实例化popupWindow
        PopupWindow popupWindow = new PopupWindow(layout, 300, 500);
        //控制键盘是否可以获得焦点
        popupWindow.setFocusable(true);
        //设置popupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null,""));
        WindowManager manager=(WindowManager) getSystemService(CompanyxqAct.this.WINDOW_SERVICE);
        @SuppressWarnings("deprecation")
        //获取xoff
                int xpos=manager.getDefaultDisplay().getWidth()/2-popupWindow.getWidth()/2;
        //xoff,yoff基于anchor的左下角进行偏移。
        popupWindow.showAsDropDown(parent, xpos, 0);
    }


}
