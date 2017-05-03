package com.cy.util;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * Created by zxj on 2017/5/2.
 */
public class FreeMarkerUtil {

    public static Configuration getConfig() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
//        cfg.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "ftl");
        cfg.setClassLoaderForTemplateLoading(FreeMarkerUtil.class.getClassLoader(), "ftl");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        return cfg;
    }


}
