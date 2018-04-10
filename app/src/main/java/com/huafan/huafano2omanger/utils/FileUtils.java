package com.huafan.huafano2omanger.utils;

import android.content.Context;

import java.io.File;

/**
 * @描述:文件存储工具类
 * @创建人：zhangpeisen
 * @创建时间：2017/12/26 上午11:15
 * @修改人：zhangpeisen
 * @修改时间：2017/12/26 上午11:15
 * @修改备注：
 * @throws
 */
public class FileUtils {

    public static File getExternalFilesDir(Context context, String type) {
        File dirs = context.getExternalFilesDir(type);
        if (dirs != null)
            return dirs;
        return context.getFilesDir();
    }
}
