package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.beyonditsm.echinfo.view.MySelfSheetDialog;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 地图展示
 * Created by wangbin on 16/4/8.
 */
public class MapAct extends BaseActivity {
    @ViewInject(R.id.mapView)
    private MapView mapView;
    private BaiduMap mBaiduMap;// 地图实例
    private double lat=39.963175;
    private double lng=116.400244;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_map);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("公司所在地");
        mBaiduMap = mapView.getMap();
        LatLng ll = new LatLng(lat,lng);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(u);

        View view = View.inflate(MapAct.this, R.layout.mymarker, null);
        ImageView ivNavigaion= (ImageView) view.findViewById(R.id.ivNavigaion);
        ivNavigaion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySelfSheetDialog dialog=new MySelfSheetDialog(MapAct.this);
                dialog.builder();
                if(!EchinfoUtils.isAvilible(MapAct.this,"com.baidu.BaiduMap")){
                    MyToastUtils.showShortToast(getApplicationContext(),"没有检测到相关应用");
                }else{
                    dialog.addSheetItem("百度地图", MySelfSheetDialog.SheetItemColor.Blue, new MySelfSheetDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {

                        }
                    });
                }

            }
        });
//        carnumber = (TextView) view.findViewById(R.id.carnumber);
//        carnumber.setText(spaceNum + "");
        BitmapDescriptor mIconMaker = BitmapDescriptorFactory.fromView(view);
//        //构建Marker图标
//        BitmapDescriptor bitmap = BitmapDescriptorFactory
//                .fromResource(R.mipmap.location);
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


}