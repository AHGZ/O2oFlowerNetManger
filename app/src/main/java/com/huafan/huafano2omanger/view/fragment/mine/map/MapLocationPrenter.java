package com.huafan.huafano2omanger.view.fragment.mine.map;


import android.text.TextUtils;

import com.huafan.huafano2omanger.entity.MapEntity;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.huafan.huafano2omanger.view.fragment.mine.IMineModel;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * @描述:地图定位的逻辑层
 * @创建人：zhangpeisen
 * @创建时间：2017/10/30 下午5:38
 * @修改人：zhangpeisen
 * @修改时间：2017/10/30 下午5:38
 * @修改备注：
 * @throws
 */
public class MapLocationPrenter extends IPresenter<MapLocationView> {
    private IMineModel mineModel;

    public MapLocationPrenter() {

        mineModel = new IMineModel();
    }

    /**
     * @throws
     * @描述: 设置经纬度
     * @创建人：zhangpeisen
     * @创建时间：2018/1/9 下午1:39
     * @修改人：zhangpeisen
     * @修改时间：2018/1/9 下午1:39
     * @修改备注：
     */
    public void UpMaps() {
        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        String longitude = getView().longitude();
        String latitude = getView().latitude();
        if (TextUtils.isEmpty(longitude) && longitude.equals("")) {
            getView().onError("抱歉,未能找到结果");
            return;
        }
        if (TextUtils.isEmpty(latitude) && latitude.equals("")) {
            getView().onError("抱歉,未能找到结果");
            return;
        }
        getView().showDialog();
        mineModel.settingUpMap(longitude, latitude, new IModelImpl<ApiResponse<MapEntity>, MapEntity>() {
            @Override
            protected void onSuccess(MapEntity data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<MapEntity>> data, String message) {
                getView().hideDialog();
            }

            @Override
            protected void onFailure(String code, String message) {
                getView().hideDialog();
                getView().onError(message);
            }

            @Override
            protected void onSuccess() {

            }
        });
    }


    @Override
    protected void cancel() {
        mineModel.cancel();
    }
}
