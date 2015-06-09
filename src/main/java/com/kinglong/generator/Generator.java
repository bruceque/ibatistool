package com.kinglong.generator;

import com.kinglong.config.Config;
import com.kinglong.db.Conn;
import com.kinglong.db.DataBaseManager;
import com.kinglong.processor.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjinlong on 15/6/5.
 */
public class Generator {
    public void generate() throws ClassNotFoundException, SQLException, IOException {
        Connection conn = Conn.factory().getConn();
        String prefix = "show full fields from ";
        List<String> columns = null;
        List<String> types = null;
        List<String> comments = null;
        PreparedStatement pstate = null;
        List<String> tables = DataBaseManager.getTables();
        Map<String, String> tableComments = DataBaseManager.getTableComment();
        for ( String table : tables ) {
            columns = new ArrayList<String>();
            types = new ArrayList<String>();
            comments = new ArrayList<String>();
            pstate = conn.prepareStatement(prefix + table);
            ResultSet results = pstate.executeQuery();
            while ( results.next() ) {
                columns.add(results.getString("FIELD"));
                types.add(results.getString("TYPE"));
                comments.add(results.getString("COMMENT"));
            }
            BaseProcessor.TABLE_NAME = table;
            BaseProcessor.processTable(table);
            //          this.outputBaseBean();
            String tableComment = tableComments.get(BaseProcessor.TABLE_NAME);
            BeanQueryParamProcessor.buildBeanQueryParam(columns,types,comments,tableComment);
            EntityProcessor.buildEntityBean(columns, types, comments, tableComment);
            MapperProcessor.buildMapper();
            MapperXmlProcessor.buildMapperXml(columns, types, comments);
        }
        conn.close();
    }

    public void generate4SingleTable() throws ClassNotFoundException,SQLException,IOException {
        String tableName = Config.TABLE;
        Connection conn = Conn.factory().getConn();
        String prefix = "show full fields from ";
        List<String> columns = new ArrayList<String>();
        List<String> types = new ArrayList<String>();
        List<String> comments = new ArrayList<String>();
        PreparedStatement pstate = conn.prepareStatement(prefix + tableName);
        Map<String, String> tableComments = DataBaseManager.getTableComment();
        ResultSet results = pstate.executeQuery();
        while ( results.next() ) {
            columns.add(results.getString("FIELD"));
            types.add(results.getString("TYPE"));
            comments.add(results.getString("COMMENT"));
        }
        BaseProcessor.TABLE_NAME = tableName;
        BaseProcessor.processTable(tableName);
        String tableComment = tableComments.get(BaseProcessor.TABLE_NAME);
//        BeanQueryParamProcessor.buildBeanQueryParam(columns, types, comments, tableComment);
        EntityProcessor.buildEntityBean(columns, types, comments, tableComment);
        MapperProcessor.buildMapper();
        MapperXmlProcessor.buildMapperXml(columns, types, comments);
    }
}
