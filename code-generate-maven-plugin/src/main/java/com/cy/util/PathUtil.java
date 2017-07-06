package com.cy.util;

import com.cy.api.GenerateConfig;
import com.cy.resolver.NameResolver;

import java.io.File;

/**
 * Created by zxj on 2017/5/3.
 */
public class PathUtil {

    public static String getModelPath(String basePath, String tableName) {
        GenerateConfig generateConfig = GenerateConfig.getInstance();
        StringBuilder path = new StringBuilder();
        path.append(basePath);
        if (generateConfig.modelTargetProject != null && !generateConfig.modelTargetProject.isEmpty()) {
            backDir(path, generateConfig.modelTargetProject);
        }
        if (generateConfig.modelPackage != null && !generateConfig.modelPackage.isEmpty()) {
            backDir(path, generateConfig.modelPackage.replaceAll("\\.", "/"));
        }
        path.append(File.separator);
        path.append(NameResolver.getJavaClassName(tableName));
        path.append(".java");
        return path.toString();
    }

    public static String getDaoPath(String basePath, String tableName) {
        GenerateConfig generateConfig = GenerateConfig.getInstance();
        StringBuilder path = new StringBuilder();
        path.append(basePath);
        if (generateConfig.daoTargetProject != null && !generateConfig.daoTargetProject.isEmpty()) {
            backDir(path, generateConfig.daoTargetProject);
        }
        if (generateConfig.daoPackage != null && !generateConfig.daoPackage.isEmpty()) {
            backDir(path, generateConfig.daoPackage.replaceAll("\\.", "/"));
        }
        path.append(File.separator);
        path.append(NameResolver.getJavaClassName(tableName));
        path.append("DAO.java");
        return path.toString();
    }

    public static String getMapperPath(String basePath, String tableName) {
        GenerateConfig generateConfig = GenerateConfig.getInstance();
        StringBuilder path = new StringBuilder();
        path.append(basePath);
        if (generateConfig.mapperTargetProject != null && !generateConfig.mapperTargetProject.isEmpty()) {
            backDir(path, generateConfig.mapperTargetProject);
        }
        if (generateConfig.mapperPackage != null && !generateConfig.mapperPackage.isEmpty()) {
            backDir(path, generateConfig.mapperPackage.replaceAll("\\.", "/"));
        }
        path.append(File.separator);
        path.append(tableName);
        path.append(".xml");
        return path.toString();
    }

    public static String getTestPath(String basePath, String tableName) {
        GenerateConfig generateConfig = GenerateConfig.getInstance();
        StringBuilder path = new StringBuilder();
        path.append(basePath);
        if (generateConfig.testTargetProject != null && !generateConfig.testTargetProject.isEmpty()) {
            backDir(path, generateConfig.testTargetProject);
        }
        if (generateConfig.testPackage != null && !generateConfig.testPackage.isEmpty()) {
            backDir(path, generateConfig.testPackage.replaceAll("\\.", "/"));
        }
        path.append(File.separator);
        path.append(NameResolver.getJavaClassName(tableName));
        path.append("ServiceTest.java");
        return path.toString();
    }

    /**
     * 目录..的时候退掉一个目录
     * @param path
     * @param add
     */
    public static void backDir(StringBuilder path, String add) {
        String temp = path.toString();
        if (temp.endsWith("\\")) {
            temp = temp.substring(0, temp.length() - 1);
        }
        if (add.startsWith("/")) {
            add = add.substring(1);
        }
        if (add != null && add.startsWith("../")) {
            add = add.replace("../", "");
            if (temp.lastIndexOf("\\") != -1) {
                temp = temp.substring(0, temp.lastIndexOf("\\"));
            }
        }
        path.delete(0, path.length());
        path.append(String.format("%s/%s", temp, add));
    }

    public static void main(String[] args) {
        StringBuilder path = new StringBuilder("E:\\56top\\saas\\saas-basic");
        backDir(path, "../saas-basic-model/src/main/java");
        backDir(path, "com/cy/saas/basic/model/po");
        System.out.println(path.toString());
    }

}
