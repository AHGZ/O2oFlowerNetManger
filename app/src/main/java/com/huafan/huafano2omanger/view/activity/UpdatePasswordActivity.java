package com.huafan.huafano2omanger.view.activity;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.view.fragment.updatepwd.UpdatePwdFragment;


/**
 * Created by caishen on 2017/10/18.
 * by--忘记登录密码的页面
 */

public class UpdatePasswordActivity extends KActivity {

    @Override
    protected KFragment getFirstFragment() {
        return UpdatePwdFragment.newInstance();
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
