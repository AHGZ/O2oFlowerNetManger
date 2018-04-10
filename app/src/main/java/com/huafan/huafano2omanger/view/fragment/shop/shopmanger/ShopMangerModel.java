package com.huafan.huafano2omanger.view.fragment.shop.shopmanger;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.GoodTopSortBean;
import com.huafan.huafano2omanger.entity.SelGoodsListBean;
import com.huafan.huafano2omanger.entity.ShopMangerBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.ShopService;
import com.huafan.huafano2omanger.utils.SignUtil;
import com.rxy.netlib.http.ApiResponse;
import com.rxy.netlib.http.RetrofitUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by caishen on 2017/12/25.
 * by--
 */

public class ShopMangerModel implements IModel {

    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;

    @Override
    public void cancel() {

        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public ShopMangerModel() {

        //初始化网络请求
        retrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * 商品管理数据
     */
    private static List<ShopMangerBean> shopList = new ArrayList<>();

    public static List<ShopMangerBean> ShopList() {
        for (int i = 0; i < 10; i++) {
            ShopMangerBean shopMangerBean = new ShopMangerBean();
            shopMangerBean.setName("新品双人套餐" + i);
            shopMangerBean.setInventory("250" + i);
            shopMangerBean.setId(i);
            shopList.add(shopMangerBean);
        }
        return shopList;
    }

    //获取商品列表数据
    public void getShopList(String cateId, int page, IModelImpl<ApiResponse<SelGoodsListBean>, SelGoodsListBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(page));
        map.put("cate_id", cateId);

        HashMap<String, String> param = new HashMap<>();
        param.put("page", String.valueOf(page));
        param.put("cate_id", cateId);
        String sign = SignUtil.getInstance().getSign(map);
        ShopService codeService = retrofit.create(ShopService.class);
        Disposable subscribe = codeService.getShopList(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<SelGoodsListBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<SelGoodsListBean> checkMobileBeanApiResponse) throws Exception {
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

    //删除商品
    public void delShop(String specId, IModelImpl<ApiResponse<String>, String> listener) {

        HashMap<String, String> map = new HashMap<>();
        map.put("goods_id", specId);

        HashMap<String, String> param = new HashMap<>();
        param.put("goods_id", specId);
        String sign = SignUtil.getInstance().getSign(map);
        ShopService codeService = retrofit.create(ShopService.class);
        Disposable subscribe = codeService.delShop(sign, Constants.TOKEN, param)
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

    //商品置顶排序
    public void topSortShop(int id, int position, IModelImpl<ApiResponse<GoodTopSortBean>, GoodTopSortBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        map.put("goods_id", String.valueOf(id));
        map.put("sort", String.valueOf(position));

        HashMap<String, String> param = new HashMap<>();
        param.put("goods_id", String.valueOf(id));
        param.put("sort", String.valueOf(position));
        String sign = SignUtil.getInstance().getSign(map);
        ShopService codeService = retrofit.create(ShopService.class);
        Disposable subscribe = codeService.topSortShop(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<GoodTopSortBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<GoodTopSortBean> checkMobileBeanApiResponse) throws Exception {
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

    //商品排序
    public void sortShop(int id, int id1, int targetPosition, int srcPosition, IModelImpl<ApiResponse<GoodTopSortBean>, GoodTopSortBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        map.put("goods_id1", String.valueOf(id));
        map.put("sort1", String.valueOf(targetPosition));
        map.put("goods_id2", String.valueOf(id1));
        map.put("sort2", String.valueOf(srcPosition));

        HashMap<String, String> param = new HashMap<>();
        param.put("goods_id1", String.valueOf(id));
        param.put("sort1", String.valueOf(targetPosition));
        param.put("goods_id2", String.valueOf(id1));
        param.put("sort2", String.valueOf(srcPosition));
        String sign = SignUtil.getInstance().getSign(map);
        ShopService codeService = retrofit.create(ShopService.class);
        Disposable subscribe = codeService.goodSwapSort(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<GoodTopSortBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<GoodTopSortBean> checkMobileBeanApiResponse) throws Exception {
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
