package com.myframework.framework.ToolsUtil;

import java.util.Objects;

/**
 * Created by liujiayan on 2016/3/3.
 */
public final class CastUtil {
    public static String castString(Object object){
        return castString(object,"");
    }

    public static String castString(Object object,String defaltValue){
        return object != null ? String.valueOf(object):defaltValue;
    }

    public static int castInt(Object object){

    }
}
