package com.huafan.huafano2omanger.view.fragment.shop.shop_refund;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by caishen on 2018/1/10.
 * by--退款管理
 */

public class ShopRefundMangerActivity extends KActivity {
    @Override
    protected KFragment getFirstFragment() {

        return ShopRefundMangerFragment.newIntence();
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
