package com.cy.resolver;

//import com.cy.common.MybatisConfig;

import com.cy.model.Column;
import com.cy.model.Table;
import com.cy.util.JdbcConnectionFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxj on 2017/3/8.
 */
public class MysqlDaoResolver {

    public MysqlDaoResolver(JdbcConnectionFactory jdbcConnectionFactory) {
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
        table.setRemark(getTableRemark(conn, tableName));
        table.setPrimaryKeyName(getPrimaryKey(conn, dbmd, tableName));
        table.setColumnList(getColumns(dbmd, tableName));
        List<String> uniKeySingleList = getUniKeySingleList(dbmd, tableName);
        table.setUniKeyList(getUniColumn(table.getColumnList(), uniKeySingleList));
        table.setUniKeyNameList(uniKeySingleList);
        return table;
    }

    private String getTableRemark(Connection conn, String tableName) throws Exception {
        PreparedStatement pst = conn.prepareStatement("SELECT TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ?");
        pst.setString(1, tableName);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }
        return "";
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

    private List<Column> getUniColumn(List<Column> columnList, List<String> uniKeyList) {
        List<Column> result = new ArrayList<Column>();
        for (String key : uniKeyList) {
            for (Column column : columnList) {
                if (column.getName().equalsIgnoreCase(key)) {
                    result.add(column);
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 获取单列唯一索引，排除主键，排除唯一索引中有多个字段
     * @return
     */
    private List<String> getUniKeySingleList(DatabaseMetaData dbmd, String tableName) throws Exception {
        List<String> result = new ArrayList<String>();

        List<String> temp = new ArrayList<String>();
        ResultSet rs =  dbmd.getIndexInfo(null, null, tableName, true, false );
        while (rs.next()) {
            String indexName = rs.getString("INDEX_NAME");
            if (!"PRIMARY".equalsIgnoreCase(indexName)) {
                String colunmName = rs.getString("COLUMN_NAME");
                temp.add(String.format("%s:%s", indexName, colunmName));
            }
        }
        for (String i : temp) {
            int count = 0;
            for (String j : temp) {
                if (i.substring(0, i.indexOf(":")).equals(j.substring(0, j.indexOf(":")))) {
                    count++;
                }
            }
            if (count == 1) {
                result.add(i.substring(i.indexOf(":") + 1));
            }
        }
        return result;
    }

    public String getPrimaryKey(Connection conn, DatabaseMetaData dbmd, String tableName) throws Exception {
        ResultSet primaryKeyResultSet = dbmd.getPrimaryKeys(conn.getCatalog(), null, tableName);
        while(primaryKeyResultSet.next()){
            String primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
            return primaryKeyColumnName;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        MysqlDaoResolver mysqlDaoResolver = new MysqlDaoResolver(new JdbcConnectionFactory());
        mysqlDaoResolver.getTable("quote_price_section");
    }

}
