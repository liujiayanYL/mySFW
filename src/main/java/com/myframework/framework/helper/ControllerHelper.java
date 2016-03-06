package com.myframework.framework.helper;

import com.myframework.framework.ToolsUtil.ArrayUtil;
import com.myframework.framework.ToolsUtil.CollectionUtil;
import com.myframework.framework.annotation.Action;
import com.myframework.framework.bean.Handler;
import com.myframework.framework.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by liujiayan-001 on 2016/3/6.
 */
public final class ControllerHelper {
    private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request,Handler>();

    static {
        Set<Class<?>> controllerSet = ClassHelper.getControllerClassSet();
        if(CollectionUtil.isNotEmpty(controllerSet)){
            for(Class<?> cls : controllerSet){
                Method[] methods = cls.getDeclaredMethods();
                if(ArrayUtil.isNotEmpty(methods)){
                    for(Method method : methods){
                        if(method.isAnnotationPresent(Action.class)){
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] stringArr = mapping.split(":");
                                if(ArrayUtil.isNotEmpty(stringArr) && stringArr.length==2){
                                    String requestMethod = stringArr[0];
                                    String requestPath = stringArr[1];
                                    Request request = new Request(requestMethod,requestPath);
                                    Handler handler = new Handler(cls,method);
                                    ACTION_MAP.put(request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod,String requestPath){
        Request request = new Request(requestMethod,requestPath);
        return ACTION_MAP.get(request);
    }
}
