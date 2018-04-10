package com.huafan.huafano2omanger.view.fragment.pending.waitdispose;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.entity.SelfMotionBean;
import com.huafan.huafano2omanger.entity.WaitDisposeBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.WaitDisposeService;
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
 * Created by caishen on 2018/1/9.
 * by--
 */

public class WaitDisposeModel implements IModel {

    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;

    @Override
    public void cancel() {

        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public WaitDisposeModel() {

        //初始化网络请求
        retrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();

    }

    //获取待处理订单
    public void getWaitDis(String state, String page, IModelImpl<ApiResponse<WaitDisposeBean>, WaitDisposeBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("state", state);
        param.put("page", page);

        map.put("state", state);
        map.put("page", page);

        String sign = SignUtil.getInstance().getSign(map);
        WaitDisposeService registerService = retrofit.create(WaitDisposeService.class);
        Disposable subscribe = registerService.getWaitDis(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<WaitDisposeBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<WaitDisposeBean> sendCodeBeanApiResponse) throws Exception {
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

    //拒绝接单
    public void refuseOrder(String orderId, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("order_id", orderId);
        map.put("order_id", orderId);

        String sign = SignUtil.getInstance().getSign(map);
        WaitDisposeService registerService = retrofit.create(WaitDisposeService.class);
        Disposable subscribe = registerService.refuseOrder(sign, Constants.TOKEN, param)
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

    //确认接单
    public void confirmOrder(String orderId, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("order_id", orderId);
        map.put("order_id", orderId);

        String sign = SignUtil.getInstance().getSign(map);
        WaitDisposeService registerService = retrofit.create(WaitDisposeService.class);
        Disposable subscribe = registerService.confirmOrder(sign, Constants.TOKEN, param)
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

    //取消订单
    public void cancelOrder(String orderId, String reason, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("order_id", orderId);
        param.put("reason", reason);
        map.put("order_id", orderId);
        map.put("reason", reason);

        String sign = SignUtil.getInstance().getSign(map);
        WaitDisposeService registerService = retrofit.create(WaitDisposeService.class);
        Disposable subscribe = registerService.cancelOrder(sign, Constants.TOKEN, param)
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

    //确认收货
    public void confirmCom(String orderId, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("order_id", orderId);
        map.put("order_id", orderId);

        String sign = SignUtil.getInstance().getSign(map);
        WaitDisposeService registerService = retrofit.create(WaitDisposeService.class);
        Disposable subscribe = registerService.confirmCom(sign, Constants.TOKEN, param)
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

    //获取自动接单数据
    public void getselOrderInfo(String orderId, IModelImpl<ApiResponse<SelfMotionBean>, SelfMotionBean> listener) {


        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("order_num", orderId);
        map.put("order_num", orderId);

        String sign = SignUtil.getInstance().getSign(map);
        WaitDisposeService registerService = retrofit.create(WaitDisposeService.class);
        Disposable subscribe = registerService.getselOrderInfo(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<SelfMotionBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<SelfMotionBean> sendCodeBeanApiResponse) throws Exception {
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
