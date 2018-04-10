package com.huafan.huafano2omanger.view.fragment.shop.sortmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.SortManagementAdapter;
import com.huafan.huafano2omanger.entity.SortManagementBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.AppEditextAlertDialog;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;
import com.huafan.huafano2omanger.view.fragment.shop.shopmanger.ShopMangerActivity;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by heguozhong on 2017/12/25/025.
 * 分类管理主界面
 */

public class SortManagementFragment extends KFragment<ISortManagementView, ISortManagementPresenter> implements ISortManagementView, NormalTopBar.normalTopClickListener {
    //自定义通用标题
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    //绑定分类管理listview
    @BindView(R.id.sort_management_recyclerView)
    RecyclerView sortManagementRecyclerView;
    //红色添加按钮
    @BindView(R.id.sort_management_add)
    FloatingActionButton sortManagementAdd;
    private List<SortManagementBean.ListBean> list;
    private SortManagementAdapter sortManagementAdapter;
    private ShapeLoadingDialog shapeLoadingDialog;

    public static SortManagementFragment newInstance() {
        SortManagementFragment fragment = new SortManagementFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public ISortManagementPresenter createPresenter() {
        return new ISortManagementPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sort_management;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        normalTop.setTitleText("分类管理");
        normalTop.setTopClickListener(this);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);

        //初始化加载进度条
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .delay(5000)
                .loadText("加载中...")
                .build();


        //将悬浮按钮附着到Listview上
        sortManagementAdd.attachToRecyclerView(sortManagementRecyclerView);

        sortManagementRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==0){
                    sortManagementAdd.show();
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



    //红色添加按钮点击事件
    @OnClick(R.id.sort_management_add)
    public void onViewClicked() {
        showAlertEdittextDialog("添加分类", "确定", "取消");
    }

    //弹出删除对话框
    public void showAlertMsgDialog(String msg, String title, String positive, String negative, int position) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //调用删除分类方法
                        mPresenter.deleteSortShop(Integer.parseInt(list.get(position).getId()));
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        alertDialog.show();
    }

    //添加分类对话框
    public void showAlertEdittextDialog(String title, String positive, String negative) {
        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle(title);
        alertDialog.setPositiveButton(positive, new AppEditextAlertDialog.OnposClickLitener() {
                    @Override
                    public void onPosEditClick(String s, String trim, String trim1, String trim2) {
                        if (s.equals("")||trim.equals("")){
                            showShortToast("输入内容不能为空，请检查您输入的内容");

                        }else if (Integer.parseInt(alertDialog.et_price.getText().toString().trim())>99){
                            showShortToast("您输入的排序超出规定范围，排序范围为（1-99）");
                        }else if (Integer.parseInt(alertDialog.et_price.getText().toString().trim())==0) {
                            showShortToast("排序不能为0");
                        }else if (s.length()>10){
                            showShortToast("分类名称最多10个字符");
                        }else {
                            mPresenter.addSortShop(s, Integer.parseInt(trim));
                            alertDialog.hide();
                        }
                    }

                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.et_name.setHint("请输入菜品分类");
        alertDialog.et_price.setHint("请输入排序（1-99）");
        alertDialog.remove(alertDialog.et_rl_price);
        alertDialog.remove(alertDialog.et_stock);
        alertDialog.show();
    }
    //修改分类对话框
    public void showAlertEdittextUpdateDialog(String title, String positive, String negative,int position) {
        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle(title);
        alertDialog.setPositiveButton(positive, new AppEditextAlertDialog.OnposClickLitener() {
            @Override
            public void onPosEditClick(String s, String trim, String trim1, String trim2) {
                if (s.equals("") || trim.equals("")) {
                    showShortToast("输入内容不能为空，请检查您输入的内容");
                } else if (Integer.parseInt(alertDialog.et_price.getText().toString().trim())>99){
                    showShortToast("您输入的排序超出规定范围，排序范围为（1-99）");
                }else if (Integer.parseInt(alertDialog.et_price.getText().toString().trim())==0) {
                    showShortToast("排序不能为0");
                }else if (s.length()>10){
                    showShortToast("分类名称最多10个字符");
                }else {
                    //调用修改分类方法
                    mPresenter.updateSortShop(Integer.parseInt(list.get(position).getId()), s, Integer.parseInt(trim));
                    alertDialog.hide();
                }

            }

        }); alertDialog.setNegativeButton(negative, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        alertDialog.et_name.setHint("请输入菜品分类");
        alertDialog.et_price.setHint("请输入排序（1-99）");
        alertDialog.et_name.setText(list.get(position).getName());
        alertDialog.et_price.setText(list.get(position).getSort());
        alertDialog.remove(alertDialog.et_rl_price);
        alertDialog.remove(alertDialog.et_stock);
        alertDialog.show();
    }

    @Override
    public void onError(String errorMsg) {
        showShortToast(errorMsg);
    }

    @Override
    public void onSuccess(SortManagementBean sortManagementBean) {
        list = sortManagementBean.getList();
        //获取布局管理者
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置为垂直排列格式
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerview应用垂直排列格式
        sortManagementRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        sortManagementAdapter = new SortManagementAdapter(getActivity(), list);
        sortManagementRecyclerView.setAdapter(sortManagementAdapter);

        //实现删除分类点击事件
        sortManagementAdapter.setOnDeleteClickListener(new SortManagementAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View v, int position) {
                showAlertMsgDialog("确定删除吗?", "确认删除", "确定", "取消", position);
            }
        });
        //实现修改分类点击事件
        sortManagementAdapter.setOnUpdateClickListener(new SortManagementAdapter.OnUpdateClickListener() {
            @Override
            public void onUpdateClick(View v, int position) {
                showAlertEdittextUpdateDialog("修改分类", "确定", "取消",position);
            }
        });
        sortManagementAdapter.setOnEveryItemClickListener(new SortManagementAdapter.OnEveryItemClickListener() {
            @Override
            public void onEveryItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), ShopMangerActivity.class);
                intent.putExtra("cate_id", list.get(position).getId());
                intent.putExtra("tag",1);
                intent.putExtra("sortName", list.get(position).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(String message) {
        showShortToast(message);
        mPresenter.selectSortShop();
        sortManagementAdapter.notifyDataSetChanged();
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
    public void onResume() {
        super.onResume();
        //调用查询分类方法
        mPresenter.selectSortShop();
    }
}
