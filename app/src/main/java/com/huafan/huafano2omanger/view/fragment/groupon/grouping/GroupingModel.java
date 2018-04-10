package com.huafan.huafano2omanger.view.fragment.groupon.grouping;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.GroupOnBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.GroupSercice;
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
 * Created by caishen on 2018/1/15.
 * by--
 */

public class GroupingModel implements IModel {

    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;

    @Override
    public void cancel() {

        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public GroupingModel() {

        //初始化网络请求
        retrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }


    //获取团购列表数据
    public void getGroup(String state, int page, IModelImpl<ApiResponse<GroupOnBean>, GroupOnBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        map.put("state", state);
        map.put("page", String.valueOf(page));

        HashMap<String, String> param = new HashMap<>();
        param.put("state", state);
        param.put("page", String.valueOf(page));
        String sign = SignUtil.getInstance().getSign(map);
        GroupSercice codeService = retrofit.create(GroupSercice.class);
        Disposable subscribe = codeService.getGroup(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<GroupOnBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<GroupOnBean> checkMobileBeanApiResponse) throws Exception {
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

    //修改团购状态
    public void changeState(String group_id, String is_sale, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        map.put("group_id", group_id);
        map.put("is_sale", is_sale);

        HashMap<String, String> param = new HashMap<>();
        param.put("group_id", group_id);
        param.put("is_sale", is_sale);
        String sign = SignUtil.getInstance().getSign(map);
        GroupSercice codeService = retrofit.create(GroupSercice.class);
        Disposable subscribe = codeService.changeState(sign, Constants.TOKEN, param)
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
