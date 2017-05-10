package com.cy.common.util;

import com.cy.common.exception.SystemException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zxj on 2017/5/10.
 */
public class WebUtil {

    public static void writeToJson(HttpServletResponse response, Object obj) {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "application/json");
        try {
            PrintWriter out = response.getWriter();
            out.write(JsonUtil.toJsonStr(obj));
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new SystemException("写json数据失败", e);
        }
    }

}
