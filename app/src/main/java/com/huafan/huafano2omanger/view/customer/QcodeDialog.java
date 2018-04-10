package com.huafan.huafano2omanger.view.customer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.huafan.huafano2omanger.R;

/**
 * Created by heguozhong on 2018/1/30/030.
 * 打烊dialog
 */

public class QcodeDialog extends Dialog{
    public QcodeDialog(Context context) {
        super(context);
        this.show();
    }

    public QcodeDialog(Context context, int theme) {
        super(context, theme);
        this.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qcode_layout);
    }

}
