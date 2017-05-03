package com.cy.resolver;

import com.cy.JavaModelDTO;
import com.cy.api.GenerateConfig;
import com.cy.model.Table;
import com.cy.util.FreeMarkerUtil;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zxj on 2017/5/2.
 */
public class JavaModelResolver extends AbstractResolver {

    private Table table;
    private String path;

    public void gen(Table table, String path) throws Exception {
        this.table = table;
        this.path = path;
        Configuration cfg = FreeMarkerUtil.getConfig();
        Template temp = cfg.getTemplate("javaModel.ftl");
        FileWriter out = new FileWriter(path);
        temp.process(getDataModel(), out);
        out.flush();
        out.close();
    }

    @Override
    protected void dataModel(Map<String, Object> root) {
        GenerateConfig generateConfig = GenerateConfig.getInstance();
        JavaModelDTO dto = new JavaModelDTO();
        dto.setPackageName(generateConfig.modelPackage);
        dto.setImportList(JavaTypeResolver.getImportList(table.getColumnList()));
        dto.setAuth("zxj");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dto.setDate(dateFormat.format(new Date()));
        dto.setModelName(table.getName());
        dto.setColumnList(table.getColumnList());

        root.put("dto", dto);
    }


}
