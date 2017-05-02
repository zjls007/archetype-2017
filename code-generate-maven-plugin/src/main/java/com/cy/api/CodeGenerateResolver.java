package com.cy.api;

import java.io.File;
import java.io.IOException;

/**
 * Created by zxj on 2017/5/2.
 */
public class CodeGenerateResolver {

    private JdbcConnectionFactory jdbcConnectionFactory;

    private File baseDir;

    public CodeGenerateResolver(JdbcConnectionFactory jdbcConnectionFactory, File baseDir) {
        this.jdbcConnectionFactory = jdbcConnectionFactory;
        this.baseDir = baseDir;
    }

    public void generate() {
        try {
            System.out.println(jdbcConnectionFactory.getConnection());
            File f = new File(baseDir.getAbsolutePath() + File.separator + "aa.txt");
            System.out.println(f.getAbsoluteFile());
            f.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
