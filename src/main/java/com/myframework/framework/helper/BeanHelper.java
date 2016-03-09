package com.myframework.framework.helper;

import com.myframework.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Set;


/**
 * Created by liujiayan-001 on 2016/3/5.
 */
public final class BeanHelper {
    private static final HashMap<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();

    static {
        Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
        for(Class cls : classSet){
            Object object = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls,object);
        }
    }

    public static HashMap<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class" + cls);
        }
        return (T)BEAN_MAP.get(cls);
    }

    public static void setBean(Class<?> cls,Object object){
            BEAN_MAP.put(cls,object);
    }
}
