package com.myframework.framework.ToolsUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyPermission;

/**
 * Created by liujiayan on 2016/3/2.
 */
public final class PropsUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String propsPath){
        Properties properties = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propsPath);
            if (is == null) {
                throw new FileNotFoundException(propsPath + "file is not found");
            }
            properties = new Properties();
            properties.load(is);
        }
        catch(IOException e){
            logger.error("load properties file fail",e);
        }
        finally {
            if(is != null){
                try{
                    is.close();
                }
                catch (IOException e){
                    logger.error("stream close fail",e);
                }
            }
        }

        return properties;
    }

    public static String getString(Properties properties,String key){
        return getString(properties,key,"");
    }

    public static String getString(Properties properties,String key,String defaltValue){
        String value = defaltValue;
        if(properties.containsKey(key)){
            value = properties.getProperty(key);
        }
        return value;
    }


}
