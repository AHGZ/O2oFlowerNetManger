package com.huafan.huafano2omanger.constant;

/**
 * author: zhangpeisen
 * created on: 2017/10/9 下午3:55
 * description: api接口类
 */
public interface ApiUrlConstant {
    // 测试
//    String API_BASE_URL = "http://192.168.1.220/";
    String API_BASE_URL = "http://192.168.1.253:8082/";
//        String API_BASE_URL = "http://api.huafanwang.com/";
    // https
//    String API_BASE_URL = "https://192.168.1.253/";
    // 张仪
//    String API_BASE_URL = "http://192.168.1.157/";
    // 图片地址
//    String API_IMG_URL = "http://192.168.1.220:8080/";
    String API_IMG_URL = "http://192.168.1.253:8082/";
//    String API_IMG_URL = "http://api.huafanwang.com/";
    // 登录
    String HFW_LOGIN = API_BASE_URL + "houtai/index/login";
    // 注册
    String HFW_REGISTER = API_BASE_URL + "houtai/index/register";
    // 探测手机号接口
    String HFW_CHECK_MOBILE = API_BASE_URL + "houtai/index/check_mobile";
    // 获取验证码
    String HFW_SENDMOBILECODE = API_BASE_URL + "houtai/index/gen_mobile_code";
    // 归属省市
    String GET_PROVINCE_CITY = API_BASE_URL + "houtai/common/get_province_city";
    // 重置登录密码
    String RESET_ACCOUNT_PWD = API_BASE_URL + "houtai/index/reset_account_pwd";
    //修改登录密码
    String UPDATE_USER_PWD = API_BASE_URL + "home/user/update_user_pwd";
    // 个人中心
    String GET_MERCH_CORE = API_BASE_URL + "houtai/merchant/get_merch_core";
    // 门店分类管理查询商品分类
    String SELECT_SHOP_CLASSIFY = API_BASE_URL + "houtai/Goodcate/selCate";
    // 门店分类管理添加商品分类
    String ADD_SHOP_CLASSIFY = API_BASE_URL + "houtai/Goodcate/addCate";
    // 门店分类管理删除商品分类
    String DELETE_SHOP_CLASSIFY = API_BASE_URL + "houtai/Goodcate/delCate";
    // 门店分类管理修改商品分类
    String UPDATE_SHOP_CLASSIFY = API_BASE_URL + "houtai/Goodcate/upCate";
    // 门店商品单位查询商品单位
    String SELECT_SHOP_UNIT = API_BASE_URL + "houtai/Goodunit/merchUnit";
    // 门店商品单位添加商品单位
    String ADD_SHOP_UNIT = API_BASE_URL + "houtai/Goodunit/addUnit";
    // 门店商品单位删除商品单位
    String DELETE_SHOP_UNIT = API_BASE_URL + "houtai/Goodunit/delUnit";
    // 门店商品单位修改商品单位
    String UPDATE_SHOP_UNIT = API_BASE_URL + "houtai/Goodunit/upUnit";
    //查看商品
    String SELGOODSLIST = API_BASE_URL + "houtai/Goods/selGoodsList";
    //上传图片返回地址
    String UPLOAD_IMG = API_BASE_URL + "houtai/Upload/upload_img";
    //修改商品
    String UPGOODS = API_BASE_URL + "houtai/Goods/upGoods";
    //添加商品
    String ADDGOODS = API_BASE_URL + "houtai/Goods/addGoods";
    //删除商品
    String DELGOODS = API_BASE_URL + "houtai/Goods/delGoods";
    //查看商品详情
    String GOODSXQ = API_BASE_URL + "houtai/Goods/goodsxq";
    //商品置顶排序
    String GOODTOPSORT = API_BASE_URL + "houtai/Goods/goodTopSort";
    //商品对调排序
    String GOODSWAPSORT = API_BASE_URL + "houtai/Goods/goodSwapSort";
    //获取接单设置
    String GET_ORDER_RECEIVING_SETTING = API_BASE_URL + "houtai/merch_setting/get_tni_setting";
    //更改接单设置
    String CHANGE_ORDER_RECEIVING_SETTING = API_BASE_URL + "houtai/merch_setting/up_tni_setting";
    //获取配送设置
    String GET_DISPATCHING_SETTING = API_BASE_URL + "houtai/merch_setting/get_distrib_setting";
    //更改配送设置
    String CHANGE_DISPATCHING_SETTING = API_BASE_URL + "houtai/merch_setting/up_distrib_setting";
    //获取营业设置
    String GET_DOBUSINESS_SETTING = API_BASE_URL + "houtai/merch_setting/get_business";
    //修改营业设置
    String UPDATE_DOBUSINESS_SETTING = API_BASE_URL + "houtai/merch_setting/up_business";
    //添加营业时间
    String ADD_DOBUSINESS_TIME = API_BASE_URL + "houtai/merch_business_setting/add_business_time";
    //删除营业时间
    String DELETE_DOBUSINESS_TIME = API_BASE_URL + "houtai/merch_business_setting/del_business_time";
    //修改营业时间
    String UPDATE_DOBUSINESS_TIME = API_BASE_URL + "houtai/merch_business_setting/up_business_time";
    // 获取地图设置
    String SETTING_GET_MAP = API_BASE_URL + "houtai/merch_setting/get_map";
    // 更改地图设置
    String SETTING_UP_MAP = API_BASE_URL + "houtai/merch_setting/up_map";
    //查看订单评价
    String SELGOODSEVAL = API_BASE_URL + "houtai/merch_goods_eval/selGoodsEval";
    //回复店铺评价
    String HFGOODSEVAL = API_BASE_URL + "houtai/merch_goods_eval/hfGoodsEval";
    //获取商家评价
    String SELEVAL = API_BASE_URL + "houtai/merch_eval/selEval";
    //回复商家评价
    String HFEVAL = API_BASE_URL + "houtai/merch_eval/hfEval";
    //查看订单
    String SELORDER = API_BASE_URL + "houtai/Order/selOrder";
    //查看退款列表
    String SELREFUND = API_BASE_URL + "houtai/Refund/selRefund";
    //确认接单
    String CONFIRMORDER = API_BASE_URL + "houtai/Order/confirmOrder";
    //同意退款
    String TYREFUND = API_BASE_URL + "houtai/Refund/tyRefund";
    //拒绝退款
    String REFUSINGREFUND = API_BASE_URL + "houtai/Refund/refusingRefund";
    //拒绝接单
    String REFUSINGORDER = API_BASE_URL + "houtai/Order/refusingOrder";
    //取消订单
    String CANCELORDER = API_BASE_URL + "houtai/Order/cancelOrder";
    //确认收货
    String CONFIRMCOM = API_BASE_URL + "houtai/Order/confirmCom";
    //获取团购列表数据
    String SELGROUP = API_BASE_URL + "houtai/Group/selGroup";
    //添加团购
    String ADDGROUP = API_BASE_URL + "houtai/Group/addGroup";
    //团购详情
    String GROUPXQ = API_BASE_URL + "houtai/Group/groupXq";
    //修改团购
    String GROUPUP = API_BASE_URL + "houtai/Group/groupUp";
    //修改团购状态
    String GROUPSTATE = API_BASE_URL + "houtai/Group/groupState";
    //查看满返
    String SELECT_FULL_RETURN = API_BASE_URL + "houtai/rebate_setting/rebateList";
    //添加满返
    String ADD_FULL_RETURN = API_BASE_URL + "houtai/rebate_setting/rebateAdd";
    //修改满返
    String UPDATE_FULL_RETURN = API_BASE_URL + "houtai/rebate_setting/rebateUp";
    //删除满返
    String DELETE_FULL_RETURN = API_BASE_URL + "houtai/rebate_setting/rebateDel";
    //添加提现
    String ADD_CASH = API_BASE_URL + "houtai/Withdrawals/addWithdrawals";
    //提现
    String CASH = API_BASE_URL + "houtai/Withdrawals/selWithdrawals";
    //获取店铺信息
    String GET_MERCHANT_INFO = API_BASE_URL + "houtai/merchant/get_merchant_info";
    //修改店铺信息
    String UP_MERCHANT_INFO = API_BASE_URL + "houtai/merchant/up_merchant_info";
    //查询财务概况
    String SELECT_FINANCING_DETAIL = API_BASE_URL + "houtai/Finance/selFinance";
    //查询财务概况团购明细
    String SELECT_GROUP_DETAIL = API_BASE_URL + "houtai/Finance/groupDetailed";
    //查询财务概况外卖明细
    String SELECT_TAKEOUT_DETAIL = API_BASE_URL + "houtai/Finance/wmDetailed";
    //提交团购券码
    String SUBMIT_CODE = API_BASE_URL + "houtai/Group/Writeoff";
    //获取指定分类消息列表
    String GET_NOTICE_LIST = API_BASE_URL + "home/notice/get_notice_list";
    //获取消息详情并更改状态
    String GET_NOTICE_INFO = API_BASE_URL + "home/notice/get_notice_info";
    //2.1检查用户登录密码
    String CHECK_PWD = API_BASE_URL + "houtai/merch_card/check_pwd";
    //去绑卡
    String GO_BIND_CARD = API_BASE_URL + "houtai/merch_card/go_bind_card";
    //添加银行卡
    String ADD_CARD = API_BASE_URL + "houtai/merch_card/add_card";
    // 银行卡列表
    String CARD_LIST = API_BASE_URL + "houtai/merch_card/card_list";
    //解绑
    String UN_BIND = API_BASE_URL + "houtai/merch_card/un_bind";
    //商家支付宝提现
    String WITHDRAWALS = API_BASE_URL + "home/Alipay/merchWithdrawals";
    //商家银行卡提现
    String WITHDRAWALS_BANKCARD = API_BASE_URL + "home/Wallet/Withdrawals";
    // 查看提现
    String HFW_SELWITHDRAWALS = API_BASE_URL + "home/Wallet/selWithdrawals";


    //自动接单
    String SELORDERINFO = API_BASE_URL + "houtai/Order/selOrderInfo";
}
