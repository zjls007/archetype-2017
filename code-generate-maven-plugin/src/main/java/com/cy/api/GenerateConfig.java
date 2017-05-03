package com.cy.api;

import java.util.Properties;

/**
 * Created by zxj on 2017/5/3.
 */
public class GenerateConfig {

    public String modelPackage;

    public Boolean genModel = true;

    public String tableName;

    public static GenerateConfig generateConfig;

    private Properties p;

    private GenerateConfig(Properties p) {
        this.p = p;
        genModel = !"false".equalsIgnoreCase((String) p.get("gen.model"));
        p.get("gen.dao");
        p.get("gen.mapperxml");
        tableName = (String) p.get("table.name");
        modelPackage = (String) p.get("model.package");
    }

    public static GenerateConfig getInstance(Properties p) {
        if (generateConfig == null) {
            generateConfig = new GenerateConfig(p);
        }
        return generateConfig;
    }

}
