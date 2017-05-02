package com.cy.com.cy;

//import com.cy.common.MybatisConfig;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by zxj on 2017/3/8.
 */
public class MysqlRead {

    public void a() throws Exception {
//        Class.forName(MybatisConfig.JDBC_CLASS_NAME).newInstance();
//        Connection conn = DriverManager.getConnection(MybatisConfig.JDBC_URL, MybatisConfig.JDBC_USERNAME, MybatisConfig.JDBC_PASSWORD);
//        DatabaseMetaData dbmd = conn.getMetaData();
//        ResultSet rs = dbmd.getTables(null, "%", MybatisConfig.TABLE_NAME, new String[] {"TABLE"});
//        if (!rs.next()) {
//            throw new RuntimeException(String.format("表[%s]不存在!", MybatisConfig.TABLE_NAME));
//        }
//        rs = dbmd.getColumns(null, "%", MybatisConfig.TABLE_NAME, "%");
//        while (rs.next()) {
//            System.out.print(rs.getString("COLUMN_NAME")+ "  ");
//            System.out.println(rs.getString("TYPE_NAME"));
//        }
//        System.out.println(rs);
    }


    public static void main(String[] args) throws Exception {
        MysqlRead a = new MysqlRead();
        a.a();
    }

}
