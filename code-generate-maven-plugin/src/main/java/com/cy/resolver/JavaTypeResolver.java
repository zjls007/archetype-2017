package com.cy.resolver;

import com.cy.model.Column;

import java.util.*;

/**
 * Created by zxj on 2017/5/2.
 */
public class JavaTypeResolver {

    public static Map<String, String[]> map = new HashMap<String, String[]>() {
        {
            put("int", new String[] {"Integer"});
            put("bigint", new String[] {"Long"});
            put("tinyint", new String[] {"Byte"});
            put("smallint", new String[] {"Byte"});
            put("datetime", new String[] {"Date", "import java.util.Date;"});
            put("date", new String[] {"Date", "import java.util.Date;"});
            put("decimal", new String[] {"BigDecimal", "import java.math.BigDecimal;"});
            put("varchar", new String[] {"String"});
        }
    };

    public static String getType(String type) {
        String[] val = map.get(type.toLowerCase());
        return val[0];
    }

    public static List<String> getImportList(List<Column> list) {
        Set<String> set = new LinkedHashSet<String>();
        for (Column column : list) {
            String[] val = map.get(column.getType().toLowerCase());
            if (val.length == 2) {
                set.add(val[1]);
            }
        }
        return new ArrayList<String>(set);
    }

}
