package com.huafan.huafano2omanger.view.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.UserInfoEvent;
import com.huafan.huafano2omanger.event.PushMsgEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.SPUtils;
import com.huafan.huafano2omanger.view.activity.LoginActivity;
import com.huafan.huafano2omanger.view.bottombar.BottomBarLayout;
import com.huafan.huafano2omanger.view.customer.NoScrollViewPager;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;
import com.huafan.huafano2omanger.view.fragment.groupon.GrouponFragment;
import com.huafan.huafano2omanger.view.fragment.mine.MineFragment;
import com.huafan.huafano2omanger.view.fragment.pending.PendingFragment;
import com.huafan.huafano2omanger.view.fragment.shop.ShopFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: zhangpeisen
 * created on: 2017/10/10 上午11:25
 * description: 花返网 主类
 */
public class MainFragment extends KFragment<IMainView, IMainPresenter> {

    @BindView(R.id.vp_content)
    NoScrollViewPager mVpContent;
    @BindView(R.id.bbl)
    BottomBarLayout mBottomBarLayout;

    private List<KFragment> mFragmentList = new ArrayList<>();
    private RotateAnimation mRotateAnimation;
    private Handler mHandler = new Handler();
    private MediaPlayer mediaPlayer;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IMainPresenter createPresenter() {

        return new IMainPresenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        initData();
        initListener();
    }

    public void initData() {

        mFragmentList.add(PendingFragment.newInstance("待处理"));//首页
        mFragmentList.add(GrouponFragment.newInstance("1"));//团购
        mFragmentList.add(ShopFragment.newInstance());//门店
        mFragmentList.add(MineFragment.newInstance());//我的
        mVpContent.setAdapter(new MyAdapter(getChildFragmentManager()));
        mBottomBarLayout.setViewPager(mVpContent);

        //判断是否登录
        String token = SPUtils.get(getActivity(), "token", "").toString();
        if (token.equals("") || TextUtils.isEmpty(token)) {

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserInfoEvent userInfoEvent) {

        if (userInfoEvent.getUserInfo() == null) {//未登录

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

        } else {//已登录
        }
    }

    public void initListener() {

//        mBottomBarLayout.setUnread(0, 20);//设置第一个页签的未读数为20
//        mBottomBarLayout.setUnread(1, 101);//设置第二个页签的未读书
//        mBottomBarLayout.showNotify(0);//设置第三个页签显示提示的小红点0
//        mBottomBarLayout.showNotify(1);//设置第三个页签显示提示的小红点0
//        mBottomBarLayout.showNotify(3);//设置第三个页签显示提示的小红点0
//        mBottomBarLayout.showNotify(4);//设置第三个页签显示提示的小红点0
//        mBottomBarLayout.setMsg(3, "NEW");//设置第四个页签显示NEW提示文字

    }


    /**
     * 收到推送的处理
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PushMsgEvent event) {
        if (event == null) {
            return;
        }

        String content = event.getContent();
        String myvalue = null;
        String order_num = null;
        if (!TextUtils.isEmpty(content)) {
            JSONObject customJson = null;
            try {
                customJson = new JSONObject(content);
                myvalue = null;
                if (!customJson.isNull("type")) {
                    myvalue = customJson.getString("type");
                }

                if (!customJson.isNull("order_num")) {
                    order_num = customJson.getString("order_num");
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (myvalue != null) {
            //您有新的花返退款申请,待处理
            if ("20".equals(myvalue)) {
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.refund_money);
                playVoice(getActivity());
                //您有新的花返外卖订单,请及时处理
            } else if ("21".equals(myvalue)) {
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.neworder);
                playVoice(getActivity());
                //花返已经为您自动接单
            } else if ("23".equals(myvalue)) {
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.automic_order);
                playVoice(getActivity());
                //您有新的花返推送消息,请查收
            } else if ("22".equals(myvalue)) {
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.new_push);
                playVoice(getActivity());
            }

            showAlertMsgDialog(TextUtils.isEmpty(event.getDescription()) ? "" : event.getDescription(),
                    TextUtils.isEmpty(event.getTitle()) ? "" : event.getTitle(), "确定");
        }
    }

    public void playVoice(Context context) {

        try {

            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.reset();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }

    /**
     * @Title: showAlertMsgDialog @Description: TODO 弹出消息框 @param @param
     * msg @param @param title @param @param positive @param @param
     * negative @return_ticket void 返回类型 @throws
     */
    public void showAlertMsgDialog(String msg, String title, String positive) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.show();
    }
}
