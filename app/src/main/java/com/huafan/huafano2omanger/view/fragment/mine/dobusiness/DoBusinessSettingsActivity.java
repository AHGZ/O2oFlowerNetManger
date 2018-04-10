package com.huafan.huafano2omanger.view.fragment.mine.dobusiness;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by heguozhong on 2017/12/21/021.
 * 营业设置主页面
 */

public class DoBusinessSettingsActivity extends KActivity {

    @Override
    protected KFragment getFirstFragment() {
        return DoBusinessSettingsFragment.newInstance();
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
