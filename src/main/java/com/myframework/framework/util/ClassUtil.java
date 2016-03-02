package com.myframework.framework.util;



import com.myframework.framework.ToolsUtil.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
                    if("file".equals(protocal)){
                        String packagePath = url.getPath().replaceAll("%20","");
                        addClass(classSet,packagePath,packageName);
                    }
                    else if("jar".equals(protocal)){
                        JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
                        if(jarURLConnection!= null){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null){
                                Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                                while (jarEntryEnumeration.hasMoreElements()){
                                    JarEntry jarEntry = jarEntryEnumeration.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")){
                                        String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                        doAddClassSet(classSet,className);
                                    }
                                }
                            }
                        }
                    }
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
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return (pathname.isFile() && pathname.getName().endsWith(".class"))||pathname.isDirectory();
            }
        });
        for(File file : files){
            String filename = "";
            filename = file.getName();
            if(file.isFile()){
                String className = filename.substring(0,filename.lastIndexOf("."));
                if(StringUtil.isEmpty(packageName)){
                    className = packageName + "." +className;
                }
                doAddClassSet(classSet,className);
            }
            else{

            }
        }
    }

    private static void doAddClassSet(Set<Class<?>> classSet,String className){
        Class<?> cls = loadClass(className,false);
        classSet.add(cls);

    }
}
