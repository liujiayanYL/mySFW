package com.myframework.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by liujiayan-001 on 2016/3/5.
 */
public final class ReflectionUtil {
    private static Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> cls){
        Object newInstance;
        try {
            newInstance = cls.newInstance();
        }
        catch (Exception e){
            logger.error("new instance failed",e);
            throw new RuntimeException(e);
        }
        return newInstance;
    }

    public static Object invokeMethod(Object object,Method method,Object ... args){
        Object result;
        try{
            method.setAccessible(true);
            result = method.invoke(object,args);
        }
        catch (Exception e){
            logger.error("invoke method failed",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void setField(Object object,Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(object,value);
        }
        catch (Exception e){
            logger.error("field set failed",e);
            throw new RuntimeException(e);
        }
    }
}
