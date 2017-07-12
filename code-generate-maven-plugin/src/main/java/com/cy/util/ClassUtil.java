package com.cy.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zxj on 2017/6/30.
 */
public class ClassUtil {

    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.cy.entity.UserInfo");
        List<String> list = getSetMethodInvoke(clazz, "entity");
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static List<String> setBySameGet(Class<?> originClazz, String originPrefix, Class<?> targetClazz, String targetPrefix) {
        List<String> list = new ArrayList<String>();
        // 可以获得有序的字段(method方法获取时是无序的)
        Field[] fields = originClazz.getDeclaredFields();
        for (Field f : fields) {
            try {
                PropertyDescriptor pd = new PropertyDescriptor(f.getName(), originClazz);
                Method method = pd.getWriteMethod();
                if (method != null) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    StringBuilder sb = new StringBuilder();
                    if (originPrefix != null && !originPrefix.isEmpty()) {
                        sb.append(originPrefix).append(".");
                    }
                    sb.append(method.getName());
                    sb.append("(");
                    sb.append(targetPrefix);
                    sb.append(".");
                    sb.append(get(targetClazz, f));
                    sb.append(");");
                    list.add(sb.toString());
                }
            } catch (IntrospectionException e) {
                // 没有get,set方法的字段跳过
            }
        }
        return list;
    }

    private static String get(Class<?> clazz, Field f) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(f.getName())) {
                String r = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1) + "()";
                if (field.getType().equals(f.getType())) {
                    return r;
                } else {
                    return r + "类型不匹配";
                }
            }
        }
        return "";
    }


    public static List<String> getSetMethodInvoke(Class<?> clazz, String prefix) {
        List<String> list = new ArrayList<String>();
        // 可以获得有序的字段(method方法获取时是无序的)
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            try {
                PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
                Method method = pd.getWriteMethod();
                if (method != null) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    StringBuilder sb = new StringBuilder();
                    if (prefix != null && !prefix.isEmpty()) {
                        sb.append(prefix).append(".");
                    }
                    sb.append(method.getName());
                    sb.append("(");
                    int i = 0;
                    for (Class<?> c : parameterTypes) {
                        if (i++ > 0) {
                            sb.append(", ");
                        }
                        if (c.equals(String.class)) {
                            sb.append("\"test\"");
                        } else if (c.equals(Integer.class)) {
                            sb.append("1");
                        } else if (c.equals(Long.class)) {
                            sb.append("1l");
                        } else if (c.equals(Byte.class)) {
                            sb.append("(byte)1");
                        } else if (c.equals(BigDecimal.class)) {
                            sb.append("BigDecimal.ZERO");
                        } else if (c.equals(Date.class)) {
                            sb.append("new Date()");
                        } else if (c.equals(Double.class)) {
                            sb.append("0D");
                        } else {
                            sb.append(String.format("不支持的类型【%s】", c.getName()));
                        }
                    }
                    sb.append(");");
                    list.add(sb.toString());
                }
            } catch (IntrospectionException e) {
                // 没有get,set方法的字段跳过
            }
        }
        return list;
    }

}
