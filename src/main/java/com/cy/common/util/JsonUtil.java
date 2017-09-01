package com.cy.common.util;

import com.cy.common.exception.SystemException;
import com.cy.entity.system.MenuInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static <T> T toBean(String jsonStr, Class<T> valueType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, valueType);
    }

}
