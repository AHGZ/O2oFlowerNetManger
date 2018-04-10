package com.huafan.huafano2omanger.view.fragment.shop.fullmanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.FullManagementAdapter;
import com.huafan.huafano2omanger.entity.FullManagementBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.AppHorizontalEditextAlertDialog;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by heguozhong on 2017/12/26/026.
 * 满返管理主界面
 */

public class FullManagementFragment extends KFragment<IFullManagementView, IFullManagementPresenter> implements IFullManagementView,NormalTopBar.normalTopClickListener {

    //自定义通用标题
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    //绑定满返管理listview
    @BindView(R.id.full_management_recyclerview)
    RecyclerView fullManagementRecyclerview;
    //红色添加按钮
    @BindView(R.id.full_management_add)
    FloatingActionButton fullManagementAdd;
    private List<FullManagementBean.ListBean> list;
    private FullManagementAdapter fullManagementAdapter;
    private ShapeLoadingDialog shapeLoadingDialog;

    public static FullManagementFragment newInstance() {
        Bundle args = new Bundle();
        FullManagementFragment fragment = new FullManagementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IFullManagementPresenter createPresenter() {
        return new IFullManagementPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_full_management;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        normalTop.setTitleText("满返管理");
        normalTop.setTopClickListener(this);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);

        //将悬浮按钮附着到Listview上
        fullManagementAdd.attachToRecyclerView(fullManagementRecyclerview);

        //初始化加载进度条
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .delay(5000)
                .loadText("加载中...")
                .build();
        //查询满返数据
        mPresenter.selectFullReturn();
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

    //红色添加按钮点击事件
    @OnClick(R.id.full_management_add)
    public void onViewClicked() {
        //展示添加对话框
        showAlertHorizontalEdittextDialog("添加满返", "确定", "取消");
    }


    @Override
    public void onError(String errorMsg) {

    }

    @Override
    public void onSuccess(FullManagementBean fullManagementBean) {
        list = fullManagementBean.getList();
        //获取布局管理者
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置为垂直排列格式
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerview应用垂直排列格式
        fullManagementRecyclerview.setLayoutManager(linearLayoutManager);
        //设置适配器
        fullManagementAdapter = new FullManagementAdapter(getActivity(), list);
        fullManagementRecyclerview.setAdapter(fullManagementAdapter);

        //删除满返监听
        fullManagementAdapter.setOnDeleteClickListener(new FullManagementAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View v, int position) {
                //展示删除对话框
                showAlertMsgDialog("确定删除吗?","确认删除","确定","取消",position);
            }
        });
        //修改满返监听
        fullManagementAdapter.setOnUpdateClickListener(new FullManagementAdapter.OnUpdateClickListener() {
            @Override
            public void onUpdateClick(View v, int position) {
                //展示修改对话框
                showAlertHorizontalEdittextDialog2("修改满返", "确定", "取消",position);
            }
        });
    }

    @Override
    public void onSuccess(String message) {
        showShortToast(message);
        mPresenter.selectFullReturn();
        fullManagementAdapter.notifyDataSetChanged();
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
    //添加满返对话框
    public void showAlertHorizontalEdittextDialog(String title, String positive, String negative) {
        AppHorizontalEditextAlertDialog alertDialog = new AppHorizontalEditextAlertDialog(getActivity()).builder().setTitle(title)
                .setPositiveButton(positive, new AppHorizontalEditextAlertDialog.OnposClickLitener() {
                    @Override
                    public void onPosEditClick(String trim, String trim1) {
                        if (trim.equals("")||trim1.equals("")){
                            showShortToast("输入内容不能为空，请检查您输入的内容");

                        }else if(Integer.parseInt(trim1)>Integer.parseInt(trim)){
                            showShortToast("返的金额不能大于满的金额，请重新输入");
                        }else{
                            //调用添加满返方法
                            mPresenter.addFullReturn(trim,trim1);
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
    //弹出删除对话框
    public void showAlertMsgDialog(String msg, String title, String positive, String negative, int position) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //调用删除满返方法
                        mPresenter.deleteFullReturn(list.get(position).getRebate_id());
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        alertDialog.show();
    }

    //修改满返对话框
    public void showAlertHorizontalEdittextDialog2(String title, String positive, String negative,int position) {
        AppHorizontalEditextAlertDialog alertDialog = new AppHorizontalEditextAlertDialog(getActivity()).builder().setTitle(title)
                .setPositiveButton(positive, new AppHorizontalEditextAlertDialog.OnposClickLitener() {
                    @Override
                    public void onPosEditClick(String trim, String trim1) {

                        if (trim.equals("")||trim1.equals("")){
                            showShortToast("输入内容不能为空，请检查您输入的内容");

                        }else if(Float.parseFloat(trim1)>Float.parseFloat(trim)){
                            showShortToast("返的金额不能大于满的金额，请重新输入");
                        }else {
                            //调用修改满返方法
                          mPresenter.updateFullReturn(list.get(position).getRebate_id(),trim,trim1);
                        }
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.et_left.setText(list.get(position).getOrder_amount());
        alertDialog.et_right.setText(list.get(position).getRebate());
        alertDialog.show();
    }
}
