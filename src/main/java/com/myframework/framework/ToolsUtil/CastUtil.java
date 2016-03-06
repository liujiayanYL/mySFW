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
        return  castInt(object,0);
    }

    public static int castInt(Object object,int defaltValue){
        int value = defaltValue;
        if(object != null){
            String strValue = castString(object);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    value = Integer.parseInt(strValue);
                }
                catch (NumberFormatException e){
                    value = defaltValue;
                }
            }
        }
        return value;
    }

    public static double castDouble(Object object){
        return castDouble(object,0);
    }

    public static double castDouble(Object object,double defaltValue){
        double value = defaltValue;
        if(object != null){
            String strValue = castString(object);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    value = Double.parseDouble(strValue);
                }
                catch (NumberFormatException e){
                    value = defaltValue;
                }
            }
        }
        return value;
    }

    public static long castLong(Object object){
        return castLong(object,0);
    }

    public static long castLong(Object object,long defaltValue){
        long value = defaltValue;
        if(object != null){
            String strValue = castString(object);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    value = Long.parseLong(strValue);
                }
                catch (NumberFormatException e){
                    value = defaltValue;
                }
            }
        }
        return value;
    }

    public static boolean castBoolean(Object object){
        return castBoolean(object,false);
    }

    public static boolean castBoolean(Object object,boolean defaltValue){
        boolean value = defaltValue;
        if (object != null){
            value = Boolean.parseBoolean(castString(object));
        }
        return value;
    }
}
