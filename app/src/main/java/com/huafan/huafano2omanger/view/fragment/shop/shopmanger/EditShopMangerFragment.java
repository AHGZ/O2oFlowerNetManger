package com.huafan.huafano2omanger.view.fragment.shop.shopmanger;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caimuhao.rxpicker.RxPicker;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.ghnor.flora.Flora;
import com.ghnor.flora.callback.Callback;
import com.ghnor.flora.spec.calculation.Calculation;
import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.adapter.GridViewAddImgesAdpter;
import com.huafan.huafano2omanger.adapter.SpecificationAdapter;
import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.GoodsDetailBean;
import com.huafan.huafano2omanger.entity.ImgDataBean;
import com.huafan.huafano2omanger.entity.ShopUnitBean;
import com.huafan.huafano2omanger.entity.SortManagementBean;
import com.huafan.huafano2omanger.entity.SpecificationBean;
import com.huafan.huafano2omanger.event.AddShopMangerEvent;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.GlideImageLoader;
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
import com.huafan.huafano2omanger.view.customer.actionsheet.OptionPicker;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by caishen on 2017/12/25.
 * by--添加管理商品
 */

public class EditShopMangerFragment extends KFragment<IAddShopMangerView, IAddShopMangerPrenter>
        implements ActionSheet.ActionSheetListener, NormalTopBar.normalTopClickListener, IAddShopMangerView,
        CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    @BindView(R.id.et_name)
    CustomEditText etName;
    @BindView(R.id.tv_sp_type)
    TextView tvSpType;
    @BindView(R.id.iv_sp_type)
    ImageView ivSpType;
    @BindView(R.id.ll_sp_type)
    LinearLayout llSpType;
    @BindView(R.id.add_img_gridview)
    MyGridView addImgGridview;
    @BindView(R.id.et_price)
    CustomEditText etPrice;
    @BindView(R.id.et_rl_price)
    CustomEditText etRlPrice;
    @BindView(R.id.et_every_indentry)
    CustomEditText etEveryIndentry;
    @BindView(R.id.et_container_num)
    CustomEditText etContainerNum;
    @BindView(R.id.et_container_price)
    CustomEditText etContainerPrice;
    @BindView(R.id.specification_listView)
    MyListView specificationListView;
    @BindView(R.id.tv_sp_dw)
    TextView tvSpDw;
    @BindView(R.id.ll_sp_dw)
    LinearLayout llSpDw;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.week_gridview)
    MyGridView weekGridview;
    @BindView(R.id.et_sp_desc)
    CustomEditText etSpDesc;
    @BindView(R.id.btn_out_login)
    Button btnOutLogin;
    @BindView(R.id.cb_1)
    CheckBox cb1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.cb_2)
    CheckBox cb2;
    @BindView(R.id.cb_3)
    CheckBox cb3;
    @BindView(R.id.cb_4)
    CheckBox cb4;
    @BindView(R.id.cb_5)
    CheckBox cb5;
    @BindView(R.id.cb_6)
    CheckBox cb6;
    @BindView(R.id.cb_0)
    CheckBox cb0;
    @BindView(R.id.cb_sale)
    CheckBox cbSale;
    @BindView(R.id.item_grida_image)
    ImageView itemGridaImage;
    @BindView(R.id.ll_add_specification)
    LinearLayout llAddSpecification;
    private List<File> fileData = new ArrayList<>();//添加图片
    private List<SpecificationBean> arrData = new ArrayList<>();//添加商品规格
    private GridViewAddImgesAdpter mAdapter;
    private static final int COMPRESS_COMPLETE = 1;//压缩完成
    private int number;
    private SpecificationAdapter typeAdapter;
    private String ids;
    private String cate_id = "0";
    private String sortName = "";
    private String flag = "0";
    private int tag = 0;
    private String isUpdate = "";//状态 0-添加 1-修改
    private ShapeLoadingDialog shapeLoadingDialog;
    private String is_sale0 = "0";
    private String is_sale6 = "0";
    private String is_sale5 = "0";
    private String is_sale4 = "0";
    private String is_sale3 = "0";
    private String is_sale2 = "0";
    private String is_sale1 = "0";
    private String unit_id = "";//商品单元id
    private String type = "3";
    private List<String> file_path = new ArrayList<>();
    private String is_sale = "0";//是否上架
    private String isnew = "0";//是否新品
    private String goodId = "";
    private String goods_img = "";//商品图片id


    public static KFragment newIntence(String cateId, String isUpdate, String goodId,int tag,String sortName,String flag) {
        EditShopMangerFragment addShopMangerFragment = new EditShopMangerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cate_id", cateId);
        bundle.putString("isUpdate", isUpdate);
        bundle.putString("goodId", goodId);
        bundle.putString("sortName", sortName);
        bundle.putString("flag", flag);
        bundle.putInt("tag", tag);
        addShopMangerFragment.setArguments(bundle);
        return addShopMangerFragment;
    }

    @Override
    public IAddShopMangerPrenter createPresenter() {
        return new IAddShopMangerPrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_addmanger;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        goodId = arguments.getString("goodId");
        isUpdate = arguments.getString("isUpdate");
        cate_id = arguments.getString("cate_id");
        sortName = arguments.getString("sortName");
        flag = arguments.getString("flag");
        tag = arguments.getInt("tag");
    }

    @Override
    public void initData() {

        mPresenter.getShopDetail();
    }

    /**
     * 添加图片
     *
     * @param viewid
     */
    public void showActionSheet(int viewid) {

        ActionSheet.createBuilder(getActivity(), getFragmentManager(), viewid)
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("选择商品图片")
                .setCancelableOnTouchOutside(true).setListener(this).show();
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {


    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index, int viewId) {
        switch (index + 1) {
            case 1:
                // 选择图片
                RxPicker.of().single(true).limit(1, 2).start(getActivity()).subscribe(new Consumer<List<ImageItem>>() {

                    @Override
                    public void accept(@NonNull List<ImageItem> imageItems) throws Exception {

                        if (!fileData.isEmpty() && fileData.size() > 0) {
                            fileData.clear();
                        }
                        number = 0;
                        for (ImageItem imageItem : imageItems) {
                            compressByUs(imageItem.getPath());
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    //压缩图片
    private void compressByUs(String path) {
        number = 0;
        Flora.with(this).calculation(new Calculation() {
            @Override
            public int calculateInSampleSize(int srcWidth, int srcHeight) {
                return super.calculateInSampleSize(srcWidth, srcHeight);
            }

            @Override
            public int calculateQuality(int srcWidth, int srcHeight, int targetWidth, int targetHeight) {
                return super.calculateQuality(srcWidth, srcHeight, targetWidth, targetHeight);
            }
        }).load(path).compress(new Callback<String>() {
            @Override
            public void callback(String compressResults) {
                File file = new File(compressResults);
                fileData.add(file);
                goods_img = "0";//修改商品需要
                mPresenter.uploadImg();
            }
        });
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        //初始化沉浸式
        Utils.setStatusBar(getActivity(), 0, false);
        // 初始化标题及相关事件监听
        if (flag.equals("0")){
            normalTop.setTitleText("商品编辑");
        }else{
            normalTop.setTitleText("商品添加");
        }
        normalTop.getRightImage().setVisibility(View.GONE);
        // 扩大事件的点击范围
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        normalTop.setTopClickListener(this);

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        initcheckbox();

        if (isUpdate.equals("0")) {//添加

            typeAdapter = new SpecificationAdapter(getActivity(), arrData);
            specificationListView.setAdapter(typeAdapter);

            //删除规格
            typeAdapter.setOnitemDeleteClickListener(new SpecificationAdapter.OnitemDeleteClickListener() {
                @Override
                public void itemDeleteClick(View view, int position) {

                    showAlertMsgDialog("温馨提示", "确认删除该规格？", "确认", "取消", position);
                }
            });
            if (tag==1){
                tvSpType.setText(sortName);
            }

        } else {//修改

            initData();
        }
    }

    //初始化星期单选
    private void initcheckbox() {

        cb0.setOnCheckedChangeListener(this);
        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        cb4.setOnCheckedChangeListener(this);
        cb5.setOnCheckedChangeListener(this);
        cb6.setOnCheckedChangeListener(this);
        checkbox.setOnCheckedChangeListener(this);
        cbSale.setOnCheckedChangeListener(this);
    }

    @OnClick({R.id.ll_sp_type, R.id.ll_sp_dw, R.id.btn_out_login,
            R.id.item_grida_image, R.id.ll_add_specification})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_sp_type://商品分类

                mPresenter.getShopCategray();

                break;
            case R.id.ll_sp_dw://商品单位

                mPresenter.getShopUnit();

                break;
            case R.id.btn_out_login://提交

                if (!cb0.isChecked()&&!cb1.isChecked()&&!cb2.isChecked()&&!cb3.isChecked()&&!cb4.isChecked()&&!cb5.isChecked()&&!cb6.isChecked()){
                    showShortToast("商品星期未选择，请至少选择一个");
                    break;
                }
                if (tvSpType.getText().toString().trim().equals("")){
                    showShortToast("请选择商品分类");
                    break;
                }
                if (isUpdate.equals("0")) {//添加

                    try {
                        mPresenter.commitTask();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {//修改

                    try {
                        mPresenter.updata();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                break;

            case R.id.item_grida_image://上传图片

                // 添加图片
                getActivity().setTheme(R.style.ActionSheetStyleiOS7);
                showActionSheet(itemGridaImage.getId());

                break;
            case R.id.ll_add_specification://添加规格

                showAlertEdittextDialog("添加规格", "确定", "取消");

                break;
        }
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
    public void showAlertMsgDialog(String msg, String title, String positive, String negative, int position) {
        AppPartnerAlertDialog alertDialog = new AppPartnerAlertDialog(getActivity()).builder().setTitle(title).setMsg(msg)
                .setPositiveButton(positive, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        arrData.remove(position);
                        typeAdapter.notifyDataSetChanged();
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
     * @Title: showAlertMsgDialog @Description: TODO 弹出消息框 @param @param
     * msg @param @param title @param @param positive @param @param
     * negative @return void 返回类型 @throws
     */
    public void showAlertEdittextDialog(String title, String positive, String negative) {

        AppEditextAlertDialog alertDialog = new AppEditextAlertDialog(getActivity());
        alertDialog.builder();
        alertDialog.setTitle(title);
        alertDialog.setPositiveButton(positive, new AppEditextAlertDialog.OnposClickLitener() {
                    @Override
                    public void onPosEditClick(String trim, String trim1, String trim2, String trim3) {

                        if (trim.equals("")||trim1.equals("")||trim2.equals("")||trim3.equals("")){
                            showShortToast("输入内容不能为空，请检查输入的内容");
                            return;
                        }else if (trim1.equals(".")||trim2.equals(".")){
                            showShortToast("请输入正确的价格，不能只输入字符");
                            return;
                        }else if (Float.parseFloat(trim2)>Float.parseFloat(trim1)){
                            showShortToast("让利价不能大于价格");
                            return;
                        }
                        SpecificationBean specificationBean = new SpecificationBean();
                        specificationBean.setGoods_spec(trim);
                        specificationBean.setSpec_price(Float.parseFloat(trim2));//让利价
                        specificationBean.setO_spec_price(Float.parseFloat(trim1));//价格
                        specificationBean.setSpec_stock(trim3);
                        arrData.add(specificationBean);
                        if (typeAdapter != null) {
                            typeAdapter.notifyDataSetChanged();
                        }
                        alertDialog.hide();
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        alertDialog.setType(alertDialog.et_price,8194);

        alertDialog.show();
    }

    @Override
    public void hideDiaglog() {

        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
        }
    }

    /**
     * 商品分类
     *
     * @param data
     */
    @Override
    public void onSuccessSortShop(SortManagementBean data) {

        if (data != null) {

            List<SortManagementBean.ListBean> list = data.getList();

            String[] options = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                options[i] = list.get(i).getName();
            }

            OptionPicker optionPicker = new OptionPicker(getActivity(), options);
            optionPicker.setTitleText("");
            optionPicker.setSubmitTextColor(Color.parseColor("#FF2E00"));
            optionPicker.show();
            optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                @Override
                public void onOptionPicked(String option, int selectedIndex) {

                    tvSpType.setText(options[selectedIndex]);
                    cate_id = list.get(selectedIndex).getId();
                }
            });
        }
    }

    @Override
    public void onError(String s) {

        showShortToast(s);
    }

    @Override
    public void showDialog() {

        shapeLoadingDialog.show();
    }

    /**
     * 商品单元
     *
     * @param data
     */
    @Override
    public void onSuccessUnitShop(ShopUnitBean data) {

        if (data != null) {

            List<ShopUnitBean.ListBean> list = data.getList();
            if (list == null || list.size() <= 0) {

                return;
            }

            String[] options = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                options[i] = list.get(i).getUnit();
            }

            OptionPicker optionPicker = new OptionPicker(getActivity(), options);
            optionPicker.setTitleText("");
            optionPicker.setSubmitTextColor(Color.parseColor("#FF2E00"));
            optionPicker.show();
            optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                @Override
                public void onOptionPicked(String option, int selectedIndex) {

                    tvSpDw.setText(options[selectedIndex]);
                    unit_id = list.get(selectedIndex).getId();
                }
            });
        }
    }

    @Override
    public String getCataId() {
        return cate_id;
    }

    @Override
    public String getName() {

        return etName.getText().toString().trim();
    }

    @Override
    public List<String> getImgPath() {

        return file_path;
    }

    @Override
    public String getPrice() {

        return etPrice.getText().toString().trim();
    }

    @Override
    public String getoprice() {

        return etRlPrice.getText().toString().trim();
    }

    @Override
    public String getboxnum() {
        return etContainerNum.getText().toString().trim();
    }

    @Override
    public String getbox_price() {
        return etContainerPrice.getText().toString().trim();
    }

    @Override
    public String getUnitId() {
        return unit_id;
    }

    @Override
    public String getisNew() {
        return isnew;
    }

    @Override
    public String is_sale0() {
        return is_sale0;
    }

    @Override
    public String is_sale1() {
        return is_sale1;
    }

    @Override
    public String is_sale2() {
        return is_sale2;
    }

    @Override
    public String is_sale3() {
        return is_sale3;
    }

    @Override
    public String is_sale4() {
        return is_sale4;
    }

    @Override
    public String is_sale5() {
        return is_sale5;
    }

    @Override
    public String is_sale6() {
        return is_sale6;
    }

    @Override
    public String isSale() {
        return is_sale;
    }

    @Override
    public String getdescription() {
        return etSpDesc.getText().toString().trim();
    }

    @Override
    public String getstock() {
        return etEveryIndentry.getText().toString().trim();
    }

    @Override
    public List<SpecificationBean> getgoods_spec() {
        return arrData;
    }

    @Override
    public List<File> getUserImgList() {
        return fileData;
    }

    @Override
    public String getType() {
        return type;
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
            file_path = data.getFile_path();
            new GlideImageLoader().displayImage(getActivity(), ApiUrlConstant.API_IMG_URL + file_path.get(0).toString(),
                    itemGridaImage);
        }
    }

    @Override
    public void onsuccessAddShop(String message) {

        showShortToast("添加成功");
        //发送消息刷新页面
        EventBus.getDefault().post(new AddShopMangerEvent());
        removeFragment();
    }

    @Override
    public String getGoodId() {

        return goodId;
    }

    /***
     * 商品详情
     * @param data
     */
    @Override
    public void onSuccessShopDetail(GoodsDetailBean data) {

        if (data != null) {

            if (data.getList() != null) {

                //设置添加规格的适配器
                arrData = data.getList();//规格数据
                typeAdapter = new SpecificationAdapter(getActivity(), arrData);
                specificationListView.setAdapter(typeAdapter);

                //删除规格
                typeAdapter.setOnitemDeleteClickListener(new SpecificationAdapter.OnitemDeleteClickListener() {
                    @Override
                    public void itemDeleteClick(View view, int position) {

                        showAlertMsgDialog("温馨提示", "确认删除该规格？", "确认", "取消", position);
                    }
                });
            }

            if (data.getFile_path() != null && !TextUtils.isEmpty(data.getFile_path())) {

                file_path.add(data.getFile_path().toString());//图片地址
                String file_path = ApiUrlConstant.API_IMG_URL + data.getFile_path();
                new GlideImageLoader().displayImage(getActivity(), file_path, itemGridaImage);

            }

            etName.setText(data.getName().toString());
            cate_id = data.getCate_id();
            goods_img = data.getGoods_img();
            tvSpType.setText(data.getCate_name());//分类
            cate_id = data.getCate_id();
            etPrice.setText(data.getPrice());
            etRlPrice.setText(data.getO_price());
            etEveryIndentry.setText(data.getStock());
            etContainerNum.setText(data.getBox_num());
            etContainerPrice.setText(data.getBox_price());
            tvSpDw.setText(data.getUnit_name());
            unit_id = data.getUnit_id();
            etSpDesc.setText(data.getDescription());

            //1-是 0-否
            isnew = data.getIs_new();//是否新品
            is_sale = data.getIs_sale();//是否上架
            is_sale0 = data.getIs_sale0();//星期日
            is_sale1 = data.getIs_sale1();//星期一
            is_sale2 = data.getIs_sale2();//星期二
            is_sale3 = data.getIs_sale3();//星期三
            is_sale4 = data.getIs_sale4();//星期四
            is_sale5 = data.getIs_sale5();//星期五
            is_sale6 = data.getIs_sale6();//星期六

            if (isnew.equals("1")) {
                checkbox.setChecked(true);
            } else {
                checkbox.setChecked(false);
            }

            if (is_sale.equals("1")) {
                cbSale.setChecked(true);
            } else {
                cbSale.setChecked(false);
            }

            if (is_sale0.equals("1")) {
                cb0.setChecked(true);
            } else {
                cb0.setChecked(false);
            }


            if (is_sale1.equals("1")) {
                cb1.setChecked(true);
            } else {
                cb1.setChecked(false);
            }


            if (is_sale2.equals("1")) {
                cb2.setChecked(true);
            } else {
                cb2.setChecked(false);
            }

            if (is_sale3.equals("1")) {
                cb3.setChecked(true);
            } else {
                cb3.setChecked(false);
            }


            if (is_sale4.equals("1")) {
                cb4.setChecked(true);
            } else {
                cb4.setChecked(false);
            }


            if (is_sale5.equals("1")) {
                cb5.setChecked(true);
            } else {
                cb5.setChecked(false);
            }


            if (is_sale6.equals("1")) {
                cb6.setChecked(true);
            } else {
                cb6.setChecked(false);
            }
        }
    }

    @Override
    public void onsuccess(String message) {

        showShortToast(message);
        removeFragment();
    }

    @Override
    public String getGoods_img() {
        return goods_img;
    }

    @Override
    public void onsuccessUpdataShop(String message) {

        showShortToast("修改成功！");

        //发送消息刷新页面
        EventBus.getDefault().post(new AddShopMangerEvent());
        removeFragment();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String tag = buttonView.getTag().toString();
        switch (tag) {
            case "0"://星期日

                if (isChecked) {
                    is_sale0 = "1";
                } else {
                    is_sale0 = "0";
                }

                break;
            case "1"://星期1
                if (isChecked) {
                    is_sale1 = "1";
                } else {
                    is_sale1 = "0";
                }

                break;
            case "2"://星期2

                if (isChecked) {
                    is_sale2 = "1";
                } else {
                    is_sale2 = "0";
                }

                break;
            case "3"://星期3

                if (isChecked) {
                    is_sale3 = "1";
                } else {
                    is_sale3 = "0";
                }

                break;
            case "4"://星期4

                if (isChecked) {
                    is_sale4 = "1";
                } else {
                    is_sale4 = "0";
                }

                break;
            case "5"://星期5

                if (isChecked) {
                    is_sale5 = "1";
                } else {
                    is_sale5 = "0";
                }

                break;
            case "6"://星期6

                if (isChecked) {
                    is_sale6 = "1";
                } else {
                    is_sale6 = "0";
                }

                break;

            case "issale"://是否上架

                if (isChecked) {
                    is_sale = "1";
                } else {
                    is_sale = "0";
                }

                break;
            case "isnew"://是否是新品

                if (isChecked) {
                    isnew = "1";
                } else {
                    isnew = "0";
                }

                break;
        }
    }
}
