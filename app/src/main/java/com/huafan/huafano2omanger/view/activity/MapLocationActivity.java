package com.huafan.huafano2omanger.view.activity;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.view.fragment.mine.map.MapLocationFragment;


/**
 * @描述: 地图定位主页面
 * @创建人：zhangpeisen
 * @创建时间：2017/11/11 上午10:21
 * @修改人：zhangpeisen
 * @修改时间：2017/11/11 上午10:21
 * @修改备注：
 * @throws
 */
public class MapLocationActivity extends KActivity {

    @Override
    protected KFragment getFirstFragment() {
        return MapLocationFragment.newInstance();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            removeFragment();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
