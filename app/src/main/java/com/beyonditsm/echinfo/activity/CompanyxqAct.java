package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.beyonditsm.echinfo.db.UserDao;
import com.beyonditsm.echinfo.entity.CompanyEntity;
import com.beyonditsm.echinfo.entity.ResultData;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.GsonUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
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
    private TextView tvAttention;//点击关注


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
    private TextView location;//公司位置

    private CompanyEntity entity;
    private boolean flag=false;
    String companyId="12";
    @Override
    public void setLayout() {
        setContentView(R.layout.act_companyxq);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("华东控股集团有限公司");
        findEnterpriseInfoMsgById(companyId);
        gvqy.setAdapter(new CompanyAdapter(CompanyxqAct.this));
        gvqy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //            private final String TITLES[] = {"工商信息", "企业图谱", "行业分析", "失业信息", "诉讼信息",
//                    "对外投资","股东信息","企业资讯","年报信息","分子机构","主要成员","变更记录"
//            };
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0://工商信息
                        intent = new Intent(CompanyxqAct.this, BusinessinfoAct.class);
                        intent.putExtra("id", companyId);
                        startActivity(intent);
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3://失信信息
                        intent = new Intent(CompanyxqAct.this, DishonestyInfoAct.class);
                        intent.putExtra("id", companyId);
                        startActivity(intent);
                        break;
                    case 4://诉讼信息
                        intent = new Intent(CompanyxqAct.this, LitigationAct.class);
                        intent.putExtra("id", companyId);
                        startActivity(intent);
                        break;
                    case 5://对外投资
                        intent = new Intent(CompanyxqAct.this, InvestmentAct.class);
                        intent.putExtra("id", companyId);
                        startActivity(intent);
                        break;
                    case 6://股东信息
                        intent = new Intent(CompanyxqAct.this, GudonginfoAct.class);
                        intent.putExtra("id", companyId);
                        startActivity(intent);
                        break;
                    case 7://企业资讯
                        intent = new Intent(CompanyxqAct.this, InformationAct.class);
                        intent.putExtra("id", companyId);
                        startActivity(intent);
                        break;
                    case 8://年报信息
                        intent = new Intent(CompanyxqAct.this, AnnualAct.class);
                        intent.putExtra("id", companyId);
                        startActivity(intent);
                        break;
                    case 9://分支机构
                        intent = new Intent(CompanyxqAct.this, PamentAct.class);
                        intent.putExtra("id", companyId);
                        startActivity(intent);
                        break;
                    case 10://主要成员
                        intent = new Intent(CompanyxqAct.this, PeopleAct.class);
                        intent.putExtra("id", companyId);
                        startActivity(intent);
                        break;
                    case 11://变更记录
                        intent = new Intent(CompanyxqAct.this, ChangecodeAct.class);
                        intent.putExtra("id", companyId);
                        startActivity(intent);
//                        openActivity(ChangecodeAct.class);
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
                if(!flag) {
                    addMyAttention(companyId);
                }else {
                    removeMyAttention(companyId);
                }
                break;
        }


    }

    /**
     * 关注企业
     * @param companyId
     */
    private void addMyAttention(String companyId){
        RequestManager.getCommManager().addMyAttention(companyId, new CallBack() {
            @Override
            public void onSucess(String result) {
                tvAttention.setText("已关注");
                flag=true;
                MyToastUtils.showShortToast(CompanyxqAct.this,"关注企业成功");
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    /**
     * 取消关注
     * @param id
     */
    private void removeMyAttention(String id){
        RequestManager.getCommManager().removeMyAttention(id, new CallBack() {
            @Override
            public void onSucess(String result) {
                flag = false;
                tvAttention.setText("关注");
                MyToastUtils.showShortToast(CompanyxqAct.this,"取消关注成功");
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    /**
     * 查询企业信息
     * @param id
     */
    private void findEnterpriseInfoMsgById(String id){
        RequestManager.getCommManager().findEnterpriseInfoMsgById(id, new CallBack() {
            @Override
            public void onSucess(String result) {
                ResultData<CompanyEntity> rd = (ResultData<CompanyEntity>) GsonUtils.json(result, CompanyEntity.class);
                entity = rd.getData();
                setBusiness(entity);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    //设置企业信息
    private void setBusiness(CompanyEntity entity){
        if(entity!=null){
            if(!TextUtils.isEmpty(entity.getCompanyName())){
                setTopTitle(entity.getCompanyName());
            }
//                gxtime.setText();
            if(!TextUtils.isEmpty(entity.getManagementStatus())) {
                xc.setText(entity.getManagementStatus());
            }else {
                xc.setText("");
            }
            if(!TextUtils.isEmpty(entity.getBrowseCount())) {
                looknum.setText(entity.getBrowseCount());
            }else {
                looknum.setText("0");
            }
            if(!TextUtils.isEmpty(entity.getFocus())) {
                guanzhunum.setText(entity.getFocus());
            }else {
                guanzhunum.setText("0");
            }
//                dbname.setText();
            if(!TextUtils.isEmpty(entity.getCompanyInverstment())) {
                zczj.setText(entity.getCompanyInverstment()+"万人民币");
            }else {
                zczj.setText("");
            }
//                cltime.setText(entity.get);
            if(!TextUtils.isEmpty(entity.getAddress())&&!TextUtils.isEmpty(entity.getCoords())) {
                location.setText(entity.getAddress()+entity.getCoords());
            }else {
                if(!TextUtils.isEmpty(entity.getAddress())){
                    location.setText(entity.getAddress());
                }else if(!TextUtils.isEmpty(entity.getCoords())){
                    location.setText(entity.getCoords());
                }else {
                    location.setText("");
                }
            }
            if (!TextUtils.isEmpty(entity.getLevel())) {
                ratingBar.setProgress(Integer.valueOf(entity.getLevel()));
            }else {
                ratingBar.setProgress(0);
            }
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
