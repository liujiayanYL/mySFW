package com.myframework.framework;

import com.myframework.framework.ToolsUtil.*;
import com.myframework.framework.bean.Data;
import com.myframework.framework.bean.Handler;
import com.myframework.framework.bean.Param;
import com.myframework.framework.bean.View;
import com.myframework.framework.helper.BeanHelper;
import com.myframework.framework.helper.ConfigHelper;
import com.myframework.framework.helper.ControllerHelper;
import com.myframework.framework.util.ReflectionUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by liujiayan-001 on 2016/3/6.
 */
@WebServlet(urlPatterns = "/*",loadOnStartup = 0)
public class DispacherServlet extends HttpServlet{

    public void init(ServletConfig servletConfig) throws ServletException{
        HelperLoader.init();
        ServletContext servletContext = servletConfig.getServletContext();
        ServletRegistration servletRegistration = servletContext.getServletRegistration("jsp");
        servletRegistration.addMapping(ConfigHelper.getAppJspPath()+"*");
        ServletRegistration assetRegistration = servletContext.getServletRegistration("default");
        assetRegistration.addMapping(ConfigHelper.getAssetPath()+"*");
    }
    public void service(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();
        Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);
        if(handler != null){
            Class<?> cls = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(cls);
            Map<String,Object> paramMap = new HashMap<String,Object>();
            Enumeration<String> paramNames = request.getParameterNames();
            while(paramNames.hasMoreElements()){
                String paramName =paramNames.nextElement();
                String paramValue = request.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }
            String body = CodecUtil.decodeUrl(StreamUtil.getString(request.getInputStream()));
            if(StringUtil.isNotEmpty(body)){
                String[] params = StringUtil.splitString(body,"&");
                if(ArrayUtil.isNotEmpty(params)){
                    for (String p : params) {
                        String[] arr = StringUtil.splitString(p,"=");
                        if(ArrayUtil.isNotEmpty(arr) && arr.length==2){
                            String paramName = arr[0];
                            String paramValue = arr[1];
                            paramMap.put(paramName,paramValue);
                        }
                    }
                }
            }

            Param param = new Param(paramMap);
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);

            if(result instanceof View){
                View view = (View)result;
                String path = view.getPath();
                if(path.startsWith("/")){
                    response.sendRedirect(request.getContextPath() + path);
                }
                else {
                    Map<String,Object> model = view.getModel();
                    for(Map.Entry<String,Object> entry : model.entrySet()){
                        request.setAttribute(entry.getKey(),entry.getValue());
                    }
                    request.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(request,response);
                }
            }
            else if(result instanceof Data){
                Data data = (Data) result;
                Object model = data.getModel();
                if(model != null){
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter writer = response.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }

        }

    }

}
