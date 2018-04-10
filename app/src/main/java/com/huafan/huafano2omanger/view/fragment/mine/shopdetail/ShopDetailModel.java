package com.huafan.huafano2omanger.view.fragment.mine.shopdetail;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.entity.ShopDetailBean;
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
 * Created by caishen on 2018/1/20.
 * by--
 */

public class ShopDetailModel implements IModel {

    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;

    @Override
    public void cancel() {

        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public ShopDetailModel() {

        //初始化网络请求
        retrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }


    //获取店铺信息
    public void getShopDetail(IModelImpl<ApiResponse<ShopDetailBean>, ShopDetailBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();

        String sign = SignUtil.getInstance().getSign(map);
        MineService codeService = retrofit.create(MineService.class);
        Disposable subscribe = codeService.getShopDetail(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<ShopDetailBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<ShopDetailBean> sendCodeBeanApiResponse) throws Exception {
                        listener.onComplete(sendCodeBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //修改店铺信息
    public void up_merchant_info(String getfilePath, String manager_name,
                                 String manager_tel, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("file_path", getfilePath);
        map.put("manager_name", manager_name);
        map.put("manager_tel", manager_tel);

        HashMap<String, String> param = new HashMap<>();
        param.put("file_path", getfilePath);
        param.put("manager_name", manager_name);
        param.put("manager_tel", manager_tel);

        String sign = SignUtil.getInstance().getSign(map);
        MineService codeService = retrofit.create(MineService.class);
        Disposable subscribe = codeService.up_merchant_info(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<ReplyBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<ReplyBean> sendCodeBeanApiResponse) throws Exception {
                        listener.onComplete(sendCodeBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);

    }
}
