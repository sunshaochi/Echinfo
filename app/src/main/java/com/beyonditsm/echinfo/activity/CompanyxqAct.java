package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
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
import com.beyonditsm.echinfo.entity.StatusEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.GsonUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.beyonditsm.echinfo.view.MyGridView;
import com.beyonditsm.echinfo.view.MySelfSheetDialog;
import com.beyonditsm.echinfo.widget.ShareDialog;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONException;
import org.json.JSONObject;

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
    private boolean flag;
    String iId =null;
    public static final String ID ="id";//企业详情
    public static final String COMPANYID ="companyId";//企业详情中gridview的item
    @Override
    public void setLayout() {
        setContentView(R.layout.act_companyxq);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        iId =getIntent().getStringExtra(ID);
        if(!TextUtils.isEmpty(iId)) {
            selectStatus(iId);
            findEnterpriseInfoMsgById(iId);
        }

        gvqy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //            private final String TITLES[] = {"工商信息", "企业图谱", "行业分析", "失业信息", "诉讼信息",
//                    "对外投资","股东信息","企业资讯","年报信息","分子机构","主要成员","变更记录"
//            };
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0://工商信息
                        if ("1".equals(statusEntity.getGongshangStatus())) {
                            intent = new Intent(CompanyxqAct.this, BusinessinfoAct.class);
                            intent.putExtra(COMPANYID, iId);
                            startActivity(intent);
                        }
                        break;
                    case 1://企业图谱
                        if ("1".equals(statusEntity.getTupuStatus())) {
                            intent = new Intent(CompanyxqAct.this, WebAct.class);
                            intent.putExtra(WebAct.WEB_TYPE, 1);
                            intent.putExtra(WebAct.ID, iId);
                            startActivity(intent);
                        }
                        break;
                    case 2://行业分析
                        if ("1".equals(statusEntity.getHangyeStatus())) {
                            intent = new Intent(CompanyxqAct.this, WebAct.class);
                            intent.putExtra(WebAct.WEB_TYPE, 2);
                            intent.putExtra(WebAct.ID, iId);
                            startActivity(intent);
                        }
                        break;
                    case 3://失信信息
                        if ("1".equals(statusEntity.getCourtitemStatus())) {
                            intent = new Intent(CompanyxqAct.this, DishonestyInfoAct.class);
                            intent.putExtra(COMPANYID, iId);
                            intent.putExtra("iname", entity.getCompanyName());
                            startActivity(intent);
                        }
                        break;
                    case 4://诉讼信息
                        if ("1".equals(statusEntity.getLawsuitmsgStatus())) {
                            intent = new Intent(CompanyxqAct.this, LitigationAct.class);
                            intent.putExtra(COMPANYID, iId);
                            startActivity(intent);
                        }
                        break;
                    case 5://对外投资
                        if ("1".equals(statusEntity.getAbroadinvestmenttatus())) {
                            intent = new Intent(CompanyxqAct.this, InvestmentAct.class);
                            intent.putExtra(COMPANYID, iId);
                            startActivity(intent);
                        }
                        break;
                    case 6://股东信息
                        if ("1".equals(statusEntity.getStockmsgStatus())) {
                            intent = new Intent(CompanyxqAct.this, GudonginfoAct.class);
                            intent.putExtra(COMPANYID, iId);
                            startActivity(intent);
                        }
                        break;
                    case 7://企业资讯
                        if ("1".equals(statusEntity.getEnenewterprissStatus())) {
                            intent = new Intent(CompanyxqAct.this, InformationAct.class);
                            intent.putExtra(COMPANYID, iId);
                            startActivity(intent);
                        }
                        break;
                    case 8://年报信息
                        if ("1".equals(statusEntity.getAnnualportsmsgStatus())) {
                            intent = new Intent(CompanyxqAct.this, AnnualAct.class);
                            intent.putExtra(COMPANYID, iId);
                            startActivity(intent);
                        }
                        break;
                    case 9://分支机构
                        if ("1".equals(statusEntity.getSonenterpriseStatus())) {
                            intent = new Intent(CompanyxqAct.this, PamentAct.class);
                            intent.putExtra(COMPANYID, iId);
                            startActivity(intent);
                        }
                        break;
                    case 10://主要成员
                        if ("1".equals(statusEntity.getMainmemberStatus())) {
                            intent = new Intent(CompanyxqAct.this, PeopleAct.class);
                            intent.putExtra(COMPANYID, iId);
                            startActivity(intent);
                        }
                        break;
                    case 11://变更记录
                        if ("1".equals(statusEntity.getEditrecordStatus())) {
                            intent = new Intent(CompanyxqAct.this, ChangecodeAct.class);
                            intent.putExtra(COMPANYID, iId);
                            startActivity(intent);
                        }
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
                Bundle bundle=new Bundle();
                bundle.putParcelable(MapAct.ADDRESS,entity);
                openActivity(MapAct.class,bundle);
                break;
            case R.id.tvAttention://关注
                if (UserDao.getUser()!=null) {
                    if (!flag) {
                        addMyAttention(iId);
                    } else {
                        removeMyAttention(iId);
                    }
                }else {
                    openActivity(LoginAct.class);
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
                tvAttention.setTextColor(getResources().getColor(R.color.tv_gray));
                tvAttention.setBackgroundResource(R.drawable.yellow_btn);
                flag = true;
                //"status":200,"data":{"focus":0},"message":"成功！"
                try {
                    JSONObject json=new JSONObject(result);
                    JSONObject data=json.getJSONObject("data");
                    String num=data.getString("focus");
                    guanzhunum.setText(num);
                    sendBroadcast(new Intent(MainAct.MAIN_RECEIVER));
                    sendBroadcast(new Intent(ReqyAct.MAIN_RECEIVER_HOT));
                    sendBroadcast(new Intent(MyFollowAct.MAIN_RECEIVER_FOLLOW));
                    MyToastUtils.showShortToast(CompanyxqAct.this, "关注企业成功");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                MyToastUtils.showShortToast(CompanyxqAct.this, error);
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
                tvAttention.setTextColor(getResources().getColor(R.color.white));
                tvAttention.setBackgroundResource(R.drawable.yellow_tran_btn);
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject data = json.getJSONObject("data");
                    String num = data.getString("focus");
                    guanzhunum.setText(num);
                    sendBroadcast(new Intent(MainAct.MAIN_RECEIVER));
                    sendBroadcast(new Intent(ReqyAct.MAIN_RECEIVER_HOT));
                    sendBroadcast(new Intent(MyFollowAct.MAIN_RECEIVER_FOLLOW));
                    MyToastUtils.showShortToast(CompanyxqAct.this, "取消关注成功");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                MyToastUtils.showShortToast(CompanyxqAct.this, error);
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
            if(!TextUtils.isEmpty(entity.getSts())){
                if("1".equals(entity.getSts())){
                    flag=true;
                    tvAttention.setText("已关注");
                    tvAttention.setTextColor(getResources().getColor(R.color.tv_gray));
                    tvAttention.setBackgroundResource(R.drawable.yellow_btn);
                }else {
                    flag=false;
                    tvAttention.setText("关注");
                    tvAttention.setTextColor(getResources().getColor(R.color.white));
                    tvAttention.setBackgroundResource(R.drawable.yellow_tran_btn);
                }
            }else {
                flag=false;
                tvAttention.setText("关注");
                tvAttention.setTextColor(getResources().getColor(R.color.white));
                tvAttention.setBackgroundResource(R.drawable.yellow_tran_btn);
            }
            if(!TextUtils.isEmpty(entity.getCompanyName())){
                if(entity.getCompanyName().length()<=12) {
                    setTopTitle(entity.getCompanyName());
                }else {
                    setTopTitle(entity.getCompanyName().substring(0,11)+"...");
                }
            }
            if(!TextUtils.isEmpty(entity.getLastUpdateTime())){
                gxtime.setText(entity.getLastUpdateTime());
            }else {
                gxtime.setText("      ");
            }
            if(!TextUtils.isEmpty(entity.getManagementStatus())) {
                xc.setText(entity.getManagementStatus());
            }else {
                xc.setText("");
                xc.setVisibility(View.GONE);
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
            if(!TextUtils.isEmpty(entity.getLegalRepPersion())){
                dbname.setText(entity.getLegalRepPersion());
            }else {
                dbname.setText("");
            }
            if(!TextUtils.isEmpty(entity.getRegistCapital())) {
                if(entity.getRegistCapital().contains("未")){
                    zczj.setText(entity.getRegistCapital());
                }else {
                    zczj.setText(Integer.valueOf(entity.getRegistCapital()) + "万人民币");
                }
            }else {
                zczj.setText("0万人民币");
            }
            if(!TextUtils.isEmpty(entity.getCompanyCreatTime())){
                cltime.setText(entity.getCompanyCreatTime());
            }else {
                cltime.setText(entity.getCompanyCreatTime());
            }
            if(!TextUtils.isEmpty(entity.getAddress())) {
                location.setText(entity.getAddress());
            }else {
                location.setText("");
            }
            if (!TextUtils.isEmpty(entity.getLevel())) {
                ratingBar.setProgress(Integer.valueOf(entity.getLevel()));
            }else {
                ratingBar.setProgress(0);
            }
        }
    }

    private StatusEntity statusEntity;
    /**
     * 查询企业状态
     * @param companyId
     */
    private void selectStatus(String companyId){
        RequestManager.getCommManager().selectStatus(companyId, new CallBack() {
            @Override
            public void onSucess(String result) {
                ResultData<StatusEntity> rd = (ResultData<StatusEntity>) GsonUtils.json(result, StatusEntity.class);
                statusEntity = rd.getData();
                gvqy.setAdapter(new CompanyAdapter(CompanyxqAct.this,statusEntity));
            }

            @Override
            public void onError(String error) {

            }
        });
    }
    private void showPopupWindow(View parent) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.popuwindows_dialog, null);
        // 实例化popupWindow
         final PopupWindow popupWindow = new PopupWindow(layout, AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
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
                if(!TextUtils.isEmpty(entity.getCompanyPhoneNo())) {
                    dialog.builder().addSheetItem(entity.getCompanyPhoneNo(), MySelfSheetDialog.SheetItemColor.Blue, new MySelfSheetDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {
                            Intent phoneintent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + entity.getCompanyPhoneNo()));
                            startActivity(phoneintent);

                        }
                    })/*.addSheetItem("7-830940-7009",MySelfSheetDialog.SheetItemColor.Blue, new MySelfSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        Intent phoneintent=new Intent();
                        phoneintent.setAction(Intent.ACTION_CALL);
                        phoneintent.setData(Uri.parse("tel:7-830940-7009"));
                        startActivity(phoneintent);

                    }
                })*/.show();
                }else {
                    MyToastUtils.showShortToast(CompanyxqAct.this,"暂时没有企业电话！");
                }

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
