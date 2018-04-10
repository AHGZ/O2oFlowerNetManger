package com.huafan.huafano2omanger.view.fragment.pending.waitdispose;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.WaitDisposAdapter;
import com.huafan.huafano2omanger.entity.SelfMotionBean;
import com.huafan.huafano2omanger.entity.UserInfoEvent;
import com.huafan.huafano2omanger.entity.WaitDisposeBean;
import com.huafan.huafano2omanger.event.OrderWaitBackEvent;
import com.huafan.huafano2omanger.event.PushMsgEvent;
import com.huafan.huafano2omanger.event.WaitDisposeEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.SPUtils;
import com.huafan.huafano2omanger.view.customer.AppEditextAlertDialog;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;
import com.huafan.huafano2omanger.view.fragment.mine.printset.dialogs.BluetoothTestDialogFragment;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import am.util.printer.PrinterWriter;
import am.util.printer.PrinterWriter80mm;
import butterknife.BindView;


/**
 * Created by caishen on 2018/1/9.
 * by--待处理订单
 */

public class WaitDisposeFragment extends KFragment<IWaitDisposeView, IWaitDisposePrenter>
        implements IWaitDisposeView {

    @BindView(R.id.et_listView)
    ExpandableListView etListView;
    @BindView(R.id.pull_refresh)
    PullToRefreshLayout pullRefresh;
    private ShapeLoadingDialog shapeLoadingDialog;
    private String state = "1";//1-待接单 2-待发货/跑腿取餐 3-待收货 4-已收货  10-交易完成
    private int page = 1;
    private boolean isLoad = false;
    private List<WaitDisposeBean.ListBean> list;
    private WaitDisposAdapter mAdapter;
    private int count = 0;
    private static final String FRAGMENT_BLUETOOTH = "bluetooth";
    private int type = PrinterWriter80mm.TYPE_80;
    private int height = PrinterWriter.HEIGHT_PARTING_DEFAULT;
    private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private AlertDialog dlgBluetoothOpen;
    private boolean shouldOpen = false;
    private boolean shouldClose = false;

    // 蓝牙广播
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                if (bluetoothAdapter.isEnabled()) {
                    if (shouldOpen) {
                        shouldOpen = false;
                        showBluetoothTest(listBean, merch_name, today_count);
                        shouldClose = true;
                    }
                } else {
                    Fragment fragment = getFragmentManager().findFragmentByTag(FRAGMENT_BLUETOOTH);
                    if (fragment != null) {
                        BluetoothTestDialogFragment bluetooth = (BluetoothTestDialogFragment) fragment;
                        bluetooth.dismissAllowingStateLoss();
                    }
                }
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                Fragment fragment = getFragmentManager().findFragmentByTag(FRAGMENT_BLUETOOTH);
                if (fragment != null && fragment instanceof BluetoothTestDialogFragment) {
                    BluetoothTestDialogFragment bluetooth = (BluetoothTestDialogFragment) fragment;
                    bluetooth.updateAdapter();
                }
            }
        }

    };
    private String order_id = "";
    private List<WaitDisposeBean.ListBean> datas;
    private WaitDisposeBean.ListBean listBean;
    private String today_count;
    private String merch_name;
    private MediaPlayer mediaPlayer;

    @Override
    public IWaitDisposePrenter createPresenter() {
        return new IWaitDisposePrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_wait_dispose;
    }

    public static Fragment newIntence(String s) {

        WaitDisposeFragment waitDisposeFragment = new WaitDisposeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("state", s);
        waitDisposeFragment.setArguments(bundle);
        return waitDisposeFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        state = arguments.getString("state");
    }

    @Override
    public void initData() {

        //判断是否登录
        String token = SPUtils.get(getActivity(), "token", "").toString();
        if (token.equals("") || TextUtils.isEmpty(token)) {

        } else {
            mPresenter.getWaitDis();
        }
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

        switch (myvalue) {
            case "20"://您有新的花返退款申请,待处理
                break;
            case "21"://您有新的花返外卖订单,请及时处理
                mPresenter.getWaitDis();
                break;
            case "22": //您有新的花返推送消息,请查收


                break;
            case "23": //花返已经为您自动接单
                //自动打印餐单
                mPresenter.getselOrderInfo();

                break;
        }
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        getActivity().registerReceiver(receiver, intentFilter);

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        initData();

        //设置下拉加载更多数据
        pullRefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {

                page = 1;
                count = 0;
                mPresenter.getWaitDis();
                pullRefresh.finishRefresh();
            }

            @Override
            public void loadMore() {

                if (mAdapter != null) {

                    if (count >= mAdapter.getGroupCount()) {

                        isLoad = true;
                        page += 1;
                        mPresenter.getWaitDis();

                    } else {

                        showShortToast("没有更多数据了！");
                    }
                }

                pullRefresh.finishLoadMore();
            }
        });
    }


    private int convertDpToPixel(int dp) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        return (int) (dp * displayMetrics.density);
    }

    @Override
    public void showDialog() {
        shapeLoadingDialog.show();
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void hideDiaglog() {

        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserInfoEvent userInfoEvent) {

        if (userInfoEvent.getUserInfo() == null) {//未登录

        } else {//已登录

            initData();
        }
    }


    @Override
    public void onSuccessData(WaitDisposeBean data) {

        if (data != null) {

            //发送消息刷新数量
            EventBus.getDefault().post(new WaitDisposeEvent("0", data.getCount()));//0-待处理订单 1待处理退款

            list = data.getList();
            count = list.size();

            if (!isLoad) {

                datas = list;

                //设置适配器
                mAdapter = new WaitDisposAdapter(getActivity(), datas);
                etListView.setAdapter(mAdapter);

                //设置右边的点击事件
                mAdapter.setOnRightButtonStateClickLitener(new WaitDisposAdapter.OnRightButtonStateClickLitener() {
                    @Override
                    public void rightButtonStateLitener(View view, int position, String id) {

                        //tag 0 打印餐单 1 取消订单 2确认发货 3 确认收货
                        switch (view.getTag().toString()) {
                            case "0"://打印餐单

                                today_count = data.getToday_count();
                                merch_name = data.getMerch_name();
                                WaitDisposeBean.ListBean listBean = datas.get(position);
                                // 蓝牙打印
                                checkBluetooth(listBean, merch_name, today_count);

                                break;
                            case "1"://取消订单

                                showAlertEdittextDialog("取消订单", "是否确认取消订单", "确定", "取消");

                                break;
                            case "2"://确认发货

                                order_id = id;
                                showAlertMsgDialog("确认发货", "确定发货？", "确定", "取消", "2");

                                break;
                            case "3"://确认收货

                                order_id = id;
                                showAlertMsgDialog("确认收货", "确定收货？", "确定", "取消", "2");

                                break;
                        }
                    }
                });


                //设置中间的点击事件
                mAdapter.setOnCenterButtonStateClickLitener(new WaitDisposAdapter.OnCenterButtonStateClickLitener() {
                    @Override
                    public void centerButtonStateLitener(View view, int position, String id) {

                        //tag 0 打印餐单 1 取消订单 2确认发货 3 确认收货
                        switch (view.getTag().toString()) {
                            case "0"://打印餐单

                                today_count = data.getToday_count();
                                merch_name = data.getMerch_name();
                                WaitDisposeBean.ListBean listBean = datas.get(position);
                                // 蓝牙打印
                                checkBluetooth(listBean, merch_name, today_count);

                                break;
                            case "1"://取消订单

                                showAlertEdittextDialog("取消订单", "是否确认取消订单", "确定", "取消");

                                break;
                            case "2"://确认发货

                                order_id = id;
                                showAlertMsgDialog("确认发货", "确定发货？", "确定", "取消", "2");

                                break;
                            case "3"://确认收货

                                order_id = id;
                                showAlertMsgDialog("确认收货", "确定收货？", "确定", "取消", "2");

                                break;
                        }
                    }
                });

                //设置左边的点击事件
                mAdapter.setOnLeftButtonStateClickLitener(new WaitDisposAdapter.OnLeftButtonStateClickLitener() {
                    @Override
                    public void leftButtonStateLitener(View view, int position, String id) {

                        //tag 0 打印餐单 1 取消订单 2确认发货 3 确认收货
                        switch (view.getTag().toString()) {
                            case "0"://打印餐单

                                today_count = data.getToday_count();
                                merch_name = data.getMerch_name();
                                WaitDisposeBean.ListBean listBean = datas.get(position);
                                // 蓝牙打印
                                checkBluetooth(listBean, merch_name, today_count);

                                break;
                            case "1"://取消订单

                                showAlertEdittextDialog("取消订单", "是否确认取消订单", "确定", "取消");

                                break;
                            case "2"://确认发货

                                order_id = id;
                                showAlertMsgDialog("确认发货", "确定发货？", "确定", "取消", "2");

                                break;
                            case "3"://确认收货

                                order_id = id;
                                showAlertMsgDialog("确认收货", "确定收货？", "确定", "取消", "2");

                                break;
                        }
                    }
                });

                //设置接单的点击事件
                mAdapter.setOnButtonClickLitener(new WaitDisposAdapter.OnButtonClickLitener() {
                    @Override
                    public void buttonClickLitener(View view, int position, String id) {

                        order_id = id;
                        showAlertMsgDialog("确认接单", "确定接单？", "确定", "取消", "0");
                    }
                });

                //设置拒绝接单的点击事件
                mAdapter.setOnRefuseButtonClickLitener(new WaitDisposAdapter.OnRefuseButtonClickLitener() {
                    @Override
                    public void refuseButtonClickLitener(View view, int position, String id) {

                        order_id = id;
                        showAlertMsgDialog("拒绝接单", "确定拒绝接单？", "确定", "取消", "1");
                    }
                });


                //隐藏下拉按钮
                etListView.setGroupIndicator(null);

                //让exListView不能被点击
                etListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        return true;
                    }
                });


                //展开收起的点击事件
                mAdapter.setOnEvulateShowClickLitener(new WaitDisposAdapter.OnEvulateShowClickLitener() {
                    @Override
                    public void evulateClickLitener(View view, int position, boolean isShow) {

                        if (isShow) {//展开
                            etListView.expandGroup(position);//展开
                        } else {
                            etListView.collapseGroup(position);//收起
                        }
                    }
                });

            } else {

                isLoad = false;

                if (list.size() > 0) {

                    datas.addAll(list);
                    mAdapter.notifyDataSetChanged();

                } else {

                    showShortToast("没有更多数据了！");
                }
            }
        }
    }

    /**
     * @Title: showAlertMsgDialog @Description: TODO 弹出消息框 @param @param
     * msg @param @param title @param @param positive @param @param
     * negative @return void 返回类型 @throws
     */
    public void showAlertMsgDialog(String msg, String title, String positive, String negative, String id) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        if (id.equals("0")) {//确认接单

                            mPresenter.confirmOrder();

                        } else if (id.equals("1")) {//拒绝接单

                            mPresenter.refuseOrder();

                        } else if (id.equals("2")) {//确认收货

                            mPresenter.confirmCom();
                        }
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.show();
    }

    //添加取消订单原因对话框
    public void showAlertEdittextDialog(String title, String message, String positive, String negative) {

        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle(title);
        alertDialog.setMsg(message);
        alertDialog.setPositiveButton(positive, new AppEditextAlertDialog.OnposClickLitener() {
            @Override
            public void onPosEditClick(String s, String trim, String trim1, String trim2) {
                if (s.equals("") || trim.equals("")) {
                    showShortToast("输入内容不能为空，请检查您输入的内容");
                } else {
                    mPresenter.cancelOrder(s);
                    alertDialog.hide();
                }
            }
        })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.et_name.setHint("请输入取消原因");
        alertDialog.txt_message.setVisibility(View.VISIBLE);
        alertDialog.remove(alertDialog.et_rl_price);
        alertDialog.remove(alertDialog.et_stock);
        alertDialog.remove(alertDialog.et_price);
        alertDialog.show();
    }


    @Override
    public void onError(String s) {

        showShortToast(s);
    }

    @Override
    public int getPage() {
        return page;
    }


    private void checkBluetooth(WaitDisposeBean.ListBean datas, String merch_name, String today_count) {
        if (bluetoothAdapter == null) {
            showShortToast(getResources().getString(R.string.printer_bluetooth_not_support));
            return;
        }
        if (bluetoothAdapter.isEnabled()) {
            if (dlgBluetoothOpen != null && dlgBluetoothOpen.isShowing())
                dlgBluetoothOpen.dismiss();
            // 载入设备
            showBluetoothTest(datas, merch_name, today_count);

        } else {

            showForceTurnOnBluetoothDialog();
        }
    }

    private void showBluetoothTest(WaitDisposeBean.ListBean datas, String merch_name, String today_count) {

        int width = 650;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(FRAGMENT_BLUETOOTH);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        BluetoothTestDialogFragment fragment = BluetoothTestDialogFragment.getFragment(datas, merch_name, today_count, type, width, height, "lanya");
        fragment.show(ft, FRAGMENT_BLUETOOTH);

    }

    private void showForceTurnOnBluetoothDialog() {

        if (dlgBluetoothOpen == null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.printer_bluetooth_dlg_open);
            builder.setNegativeButton(R.string.printer_deny, null);
            builder.setPositiveButton(R.string.printer_allow,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            forceTurnOnBluetooth();
                        }
                    });
            dlgBluetoothOpen = builder.create();
        }
        dlgBluetoothOpen.show();
    }

    private void forceTurnOnBluetooth() {

        final boolean open = bluetoothAdapter.isEnabled() || bluetoothAdapter.enable();
        shouldOpen = true;
        if (!open) {
            showSystemTurnOnBluetoothDialog();
        }
    }

    private void showSystemTurnOnBluetoothDialog() {

        Intent requestBluetoothOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestBluetoothOn.setAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        requestBluetoothOn.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);
        startActivity(requestBluetoothOn);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(OrderWaitBackEvent event) {
        back();
    }


    public void back() {

        if (shouldClose && bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.printer_bluetooth_dlg_close);
            builder.setNegativeButton(R.string.printer_no,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            shouldClose = false;
                            removeFragment();
                        }
                    });
            builder.setPositiveButton(R.string.printer_yes,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (bluetoothAdapter.disable()) {
                                shouldClose = false;
                                removeFragment();
                            } else {
                                showShortToast(getResources().getString(R.string.printer_bluetooth_dlg_close_error));
                                shouldClose = false;
                                removeFragment();
                                startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                            }
                        }
                    });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    shouldClose = false;
                    removeFragment();
                }
            });
            builder.create().show();
        } else {
            removeFragment();
        }
    }

    @Override
    public String getOrderId() {
        return order_id;
    }

    @Override
    public void onsuccess(String message) {

        showShortToast(message);
        mPresenter.getWaitDis();
    }

    /***
     * 自动打印餐单数据成功回调
     * @param data
     */
    @Override
    public void onsuccessSelf(SelfMotionBean data) {

        if (data != null) {

            if (data.getList() != null && data.getList().size() > 0) {

                // 蓝牙打印
                checkBluetooth(data.getList().get(0),
                        data.getMerch_name() == null ? "" : data.getMerch_name(),
                        data.getToday_count() == null ? "" : data.getToday_count());
            }
        }
    }
}
