package com.cy.resolver;

import com.cy.model.Column;

import java.util.*;

/**
 * Created by zxj on 2017/5/2.
 */
public class JavaTypeResolver {

    public static Map<String, String[]> map = new HashMap<String, String[]>() {
        {
            put("int", new String[] {"Integer", ""});
            put("bigint", new String[] {"Long", ""});
            put("tinyint", new String[] {"Byte", ""});
            put("smallint", new String[] {"Byte", ""});
            put("float", new String[] {"BigDecimal", "java.math.BigDecimal"});
            put("double", new String[] {"BigDecimal", "java.math.BigDecimal"});
            put("decimal", new String[] {"BigDecimal", "java.math.BigDecimal"});
            put("datetime", new String[] {"Date", "java.util.Date"});
            put("timestamp", new String[] {"Date", "java.util.Date"});
            put("date", new String[] {"Date", "java.util.Date"});
            put("varchar", new String[] {"String", ""});
            put("mediumtext", new String[] {"String", ""});
        }
    };

    public static void initConfig(Properties p) {
        String value = p.getProperty("typeConvert");
        if (value != null) {
            for (String item : value.split(";")) {
                if (item != null && !item.isEmpty()) {
                    String[] t = item.split(":", 3);
                    map.put(t[0], new String[] {t[1], t[2]});
                }
            }
        }

    }

    public static String[] getType(String type) {
        String[] val = map.get(type.toLowerCase());
        if (val == null) {
            throw new RuntimeException(String.format("JavaTypeResolver不支持解析【%s】类型", type));
        }
        return val;
    }

}
