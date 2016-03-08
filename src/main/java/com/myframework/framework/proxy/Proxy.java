package com.myframework.framework.proxy;

/**
 * Created by liujiayan on 2016/3/8.
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
