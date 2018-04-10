package com.huafan.huafano2omanger.view.fragment.mine.printset;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.event.BlueToothEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.utils.appstatus.Eyes;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.fragment.mine.printset.dialogs.BluetoothTestDialogFragment;
import com.huafan.huafano2omanger.view.fragment.mine.printset.dialogs.IPTestDialogFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import am.util.printer.PrinterWriter;
import am.util.printer.PrinterWriter80mm;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * @描述: 打印设置页面
 * @创建人：zhangpeisen
 * @创建时间：2017/12/25 下午6:28
 * @修改人：zhangpeisen
 * @修改时间：2017/12/25 下午6:28
 * @修改备注：
 * @throws
 */
public class PrintSetFragment extends KFragment<IPrintSetView, IPrintSetPrenter> implements NormalTopBar.normalTopClickListener {
    @BindView(R.id.normal_top)
    //自定义通用标题
            NormalTopBar normalTopBar;
    @BindView(R.id.netprinter_ly)
    // 网络打印
            LinearLayout netprinterLy;
    @BindView(R.id.bluetoothprinter_ly)
    // 蓝牙打印
            LinearLayout bluetoothprinterLy;
    @BindView(R.id.save_btn)
    Button saveBtn;

    private static final String FRAGMENT_IP = "ip";
    private static final String FRAGMENT_BLUETOOTH = "bluetooth";
    @BindView(R.id.bluetoothprinter_tv)
    TextView bluetoothprinterTv;
    @BindView(R.id.netprinter_tv)
    TextView netprinterTv;
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
                        showBluetoothTest();
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

    public static PrintSetFragment newInstance() {
        PrintSetFragment printSetFragment = new PrintSetFragment();
        Bundle bundle = new Bundle();
        printSetFragment.setArguments(bundle);
        return printSetFragment;
    }

    @Override
    public IPrintSetPrenter createPresenter() {
        return new IPrintSetPrenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.mine_printset_fragment;
    }

    @Override
    public void initData() {

    }


    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        //初始化沉浸式
        Eyes.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        // 初始化标题及相关事件监听
        normalTopBar.setTitleText("打印设置");
        normalTopBar.getRightImage().setVisibility(View.GONE);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTopBar.getLeftImage(), 50);
        normalTopBar.setTopClickListener(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        getActivity().registerReceiver(receiver, intentFilter);
    }


    @OnClick({R.id.netprinter_ly, R.id.bluetoothprinter_ly, R.id.save_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.netprinter_ly:
                // 网络打印
                showIpTest();
                break;
            case R.id.bluetoothprinter_ly:
                // 蓝牙打印
                checkBluetooth();
                break;
            case R.id.save_btn:
                // 保存按钮
                removeFragment();
                break;
        }
    }


    private void showIpTest() {
        int width;

        width = 500;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(FRAGMENT_IP);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        IPTestDialogFragment fragment = IPTestDialogFragment
                .getFragment(type, width, height, "1235685684");
        fragment.show(ft, FRAGMENT_IP);
    }


    private void checkBluetooth() {
        if (bluetoothAdapter == null) {
            showShortToast(getResources().getString(R.string.printer_bluetooth_not_support));
            return;
        }
        if (bluetoothAdapter.isEnabled()) {
            if (dlgBluetoothOpen != null && dlgBluetoothOpen.isShowing())
                dlgBluetoothOpen.dismiss();
            // 载入设备
            showBluetoothTest();
        } else {
            showForceTurnOnBluetoothDialog();
        }
    }

    private void showBluetoothTest() {
        int width = 500;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(FRAGMENT_BLUETOOTH);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        BluetoothTestDialogFragment fragment = BluetoothTestDialogFragment.getFragment(null, "", "", type, width, height, "lanya");
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

    @Override
    public void onLeftClick(View view) {
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
    public void onRightClick(View view) {

    }

    @Override
    public void onTitleClick(View view) {

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
        bluetoothprinterTv.setText(TextUtils.isEmpty(bluetoothDevice.getName()) ? "打印机名称" : bluetoothDevice.getName());
    }


}
