package com.huafan.huafano2omanger.view.fragment.shop.teamexamine;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.TeamExamineBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.TeamExamineSercice;
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
 * 团购核销数据层
 */

public class ITeamExamineModel implements IModel{
    private Retrofit mRetrofit;
    private CompositeDisposable compositeDisposable;

    public ITeamExamineModel() {

        mRetrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //提交团购码
    public void submitCode(String group_code,final IModelImpl<ApiResponse<TeamExamineBean>, TeamExamineBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        map.put("group_code",group_code+"");

        HashMap<String, String> param = new HashMap<>();
        param.put("group_code", group_code+"");
        String sign = SignUtil.getInstance().getSign(map);

        TeamExamineSercice service = mRetrofit.create(TeamExamineSercice.class);
        Disposable disposable = service.submitCode(sign,Constants.TOKEN,param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<TeamExamineBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<TeamExamineBean> teamExamineBeanApiResponse) throws Exception {
                        listener.onComplete(teamExamineBeanApiResponse);
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
