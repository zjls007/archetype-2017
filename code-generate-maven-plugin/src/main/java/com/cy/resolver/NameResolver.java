package com.cy.resolver;

/**
 * Created by zxj on 2017/5/2.
 */
public class NameResolver {

    public static String getJavaClassName(String name) {
        return getTFStr(name, true);
    }

    public static String getFieldName(String name) {
        return getTFStr(name, false);
    }

    public static String getGetMethodName(String name) {
        return String.format("get%s", getTFStr(name, true));
    }

    public static String getSetMethodName(String name) {
        return String.format("set%s", getTFStr(name, true));
    }

    public static String getTableAlias(String tableName) {
        StringBuilder sb = new StringBuilder();
        if (tableName != null) {
            tableName = "_" + tableName;
            for (int i = 0; i < tableName.length(); i++) {
                if (String.valueOf(tableName.charAt(i)).equals("_") && (i + 1) < tableName.length()) {
                    sb.append(tableName.charAt(i + 1));
                }
            }
        }
        return sb.toString();
    }

    private static String getTFStr(String str, boolean firstUpCase) {
        StringBuilder sb = new StringBuilder();
        if (str != null && !str.isEmpty()) {
            int length = str.length();
            boolean pre = length >= 2 && String.valueOf(str.charAt(1)).equals("_");
            if (pre) {
                if (length >= 3) {
                    str = str.substring(2);
                } else {
                    str = "";
                }
            }
            boolean afterSplit = false;
            for (int i = 0; i < str.length(); i++) {
                String c = String.valueOf(str.charAt(i));
                if (c.equals("_")) {
                    afterSplit = true;
                    continue;
                }
                if (firstUpCase && i == 0) {
                    sb.append(String.valueOf(str.charAt(i)).toUpperCase());
                    continue;
                }
                if (afterSplit) {
                    sb.append(String.valueOf(str.charAt(i)).toUpperCase());
                    afterSplit = false;
                } else {
                    sb.append(String.valueOf(str.charAt(i)));
                }
            }
        }
        return sb.toString();
    }
}
