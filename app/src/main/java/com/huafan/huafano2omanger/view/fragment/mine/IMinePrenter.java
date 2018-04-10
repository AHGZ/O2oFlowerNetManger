package com.huafan.huafano2omanger.view.fragment.mine;

import com.huafan.huafano2omanger.entity.O2oMineBean;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.mvp.IPresenter;
import com.huafan.huafano2omanger.utils.NetWorkUtils;
import com.rxy.netlib.http.ApiResponse;

import java.util.ArrayList;

/**
 * @描述:我的数据
 * @创建人：zhangpeisen
 * @创建时间：2018/1/3 上午11:54
 * @修改人：zhangpeisen
 * @修改时间：2018/1/3 上午11:54
 * @修改备注：
 * @throws
 */
public class IMinePrenter extends IPresenter<IMineView> {

    private IMineModel mineModel;

    public IMinePrenter() {

        mineModel = new IMineModel();
    }

    /**
     * @throws
     * @描述:
     * @创建人：zhangpeisen
     * @创建时间：2018/1/3 上午11:57
     * @修改人：zhangpeisen
     * @修改时间：2018/1/3 上午11:57
     * @修改备注：
     */
    public void merchcore() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        mineModel.merchcore(new IModelImpl<ApiResponse<O2oMineBean>, O2oMineBean>() {
            @Override
            protected void onSuccess(O2oMineBean data, String message) {
                getView().hideDialog();
                getView().onSuccess(data);
            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<O2oMineBean>> data, String message) {

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

    /**
     * @throws
     * @描述:获取地图经纬度
     * @创建人：zhangpeisen
     * @创建时间：2018/1/9 下午1:50
     * @修改人：zhangpeisen
     * @修改时间：2018/1/9 下午1:50
     * @修改备注：
     */
    public void getMaps() {

        if (!NetWorkUtils.isNetworkAvailable()) {
            getView().onError("网络信号弱,请稍后重试");
            return;
        }
        getView().showDialog();
        mineModel.settingGetMap(new IModelImpl<ApiResponse<String>, String>() {
            @Override
            protected void onSuccess(String data, String message) {
                getView().hideDialog();

            }

            @Override
            protected void onSuccess(ArrayList<ApiResponse<String>> data, String message) {
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
