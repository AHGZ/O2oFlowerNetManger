package com.huafan.huafano2omanger.anntoation;

import android.support.annotation.IntDef;

import com.huafan.huafano2omanger.callback.ISelect;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;

/**
 * author: zhangpeisen
 * created on: 2017/10/26 下午5:26
 * description:实现Recycleview多选注解类
 */
@Target(PARAMETER)
@IntDef(flag = true, value = {
        ISelect.SINGLE_MODE,
        ISelect.MULTIPLE_MODE
})
public @interface SelectMode {
}
