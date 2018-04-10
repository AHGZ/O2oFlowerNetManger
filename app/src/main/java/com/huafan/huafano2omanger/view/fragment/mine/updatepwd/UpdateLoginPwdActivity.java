package com.huafan.huafano2omanger.view.fragment.mine.updatepwd;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;


/**
 * Created by caishen on 2017/11/18.
 * by--修改用户登录密码
 */

public class UpdateLoginPwdActivity extends KActivity {
    @Override
    protected KFragment getFirstFragment() {
        return UpdateLoginPwdFragment.newIntence();
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
