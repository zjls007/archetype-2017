package com.cy.util;

import java.util.Properties;

/**
 * Created by zxj on 2017/5/3.
 */
public class GenerateConfig {

    public String auth;

    public String modelPackage;

    public String enumpackage;

    public String daoPackage;

    public Boolean genModel = true;

    public Boolean genDao = true;

    public Boolean genMapper = true;

    public Boolean genTest = true;

    public String tableName;

    public String modelTargetProject;

    public String daoTargetProject;

    public String mapperTargetProject;

    public String mapperPackage;

    public String testTargetProject;

    public String testPackage;

    public static GenerateConfig generateConfig;

    private Properties p;

    private GenerateConfig(Properties p) {
        this.p = p;
        genModel = !"false".equalsIgnoreCase((String) p.get("gen.model"));
        genDao = !"false".equalsIgnoreCase((String) p.get("gen.dao"));
        genMapper = !"false".equalsIgnoreCase((String) p.get("gen.mapper"));
        genTest = !"false".equalsIgnoreCase((String) p.get("gen.test"));
        tableName = (String) p.get("table.name");
        modelPackage = (String) p.get("model.package");
        enumpackage = (String) p.get("model.enum.package");
        daoPackage = (String) p.get("dao.package");
        modelTargetProject = (String) p.get("model.targetProject");
        daoTargetProject = (String) p.get("dao.targetProject");
        mapperTargetProject = (String) p.get("mapper.targetProject");
        mapperPackage = (String) p.get("mapper.package");
        testTargetProject = (String) p.get("test.targetProject");
        testPackage = (String) p.get("test.package");

        auth = (String) p.get("auth");
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
