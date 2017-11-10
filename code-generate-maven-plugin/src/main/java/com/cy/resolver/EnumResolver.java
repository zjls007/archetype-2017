package com.cy.resolver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zxj on 2017/11/10.
 */
public class EnumResolver {


    public static void main(String[] args) {
        String remark = "数据来源（DriverApp-司机app定位、GPS-GPS定位、LBS-LBS定位）";
        remark = "数据来源(DriverApp-司机app定位、GPS-GPS定位、LBS-LBS定位)";
        Pattern compile = Pattern.compile("(\\(.*?\\))");
        Matcher matcher = compile.matcher(remark);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        } else {
            System.out.println("no Match!");
        }
    }

}
