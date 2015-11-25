package com.kinglong;

import com.kinglong.db.Conn;
import com.kinglong.generator.Generator;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by chenjinlong on 15/9/7.
 */
public class Gen4Python {
    public static void main(String[] args) {
        try {
            new Generator().generateSingle4Python();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Conn.factory().getConn().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
