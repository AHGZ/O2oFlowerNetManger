package com.huafan.huafano2omanger.view.fragment.shop.shopmanger;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.ShopMangerAdapter;
import com.huafan.huafano2omanger.entity.SelGoodsListBean;
import com.huafan.huafano2omanger.event.AddShopMangerEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.utils.Utils;
import com.huafan.huafano2omanger.view.customer.DefaultItemTouchHelpCallback;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.RecycleViewDivider;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.melnykov.fab.FloatingActionButton;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by caishen on 2017/12/25.
 * by--商品管理
 */

public class ShopMangerFragment extends KFragment<IShopMangerView, IShopMangerPrenter>
        implements NormalTopBar.normalTopClickListener, IShopMangerView {
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Unbinder unbinder;
    @BindView(R.id.pull_refresh)
    PullToRefreshLayout pullRefresh;
    private ShopMangerAdapter shopMangerAdapter;
    private String cate_id = "0";//分类Id
    private String sortName = "";//分类Id
    private int tag = 0;//分类Id
    private int page = 1;
    private int pages =1;
    private ShapeLoadingDialog shapeLoadingDialog;
    private String specId = "";
    private int count = 0;//每页数据
    private boolean isLoad = false;//是否加载更多
    private List<SelGoodsListBean.ListBean> shopS;
    private List<SelGoodsListBean.ListBean> list;


    public static KFragment newIntence(String cate_id,int tag,String sortName) {

        ShopMangerFragment shopMangerFragment = new ShopMangerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cate_id", cate_id);
        bundle.putInt("tag", tag);
        bundle.putString("sortName", sortName);
        shopMangerFragment.setArguments(bundle);
        return shopMangerFragment;
    }


    @Override
    public IShopMangerPrenter createPresenter() {
        return new IShopMangerPrenter();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        cate_id = arguments.getString("cate_id");
        tag = arguments.getInt("tag");
        sortName = arguments.getString("sortName");
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_shop_manger;
    }

    @Override
    public void initData() {

        mPresenter.getShopList();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        //初始化沉浸式
        Utils.setStatusBar(getActivity(), 0, false);
        // 初始化标题及相关事件监听
        normalTop.setTitleText("商品管理");

        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        normalTop.setTopClickListener(this);

        recyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 24, getResources().getColor(R.color.divide_gray_color)));

        //初始化加载进度条
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .delay(5000)
                .loadText("加载中...")
                .build();

        //设置下拉加载更多数据
        pullRefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {

                pages=1;
                count = 0;
                mPresenter.getShopList();
                pullRefresh.finishRefresh();
            }

            @Override
            public void loadMore() {

                if (shopMangerAdapter != null) {

                    if (count >= shopMangerAdapter.getItemCount()) {

                        isLoad = true;
                        page += 1;
                        pages=page;
                        mPresenter.getShopList();

                    } else {

                        showShortToast("没有更多数据了！");
                    }
                }

                pullRefresh.finishLoadMore();
            }
        });


        //设置加的点击事件
        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), EditShopMangerActivity.class);
                intent.putExtra("cate_id", cate_id);
                intent.putExtra("goodId", "");
                intent.putExtra("isUpdate", "0");//0-添加 1-修改
                intent.putExtra("tag", tag);
                intent.putExtra("sortName", sortName);
                intent.putExtra("flag", "1");
                startActivity(intent);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==0){
                    fab.show();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        initData();
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

    /**
     * @Title: showAlertMsgDialog @Description: TODO 弹出消息框 @param @param
     * msg @param @param title @param @param positive @param @param
     * negative @return void 返回类型 @throws
     */
    public void showAlertMsgDialog(String msg, String title, String positive, String negative, int position, List<SelGoodsListBean.ListBean> list) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        mPresenter.delShop(list.get(position).getId());
//                        list.remove(position);
//                        shopMangerAdapter.notifyItemRemoved(position);
//                        shopMangerAdapter.notifyDataSetChanged();
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
    public String getCateId() {
        return cate_id;
    }

    @Override
    public int getPage() {
        return pages;
    }

    @Override
    public void onError(String s) {

        showShortToast(s);
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
    public void SuccessSelGoodList(SelGoodsListBean data) {

        if (data != null) {

            List<SelGoodsListBean.ListBean> list = data.getList();
            //赋值加载数据
            count += list.size();

            if (!isLoad) {

                shopS = list;

                //首次加载数据
                if (shopS != null && shopS.size() > 0) {

                    //设置适配器
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    shopMangerAdapter = new ShopMangerAdapter(getActivity(),tag);
                    shopMangerAdapter.attachRecyclerView(recyclerView);
                    shopMangerAdapter.setList(shopS);

                    //设置点击事件
                    shopMangerAdapter.setOnItemClicksListener(new ShopMangerAdapter.OnItemClicksListener() {
                        @Override
                        public void ItemClickLitener(View view, int position) {

                            Intent intent = new Intent(getActivity(), EditShopMangerActivity.class);
                            intent.putExtra("goodId", String.valueOf(shopS.get(position).getId()));
                            intent.putExtra("cate_id", cate_id);
                            intent.putExtra("isUpdate", "1");//0-添加 1-修改
                            intent.putExtra("flag","0");
                            startActivity(intent);
                        }
                    });

                    //设置拖拽的点击事件
                    DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener =
                            new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
                                @Override
                                public void onSwiped(int adapterPosition) {
                                }

                                @Override
                                public boolean onMove(int srcPosition, int targetPosition) {
                                    if (shopS != null) {
                                        page=1;
                                        pages=1;
                                        mPresenter.sortShop(shopS.get(targetPosition).getId(), shopS.get(srcPosition).getId(),
                                                shopS.get(targetPosition).getSort(), shopS.get(targetPosition).getSort());
//                        // 更换数据源中的数据Item的位置
//                        Collections.swap(list, srcPosition, targetPosition);
//                        // 更新UI中的Item的位置，主要是给用户看到交互效果
//                        shopMangerAdapter.notifyItemMoved(srcPosition, targetPosition);
//                        shopMangerAdapter.notifyItemChanged(targetPosition);
//                        shopMangerAdapter.notifyItemChanged(srcPosition);

                                        return true;
                                    }
                                    return false;
                                }
                            };

                    //设置排序的点击事件
                    shopMangerAdapter.setItemSortClickListener(new ShopMangerAdapter.ItemSortClickListener() {
                        @Override
                        public void ItemSortUpClick(View view, int position) {
                            if (shopS != null) {

                                if (position == 0) {//第一位不可上移
                                    showShortToast("已经是第一位了");
                                    return;
                                }
                                page=1;
                                pages=1;
                                mPresenter.sortShop(shopS.get(position).getId(), shopS.get(position - 1).getId(),
                                        shopS.get(position).getSort(), shopS.get(position - 1).getSort());

//                        // 更换数据源中的数据Item的位置
//                        Collections.swap(list, position, position - 1);
//                        // 更新UI中的Item的位置，主要是给用户看到交互效果
//                        shopMangerAdapter.notifyItemMoved(position, position - 1);
//                        shopMangerAdapter.notifyItemChanged(position - 1);
//                        shopMangerAdapter.notifyItemChanged(position);
                            }
                        }

                        @Override
                        public void ItemSortDownClick(View view, int position) {
                            if (shopS != null) {
                                if (position == shopS.size() - 1) {//最后一位不可下移
                                    showShortToast("已经是最后一位了");
                                    return;
                                }
                                page=1;
                                pages=1;
                                mPresenter.sortShop(shopS.get(position).getId(), shopS.get(position + 1).getId(),
                                        shopS.get(position).getSort(), shopS.get(position + 1).getSort());
//                        // 更换数据源中的数据Item的位置
//                        Collections.swap(list, position, position + 1);
//                        // 更新UI中的Item的位置，主要是给用户看到交互效果
//                        shopMangerAdapter.notifyItemMoved(position, position + 1);
//                        shopMangerAdapter.notifyItemChanged(position + 1);
//                        shopMangerAdapter.notifyItemChanged(position);
                            }
                        }
                    });

                    //设置置顶的点击事件
                    shopMangerAdapter.setItemStickClickListener(new ShopMangerAdapter.ItemStickClickListener() {
                        @Override
                        public void ItemStickClick(View view, int position) {
                            if (shopS != null) {
//                        // 更换数据源中的数据Item的位置
//                        Collections.swap(list, position, 0);
//                        // 更新UI中的Item的位置，主要是给用户看到交互效果
//                        shopMangerAdapter.notifyItemMoved(position, 0);
//                        shopMangerAdapter.notifyItemChanged(0);
//                        shopMangerAdapter.notifyItemChanged(position);
                                page=1;
                                pages=1;
                                mPresenter.topSort(shopS.get(position).getId(), shopS.get(position).getSort());
                            }

                        }
                    });


                    //设置删除的点击事件
                    shopMangerAdapter.setOnDelItemClickListener(new ShopMangerAdapter.ItemDeleteClickListener() {
                        @Override
                        public void ItemDeleteClick(View view, int position) {

                            // 滑动删除的时候，从数据源移除，并刷新这个Item。
                            if (shopS != null) {

                                showAlertMsgDialog("温馨提示", "确认删除该商品？", "确认", "取消", position, shopS);
                            }
                            page=1;
                            pages=1;
                        }
                    });

//            DefaultItemTouchHelper itemTouchHelper = new DefaultItemTouchHelper(onItemTouchCallbackListener);
//            itemTouchHelper.attachToRecyclerView(recyclerView);
//            itemTouchHelper.setDragEnable(true);
//            itemTouchHelper.setSwipeEnable(false);
                }

            } else {

                isLoad = false;
                if (list.size() > 0) {

                    shopS.addAll(list);
                    shopMangerAdapter.appendList(list);
                    shopMangerAdapter.notifyDataSetChanged();



                } else {
                    showShortToast("没有更多数据了！");
                }
            }
        }
    }

    @Override
    public String getSpecId() {
        return specId;
    }

    @Override
    public void onsuccess(String message) {

        showShortToast(message);
    }

    @Override
    public void onDelsuccess(String data, String message) {

        showShortToast("删除成功！");
        mPresenter.getShopList();
    }

    /***
     * 置顶
     * @param message
     */
    @Override
    public void onTopSortsuccess(String message) {

        showShortToast("置顶成功");
        mPresenter.getShopList();

    }


    /**
     * 接收刷新页面
     *
     * @param str
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AddShopMangerEvent str) {

        mPresenter.getShopList();
    }

    /**
     * 重置位置
     *
     * @param message
     */
    @Override
    public void onSortsuccess(String message) {

        showShortToast(message);
        mPresenter.getShopList();
    }
}
