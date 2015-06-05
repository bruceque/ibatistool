package com.kinglong.db;

import com.kinglong.config.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        Class.forName(Config.DRIVER_NAME);
        conn = DriverManager.getConnection(Config.URL, Config.USER, Config.PASSWORD);
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
