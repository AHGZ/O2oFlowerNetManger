package com.huafan.huafano2omanger.entity;

import android.content.Context;
import android.graphics.Bitmap;

import com.huafan.huafano2omanger.R;
import com.huafan.huafano2omanger.utils.DateUtils;
import com.huafan.huafano2omanger.utils.FileUtils;
import com.huafan.huafano2omanger.utils.PrintUtils;
import com.huafan.huafano2omanger.utils.QRCodeUtil;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import am.util.printer.PrintDataMaker;
import am.util.printer.PrinterWriter;
import am.util.printer.PrinterWriter58mm;
import am.util.printer.PrinterWriter80mm;

/**
 * 测试数据生成器
 * Created by Alex on 2016/11/10.
 */

public class TestPrintDataMaker implements PrintDataMaker {

    private final WaitDisposeBean.ListBean datas;
    private final String merch_name;
    private final String today_count;
    private Context context;
    private String qr;
    private int width;
    private int height;

    public TestPrintDataMaker(Context context, String qr, int width, int height, WaitDisposeBean.ListBean data,
                              String merch_name, String today_count) {
        this.context = context;
        this.qr = qr;
        this.width = width;
        this.height = height;
        this.datas = data;
        this.merch_name = merch_name;
        this.today_count = today_count;
    }

    @Override
    public List<byte[]> getPrintData(int type) {
        ArrayList<byte[]> data = new ArrayList<>();

        try {
            PrinterWriter printer;
            printer = type == PrinterWriter58mm.TYPE_58 ? new PrinterWriter58mm(height, width) : new PrinterWriter80mm(height, width);
            printer.setAlignCenter();
            data.add(printer.getDataAndReset());

//            ArrayList<byte[]> image1 = printer.getImageByte(context.getResources(), R.mipmap.logo);
//            data.addAll(image1);
            printer.setEmphasizedOn();
            printer.setAlignCenter();
            printer.setFontSize(0);
            printer.print("***  #" + today_count + "花返网" + "***");
            printer.printLineFeed();
            printer.setEmphasizedOff();
            printer.print("--------------------------------");
            printer.printLineFeed();

            printer.setAlignCenter();
            printer.print(merch_name);
            printer.printLineFeed();

            printer.setAlignCenter();
            DateUtils dateUtils = new DateUtils();
            printer.print("下单时间：" + dateUtils.timetodate(datas.getCreated()));
            printer.printLineFeed();
            printer.print("--------------------------------");
            printer.printLineFeed();

//            printer.print(datas.getCustomer_tel());
//            printer.printLineFeed();
//            printer.setAlignLeft();
//            printer.printLineFeed();
//            printer.print(datas.getOrder_num());//订单号
//            printer.printLineFeed();
//            printer.print("预计送达：" + TimeTools.getCountTimeByLong(datas.getBooking_time()));

            List<WaitDisposeBean.ListBean.GoodsBean> goods = datas.getGoods();
            for (int i = 0; i < goods.size(); i++) {

                printer.print(PrintUtils.printThreeData(goods.get(i).getGoods_name()
                        , "×" + goods.get(i).getGoods_num(), "￥" + goods.get(i).getGoods_price()));
                printer.printLineFeed();
                printer.printLineFeed();
            }

            printer.printLineFeed();

            printer.print("------------其他费用------------");
            printer.print(PrintUtils.printTwoData("餐盒x" + datas.getDinner_set_count(), "￥" + datas.getBox_cost()));
            printer.printLineFeed();
            printer.print(PrintUtils.printTwoData("配送费", "￥" + datas.getDistrib_cost()));
            printer.printLineFeed();
            printer.print("--------------------------------");
            printer.printLineFeed();

            printer.setAlignLeft();
            printer.setEmphasizedOn();
            String pay_channel = datas.getPay_channel();
            if (pay_channel.equals("1")) {
                printer.print("已付               ");
            } else if (pay_channel.equals("2")) {
                printer.print("已付               ");
            } else if (pay_channel.equals("3")) {
                printer.print("已付               ");
            } else if (pay_channel.equals("4")) {
                printer.print("已付               ");
            } else {
                printer.print("未支付              ");
            }

            printer.print("合计：￥" + datas.getPrice());//合计费用
            printer.printLineFeed();
            printer.print("--------------------------------");
            printer.printLineFeed();

            printer.setAlignLeft();
            printer.setEmphasizedOn();
            printer.print(datas.getDetail_address());//地址
            printer.setEmphasizedOff();
            printer.printLineFeed();
            printer.printLineFeed();

            printer.setEmphasizedOn();
            printer.setAlignLeft();
            printer.print(datas.getCustomer_name());//姓名
            printer.setEmphasizedOff();
            printer.printLineFeed();
            printer.printLineFeed();

            printer.setEmphasizedOn();
            printer.print(datas.getCustomer_tel());//收货人手机号
            printer.setEmphasizedOff();
            printer.printLineFeed();
            printer.printLineFeed();
            printer.print("备注：" + datas.getNote());
            printer.printLineFeed();
            printer.printLineFeed();
            printer.printLineFeed();
            data.add(printer.getDataAndReset());

            String bitmapPath = FileUtils.getExternalFilesDir(context, "Temp") + "tmp_qr.jpg";
            Bitmap bitmap = QRCodeUtil.creatBarcode(context, datas.getOrder_num(), 380, 135, false);
            boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(bitmapPath));
            if (compress) {
                ArrayList<byte[]> image2 = printer.getImageByte(bitmapPath);
                data.addAll(image2);
            } else {
                ArrayList<byte[]> image2 = printer.getImageByte(context.getResources(), R.drawable.ic_printer_qr);
                data.addAll(image2);
            }
            printer.setAlignCenter();
            printer.print(datas.getOrder_num());


            printer.printLineFeed();
            printer.printLineFeed();
            printer.printLineFeed();

            printer.setEmphasizedOn();
            printer.setAlignCenter();
            printer.setFontSize(0);
            printer.print("***  #" + today_count + "完" + "***");
            printer.setEmphasizedOff();
            printer.printLineFeed();
            printer.printLineFeed();
            printer.printLineFeed();
            printer.feedPaperCutPartial();
            data.add(printer.getDataAndClose());
            return data;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
