package com.huafan.huafano2omanger.view.fragment.pending.waitrebund;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.WaitReFundAdapter;
import com.huafan.huafano2omanger.entity.UserInfoEvent;
import com.huafan.huafano2omanger.entity.WaitReFundBean;
import com.huafan.huafano2omanger.event.PushMsgEvent;
import com.huafan.huafano2omanger.event.WaitDisposeEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.SPUtils;
import com.huafan.huafano2omanger.view.customer.AppEditextAlertDialog;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * Created by caishen on 2018/1/10.
 * by--待处理退款
 */

public class WaitRebundFragment extends KFragment<IWaitRebundView, IWaitRebundPrenter> implements IWaitRebundView {

    @BindView(R.id.et_listView)
    ExpandableListView etListView;
    @BindView(R.id.pull_refresh)
    PullToRefreshLayout pullRefresh;
    private ShapeLoadingDialog shapeLoadingDialog;
    private String state = "0";//0-申请中 1-同意退款 2-拒绝退款 3取消退款
    private int page = 1;
    private List<WaitReFundBean.ListBean> list;
    private boolean isLoad = false;
    private WaitReFundAdapter mAdapter;
    private int count = 0;
    private String refundId = "";
    private List<WaitReFundBean.ListBean> datas;

    @Override
    public IWaitRebundPrenter createPresenter() {
        return new IWaitRebundPrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_wait_rebund;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = getArguments().getString("state");
    }

    @Override
    public void initData() {

        //判断是否登录
        String token = SPUtils.get(getActivity(), "token", "").toString();
        if (token.equals("") || TextUtils.isEmpty(token)) {

        } else {

            mPresenter.getWaitRebund();
        }
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        initData();

        //设置下拉加载更多数据
        pullRefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {

                page = 1;
                count = 0;
                mPresenter.getWaitRebund();
                pullRefresh.finishRefresh();
            }

            @Override
            public void loadMore() {

                if (mAdapter != null) {

                    if (count >= mAdapter.getGroupCount()) {

                        isLoad = true;
                        page += 1;
                        mPresenter.getWaitRebund();

                    } else {

                        showShortToast("没有更多数据了！");
                    }
                }

                pullRefresh.finishLoadMore();
            }
        });
    }

    public static Fragment newIntence(String s) {

        WaitRebundFragment waitRebundFragment = new WaitRebundFragment();
        Bundle bundle = new Bundle();
        bundle.putString("state", s);
        waitRebundFragment.setArguments(bundle);
        return waitRebundFragment;
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
    public String getState() {
        return state;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void hideDiaglog() {

        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserInfoEvent userInfoEvent) {

        if (userInfoEvent.getUserInfo() == null) {//未登录

        } else {//已登录

            initData();
        }
    }

    /**
     * 收到推送的处理
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PushMsgEvent event) {
        if (event == null) {
            return;
        }
        String content = event.getContent();
        String myvalue = null;
        String order_num = null;
        if (!TextUtils.isEmpty(content)) {
            JSONObject customJson = null;
            try {
                customJson = new JSONObject(content);
                myvalue = null;
                if (!customJson.isNull("type")) {
                    myvalue = customJson.getString("type");
                }

                if (!customJson.isNull("order_num")) {
                    order_num = customJson.getString("order_num");
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (myvalue != null) {

            switch (myvalue) {
                case "20"://您有新的花返退款申请,待处理

                    mPresenter.getWaitRebund();

                    break;
                case "21"://您有新的花返外卖订单,请及时处理
                    break;
                case "22": //您有新的花返推送消息,请查收

                    break;
                case "23": //花返已经为您自动接单
                    //自动打印餐单

                    break;
            }
        }
    }


    @Override
    public void onSuccessData(WaitReFundBean data) {

        if (data != null) {

            list = data.getList();
            count = list.size();

            //发送消息刷新数量
            EventBus.getDefault().post(new WaitDisposeEvent("1", data.getCount()));//0-待处理订单 1待处理退款

            if (!isLoad) {

                datas = list;

                //设置适配器
                mAdapter = new WaitReFundAdapter(getActivity(), datas, state);
                etListView.setAdapter(mAdapter);


                //设置拒绝退款的按钮
                mAdapter.setOnLeftButtonStateClickLitener(new WaitReFundAdapter.OnLeftButtonStateClickLitener() {
                    @Override
                    public void leftButtonStateLitener(View view, int position, String id) {

                        refundId = id;
                        showAlertEdittextDialog("拒绝退款", "是否确认拒绝退款", "确定", "取消");
                    }
                });

                //设置同意退款的按钮
                mAdapter.setOnRightButtonStateClickLitener(new WaitReFundAdapter.OnRightButtonStateClickLitener() {
                    @Override
                    public void rightButtonStateLitener(View view, int position, String id) {

                        refundId = id;
                        showAlertMsgDialog("同意退款", "确定同意退款吗？", "确定", "取消");
                    }
                });


                //隐藏下拉按钮
                etListView.setGroupIndicator(null);

                //让exListView不能被点击
                etListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        return true;
                    }
                });


                //展开收起的点击事件
                mAdapter.setOnEvulateShowClickLitener(new WaitReFundAdapter.OnEvulateShowClickLitener() {
                    @Override
                    public void evulateClickLitener(View view, int position, boolean isShow) {

                        if (isShow) {//展开
                            etListView.expandGroup(position);//展开
                        } else {
                            etListView.collapseGroup(position);//收起
                        }
                    }
                });

            } else {

                isLoad = false;
                if (list.size() > 0) {

                    datas.addAll(list);
                    mAdapter.notifyDataSetChanged();

                } else {

                    showShortToast("没有更多数据了！");
                }
            }
        }
    }

    @Override
    public String getrefundId() {
        return refundId;
    }

    @Override
    public void onSuccess(String message) {

        showShortToast(message);
        mPresenter.getWaitRebund();
    }

    /**
     * @Title: showAlertMsgDialog @Description: TODO 弹出消息框 @param @param
     * msg @param @param title @param @param positive @param @param
     * negative @return void 返回类型 @throws
     */
    public void showAlertMsgDialog(String msg, String title, String positive, String negative) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        mPresenter.comfirmRefund();
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.show();
    }

    //添加拒绝原因对话框
    public void showAlertEdittextDialog(String title, String message, String positive, String negative) {

        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle(title);
        alertDialog.setMsg(message);
        alertDialog.setPositiveButton(positive, new AppEditextAlertDialog.OnposClickLitener() {
            @Override
            public void onPosEditClick(String s, String trim, String trim1, String trim2) {
                if (s.equals("") || s.equals("")) {
                    showShortToast("输入内容不能为空，请检查您输入的内容");
                } else {
                    mPresenter.refuseRefund(s);
                    alertDialog.hide();
                }
            }
        })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.et_name.setHint("请输入拒绝原因");
        alertDialog.txt_message.setVisibility(View.VISIBLE);
        alertDialog.remove(alertDialog.et_rl_price);
        alertDialog.remove(alertDialog.et_stock);
        alertDialog.remove(alertDialog.et_price);
        alertDialog.show();
    }
}
