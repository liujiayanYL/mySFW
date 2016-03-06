package com.myframework.framework;

import com.myframework.framework.helper.ConfigHelper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

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
        ServletRegistration assetRegistration = servletContext.getServletRegistration("defalt");
        assetRegistration.addMapping(ConfigHelper.getAssetPath()+"*");
    }


}
