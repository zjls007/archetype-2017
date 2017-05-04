package com.cy.resolver;

import com.cy.MysqlRead;
import com.cy.api.GenerateConfig;
import com.cy.api.JdbcConnectionFactory;
import com.cy.model.Table;
import com.cy.util.PathUtil;

import java.io.File;
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
            MysqlRead mysqlRead = new MysqlRead(jdbcConnectionFactory);
            for (String tableName : generateConfig.tableName.split(",")) {
                Table table = mysqlRead.getTable(tableName);
                if (generateConfig.genModel) {
                    JavaModelResolver resolver = new JavaModelResolver();
                    resolver.gen(table, PathUtil.getModelPath(baseDir.getAbsolutePath(), tableName));
                }
                if (generateConfig.genDao) {
                    JavaDaoResolver resolver = new JavaDaoResolver();
                    resolver.gen(table, PathUtil.getDaoPath(baseDir.getAbsolutePath(), tableName));
                }
                if (generateConfig.genMapper) {
                    XmlMapperResolver resolver = new XmlMapperResolver();
                    resolver.gen(table, PathUtil.getMapperPath(baseDir.getAbsolutePath(), tableName));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
