package com.huafan.huafano2omanger.view.fragment.shop.messagemanagement;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.MessaTypeBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.MessageService;
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

public class IMessageModel implements IModel {

    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;

    @Override
    public void cancel() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public IMessageModel() {

        //初始化网络请求
        retrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //获取指定类型消息
    public void getNoticeList(String type, int page, String receiver, IModelImpl<ApiResponse<MessaTypeBean>, MessaTypeBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("receiver", receiver);
        map.put("page", String.valueOf(page));

        HashMap<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("receiver", receiver);
        param.put("page", String.valueOf(page));

        String sign = SignUtil.getInstance().getSign(map);
        MessageService codeService = retrofit.create(MessageService.class);
        Disposable subscribe = codeService.getNoticeList(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<MessaTypeBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<MessaTypeBean> checkMobileBeanApiResponse) throws Exception {
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
