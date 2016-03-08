package com.myframework.framework.proxy;

import com.myframework.framework.bean.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by liujiayan on 2016/3/8.
 */
public abstract class AspectProxy implements Proxy{
    private static Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    public final Object doProxy(ProxyChain proxyChain) throws Throwable{
        Object result = null;
        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();
        begin();
        try{
            if(intecept(cls,method,params)){
                before(cls,method,params);
                result = proxyChain.doProxyChain();
                after(cls,method,params);
            }
            else {
                result = proxyChain.doProxyChain();
            }
        }
        catch (Exception e){
            logger.error("proxy failed",e);
            error(cls,method,params);
            throw e;
        }
        finally {
            end();
        }
        return result;
    }

    public void begin(){

    }
    public boolean intecept(Class<?> cls,Method method ,Object[] params)throws Throwable{
        return true;
    }
    public void before(Class<?> cls,Method method,Object[] params)throws Throwable{

    }
    public void after(Class<?> cls, Method method,Object[] params)throws Throwable{

    }
    public void error(Class<?> cls,Method method,Object[] params)throws Throwable{

    }
    public void end(){

    }
}
