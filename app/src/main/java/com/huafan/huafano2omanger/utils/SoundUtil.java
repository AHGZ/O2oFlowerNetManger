package com.huafan.huafano2omanger.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.base.BaseApplication;


/**
 * Created by caishen on 2018/2/5.
 * by--使用SoundPool实现语音提示
 */

public class SoundUtil {

    public static boolean open = false;//开通微店
    public static float voiceVolume = 9;//音量
    public static boolean newOrderVoice;//新订单语音
    public static boolean timeoutOrderVoice;//超时未接单语音
    //新订单语音播放中
    public static boolean soundNewPlaying = false;
    //超时订单语音播放中
    public static boolean soundTimeoutPlaying = false;
    public static SoundHandler soundHandler;
    //
    public final static int SOUND_NEW_ORDER = 1;
    public final static int SOUND_TIMEOUT_ORDER = 2;
    public final static int SOUND_DELAY_NEW_ORDER = 3;
    public final static int SOUND_DELAY_TIMEOUT = 4;
    //有另外一个语音要播放，需等待5秒
    public final static long delay = 5000;
    private static int neworder;
    private static int new_push;
    private static int refund_money;
    private static int automic_order;

    /**
     * 资源信息初始化，在打开APP时调用。我是在登陆成功后调用的。
     */
    public static void init(Context context) {

        //音量
        voiceVolume = (float) SPUtils.get(context, SPUtils.KEY_WD_VOICE_VOLUME, 1.0f);
        //新订单语音，播放或静音
        newOrderVoice = (boolean) SPUtils.get(context, SPUtils.KEY_WD_VOICE_NEW, true);
        //超时未接单语音，播放或静音
        timeoutOrderVoice = (boolean) SPUtils.get(context, SPUtils.KEY_WD_VOICE_TIMEOUT, true);
        soundHandler = new SoundHandler();

        //加载语音资源
        initSound();
    }

    /**
     * 释放资源，关闭APP后调用。我是在退出登录后调用的。
     */
    public static void release() {

        if (null != soundHandler) {

            soundHandler.removeCallbacksAndMessages(null);
            soundHandler = null;
        }

        if (null != mSoundPool) {
            mSoundPool.release();
            mSoundPool = null;
        }
    }

    /**
     * 播放提示音，新的语音要播放时，要延迟5秒。
     */
    private static void playing(int msg, String type) {

        if (null == soundHandler) {
            soundHandler = new SoundHandler();
        }
        switch (msg) {
            //新订单
            case SOUND_NEW_ORDER:

                //有语音在播放时，等待5秒后再去播放语音
                if (soundTimeoutPlaying || soundNewPlaying) {

                    soundHandler.sendEmptyMessageDelayed(SOUND_DELAY_NEW_ORDER, delay);

                } else {

                    soundNewPlaying = true;

                    if (!TextUtils.isEmpty(type)) {

                        //您有新的花返退款申请,待处理
                        if ("20".equals(type)) {
                            mSoundPool.play(refund_money, voiceVolume, voiceVolume, 1, 0, 1);

                            //您有新的花返外卖订单,请及时处理
                        } else if ("21".equals(type)) {
                            mSoundPool.play(neworder, voiceVolume, voiceVolume, 1, 0, 1);

                            //花返已经为您自动接单
                        } else if ("23".equals(type)) {
                            mSoundPool.play(automic_order, voiceVolume, voiceVolume, 1, 0, 1);
                            //您有新的花返推送消息,请查收
                        } else if ("22".equals(type)) {
                            mSoundPool.play(new_push, voiceVolume, voiceVolume, 1, 0, 1);
                        }
                    }
                    soundHandler.sendEmptyMessageDelayed(msg, delay);
                }
                break;
        }
    }

    private static SoundPool mSoundPool;
    private static int mNewOrderSoundResId;//新订单语音
    private static int mTimeoutSoundResId;//超时订单语音

    /**
     * 加载语音资源
     **/
    private static void initSound() {

        mSoundPool = new SoundPool(1, AudioManager.STREAM_VOICE_CALL, 5);
        neworder = mSoundPool.load(BaseApplication.getContext(), R.raw.neworder, 1);
        new_push = mSoundPool.load(BaseApplication.getContext(), R.raw.new_push, 1);
        refund_money = mSoundPool.load(BaseApplication.getContext(), R.raw.refund_money, 1);
        automic_order = mSoundPool.load(BaseApplication.getContext(), R.raw.automic_order, 1);
    }

    /**
     * 播放语音提示
     */
    public static synchronized void playSound(String wdOrderStatus) {

        if (!TextUtils.isEmpty(wdOrderStatus)) {
            //您有新的花返退款申请,待处理
            if ("20".equals(wdOrderStatus)) {
                playing(SOUND_NEW_ORDER, "20");
                //您有新的花返外卖订单,请及时处理
            } else if ("21".equals(wdOrderStatus)) {
                playing(SOUND_NEW_ORDER, "21");
                //花返已经为您自动接单
            } else if ("23".equals(wdOrderStatus)) {
                playing(SOUND_NEW_ORDER, "23");
                //您有新的花返推送消息,请查收
            } else if ("22".equals(wdOrderStatus)) {
                playing(SOUND_NEW_ORDER, "22");
            }
        }
    }

    static class SoundHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SOUND_NEW_ORDER:
                    soundNewPlaying = false;
                    break;
                case SOUND_TIMEOUT_ORDER:
                    soundTimeoutPlaying = false;
                    break;
            }
        }
    }
}
