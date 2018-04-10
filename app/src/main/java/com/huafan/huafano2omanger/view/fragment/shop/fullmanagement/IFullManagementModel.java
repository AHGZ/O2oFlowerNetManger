package com.huafan.huafano2omanger.view.fragment.shop.fullmanagement;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.FullManagementBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.FullManagementService;
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
 * Created by heguozhong on 2017/12/27/027.
 * 满返管理数据层
 */

public class IFullManagementModel implements IModel{
    private Retrofit mRetrofit;
    private CompositeDisposable compositeDisposable;

    public IFullManagementModel() {

        mRetrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //门店满返管理查询满返方法
    public void selectFullReturn(final IModelImpl<ApiResponse<FullManagementBean>, FullManagementBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        String sign = SignUtil.getInstance().getSign(map);
        FullManagementService service = mRetrofit.create(FullManagementService.class);
        Disposable disposable = service.selectFullReturn(sign,Constants.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<FullManagementBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<FullManagementBean> fullManagementBeanApiResponse) throws Exception {
                        listener.onComplete(fullManagementBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    //门店满返管理增加满返方法
    public void addFullReturn(String order_amount,String rebate,final IModelImpl<ApiResponse<FullManagementBean>, FullManagementBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //订单金额
        map.put("order_amount", order_amount);
        //返润额
        map.put("rebate", rebate);

        HashMap<String, String> param = new HashMap<>();
        //订单金额
        param.put("order_amount", order_amount);
        //返润额
        param.put("rebate", rebate);

        String sign = SignUtil.getInstance().getSign(map);
        FullManagementService service = mRetrofit.create(FullManagementService.class);
        Disposable subscribe = service.addFullReturn(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<FullManagementBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<FullManagementBean> fullManagementBeanApiResponse) throws Exception {
                        listener.onComplete(fullManagementBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);

    }

    //门店满返管理删除满返方法
    public void deleteFullReturn(int rebate_id, IModelImpl<ApiResponse<FullManagementBean>, FullManagementBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //满返id
        map.put("rebate_id", String.valueOf(rebate_id));

        HashMap<String, String> param = new HashMap<>();
        param.put("rebate_id", String.valueOf(rebate_id));

        String sign = SignUtil.getInstance().getSign(map);
        FullManagementService service = mRetrofit.create(FullManagementService.class);
        Disposable subscribe = service.deleteFullReturn(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<FullManagementBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<FullManagementBean> fullManagementBeanApiResponse) throws Exception {
                        listener.onComplete(fullManagementBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //门店满返管理修改满返方法
    public void updateFullReturn(int rebate_id,String order_amount, String rebate,IModelImpl<ApiResponse<FullManagementBean>, FullManagementBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //满返id
        map.put("rebate_id", String.valueOf(rebate_id));
        //订单金额
        map.put("order_amount", String.valueOf(order_amount));
        //返润额
        map.put("rebate", rebate);

        HashMap<String, String> param = new HashMap<>();
        param.put("rebate_id", String.valueOf(rebate_id));
        param.put("order_amount", order_amount);
        param.put("rebate", rebate);

        String sign = SignUtil.getInstance().getSign(map);
        FullManagementService service = mRetrofit.create(FullManagementService.class);
        Disposable subscribe = service.updateFullReturn(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<FullManagementBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<FullManagementBean> fullManagementBeanApiResponse) throws Exception {
                        listener.onComplete(fullManagementBeanApiResponse);
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
