package com.huafan.huafano2omanger.view.fragment.shop.shopunit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.ShopUnitAdapter;
import com.huafan.huafano2omanger.entity.ShopUnitBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.AppTextEditextAlertDialog;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by heguozhong on 2017/12/23.
 * 商品单位主界面
 */

public class ShopUnitFragment extends KFragment<IShopUnitView, IShopUnitPresenter>
        implements IShopUnitView, NormalTopBar.normalTopClickListener {
    //自定义通用标题
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    //绑定商品单位RecyclerView
    @BindView(R.id.shopunit_recyclerView)
    RecyclerView shopUnitRecyclerView;
    //红色添加按钮
    @BindView(R.id.shop_unit_add)
    FloatingActionButton shopUnitAdd;
    private ShopUnitAdapter shopUnitAdapter;
    private List<ShopUnitBean.ListBean> list;
    private ShapeLoadingDialog shapeLoadingDialog;

    public static ShopUnitFragment newInstance() {
        Bundle args = new Bundle();
        ShopUnitFragment fragment = new ShopUnitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IShopUnitPresenter createPresenter() {
        return new IShopUnitPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_shop_unit;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {
        normalTop.setTitleText("菜品单位");
        normalTop.setTopClickListener(this);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);

        //初始化加载进度条
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .delay(5000)
                .loadText("加载中...")
                .build();
        //调用查询商品单位方法
        mPresenter.selectShopUnit();

        //将悬浮按钮附着到listview上
        shopUnitAdd.attachToRecyclerView(shopUnitRecyclerView);

        shopUnitRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==0){
                    shopUnitAdd.show();
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

    @OnClick(R.id.shop_unit_add)
    public void onViewClicked() {
        showAlertTextEdittextDialog("请添加菜品单位名称", "添加单位", "确定", "取消");
    }

    //弹出添加菜品单位输入框
    public void showAlertTextEdittextDialog(String message, String title, String positive, String negative) {
        AppTextEditextAlertDialog alertDialog = new AppTextEditextAlertDialog(getActivity()).builder().setTitle(title).setMessage(message)
                .setPositiveButton(positive, new AppTextEditextAlertDialog.OnposClickLitener() {
                    @Override
                    public void onPosEditClick(String trim) {
                        if (trim.equals("")) {
                            showShortToast("单位名称不能为空");
                        } else if (trim.length()>5){
                            showShortToast("单位名称长度不能超过5个字符");
                        }else {
                            //调用添加商品单位方法
                            mPresenter.addShopUnit(trim);
                            shopUnitAdapter.notifyDataSetChanged();
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

    @Override
    public void onError(String errorMsg) {
        showShortToast(errorMsg);
    }

    @Override
    public void onSuccess(ShopUnitBean ShopUnitBean) {
        list = ShopUnitBean.getList();
        //获取布局管理者
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置为垂直排列格式
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerview应用垂直排列格式
        shopUnitRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        shopUnitAdapter = new ShopUnitAdapter(getActivity(), list);
        shopUnitRecyclerView.setAdapter(shopUnitAdapter);
        //实现删除商品单位点击监听
        shopUnitAdapter.setOnDeleteClickListener(new ShopUnitAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View v, int position) {
                showAlertMsgDialog("确定删除吗?", "确认删除", "确定", "取消", position);
            }
        });
        //实现修改商品单位点击监听
        shopUnitAdapter.setOnUpdateClickListener(new ShopUnitAdapter.OnUpdateClickListener() {
            @Override
            public void onUpdateClick(View v, int position) {
                showAlertTextEdittextUpdateDialog("请修改菜品单位名称", "修改单位", "确定", "取消", position);
            }
        });
    }

    @Override
    public void onSuccess(String message) {
        showShortToast(message);
        mPresenter.selectShopUnit();
        shopUnitAdapter.notifyDataSetChanged();
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

    //弹出删除对话框
    public void showAlertMsgDialog(String msg, String title, String positive, String negative, int position) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //调用删除商品单位方法
                        mPresenter.deleteShopUnit(Integer.parseInt(list.get(position).getId()));
                        shopUnitAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        alertDialog.show();
    }

    //修改菜品单位输入框
    public void showAlertTextEdittextUpdateDialog(String message, String title, String positive, String negative, int position) {
        AppTextEditextAlertDialog alertDialog = new AppTextEditextAlertDialog(getActivity()).builder().setTitle(title).setMessage(message)
                .setPositiveButton(positive, new AppTextEditextAlertDialog.OnposClickLitener() {
                    @Override
                    public void onPosEditClick(String trim) {
                        if (trim.equals("")) {
                            showShortToast("单位名称不能为空");
                        } else if (trim.length()>5){
                            showShortToast("单位名称长度不能超过5个字符");
                        }else {
                            //调用修改商品单位方法
                            mPresenter.updateShopUnit(Integer.parseInt(list.get(position).getId()), trim);
                            shopUnitAdapter.notifyDataSetChanged();
                        }

                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.et_name.setText(list.get(position).getUnit());
        alertDialog.show();
    }
}
