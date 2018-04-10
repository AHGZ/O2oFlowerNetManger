package com.huafan.huafano2omanger.view.fragment.groupon.addgroup;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.caimuhao.rxpicker.RxPicker;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.AddBannerAdapter;
import com.huafan.huafano2omanger.adapter.FitPersonAdapter;
import com.huafan.huafano2omanger.adapter.GroupDetailAdapter;
import com.huafan.huafano2omanger.entity.DetailGroupBean;
import com.huafan.huafano2omanger.entity.ImgDataBean;
import com.huafan.huafano2omanger.event.GroupOnEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.utils.Utils;
import com.huafan.huafano2omanger.view.customer.AppEditextAlertDialog;
import com.huafan.huafano2omanger.view.customer.CustomEditText;
import com.huafan.huafano2omanger.view.customer.MyGridView;
import com.huafan.huafano2omanger.view.customer.MyListView;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.ActionSheet;
import com.huafan.huafano2omanger.view.customer.actionsheet.AppPartnerAlertDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.DatePicker;
import com.huafan.huafano2omanger.view.customer.actionsheet.TimePicker;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by caishen on 2018/1/13.
 * by--添加团购
 */

public class AddGroupingFragment extends KFragment<IAddGroupingView, IAddGroupingPrenter>
        implements NormalTopBar.normalTopClickListener, ActionSheet.ActionSheetListener,
        IAddGroupingView, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    @BindView(R.id.et_title)
    CustomEditText etTitle;
    @BindView(R.id.et_short_title)
    CustomEditText etShortTitle;
    @BindView(R.id.gridView_person)
    MyGridView gridViewPerson;
    @BindView(R.id.et_content)
    CustomEditText etContent;
    @BindView(R.id.add_banner_gridview)
    MyGridView addBannerGridview;
    @BindView(R.id.et_ms_price)
    CustomEditText etMsPrice;
    @BindView(R.id.et_ct_price)
    CustomEditText etCtPrice;
    @BindView(R.id.et_rl_price)
    CustomEditText etRlPrice;
    @BindView(R.id.et_enjoin_person)
    CustomEditText etEnjoinPerson;
    @BindView(R.id.et_rj_price)
    CustomEditText etRjPrice;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_else)
    TextView tvElse;
    @BindView(R.id.listView_content)
    MyListView listViewContent;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.add_detail_gridview)
    MyGridView addDetailGridview;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_ky_start_time)
    TextView tvKyStartTime;
    @BindView(R.id.tv_ky_end_time)
    TextView tvKyEndTime;
    @BindView(R.id.cb_appointment)
    CheckBox cbAppointment;
    @BindView(R.id.et_appointment_hint)
    CustomEditText etAppointmentHint;
    @BindView(R.id.et_use_hint)
    CustomEditText etUseHint;
    @BindView(R.id.et_hint)
    CustomEditText etHint;
    @BindView(R.id.cb_isnew)
    CheckBox cbIsnew;
    @BindView(R.id.cb_isputaway)
    CheckBox cbIsputaway;
    @BindView(R.id.cb_ispackaging)
    CheckBox cbIspackaging;
    @BindView(R.id.et_group_key)
    CustomEditText etGroupKey;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.iv_add_group_detail)
    ImageView imageAddDeta;//添加团单内容
    @BindView(R.id.cb_jiari)
    CheckBox cbJiari;
    @BindView(R.id.cb_six)
    CheckBox cbSix;
    private ShapeLoadingDialog shapeLoadingDialog;
    private String state = "";//0-添加 1-修改
    private String groupId = "";
    private List<DetailGroupBean.GroupContentBean> arrData = new ArrayList<>();//添加团单内容
    private GroupDetailAdapter groupDetailAdapter;
    private List<String> imgData = new ArrayList<>();//轮播图图片数据
    private List<String> detailImags = new ArrayList<>();//团购详情图片数据
    private List<File> bannerfileData = new ArrayList<>();
    private List<File> detailfileData = new ArrayList<>();
    private AddBannerAdapter bannerAdapter;
    private String tag;
    private AddBannerAdapter addDetailAdapter;
    private String holiday_usable = "1";//节假日是否可用
    private String weekend_usable = "0";//周日可用
    private String need_resv = "1";//是否需要预约
    private String isNew = "1";//是否新品
    private String isputaway = "1";//是否上架
    private String ispackaging = "1";//是否支持打包
    private String fitType = "1";//适用人群
    private FitPersonAdapter personAdapter;
    private String[] desc_id = null;
    private String[] img_id = null;
    private TimePickerDialog mDialogAll;


    @Override
    public IAddGroupingPrenter createPresenter() {
        return new IAddGroupingPrenter();
    }

    public static KFragment newIntence(String state, String groupId) {

        AddGroupingFragment addGroupingFragment = new AddGroupingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("state", state);
        bundle.putString("groupId", groupId);
        addGroupingFragment.setArguments(bundle);
        return addGroupingFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        state = arguments.getString("state");
        groupId = arguments.getString("groupId");
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_addgroup;
    }

    @Override
    public void initData() {

        mPresenter.getGroupDetail();
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        //初始化沉浸式
        Utils.setStatusBar(getActivity(), 0, false);
        // 初始化标题及相关事件监听
        normalTop.setTitleText("添加团购");
        normalTop.getRightImage().setVisibility(View.GONE);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        normalTop.setTopClickListener(this);

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();


        //设置节假日是否可用
        cbJiari.setOnCheckedChangeListener(this);//节假日可用
        cbSix.setOnCheckedChangeListener(this);//六日可用
        cbAppointment.setOnCheckedChangeListener(this);//是否需要预约
        cbIsnew.setOnCheckedChangeListener(this);//是否新品
        cbIsputaway.setOnCheckedChangeListener(this);//是否上架
        cbIspackaging.setOnCheckedChangeListener(this);//是否打包


        //设置有效期时间，可用时间 的点击事件
        tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onTimePicker(v, tvStartTime);
            }
        });

        //结束时间
        tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onTimePicker(v, tvEndTime);
            }
        });

        //可用开始时间
        tvKyStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onTimePicker1(v, tvKyStartTime);
            }
        });

        //可用结束时间
        tvKyEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onTimePicker1(v, tvKyEndTime);
            }
        });


        //设置适合人数的适配器
        personAdapter = new FitPersonAdapter(getActivity());
        gridViewPerson.setAdapter(personAdapter);
        gridViewPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                personAdapter.setIndexPosition(position);
                personAdapter.notifyDataSetChanged();
            }
        });

        //设置选中的点击事件
        personAdapter.setOnCheckedLitener(new FitPersonAdapter.OnCheckedLitener() {
            @Override
            public void onCheckLitener(String name, int position) {

                if (position == 0) {//单人餐
                    fitType = "1";
                } else if (position == 1) {//双人餐
                    fitType = "2";
                } else if (position == 2) {//3-4人餐
                    fitType = "3";
                } else if (position == 3) {//5-10人餐
                    fitType = "4";
                } else {//10人以上餐
                    fitType = "5";
                }
            }
        });


        //设置轮播图的适配器
        bannerAdapter = new AddBannerAdapter(getActivity(), imgData);
        addBannerGridview.setAdapter(bannerAdapter);
        bannerAdapter.setMaxImages(6);
        bannerAdapter.setOndeleteImgClickLitener(new AddBannerAdapter.OndeleteImgClickLitener() {
            @Override
            public void deleteImgClickLitener(View view, int position) {

                if (imgData == null) {
                    return;
                }

                imgData.remove(position);
                if (img_id != null) {
                    for (int i = 0; i < img_id.length; i++) {
                        if (position == i) {
                            img_id[i] = "";
                            break;
                        }
                    }
                }
                bannerAdapter.notifyDataSetChanged();
            }
        });

        //设置添加轮播图图片的点击事件
        bannerAdapter.setOnItemClickLiteners(new AddBannerAdapter.OnItemClickLiteners() {
            @Override
            public void onItemClickLitener(View view, int position) {

                tag = "0";
                // 添加图片
                getActivity().setTheme(R.style.ActionSheetStyleiOS7);
                showActionSheet(addBannerGridview.getId());
            }
        });

        //设置团购详情图片上传
        addDetailAdapter = new AddBannerAdapter(getActivity(), detailImags);
        addDetailGridview.setAdapter(addDetailAdapter);
        addDetailAdapter.setMaxImages(999);
        addDetailAdapter.setOndeleteImgClickLitener(new AddBannerAdapter.OndeleteImgClickLitener() {
            @Override
            public void deleteImgClickLitener(View view, int position) {

                if (detailImags == null) {
                    return;
                }

                detailImags.remove(position);
                if (desc_id != null) {
                    for (int i = 0; i < desc_id.length; i++) {
                        if (position == i) {
                            desc_id[i] = "";
                            break;
                        }
                    }
                }
                addDetailAdapter.notifyDataSetChanged();
            }
        });

        //设置添加团购详情图片的点击事件
        addDetailAdapter.setOnItemClickLiteners(new AddBannerAdapter.OnItemClickLiteners() {
            @Override
            public void onItemClickLitener(View view, int position) {

                tag = "1";
                // 添加图片
                getActivity().setTheme(R.style.ActionSheetStyleiOS7);
                showActionSheet(addDetailGridview.getId());
            }
        });


        //判断是否是修改数据
        if (state.equals("1") && !TextUtils.isEmpty(groupId)) {

            initData();

        } else {//添加数据

            //设置团单内容的适配器
            groupDetailAdapter = new GroupDetailAdapter(getActivity(), arrData);
            listViewContent.setAdapter(groupDetailAdapter);

            groupDetailAdapter.setOnitemDeleteClickListener(new GroupDetailAdapter.OnitemDeleteClickListener() {
                @Override
                public void itemDeleteClick(View view, int position) {

                    showAlertMsgDialog("温馨提示", "确认删除该内容吗？", "确认", "取消", position);
                }
            });
        }
    }

    /**
     * 选择小时分钟时间
     *
     * @param view
     * @param textView
     */
    private void onTimePicker1(View view, TextView textView) {

        //默认选中当前时间
        TimePicker picker = new TimePicker(getActivity());
        picker.setTopLineVisible(false);
        picker.setTitleText("选择接单时间");
        picker.setSubmitTextColor(Color.parseColor("#FF2E00"));//确定
        picker.setCancelTextColor(Color.parseColor("#7B838D"));//取消
        picker.setTitleTextColor(Color.parseColor("#656565"));
        picker.setTextColor(Color.parseColor("#333333"));
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {

                textView.setText(hour + ":" + minute);
            }
        });
        picker.show();
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

    //选择时间的控件
    public void onTimePicker(View view, TextView textView) {

        //默认选中当前时间
        DatePicker picker = new DatePicker(getActivity());
        picker.setTitleText("选择日期");
        picker.setSubmitTextColor(Color.parseColor("#FF2E00"));//确定
        picker.setCancelTextColor(Color.parseColor("#7B838D"));//取消
        picker.setTitleTextColor(Color.parseColor("#656565"));
        picker.setTextColor(Color.parseColor("#333333"));

        Calendar nowCalendar = Calendar.getInstance();
        int nowyear = nowCalendar.get(GregorianCalendar.YEAR);
        int nowmonth = nowCalendar.get(GregorianCalendar.MONTH);
        int nowday = nowCalendar.get(GregorianCalendar.DATE);
        picker.setSelectedItem(nowyear, nowmonth + 1, nowday + 1);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {

                if (view.getId() == R.id.tv_start_time) {//有效时间

                } else if (view.getId() == R.id.tv_end_time) {

                } else if (view.getId() == R.id.tv_ky_start_time) {//可用时间

                } else if (view.getId() == R.id.tv_ky_end_time) {

                }

                textView.setText(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }

    /**
     * 添加图片
     *
     * @param viewid
     */
    public void showActionSheet(int viewid) {

        ActionSheet.createBuilder(getActivity(), getFragmentManager(), viewid)
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("选择图片")
                .setCancelableOnTouchOutside(true).setListener(this).show();
    }


    @OnClick({R.id.iv_add_group_detail, R.id.btn_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_group_detail://添加团单内容

                showAlertEdittextDialog("添加团单内容", "确定", "取消");

                break;
            case R.id.btn_commit://按钮提交

                if (state.equals("0")) {//添加

                    try {
                        mPresenter.commit();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {//修改
                    mPresenter.update();
                }

                break;
        }
    }

    /**
     * @Title: showAlertMsgDialog @Description: TODO 弹出消息框 @param @param
     * msg @param @param title @param @param positive @param @param
     * negative @return void 返回类型 @throws
     */
    public void showAlertMsgDialog(String msg, String title, String positive, String negative, int position) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        if (arrData != null && arrData.size() > 0) {

                            arrData.remove(position);
                        }

                        if (groupDetailAdapter != null) {
                            groupDetailAdapter.notifyDataSetChanged();
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

    /**
     * 带输入文本框的弹窗
     *
     * @param title
     * @param positive
     * @param negative
     */
    public void showAlertEdittextDialog(String title, String positive, String negative) {

        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle(title);
        alertDialog.setPositiveButton(positive, new AppEditextAlertDialog.OnposClickLitener() {
            @Override
            public void onPosEditClick(String trim, String trim1, String trim2, String trim3) {

                if (TextUtils.isEmpty(trim)||TextUtils.isEmpty(trim1)||TextUtils.isEmpty(trim2)) {
                    showShortToast("输入内容为空，请检查输入内容");
                    return;
                }else if (trim1.equals(".")){
                    showShortToast("输入价格格式不正确，不能只输入字符");
                    return;
                }else if (Integer.parseInt(trim2)>=256){
                    showShortToast("输入数量过大，不能大于256");
                    return;
                }
                DetailGroupBean.GroupContentBean specificationBean = new DetailGroupBean.GroupContentBean();
                specificationBean.setGoods_name(trim);
                specificationBean.setNum(trim2);
                specificationBean.setPrice(Float.parseFloat(trim1));
                arrData.add(specificationBean);
                if (groupDetailAdapter != null) {
                    groupDetailAdapter.notifyDataSetChanged();
                }
                alertDialog.hide();
            }
        })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.setType(alertDialog.et_rl_price, InputType.TYPE_CLASS_NUMBER);
        alertDialog.setType(alertDialog.et_price, 8194);

        alertDialog.et_name.setHint("请输入名称");
        alertDialog.et_price.setHint("请输入价格");
        alertDialog.et_rl_price.setHint("请输入数量");
        alertDialog.et_stock.setVisibility(View.GONE);
        alertDialog.show();
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index, int viewId) {
        switch (index + 1) {
            case 1:

                int limit = 1;
                if (viewId == addBannerGridview.getId()) {//添加轮播图
                    limit = 6;
                } else {//团购详情
                    limit = 999;
                }

                // 选择图片
                RxPicker.of().single(false)
                        .limit(1, limit)
                        .start(getActivity()).subscribe(new Consumer<List<ImageItem>>() {

                    @Override
                    public void accept(@NonNull List<ImageItem> imageItems) throws Exception {

                        if (viewId == addBannerGridview.getId()) {//轮播图
                            if (bannerfileData != null && bannerfileData.size() > 0) {

                                bannerfileData.clear();
                            }
                        } else {

                            if (detailfileData != null && detailfileData.size() > 0) {
                                detailfileData.clear();
                            }
                        }

                        //压缩上传图片
                        compressByUs(imageItems, viewId);
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * @throws
     * @描述: 压缩图片
     * @创建人：zhangpeisen
     * @创建时间：2017/12/29 下午3:14
     * @修改人：zhangpeisen
     * @修改时间：2017/12/29 下午3:14
     * @修改备注：
     */
    private void compressByUs(List<ImageItem> path, int viewId) {

        File[] uris = new File[path.size()];
        for (int i = 0; i < path.size(); i++) {
            uris[i] = new File(path.get(i).getPath());
        }

        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        Tiny.getInstance().source(uris).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
            @Override
            public void callback(boolean isSuccess, String[] outfile) {
                //return the batch compressed file path

                if (!isSuccess) {
                    showShortToast("上传失败");
                    return;
                }

                for (int i1 = 0; i1 < outfile.length; i1++) {

                    File file = new File(outfile[i1]);
                    if (viewId == addBannerGridview.getId()) {//添加轮播图图片

                        bannerfileData.add(file);

                    } else {//添加团购详情图片

                        detailfileData.add(file);
                    }
                }
                mPresenter.uploadImg();
            }
        });
    }

    @Override
    public void onError(String s) {

        showShortToast(s);
    }

    @Override
    public List<File> getImgList() {

        if (tag.equals("0")) {//轮播图
            return bannerfileData;
        } else {//团购详情图片
            return detailfileData;
        }
    }

    @Override
    public String getType() {
        return "3";
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
    public void mofityPhoto(ImgDataBean data) {

        if (data != null) {

            showShortToast("上传成功!");

            List<String> file_path = data.getFile_path();

            if (tag.equals("0")) {//轮播图

                for (int i = 0; i < file_path.size(); i++) {
                    imgData.add(file_path.get(i));
                }
                if (bannerAdapter != null) {
                    bannerAdapter.notifyDataSetChanged();
                }

            } else {//团购详情

                for (int i = 0; i < file_path.size(); i++) {
                    detailImags.add(file_path.get(i));
                }

                if (addDetailAdapter != null) {
                    addDetailAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public String gettitle() {

        return etTitle.getText().toString().trim();
    }

    @Override
    public String getshort_title() {
        return etShortTitle.getText().toString().trim();
    }

    @Override
    public List<String> getimags() {
        return imgData;
    }

    @Override
    public String getintro() {//团购介绍
        return etContent.getText().toString().trim();
    }

    @Override
    public String getmarket_price() {
        return etMsPrice.getText().toString().trim();
    }

    @Override
    public String getprice() {//参团价（售价）
        return etCtPrice.getText().toString().trim();
    }

    @Override
    public String getsupply_price() {
        return etRlPrice.getText().toString().trim();
    }

    @Override
    public String getstock() {//参团人数
        return etEnjoinPerson.getText().toString().trim();
    }

    @Override
    public List<DetailGroupBean.GroupContentBean> group_content() {//团购商品
        return arrData;
    }

    @Override
    public List<String> getdescription() {//团购详情图片
        return detailImags;
    }

    @Override
    public String getstarttime() {//团购开始时间
        return tvStartTime.getText().toString().trim();
    }

    @Override
    public String getendtime() {//团购结束时间
        return tvEndTime.getText().toString().trim();
    }

    @Override
    public String getday_start() {//团购可用开始时间
        return tvKyStartTime.getText().toString().trim();
    }

    @Override
    public String getday_end() {//团购可用结束时间
        return tvKyEndTime.getText().toString().trim();
    }

    @Override
    public String getholiday_usable() {//节假日可用
        return holiday_usable;
    }

    @Override
    public String getweekend_usable() {//周末可用
        return weekend_usable;
    }

    @Override
    public String getneed_resv() {//是否需要预约
        return need_resv;
    }

    @Override
    public String getresv_tips() {//预约提示
        return etAppointmentHint.getText().toString().trim();
    }

    @Override
    public String getrules() {//使用规则
        return etUseHint.getText().toString().trim();
    }

    @Override
    public String gettips() {//温馨提示
        return etHint.getText().toString().trim();
    }

    @Override
    public String getkeywords() {//团购关键词
        return etGroupKey.getText().toString().trim();
    }

    @Override
    public String isNew() {
        return isNew;
    }

    @Override
    public String getis_sale() {
        return isputaway;
    }

    @Override
    public String getis_takeaway() {
        return ispackaging;
    }

    @Override
    public String getconsume_avg() {//人均消费
        return etRjPrice.getText().toString().trim();
    }

    @Override
    public String getfitType() {
        return fitType;
    }

    @Override
    public void onSuccess(String message) {

        showShortToast("添加成功！");
        removeFragment();
        //发消息刷新团购数据
        EventBus.getDefault().post(new GroupOnEvent());
    }

    @Override
    public String getGroupId() {
        return groupId;
    }

    /**
     * 团购详情
     *
     * @param data
     */
    @Override
    public void SuccessData(DetailGroupBean data) {

        if (data != null) {

            etTitle.setText(data.getTitle());
            etShortTitle.setText(data.getShort_title());
            fitType = data.getFit_type();
            etContent.setText(data.getIntro());
            etMsPrice.setText(data.getMarket_price());
            etCtPrice.setText(data.getPrice());
            etRlPrice.setText(data.getSupply_price());
            etEnjoinPerson.setText(data.getStock());
            etRjPrice.setText(data.getConsume_avg());
            arrData = data.getGroup_content();
            //设置团单内容的适配器
            groupDetailAdapter = new GroupDetailAdapter(getActivity(), arrData);
            listViewContent.setAdapter(groupDetailAdapter);

            groupDetailAdapter.setOnitemDeleteClickListener(new GroupDetailAdapter.OnitemDeleteClickListener() {
                @Override
                public void itemDeleteClick(View view, int position) {

                    showAlertMsgDialog("温馨提示", "确认删除该内容吗？", "确认", "取消", position);
                }
            });

            tvStartTime.setText(data.getStarttime());
            tvEndTime.setText(data.getEndtime());
            tvKyStartTime.setText(data.getDay_start());
            tvKyEndTime.setText(data.getDay_end());
            etAppointmentHint.setText(data.getResv_tips());
            etUseHint.setText(data.getRules());
            etHint.setText(data.getTips());
            etGroupKey.setText(data.getKeywords());

            holiday_usable = data.getHoliday_usable();
            if (holiday_usable.equals("1")) {
                cbJiari.setChecked(true);
            } else {
                cbJiari.setChecked(false);
            }

            weekend_usable = data.getWeekend_usable();
            if (weekend_usable.equals("1")) {
                cbSix.setChecked(true);
            } else {
                cbSix.setChecked(false);
            }

            need_resv = data.getNeed_resv();
            if (need_resv.equals("1")) {
                cbAppointment.setChecked(true);
            } else {
                cbAppointment.setChecked(false);
            }

            isNew = data.getIs_new();
            if (isNew.equals("1")) {
                cbIsnew.setChecked(true);
            } else {
                cbIsnew.setChecked(false);
            }

            isputaway = data.getIs_sale();
            if (isputaway.equals("1")) {
                cbIsputaway.setChecked(true);
            } else {
                cbIsputaway.setChecked(false);
            }

            ispackaging = data.getIs_takeaway();
            if (ispackaging.equals("1")) {
                cbIspackaging.setChecked(true);
            } else {
                cbIspackaging.setChecked(false);
            }

            //轮播图数据
            String imgs = data.getImgs();
            String[] imgUrl = imgs.split(",");
            for (int i = 0; i < imgUrl.length; i++) {
                imgData.add(imgUrl[i]);//轮播图数据
                if (bannerAdapter != null) {
                    bannerAdapter.notifyDataSetChanged();
                }
            }

            String str1 = data.getImg_id();
            img_id = str1.split(",");

            //团购详情图片数据
            String description = data.getDescription();
            String[] descriptions = description.split(",");
            for (int i = 0; i < descriptions.length; i++) {
                detailImags.add(descriptions[i]);
                if (addDetailAdapter != null) {
                    addDetailAdapter.notifyDataSetChanged();
                }
            }

            String str = data.getDesc_id();
            desc_id = str.split(",");

            String fit_type = data.getFit_type();
            int index = 0;
            if (fit_type.equals("1")) {//单人餐
                fitType = "1";
                index = 0;
            } else if (fit_type.equals("2")) {//双人餐
                fitType = "2";
                index = 1;
            } else if (fit_type.equals("3")) {//3-4人餐
                fitType = "3";
                index = 2;
            } else if (fit_type.equals("4")) {//5-10人餐
                fitType = "4";
                index = 3;
            } else {//10人以上餐
                fitType = "5";
                index = 4;
            }
            personAdapter.setIndexPosition(index);
        }
    }

    @Override
    public void onUpdataSuccess(String message) {

        showShortToast("修改成功");
        removeFragment();
    }

    @Override
    public String getimg_id() {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < img_id.length; i++) {
            stringBuilder.append(img_id[i]).append(",");
        }

        return stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf(","));
    }

    @Override
    public String getdesc_id() {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < desc_id.length; i++) {
            stringBuilder.append(desc_id[i]).append(",");
        }

        return stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf(","));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.cb_jiari://节假日

                if (isChecked) {
                    holiday_usable = "1";
                } else {
                    holiday_usable = "0";
                }
                break;
            case R.id.cb_six://六日可用

                if (isChecked) {
                    weekend_usable = "1";
                } else {
                    weekend_usable = "0";
                }
                break;
            case R.id.cb_appointment://是否需要预约

                if (isChecked) {
                    need_resv = "1";
                } else {
                    need_resv = "0";
                }
                break;
            case R.id.cb_isnew://是否新品
                if (isChecked) {
                    isNew = "1";
                } else {
                    isNew = "0";
                }
                break;
            case R.id.cb_isputaway://是否上架
                if (isChecked) {
                    isputaway = "1";
                } else {
                    isputaway = "0";
                }
                break;
            case R.id.cb_ispackaging://是否支持打包
                if (isChecked) {
                    ispackaging = "1";
                } else {
                    ispackaging = "0";
                }
                break;
        }
    }
}
