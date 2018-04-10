package com.huafan.huafano2omanger.view.fragment.shop.cashmanagement.withdraw;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.WithDrawInfoBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.CashManagementService;
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
 * @描述: 用户资产数据层
 * @创建人：zhangpeisen
 * @创建时间：2017/12/23 上午9:45
 * @修改人：zhangpeisen
 * @修改时间：2017/12/23 上午9:45
 * @修改备注：
 * @throws
 */
public class IWithDrawModel implements IModel {

    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;


    public IWithDrawModel() {

        //初始化网络请求
        retrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }


     //查看提现
    public void selwithdrawals(final IModelImpl<ApiResponse<WithDrawInfoBean>, WithDrawInfoBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        String sign = SignUtil.getInstance().getSign(map);
        CashManagementService  cashManagementService = retrofit.create(CashManagementService.class);
        Disposable subscribe = cashManagementService.selwithdrawals(sign, Constants.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<WithDrawInfoBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<WithDrawInfoBean> checkMobileBeanApiResponse) throws Exception {
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

    /**
     * @描述:提现到银行卡
     */
    public void withdrawalsToBankCard(String balance, final IModelImpl<ApiResponse<String>, String> listener) {

        HashMap<String, String> map = new HashMap<>();
        map.put("balance", balance);
        HashMap<String, String> param = new HashMap<>();
        param.put("balance", balance);
        String sign = SignUtil.getInstance().getSign(map);
        CashManagementService bankcardService = retrofit.create(CashManagementService.class);
        Disposable subscribe = bankcardService.withdrawalsToBankCard(sign, Constants.TOKEN, param)
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
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
