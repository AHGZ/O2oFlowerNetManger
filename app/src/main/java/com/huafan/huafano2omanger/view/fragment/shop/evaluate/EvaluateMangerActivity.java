package com.huafan.huafano2omanger.view.fragment.shop.evaluate;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by caishen on 2017/12/26.
 * by--评价管理
 */

public class EvaluateMangerActivity extends KActivity {
    @Override
    protected KFragment getFirstFragment() {

        return EvaluateMangerFragment.newIntence();
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
