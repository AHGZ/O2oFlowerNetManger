package com.huafan.huafano2omanger.view.fragment.mine.bankcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.BankCardListBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.AppEditextAlertDialog;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by caishen on 2018/1/30.
 * by--银行卡信息
 */

public class BankCardFragment extends KFragment<IBankCardView, IBankCardPrenter> implements IBankCardView, NormalTopBar.normalTopClickListener {

    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    @BindView(R.id.bankcardname_tv)
    TextView bankcardnameTv;
    @BindView(R.id.bankcard_im)
    ImageView bankcardIm;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.addbankcard_ly)
    LinearLayout addbankcardLy;
    @BindView(R.id.tv_bankcard)
    TextView tvBankcard;
    @BindView(R.id.tv_jiebang)
    TextView tvJiebang;
    @BindView(R.id.tv_bankname)
    TextView tvBankname;
    private ShapeLoadingDialog shapeLoadingDialog;
    private String pwd = "";//登录密码
    private List<BankCardListBean.ListsBean> lists;
    private String tag = "";//0解绑银行卡 1添加银行卡
    private String id = "";//银行卡id

    @Override
    public IBankCardPrenter createPresenter() {

        return new IBankCardPrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_bankcard;
    }

    @Override
    public void initData() {

        mPresenter.getcard_list();
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();
        normalTop.setTopClickListener(this);
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);

        initData();
    }

    public static KFragment newIntence() {

        BankCardFragment bankCardFragment = new BankCardFragment();
        Bundle bundle = new Bundle();
        bankCardFragment.setArguments(bundle);
        return bankCardFragment;
    }

    @Override
    public void showDialog() {

        shapeLoadingDialog.show();
    }

    @Override
    public void onError(String s) {

        showShortToast(s);
    }

    @Override
    public void hideDiaglog() {

        if (shapeLoadingDialog != null) {

            shapeLoadingDialog.dismiss();
        }
    }

    @Override
    public void onSuccesscheck(String data) {

        if (data != null) {

            if (tag.equals("0")) {//解绑银行卡

                mPresenter.un_bind();

            } else {//添加银行卡

                Intent intent = new Intent(getActivity(), AddbankCardActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public String getPwd() {
        return pwd;
    }


    @Override
    public void onSuccessData(BankCardListBean data) {

        if (data != null) {

            lists = data.getLists();

            if (lists != null && lists.size() > 0) {

                addbankcardLy.setVisibility(View.GONE);

                bankcardnameTv.setText(lists.get(0).getBank_name() == null ? "" : lists.get(0).getBank_name());
                tvBankname.setText(lists.get(0).getBank_name() == null ? "" : lists.get(0).getBank_name());
                tvBankcard.setText(lists.get(0).getCard_num() == null ? "" : lists.get(0).getCard_num());
                id = lists.get(0).getId();

            } else {

                addbankcardLy.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSuccess(String message) {

        showShortToast(message);

        bankcardnameTv.setText("");
        tvBankname.setText("");
        tvBankcard.setText("");
        id = "";
        mPresenter.getcard_list();
    }

    @Override
    public String id() {
        return id;
    }


    @OnClick({R.id.tv_jiebang, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_jiebang://解绑银行卡

                if (lists != null && lists.size() > 0) {

                    tag = "0";
                    showAlertMsgDialog("确认解绑当前银行卡", "解绑银行卡", "确定", "取消");

                } else {

                    showShortToast("请先添加一张银行卡");
                }

                break;
            case R.id.tv_commit://添加银行卡

                tag = "1";
                AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
                alertDialog.builder();
                alertDialog.setTitle("请填写登录密码");
                alertDialog.setPositiveButton("确定", new AppEditextAlertDialog.OnposClickLitener() {
                            @Override
                            public void onPosEditClick(String trim, String trim1, String trim2, String trim3) {

                                if (TextUtils.isEmpty(trim)) {
                                    return;
                                }
                                pwd = trim;
                                mPresenter.check_pwd();
                                alertDialog.hide();
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                alertDialog.et_name.setHint("请输入登录密码");
                alertDialog.et_price.setVisibility(View.GONE);
                alertDialog.et_rl_price.setVisibility(View.GONE);
                alertDialog.et_stock.setVisibility(View.GONE);
                alertDialog.show();

                break;
        }
    }

    /**
     * @Title: showAlertMsgDialog @Description: TODO 弹出消息框 @param @param
     * msg @param @param title @param @param positive @param @param
     * negative @return void 返回类型 @throws
     */
    public void showAlertMsgDialog(String msg, String title, String positive, String negative) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
                        alertDialog.builder();
                        alertDialog.setTitle("请填写登录密码");
                        alertDialog.setPositiveButton("确定", new AppEditextAlertDialog.OnposClickLitener() {
                                    @Override
                                    public void onPosEditClick(String trim, String trim1, String trim2, String trim3) {

                                        if (TextUtils.isEmpty(trim)) {
                                            return;
                                        }
                                        pwd = trim;
                                        mPresenter.check_pwd();
                                        alertDialog.hide();
                                    }
                                })
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                        alertDialog.et_name.setHint("请输入登录密码");
                        alertDialog.et_price.setVisibility(View.GONE);
                        alertDialog.et_rl_price.setVisibility(View.GONE);
                        alertDialog.et_stock.setVisibility(View.GONE);
                        alertDialog.show();
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.show();
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
}
