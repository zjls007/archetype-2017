package com.cy;

//import com.cy.common.MybatisConfig;

import com.cy.api.JdbcConnectionFactory;
import com.cy.model.Column;
import com.cy.model.Table;
import com.cy.util.FreeMarkerUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/3/8.
 */
public class MysqlRead {

    public MysqlRead(JdbcConnectionFactory jdbcConnectionFactory) {
        this.jdbcConnectionFactory = jdbcConnectionFactory;
    }

    JdbcConnectionFactory jdbcConnectionFactory;

    public Table getTable(String tableName) throws Exception {
        Connection conn = jdbcConnectionFactory.getConnection();
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rs = dbmd.getTables(null, "%", tableName, new String[] {"TABLE"});
        if (!rs.next()) {
            throw new RuntimeException(String.format("表[%s]不存在!", tableName));
        }

        Table table = new Table();
        table.setName(tableName);
        table.setPrimaryKeyName(getPrimaryKey(conn, dbmd, tableName));
        table.setColumnList(getColumns(dbmd, tableName));
        return table;
    }

    private List<Column> getColumns(DatabaseMetaData dbmd, String tableName) throws Exception {
        List<Column> list = new ArrayList<Column>();
        ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
        while (rs.next()) {
            Column column = new Column();
            column.setName(rs.getString("COLUMN_NAME"));
            column.setType(rs.getString("TYPE_NAME"));
            column.setNotNull(rs.getString("NULLABLE").equals("0"));
            column.setRemark(rs.getString("REMARKS"));
            list.add(column);
        }
        return list;
    }

    public String getPrimaryKey(Connection conn, DatabaseMetaData dbmd, String tableName) throws Exception {
        ResultSet primaryKeyResultSet = dbmd.getPrimaryKeys(conn.getCatalog(), null, tableName);
        while(primaryKeyResultSet.next()){
            String primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
            return primaryKeyColumnName;
        }
        return null;
    }

}
