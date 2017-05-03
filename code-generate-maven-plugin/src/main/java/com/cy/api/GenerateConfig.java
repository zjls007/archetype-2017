package com.cy.api;

import java.util.Properties;

/**
 * Created by zxj on 2017/5/3.
 */
public class GenerateConfig {

    public String modelPackage;

    public String daoPackage;

    public Boolean genModel = true;

    public Boolean genDao = true;

    public String tableName;

    public String modelTargetProject;

    public String daoTargetProject;

    public static GenerateConfig generateConfig;

    private Properties p;

    private GenerateConfig(Properties p) {
        this.p = p;
        genModel = !"false".equalsIgnoreCase((String) p.get("gen.model"));
        genDao = !"false".equalsIgnoreCase((String) p.get("gen.dao"));
        p.get("gen.dao");
        p.get("gen.mapperxml");
        tableName = (String) p.get("table.name");
        modelPackage = (String) p.get("model.package");
        daoPackage = (String) p.get("dao.package");
        modelTargetProject = (String) p.get("model.targetProject");
        daoTargetProject = (String) p.get("dao.targetProject");
    }

    public static void init(Properties p) {
        if (generateConfig == null) {
            generateConfig = new GenerateConfig(p);
        }
    }

    public static GenerateConfig getInstance() {
        return generateConfig;
    }

}
