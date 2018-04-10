package com.huafan.huafano2omanger.view.fragment.pending.waitrebund;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.entity.WaitReFundBean;
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
 * Created by caishen on 2018/1/10.
 * by--
 */

public class IWaitRebundModel implements IModel {

    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;

    @Override
    public void cancel() {

        if (compositeDisposable != null) {

            compositeDisposable.dispose();
        }
    }

    public IWaitRebundModel() {

        //初始化网络请求
        retrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();

    }

    //获取退款列表数据
    public void getWaitRebund(String state, String page,
                              IModelImpl<ApiResponse<WaitReFundBean>, WaitReFundBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("state", state);
        param.put("page", page);

        map.put("state", state);
        map.put("page", page);

        String sign = SignUtil.getInstance().getSign(map);
        WaitDisposeService registerService = retrofit.create(WaitDisposeService.class);
        Disposable subscribe = registerService.getWaitRebund(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<WaitReFundBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<WaitReFundBean> sendCodeBeanApiResponse) throws Exception {
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

    //同意退款
    public void addirmRefund(String refundId, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("refund_id", refundId);
        map.put("refund_id", refundId);

        String sign = SignUtil.getInstance().getSign(map);
        WaitDisposeService registerService = retrofit.create(WaitDisposeService.class);
        Disposable subscribe = registerService.addirmRefund(sign, Constants.TOKEN, param)
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

    //拒绝退款
    public void refuseRefund(String refundId, String refuse_reason, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("refund_id", refundId);
        param.put("refuse_reason", refuse_reason);
        map.put("refund_id", refundId);
        map.put("refuse_reason", refuse_reason);

        String sign = SignUtil.getInstance().getSign(map);
        WaitDisposeService registerService = retrofit.create(WaitDisposeService.class);
        Disposable subscribe = registerService.refuseRefund(sign, Constants.TOKEN, param)
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
