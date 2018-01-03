package com.cy.web.controller.front;

import com.cy.common.util.ParamUtil;
import com.cy.common.util.RSAUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxj on 2018/1/2.
 */
@RequestMapping("signature")
@Controller
public class SignatureController {

    /**
     * MAP key: 客户id
     *     value: MAP （存放rsa公钥私钥）
     */
    public static Map<String, Map<String, String>> MAP = new HashMap<String, Map<String, String>>();

    /**
     * 获取rsa公钥私钥
     * @param appId 客户id
     */
    public Map<String, String> getByAppId(String appId) {
        if (!MAP.containsKey(appId)) {
            MAP.put(appId, RSAUtil.genKeyPair());
        }
        return MAP.get(appId);
    }

    @RequestMapping("index")
    public String index() {
        return "signature/index";
    }

    /**
     * 客户方获取请求签名
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("getSign")
    @ResponseBody
    public Object getSign(HttpServletRequest request) throws Exception {
        Map<String,String> map = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String s = parameterNames.nextElement();
            map.put(s, request.getParameter(s));
        }
        // 公共参数
        Map<String, String> publicMap = new HashMap<>();
        publicMap.put("datetime", Long.toString(System.currentTimeMillis()));
        publicMap.put("version", "v1");
        map.putAll(publicMap);
        String param = ParamUtil.createLinkString(ParamUtil.paraFilter(map), false);

        publicMap.put("sign", RSAUtil.sign(param, getByAppId(map.get("appId")).get(RSAUtil.PRIVATE_KEY), "UTF-8"));
        return publicMap;
    }

    /**
     * 服务端验签
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("serviceIntercept")
    @ResponseBody
    public String serviceIntercept(HttpServletRequest request) throws Exception {
        Map<String,String> map = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String s = parameterNames.nextElement();
            map.put(s, request.getParameter(s));
        }
        String param = ParamUtil.createLinkString(ParamUtil.paraFilter(map), false);

        if (RSAUtil.verify(param, map.get("sign"), getByAppId(map.get("appId")).get(RSAUtil.PUBLIC_KEY), "UTF-8")) {
            return "验签成功!";
        } else {
            return "验签失败!";
        }
    }

}
