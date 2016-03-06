package com.myframework.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liujiayan-001 on 2016/3/6.
 */
public class View {
    private String path;

    private Map<String,Object> model;

    public View(String path){
        this.path = path;
        model = new HashMap<String,Object>();
    }

    public View addModel(String key,Object value){
        model.put(key,value);
        return this;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public String getPath() {
        return path;
    }
}
