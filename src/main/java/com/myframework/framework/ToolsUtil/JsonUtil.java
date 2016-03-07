package com.myframework.framework.ToolsUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liujiayan on 2016/3/7.
 */
public final class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String toJson(T objetT){
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(objetT);
        }
        catch (Exception e){
            logger.error("object transfer to json failed" , e);
            throw new RuntimeException(e);
        }
        return json;
    }

    public static <T> T fromJson(String json,Class<T> tClass){
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json,tClass);
        }
        catch (Exception e){
            logger.error("json transfer to pojo failed",e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
