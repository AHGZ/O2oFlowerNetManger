package com.huafan.huafano2omanger.view.fragment.shop.cashmanagement;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.CashManagementBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.CashManagementService;
import com.huafan.huafano2omanger.service.ShopService;
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
 * 提现管理数据层
 */

public class ICashManagementModel implements IModel{
    private Retrofit mRetrofit;
    private CompositeDisposable compositeDisposable;

    public ICashManagementModel() {

        mRetrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //门店提现管理查询提现方法
    public void selectCash(final IModelImpl<ApiResponse<CashManagementBean>, CashManagementBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        String sign = SignUtil.getInstance().getSign(map);
        CashManagementService service = mRetrofit.create(CashManagementService.class);
        Disposable disposable = service.selectCash(sign,Constants.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<CashManagementBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<CashManagementBean> cashManagementBeanApiResponse) throws Exception {
                        listener.onComplete(cashManagementBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    //门店提现管理增加提现方法
    public void addCash(String balance,final IModelImpl<ApiResponse<CashManagementBean>, CashManagementBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //提现金额
        map.put("balance", balance);
        HashMap<String, String> param = new HashMap<>();
        param.put("balance", balance);

        String sign = SignUtil.getInstance().getSign(map);
        CashManagementService service = mRetrofit.create(CashManagementService.class);
        Disposable subscribe = service.addCash(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<CashManagementBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<CashManagementBean> cashManagementBeanApiResponse) throws Exception {
                        listener.onComplete(cashManagementBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);

    }

    //支付宝提现
    public void withdraw(String money, String alipayAccount, final IModelImpl<ApiResponse<String>, String> listener) {

        HashMap<String, String> map = new HashMap<>();
        map.put("money", money);
        map.put("alipayAccount", alipayAccount);

        HashMap<String, String> parma = new HashMap<>();
        parma.put("money", money);
        parma.put("alipayAccount", alipayAccount);

        String sign = SignUtil.getInstance().getSign(map);
        ShopService bankcardService = mRetrofit.create(ShopService.class);
        Disposable subscribe = bankcardService.withdraw(sign, Constants.TOKEN, parma)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<String>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<String> checkMobileBeanApiResponse) throws Exception {
                        listener.onComplete(checkMobileBeanApiResponse);
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
