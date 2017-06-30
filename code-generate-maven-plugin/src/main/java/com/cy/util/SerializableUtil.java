package com.cy.util;

import com.itranswarp.compiler.JavaStringCompiler;

import java.io.ObjectStreamClass;
import java.util.Map;

/**
 * Created by zxj on 2017/6/6.
 */
public class SerializableUtil {

    /**
     *
     * @param javaFileName 如：UserInfo.java
     * @param source java源码
     * @param javafullName 如：com.cy.entity.UserInfo
     * @return
     */
    public static Long getSerialVersionUID(String javaFileName, String source, String javafullName) {
        try {
            Class<?> clazz = getCalssBySource(javaFileName, source, javafullName);
            ObjectStreamClass objectStreamClass = ObjectStreamClass.lookup(clazz);
            return objectStreamClass.getSerialVersionUID();
        } catch (Exception e) {
            throw new RuntimeException("生成序列号id出错!", e);
        }
    }

    public static Long getSerialVersionUID(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        try {
            ObjectStreamClass objectStreamClass = ObjectStreamClass.lookup(clazz);
            return objectStreamClass.getSerialVersionUID();
        } catch (Exception e) {
            throw new RuntimeException("生成序列号id出错!", e);
        }
    }

    /**
     *
     * @param javaFileName 如：UserInfo.java
     * @param source java源码
     * @param javafullName 如：com.cy.entity.UserInfo
     * @return
     */
    public static Class<?> getCalssBySource(String javaFileName, String source, String javafullName) {
        try {
            JavaStringCompiler compiler = new JavaStringCompiler();
            Map<String, byte[]> results = compiler.compile(javaFileName, source);
            Class<?> clazz = compiler.loadClass(javafullName, results);
            return clazz;
        } catch (Exception e) {
            throw new RuntimeException("生成Class出错!", e);
        }
    }

}
