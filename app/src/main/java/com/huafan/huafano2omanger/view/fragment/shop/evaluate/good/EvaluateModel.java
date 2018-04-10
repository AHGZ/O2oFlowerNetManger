package com.huafan.huafano2omanger.view.fragment.shop.evaluate.good;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.EvaluateBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.EvaluateService;
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

public class EvaluateModel implements IModel {

    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;

    @Override
    public void cancel() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }


    public EvaluateModel() {

        //初始化网络请求
        retrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //获取订单评价
    public void getEvaluate(int page, String state, IModelImpl<ApiResponse<EvaluateBean>, EvaluateBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("state", String.valueOf(state));
        param.put("page", String.valueOf(page));

        map.put("state", String.valueOf(state));
        map.put("page", String.valueOf(page));

        String sign = SignUtil.getInstance().getSign(map);
        EvaluateService registerService = retrofit.create(EvaluateService.class);
        Disposable subscribe = registerService.getEvaluate(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<EvaluateBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<EvaluateBean> sendCodeBeanApiResponse) throws Exception {
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

    //回复商家评价
    public void replyEva(String reply, String eval_id, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("reply", reply);
        param.put("eval_id", eval_id);

        map.put("reply", reply);
        map.put("eval_id", eval_id);

        String sign = SignUtil.getInstance().getSign(map);
        EvaluateService registerService = retrofit.create(EvaluateService.class);
        Disposable subscribe = registerService.replyEvaluate(sign, Constants.TOKEN, param)
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

    //获取商家评价列表数据
    public void getMerticEvaluate(int page, String state, IModelImpl<ApiResponse<EvaluateBean>, EvaluateBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("state", String.valueOf(state));
        param.put("page", String.valueOf(page));

        map.put("state", String.valueOf(state));
        map.put("page", String.valueOf(page));

        String sign = SignUtil.getInstance().getSign(map);
        EvaluateService registerService = retrofit.create(EvaluateService.class);
        Disposable subscribe = registerService.getMerticEvaluate(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<EvaluateBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<EvaluateBean> sendCodeBeanApiResponse) throws Exception {
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

    //回复商家评价
    public void replyMerticEva(String reply, String eval_id, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        param.put("reply", reply);
        param.put("eval_id", eval_id);

        map.put("reply", reply);
        map.put("eval_id", eval_id);

        String sign = SignUtil.getInstance().getSign(map);
        EvaluateService registerService = retrofit.create(EvaluateService.class);
        Disposable subscribe = registerService.replyMerticEvaluate(sign, Constants.TOKEN, param)
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
