package com.huafan.huafano2omanger.view.fragment.shop.shopunit;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.ShopUnitBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.ShopUnitService;
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
 * 商品单位数据层
 */

public class IShopUnitModel implements IModel{
    private Retrofit mRetrofit;
    private CompositeDisposable compositeDisposable;

    public IShopUnitModel() {

        mRetrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //门店商品单位查询商品单位方法
    public void selectShopUnit(final IModelImpl<ApiResponse<ShopUnitBean>, ShopUnitBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        String sign = SignUtil.getInstance().getSign(map);
        ShopUnitService service = mRetrofit.create(ShopUnitService.class);
        Disposable disposable = service.selectShopUnit(sign,Constants.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<ShopUnitBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<ShopUnitBean> shopUnitBeanApiResponse) throws Exception {
                        listener.onComplete(shopUnitBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    //门店商品单位增加商品单位方法
    public void addShopUnit(String unit,final IModelImpl<ApiResponse<ShopUnitBean>, ShopUnitBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //单元名
        map.put("unit", unit);

        HashMap<String, String> param = new HashMap<>();
        param.put("unit", unit);

        String sign = SignUtil.getInstance().getSign(map);
        ShopUnitService service = mRetrofit.create(ShopUnitService.class);
        Disposable subscribe = service.addShopUnit(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<ShopUnitBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<ShopUnitBean> shopUnitBeanApiResponse) throws Exception {
                        listener.onComplete(shopUnitBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);

    }

    //门店商品单位删除商品单位方法
    public void deleteShopUnit(int unit_id, IModelImpl<ApiResponse<ShopUnitBean>, ShopUnitBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //单元ID
        map.put("unit_id", String.valueOf(unit_id));

        HashMap<String, String> param = new HashMap<>();
        param.put("unit_id", String.valueOf(unit_id));

        String sign = SignUtil.getInstance().getSign(map);
        ShopUnitService service = mRetrofit.create(ShopUnitService.class);
        Disposable subscribe = service.deleteShopUnit(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<ShopUnitBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<ShopUnitBean> shopUnitBeanApiResponse) throws Exception {
                        listener.onComplete(shopUnitBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //门店商品单位修改商品单位方法
    public void updateShopUnit(int unit_id, String unit,IModelImpl<ApiResponse<ShopUnitBean>, ShopUnitBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //单元ID
        map.put("unit_id", String.valueOf(unit_id));
        //单元名
        map.put("unit", unit);

        HashMap<String, String> param = new HashMap<>();
        param.put("unit_id", String.valueOf(unit_id));
        param.put("unit", unit);

        String sign = SignUtil.getInstance().getSign(map);
        ShopUnitService service = mRetrofit.create(ShopUnitService.class);
        Disposable subscribe = service.updateShopUnit(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<ShopUnitBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<ShopUnitBean> shopUnitBeanApiResponse) throws Exception {
                        listener.onComplete(shopUnitBeanApiResponse);
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
