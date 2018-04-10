package com.huafan.huafano2omanger.view.fragment.mine.bankcard;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by caishen on 2018/1/30.
 * by--添加银行卡页面
 */

public class AddbankCardActivity extends KActivity {
    @Override
    protected KFragment getFirstFragment() {

        return AddbankCardFragment.newIntence();
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
