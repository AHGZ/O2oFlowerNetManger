package com.huafan.huafano2omanger.view.fragment.mine.updatepwd;


import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.LoginService;
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
 * Created by caishen on 2017/11/18.
 * by--修改登录密码的数据层
 */

public class UpdateLoginPwdModel implements IModel {

    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;

    @Override
    public void cancel() {
        if (compositeDisposable != null) {

            compositeDisposable.dispose();
        }
    }

    public UpdateLoginPwdModel() {

        //初始化网络请求
        retrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //修改登录密码
    public void updateUserPwd(String aPwd, String bPwd, String cPwd, IModelImpl<ApiResponse<String>,
            String> iModel) {

        HashMap<String, String> map = new HashMap<>();
        map.put("origin_pwd", aPwd);
        map.put("new_pwd", bPwd);
        map.put("confirm_pwd", cPwd);
        String sign = SignUtil.getInstance().getSign(map);
        LoginService codeService = retrofit.create(LoginService.class);
        Disposable subscribe = codeService.updateLoginPwd(sign, Constants.TOKEN, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<String>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<String> sendCodeBeanApiResponse) throws Exception {
                        iModel.onComplete(sendCodeBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iModel.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }
}
