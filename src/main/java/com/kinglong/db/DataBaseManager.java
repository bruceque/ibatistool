package com.kinglong.db;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjinlong on 15/6/5.
 */
public class DataBaseManager {
    /**
     *  获取所有的表
     *
     * @return
     * @throws SQLException
     */
    public static Connection conn = null;
    static {
        conn = Conn.factory().getConn();
    }
    public static List<String> getTables() throws SQLException {

        List<String> tables = new ArrayList<String>();
        PreparedStatement pstate = conn.prepareStatement("show tables");
        ResultSet results = pstate.executeQuery();
        while ( results.next() ) {
            String tableName = results.getString(1);
            //          if ( tableName.toLowerCase().startsWith("yy_") ) {
            tables.add(tableName);
            //          }
        }
        return tables;
    }

    /**
     *  获取所有的数据库表注释
     *
     * @return
     * @throws SQLException
     */
    public static Map<String, String> getTableComment() throws SQLException {
        Map<String, String> maps = new HashMap<String, String>();
        PreparedStatement pstate = conn.prepareStatement("show table status");
        ResultSet results = pstate.executeQuery();
        while ( results.next() ) {
            String tableName = results.getString("NAME");
            String comment = results.getString("COMMENT");
            maps.put(tableName, comment);
        }
        return maps;
    }

}
