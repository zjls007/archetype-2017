package com.cy.resolver;

import com.cy.api.GenerateConfig;
import com.cy.dto.JavaDaoDTO;
import com.cy.dto.JavaModelDTO;
import com.cy.model.Table;
import com.cy.util.FreeMarkerUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by zxj on 2017/5/2.
 */
public class JavaDaoResolver extends AbstractResolver {

    private Table table;
    private String path;

    public void gen(Table table, String path) throws Exception {
        this.table = table;
        this.path = path;
        Configuration cfg = FreeMarkerUtil.getConfig();
        Template temp = cfg.getTemplate("javaDao.ftl");
        FileWriter out = new FileWriter(path);
        temp.process(getDataModel(), out);
        out.flush();
        out.close();
    }

    @Override
    protected void dataModel(Map<String, Object> root) {
        GenerateConfig generateConfig = GenerateConfig.getInstance();
        JavaDaoDTO dto = new JavaDaoDTO();
        dto.setModelPackage(generateConfig.modelPackage);
        dto.setDaoPackage(generateConfig.daoPackage);
        dto.setAuth("zxj");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dto.setDate(dateFormat.format(new Date()));
        dto.setModelName(NameResolver.getJavaClassName(table.getName()));

        root.put("dto", dto);
    }


}
