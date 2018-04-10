package com.huafan.huafano2omanger.view.fragment.mine.shopdetail;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by caishen on 2018/1/20.
 * by--店铺详情页面
 */

public class ShopDetailActivity extends KActivity {
    @Override
    protected KFragment getFirstFragment() {

        return ShopDetailFragment.newIntence();
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
