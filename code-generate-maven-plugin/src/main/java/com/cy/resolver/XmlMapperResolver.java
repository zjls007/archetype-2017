package com.cy.resolver;

import com.cy.api.GenerateConfig;
import com.cy.dto.XmlMapperDTO;
import com.cy.model.Column;
import com.cy.model.Table;
import com.cy.util.FreeMarkerUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/5/2.
 */
public class XmlMapperResolver extends AbstractResolver {

    private Table table;
    private String path;

    public void gen(Table table, String path) throws Exception {
        this.table = table;
        this.path = path;
        Configuration cfg = FreeMarkerUtil.getConfig();
        Template temp = cfg.getTemplate("xmlMapper.ftl");
        FileWriter out = new FileWriter(path);
        temp.process(getDataModel(), out);
        out.flush();
        out.close();
        System.out.println(String.format("生成: %s", path));
    }

    @Override
    protected void dataModel(Map<String, Object> root) {
        GenerateConfig generateConfig = GenerateConfig.getInstance();
        XmlMapperDTO dto = new XmlMapperDTO();
        String modelName = NameResolver.getJavaClassName(table.getName());
        dto.setModelFullName(String.format("%s.%s", generateConfig.modelPackage, modelName));
        dto.setDaoFullName(String.format("%s.%s%s", generateConfig.daoPackage, modelName, "DAO"));
        dto.setTableName(table.getName());
        dto.setPrimaryKeyName(table.getPrimaryKeyName());
        List<String> columnList = new ArrayList<String>();
        for (Column column : table.getColumnList()) {
            columnList.add(column.getName());
        }
        dto.setColumnList(columnList);
        List<String> noIdColunmList = new ArrayList<String>(columnList);
        noIdColunmList.remove(table.getPrimaryKeyName());
        dto.setNoIdColunmList(noIdColunmList);
        root.put("dto", dto);
    }


}
