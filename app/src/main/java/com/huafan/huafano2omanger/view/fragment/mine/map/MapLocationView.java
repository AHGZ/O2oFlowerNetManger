package com.huafan.huafano2omanger.view.fragment.mine.map;

import com.huafan.huafano2omanger.entity.MapEntity;

/**
 * @描述:地图定位视图类
 * @创建人：zhangpeisen
 * @创建时间：2017/10/30 下午5:38
 * @修改人：zhangpeisen
 * @修改时间：2017/10/30 下午5:38
 * @修改备注：
 * @throws
 */

public interface MapLocationView {
    String longitude();

    String latitude();

    void showDialog();

    void hideDialog();

    void onError(String errorMsg);

    void onSuccess(MapEntity mapEntity);
}
