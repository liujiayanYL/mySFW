package my.firstframework.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liujiayan on 2016/3/1.
 */
public class ClassUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);
    /*
    获取类加载器
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String ClassName,boolean isInitialized ){
        Class<?> cls;
        try {
            cls = Class.forName(ClassName,isInitialized,getClassLoader());
        }
        catch (ClassNotFoundException e){
            logger.error("Load class failure",e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 获取指定报名下的全部类
     */
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urlEnumeration = getClassLoader().getResources(packageName.replace(".","/"));
            while (urlEnumeration.hasMoreElements()){
                URL url = urlEnumeration.nextElement();
                if(url != null){
                    String protocal = url.getProtocol();

                }
            }
        }
        catch (Exception e){
            logger.error("get class set failure",e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet,String packagePath,String packageName){

    }
}
