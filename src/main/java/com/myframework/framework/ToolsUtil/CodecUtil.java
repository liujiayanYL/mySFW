package com.myframework.framework.ToolsUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by liujiayan on 2016/3/7.
 */
public final class CodecUtil {
    private static Logger logger = LoggerFactory.getLogger(CodecUtil.class);
    public static String encodeUrl(String src){
        String target;
        try{
            target = URLEncoder.encode(src,"UTF-8");
        }
        catch (Exception e){
            logger.error("url encode failed",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    public static String decodeUrl(String src){
        String target;
        try{
            target = URLDecoder.decode(src,"UTF-8");
        }
        catch (Exception e){
            logger.error("url decode failed",e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
