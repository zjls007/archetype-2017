package com.cy.web.controller.front;

import com.cy.common.util.RSAUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxj on 2018/1/2.
 */
@RequestMapping("signature")
@Controller
public class SignatureController {

    /**
     * map key: 客户id
     *     value: map （存放rsa公钥私钥）
     */
    public static Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

    /**
     * 获取rsa公钥私钥
     * @param appId 客户id
     */
    public Map<String, String> getByAppId(String appId) {
        if (!map.containsKey(appId)) {
            map.put(appId, RSAUtil.genKeyPair());
        }
        return map.get(appId);
    }

    @RequestMapping("index")
    public String index() {
        return "signature/index";
    }

    @RequestMapping("clientRequest")
    public String clientRequest() {
        return "";
    }

}
