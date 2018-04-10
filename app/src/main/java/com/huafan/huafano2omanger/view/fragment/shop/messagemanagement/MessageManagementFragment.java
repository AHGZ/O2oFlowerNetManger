package com.huafan.huafano2omanger.view.fragment.shop.messagemanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.MessageManagementListviewAdapter;
import com.huafan.huafano2omanger.entity.MessaTypeBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.List;

import butterknife.BindView;


/**
 * Created by heguozhong on 2017/12/27/027.
 * 消息管理主页面
 */

public class MessageManagementFragment extends KFragment<IMessageManagementView, IMessageManagementPresenter>
        implements NormalTopBar.normalTopClickListener, IMessageManagementView {

    //自定义通用标题
    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    @BindView(R.id.message_management_listview)
    ListView messageManagementListview;
    @BindView(R.id.pull_refresh)
    PullToRefreshLayout pullRefresh;
    private String type = "1,2,3,4,5";
    private int page = 1;
    private ShapeLoadingDialog shapeLoadingDialog;
    private String receiver = "1,2";
    private boolean isLoad = false;
    private List<MessaTypeBean.ListsBean> lists;
    private MessageManagementListviewAdapter mmla;
    private int count = 0;

    public static MessageManagementFragment newInstance() {

        Bundle args = new Bundle();
        MessageManagementFragment fragment = new MessageManagementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IMessageManagementPresenter createPresenter() {
        return new IMessageManagementPresenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.activity_message_management;
    }

    @Override
    public void initData() {

        mPresenter.get_notice_list();
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        normalTop.setTitleText("消息中心");
        normalTop.setTopClickListener(this);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        initData();

        //设置下拉刷新，上拉加载更多
        pullRefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {//刷新

                page = 1;
                mPresenter.get_notice_list();
                pullRefresh.finishRefresh();
            }

            @Override
            public void loadMore() {//加载更多

                if (mmla != null) {

                    if (count >= mmla.getCount()) {

                        isLoad = true;
                        page += 1;
                        mPresenter.get_notice_list();

                    } else {

                        showShortToast("没有更多数据了！");
                    }
                }
                pullRefresh.finishLoadMore();
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

    @Override
    public void onError(String s) {

        showShortToast(s);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getreceiver() {
        return receiver;
    }

    @Override
    public int getPage() {
        return page;
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
    public void onsuccessType(MessaTypeBean data) {

        if (data != null) {

            lists = data.getLists();
            count = lists.size();

            if (!isLoad) {

                //设置适配器
                mmla = new MessageManagementListviewAdapter(getActivity(), lists);
                messageManagementListview.setAdapter(mmla);
                messageManagementListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ImageView imageView = (ImageView) view.findViewById(R.id.message_management_item_img);
                        imageView.setImageResource(R.drawable.message_icon_yck);
                        view.findViewById(R.id.message_management_item_message).setVisibility(View.VISIBLE);
                    }
                });

            } else {

                isLoad = false;
                if (lists.size() > 0) {

                    lists.addAll(lists);
                    mmla.notifyDataSetChanged();

                } else {

                    showShortToast("没有更多数据了！");
                }
            }
        }
    }
}
