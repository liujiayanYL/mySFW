package com.myframework.framework.helper;

import com.myframework.framework.ConfigConstant;
import com.myframework.framework.ToolsUtil.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Properties;

/**
 * Created by liujiayan on 2016/3/2.
 */
public final class ConfigHelper {
    private final static Logger logger = LoggerFactory.getLogger(ConfigHelper.class);
    private static final Properties CONFIG_PROPERTIES = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPERTIES,ConfigConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPERTIES,ConfigConstant.JDBC_URL);
    }

    public static String getJdbcUser(){return PropsUtil.getString(CONFIG_PROPERTIES,ConfigConstant.JDBC_USERNAME);}

    public static String getJdbcPassword(){return PropsUtil.getString(CONFIG_PROPERTIES,ConfigConstant.JDBC_PASSWORD);}

    public static String getAppBasePackage(){return PropsUtil.getString(CONFIG_PROPERTIES,ConfigConstant.APP_BASE_PACKAGE);}

    public static String getAppJspPath(){return PropsUtil.getString(CONFIG_PROPERTIES,ConfigConstant.APP_JSP_PATH);}

    public static String getAssetPath(){return PropsUtil.getString(CONFIG_PROPERTIES,ConfigConstant.APP_ASSET_PATH,"/asset/");}
}
