package com.cy.util;

import com.cy.resolver.NameResolver;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by zxj on 2017/5/3.
 */
public class PathUtil {

    public static String getModelPath(String basePath, String modelPackage, String tableName) {
        StringBuilder path = new StringBuilder();
        path.append(basePath);
        path.append(File.separator);
        path.append(modelPackage.replaceAll(".", File.separator));
        path.append(File.separator);
        path.append(NameResolver.getFieldName(tableName));
        return path.toString();
    }

}
