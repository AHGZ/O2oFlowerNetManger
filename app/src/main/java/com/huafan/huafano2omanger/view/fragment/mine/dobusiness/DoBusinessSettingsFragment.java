package com.huafan.huafano2omanger.view.fragment.mine.dobusiness;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.DoBusinessAdapter;
import com.huafan.huafano2omanger.entity.DobusinessBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.TimeTools;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.DoAppEditextAlertDialog;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.TimePicker;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by heguozhong on 2017/12/21/021.
 * 营业设置主界面
 */

public class DoBusinessSettingsFragment extends KFragment<IDoBusinessSettingsView,
        IDoBusinessSettingsPresenter> implements IDoBusinessSettingsView, NormalTopBar.normalTopClickListener {

    //自定义通用标题
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;

    //展示营业时间的recyclerview
    @BindView(R.id.doBusinessRecyclerview)
    RecyclerView doBusinessRecyclerview;
    //添加营业时间红色按钮
    @BindView(R.id.add_business_hours)
    FloatingActionButton addBusinessHours;
    private DoBusinessAdapter doBusinessAdapter;
    private List<DobusinessBean.ListsBean> lists;
    private CheckBox doBusinessStateImg;
    private ShapeLoadingDialog shapeLoadingDialog;

    public static DoBusinessSettingsFragment newInstance() {
        Bundle args = new Bundle();
        DoBusinessSettingsFragment fragment = new DoBusinessSettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public IDoBusinessSettingsPresenter createPresenter() {
        return new IDoBusinessSettingsPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_do_business_settings;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        normalTop.setTitleText("营业设置");
        normalTop.setTopClickListener(this);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        //初始化加载进度条
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .delay(5000)
                .loadText("加载中...")
                .build();

        //调用查询营业时间接口
        mPresenter.getDobusiness();

        //将悬浮按钮附着到recyclerview上
        addBusinessHours.attachToRecyclerView(doBusinessRecyclerview);

        doBusinessRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    addBusinessHours.show();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
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

    @OnClick({R.id.add_business_hours})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //设置添加营业时间监听
            case R.id.add_business_hours:
                //弹窗添加营业时间对话框
                if (lists.size() < 5) {
                    showAlertEdittextDialog("添加营业时间", "确定", "取消");
                } else {
                    showShortToast("营业时间最多添加5条，不能再添加了");
                }

                break;

        }
    }


    @Override
    public void onError(String errorMsg) {

        showShortToast(errorMsg);
        if (doBusinessStateImg != null) {
            doBusinessStateImg.setBackgroundResource(R.drawable.close);
        }

    }

    @Override
    public void onSuccess(DobusinessBean dobusinessBean) {
        lists = dobusinessBean.getLists();

        //获取布局管理者
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置为垂直排列格式
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerview应用垂直排列格式
        doBusinessRecyclerview.setLayoutManager(linearLayoutManager);
        //设置适配器
        doBusinessAdapter = new DoBusinessAdapter(getActivity(), lists, dobusinessBean);
        doBusinessRecyclerview.setAdapter(doBusinessAdapter);

        //开始结束营业时间linearlayout
        doBusinessAdapter.setOnLinearClickListener(new DoBusinessAdapter.OnLinearClickListener() {

            @Override
            public void onLinearClick(View v, int position) {
                //弹出修改营业时间对话框
                showAlertEdittextDialog2("修改营业时间", "确定", "取消", position);
            }
        });
        //删除营业结束时间
        doBusinessAdapter.setOnDeleteEndTimeClickListener(new DoBusinessAdapter.OnDeleteEndTimeClickListener() {

            @Override
            public void onDeleteEndTimeClick(View v, int position) {
                //弹出删除营业时间对话框
                showAlertMsgDialog("确定删除吗?", "确认删除", "确定", "取消", position);
            }
        });
        //营业状态
        doBusinessAdapter.setOnStateClickListener(new DoBusinessAdapter.OnStateClickListener() {

            @Override
            public void onStateClick(View v) {
                //状态框
                doBusinessStateImg = (CheckBox) v.findViewById(R.id.doBusinessState_img);
                if (dobusinessBean.getState() == 0) {
                    //调取更改状态的接口
                    mPresenter.updateDobusiness(1);
                } else if (dobusinessBean.getState() == 1) {
                    //调取更改状态的接口
                    mPresenter.updateDobusiness(0);
                }

            }
        });
    }

    @Override
    public void onSuccess(String message) {
        showShortToast(message);
        mPresenter.getDobusiness();
        doBusinessAdapter.notifyDataSetChanged();
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

    //添加营业时间对话框
    public void showAlertEdittextDialog(String title, String positive, String negative) {

        DoAppEditextAlertDialog alertDialog = new DoAppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle(title);
        alertDialog.setPositiveButton(positive, new DoAppEditextAlertDialog.OnposClickLitener() {

            @Override
            public void onPosEditClick(String s, String trim, String trim1) {
                if (s.equals("") || trim.equals("") || trim1.equals("")) {
                    showShortToast("输入内容不能为空，请检查您输入的内容");
                } else if (trim1.equals(".")) {
                    showShortToast("请输入正确的配送费用，不能只输入字符");
                } else {
                    String startTime = s.replace(":", ",");
                    String endTime = trim.replace(":", ",");
                    if (Integer.parseInt(startTime.replace(",", "")) >= Integer.parseInt(endTime.replace(",", ""))) {
                        showShortToast("请检查您输入的时间，结束时间不能早于开始时间切不能等于开始时间");
                    } else {
                        //调用添加营业时间接口
                        mPresenter.addDobusinessTime(startTime, endTime, Float.parseFloat(trim1));
                        alertDialog.hide();
                    }


                }
            }

        })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.et_name.setHint("开始时间");
        alertDialog.et_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimePicker(v, alertDialog.et_name);
            }
        });
        alertDialog.et_price.setHint("结束时间");
        alertDialog.et_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimePicker(v, alertDialog.et_price);
            }
        });
        alertDialog.et_rl_price.setHint("配送费用");
        alertDialog.show();
    }

    //修改营业时间对话框
    public void showAlertEdittextDialog2(String title, String positive, String negative, int position) {
        DoAppEditextAlertDialog alertDialog = new DoAppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle(title);
        alertDialog.setPositiveButton(positive, new DoAppEditextAlertDialog.OnposClickLitener() {

            @Override
            public void onPosEditClick(String s, String trim, String trim1) {
                if (s.equals("") || trim.equals("") || trim1.equals("")) {
                    showShortToast("输入内容不能为空，请检查您输入的内容");
                } else if (trim1.equals(".")) {
                    showShortToast("请输入正确的配送费用，不能只输入字符");
                } else {
                    String startTime = s.replace(":", ",");
                    String endTime = trim.replace(":", ",");
                    if (Integer.parseInt(startTime.replace(",", "")) >= Integer.parseInt(endTime.replace(",", ""))) {
                        showShortToast("请检查您输入的时间，结束时间不能早于开始时间切不能等于开始时间");
                    } else {
                        //调用更改营业时间接口
                        mPresenter.updateDobusinessTime(lists.get(position).getId(), startTime, endTime, Float.parseFloat(trim1));
                        alertDialog.hide();
                    }

                }
            }

        })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.et_name.setHint("开始时间");
        String startTime = TimeTools.getCountTimeByLong(Long.parseLong(String.valueOf(lists.get(position).getStarttime())));
        alertDialog.et_name.setText(startTime);
        alertDialog.et_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimePicker(v, alertDialog.et_name);
            }
        });
        alertDialog.et_price.setHint("结束时间");
        String endTime = TimeTools.getCountTimeByLong(Long.parseLong(String.valueOf(lists.get(position).getEndtime())));
        alertDialog.et_price.setText(endTime);
        alertDialog.et_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimePicker(v, alertDialog.et_price);
            }
        });
        alertDialog.et_rl_price.setHint("配送费用");
        alertDialog.et_rl_price.setText(lists.get(position).getDistrib_money() + "");
        alertDialog.show();
    }

    public void onTimePicker(View view, TextView textView) {
        //默认选中当前时间
        TimePicker picker = new TimePicker(getActivity());
        picker.setTopLineVisible(false);
        if (view.getId() == R.id.et_name) {
            picker.setTitleText("开始时间");
        } else {
            picker.setTitleText("结束时间");
        }

        picker.setSubmitTextColor(Color.parseColor("#FF2E00"));//确定
        picker.setCancelTextColor(Color.parseColor("#7B838D"));//取消
        picker.setTitleTextColor(Color.parseColor("#656565"));
        picker.setTextColor(Color.parseColor("#333333"));
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                if (view.getId() == R.id.et_name) {
                    textView.setText(hour + ":" + minute);
                } else {
                    textView.setText(hour + ":" + minute);
                }
            }
        });
        picker.show();
    }

    //弹出删除对话框
    public void showAlertMsgDialog(String msg, String title, String positive, String negative, int position) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //调用删除营业时间接口
                        mPresenter.deleteDobusinessTime(lists.get(position).getId());
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        alertDialog.show();
    }
}
