package com.huafan.huafano2omanger.view.activity;

import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.view.fragment.mine.printset.PrintSetFragment;

/**
 * @描述:打印设置主页
 * @创建人：zhangpeisen
 * @创建时间：2017/12/25 下午6:30
 * @修改人：zhangpeisen
 * @修改时间：2017/12/25 下午6:30
 * @修改备注：
 * @throws
 */
public class PrintSetActivity extends KActivity {
    @Override
    protected KFragment getFirstFragment() {
        return PrintSetFragment.newInstance();
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
