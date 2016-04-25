package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.CompanyEntity;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.beyonditsm.echinfo.view.MySelfSheetDialog;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.net.URISyntaxException;

/**
 * 地图展示
 * Created by wangbin on 16/4/8.
 */
public class MapAct extends BaseActivity {
    @ViewInject(R.id.mapView)
    private MapView mapView;
    private TextView tvCoun;
    private TextView tvAddress;
    private BaiduMap mBaiduMap;// 地图实例
    private double lat = 0.0;
    private double lng = 0.0;

    public static final String ADDRESS="address";
    CompanyEntity entity;

    @Override
    public void setLayout() {
        setContentView(R.layout.act_map);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("公司所在地");
         entity=getIntent().getParcelableExtra(ADDRESS);

        mBaiduMap = mapView.getMap();
        lat=entity.getLatitude();
        lng=entity.getLongitude();
        LatLng ll = new LatLng(lat, lng);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(u);

        View view = View.inflate(MapAct.this, R.layout.mymarker, null);
        tvCoun= (TextView) view.findViewById(R.id.tvCoun);
        tvAddress= (TextView) view.findViewById(R.id.tvAddress);
        tvCoun.setText(entity.getAddress());
        tvAddress.setText(entity.getCoords());
        ImageView ivNavigaion = (ImageView) view.findViewById(R.id.ivNavigaion);
        ivNavigaion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySelfSheetDialog dialog = new MySelfSheetDialog(MapAct.this);
                dialog.builder();
                if (!EchinfoUtils.isAvilible(MapAct.this, "com.baidu.BaiduMap")&&!EchinfoUtils.isAvilible(MapAct.this, "com.autonavi.minimap")) {
                    MyToastUtils.showShortToast(getApplicationContext(), "没有检测到相关应用");
                } else {
                    if(EchinfoUtils.isAvilible(MapAct.this, "com.baidu.BaiduMap")) {
                        dialog.addSheetItem("百度地图", MySelfSheetDialog.SheetItemColor.Blue, new MySelfSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                try {
                                    Intent intent = Intent.getIntent("intent://map/marker?location="+lat+","+lng+"&title=" +entity.getAddress()+
                                            "&content=\"\"&src=yourCompanyName|一企查#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                                    startActivity(intent); //启动调用
                                } catch (URISyntaxException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    if(EchinfoUtils.isAvilible(MapAct.this, "com.autonavi.minimap")) {
                        dialog.addSheetItem("高德地图", MySelfSheetDialog.SheetItemColor.Blue, new MySelfSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                   openGaoDeMap();
                            }
                        });
                    }
                    dialog.show();
                }

            }
        });

        //定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(lat,lng);
//创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(view, pt,-87);
//显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);
//        carnumber = (TextView) view.findViewById(R.id.carnumber);
//        carnumber.setText(spaceNum + "");
//        BitmapDescriptor mIconMaker = BitmapDescriptorFactory.fromView(view);
//        //构建Marker图标
        BitmapDescriptor mIconMaker = BitmapDescriptorFactory
                .fromResource(R.mipmap.location);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(ll)
                .icon(mIconMaker);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }


    private void openGaoDeMap()
    {
        try
        {
            Intent intent = Intent.getIntent("androidamap://viewMap?sourceApplication=一企查&poiname="+entity.getAddress()+"&lat="+lat+"&lon="+lng+"&dev=0");
            startActivity(intent);
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
    }

}
