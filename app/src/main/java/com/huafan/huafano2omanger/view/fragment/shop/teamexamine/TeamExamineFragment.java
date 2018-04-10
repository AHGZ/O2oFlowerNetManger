package com.huafan.huafano2omanger.view.fragment.shop.teamexamine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.TeamExamineBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.CustomEditText;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.zxing.activity.CaptureActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 团队核销主界面
 */

public class TeamExamineFragment extends KFragment<ITeamExamineView, ITeamExaminePresenter> implements NormalTopBar.normalTopClickListener,ITeamExamineView {

    //自定义通用标题
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    //二维码图标
    @BindView(R.id.team_examine_img)
    ImageView teamExamineImg;
    //团队劵码输入框
    @BindView(R.id.team_examine_edittext)
    CustomEditText teamExamineEdittext;
    //提交按钮
    @BindView(R.id.team_examine_button)
    Button teamExamineButton;
    private ShapeLoadingDialog shapeLoadingDialog;

    public static TeamExamineFragment newInstance() {
        TeamExamineFragment pendingFragment = new TeamExamineFragment();
        Bundle bundle = new Bundle();
        pendingFragment.setArguments(bundle);
        return pendingFragment;
    }

    @Override
    public ITeamExaminePresenter createPresenter() {
        return new ITeamExaminePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_team_examine;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        normalTop.setTitleText("团队核销");
        normalTop.setTopClickListener(this);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        //初始化加载进度条
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .delay(5000)
                .loadText("加载中...")
                .build();
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

    //二维码图标，团队劵码输入框，提交按钮点击事件
    @OnClick({R.id.team_examine_img, R.id.team_examine_edittext, R.id.team_examine_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.team_examine_img:
                startActivity(new Intent(getActivity(), CaptureActivity.class));
                break;
            case R.id.team_examine_edittext:

                break;
            case R.id.team_examine_button:
                String codeStr = teamExamineEdittext.getText().toString().trim();
                if (codeStr.equals("")){
                    showShortToast("请输入团购劵码");
                }else{
                    mPresenter.submitCode(codeStr);
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg) {
        showShortToast(errorMsg);
    }

    @Override
    public void onSuccess(TeamExamineBean teamExamineBean) {

    }

    @Override
    public void onSuccess(String message) {

        showShortToast(message);
        removeFragment();
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
}
