package com.cy.api;

import com.cy.MysqlRead;
import com.cy.model.Table;
import com.cy.resolver.JavaModelResolver;
import javafx.scene.control.Tab;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by zxj on 2017/5/2.
 */
public class CodeGenerateResolver {

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
            GenerateConfig generateConfig = GenerateConfig.getInstance(p);
            System.out.println(generateConfig.genModel.getClass());
            MysqlRead mysqlRead = new MysqlRead(jdbcConnectionFactory);
            Table table = mysqlRead.getTable("t_why_table");
            JavaModelResolver javaModelResolver = new JavaModelResolver();
            javaModelResolver.gen(table, p, baseDir.getAbsolutePath() + File.separator + "aa.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
