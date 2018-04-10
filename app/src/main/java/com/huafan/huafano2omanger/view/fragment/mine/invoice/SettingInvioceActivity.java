package com.huafan.huafano2omanger.view.fragment.mine.invoice;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by caishen on 2018/1/8.
 * by--发票设置
 */

public class SettingInvioceActivity extends KActivity{
    @Override
    protected KFragment getFirstFragment() {
        return SettingInvioceFragment.newItence();
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
