package com.huafan.huafano2omanger.view.fragment.shop.shopunit;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by heguozhong on 2017/12/23.
 *商品单位主页面
 */

public class ShopUnitActivity extends KActivity{
    @Override
    protected KFragment getFirstFragment() {
        return ShopUnitFragment.newInstance();
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
