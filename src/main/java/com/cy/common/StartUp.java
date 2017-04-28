package com.cy.common;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxj on 2017/4/28.
 */
public class StartUp {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        String file = StartUp.class.getClassLoader().getResource("mybatis/generator/generatorConfig.xml").getFile();
        //直接获取generatorConfig.xml的文件路径 根据具体情况查看
        File configFile = new File(file);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}
