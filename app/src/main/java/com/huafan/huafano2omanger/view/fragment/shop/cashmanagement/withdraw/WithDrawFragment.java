package com.huafan.huafano2omanger.view.fragment.shop.cashmanagement.withdraw;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.BankCardListBean;
import com.huafan.huafano2omanger.entity.CashManagementBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.utils.Utils;
import com.huafan.huafano2omanger.view.customer.AppEditextAlertDialog;
import com.huafan.huafano2omanger.view.customer.CustomEditText;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.fragment.mine.bankcard.BankCardActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.huafan.huafano2omanger.R.id.allwithdraw_value;

/**
 * @描述:提现页面
 * @创建人：zhangpeisen
 * @创建时间：2017/11/15 下午4:03
 * @修改人：zhangpeisen
 * @修改时间：2017/11/15 下午4:03
 * @修改备注：
 * @throws
 */
public class WithDrawFragment extends KFragment<IWithDrawView, IWithDrawPrenter>
        implements IWithDrawView, CustomEditText.IMyRightDrawableClick {
    @BindView(R.id.im_back)
    // 返回按钮
            ImageView im_back;
    @BindView(R.id.tv_cancel)
    // 取消按钮
            TextView tvCancel;
    @BindView(R.id.bankcardname_tv)
    // 银行卡名字
            TextView bankcardname_tv;
    @BindView(R.id.bankcardnum_tv)
    // 银行卡卡号
            TextView bankcardnum_tv;
    @BindView(R.id.bankcardicon_iv)
    // 银行卡图标
            ImageView bankcardicon_iv;
    @BindView(R.id.userbalance_tv)
    // 账户余额
            TextView userbalance_tv;
    @BindView(allwithdraw_value)
    // 全部提现按钮
            TextView allwithdrawValue;
    @BindView(R.id.withdrawamunt_ed)
    // 提现金额
            CustomEditText withdrawamuntEd;
    @BindView(R.id.withdrawtime_tv)
    // 提现到账时间
            TextView withdrawtimeTv;
    @BindView(R.id.withdraw_showhint)
    // 提现状态提示
            TextView withdrawShowhint;
    @BindView(R.id.finished_btn)
    // 完成按钮
            Button finishedBtn;
    @BindView(R.id.container_ly)
    // 完成按钮
            LinearLayout container_ly;
    @BindView(R.id.userbalance_unit)
    TextView userbalanceUnit;
    @BindView(R.id.withdrawamunt_unit)
    TextView withdrawamuntUnit;
    @BindView(R.id.withdrawtime_unit)
    TextView withdrawtimeUnit;
    // 加载进度条
    private ShapeLoadingDialog shapeLoadingDialog;

    private String mPayPwdStr;

    boolean mIsPass;
    // 今天已用金额
    private String Withdrawals;
    // 余额
    private String WithdrawMoneny;


    private String loginPwd = "";//登录密码

    public static WithDrawFragment newIntence() {

        Bundle bundle = new Bundle();
        WithDrawFragment walletFragment = new WithDrawFragment();
        walletFragment.setArguments(bundle);
        return walletFragment;
    }

    @Override
    public IWithDrawPrenter createPresenter() {
        return new IWithDrawPrenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.mine_withdraw_fragment;
    }

    @Override
    public void initData() {
        //初始化沉浸式
        Utils.setStatusBar(getActivity(), 2, false);
        //增加控件点击区域
        UIUtils.setTouchDelegate(im_back, 50);
        UIUtils.setTouchDelegate(allwithdrawValue, 50);
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        initData();
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        //检查有没有绑定银行卡
        mPresenter.getcard_list();

        mPresenter.selectCash();

//        mPresenter.selwithdrawals();
        withdrawamuntEd.setRightDrawable(getResources().getDrawable(R.drawable.edittext_clear_close));
        withdrawamuntEd.setDrawableClick(this);
    }

    @Override
    public void rightDrawableClick(View view) {
        switch (view.getId()) {
            case R.id.withdrawamunt_ed:
                withdrawamuntEd.setText("");
                break;
        }

    }


    @Override
    public String getPwd() {
        return loginPwd;
    }

    @Override
    public String getBalance() {
        return withdrawamuntEd.getText().toString().trim();
    }

    @Override
    public void showDialog() {
        shapeLoadingDialog.show();
    }

    @Override
    public void hideDialog() {
        if (shapeLoadingDialog.isShowing())
            shapeLoadingDialog.dismiss();
    }

    @Override
    public void onError(String errorMsg) {
        showShortToast(errorMsg);
    }

    @Override
    public void onSucces(String message) {
        showShortToast(message);
    }

    //查询提现记录
    @Override
    public void onSuccess(CashManagementBean cashManagementBean) {
        //账户余额
        if (cashManagementBean.getWithdraw_money().equals("")) {
            userbalance_tv.setText("" + 0);
        } else {
            userbalance_tv.setText(cashManagementBean.getWithdraw_money() + "");
        }
    }


//    @Override
//    public void onSuccessWithDraw(WithDrawInfoBean withDrawInfoBean) {
//        if (withDrawInfoBean == null) {
//            return;
//        }
//        // 银行卡名字
//        bankcardname_tv.setText(TextUtils.isEmpty(withDrawInfoBean.getBank_name())
//                && withDrawInfoBean.getBank_name().equals("") ? "" : withDrawInfoBean.getBank_name());
//        // 银行卡卡号
//        bankcardnum_tv.setText(TextUtils.isEmpty(withDrawInfoBean.getCard_num())
//                && withDrawInfoBean.getCard_num().equals("") ? "" : withDrawInfoBean.getCard_num()
//                .substring(withDrawInfoBean.getCard_num().length() - 4, withDrawInfoBean.getCard_num().length()));
//        // 可用余额
//        userbalance_tv.setText(TextUtils.isEmpty(withDrawInfoBean.getMoney())
//                && withDrawInfoBean.getMoney().equals("") ? "" : withDrawInfoBean.getMoney());
//        // 今天已用金额
//        Withdrawals = TextUtils.isEmpty(withDrawInfoBean.getWithdrawals())
//                && withDrawInfoBean.getWithdrawals().equals("") ? "" : withDrawInfoBean.getWithdrawals();
//        // 用户金额
//        WithdrawMoneny = TextUtils.isEmpty(withDrawInfoBean.getMoney())
//                && withDrawInfoBean.getMoney().equals("") ? "" : withDrawInfoBean.getMoney();
//        if (TextUtils.isEmpty(withDrawInfoBean.getBankimg())) {
//            return;
//        }
//        new GlideImageLoader().displayImage(getActivity(), ApiUrlConstant.API_IMG_URL + withDrawInfoBean.getBankimg(), bankcardicon_iv);
//    }

    @Override
    public void onSuccessData(BankCardListBean data) {
        if (data.getLists().size() == 0) {
            showShortToast("请绑定银行卡");
            bankcardicon_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), BankCardActivity.class));
                }
            });
        } else {
            //赋值银行卡名字
            bankcardname_tv.setText(data.getLists().get(0).getBank_name());
            //赋值银行卡卡号
            bankcardnum_tv.setText(data.getLists().get(0).getCard_num());
//            mPresenter.selwithdrawals();
            finishedBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 初始化弹框
                    if (TextUtils.isEmpty(getBalance()) && getBalance().equals("")) {
                        showShortToast("请输入提现金额");
                        return;
                    }
                    showAlertEdittextDialog();
                }
            });

        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
//    @OnClick({R.id.im_back, R.id.tv_cancel, allwithdraw_value, R.id.finished_btn})
    @OnClick({R.id.im_back, R.id.tv_cancel, allwithdraw_value})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back:
                // 返回按钮
                removeFragment();
                break;
            case R.id.tv_cancel:
                // 取消按钮
                removeFragment();
                break;
            case allwithdraw_value:
                // 设置全部用户余额
                withdrawamuntEd.setText(userbalance_tv.getText().toString().trim());
                break;
//            case R.id.finished_btn:
//
//                // 初始化弹框
//                if (TextUtils.isEmpty(getBalance()) && getBalance().equals("")) {
//                    showShortToast("请输入提现金额");
//                    return;
//                }

//                BigDecimal mWithdrawMonenyDecimal = new BigDecimal(WithdrawMoneny);
//                // 用户余额
//                BigDecimal mDrawalsDecimal = new BigDecimal(getBalance());
//                // 今天已用金额
//                BigDecimal mWithDrawalsDecimal = new BigDecimal(Withdrawals);
//                // 最大提现金额
//                BigDecimal mMaxWithDrawDecimal = new BigDecimal("50000");
//                if (mDrawalsDecimal.compareTo(mWithdrawMonenyDecimal) == 1) {
//                    // 提现金额 大于 用户余额
//                    showShortToast("可提现余额不足,请及时充值");
//                    return;
//                }
//                if (getBalance().equals("0") || getBalance().equals("0.00")) {
//                    showShortToast("用户余额不足,请及时充值");
//                    return;
//                }
//                if (mWithDrawalsDecimal.compareTo(mMaxWithDrawDecimal) == 1) {
//                    // 提现金额 大于 最大提现金额
//                    showShortToast("可提金额已超限");
//                    return;
//                }
//                showAlertEdittextDialog();
//                    mPresenter.withdrawalsToBankCard();
//                } else {
////                    mPresenter.HasPayPwd();
//                }
//                break;
        }
    }

    @Override
    public void onSuccesscheck(String data) {
        //登录密码验证成功
        mPresenter.addCash(withdrawamuntEd.getText().toString().trim());
    }

    //登录密码输入框
    public void showAlertEdittextDialog() {
        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle("请输入登录密码");
        alertDialog.setPositiveButton("确定", new AppEditextAlertDialog.OnposClickLitener() {
                    @Override
                    public void onPosEditClick(String s, String trim, String trim1, String trim2) {
                        if (TextUtils.isEmpty(s)) {

                            showShortToast("登录密码输入不正确");

                        } else {

                            loginPwd = s;
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
