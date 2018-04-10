package com.huafan.huafano2omanger.view.fragment.shop.cashmanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.CashManagementAdapter;
import com.huafan.huafano2omanger.entity.CashManagementBean;
import com.huafan.huafano2omanger.entity.SelectorDialogBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.AppEditextAlertDialog;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.SelectorAlertDialog;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.fragment.shop.cashmanagement.withdraw.WithDrawFragment;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by heguozhong on 2017/12/26/026.
 * 提现管理主界面
 */

public class CashManagementFragment extends KFragment<ICashManagementView, ICashManagementPresenter> implements ICashManagementView, NormalTopBar.normalTopClickListener {
    //自定义通用标题
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    //可提现金额
    @BindView(R.id.cash_management_ktxje)
    TextView cashManagementKtxje;
    //已提现金额
    @BindView(R.id.cash_management_ytxje)
    TextView cashManagementYtxje;
    //提现管理listview
    @BindView(R.id.cash_management_recyclerview)
    RecyclerView cashManagementRecyclerview;
    //提现按钮
    @BindView(R.id.cash_management_withdraw_deposit)
    FloatingActionButton cashManagementWithdrawDeposit;
    private CashManagementAdapter cashManagementAdapter;
    private ShapeLoadingDialog shapeLoadingDialog;
    private String alipayAccount = "";//支付宝账号
    private String withMoey = "";//支付宝金额
    private String loginPwd = "";//登录密码

    public static CashManagementFragment newInstance() {
        Bundle args = new Bundle();
        CashManagementFragment fragment = new CashManagementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ICashManagementPresenter createPresenter() {
        return new ICashManagementPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_cash_management;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        normalTop.setTitleText("提现管理");
        normalTop.setTopClickListener(this);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);

        //将悬浮按钮附着到Listview上
        cashManagementWithdrawDeposit.attachToRecyclerView(cashManagementRecyclerview);

        //初始化加载进度条
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .delay(5000)
                .loadText("加载中...")
                .build();
        //查询提现金额
        mPresenter.selectCash();
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

    //提现按钮监听事件
    @OnClick(R.id.cash_management_withdraw_deposit)
    public void onViewClicked() {
        showSelectorDiog("请选择提现方式");
    }


    @Override
    public void onError(String errorMsg) {
        showShortToast(errorMsg);
    }

    @Override
    public void onSuccess(CashManagementBean cashManagementBean) {
        List<CashManagementBean.ListBean> list = cashManagementBean.getList();
        if (cashManagementBean.getWithdraw_money().equals("")) {
            //可提现金额
            cashManagementKtxje.setText("可提现金额: ¥" + 0);
        } else {
            cashManagementKtxje.setText("可提现金额: ¥" + cashManagementBean.getWithdraw_money());
        }
        if (cashManagementBean.getWithdraw_total().equals("")) {
            //已提现金额
            cashManagementYtxje.setText("已提现金额: ¥" + 0);
        } else {
            cashManagementYtxje.setText("已提现金额: ¥" + cashManagementBean.getWithdraw_total());
        }

        //获取布局管理者
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置为垂直排列格式
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerview应用垂直排列格式
        cashManagementRecyclerview.setLayoutManager(linearLayoutManager);
        //设置适配器
        cashManagementAdapter = new CashManagementAdapter(getActivity(), list);
        cashManagementRecyclerview.setAdapter(cashManagementAdapter);
    }

    @Override
    public void onSuccess(String message) {
        showShortToast(message);
        mPresenter.selectCash();
        cashManagementAdapter.notifyDataSetChanged();
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
    public void onSuccesscheck(String data) {

        //登录密码验证成功，调取支付宝提现接口
        mPresenter.withdraw();

    }

    @Override
    public String getPwd() {
        return loginPwd;
    }

    @Override
    public void onSuccessedWith(String message) {
        showShortToast("提现成功");
//        mPresenter.addCash(withMoey);
        mPresenter.selectCash();
        if (shapeLoadingDialog != null) {

            shapeLoadingDialog.dismiss();
        }
    }

    @Override
    public String getAlipayAccount() {
        return alipayAccount;
    }

    @Override
    public String getMoney() {
        return withMoey;
    }

    //提现方式选择弹窗
    private void showSelectorDiog(String title) {

        ArrayList<SelectorDialogBean> selectors = new ArrayList<>();

        for (int i = 0; i < 2; i++) {

            SelectorDialogBean selectorDialogBean = new SelectorDialogBean();
            selectorDialogBean.setId(i);
            if (i == 0) {
                selectorDialogBean.setName("提现到银行卡");
            } else {
                selectorDialogBean.setName("提现到支付宝");
            }
            selectors.add(selectorDialogBean);
        }

        SelectorAlertDialog alertDialog = new SelectorAlertDialog(getActivity(), selectors).builder().setTitle(title)
                .setOnItemClickLitener(new SelectorAlertDialog.OnItemClickLitener() {
                    @Override
                    public void onItemClickLitener(int position, View view) {

                        if (position == 0) {//提现到银行卡
                            addFragment(WithDrawFragment.newIntence());

                        } else {//提现到支付宝
//                            showAlertEdittextDialog("提现到支付宝","确定","取消");
                            showAiPay();
                        }
                    }
                });

        alertDialog.show();
    }

    //提现到支付宝
    private void showAiPay() {

        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle("请输入支付宝账号");
        alertDialog.setPositiveButton("确定", new AppEditextAlertDialog.OnposClickLitener() {
                    @Override
                    public void onPosEditClick(String trim, String trim1, String trim2, String trim3) {

                        if (TextUtils.isEmpty(trim)) {

                            showShortToast("请输入支付宝账号");

                        } else {

                            alipayAccount = trim;
                            showWithdraw();
                            alertDialog.hide();
                        }
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.et_name.setHint("请输入支付宝账号(必填)");
        alertDialog.remove(alertDialog.et_price);
        alertDialog.remove(alertDialog.et_rl_price);
        alertDialog.remove(alertDialog.et_stock);
        alertDialog.show();
    }

    //提现金额
    private void showWithdraw() {

        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle("请输入提现金额");
        alertDialog.setMsg("支付宝提现每笔仅支持100-50000元，每日最多2笔共50000元");
        alertDialog.setPositiveButton("确定", new AppEditextAlertDialog.OnposClickLitener() {
                    @Override
                    public void onPosEditClick(String trim, String trim1, String trim2, String trim3) {

                        if (TextUtils.isEmpty(trim)) {

                            showShortToast("请输入提现金额");

                        } else {

                            withMoey = trim;
                            showLoginPwd();
                            alertDialog.hide();
                        }
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.et_name.setHint("请输入提现金额(必填)");
        alertDialog.et_name.setInputType(8194);
        alertDialog.remove(alertDialog.et_price);
        alertDialog.remove(alertDialog.et_rl_price);
        alertDialog.remove(alertDialog.et_stock);
        alertDialog.show();
    }

    //登录密码对话框
    private void showLoginPwd() {

        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle("请输入登录密码");
        alertDialog.setPositiveButton("确定", new AppEditextAlertDialog.OnposClickLitener() {
                    @Override
                    public void onPosEditClick(String trim, String trim1, String trim2, String trim3) {

                        if (TextUtils.isEmpty(trim)) {

                            showShortToast("登录密码输入不正确");

                        } else {

                            loginPwd = trim;
                            mPresenter.check_pwd();
                            alertDialog.hide();
                        }
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.et_name.setHint("请输入登录密码(必填)");
        alertDialog.remove(alertDialog.et_price);
        alertDialog.remove(alertDialog.et_rl_price);
        alertDialog.remove(alertDialog.et_stock);
        alertDialog.show();
    }

}
