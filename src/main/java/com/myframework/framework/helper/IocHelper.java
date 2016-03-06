package com.myframework.framework.helper;

import com.myframework.framework.ToolsUtil.ArrayUtil;
import com.myframework.framework.ToolsUtil.CollectionUtil;
import com.myframework.framework.annotation.Inject;
import com.myframework.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by liujiayan-001 on 2016/3/5.
 */
public final class IocHelper {
    static {
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            for(Map.Entry<Class<?>,Object> entry : beanMap.entrySet()){
                Class<?> beanClass = entry.getKey();
                Object beanObject = entry.getValue();
                Field[] beanField = beanClass.getFields();
                if(ArrayUtil.isNotEmpty(beanField)){
                    for(Field field : beanField){
                        if(field.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = field.getType();
                            Object instance = ReflectionUtil.newInstance(beanFieldClass);
                            if(instance != null){
                                ReflectionUtil.setField(beanObject,field,instance);
                            }
                        }
                    }
                }
            }
        }
    }
}
