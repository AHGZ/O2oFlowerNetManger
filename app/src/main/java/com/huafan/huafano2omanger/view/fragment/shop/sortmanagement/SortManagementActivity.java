package com.huafan.huafano2omanger.view.fragment.shop.sortmanagement;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 分类管理主页面
 */

public class SortManagementActivity extends KActivity {

    @Override
    protected KFragment getFirstFragment() {
        return SortManagementFragment.newInstance();
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
