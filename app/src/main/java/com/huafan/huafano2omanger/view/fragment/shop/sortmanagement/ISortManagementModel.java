package com.huafan.huafano2omanger.view.fragment.shop.sortmanagement;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.SortManagementBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.SortManagementService;
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
 * Created by heguozhong on 2017/12/29/029.
 * 分类管理数据层
 */

public class ISortManagementModel implements IModel {
    private Retrofit mRetrofit;
    private CompositeDisposable compositeDisposable;

    public ISortManagementModel() {

        mRetrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //门店分类管理查询分类方法
    public void selectSortShop(IModelImpl<ApiResponse<SortManagementBean>, SortManagementBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        String sign = SignUtil.getInstance().getSign(map);
        SortManagementService service = mRetrofit.create(SortManagementService.class);
        Disposable disposable = service.selectSortShop(sign, Constants.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<SortManagementBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<SortManagementBean> sortManagementBeanApiResponse) throws Exception {
                        listener.onComplete(sortManagementBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    //门店分类管理增加分类方法
    public void addSortShop(String name,int sort,IModelImpl<ApiResponse<SortManagementBean>, SortManagementBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //单元名
        map.put("name", name);
        //排序
        map.put("sort", sort+"");

        HashMap<String, String> param = new HashMap<>();
        param.put("name", name);
        param.put("sort", sort+"");

        String sign = SignUtil.getInstance().getSign(map);
        SortManagementService service = mRetrofit.create(SortManagementService.class);
        Disposable subscribe = service.addSortShop(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<SortManagementBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<SortManagementBean> sortManagementBeanApiResponse) throws Exception {
                        listener.onComplete(sortManagementBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);

    }

    //门店分类管理删除分类方法
    public void deleteSortShop(int cate_id, IModelImpl<ApiResponse<SortManagementBean>, SortManagementBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //单元ID
        map.put("cate_id", String.valueOf(cate_id));

        HashMap<String, String> param = new HashMap<>();
        param.put("cate_id", String.valueOf(cate_id));

        String sign = SignUtil.getInstance().getSign(map);
        SortManagementService service = mRetrofit.create(SortManagementService.class);
        Disposable subscribe = service.deleteSortShop(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<SortManagementBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<SortManagementBean> sortManagementBeanApiResponse) throws Exception {
                        listener.onComplete(sortManagementBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //门店分类管理修改分类方法
    public void updateSortShop(int cate_id, String name, int sort, IModelImpl<ApiResponse<SortManagementBean>, SortManagementBean> listener) {
        HashMap<String, String> map = new HashMap<>();
        //分类ID
        map.put("cate_id", String.valueOf(cate_id));
        //分类名
        map.put("name", name);
        //排序
        map.put("sort", String.valueOf(sort));

        HashMap<String, String> param = new HashMap<>();
        param.put("cate_id", String.valueOf(cate_id));
        param.put("name", name);
        param.put("sort", String.valueOf(sort));

        String sign = SignUtil.getInstance().getSign(map);
        SortManagementService service = mRetrofit.create(SortManagementService.class);
        Disposable subscribe = service.updateSortShop(sign,Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<SortManagementBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<SortManagementBean> sortManagementBeanApiResponse) throws Exception {
                        listener.onComplete(sortManagementBeanApiResponse);
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
