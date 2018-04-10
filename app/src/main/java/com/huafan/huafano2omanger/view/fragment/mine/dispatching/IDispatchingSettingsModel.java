package com.huafan.huafano2omanger.view.fragment.mine.dispatching;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.DispatchingBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.DispatchingService;
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
 * 配送设置数据层
 */

public class IDispatchingSettingsModel implements IModel {
    private Retrofit mRetrofit;
    private CompositeDisposable compositeDisposable;

    public IDispatchingSettingsModel() {

        mRetrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }
    //获取配送设置
    public void getDispatching(final IModelImpl<ApiResponse<DispatchingBean>, DispatchingBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        String sign = SignUtil.getInstance().getSign(map);
        DispatchingService service = mRetrofit.create(DispatchingService.class);
        Disposable subscribe = service.getDispatching(sign,Constants.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<DispatchingBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<DispatchingBean> dispatchingBeanApiResponse) throws Exception {
                        listener.onComplete(dispatchingBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);

    }

    //更改配送设置
    public void changeDispatching(int distrib,float quota,int self_pick,int prepare_time, IModelImpl<ApiResponse<DispatchingBean>, DispatchingBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //请求类型（2，为接单设置）
        map.put("distrib", String.valueOf(distrib));
        map.put("quota", String.valueOf(quota));
        map.put("self_pick", String.valueOf(self_pick));
        map.put("prepare_time", String.valueOf(prepare_time));


        HashMap<String, String> param = new HashMap<>();
        param.put("distrib", String.valueOf(distrib));
        param.put("quota", String.valueOf(quota));
        param.put("self_pick", String.valueOf(self_pick));
        param.put("prepare_time", String.valueOf(prepare_time));

        String sign = SignUtil.getInstance().getSign(map);
        DispatchingService service = mRetrofit.create(DispatchingService.class);
        Disposable subscribe = service.changeDispatching(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<DispatchingBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<DispatchingBean> dispatchingBeanApiResponse) throws Exception {
                        listener.onComplete(dispatchingBeanApiResponse);
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
