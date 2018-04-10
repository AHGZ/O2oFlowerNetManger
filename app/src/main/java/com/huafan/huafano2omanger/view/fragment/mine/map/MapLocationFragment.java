package com.huafan.huafano2omanger.view.fragment.mine.map;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.MapEntity;
import com.huafan.huafano2omanger.event.getMapEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.SPUtils;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.utils.appstatus.Eyes;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @描述:地图定位主页
 * @创建人：zhangpeisen
 * @创建时间：2017/10/30 下午5:40
 * @修改人：zhangpeisen
 * @修改时间：2017/10/30 下午5:40
 * @修改备注：
 * @throws
 */

public class MapLocationFragment extends KFragment<MapLocationView, MapLocationPrenter> implements SensorEventListener, NormalTopBar.normalTopClickListener, OnGetGeoCoderResultListener, MapLocationView, BDLocationListener, BaiduMap.OnMapStatusChangeListener {
    @BindView(R.id.normal_top)
    //自定义通用标题
            NormalTopBar normalTopBar;

    // 定位相关
    LocationClient mLocClient;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    //定位坐标
    private LatLng locationLatLng;
    //是否是第一次定位
    private boolean isFirstLoc = true;
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    @BindView(R.id.bmapView)
    MapView mMapView;
    BaiduMap mBaiduMap;
    private MyLocationData locData;
    private GeoCoder geoCoder;
    @BindView(R.id.save_btn)
    Button saveBtn;
    // 加载进度条
    private ShapeLoadingDialog shapeLoadingDialog;
    // 经度
    private double longitude = 0.00;
    // 纬度
    private double latitude = 0.00;


    public static MapLocationFragment newInstance() {
        Bundle args = new Bundle();
        MapLocationFragment fragment = new MapLocationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public MapLocationPrenter createPresenter() {
        return new MapLocationPrenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.storemap_fragment;
    }


    @Override
    public void onLeftClick(View view) {
        removeFragment();
    }

    @Override
    public void onRightClick(View view) {

    }

    @Override
    public void onTitleClick(View view) {

    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        // 设置地图的中心点
//        MapStatusUpdate statusMap = MapStatusUpdateFactory.newLatLng(geoCodeResult.getLocation());
//        mBaiduMap.animateMapStatus(statusMap);
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            showShortToast("抱歉,未能找到结果");
            return;
        }
        mBaiduMap.clear();
        // 用来改变地图的状态
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(reverseGeoCodeResult.getLocation()));
        latitude = reverseGeoCodeResult.getLocation().latitude;
        longitude = reverseGeoCodeResult.getLocation().longitude;
//        showShortToast("经纬度:" + reverseGeoCodeResult.getLocation() + "," + "定位地址"
//                + reverseGeoCodeResult.getAddressDetail().city + "," + reverseGeoCodeResult.getAddressDetail().district + ","
//                + reverseGeoCodeResult.getAddressDetail().street);
    }

    @Override
    public String longitude() {
        return String.valueOf(longitude);
    }

    @Override
    public String latitude() {
        return String.valueOf(latitude);
    }

    @Override
    public void showDialog() {
        shapeLoadingDialog.show();
    }

    @Override
    public void hideDialog() {
        if (shapeLoadingDialog.isShowing()) {
            shapeLoadingDialog.dismiss();
        }
    }

    @Override
    public void onError(String errorMsg) {
        showShortToast(errorMsg);
    }

    @Override
    public void onSuccess(MapEntity mapEntity) {
        removeFragment();
        EventBus.getDefault().post(new getMapEvent());
    }

    @OnClick({R.id.save_btn})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.save_btn:
                // 保存经纬度
                mPresenter.UpMaps();
                SPUtils.put(getActivity(),"latitude",latitude);
                SPUtils.put(getActivity(),"longitude",longitude);
                break;
        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onDestroyView() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroyView();
    }


    @Override
    public void initData() {
        //移除放大缩小图标
        mMapView.showZoomControls(false);
        mBaiduMap = mMapView.getMap();
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder().zoom(18).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);

        //地图状态改变相关监听
        mBaiduMap.setOnMapStatusChangeListener(this);

        //开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        //定位图层显示方式
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        /**
         * 设置定位图层配置信息，只有先允许定位图层后设置定位图层配置信息才会生效
         * customMarker 用户自定义定位图标
         * enableDirection 是否允许显示方向信息
         * locationMode 定位图层显示方式
         */
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, null));

        //初始化定位
        mLocClient = new LocationClient(getActivity());
        //注册定位监听
        mLocClient.registerLocationListener(this);
        //定位选项
        LocationClientOption option = new LocationClientOption();
        /**
         * coorType - 取值有 3 个：
         * 返回国测局经纬度坐标系：gcj02
         * 返回百度墨卡托坐标系 ：bd09
         * 返回百度经纬度坐标系 ：bd09ll
         */
        option.setCoorType("bd09ll");
        //设置是否需要地址信息，默认为无地址
        option.setIsNeedAddress(true);
        //设置是否需要返回位置语义化信息，可以在 BDLocation.getLocationDescribe()中得到数据，ex:"在天安门附近"， 可以用作地址信息的补充
        option.setIsNeedLocationDescribe(true);
        //设置是否需要返回位置 POI 信息，可以在 BDLocation.getPoiList()中得到数据
        option.setIsNeedLocationPoiList(true);
        /**
         * 设置定位模式
         * Battery_Saving
         * 低功耗模式
         * Device_Sensors
         * 仅设备(Gps)模式
         * Hight_Accuracy
         * 高精度模式
         */
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //设置是否打开 gps 进行定位
        option.setOpenGps(true);
        //设置扫描间隔，单位是毫秒 当<1000(1s)时，定时定位无效
        option.setScanSpan(1000);

        //设置 LocationClientOption
        mLocClient.setLocOption(option);

        //开始定位
        mLocClient.start();

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        //初始化沉浸式
        Eyes.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        // 初始化标题及相关事件监听
        normalTopBar.setTitleText("商家定位");
        normalTopBar.getRightImage().setVisibility(View.GONE);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTopBar.getLeftImage(), 50);
        normalTopBar.setTopClickListener(this);

        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 恢复时同时恢复地图控件
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //退出时停止定位
        mLocClient.stop();
        //退出时关闭定位图层
        if (null != mBaiduMap) {
            mBaiduMap.setMyLocationEnabled(false);
        }

        // 销毁时同时销毁地图控件
        if (null != mMapView) {
            mMapView.onDestroy();
        }

        //释放资源
        if (geoCoder != null) {
            geoCoder.destroy();
        }

        mMapView = null;
    }

    /**
     * 手势操作地图，设置地图状态等操作导致地图状态开始改变
     *
     * @param mapStatus 地图状态改变开始时的地图状态
     */
    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {

    }


    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

    }

    /**
     * 地图状态变化中
     *
     * @param mapStatus 当前地图状态
     */
    @Override
    public void onMapStatusChange(MapStatus mapStatus) {

    }

    /**
     * 地图状态改变结束
     *
     * @param mapStatus 地图状态改变结束后的地图状态
     */
    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        //地图操作的中心点
        LatLng cenpt = mapStatus.target;
        if (geoCoder != null) {
            geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(cenpt));
        }
    }

    //定位监听
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        //如果 bdLocation 为空或 mapView 销毁后不再处理新数据接收的位置
        if (bdLocation == null || mBaiduMap == null) {
            return;
        }

        //定位数据
        MyLocationData data = new MyLocationData.Builder()
                //定位精度 bdLocation.getRadius()
                .accuracy(bdLocation.getRadius())
                //此处设置开发者获取到的方向信息，顺时针 0-360
                .direction(bdLocation.getDirection())
                //经度
                .latitude(bdLocation.getLatitude())
                //纬度
                .longitude(bdLocation.getLongitude())
                //构建
                .build();

        //设置定位数据
//        mBaiduMap.setMyLocationData(data);

        //是否是第一次定位
        if (isFirstLoc) {
            isFirstLoc = false;

            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(ll, 18);

            mBaiduMap.animateMapStatus(msu);
            //获取坐标，待会用于 POI 信息点与定位的距离
            locationLatLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());

            //创建 GeoCoder 实例对象
            geoCoder = GeoCoder.newInstance();
            //发起反地理编码请求(经纬度->地址信息)
            ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption();
            //设置反地理编码位置坐标

            boolean mapFlag = (boolean) SPUtils.get(getActivity(), "mapFlag", false);
            if (mapFlag==false){
                SPUtils.put(getActivity(),"mapFlag",true);
                SPUtils.put(getActivity(),"latitude",latitude);
                SPUtils.put(getActivity(),"longitude",longitude);
                reverseGeoCodeOption.location(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()));
                //设置查询结果监听者
                geoCoder.setOnGetGeoCodeResultListener(this);
                geoCoder.reverseGeoCode(reverseGeoCodeOption);
            }else{
                String mLatitude = (String) SPUtils.get(getActivity(),"latitude","");
                String mLongitude = (String)SPUtils.get(getActivity(),"longitude","");

                reverseGeoCodeOption.location(new LatLng(Double.parseDouble(mLatitude),Double.parseDouble(mLongitude) ));
                //设置查询结果监听者
                geoCoder.setOnGetGeoCodeResultListener(this);
                geoCoder.reverseGeoCode(reverseGeoCodeOption);
            }
        }

    }
}
//public class MapLocationFragment extends KFragment<MapLocationView, MapLocationPrenter> implements SensorEventListener, NormalTopBar.normalTopClickListener, OnGetGeoCoderResultListener, MapLocationView {
//    @BindView(R.id.normal_top)
//    //自定义通用标题
//            NormalTopBar normalTopBar;
//
//    // 定位相关
//    LocationClient mLocClient;
//    public MyLocationListenner myListener = new MyLocationListenner();
//    private MyLocationConfiguration.LocationMode mCurrentMode;
//    private static final int accuracyCircleFillColor = 0xAAFFFF88;
//    private static final int accuracyCircleStrokeColor = 0xAA00FF00;
//    BitmapDescriptor mCurrentMarker;
//    private SensorManager mSensorManager;
//    private Double lastX = 0.0;
//    private int mCurrentDirection = 0;
//    private double mCurrentLat = 0.0;
//    private double mCurrentLon = 0.0;
//    private float mCurrentAccracy;
//    @BindView(R.id.bmapView)
//    MapView mMapView;
//    BaiduMap mBaiduMap;
//    private MyLocationData locData;
//    private GeoCoder geoCoder;
//    @BindView(R.id.save_btn)
//    Button saveBtn;
//    // 加载进度条
//    private ShapeLoadingDialog shapeLoadingDialog;
//    // 经度
//    private double longitude = 0.00;
//    // 纬度
//    private double latitude = 0.00;
//
//
//    public static MapLocationFragment newInstance() {
//        Bundle args = new Bundle();
//        MapLocationFragment fragment = new MapLocationFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public MapLocationPrenter createPresenter() {
//        return new MapLocationPrenter();
//    }
//
//    @Override
//    protected int getLayout() {
//        return R.layout.storemap_fragment;
//    }
//
//    @Override
//    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
//        //初始化沉浸式
//        Eyes.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.colorPrimary));
//        // 初始化标题及相关事件监听
//        normalTopBar.setTitleText("商家定位");
//        normalTopBar.getRightImage().setVisibility(View.GONE);
//        // 扩大事件的点击范围
//        UIUtils.setTouchDelegate(normalTopBar.getLeftImage(), 50);
//        normalTopBar.setTopClickListener(this);
//        // 地图初始化
//        mBaiduMap = mMapView.getMap();
//        geoCoder = GeoCoder.newInstance();
//        geoCoder.setOnGetGeoCodeResultListener(this);
//        //为系统的方向传感器注册监听器
//        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);//获取传感器管理服务
//        Sensor sensor = mSensorManager
//                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        if (null != sensor)
//            mSensorManager.registerListener(this, sensor,
//                    SensorManager.SENSOR_DELAY_NORMAL);
//        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
//                .loadText("加载中...")
//                .delay(5000)
//                .build();
//        // 定位初始化
//        mLocClient = new LocationClient(getActivity());
//        mLocClient.registerLocationListener(myListener);
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000);
//        mLocClient.setLocOption(option);
//        mLocClient.start();
//        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
//        // 修改为自定义marker
//        mCurrentMarker = BitmapDescriptorFactory
//                .fromResource(R.mipmap.icon_dw);
//
//        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
//                mCurrentMode, true, mCurrentMarker,
//                accuracyCircleFillColor, accuracyCircleStrokeColor));
//        MapStatus.Builder builder = new MapStatus.Builder();
//        builder.overlook(0);
//        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
//            @Override
//            public void onMapStatusChangeStart(MapStatus mapStatus) {
//
//            }
//
//            @Override
//            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {
//
//            }
//
//            @Override
//            public void onMapStatusChange(MapStatus mapStatus) {
//
//            }
//
//            @Override
//            public void onMapStatusChangeFinish(MapStatus mapStatus) {
//                LatLng ptCenter = mBaiduMap.getMapStatus().target;
//                geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(ptCenter));
//            }
//        });
//    }
//
//    @Override
//    public void initData() {
//
//
//    }
//
//    @Override
//    public void onLeftClick(View view) {
//        removeFragment();
//    }
//
//    @Override
//    public void onRightClick(View view) {
//
//    }
//
//    @Override
//    public void onTitleClick(View view) {
//
//    }
//
//    @Override
//    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
//        // 设置地图的中心点
//        MapStatusUpdate statusMap = MapStatusUpdateFactory.newLatLng(geoCodeResult.getLocation());
//        mBaiduMap.animateMapStatus(statusMap);
//    }
//
//    @Override
//    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
//        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
//            showShortToast("抱歉,未能找到结果");
//            return;
//        }
//        mBaiduMap.clear();
//        // 用来改变地图的状态
//        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(reverseGeoCodeResult.getLocation()));
//        latitude = reverseGeoCodeResult.getLocation().latitude;
//        longitude = reverseGeoCodeResult.getLocation().longitude;
//        showShortToast("经纬度:" + reverseGeoCodeResult.getLocation() + "," + "定位地址"
//                + reverseGeoCodeResult.getAddressDetail().city + "," + reverseGeoCodeResult.getAddressDetail().district + ","
//                + reverseGeoCodeResult.getAddressDetail().street);
//    }
//
//    @Override
//    public String longitude() {
//        return String.valueOf(longitude);
//    }
//
//    @Override
//    public String latitude() {
//        return String.valueOf(latitude);
//    }
//
//    @Override
//    public void showDialog() {
//        shapeLoadingDialog.show();
//    }
//
//    @Override
//    public void hideDialog() {
//        if (shapeLoadingDialog.isShowing()) {
//            shapeLoadingDialog.dismiss();
//        }
//    }
//
//    @Override
//    public void onError(String errorMsg) {
//        showShortToast(errorMsg);
//    }
//
//    @Override
//    public void onSuccess(MapEntity mapEntity) {
//        removeFragment();
//        EventBus.getDefault().post(new getMapEvent());
//    }
//
//    @OnClick({R.id.save_btn})
//    public void onViewClick(View v) {
//        switch (v.getId()) {
//            case R.id.save_btn:
//                // 保存经纬度
//                mPresenter.UpMaps();
//                break;
//        }
//    }
//
//    /**
//     * 定位SDK监听函数
//     */
//    public class MyLocationListenner implements BDLocationListener {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // map view 销毁后不在处理新接收的位置
//            if (location == null || mMapView == null) {
//                return;
//            }
//            mCurrentLat = location.getLatitude();
//            mCurrentLon = location.getLongitude();
//            mCurrentAccracy = location.getRadius();
//            locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(mCurrentDirection).latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//            mBaiduMap.setMyLocationData(locData);
//            LatLng ll = new LatLng(location.getLatitude(),
//                    location.getLongitude());
//            MapStatus.Builder builder = new MapStatus.Builder();
//            builder.target(ll).zoom(18.0f);
//            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//        }
//
//        public void onReceivePoi(BDLocation poiLocation) {
//        }
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }
//
//    @Override
//    public void onStop() {
//        //取消注册传感器监听
//        mSensorManager.unregisterListener(this);
//        super.onStop();
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//        double x = sensorEvent.values[SensorManager.DATA_X];
//        if (Math.abs(x - lastX) > 1.0) {
//            mCurrentDirection = (int) x;
//            locData = new MyLocationData.Builder()
//                    .accuracy(mCurrentAccracy)
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(mCurrentDirection).latitude(mCurrentLat)
//                    .longitude(mCurrentLon).build();
//            mBaiduMap.setMyLocationData(locData);
//        }
//        lastX = x;
//
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }
//
//    @Override
//    public void onDestroyView() {
//        // 退出时销毁定位
//        mLocClient.stop();
//        // 关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);
//        mMapView.onDestroy();
//        mMapView = null;
//        super.onDestroyView();
//    }
//
//}

