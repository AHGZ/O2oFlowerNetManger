package com.huafan.huafano2omanger.view.customer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huafan.huafano2omanger.R;

/**
 * Created by caishen on 2017/12/25.
 * by--自定义弹出输入框
 */

public class DoAppEditextAlertDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    public TextView txt_title;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    public TextView et_name;
    public TextView et_price;
    private OnposClickLitener onposClickLitener;
    public CustomEditText et_rl_price;

    public DoAppEditextAlertDialog(Activity activity) {
        this.context = activity;
        WindowManager windowManager = (WindowManager) activity.getSystemService(Context
                .WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public DoAppEditextAlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.app_text_dialog, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);
        et_name = (TextView) view.findViewById(R.id.et_name);
        et_price = (TextView) view.findViewById(R.id.et_price);
        et_rl_price = (CustomEditText) view.findViewById(R.id.et_rl_price);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
       /* lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.8)
                , LayoutParams.WRAP_CONTENT));*/
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (display.getWidth() * 0.9);
        dialog.setCanceledOnTouchOutside(false);
        return this;
    }

    public DoAppEditextAlertDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText("标题");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    public DoAppEditextAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public DoAppEditextAlertDialog setPositiveButton(String text, OnposClickLitener onposClickLitener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onposClickLitener != null) {
                    String trim = et_name.getText().toString().trim();
                    String trim1 = et_price.getText().toString().trim();
                    String trim2 = et_rl_price.getText().toString().trim();
                    onposClickLitener.onPosEditClick(trim, trim1, trim2);
                }

            }
        });
        return this;
    }

    public DoAppEditextAlertDialog setNegativeButton(String text, final View.OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
        dialog.setOnDismissListener(dismissListener);
    }

    public void setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        dialog.setOnKeyListener(onKeyListener);
    }

    public void setCanceledOnTouchOutside(boolean b) {
        dialog.setCanceledOnTouchOutside(b);
    }


    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("提示");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("确定");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.iosdialog_single_selector);
            btn_pos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.iosdialog_right_selector);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.iosdialog_left_selector);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            img_line.setVisibility(View.GONE);
            btn_neg.setVisibility(View.GONE);
            btn_pos.setGravity(Gravity.CENTER);
            btn_pos.setBackgroundResource(R.drawable.iosdialog_single_selector);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            img_line.setVisibility(View.GONE);
            btn_pos.setVisibility(View.GONE);
            btn_neg.setGravity(Gravity.CENTER);
            btn_neg.setBackgroundResource(R.drawable.iosdialog_single_selector);
        }
    }

    //确定回调
    public interface OnposClickLitener {

        void onPosEditClick(String s, String trim, String trim1);

    }

    public void show() {
        setLayout();
        dialog.show();
    }

    public void hide() {
        dialog.dismiss();
    }

    public void remove(View view) {
        view.setVisibility(View.GONE);
    }
}
