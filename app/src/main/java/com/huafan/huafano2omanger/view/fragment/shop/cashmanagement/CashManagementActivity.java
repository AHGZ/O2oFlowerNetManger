package com.huafan.huafano2omanger.view.fragment.shop.cashmanagement;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by heguozhong on 2017/12/26/026.
 * 提现管理主页面
 */

public class CashManagementActivity extends KActivity {

    @Override
    protected KFragment getFirstFragment() {
        return CashManagementFragment.newInstance();
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
