package com.huafan.huafano2omanger.view.fragment.shop.teamexamine;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 团队核销主页面
 */

public class TeamExamineActivity extends KActivity {

    @Override
    protected KFragment getFirstFragment() {
        return TeamExamineFragment.newInstance();
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