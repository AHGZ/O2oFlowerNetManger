package com.huafan.huafano2omanger.view.fragment.shop.shopmanger;

import android.annotation.SuppressLint;

import com.huafan.huafano2omanger.constant.Constants;
import com.huafan.huafano2omanger.entity.DetailGroupBean;
import com.huafan.huafano2omanger.entity.GoodTopSortBean;
import com.huafan.huafano2omanger.entity.GoodsDetailBean;
import com.huafan.huafano2omanger.entity.ImgDataBean;
import com.huafan.huafano2omanger.entity.ReplyBean;
import com.huafan.huafano2omanger.entity.SpecificationBean;
import com.huafan.huafano2omanger.mvp.IModel;
import com.huafan.huafano2omanger.mvp.IModelImpl;
import com.huafan.huafano2omanger.service.GroupSercice;
import com.huafan.huafano2omanger.service.ShopService;
import com.huafan.huafano2omanger.service.UpLoadImgService;
import com.huafan.huafano2omanger.utils.MD5Utils;
import com.huafan.huafano2omanger.utils.SignUtil;
import com.rxy.netlib.http.ApiResponse;
import com.rxy.netlib.http.RetrofitUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * Created by caishen on 2018/1/2.
 * by--
 */

public class AddShopMangerModel implements IModel {

    private final Retrofit retrofit;
    private final CompositeDisposable compositeDisposable;
    private Disposable subscribe;

    @Override
    public void cancel() {

        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public AddShopMangerModel() {

        //初始化网络请求
        retrofit = RetrofitUtils.getInstance();
        compositeDisposable = new CompositeDisposable();
    }

    //添加
    public void addGoods(String id, String name, List<String> imgPath, String price, String getoprice, String getboxnum,
                         String box_price, String unitId, String isNew, String sale0, String sale1, String sale2, String sale3,
                         String sale4, String sale5, String sale6, String sale, String description,
                         String stock, List<SpecificationBean> specificationBeen, IModelImpl<ApiResponse<GoodTopSortBean>, GoodTopSortBean> listener) throws JSONException {

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        String personInfos = "";
        JSONObject tmpObj = null;
        if (specificationBeen != null && specificationBeen.size() > 0) {

            for (int i = 0; i < specificationBeen.size(); i++) {
                tmpObj = new JSONObject();
                tmpObj.put("goods_spec", specificationBeen.get(i).getGoods_spec());
                tmpObj.put("o_spec_price", specificationBeen.get(i).getO_spec_price());
                tmpObj.put("spec_price", specificationBeen.get(i).getSpec_price());
                tmpObj.put("spec_stock", specificationBeen.get(i).getSpec_stock());
                jsonArray.put(tmpObj);
                tmpObj = null;
            }

            personInfos = jsonArray.toString(); // 将JSONArray转换得到String

        } else {

            personInfos = "";
        }


        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("cate_id", id);
        map.put("img_path", imgPath.get(0));
        map.put("price", price);
        map.put("o_price", getoprice);
        map.put("box_num", getboxnum);
        map.put("box_price", box_price);
        map.put("unit_id", unitId);
        map.put("is_new", isNew);
        map.put("is_sale0", sale0);
        map.put("is_sale1", sale1);
        map.put("is_sale2", sale2);
        map.put("is_sale3", sale3);
        map.put("is_sale4", sale4);
        map.put("is_sale5", sale5);
        map.put("is_sale6", sale6);
        map.put("is_sale", sale);
        map.put("description", description);
        map.put("stock", stock);
        map.put("goods_spec", personInfos);

        HashMap<String, String> param = new HashMap<>();
        param.put("name", name);
        param.put("cate_id", id);
        param.put("img_path", imgPath.get(0));
        param.put("price", price);
        param.put("o_price", getoprice);
        param.put("box_num", getboxnum);
        param.put("box_price", box_price);
        param.put("unit_id", unitId);
        param.put("is_new", isNew);
        param.put("is_sale0", sale0);
        param.put("is_sale1", sale1);
        param.put("is_sale2", sale2);
        param.put("is_sale3", sale3);
        param.put("is_sale4", sale4);
        param.put("is_sale5", sale5);
        param.put("is_sale6", sale6);
        param.put("is_sale", sale);
        param.put("description", description);
        param.put("stock", stock);
        param.put("goods_spec", personInfos);
        String sign = SignUtil.getInstance().getSign(map);
        ShopService personal = retrofit.create(ShopService.class);
        Disposable subscribe = personal.addShop(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<GoodTopSortBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<GoodTopSortBean> sendCodeBeanApiResponse) throws Exception {
                        listener.onComplete(sendCodeBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //修改
    public void updata(String id, String name, List<String> imgPath, String price, String getoprice, String getboxnum,
                       String box_price, String unitId, String isNew, String sale0, String sale1, String sale2, String sale3,
                       String sale4, String sale5, String sale6, String sale, String description,
                       String stock, List<SpecificationBean> specificationBeen, String goods_img, String goodId, IModelImpl<ApiResponse<ReplyBean>,
            ReplyBean> listener) throws JSONException {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        String personInfos = "";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject tmpObj = null;

        if (specificationBeen != null && specificationBeen.size() > 0) {

            for (int i = 0; i < specificationBeen.size(); i++) {

                tmpObj = new JSONObject();
                tmpObj.put("goods_spec", specificationBeen.get(i).getGoods_spec());
                tmpObj.put("o_spec_price", specificationBeen.get(i).getO_spec_price());
                tmpObj.put("spec_price", specificationBeen.get(i).getSpec_price());
                tmpObj.put("spec_stock", specificationBeen.get(i).getSpec_stock());
                jsonArray.put(tmpObj);
                tmpObj = null;
            }

            personInfos = jsonArray.toString(); // 将JSONArray转换得到String

        } else {

            personInfos = "";
        }

        map.put("name", name);
        map.put("cate_id", id);
        map.put("img_path", imgPath.get(0));
        map.put("price", price);
        map.put("o_price", getoprice);
        map.put("box_num", getboxnum);
        map.put("box_price", box_price);
        map.put("unit_id", unitId);
        map.put("is_new", isNew);
        map.put("is_sale0", sale0);
        map.put("is_sale1", sale1);
        map.put("is_sale2", sale2);
        map.put("is_sale3", sale3);
        map.put("is_sale4", sale4);
        map.put("is_sale5", sale5);
        map.put("is_sale6", sale6);
        map.put("is_sale", sale);
        map.put("description", description);
        map.put("stock", stock);
        map.put("goods_img", goods_img);
        map.put("goods_id", goodId);
        map.put("goods_spec", personInfos);

        param.put("name", name);
        param.put("cate_id", id);
        param.put("img_path", imgPath.get(0));
        param.put("price", price);
        param.put("o_price", getoprice);
        param.put("box_num", getboxnum);
        param.put("box_price", box_price);
        param.put("unit_id", unitId);
        param.put("is_new", isNew);
        param.put("is_sale0", sale0);
        param.put("is_sale1", sale1);
        param.put("is_sale2", sale2);
        param.put("is_sale3", sale3);
        param.put("is_sale4", sale4);
        param.put("is_sale5", sale5);
        param.put("is_sale6", sale6);
        param.put("is_sale", sale);
        param.put("description", description);
        param.put("stock", stock);
        param.put("goods_img", goods_img);
        param.put("goods_id", goodId);
        param.put("goods_spec", personInfos);


        String sign = SignUtil.getInstance().getSign(map);
        ShopService personal = retrofit.create(ShopService.class);
        Disposable subscribe = personal.updataShop(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<ReplyBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<ReplyBean> sendCodeBeanApiResponse) throws Exception {
                        listener.onComplete(sendCodeBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //上传图片
    public void upload(String type, List<File> userImglist, IModelImpl<ApiResponse<ImgDataBean>, ImgDataBean> listener) {

        // 带有图片信息上传
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        // 传入参数
        param.put("type", type);
        // 传入签名
        map.put("type", type);
        String sign = SignUtil.getInstance().getSign(map);
        UpLoadImgService upLoadImgService = retrofit.create(UpLoadImgService.class);
        Map<String, RequestBody> requestbodymap = new HashMap<>();
        for (int i = 0; i < userImglist.size(); i++) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), userImglist.get(i));
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), userImglist.get(i));
            requestbodymap.put("file" + i + "\";filename=\"" + userImglist.get(i).getName(), requestFile);
        }
        subscribe = upLoadImgService.upLoadImg(sign, Constants.TOKEN, type, requestbodymap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<ImgDataBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<ImgDataBean> sendCodeBeanApiResponse) throws Exception {
                        listener.onComplete(sendCodeBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //查看商品详情
    public void getShopDetail(String goodId, IModelImpl<ApiResponse<GoodsDetailBean>, GoodsDetailBean> listener) {

        // 带有图片信息上传
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        // 传入参数
        param.put("goods_id", goodId);
        // 传入签名
        map.put("goods_id", goodId);
        String sign = SignUtil.getInstance().getSign(map);
        ShopService personal = retrofit.create(ShopService.class);
        Disposable subscribe = personal.getShopDetail(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<GoodsDetailBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<GoodsDetailBean> sendCodeBeanApiResponse) throws Exception {
                        listener.onComplete(sendCodeBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }


    /**
     * @Title: getSign @Description: TODO @param @param token @param @param
     * v @param @param t @param @param deviceId @param @param
     * platform @param @return @return String 返回类型 @throws
     */
    @SuppressLint("DefaultLocale")
    public String getSign(HashMap<String, String> params, HashMap<String, List<SpecificationBean>> objectObjectHashMap) {
        // salt固定值
        params.put("salt", Constants.SALT);
        StringBuilder stringBuilder = new StringBuilder();
        List<String> sortParam = getNameSort(params);
        for (String key : sortParam) {
            //.append("&")
            stringBuilder.append(key).append("=").append(params.get(key));
        }

        List<String> sortParams = getNameSorts(objectObjectHashMap);
        for (String key : sortParams) {
            //.append("&")
            stringBuilder.append(key).append("=").append(params.get(key));
        }
        return getInterChangeSignStr(MD5Utils.MD5To32(stringBuilder.toString()));
    }


    public String getInterChangeSignStr(String signStr) {

        StringBuilder stringBuilder = new StringBuilder();
        // 前8位
        String signStrStart = signStr.substring(0, 8);
        // 中间字符串
        String signStrCenter = signStr.substring(8, signStr.length() - 8);
        // 后8位
        String signStrEnd = signStr.substring(signStr.length() - 8, signStr.length());
        stringBuilder.append(signStrEnd).append(signStrCenter).append(signStrStart);

        return stringBuilder.toString();
    }

    /**
     * @return void 返回类型 @throws
     * @Title: getNameSort
     * @Description: 获取排序的maps
     * TODO @param
     */
    @SuppressLint("DefaultLocale")
    public List<String> getNameSort(HashMap<String, String> params) {
        List<String> mEntityLists = new ArrayList<String>();
        for (String UrlParamKeys : params.keySet()) {
            mEntityLists.add(UrlParamKeys);
        }
        // 参数进行排序
        Collections.sort(mEntityLists, new StringComparator());
        return mEntityLists;
    }

    /**
     * @return void 返回类型 @throws
     * @Title: getNameSort
     * @Description: 获取排序的maps
     * TODO @param
     */
    @SuppressLint("DefaultLocale")
    public List<String> getNameSorts(HashMap<String, List<SpecificationBean>> params) {
        List<String> mEntityLists = new ArrayList<String>();
        for (String UrlParamKeys : params.keySet()) {
            mEntityLists.add(UrlParamKeys);
        }
        // 参数进行排序
        Collections.sort(mEntityLists, new StringComparator());
        return mEntityLists;
    }

    //提交团购信息
    public void commit(String gettitle, String short_title, String imags, String fitType, String intro,
                       String market_price, String price, String supply_price, String getstock, List<DetailGroupBean.GroupContentBean> group_content,
                       String getdescription, String getstarttime, String getendtime, String day_start, String day_end,
                       String holiday_usable, String weekend_usable, String need_resv, String resv_tips, String getrules,
                       String gettips, String getkeywords, String isnew, String is_sale, String is_takeaway,
                       String consume_avg, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {


        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        String group_contentInfos = "";

        JSONArray jsonArray = new JSONArray();
        JSONObject tmpObj = null;

        if (group_content != null && group_content.size() > 0) {

            for (int i = 0; i < group_content.size(); i++) {
                tmpObj = new JSONObject();
                try {
                    tmpObj.put("goods_name", group_content.get(i).getGoods_name());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    tmpObj.put("num", group_content.get(i).getNum());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    tmpObj.put("price", group_content.get(i).getPrice());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonArray.put(tmpObj);
                tmpObj = null;
            }

            group_contentInfos = jsonArray.toString(); // 将JSONArray转换得到String

        } else {

            group_contentInfos = "";
        }

        // 传入参数
        param.put("title", gettitle);
        param.put("short_title", short_title);
        param.put("imgs", imags);
        param.put("fit_type", fitType);
        param.put("intro", intro);
        param.put("market_price", market_price);
        param.put("price", price);
        param.put("supply_price", supply_price);
        param.put("stock", getstock);
        param.put("group_content", group_contentInfos);
        param.put("description", getdescription);
        param.put("starttime", getstarttime);
        param.put("endtime", getendtime);
        param.put("day_start", day_start);
        param.put("day_end", day_end);
        param.put("holiday_usable", holiday_usable);
        param.put("weekend_usable", weekend_usable);
        param.put("need_resv", need_resv);
        param.put("resv_tips", resv_tips);
        param.put("rules", getrules);
        param.put("tips", gettips);
        param.put("keywords", getkeywords);
        param.put("is_new", isnew);
        param.put("is_sale", is_sale);
        param.put("is_takeaway", is_takeaway);
        param.put("consume_avg", consume_avg);

        // 传入签名
        map.put("title", gettitle);
        map.put("short_title", short_title);
        map.put("imgs", imags);
        map.put("fit_type", fitType);
        map.put("intro", intro);
        map.put("market_price", market_price);
        map.put("price", price);
        map.put("supply_price", supply_price);
        map.put("stock", getstock);
        map.put("group_content", group_contentInfos);
        map.put("description", getdescription);
        map.put("starttime", getstarttime);
        map.put("endtime", getendtime);
        map.put("day_start", day_start);
        map.put("day_end", day_end);
        map.put("holiday_usable", holiday_usable);
        map.put("weekend_usable", weekend_usable);
        map.put("need_resv", need_resv);
        map.put("resv_tips", resv_tips);
        map.put("rules", getrules);
        map.put("tips", gettips);
        map.put("keywords", getkeywords);
        map.put("is_new", isnew);
        map.put("is_sale", is_sale);
        map.put("is_takeaway", is_takeaway);
        map.put("consume_avg", consume_avg);

        String sign = SignUtil.getInstance().getSign(map);
        ShopService personal = retrofit.create(ShopService.class);
        Disposable subscribe = personal.commitGroup(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<ReplyBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<ReplyBean> sendCodeBeanApiResponse) throws Exception {
                        listener.onComplete(sendCodeBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //获取团购详情
    public void getGroupDetail(String groupId, IModelImpl<ApiResponse<DetailGroupBean>, DetailGroupBean> listener) {

        // 带有图片信息上传
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        // 传入参数
        param.put("group_id", groupId);
        // 传入签名
        map.put("group_id", groupId);
        String sign = SignUtil.getInstance().getSign(map);
        GroupSercice personal = retrofit.create(GroupSercice.class);
        Disposable subscribe = personal.getGroupDetail(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<DetailGroupBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<DetailGroupBean> sendCodeBeanApiResponse) throws Exception {
                        listener.onComplete(sendCodeBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }

    //修改团购详情
    public void updataGroup(String gettitle, String short_title, String imgBanner, String fitType, String intro,
                            String market_price, String price, String supply_price, String getstock,
                            List<DetailGroupBean.GroupContentBean> group_content, String getdescription,
                            String getstarttime, String getendtime, String day_start, String day_end, String holiday_usable,
                            String weekend_usable, String need_resv, String resv_tips, String getrules, String gettips,
                            String getkeywords, String isnew, String is_sale, String is_takeaway, String consume_avg,
                            String img_id, String desc_id, String groupId, IModelImpl<ApiResponse<ReplyBean>, ReplyBean> listener) {

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> param = new HashMap<>();
        String group_contentInfos = "";

        JSONArray jsonArray = new JSONArray();
        JSONObject tmpObj = null;

        if (group_content != null && group_content.size() > 0) {

            for (int i = 0; i < group_content.size(); i++) {
                tmpObj = new JSONObject();
                try {
                    tmpObj.put("goods_name", group_content.get(i).getGoods_name());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    tmpObj.put("num", group_content.get(i).getNum());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    tmpObj.put("price", group_content.get(i).getPrice());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonArray.put(tmpObj);
                tmpObj = null;
            }

            group_contentInfos = jsonArray.toString(); // 将JSONArray转换得到String

        } else {

            group_contentInfos = "";
        }

        // 传入参数
        param.put("title", gettitle);
        param.put("short_title", short_title);
        param.put("imgs", imgBanner);
        param.put("fit_type", fitType);
        param.put("intro", intro);
        param.put("market_price", market_price);
        param.put("price", price);
        param.put("supply_price", supply_price);
        param.put("stock", getstock);
        param.put("group_content", group_contentInfos);
        param.put("description", getdescription);
        param.put("starttime", getstarttime);
        param.put("endtime", getendtime);
        param.put("day_start", day_start);
        param.put("day_end", day_end);
        param.put("holiday_usable", holiday_usable);
        param.put("weekend_usable", weekend_usable);
        param.put("need_resv", need_resv);
        param.put("resv_tips", resv_tips);
        param.put("rules", getrules);
        param.put("tips", gettips);
        param.put("keywords", getkeywords);
        param.put("is_new", isnew);
        param.put("is_sale", is_sale);
        param.put("is_takeaway", is_takeaway);
        param.put("consume_avg", consume_avg);
        param.put("img_id", img_id);
        param.put("desc_id", desc_id);
        param.put("group_id", groupId);

        // 传入签名
        map.put("title", gettitle);
        map.put("short_title", short_title);
        map.put("imgs", imgBanner);
        map.put("fit_type", fitType);
        map.put("intro", intro);
        map.put("market_price", market_price);
        map.put("price", price);
        map.put("supply_price", supply_price);
        map.put("stock", getstock);
        map.put("group_content", group_contentInfos);
        map.put("description", getdescription);
        map.put("starttime", getstarttime);
        map.put("endtime", getendtime);
        map.put("day_start", day_start);
        map.put("day_end", day_end);
        map.put("holiday_usable", holiday_usable);
        map.put("weekend_usable", weekend_usable);
        map.put("need_resv", need_resv);
        map.put("resv_tips", resv_tips);
        map.put("rules", getrules);
        map.put("tips", gettips);
        map.put("keywords", getkeywords);
        map.put("is_new", isnew);
        map.put("is_sale", is_sale);
        map.put("is_takeaway", is_takeaway);
        map.put("consume_avg", consume_avg);
        map.put("img_id", img_id);
        map.put("desc_id", desc_id);
        map.put("group_id", groupId);

        String sign = SignUtil.getInstance().getSign(map);
        GroupSercice personal = retrofit.create(GroupSercice.class);
        Disposable subscribe = personal.updataGroup(sign, Constants.TOKEN, param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse<ReplyBean>>() {
                    @Override
                    public void accept(@NonNull ApiResponse<ReplyBean> sendCodeBeanApiResponse) throws Exception {
                        listener.onComplete(sendCodeBeanApiResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.onError("-1", throwable.getMessage());
                    }
                });
        compositeDisposable.add(subscribe);
    }


    /**
     * @author zhangpeisen
     * @ClassName: StringComparator
     * @Description: TODO 参数排序
     * @date 2016年9月18日 下午6:38:36
     */
    public class StringComparator implements Comparator<String> {

        @Override
        public int compare(String Str1, String Str2) {
            // TODO Auto-generated method stub
            return Str1.compareTo(Str2);
        }
    }
}
