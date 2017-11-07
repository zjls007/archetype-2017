package com.cy.resolver;

//import com.cy.common.MybatisConfig;

import com.cy.model.Column;
import com.cy.model.Table;
import com.cy.util.JdbcConnectionFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

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
        Map<String, List<String>> unqKeyMap = getIndexKey(dbmd, tableName, true);
        table.setUniKeyMap(unqKeyMap);
        Map<String, List<String>> indexKeyMap = getIndexKey(dbmd, tableName, false);
        for (Map.Entry<String, List<String>> map : indexKeyMap.entrySet()) {
            if (unqKeyMap.containsKey(map.getKey())) {
                indexKeyMap.remove(map.getKey());
            }
        }
        table.setIndexKeyMap(indexKeyMap);
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

    private Map<String, List<String>> getIndexKey(DatabaseMetaData dbmd, String tableName, boolean unq) throws Exception {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        // 文档：unique 为boolean 型，当为真值时，返回具有唯一值的索引，而为假时，不论索引值是否唯一都返回；
        ResultSet rs =  dbmd.getIndexInfo(null, null, tableName, unq, false);
        while (rs.next()) {
            String indexName = rs.getString("INDEX_NAME");
            if (!"PRIMARY".equalsIgnoreCase(indexName)) {
                String colunmName = rs.getString("COLUMN_NAME");
                List<String> list = map.get(indexName);
                if (list == null) {
                    list = new ArrayList<String>();
                    map.put(indexName, list);
                }
                list.add(colunmName);
            }
        }
        return map;
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
        mysqlDaoResolver.getTable("t_waybill_cost");
    }

}
