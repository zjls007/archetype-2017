package com.cy.resolver;

import com.cy.api.GenerateConfig;
import com.cy.dto.BaseDTO;
import com.cy.dto.PropertyDTO;
import com.cy.model.Column;
import com.cy.model.Table;
import com.cy.util.FreeMarkerUtil;
import com.cy.util.SerializableUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zxj on 2017/6/26.
 */
public class ObjectResolver {

    public void gen(Properties config, Table table, String path, String ftlName, boolean serializable) throws Exception {
        Configuration cfg = FreeMarkerUtil.getConfig();
        if (serializable) {
            Template temp = cfg.getTemplate(ftlName);
            StringWriter out = new StringWriter();
            BaseDTO dataModel = new BaseDTO();
            init(config, dataModel, table);
            temp.process(dataModel, out);
            out.flush();
            out.close();

            temp = cfg.getTemplate(ftlName.replaceAll(".ftl", "-ser.ftl"));
            Long serialVersionUID = SerializableUtil.getSerialVersionUID(
                    NameResolver.getJavaClassName(table.getName()) + ".java",
                    out.toString(),
                    config.getProperty("model.package") + "." + NameResolver.getJavaClassName(table.getName()));
            dataModel.setSerialVersionUID(serialVersionUID + "L");
            FileWriter fileWriter = new FileWriter(path);
            temp.process(dataModel, fileWriter);
            fileWriter.flush();
            fileWriter.close();
            System.out.println(String.format("生成: %s", path));

        } else {
            Template temp = cfg.getTemplate(ftlName);
            FileWriter out = new FileWriter(path);
            BaseDTO dataModel = new BaseDTO();
            init(config, dataModel, table);
            temp.process(dataModel, out);
            out.flush();
            out.close();
            System.out.println(String.format("生成: %s", path));
        }
    }

    private void init(Properties config, BaseDTO dataModel, Table table) {
        dataModel.setConfig(config);
        String tableName = table.getName();
        dataModel.setTableName(tableName);
        dataModel.setBeanName(NameResolver.getJavaClassName(tableName));
        dataModel.setTableRemark(table.getRemark());
        List<PropertyDTO> propertyDTOList = new ArrayList<PropertyDTO>();
        String primaryKeyName = table.getPrimaryKeyName();
        dataModel.setPrimaryKeyColumnName(primaryKeyName);
        dataModel.setPrimaryKeyPropertyName(NameResolver.getFieldName(primaryKeyName));
        for (Column item : table.getColumnList()) {
            PropertyDTO dto = new PropertyDTO();
            String columnName = item.getName();
            dto.setColumnName(columnName);
            dto.setPropertyName(NameResolver.getFieldName(columnName));
            dto.setGetMethodName(NameResolver.getGetMethodName(columnName));
            dto.setSetMethodName(NameResolver.getSetMethodName(columnName));
            dto.setRemark(item.getRemark());
            String[] type = JavaTypeResolver1.getType(item.getType());
            dto.setTypeName(type[0]);
            dto.setTypeAllName(type[1]);
            if (!dto.getTypeAllName().equals("")) {
                dataModel.getImportTypeList().add(dto.getTypeAllName());
            }
            dto.setNotNull(item.getNotNull());
            if (primaryKeyName != null && primaryKeyName.equals(item.getName())) {
                dto.setPrimaryKey(true);
            } else {
                dto.setPrimaryKey(false);
            }
            propertyDTOList.add(dto);
        }
        dataModel.setPropertyList(propertyDTOList);
    }

    public static void main(String[] args) {
        System.out.println("a.ftl".replaceAll(".ftl", "-ser.ftl"));
    }
}
