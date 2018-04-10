package com.huafan.huafano2omanger.view.fragment.mine;

import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caimuhao.rxpicker.utils.DensityUtil;
import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.base.BaseApplication;
import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.O2oMineBean;
import com.huafan.huafano2omanger.entity.UserInfoEvent;
import com.huafan.huafano2omanger.event.BlueToothEvent;
import com.huafan.huafano2omanger.event.getMapEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.GlideImageLoader;
import com.huafan.huafano2omanger.utils.RxImageLoader;
import com.huafan.huafano2omanger.utils.SPUtils;
import com.huafan.huafano2omanger.view.activity.LoginActivity;
import com.huafan.huafano2omanger.view.activity.MapLocationActivity;
import com.huafan.huafano2omanger.view.activity.PrintSetActivity;
import com.huafan.huafano2omanger.view.customer.CircleImageView;
import com.huafan.huafano2omanger.view.customer.QcodeDialog;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.fragment.mine.bankcard.BankCardActivity;
import com.huafan.huafano2omanger.view.fragment.mine.dispatching.DispatchingSettingsActivity;
import com.huafan.huafano2omanger.view.fragment.mine.dobusiness.DoBusinessSettingsActivity;
import com.huafan.huafano2omanger.view.fragment.mine.invoice.SettingInvioceActivity;
import com.huafan.huafano2omanger.view.fragment.mine.orderreceiving.OrderReceivingActivity;
import com.huafan.huafano2omanger.view.fragment.mine.shopdetail.ShopDetailActivity;
import com.huafan.huafano2omanger.view.fragment.mine.updatepwd.UpdateLoginPwdActivity;
import com.huafan.huafano2omanger.view.fragment.mine.voice.SettingVoiceActivity;
import com.huafan.huafano2omanger.view.fragment.shop.messagemanagement.MessageManagementActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;


/**
 * @描述: 我的主页
 * @创建人：zhangpeisen
 * @创建时间：2017/12/21 上午10:47
 * @修改人：zhangpeisen
 * @修改时间：2017/12/21 上午10:47
 * @修改备注：
 * @throws
 */
public class MineFragment extends KFragment<IMineView, IMinePrenter> implements IMineView, View.OnClickListener {

    @BindView(R.id.im_bg)
    // 返回键
            ImageView imBg;
    @BindView(R.id.iv_message)
    // 消息中心入口
            FrameLayout ivMessage;
    @BindView(R.id.user_headerimg)
    // 商家头像
            CircleImageView userHeaderimg;
    @BindView(R.id.nickName)
    // 商家名称
            TextView nickName;
    @BindView(R.id.periodofvalidity_tv)
    // 有效日期
            TextView periodofvalidityTv;
    @BindView(R.id.QRcode_iv)
    // 商铺二维码
            ImageView QRcodeIv;
    @BindView(R.id.busSetup_tv)
    // 营业设置内容
            TextView busSetupTv;
    @BindView(R.id.bussetup_ly)
    // 营业设置入口
            LinearLayout bussetupLy;
    @BindView(R.id.ordertoset_tv)
    // 接单设置内容
            TextView ordertosetTv;
    @BindView(R.id.ordertoset_ly)
    // 接单设置入口
            LinearLayout ordertosetLy;
    @BindView(R.id.deliveryset_tv)
    // 配送设置内容
            TextView deliverysetTv;
    @BindView(R.id.deliveryset_ly)
    // 配送设置入口
            LinearLayout deliverysetLy;
    @BindView(R.id.mapcoord_tv)
    // 地图坐标设置内容
            TextView mapcoordTv;
    @BindView(R.id.mapcoord_ly)
    // 地图坐标设置入口
            LinearLayout mapcoordLy;
    @BindView(R.id.printset_tv)
    // 打印设置内容
            TextView printsetTv;
    @BindView(R.id.printset_ly)
    // 打印设置入口
            LinearLayout printsetLy;
    @BindView(R.id.msgvoiceset_tv)
    // 消息语音设置内容
            TextView msgvoicesetTv;
    @BindView(R.id.invoiceset_tv)
    // 发票设置内容
            TextView invoicesetTv;
    @BindView(R.id.motifypwd_ly)
    // 修改密码入口
            LinearLayout motifypwdLy;
    @BindView(R.id.logout_btn)
    // 注销登录按钮
            Button logoutBtn;
    @BindView(R.id.ll_voice)
    LinearLayout llVoice;
    @BindView(R.id.ll_invoice)
    LinearLayout llInvoice;
    @BindView(R.id.store_QRcode)
    TextView storeQRcode;
    @BindView(R.id.mine_personinfo_display)
    LinearLayout minePersoninfoDisplay;//店铺信息
    @BindView(R.id.bankcard_tv)
    TextView bankcardTv;
    @BindView(R.id.bankcard_ly)
    LinearLayout bankcardLy;
    @BindView(R.id.ll_bank)
    LinearLayout llBank;
    // 加载进度条
    private ShapeLoadingDialog shapeLoadingDialog;

    private String isLogin;
    private int imageSize;
    private String pay_qrcode;
    private PhotoView qrCodeImg;
    private Dialog mCameraDialog;

    public static MineFragment newInstance() {

        MineFragment mineFragment = new MineFragment();
        Bundle bundle = new Bundle();
        mineFragment.setArguments(bundle);
        return mineFragment;
    }

    @Override
    public IMinePrenter createPresenter() {

        return new IMinePrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();
//        mPresenter.merchcore();
//        mPresenter.getMaps();
        imageSize = DensityUtil.getDeviceWidth(userHeaderimg.getContext()) / 3;
    }

    @OnClick({R.id.iv_message, R.id.bussetup_ly, R.id.ordertoset_ly, R.id.ll_voice, R.id.ll_invoice,
            R.id.deliveryset_ly, R.id.mapcoord_ly, R.id.printset_ly, R.id.motifypwd_ly, R.id.logout_btn,
            R.id.user_headerimg, R.id.mine_personinfo_display, R.id.ll_bank, R.id.dianpu_erweicode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_message:

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {
                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {

                    // 消息中心
                    Intent intent = new Intent(getActivity(), MessageManagementActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.user_headerimg://个人信息

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {
                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    // 个人信息
                    Intent intent = new Intent(getActivity(), ShopDetailActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.bussetup_ly:

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {
                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    // 营业设置入口
                    startActivity(new Intent(getActivity(), DoBusinessSettingsActivity.class));
                }
                break;
            case R.id.ordertoset_ly:
                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {
                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    // 接单设置入口
                    startActivity(new Intent(getActivity(), OrderReceivingActivity.class));
                }
                break;
            case R.id.deliveryset_ly:

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {
                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {

                    // 配送设置入口
                    startActivity(new Intent(getActivity(), DispatchingSettingsActivity.class));
                }

                break;
            case R.id.mapcoord_ly:

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {
                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    // 地图坐标设置入口
                    startActivity(new Intent(getActivity(), MapLocationActivity.class));
                }
                break;
            case R.id.printset_ly:

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {
                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    // 打印设置入口
                    startActivity(new Intent(getActivity(), PrintSetActivity.class));
                }

                break;
            case R.id.motifypwd_ly:

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {
                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    // 修改密码入口
                    startActivity(new Intent(getActivity(), UpdateLoginPwdActivity.class));
                }
                break;
            case R.id.logout_btn:

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {

                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);

                } else {

                    // 注销登录按钮
                    isLogin = "";
                    EventBus.getDefault().post(new UserInfoEvent(null));
                    Constants.TOKEN = "";
                    SPUtils.put(getActivity(), "token", "");
                    SPUtils.put(BaseApplication.getContext(), Constants.ISLOGIN, "");
                    SPUtils.put(BaseApplication.getContext(), "region", "");
                }

                break;
            case R.id.ll_voice://语音提示

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {
                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), SettingVoiceActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.ll_invoice://发票设置

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {

                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);

                } else {

                    Intent intent = new Intent(getActivity(), SettingInvioceActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.dianpu_erweicode://查看店铺二维码
                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {

                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);

                } else {

                    showCodeView(pay_qrcode);
                }

                break;
            case R.id.mine_personinfo_display://店铺信息

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {

                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);

                } else {

                    Intent intent = new Intent(getActivity(), ShopDetailActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.ll_bank://银行卡信息

                isLogin = String.valueOf(SPUtils.get(BaseApplication.getContext(), Constants.ISLOGIN, ""));
                if (isLogin.equals("")) {

                    //去登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);

                } else {

                    Intent intent = new Intent(getActivity(), BankCardActivity.class);
                    startActivity(intent);
                }

                break;
        }
    }

    @Override
    public void showDialog() {
        shapeLoadingDialog.show();
    }

    @Override
    public void hideDialog() {
        if (shapeLoadingDialog.isShowing()) {
            shapeLoadingDialog.dismiss();
        }
    }

    @Override
    public void onError(String errorMsg) {
        showShortToast(errorMsg);
    }

    @Override
    public void onSuccess(O2oMineBean o2oMineBean) {
        if (o2oMineBean == null) {
            return;
        }
        O2oMineBean.InfoBean o2oMineBeanInfo = o2oMineBean.getInfo();
        if (o2oMineBeanInfo == null) {
            return;
        }
        // 店铺名称
        nickName.setText(TextUtils.isEmpty(o2oMineBeanInfo.getMerch_name()) ? "" : o2oMineBeanInfo.getMerch_name());
        // 商家logo
        String logoUrl = ApiUrlConstant.API_IMG_URL + o2oMineBeanInfo.getLogo_url();
        new RxImageLoader().display(userHeaderimg, logoUrl, imageSize, imageSize);
        pay_qrcode = ApiUrlConstant.API_IMG_URL + o2oMineBeanInfo.getPay_qrcode();
        new GlideImageLoader().displayImage(getActivity(), pay_qrcode, QRcodeIv);
        // 有效时间
        periodofvalidityTv.setText(o2oMineBeanInfo.getStarttime() + "-" + o2oMineBeanInfo.getEndtime());

        if (o2oMineBeanInfo.getBusiness_state().equals("1")) {
            busSetupTv.setText("营业中");
        } else {
            busSetupTv.setText("休息中");
        }

        if (o2oMineBeanInfo.getTake_setting().equals("1")) {
            ordertosetTv.setText("自动");
        } else if (o2oMineBeanInfo.getTake_setting().equals("2")) {
            ordertosetTv.setText("手动");
        } else {
            ordertosetTv.setText("未设置");
        }

        if (o2oMineBeanInfo.getDistrib_setting().equals("0")) {
            deliverysetTv.setText("不配送");
        } else if (o2oMineBeanInfo.getDistrib_setting().equals("1")) {
            deliverysetTv.setText("平台配送");
        } else if (o2oMineBeanInfo.getDistrib_setting().equals("2")) {
            deliverysetTv.setText("商家配送");
        } else {
            deliverysetTv.setText("到店自提");
        }

        if (o2oMineBeanInfo.getNotice_setting().equals("1")) {
            msgvoicesetTv.setText("响铃");
        } else {
            msgvoicesetTv.setText("语音");
        }

        if (o2oMineBeanInfo.getInvoice_setting().equals("0")) {
            invoicesetTv.setText("不支持发票");
        } else {
            invoicesetTv.setText("支持发票");
        }
        mapcoordTv.setText(o2oMineBeanInfo.getMap());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BlueToothEvent blueToothEvent) {
        if (blueToothEvent == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = blueToothEvent.getBluetoothDevice();
        if (bluetoothDevice == null) {
            return;
        }
        printsetTv.setText(TextUtils.isEmpty(bluetoothDevice.getName()) ? "打印机名称" : bluetoothDevice.getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        //判断是否登录
        String token = SPUtils.get(getActivity(), "token", "").toString();
        if (token.equals("") || TextUtils.isEmpty(token)) {
        } else {
            mPresenter.merchcore();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(getMapEvent getmapevent) {
        mPresenter.merchcore();
    }

    //展示二维码布局
    public void showCodeView(String s) {
        QcodeDialog dialog = new QcodeDialog(getActivity(), R.style.Dialog);//设置dialog的样式
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.dayangstyle); // 添加动画
        dialog.show();
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        //这句就是设置dialog横向满屏了。
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = (int) (d.getHeight() * 0.7);     //dialog屏幕占比
        window.setAttributes(lp);
//        ImageView qrCodeImg = (ImageView) dialog.findViewById(R.id.qr_code_img);
        qrCodeImg = (PhotoView) dialog.findViewById(R.id.qr_code_img);
        new GlideImageLoader().displayImage(getActivity(), s, qrCodeImg);
        qrCodeImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showSavaPhotoDialog();
                return false;
            }
        });
    }

    public void showSavaPhotoDialog() {
        mCameraDialog = new Dialog(getActivity(), R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.sava_image, null);
        root.findViewById(R.id.btn_choose_img).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
        lp.alpha = 9f; // 透明度
        root.measure(0, 0);
//        lp.height = root.getMeasuredHeight();
//        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_choose_img:
                MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),qrCodeImg.getDrawingCache(), "title", "description");
                showShortToast("保存成功");
                mCameraDialog.dismiss();
                break;
            case R.id.btn_cancel:
                mCameraDialog.dismiss();
                break;
        }
    }
}
