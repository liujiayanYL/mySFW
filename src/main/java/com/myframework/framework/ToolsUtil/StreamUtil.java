package com.myframework.framework.ToolsUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by liujiayan on 2016/3/7.
 */
public final class StreamUtil {
    private static Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    public static String getString(InputStream inputStream){
        StringBuilder stringBuilder = new StringBuilder();
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine())!= null) {
                stringBuilder.append(line);
            }
        }
        catch (Exception e){
            logger.error("get string from stream failed",e);
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}
