package com.cy.util;

import com.cy.api.GenerateConfig;
import com.cy.resolver.NameResolver;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by zxj on 2017/5/3.
 */
public class PathUtil {



    public static String getModelPath(String basePath) {
        GenerateConfig generateConfig = GenerateConfig.getInstance();
        StringBuilder path = new StringBuilder();
        path.append(basePath);
        if (generateConfig.modelTargetProject != null && !generateConfig.modelTargetProject.isEmpty()) {
            path.append(File.separator);
            path.append(generateConfig.modelTargetProject.replaceAll("\\.", "/"));
        }
        path.append(File.separator);
        path.append(generateConfig.modelPackage.replaceAll("\\.", "/"));
        path.append(File.separator);
        path.append(NameResolver.getJavaClassName(generateConfig.tableName));
        path.append(".java");
        return path.toString();
    }

}
