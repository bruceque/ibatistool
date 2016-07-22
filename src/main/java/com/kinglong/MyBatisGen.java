package com.kinglong;

import com.kinglong.db.Conn;
import com.kinglong.generator.Generator;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by chenjinlong on 15/6/5.
 */
public class MyBatisGen {
    public static void main(String[] args) {
        try {
            //不生产扩展接口和xml
            boolean noGenerateExt = true;
            new Generator().generate4SingleTable(noGenerateExt);
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
