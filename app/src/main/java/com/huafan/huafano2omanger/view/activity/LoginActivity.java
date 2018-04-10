package com.huafan.huafano2omanger.view.activity;

import android.view.KeyEvent;
import android.view.View;

import com.huafan.huafano2omanger.event.OutProgramEvent;
import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;
import com.huafan.huafano2omanger.view.fragment.login.LoginFragment;

import org.greenrobot.eventbus.EventBus;


/**
 * author: zhangpeisen
 * created on: 2017/10/11 上午9:26
 * description: 登录页面
 */
public class LoginActivity extends KActivity {

    @Override
    protected KFragment getFirstFragment() {
        return LoginFragment.newInstance();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            showAlertMsgDialog("未登录将退出花返网商家版", "狠心退出花返网商家版", "狠心离去", "取消");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * @Title: showAlertMsgDialog @Description: TODO 弹出消息框 @param @param
     * msg @param @param title @param @param positive @param @param
     * negative @return_ticket void 返回类型 @throws
     */
    public void showAlertMsgDialog(String msg, String title, String positive, String negative) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(this).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        //退出程序
                        EventBus.getDefault().post(new OutProgramEvent("1"));
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.show();
    }
}
