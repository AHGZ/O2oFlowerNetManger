package com.huafan.huafano2omanger.view.fragment.mine.voice;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.OrderReceivingBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by caishen on 2018/1/8.
 * by--消息和语音设置
 */

public class SettingVoiceFragment extends KFragment<ISettingVoiceView, ISettingVoicePrenter>
        implements NormalTopBar.normalTopClickListener, RadioGroup.OnCheckedChangeListener, ISettingVoiceView {

    private static MediaPlayer mediaPlayer;
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    @BindView(R.id.orderReceiving_radiogroup)
    RadioGroup orderReceivingRadiogroup;
    @BindView(R.id.orderReceiving_sava_button)
    Button orderReceivingSavaButton;
    private int type = 5;
    private ShapeLoadingDialog shapeLoadingDialog;
    private int states = -1;
    private SoundPool mSoundPool;

    @Override
    public ISettingVoicePrenter createPresenter() {
        return new ISettingVoicePrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_voice;
    }

    @Override
    public void initData() {

        mPresenter.getOrderReceiving();
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        normalTop.setTitleText("消息和提示音");
        normalTop.setTopClickListener(this);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        orderReceivingRadiogroup.setOnCheckedChangeListener(this);

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        initData();

    }

    public static KFragment newIntence() {

        SettingVoiceFragment settingVoiceFragment = new SettingVoiceFragment();
        Bundle bundle = new Bundle();
        settingVoiceFragment.setArguments(bundle);
        return settingVoiceFragment;
    }

    @Override
    public void onLeftClick(View view) {

        removeFragment();
    }

    @Override
    public void onRightClick(View view) {

    }

    @Override
    public void onTitleClick(View view) {

    }


    @OnClick(R.id.orderReceiving_sava_button)
    public void onClick() {//保存

        mPresenter.saveSetting();
//        playVoice(getActivity());

//        mPresenter.saveSetting();
    }

    public static void playVoice(Context context) {
        try {
            mediaPlayer = MediaPlayer.create(context, R.raw.neworder);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //停止播放声音
    public static void stopVoice() {
        if (null != mediaPlayer) {
            mediaPlayer.stop();
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int id = group.getCheckedRadioButtonId();
        switch (id) {
            case R.id.rb_yy://语音

                states = 2;

                break;
            case R.id.rb_xl://响铃

                states = 1;

                break;
        }
    }

    @Override
    public void onSuccess(String data) {

        showShortToast(data);
        removeFragment();
    }

    @Override
    public void onGetSuccess(OrderReceivingBean data) {

        if (data != null) {

            String state = data.getState();
            if (state.equals("1")) {//响铃

                RadioButton radioButton = (RadioButton) orderReceivingRadiogroup.getChildAt(0);
                radioButton.setChecked(true);
                states = 1;

            } else {//语音

                RadioButton radioButton = (RadioButton) orderReceivingRadiogroup.getChildAt(1);
                radioButton.setChecked(true);
                states = 0;
            }
        }
    }

    @Override
    public void onError(String message) {

        showShortToast(message);
    }

    @Override
    public void showDialog() {

        shapeLoadingDialog.show();
    }

    @Override
    public void hideDialog() {

        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
        }
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getState() {
        return states;
    }

}
