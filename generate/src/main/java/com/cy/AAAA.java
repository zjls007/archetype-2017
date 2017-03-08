package com.cy;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by zxj on 2017/3/8.
 */
public class AAAA {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://172.16.1.8:3306/test?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull", "root", "123456");
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rs = dbmd.getTables(null, "%", "t_web_session", new String[] {"TABLE"});
        while (rs.next()) {
            System.out.println(rs.getString("TABLE_NAME"));
        }
        rs = dbmd.getColumns(null, "%", "t_web_session", "%");
        while (rs.next()) {
            System.out.print(rs.getString("COLUMN_NAME")+ "  ");
            System.out.println(rs.getString("TYPE_NAME"));
        }
        System.out.println(rs);
    }

}
