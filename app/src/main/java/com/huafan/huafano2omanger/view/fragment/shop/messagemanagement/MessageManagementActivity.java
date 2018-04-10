package com.huafan.huafano2omanger.view.fragment.shop.messagemanagement;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by heguozhong on 2017/12/27/027.
 * 消息管理主页面
 */

public class MessageManagementActivity extends KActivity {
    @Override
    protected KFragment getFirstFragment() {
        return MessageManagementFragment.newInstance();
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
