package com.huafan.huafano2omanger.view.fragment.groupon.grouping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.GroupOnAdapter;
import com.huafan.huafano2omanger.entity.GroupOnBean;
import com.huafan.huafano2omanger.event.GroupEvent;
import com.huafan.huafano2omanger.event.GroupOnEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.SPUtils;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.fragment.groupon.addgroup.AddGroupingActivity;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.melnykov.fab.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by caishen on 2018/1/13.
 * by--团购订单列表数据
 */

public class GroupingFragment extends KFragment<IGroupingView, IGroupingPrenter> implements IGroupingView {

    @BindView(R.id.recyclerView)
    ExpandableListView recyclerView;
    @BindView(R.id.pull_refresh)
    PullToRefreshLayout pullRefresh;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private ShapeLoadingDialog shapeLoadingDialog;
    private String state = "";
    private int page = 1;
    private boolean isLoad = false;
    private int count;
    private GroupOnAdapter mAdapter;
    private List<GroupOnBean.ListBean> list1;

    @Override
    public IGroupingPrenter createPresenter() {
        return new IGroupingPrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_grouping;
    }

    @Override
    public void initData() {

        //判断是否登录
        String token = SPUtils.get(getActivity(), "token", "").toString();
        if (token.equals("") || TextUtils.isEmpty(token)) {
        } else {
            mPresenter.getGroup();
        }
    }

    public static KFragment newIntence(String s) {

        GroupingFragment groupingFragment = new GroupingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("state", s);
        groupingFragment.setArguments(bundle);
        return groupingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        state = arguments.getString("state");
    }

    /**
     * 接收刷新数据
     *
     * @param userInfoEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GroupOnEvent userInfoEvent) {

        mPresenter.getGroup();
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        //设置加的点击事件
        fab.attachToListView(recyclerView);
        //初始化加载进度条
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .delay(5000)
                .loadText("加载中...")
                .build();

        initData();


        //设置下拉加载更多数据
        pullRefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {

                page = 1;
                count = 0;
                mPresenter.getGroup();
                pullRefresh.finishRefresh();
            }

            @Override
            public void loadMore() {

                if (mAdapter != null) {

                    if (count >= mAdapter.getGroupCount()) {

                        isLoad = true;
                        page += 1;
                        mPresenter.getGroup();

                    } else {

                        showShortToast("没有更多数据了！");
                    }
                }

                pullRefresh.finishLoadMore();
            }
        });
    }


    @OnClick(R.id.fab)
    public void onClick() {//添加团购

        Intent intent = new Intent(getActivity(), AddGroupingActivity.class);
        intent.putExtra("state", "0");
        intent.putExtra("groupId", "");
        startActivity(intent);
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void hideDialog() {
        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
        }
    }

    @Override
    public void successData(GroupOnBean data) {

        if (data != null) {

            List<GroupOnBean.ListBean> list = data.getList();

            //发送消息刷新数量
            if (state.equals("1")) {
                EventBus.getDefault().post(new GroupEvent("0", data.getCount()));//0-进行中 1已结束
            } else {
                EventBus.getDefault().post(new GroupEvent("1", data.getCount()));//0-进行中 1已结束款
            }

            if (!isLoad) {

                list1 = list;

                if (list1 != null && list1.size() > 0) {

                    //设置适配器
                    mAdapter = new GroupOnAdapter(getActivity(), list1);
                    recyclerView.setAdapter(mAdapter);

                    //取消箭头样式
                    recyclerView.setGroupIndicator(null);
                    //设置向下的自定义属性箭头
                    //        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
                    recyclerView.expandGroup(0);//默认展开第一组
                    list1.get(0).setIsshow(true);
                    mAdapter.notifyDataSetInvalidated();

                    //exListView点击事件但不让group往下处理
                    recyclerView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                        @Override
                        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                            //修改数据
                            Intent intent = new Intent(getActivity(), AddGroupingActivity.class);
                            intent.putExtra("state", "1");
                            intent.putExtra("groupId", list1.get(groupPosition).getGroup_id());
                            startActivity(intent);

                            return true;
                        }
                    });


                    //展开收起的点击事件
                    mAdapter.setOnEvulateShowClickLitener(new GroupOnAdapter.OnEvulateShowClickLitener() {
                        @Override
                        public void evulateClickLitener(View view, int position, boolean isShow) {

                            if (isShow) {//展开
                                recyclerView.expandGroup(position);//展开
                            } else {
                                recyclerView.collapseGroup(position);//收起
                            }
                        }
                    });

                    //设置是否上架的点击事件
                    mAdapter.setOnCheckIssaleClickLitener(new GroupOnAdapter.OnCheckIssaleClickLitener() {
                        @Override
                        public void onCheckIsSaleClickLitener(View view, int position, boolean ischeck) {

                            mPresenter.changeState(list1.get(position).getGroup_id(), ischeck);
                        }
                    });

                } else {

                }

            } else {

                isLoad = false;
                if (list.size() > 0) {

                    list1.addAll(list);
                    mAdapter.notifyDataSetChanged();

                } else {

                    showShortToast("没有更多数据了！");
                }
            }
        }
    }

    @Override
    public void onError(String s) {

        showShortToast(s);
    }

    @Override
    public void onSuccess(String message) {

        showShortToast(message);
    }
}
