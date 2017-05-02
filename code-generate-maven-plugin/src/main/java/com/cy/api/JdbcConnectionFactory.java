package com.cy.api;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by zxj on 2017/5/2.
 */
public class JdbcConnectionFactory {

    private Properties p;

    public JdbcConnectionFactory(Properties p) {
        this.p = p;
    }

    public Connection getConnection() {
        try {
            Class.forName(p.getProperty("jdbc.className")).newInstance();
            Connection conn = DriverManager.getConnection(
                    p.getProperty("jdbc.url"),
                    p.getProperty("jdbc.username"),
                    p.getProperty("jdbc.password"));
            return conn;
        } catch (Exception e) {
            throw new RuntimeException("获取数据库连接异常", e);
        }
    }

}
