package com.huafan.huafano2omanger.view.fragment.mine.shopdetail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.caimuhao.rxpicker.RxPicker;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.caimuhao.rxpicker.utils.DensityUtil;
import com.ghnor.flora.Flora;
import com.ghnor.flora.callback.Callback;
import com.ghnor.flora.spec.calculation.Calculation;
import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.constant.ApiUrlConstant;
import com.huafan.huafano2omanger.entity.ImgDataBean;
import com.huafan.huafano2omanger.entity.ShopDetailBean;
import com.huafan.huafano2omanger.mvp.KFragment;
import com.huafan.huafano2omanger.utils.GlideImageLoader;
import com.huafan.huafano2omanger.utils.RxImageLoader;
import com.huafan.huafano2omanger.utils.UIUtils;
import com.huafan.huafano2omanger.view.customer.CircleImageView;
import com.huafan.huafano2omanger.view.customer.CustomEditText;
import com.huafan.huafano2omanger.view.customer.NormalTopBar;
import com.huafan.huafano2omanger.view.customer.QcodeDialog;
import com.huafan.huafano2omanger.view.customer.ShapeLoadingDialog;
import com.huafan.huafano2omanger.view.customer.actionsheet.ActionSheet;
import com.huafan.huafano2omanger.view.fragment.shop.messagemanagement.MessageManagementActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by caishen on 2018/1/20.
 * by--店铺详情
 */

public class ShopDetailFragment extends KFragment<IShopDetailView, IShopDetailPrenter>
        implements NormalTopBar.normalTopClickListener, IShopDetailView, ActionSheet.ActionSheetListener {

    @BindView(R.id.normal_top)
    NormalTopBar normalTop;
    @BindView(R.id.im_bg)
    ImageView imBg;
    @BindView(R.id.user_headerimg)
    CircleImageView userHeaderimg;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_shop_phone)
    TextView tvShopPhone;
    @BindView(R.id.tv_shop_type)
    TextView tvShopType;
    @BindView(R.id.tv_shop_adress)
    TextView tvShopAdress;
    @BindView(R.id.tv_shop_adress_detail)
    TextView tvShopAdressDetail;
    @BindView(R.id.et_shop_principal_name)
    CustomEditText etShopPrincipalName;
    @BindView(R.id.et_shop_principal_phone)
    CustomEditText etShopPrincipalPhone;
    @BindView(R.id.iv_business)
    ImageView ivBusiness;
    @BindView(R.id.iv_live_action1)
    ImageView ivLiveAction1;
    @BindView(R.id.iv_live_action2)
    ImageView ivLiveAction2;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    private ShapeLoadingDialog shapeLoadingDialog;
    private List<File> fileList = new ArrayList<>();
    private String filePath = "";//logo图片地址
    private int imageSize;

    @Override
    public IShopDetailPrenter createPresenter() {
        return new IShopDetailPrenter();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_shop_detail;
    }

    @Override
    public void initData() {

        mPresenter.getShopDetail();
    }

    @Override
    protected void init(@Nullable View view, @Nullable Bundle savedInstanceState) {

        normalTop.setTitleText("店铺信息");
        normalTop.setTitleTextColor(Color.WHITE);
        normalTop.setTopClickListener(this);
        UIUtils.setTouchDelegate(normalTop.getLeftImage(), 50);
        UIUtils.setTouchDelegate(normalTop.getRightImage(), 50);

        imageSize = DensityUtil.getDeviceWidth(userHeaderimg.getContext()) / 3;

        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .delay(5000)
                .build();

        initData();
    }

    public static KFragment newIntence() {

        ShopDetailFragment shopDetailFragment = new ShopDetailFragment();
        Bundle bundle = new Bundle();
        shopDetailFragment.setArguments(bundle);
        return shopDetailFragment;
    }

    @Override
    public void onLeftClick(View view) {

        removeFragment();
    }

    @Override
    public void onRightClick(View view) {

        //消息
        Intent intent = new Intent(getActivity(), MessageManagementActivity.class);
        startActivity(intent);
    }

    @Override
    public void onTitleClick(View view) {

    }

    @OnClick({R.id.user_headerimg, R.id.btn_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_headerimg://修改店铺logo

                getActivity().setTheme(R.style.ActionSheetStyleiOS7);
                showActionSheet(userHeaderimg.getId(), "请选择商标图照片");

                break;
            case R.id.btn_commit://提交保存

                mPresenter.up_merchant_info();

                break;
        }
    }

    /**
     * 显示logo图片
     *
     * @param viewid
     */
    public void showActionSheet(int viewid, String titles) {

        ActionSheet.createBuilder(getActivity(), getFragmentManager(), viewid)
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles(titles)
                .setCancelableOnTouchOutside(true).setListener(this).show();
    }

    @Override
    public void onError(String s) {

        showShortToast(s);
    }

    @Override
    public void hideDialog() {

        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
        }
    }

    @Override
    public void onSuccessData(ShopDetailBean data) {

        if (data != null) {

            ShopDetailBean.InfoBean info = data.getInfo();
            tvShopName.setText(info.getMerch_name() == null ? "" : info.getMerch_name());//店铺名称
            tvShopPhone.setText(info.getPhone() == null ? "" : info.getPhone());//电话
            tvShopType.setText(info.getMerch_type() == null ? "" : info.getMerch_type());//店铺类别
            tvShopAdress.setText(info.getArea_name() == null ? "" : info.getArea_name());//地区
            etShopPrincipalName.setText(info.getManager_name() == null ? "" : info.getManager_name());//负责人姓名
            etShopPrincipalPhone.setText(info.getManager_tel() == null ? "" : info.getManager_tel());//负责人电话
            tvShopAdressDetail.setText(info.getAddress() == null ? "" : info.getAddress());//详细地址

            GlideImageLoader glideImageLoader = new GlideImageLoader();
            //logo
            String logo_url = ApiUrlConstant.API_IMG_URL + info.getLogo_url();
            new RxImageLoader().display(userHeaderimg,logo_url,imageSize,imageSize);

            //营业执照
            String license_url = ApiUrlConstant.API_IMG_URL + info.getLicense_url();
            glideImageLoader.displayImage(getActivity(), license_url, ivBusiness);
            ivBusiness.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showBigView(license_url);
                }
            });

            //店铺实景
            List<String> scenery_url = info.getScenery_url();
            ArrayList<String> objects = new ArrayList<>();
            for (int i = 0; i < scenery_url.size(); i++) {
                String s = ApiUrlConstant.API_IMG_URL + scenery_url.get(i);
                objects.add(s);
                if (i == 0) {
                    glideImageLoader.displayImage(getActivity(), s, ivLiveAction1);
                    ivLiveAction1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showBigView(s);
                        }
                    });
                } else {
                    glideImageLoader.displayImage(getActivity(), s, ivLiveAction2);
                    ivLiveAction2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showBigView(s);
                        }
                    });
                }
            }
        }
    }

    @Override
    public void showDialog() {

        shapeLoadingDialog.show();
    }

    @Override
    public List<File> getImgList() {
        return fileList;
    }

    @Override
    public String getType() {
        return "3";
    }

    @Override
    public void mofityPhoto(ImgDataBean data) {

        if (data != null) {

            showShortToast("上传成功");

            List<String> file_path = data.getFile_path();
            if (file_path != null && file_path.size() > 0) {
                filePath = file_path.get(0);
                new RxImageLoader().display(userHeaderimg, ApiUrlConstant.API_IMG_URL + filePath, imageSize, imageSize);
            } else {
                filePath = "";
            }
        }
    }

    @Override
    public String getfilePath() {
        return filePath;
    }

    @Override
    public String getmanager_name() {
        return etShopPrincipalName.getText().toString().trim();
    }

    @Override
    public String getmanager_tel() {
        return etShopPrincipalPhone.getText().toString().trim();
    }

    @Override
    public void onsuccess(String message) {

        showShortToast("修改成功");
        removeFragment();
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index, int viewId) {
        switch (index + 1) {
            case 1:
                // 选择图片
                RxPicker.of().single(true).start(getActivity()).subscribe(new Consumer<List<ImageItem>>() {

                    @Override
                    public void accept(@NonNull List<ImageItem> imageItems) throws Exception {

                        if (!fileList.isEmpty() && fileList.size() > 0) {
                            fileList.clear();
                        }
                        for (ImageItem imageItem : imageItems) {
                            compressByUs(imageItem.getPath(), "2");
                        }
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
    private void compressByUs(String path, String tags) {

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
                fileList.add(new File(compressResults));
                mPresenter.uploadImg();
            }
        });
    }

    //查看大图
    public void showBigView(String s){
        QcodeDialog dialog = new QcodeDialog(getActivity(), R.style.Dialog);//设置dialog的样式
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.dayangstyle); // 添加动画
        dialog.show();
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        //这句就是设置dialog横向满屏了。
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = (int) (d.getHeight() * 0.7);     //dialog屏幕占比
        window.setAttributes(lp);
//        ImageView qrCodeImg = (ImageView) dialog.findViewById(R.id.qr_code_img);
        PhotoView qrCodeImg = (PhotoView) dialog.findViewById(R.id.qr_code_img);
        new GlideImageLoader().displayImage(getActivity(), s, qrCodeImg);
    }
}
