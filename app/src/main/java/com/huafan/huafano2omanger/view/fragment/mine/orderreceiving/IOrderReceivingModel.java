package com.huafan.huafano2omanger.view.fragment.mine.orderreceiving;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.OrderReceivingBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.OrderReceivingService;
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
 * 接单设置数据层
 */

public class IOrderReceivingModel implements IModel {
    private Retrofit mRetrofit;
    private CompositeDisposable compositeDisposable;

    public IOrderReceivingModel() {

        mRetrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }
    //获取接单设置
    public void getOrderReceiving(int type,final IModelImpl<ApiResponse<OrderReceivingBean>, OrderReceivingBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //请求类型（2，为接单设置）
        map.put("type", type+"");

        HashMap<String, String> param = new HashMap<>();
        param.put("type", type+"");

        String sign = SignUtil.getInstance().getSign(map);
        OrderReceivingService service = mRetrofit.create(OrderReceivingService.class);
        Disposable subscribe = service.getOrderReceiving(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<OrderReceivingBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<OrderReceivingBean> orderReceivingBeanApiResponse) throws Exception {
                        listener.onComplete(orderReceivingBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);

    }

    //更改发票设置
    public void changeInvoiceReceiving(int type, int state,String quota,
                                     IModelImpl<ApiResponse<OrderReceivingBean>, OrderReceivingBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //请求类型（2，为接单设置）
        map.put("type", String.valueOf(type));
        //设置状态
        map.put("state", String.valueOf(state));
        //设置开票金额
        map.put("quota", quota);

        HashMap<String, String> param = new HashMap<>();
        param.put("type", String.valueOf(type));
        param.put("state", String.valueOf(state));
        param.put("quota", quota);

        String sign = SignUtil.getInstance().getSign(map);
        OrderReceivingService service = mRetrofit.create(OrderReceivingService.class);
        Disposable subscribe = service.changeOrderReceiving(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<OrderReceivingBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<OrderReceivingBean> orderReceivingBeanApiResponse) throws Exception {
                        listener.onComplete(orderReceivingBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //更改接单、消息设置
    public void changeOrderMessageReceiving(int type, int state,
                                     IModelImpl<ApiResponse<OrderReceivingBean>, OrderReceivingBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //请求类型（2，为接单设置）
        map.put("type", String.valueOf(type));
        //设置状态
        map.put("state", String.valueOf(state));

        HashMap<String, String> param = new HashMap<>();
        param.put("type", String.valueOf(type));
        param.put("state", String.valueOf(state));

        String sign = SignUtil.getInstance().getSign(map);
        OrderReceivingService service = mRetrofit.create(OrderReceivingService.class);
        Disposable subscribe = service.changeOrderReceiving(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<OrderReceivingBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<OrderReceivingBean> orderReceivingBeanApiResponse) throws Exception {
                        listener.onComplete(orderReceivingBeanApiResponse);
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
