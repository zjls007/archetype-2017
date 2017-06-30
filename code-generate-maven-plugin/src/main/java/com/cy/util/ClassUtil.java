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
                        } else if (c.equals(Long.class)) {
                            sb.append("1l");
                        } else if (c.equals(Byte.class)) {
                            sb.append("(byte)1");
                        } else if (c.equals(BigDecimal.class)) {
                            sb.append("BigDecimal.ZERO");
                        } else if (c.equals(Date.class)) {
                            sb.append("new Date()");
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
