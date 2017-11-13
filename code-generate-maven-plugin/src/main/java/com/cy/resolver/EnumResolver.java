package com.cy.resolver;

import com.cy.model.Column;
import com.cy.model.Table;
import com.cy.util.FreeMarkerUtil;
import com.cy.util.PathUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zxj on 2017/11/10.
 */
public class EnumResolver {

    private final String gen = "model.enum";
    private final String regex = "model.enum.regex";
    private final String content = "model.enum.content";
    private final String split = "model.enum.split";

    private String regexVal;
    private String contentVal;
    private String splitVal;


    public void genEnum(Properties config, Column item, Table table) {
        if (!"true".equals(config.getProperty(gen))) {
            return;
        }
        regexVal = config.getProperty(regex);
        contentVal = config.getProperty(content);
        splitVal = config.getProperty(split);
        if (isEmpty(regex) || isEmpty(contentVal) || isEmpty(splitVal)) {
            return;
        }
        doGen(item, table, config);
    }

    private boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    private void doGen(Column item, Table table, Properties config) {
        String remark = item.getRemark();
        String remarkBack = remark;
        if (isEmpty(remark)) {
            return;
        }
        String[] keyVal = getKeyVal();
        // 把中文的大括号，中括号，小括号转为英文符号
        remark = remark.replaceAll("（", "(").replaceAll("）", ")").replaceAll("【", "[").replaceAll("】", "]").replaceAll("\\{", "{").replaceAll("}", "}");
        remark = remark.replaceAll("，", ",").replaceAll("、", ",");
        remark = remark.replaceAll(" ", keyVal[1]);
        String group = null;
        String replaceGroup = null;
        for (String reg : regexVal.split("\\|")) {
            if (isEmpty(reg)) {
                continue;
            }
            Matcher matcher = Pattern.compile(reg).matcher(remark);
            if (matcher.find()) {
                group = matcher.group(2);
                replaceGroup = matcher.group(1);
                break;
            }
        }
        if (isEmpty(group)) {
            return;
        }
        try {
            String name = String.format("%s%s", NameResolver.getJavaClassName(table.getName()), NameResolver.getJavaClassName(item.getName()));
            Configuration cfg = FreeMarkerUtil.getConfig();
            Template temp = cfg.getTemplate("javaEnum.ftl");
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("name", name);

            boolean isNumber = !"String".equals(JavaTypeResolver.getType(item.getType())[0]);
            List<String[]> list = new ArrayList<String[]>();
            list.add(new String[]{JavaTypeResolver.getType(item.getType())[0], keyVal[0]});
            list.add(new String[]{"String", keyVal[2]});


            map.put("list", list);
            List<String> items = getItems(group, keyVal, isNumber);
            if (items.isEmpty()) {
                return;
            }
            map.put("items", items);
            map.put("table", table);
            map.put("column", item);
            map.put("package", config.getProperty("model.enum.package"));
            map.put("date", new Date());
            map.put("auth", config.getProperty("auth"));
            FileWriter out = new FileWriter(PathUtil.getEnumPath(name));
            temp.process(map, out);
            out.flush();
            out.close();
            item.setRemark(remark.replace(replaceGroup, String.format(" 参考:{@link %s.%s}", config.getProperty("model.enum.package"), name)));
            System.out.println(String.format("生成枚举类: %s", name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getItems(String all, String[] keyVal, boolean isNumber) {
        List<String> items = new ArrayList<String>();
        String[] strings = all.split(splitVal);
        // 分项如果不大于两项不当枚举处理
        if (strings.length > 1) {
            for (String enumItem : strings) {
                if (isEmpty(enumItem)) {
                    continue;
                }
                String[] split = enumItem.split(keyVal[1]);
                items.add(String.format("%s(%s%s%s, \"%s\"),", NameResolver.getEnumName(split[0]), isNumber ? "" : "\"", split[0], isNumber ? "" : "\"", split.length == 2 ? split[1] : split[0]));
            }
        }
        return items;
    }

    private String[] getKeyVal() {
        Matcher matcher = Pattern.compile("\\{(.*?)\\}(.)\\{(.*?)\\}").matcher(contentVal);
        if (matcher.find()) {
            return new String[] {matcher.group(1), matcher.group(2), matcher.group(3)};
        }
        return null;
    }

}
