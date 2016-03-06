package com.myframework.framework.ToolsUtil;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by liujiayan-001 on 2016/3/5.
 */
public final class ArrayUtil {

    public static boolean isEmpty(Object[] arr){
        return ArrayUtils.isEmpty(arr);
    }

    public static boolean isNotEmpty(Object[] arr){
        return !ArrayUtils.isEmpty(arr);
    }
}
