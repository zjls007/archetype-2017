package com.cy.common.util;

import com.cy.common.exception.SystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by zxj on 2017/5/8.
 */
public class JsonUtil {

    public static String toJsonStr(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new SystemException("生成json字符串出错", e);
        }
    }

}
