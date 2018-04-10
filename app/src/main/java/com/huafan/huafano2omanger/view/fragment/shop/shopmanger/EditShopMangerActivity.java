package com.huafan.huafano2omanger.view.fragment.shop.shopmanger;

import android.content.Intent;
import android.view.KeyEvent;

import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;

/**
 * Created by caishen on 2017/12/25.
 * by--添加商品管理
 */

public class EditShopMangerActivity extends KActivity {
    @Override
    protected KFragment getFirstFragment() {

        Intent intent = getIntent();
        String goodId = intent.getStringExtra("goodId");
        String isUpdate = intent.getStringExtra("isUpdate");
        String cate_id = intent.getStringExtra("cate_id");
        int tag = intent.getIntExtra("tag",0);
        String sortName = intent.getStringExtra("sortName");
        String flag = intent.getStringExtra("flag");
        return EditShopMangerFragment.newIntence(cate_id,isUpdate,goodId,tag,sortName,flag);
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
