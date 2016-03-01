package my.firstframework.framework.ToolsUtil;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by liujiayan on 2016/3/1.
 */
public final class StringUtil {
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String s){
        if(s != null){
            s.trim();
        }
        return StringUtils.isEmpty(s);
    }

    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmpty(String s){
        return !isEmpty(s);
    }
}
