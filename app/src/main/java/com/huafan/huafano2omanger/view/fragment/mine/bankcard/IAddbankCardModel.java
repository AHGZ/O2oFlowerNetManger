package com.huafan.huafano2omanger.view.fragment.mine.bankcard;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.BindCardBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
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
 * Created by caishen on 2018/1/30.
 * by--
 */

class IAddbankCardModel implements IModel {

    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;

    @Override
    public void cancel() {

        if (compositeDisposable != null) {

            compositeDisposable.dispose();
        }
    }

    public IAddbankCardModel() {

        //初始化网络请求
        retrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //获取绑卡人信息
    public void go_bind_card(IModelImpl<ApiResponse<BindCardBean>, BindCardBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        String sign = SignUtil.getInstance().getSign(map);
        ShopService codeService = retrofit.create(ShopService.class);
        Disposable subscribe = codeService.go_bind_card(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<BindCardBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<BindCardBean> checkMobileBeanApiResponse) throws Exception {
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

    //绑定银行卡
    public void add_card(String name, String idNum, String card_num, String bank_name,
                         IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        map.put("idnum",idNum);
        map.put("card_num",card_num);
        map.put("bank_name",bank_name);

        HashMap<String, String> param = new HashMap<>();
        param.put("name",name);
        param.put("idnum",idNum);
        param.put("card_num",card_num);
        param.put("bank_name",bank_name);

        String sign = SignUtil.getInstance().getSign(map);
        ShopService codeService = retrofit.create(ShopService.class);
        Disposable subscribe = codeService.add_card(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<ReplyBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<ReplyBean> checkMobileBeanApiResponse) throws Exception {
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
}
