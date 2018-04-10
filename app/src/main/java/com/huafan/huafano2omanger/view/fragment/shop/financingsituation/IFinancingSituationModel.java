package com.huafan.huafano2omanger.view.fragment.shop.financingsituation;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.FinancingDetailBean;
import com.huafan.huafano2omanger.entity.FinancingSituationBean;
import com.huafan.huafano2omanger.entity.FinancingTakeDetailBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.FinancingSituationService;
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
 * Created by heguozhong on 2017/12/25/025.
 * 财务概况数据层
 */

public class IFinancingSituationModel implements IModel {
    private Retrofit mRetrofit;
    private CompositeDisposable compositeDisposable;

    public IFinancingSituationModel() {

        mRetrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //门店查询财务概况方法
    public void selectFinancingSituation(int state,String starttime,String endtime,final IModelImpl<ApiResponse<FinancingSituationBean>, FinancingSituationBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        //状态
        map.put("state", state+"");
        //开始时间
        map.put("starttime", starttime);
        //结束时间
        map.put("starttime", starttime);
        HashMap<String, String> param = new HashMap<>();

        param.put("state", state+"");
        param.put("starttime", starttime);
        param.put("endtime", endtime);

        String sign = SignUtil.getInstance().getSign(map);
        FinancingSituationService service = mRetrofit.create(FinancingSituationService.class);
        Disposable disposable = service.selectFinancingSituation(sign,Constants.TOKEN,param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<FinancingSituationBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<FinancingSituationBean> financingDetailBeanApiResponse) throws Exception {
                        listener.onComplete(financingDetailBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    //门店财务概况查询团购明细方法
    public void selectGroupDetail(int pages,final IModelImpl<ApiResponse<FinancingDetailBean>, FinancingDetailBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        //页数
        map.put("pages", pages+"");
        HashMap<String, String> param = new HashMap<>();
        param.put("pages", pages+"");

        String sign = SignUtil.getInstance().getSign(map);
        FinancingSituationService service = mRetrofit.create(FinancingSituationService.class);
        Disposable disposable = service.selectGroupDetail(sign,Constants.TOKEN,param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<FinancingDetailBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<FinancingDetailBean> financingDetailBeanApiResponse) throws Exception {
                        listener.onComplete(financingDetailBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    //门店财务概况查询外卖明细方法
    public void selectTakeOutDetail(int pages,final IModelImpl<ApiResponse<FinancingTakeDetailBean>, FinancingTakeDetailBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        //页数
        map.put("pages", pages+"");
        HashMap<String, String> param = new HashMap<>();
        param.put("pages", pages+"");

        String sign = SignUtil.getInstance().getSign(map);
        FinancingSituationService service = mRetrofit.create(FinancingSituationService.class);
        Disposable disposable = service.selectTakeOutDetail(sign,Constants.TOKEN,param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<FinancingTakeDetailBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<FinancingTakeDetailBean> financingTakeDetailBeanApiResponse) throws Exception {
                        listener.onComplete(financingTakeDetailBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }


    @Override
    public void cancel() {
        if (compositeDisposable != null)
            compositeDisposable.dispose();
    }
}
