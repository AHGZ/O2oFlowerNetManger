package com.huafan.huafano2omanger.view.fragment.shop.fullmanagement;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.view.fragment.shop.shopunit.ShopUnitFragment;

/**
 * Created by heguozhong on 2017/12/26/026.
 * 满返管理主页面
 */

public class FullManagementActivity extends KActivity{
    @Override
    protected KFragment getFirstFragment() {
        return FullManagementFragment.newInstance();
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
