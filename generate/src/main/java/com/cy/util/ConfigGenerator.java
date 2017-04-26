package com.cy.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.io.*;
import java.util.*;

public class ConfigGenerator {

    private static Logger LOG = LoggerFactory.getLogger(ConfigGenerator.class);

    public static void main(String[] args) throws Exception {
        ConfigGenerator c = new ConfigGenerator();
        c.genernateFile();
    }

    /**
     * 配置文件名称
     */
    private String resource = "config.properties";

    /**
     * 文件路径
     */
    private String filePath = "src/main/java/com/cy/common/";

    private String packageName = "com.cy.common";

    public ConfigGenerator() {
    }

    public ConfigGenerator(String resource, String filePath, String packageName) {
        if (resource != null) {
            this.resource = resource;
        }
        if (filePath != null) {
            this.filePath = filePath;
        }
        if (packageName != null) {
            this.packageName = packageName;
        }
    }

    public void genernateFile(){
        String desc = "根据配置文件生成代码";
        LOG.info("{}", desc);
        try {
            // 读取出有顺序的keys
            Properties op = new OrderedProperties();
            op.load(new FileReader(getClass().getClassLoader().getResource(resource).getFile()));

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            cfg.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "ftl");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);

            Map<String, Object> root = new HashMap<String, Object>();
            root.put("map", genMap(op.keySet()));
            root.put("package", packageName);
            Template temp = cfg.getTemplate("config.ftl");

            FileWriter out = new FileWriter(getFilePath());
            temp.process(root, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            LOG.error("{}出现异常", desc, e);
        }
    }

    private String getFilePath() throws IOException {
        String path = getClass().getClassLoader().getResource("").getFile();
        path = path.replaceAll("target/", "");
        path = path.replaceAll("classes/", "");
        StringBuilder file = new StringBuilder(path);
        file.append(filePath);
        File f = new File(file.toString());
        if (!f.exists()) {
            f.mkdirs();
        }
        file.append("Config.java");
        return file.toString();
    }

    private Map<String, String> genMap(Set<Object> set) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (Object key : set) {
            map.put(genKey((String) key), (String) key);
        }
        return map;
    }

    private String genKey(String key) {
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