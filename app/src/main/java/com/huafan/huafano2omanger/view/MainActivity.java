package com.huafan.huafano2omanger.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.huafan.huafano2omanger.mvp.KActivity;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.PreUtils;
import com.huafan.huafano2omanger.view.fragment.main.MainFragment;


public class MainActivity extends KActivity {

    private long clickTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化推送
        push();
//        autoBindBaiduYunTuiSong();
    }

    @Override
    protected KFragment getFirstFragment() {

        return MainFragment.newInstance();
    }

    /**
     * 退出主界面
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {

        if ((System.currentTimeMillis() - clickTime) > 2000) {

            showShortToast("再次点击退出");
            clickTime = System.currentTimeMillis();

        } else {

            this.finish();
            System.exit(0);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String result = intent.getStringExtra("result");
        if (result != null) {

            Log.e("TAG", "result==" + result);
        }
        // super.onNewIntent(intent);
    }

    /**
     * 如果没有绑定百度云，则绑定，并记录在属性文件中
     */
    private void autoBindBaiduYunTuiSong() {

        if (!PreUtils.isBind(getApplicationContext())) {

            PushManager.startWork(getApplicationContext(),
                    PushConstants.LOGIN_TYPE_API_KEY,
                    "dzeo8raMxhUF0GWsP8ZpXiS9mtUSvDpF");
        }
    }

    private void push() {

        // 初始化push
        initWithApiKey();
//        unBindForApp();
//        initWithApiKey();
//        Resources resource = this.getResources();
//        notification.sound =Uri sound;
//        NotificationCompat.Builder.setSound(Uri sound)
//        String pkgName = this.getPackageName();
//        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(resource.getIdentifier(
//                "notification_custom_builder", "layout", pkgName),
//                resource.getIdentifier("notification_icon", "id", pkgName),
//                resource.getIdentifier("notification_title", "id", pkgName),
//                resource.getIdentifier("notification_text", "id", pkgName));
//
//        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
////        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
//        cBuilder.setStatusbarIcon(this.getApplicationInfo().icon);
//        cBuilder.setLayoutDrawable(resource.getIdentifier("rty", "drawable", pkgName));
//        cBuilder.setChannelId(Constants.DEVICETOKEN);
//        cBuilder.setChannelName("testName");
//        cBuilder.setNotificationSound((Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/" + R.raw.neworder)));
//        // 推送高级设置，通知栏样式设置为下面的ID
//        PushManager.setNotificationBuilder(this, 1, cBuilder);
    }

    private void unBindForApp() {
        // Push：解绑
        PushManager.stopWork(getApplicationContext());
    }

    private void initWithApiKey() {

        PushManager.startWork(getApplicationContext(),
                PushConstants.LOGIN_TYPE_API_KEY, "dzeo8raMxhUF0GWsP8ZpXiS9mtUSvDpF");
    }
}