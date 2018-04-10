package com.huafan.huafano2omanger.view.fragment.mine.bankcard;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by caishen on 2018/1/30.
 * by--银行卡信息
 */

public class BankCardActivity extends KActivity{
    @Override
    protected KFragment getFirstFragment() {

        return BankCardFragment.newIntence();
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
