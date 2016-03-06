package com.myframework.framework;

import com.myframework.framework.helper.BeanHelper;
import com.myframework.framework.helper.ClassHelper;
import com.myframework.framework.helper.ControllerHelper;
import com.myframework.framework.helper.IocHelper;
import com.myframework.framework.util.ClassUtil;

/**
 * Created by liujiayan-001 on 2016/3/6.
 */
public final class HelperLoader {
    public static void init(){
        Class<?>[] classes = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for(Class<?> cls : classes){
            ClassUtil.loadClass(cls.getName());
        }
    }
}
