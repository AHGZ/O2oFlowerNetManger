package com.huafan.huafano2omanger.view.fragment.mine;


import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.MapEntity;
import com.huafan.huafano2omanger.entity.O2oMineBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.MineService;
import com.huafan.huafano2omanger.utils.SignUtil;
import com.rxy.netlib.http.ApiResponse;
import com.rxy.netlib.http.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @描述: 我的数据访问层
 * @创建人：zhangpeisen
 * @创建时间：2017/10/11 上午9:44
 * @修改人：zhangpeisen
 * @修改时间：2017/10/11 上午9:44
 * @修改备注：
 * @throws
 */
public class IMineModel implements IModel {

    private Retrofit mRetrofit;
    private CompositeDisposable compositeDisposable;

    public IMineModel() {

        mRetrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * @throws
     * @描述:个人中心
     * @方法名: merchcore
     * @返回类型void
     * @创建人 zhangpeisen
     * @创建时间 2018/1/3 上午11:52
     * @修改人 zhangpeisen
     * @修改时间 2018/1/3 上午11:52
     * @修改备注
     */
    public void merchcore(final IModelImpl<ApiResponse<O2oMineBean>, O2oMineBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        String sign = SignUtil.getInstance().getSign(map);
        MineService service = mRetrofit.create(MineService.class);
        Disposable disposable = service.merchcore(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<O2oMineBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<O2oMineBean> formalUserInfoApiResponse) throws Exception {
                        listener.onComplete(formalUserInfoApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * @throws
     * @描述: 更改地图设置
     * @创建人：zhangpeisen
     * @创建时间：2018/1/8 上午10:54
     * @修改人：zhangpeisen
     * @修改时间：2018/1/8 上午10:54
     * @修改备注：
     */
    public void settingUpMap(String longitude, String latitude, final IModelImpl<ApiResponse<MapEntity>, MapEntity> listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        HashMap<String, String> param = new HashMap<>();
        param.put("longitude", longitude);
        param.put("latitude", latitude);
        String sign = SignUtil.getInstance().getSign(map);
        MineService service = mRetrofit.create(MineService.class);
        Disposable disposable = service.settingupmap(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<MapEntity>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<MapEntity> formalUserInfoApiResponse) throws Exception {
                        listener.onComplete(formalUserInfoApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    /**
     * @throws
     * @描述:获取地图设置
     * @创建人：zhangpeisen
     * @创建时间：2018/1/8 上午10:56
     * @修改人：zhangpeisen
     * @修改时间：2018/1/8 上午10:56
     * @修改备注：
     */
    public void settingGetMap(final IModelImpl<ApiResponse<String>, String> listener) {
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        String sign = SignUtil.getInstance().getSign(map);
        MineService service = mRetrofit.create(MineService.class);
        Disposable disposable = service.settinggetmap(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<String>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<String> formalUserInfoApiResponse) throws Exception {
                        listener.onComplete(formalUserInfoApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void cancel() {
        if (compositeDisposable != null)
            compositeDisposable.dispose();
    }
}
