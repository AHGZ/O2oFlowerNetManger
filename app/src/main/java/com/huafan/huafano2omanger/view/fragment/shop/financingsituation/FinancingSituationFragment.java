package com.huafan.huafano2omanger.view.fragment.shop.financingsituation;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.entity.FinancingSituationBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.AppHorizontalTextViewAlertDialog;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.fragment.shop.financingsituation.financingdetail.FinancingDetailActivity;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 财务概况主界面
 */

public class FinancingSituationFragment extends KFragment<IFinancingSituationView, IFinancingSituationPresenter> implements IFinancingSituationView, NormalTopBar.normalTopClickListener {
    //自定义通用标题
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    //标题栏昨天
    @BindView(R.id.financing_txt_today)
    TextView financingTxtToday;
    //标题栏7天内
    @BindView(R.id.financing_txt_sevenday)
    TextView financingTxtSevenday;
    //标题栏30天内
    @BindView(R.id.financing_txt_thirtyday)
    TextView financingTxtThirtyday;
    //标题栏自定义
    @BindView(R.id.financing_txt_custom)
    TextView financingTxtCustom;
    //订单总额
    @BindView(R.id.financing_gridview_item_text12)
    TextView financingGridviewItemText12;
    //订单总额Relativelayout布局
    @BindView(R.id.financing_situation_relat1)
    RelativeLayout financingSituationRelat1;
    //店铺收入
    @BindView(R.id.financing_gridview_item_text22)
    TextView financingGridviewItemText22;
    //店铺收入Relativelayout布局
    @BindView(R.id.financing_situation_relat2)
    RelativeLayout financingSituationRelat2;
    //有效订单
    @BindView(R.id.financing_gridview_item_text32)
    TextView financingGridviewItemText32;
    //有效订单Relativelayout布局
    @BindView(R.id.financing_situation_relat3)
    RelativeLayout financingSituationRelat3;
    //无效订单
    @BindView(R.id.financing_gridview_item_text42)
    TextView financingGridviewItemText42;
    //无效订单Relativelayout布局
    @BindView(R.id.financing_situation_relat4)
    RelativeLayout financingSituationRelat4;

    private ShapeLoadingDialog shapeLoadingDialog;
    private AppHorizontalTextViewAlertDialog alertDialog;

    public static FinancingSituationFragment newInstance() {
        Bundle args = new Bundle();
        FinancingSituationFragment fragment = new FinancingSituationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IFinancingSituationPresenter createPresenter() {
        return new IFinancingSituationPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_financing_situation;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        normalTop.setTitleText("财务概况");
        normalTop.setTopClickListener(this);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        //初始化加载进度条
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .delay(5000)
                .loadText("加载中...")
                .build();
        //state:1为昨天,2为7天内3为30天内,4为自选
        //默认为昨天
        mPresenter.selectFinancingSituation(1, "", "");

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

    //标题栏今天，标题栏7天内，标题栏30天内，标题栏自定义点击事件监听
    @OnClick({R.id.financing_txt_today, R.id.financing_txt_sevenday, R.id.financing_txt_thirtyday, R.id.financing_txt_custom, R.id.financing_situation_relat3, R.id.financing_situation_relat4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //昨天
            case R.id.financing_txt_today:
                mPresenter.selectFinancingSituation(1, "", "");
                financingTxtToday.setTextColor(Color.parseColor("#FF1E00"));
                financingTxtSevenday.setTextColor(Color.parseColor("#494949"));
                financingTxtThirtyday.setTextColor(Color.parseColor("#494949"));
                financingTxtCustom.setTextColor(Color.parseColor("#494949"));
                break;
            //7天内
            case R.id.financing_txt_sevenday:
                mPresenter.selectFinancingSituation(2, "", "");
                financingTxtToday.setTextColor(Color.parseColor("#494949"));
                financingTxtSevenday.setTextColor(Color.parseColor("#FF1E00"));
                financingTxtThirtyday.setTextColor(Color.parseColor("#494949"));
                financingTxtCustom.setTextColor(Color.parseColor("#494949"));
                break;
            //30天内
            case R.id.financing_txt_thirtyday:
                mPresenter.selectFinancingSituation(3, "", "");
                financingTxtToday.setTextColor(Color.parseColor("#494949"));
                financingTxtSevenday.setTextColor(Color.parseColor("#494949"));
                financingTxtThirtyday.setTextColor(Color.parseColor("#FF1E00"));
                financingTxtCustom.setTextColor(Color.parseColor("#494949"));
                break;
            //自定义
            case R.id.financing_txt_custom:
                financingTxtToday.setTextColor(Color.parseColor("#494949"));
                financingTxtSevenday.setTextColor(Color.parseColor("#494949"));
                financingTxtThirtyday.setTextColor(Color.parseColor("#494949"));
                financingTxtCustom.setTextColor(Color.parseColor("#FF1E00"));
                showAlertHorizontalEdittextDialog("添加单位", "确定", "取消");
                break;
            //有效订单点击事件
            case R.id.financing_situation_relat3:
                startActivity(new Intent(getActivity(), FinancingDetailActivity.class));
                break;
            //无效订单点击事件
            case R.id.financing_situation_relat4:
                startActivity(new Intent(getActivity(), FinancingDetailActivity.class));
                break;
        }
    }

    //添加财务单位对话框
    public void showAlertHorizontalEdittextDialog(String title, String positive, String negative) {
        alertDialog = new AppHorizontalTextViewAlertDialog(getActivity()).builder().setTitle(title)
                .setPositiveButton(positive, new AppHorizontalTextViewAlertDialog.OnposClickLitener() {
                    @Override
                    public void onPosEditClick(String trim, String trim1) {
                        mPresenter.selectFinancingSituation(4, trim, trim1);
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.et_left.setHint("开始日期");
        alertDialog.et_right.setHint("结束日期");
        alertDialog.et_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimePicker(v);
            }
        });
        alertDialog.et_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimePicker(v);
            }
        });
        alertDialog.et_dividing_line.setText("—");
        alertDialog.show();
    }

    //    public void onTimePicker(View view, TextView textView) {
//        //默认选中当前时间
//        Calendar nowCalendar = Calendar.getInstance();
//        int nowyear = nowCalendar.get(GregorianCalendar.YEAR);
//        int nowmonth = nowCalendar.get(GregorianCalendar.MONTH);
//        int nowday = nowCalendar.get(GregorianCalendar.DATE);
//        DatePickers picker = new DatePickers(getActivity(),nowyear,2,nowday-1);
//        picker.setTitleText("选择日期");
//        picker.setSubmitTextColor(Color.parseColor("#FF2E00"));//确定
//        picker.setCancelTextColor(Color.parseColor("#7B838D"));//取消
//        picker.setTitleTextColor(Color.parseColor("#656565"));
//        picker.setTextColor(Color.parseColor("#333333"));
//
//        picker.setSelectedItem(nowyear,nowmonth + 1, nowday + 1);
//        picker.setOnDatePickListener(new DatePickers.OnYearMonthDayPickListener() {
//            @Override
//            public void onDatePicked(String year, String month, String day) {
//
//                if (view.getId() == R.id.tv_start_time) {//有效时间
//
//                } else if (view.getId() == R.id.tv_end_time) {
//
//                } else if (view.getId() == R.id.tv_ky_start_time) {//可用时间
//
//                } else if (view.getId() == R.id.tv_ky_end_time) {
//
//                }
//
//                textView.setText(year + "-" + month + "-" + day);
//            }
//        });
//        picker.show();
//    }
    public void onTimePicker(View view) {

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        if (view == alertDialog.et_left) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String timetodates = sdf.format(new Date(millseconds));
                            alertDialog.et_left.setText(timetodates);
                        } else {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String timetodates = sdf.format(new Date(millseconds));
                            alertDialog.et_right.setText(timetodates);
                        }
                    }
                })
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("选择日期")
                .setCyclic(false)
                .setMaxMillseconds(System.currentTimeMillis() - 86400000)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(Color.parseColor("#EEEEEE"))
                .setWheelItemTextSelectorColor(Color.parseColor("#000000"))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(getActivity().getSupportFragmentManager(), "TimePicker");
    }

    @Override
    public void onError(String errorMsg) {

    }

    @Override
    public void onSuccess(FinancingSituationBean financingSituationBean) {

        //赋值订单总额
        financingGridviewItemText12.setText("¥ " + financingSituationBean.getOrder_amount());
        //赋值店铺收入
        financingGridviewItemText22.setText("¥ " + financingSituationBean.getIncome());
        //赋值有效订单
        financingGridviewItemText32.setText(financingSituationBean.getValid_order_count() + "");
        //赋值无效订单
        financingGridviewItemText42.setText(financingSituationBean.getInvalid_order_count() + "");
    }

    @Override
    public void onSuccess(String message) {

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
