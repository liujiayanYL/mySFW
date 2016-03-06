package com.myframework.framework.bean;

import com.myframework.framework.ToolsUtil.CastUtil;

import java.util.Map;

/**
 * Created by liujiayan-001 on 2016/3/6.
 */
public class Param {
    private Map<String,Object> paramMap;

    public Param(Map<String,Object> paramMap){
        this.paramMap = paramMap;
    }

    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
