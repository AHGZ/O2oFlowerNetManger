package com.huafan.huafano2omanger.view.fragment.shop.evaluate.medium;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ExpandableListView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.EvluateAdapter;
import com.huafan.huafano2omanger.entity.EvaluateBean;
import com.huafan.huafano2omanger.event.EvaluateEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by caishen on 2017/12/26.
 * by--中评
 */

public class MediumEvaluateFragment extends KFragment<IMediumEvaluateView, IMediumEvaluatePrenter>
        implements IMediumEvaluateView {

    @BindView(R.id.ex_listView)
    ExpandableListView exListView;
    @BindView(R.id.pull_refresh)
    PullToRefreshLayout pullRefresh;
    private EvluateAdapter adapter;
    private ShapeLoadingDialog shapeLoadingDialog;
    private int page = 1;
    private String state = "2";
    private String type = "0";
    private int count = 0;
    private boolean isLoad = false;
    private List<EvaluateBean.ListBean> list = new ArrayList<>();
    private boolean isVisible;

    @Override
    public IMediumEvaluatePrenter createPresenter() {
        return new IMediumEvaluatePrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_good_evluate;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EvaluateEvent userInfoEvent) {

        //0 订单 1商家
        type = userInfoEvent.getType();
        if (type.equals("0")) {
            mPresenter.getEvaluate();
        } else if (type.equals("1")) {
            mPresenter.getMerticEvaluate();
        } else {

        }
    }

    @Override
    public void initData() {

        if (type.equals("0")) {
            mPresenter.getEvaluate();
        } else if (type.equals("1")) {
            mPresenter.getMerticEvaluate();
        } else {

        }
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        initData();

        //设置上垃加载更多，下拉刷新
        pullRefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {

                if (type.equals("0")) {
                    mPresenter.getEvaluate();
                } else if (type.equals("1")) {
                    mPresenter.getMerticEvaluate();
                } else {

                }
                page = 1;
                count = 0;
                pullRefresh.finishRefresh();
            }

            @Override
            public void loadMore() {

                if (adapter != null) {

                    if (count >= adapter.getGroupCount()) {

                        isLoad = true;
                        page += 1;
                        if (type.equals("0")) {
                            mPresenter.getEvaluate();
                        } else if (type.equals("1")) {
                            mPresenter.getMerticEvaluate();
                        } else {

                        }

                    } else {

                        showShortToast("没有更多数据了！");
                    }
                }

                pullRefresh.finishLoadMore();
            }
        });
    }


    public static Fragment newIntence() {
        MediumEvaluateFragment mediumEvaluateFragment = new MediumEvaluateFragment();
        Bundle bundle = new Bundle();
        mediumEvaluateFragment.setArguments(bundle);
        return mediumEvaluateFragment;
    }

    @Override
    public void onError(String s) {

        showShortToast(s);
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getState() {
        return state;
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
    public void OnsuccessDatas(EvaluateBean data) {

        if (data != null) {

            list = data.getList();

            if (!isLoad) {

                List<EvaluateBean.ListBean> datas = list;

                if (datas != null && datas.size() > 0) {

                    adapter = new EvluateAdapter(getActivity(), datas, type);
                    exListView.setAdapter(adapter);
                    //取消箭头样式
                    exListView.setGroupIndicator(null);
                    //设置向下的自定义属性箭头
                    int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
                    exListView.expandGroup(0);//默认展开第一组
                    datas.get(0).setIsshow(true);
                    adapter.notifyDataSetInvalidated();

                    //设置回复的点击事件
                    adapter.setOnReplyClickLitener(new EvluateAdapter.OnReplyClickLitener() {
                        @Override
                        public void replyClickLitener(String reply, String eval_id, int position) {

                            if (type.equals("0")) {
                                mPresenter.replyEva(reply, eval_id);
                            } else if (type.equals("1")) {
                                mPresenter.replyMerticEva(reply, eval_id);
                            } else {

                            }
                        }
                    });

                    //展开收起的点击事件
                    adapter.setOnEvulateShowClickLitener(new EvluateAdapter.OnEvulateShowClickLitener() {
                        @Override
                        public void evulateClickLitener(View view, int position, boolean isShow) {

                            if (isShow) {//展开
                                exListView.expandGroup(position);//展开
                            } else {
                                exListView.collapseGroup(position);//收起
                            }
                        }
                    });


//        for (int i = 0; i < adapter.getGroupCount(); i++) {
//
//            exListView.expandGroup(i); //关键步骤4:初始化，将ExpandableListView以展开的方式显示
//        }
                } else {

                }

            } else {

                isLoad = false;
                if (list.size() > 0) {

                    list.addAll(list);
                    adapter.notifyDataSetChanged();

                } else {

                    showShortToast("没有更多数据了！");
                }
            }
        }
    }

    @Override
    public void onSuccess(String message) {

        showShortToast(message);
        if (type.equals("0")) {
            mPresenter.getEvaluate();
        } else if (type.equals("1")) {
            mPresenter.getMerticEvaluate();
        } else {

        }
    }
}
