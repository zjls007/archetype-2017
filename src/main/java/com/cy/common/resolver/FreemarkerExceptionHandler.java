package com.cy.common.resolver;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by zxj on 2018/1/5.
 */
public class FreemarkerExceptionHandler implements TemplateExceptionHandler {

    Logger LOG = LoggerFactory.getLogger(FreemarkerExceptionHandler.class);

    @Override
    public void handleTemplateException(TemplateException e, Environment environment, Writer writer) throws TemplateException {
        LOG.error("[Freemarker页面报错：]", e);
        try {
            writer.write("【取值异常，请联系开发人员!】");
        } catch (IOException e1) {
            throw new TemplateException("Failed to print error message. Cause: " + e1, environment);
        }
    }

}
