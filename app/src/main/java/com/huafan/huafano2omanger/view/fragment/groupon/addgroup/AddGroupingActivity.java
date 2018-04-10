package com.huafan.huafano2omanger.view.fragment.groupon.addgroup;

import android.content.Intent;
import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by caishen on 2018/1/13.
 * by--添加团购
 */

public class AddGroupingActivity extends KActivity {
    @Override
    protected KFragment getFirstFragment() {
        Intent intent = getIntent();
        String state = intent.getStringExtra("state");
        String groupId = intent.getStringExtra("groupId");

        return AddGroupingFragment.newIntence(state, groupId);
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
