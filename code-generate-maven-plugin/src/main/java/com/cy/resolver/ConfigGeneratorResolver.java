package com.cy.resolver;

import com.cy.util.FreeMarkerUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.FileWriter;
import java.util.*;

public class ConfigGeneratorResolver {

    public static void genernateFile(Properties op, String filePath, String packageName, String fileName){
        try {
            Configuration cfg = FreeMarkerUtil.getConfig();
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("map", genMap(op.keySet()));
            root.put("package", packageName);
            root.put("fileName", fileName);
            Template temp = cfg.getTemplate("config.ftl");

            FileWriter out = new FileWriter(filePath);
            temp.process(root, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Map<String, String> genMap(Set<Object> set) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (Object key : set) {
            map.put(genKey((String) key), (String) key);
        }
        return map;
    }

    private static String genKey(String key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            int iv = (int) c;
            // 46为.
            if (iv == 46) {
                sb.append("_");
                // 字母a-z
            } else if (iv >= 97 && iv <= 122) {
                sb.append(Character.toUpperCase(c));
            } else if (iv >= 65 && iv <= 90) {
                sb.append("_").append(c);
            }
        }
        return sb.toString();
    }

}