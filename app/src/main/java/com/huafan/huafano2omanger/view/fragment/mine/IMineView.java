package com.huafan.huafano2omanger.view.fragment.mine;

import com.huafan.huafano2omanger.entity.O2oMineBean;

/**
 * @描述:我的视图层
 * @创建人：zhangpeisen
 * @创建时间：2018/1/3 上午11:55
 * @修改人：zhangpeisen
 * @修改时间：2018/1/3 上午11:55
 * @修改备注：
 * @throws
 */
public interface IMineView {

    void showDialog();

    void hideDialog();

    void onError(String errorMsg);

    void onSuccess(O2oMineBean o2oMineBean);
}
