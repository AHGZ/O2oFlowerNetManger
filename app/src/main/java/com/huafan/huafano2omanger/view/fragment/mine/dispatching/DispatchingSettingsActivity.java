package com.huafan.huafano2omanger.view.fragment.mine.dispatching;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by heguozhong on 2017/12/21/021.
 * 配送设置主页面
 */

public class DispatchingSettingsActivity extends KActivity {

    @Override
    protected KFragment getFirstFragment() {
        return DispatchingSettingsFragment.newInstance();
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
