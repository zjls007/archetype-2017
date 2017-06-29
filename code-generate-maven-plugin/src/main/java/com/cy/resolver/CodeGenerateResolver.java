package com.cy.resolver;

import com.cy.api.GenerateConfig;
import com.cy.api.JdbcConnectionFactory;
import com.cy.model.Table;
import com.cy.util.PathUtil;

import java.io.*;
import java.nio.charset.CharsetDecoder;
import java.util.Properties;

/**
 * Created by zxj on 2017/5/2.
 */
public class CodeGenerateResolver {

    GenerateConfig generateConfig = GenerateConfig.getInstance();

    private Properties p;

    private JdbcConnectionFactory jdbcConnectionFactory;

    private File baseDir;

    public CodeGenerateResolver(Properties p, File baseDir) {
        this.p = p;
        this.jdbcConnectionFactory = new JdbcConnectionFactory(p);
        this.baseDir = baseDir;
    }

    public void generate() {
        try {
            MysqlDaoResolver mysqlDaoResolver = new MysqlDaoResolver(jdbcConnectionFactory);
            for (String tableName : generateConfig.tableName.split(",")) {
                Table table = mysqlDaoResolver.getTable(tableName);
                ObjectResolver resolver = new ObjectResolver();
                if (generateConfig.genModel) {
                    resolver.gen(p, table, PathUtil.getModelPath(baseDir.getAbsolutePath(), tableName), "javaModel.ftl", true);
                }
                if (generateConfig.genDao) {
                    resolver.gen(p, table, PathUtil.getDaoPath(baseDir.getAbsolutePath(), tableName), "javaDao.ftl", false);
                }
                if (generateConfig.genMapper) {
                    resolver.gen(p, table, PathUtil.getMapperPath(baseDir.getAbsolutePath(), tableName), "xmlMapper1.ftl", false);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Properties p = new Properties();
        try {
            p.load(new InputStreamReader(new FileInputStream("E:\\56top\\code\\archetype-2017\\src\\main\\resources\\code-generate.properties"), "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException("加载配置文件出错", e);
        }

        JdbcConnectionFactory jdbcConnectionFactory = new JdbcConnectionFactory();
        MysqlDaoResolver mysqlDaoResolver = new MysqlDaoResolver(jdbcConnectionFactory);
        Table table = mysqlDaoResolver.getTable("t_finance_pay_receive");
        ObjectResolver resolver = new ObjectResolver();
        resolver.gen(p, table, "D:/aaa/FinancePayReceive.java", "javaModel.ftl", true);
        resolver.gen(p, table, "D:/aaa/FinancePayReceiveDao.java", "javaDao.ftl", false);
        resolver.gen(p, table, "D:/aaa/t_finance_pay_receive.xml", "xmlMapper1.ftl", false);
        System.out.println(table);
    }
}
