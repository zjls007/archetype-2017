package com.cy.resolver;

import com.cy.dto.BaseDTO;
import com.cy.dto.PropertyDTO;
import com.cy.model.Column;
import com.cy.model.Table;
import com.cy.util.ClassUtil;
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

    static Class<?> modelClass;

    private String getSource(Configuration cfg, Properties config, Table table, String ftlName) throws Exception {
        Template temp = cfg.getTemplate(ftlName);
        StringWriter out = new StringWriter();
        BaseDTO dataModel = new BaseDTO();
        init(config, dataModel, table, null);
        temp.process(dataModel, out);
        out.flush();
        out.close();
        return out.toString();
    }

    public void gen(Properties config, Table table, String path, String ftlName, boolean serializable) throws Exception {
        Configuration cfg = FreeMarkerUtil.getConfig();

        String javaFileName = NameResolver.getJavaClassName(table.getName()) + ".java";
        String javafullName = config.getProperty("model.package") + "." + NameResolver.getJavaClassName(table.getName());
        BaseDTO dataModel = new BaseDTO();
        if (serializable) {
            String modelSource = getSource(cfg, config, table, ftlName);
            modelClass = SerializableUtil.getCalssBySource(javaFileName, modelSource, javafullName);

            ftlName = ftlName.replaceAll(".ftl", "-ser.ftl");
            Long serialVersionUID = SerializableUtil.getSerialVersionUID(modelClass);
            dataModel.setSerialVersionUID(serialVersionUID + "L");
        }
        init(config, dataModel, table, modelClass);
        Template temp = cfg.getTemplate(ftlName);
        FileWriter out = new FileWriter(path);
        temp.process(dataModel, out);
        out.flush();
        out.close();
        System.out.println(String.format("生成: %s", path));
    }

    private void init(Properties config, BaseDTO dataModel, Table table, Class<?> clazz) {
        JavaTypeResolver.initConfig(config);
        dataModel.setConfig(config);

        if (clazz != null) {
            dataModel.setSetMethodInvokeStrList(ClassUtil.getSetMethodInvoke(clazz, "entity"));
        }

        String tableName = table.getName();
        dataModel.setTableName(tableName);
        dataModel.setTableAlias(NameResolver.getTableAlias(tableName));
        dataModel.setBeanName(NameResolver.getJavaClassName(tableName));
        dataModel.setTableRemark(table.getRemark());
        String primaryKeyName = table.getPrimaryKeyName();
        dataModel.setPrimaryKeyColumnName(primaryKeyName);
        dataModel.setPrimaryKeyPropertyName(NameResolver.getFieldName(primaryKeyName));

        List<PropertyDTO> propertyDTOList = new ArrayList<PropertyDTO>();
        for (Column item : table.getColumnList()) {
            if (item.getName().equals(primaryKeyName)) {
                dataModel.setPrimaryKeyType(JavaTypeResolver.getType(item.getType())[0]);
            }
            PropertyDTO dto = new PropertyDTO();
            String columnName = item.getName();
            dto.setColumnName(columnName);
            dto.setPropertyName(NameResolver.getFieldName(columnName));
            dto.setGetMethodName(NameResolver.getGetMethodName(columnName));
            dto.setSetMethodName(NameResolver.getSetMethodName(columnName));
            dto.setRemark(item.getRemark());
            String[] type = JavaTypeResolver.getType(item.getType());
            dto.setTypeName(type[0]);
            dto.setTypeAllName(type[1]);
            if (!dto.getTypeAllName().equals("")) {
                dataModel.getImportTypeList().add(dto.getTypeAllName());
            }
            dto.setNotNull(item.getNotNull());
            if (primaryKeyName != null && primaryKeyName.equals(item.getName())) {
                dto.setPrimaryKey(true);
            }
            propertyDTOList.add(dto);
        }
        dataModel.setPropertyList(propertyDTOList);

        // 唯一索引
        List<List<PropertyDTO>> list = new ArrayList<List<PropertyDTO>>();
        for (Map.Entry<String, List<String>> map : table.getUniKeyMap().entrySet()) {
            List<PropertyDTO> uniKey = new ArrayList<PropertyDTO>();
            for (String str : map.getValue()) {
                for (PropertyDTO dto : propertyDTOList) {
                    if (dto.getColumnName().equals(str)) {
                        uniKey.add(dto);
                    }
                }
            }
            list.add(uniKey);
        }
        dataModel.setUniKeyList(list);

        // 非唯一索引
        list = new ArrayList<List<PropertyDTO>>();
        for (Map.Entry<String, List<String>> map : table.getIndexKeyMap().entrySet()) {
            List<PropertyDTO> uniKey = new ArrayList<PropertyDTO>();
            for (String str : map.getValue()) {
                for (PropertyDTO dto : propertyDTOList) {
                    if (dto.getColumnName().equals(str)) {
                        uniKey.add(dto);
                    }
                }
            }
            list.add(uniKey);
        }
        dataModel.setIndexKeyList(list);
    }

    public static void main(String[] args) {
        System.out.println("a.ftl".replaceAll(".ftl", "-ser.ftl"));
    }
}
