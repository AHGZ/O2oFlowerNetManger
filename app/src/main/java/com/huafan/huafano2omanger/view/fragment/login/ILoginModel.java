package com.huafan.huafano2omanger.view.fragment.login;


import com.huafan.huafano2omanger.base.BaseApplication;
import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.UserInfo;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.LoginService;
import com.huafan.huafano2omanger.utils.DeviceUtil;
import com.huafan.huafano2omanger.utils.MD5Utils;
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
 * @描述: 数据访问层
 * @创建人：zhangpeisen
 * @创建时间：2017/10/11 上午9:44
 * @修改人：zhangpeisen
 * @修改时间：2017/10/11 上午9:44
 * @修改备注：
 * @throws
 */

public class ILoginModel implements IModel {

    private Retrofit mRetrofit;
    private CompositeDisposable compositeDisposable;

    public ILoginModel() {

        mRetrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }


    public void login(String un, String pwd, final IModelImpl<ApiResponse<UserInfo>, UserInfo> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        map.put("un", un);
        map.put("pwd", MD5Utils.MD5To32(pwd));
        map.put("last_login_ip", DeviceUtil.getIPAddress(BaseApplication.getContext()));
        map.put("location", Constants.LOCATION);

        param.put("un", un);
        param.put("pwd", MD5Utils.MD5To32(pwd));
        param.put("last_login_ip", DeviceUtil.getIPAddress(BaseApplication.getContext()));
        param.put("location", Constants.LOCATION);
        String sign = SignUtil.getInstance().getSign(map);
        LoginService service = mRetrofit.create(LoginService.class);
        Disposable disposable = service.login(sign,"", param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<UserInfo>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<UserInfo> formalUserInfoApiResponse) throws Exception {
                        listener.onComplete(formalUserInfoApiResponse);
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
