package com.huafan.huafano2omanger.view.fragment.mine.dobusiness;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.DobusinessBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.DobusinessService;
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
 * Created by heguozhong on 2018/1/2/002.
 * 营业设置数据层
 */

public class IDoBusinessSettingsModel implements IModel {
    private Retrofit mRetrofit;
    private CompositeDisposable compositeDisposable;

    public IDoBusinessSettingsModel() {

        mRetrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //获取营业设置
    public void getDobusiness(IModelImpl<ApiResponse<DobusinessBean>, DobusinessBean> listener) {
        HashMap<String, String> map = new HashMap<>();

        String sign = SignUtil.getInstance().getSign(map);
        DobusinessService service = mRetrofit.create(DobusinessService.class);
        Disposable subscribe = service.getDobusiness(sign, Constants.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<DobusinessBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<DobusinessBean> dobusinessBeanApiResponse) throws Exception {
                        listener.onComplete(dobusinessBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);

    }

    //修改营业设置
    public void updateDobusiness(int state, IModelImpl<ApiResponse<DobusinessBean>, DobusinessBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //营业状态
        map.put("state", String.valueOf(state));

        HashMap<String, String> param = new HashMap<>();
        param.put("state", String.valueOf(state));

        String sign = SignUtil.getInstance().getSign(map);
        DobusinessService service = mRetrofit.create(DobusinessService.class);
        Disposable subscribe = service.updateDobusiness(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<DobusinessBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<DobusinessBean> dobusinessBeanApiResponse) throws Exception {
                        listener.onComplete(dobusinessBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //添加营业时间
    public void addDobusinessTime(String starttime, String endtime, float distrib_money, IModelImpl<ApiResponse<DobusinessBean>, DobusinessBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //开始时间
        map.put("starttime", starttime);
        //结束时间
        map.put("endtime", endtime);
        //商家配送价格
        map.put("distrib_money", String.valueOf(distrib_money));

        HashMap<String, String> param = new HashMap<>();
        param.put("starttime", starttime);
        param.put("endtime", endtime);
        param.put("distrib_money", String.valueOf(distrib_money));

        String sign = SignUtil.getInstance().getSign(map);
        DobusinessService service = mRetrofit.create(DobusinessService.class);
        Disposable subscribe = service.addDobusinessTime(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<DobusinessBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<DobusinessBean> dobusinessBeanApiResponse) throws Exception {
                        listener.onComplete(dobusinessBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //删除营业时间
    public void deleteDobusinessTime(int business_id, IModelImpl<ApiResponse<DobusinessBean>, DobusinessBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //营业时间ID
        map.put("business_id", String.valueOf(business_id));

        HashMap<String, String> param = new HashMap<>();
        param.put("business_id", String.valueOf(business_id));

        String sign = SignUtil.getInstance().getSign(map);
        DobusinessService service = mRetrofit.create(DobusinessService.class);
        Disposable subscribe = service.deleteDobusinessTime(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<DobusinessBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<DobusinessBean> dobusinessBeanApiResponse) throws Exception {
                        listener.onComplete(dobusinessBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //修改营业时间
    public void updateDobusinessTime(int business_id, String starttime, String endtime, float distrib_money, IModelImpl<ApiResponse<DobusinessBean>, DobusinessBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //营业时间ID
        map.put("business_id", String.valueOf(business_id));
        //开始时间
        map.put("starttime", starttime);
        //结束时间
        map.put("endtime", endtime);
        //商家配送价格
        map.put("distrib_money", String.valueOf(distrib_money));

        HashMap<String, String> param = new HashMap<>();
        param.put("business_id", String.valueOf(business_id));
        param.put("starttime", starttime);
        param.put("endtime", endtime);
        param.put("distrib_money", String.valueOf(distrib_money));

        String sign = SignUtil.getInstance().getSign(map);
        DobusinessService service = mRetrofit.create(DobusinessService.class);
        Disposable subscribe = service.updateDobusinessTime(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<DobusinessBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<DobusinessBean> dobusinessBeanApiResponse) throws Exception {
                        listener.onComplete(dobusinessBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    @Override
    public void cancel() {
        if (compositeDisposable != null)
            compositeDisposable.dispose();
    }
}
