package com.huafan.huafano2omanger.anntoation;

import android.support.annotation.IntDef;

import com.huafan.huafano2omanger.callback.AdapterLoader;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;

/**
 * author: zhangpeisen
 * created on: 2017/10/26 下午5:43
 * description:
 */
@IntDef(flag = true, value = {
        AdapterLoader.STATE_LOADING,
        AdapterLoader.STATE_LASTED,
        AdapterLoader.STATE_ERROR
})
@Target(PARAMETER)
@Retention(RetentionPolicy.SOURCE)
public @interface LoadState {
}