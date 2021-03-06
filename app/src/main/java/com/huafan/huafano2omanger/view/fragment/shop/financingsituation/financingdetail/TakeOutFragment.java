package com.huafan.huafano2omanger.view.fragment.shop.financingsituation.financingdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.FinancingTakeOutDetailAdapter;
import com.huafan.huafano2omanger.entity.FinancingDetailBean;
import com.huafan.huafano2omanger.entity.FinancingTakeDetailBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/12/012.
 */

public class TakeOutFragment extends KFragment<IFinancingDetailView, IFinancingDetailPresenter> implements IFinancingDetailView {
    @BindView(R.id.financing_detail_recyclerview)
    RecyclerView financingDetailRecyclerView;
    @BindView(R.id.financing_detail_pull_refresh)
    PullToRefreshLayout pullRefresh;
    private ShapeLoadingDialog shapeLoadingDialog;
    private int pages = 1;
    private int count = 0;//每页数据
    private boolean isLoad = false;//是否加载更多
    private List<FinancingTakeDetailBean.ListBean> shopes;
    private FinancingTakeOutDetailAdapter financingTakeOutDetailAdapter;

    @Override
    public IFinancingDetailPresenter createPresenter() {
        return new IFinancingDetailPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.group_fragment_layout;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .delay(5000)
                .loadText("加载中...")
                .build();

        mPresenter.selectTakeOutDetail();

        //设置下拉加载更多数据
        pullRefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {

                pages = 1;
                count = 0;
                mPresenter.selectTakeOutDetail();
                pullRefresh.finishRefresh();
            }

            @Override
            public void loadMore() {

                if (financingTakeOutDetailAdapter != null) {

                    if (count >= financingTakeOutDetailAdapter.getItemCount()) {

                        isLoad = true;
                        pages += 1;
                        mPresenter.selectTakeOutDetail();


                    } else {

                        showShortToast("没有更多数据了！");
                    }
                }

                pullRefresh.finishLoadMore();
            }
        });
    }

    @Override
    public void onError(String errorMsg) {
        showShortToast(errorMsg);
    }

    @Override
    public void onSuccess(FinancingDetailBean financingDetailBean) {
    }

    @Override
    public void onSuccess(FinancingTakeDetailBean financingTakeDetailBean) {
        if (financingTakeDetailBean != null) {
            List<FinancingTakeDetailBean.ListBean> list = financingTakeDetailBean.getList();
            //赋值加载数据
            count += list.size();

            if (!isLoad) {

                shopes = list;

                //首次加载数据
                if (shopes != null && shopes.size() > 0) {

                    financingDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    financingTakeOutDetailAdapter = new FinancingTakeOutDetailAdapter(getActivity());
                    financingTakeOutDetailAdapter.attachRecyclerView(financingDetailRecyclerView);
                    financingTakeOutDetailAdapter.setList(shopes);
                    pages=1;
                }

            } else {

                isLoad = false;
                if (list.size() > 0) {

                    shopes.addAll(list);
                    financingTakeOutDetailAdapter.appendList(list);
                    financingTakeOutDetailAdapter.notifyDataSetChanged();



                } else {
                    showShortToast("没有更多数据了！");
                }
            }
        }
    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public int getPages() {
        return pages;
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
