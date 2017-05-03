package com.cy.resolver;

import com.cy.JavaModelDTO;
import com.cy.api.GenerateConfig;
import com.cy.model.Table;
import com.cy.util.FreeMarkerUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

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
public class JavaModelResolver {

    public void gen(Table table, Properties p, String path) throws Exception {
        GenerateConfig generateConfig = GenerateConfig.getInstance(p);
        JavaModelDTO dto = new JavaModelDTO();
        dto.setPackageName(generateConfig.modelPackage);
        dto.setAuth("zxj");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dto.setDate(dateFormat.format(new Date()));
        dto.setModelName(table.getName());
        dto.setColumnList(table.getColumnList());

        Configuration cfg = FreeMarkerUtil.getConfig();
        Template temp = cfg.getTemplate("javaModel.ftl");

        Map<String, Object> root = new HashMap<String, Object>();
        root.put("dto", dto);
        FileWriter out = new FileWriter(path);
        temp.process(root, out);
        out.flush();
        out.close();
    }

}
