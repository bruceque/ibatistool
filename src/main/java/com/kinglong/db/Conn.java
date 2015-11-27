package com.kinglong.db;

import com.kinglong.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by chenjinlong on 15/6/5.
 */
public class Conn {

    private Connection conn = null;

    private static Conn instance = null;

    private Conn() {
        try {
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conn factory() {
        if (instance==null) {
            instance = new Conn();
        }
        return instance;
    }

    private void init() throws ClassNotFoundException, SQLException {

        Properties jdbcProperties = new Properties();
        //Config.DB_PROPERTIES
        InputStream jdbcIn = ClassLoader.getSystemResourceAsStream(Config.DB_PROPERTIES);
        try {
            jdbcProperties.load(jdbcIn);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        String db_name = jdbcProperties.getProperty("db_name");
        //数据库的参数初始化
        String driver_name  = jdbcProperties.getProperty("driver");
        if (driver_name == null) {
            driver_name = "com.mysql.jdbc.Driver";
        }
        String user = jdbcProperties.getProperty("user");
        String password = jdbcProperties.getProperty("password");
        String db_url = jdbcProperties.getProperty("db_url");

        String db_charset = jdbcProperties.getProperty("db_charset");
        if (db_charset == null) {
            db_charset = "UTF-8";
        }
        String url = String.format("%s%s?characterEncoding=%s",db_url,db_name,db_charset);


        Class.forName(driver_name);
        conn = DriverManager.getConnection(url, user, password);
    }


    public Connection getConn() {
        return conn;
    }

    public void close() throws SQLException {
        if (!conn.isClosed()) {
            conn.isClosed();
        }
        instance = null;

    }


}
