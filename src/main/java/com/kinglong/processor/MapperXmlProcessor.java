package com.kinglong.processor;

import com.kinglong.config.Config;
import com.kinglong.processor.sql.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by chenjinlong on 15/6/5.
 */
public class MapperXmlProcessor extends BaseProcessor{
    public static List<SqlProcessor> sqlProcessorList = new ArrayList<SqlProcessor>();

    static {
        sqlProcessorList.add(new BaseResultMapSqlProcessor());
        sqlProcessorList.add(new BaseColumnListSqlProcessor());
        sqlProcessorList.add(new DeleteByPrimaryKeySqlProcessor());
        sqlProcessorList.add(new InsertSelectiveSqlProcessor());
        sqlProcessorList.add(new InsertSqlProcessor());
        sqlProcessorList.add(new SelectByPrimaryKeySqlProcessor());
        sqlProcessorList.add(new UpdateByPrimaryKey());
        sqlProcessorList.add(new UpdateByPrimaryKeySelective());
    }
    /**
     *  构建实体类映射XML文件
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    public static void buildMapperXml(List<String> columns, List<String> types, List<String> comments) throws IOException {

        File folder = new File(Config.XML_PATH);
        if ( !folder.exists() ) {
            folder.mkdirs();
        }

        File mapperXmlFile = new File(Config.XML_PATH, MAPPER_NAME + ".xml");
        if(!mapperXmlFile.exists()) {
            mapperXmlFile.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bw.newLine();
        bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
        bw.newLine();
        bw.write("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        bw.newLine();
        bw.write("<mapper namespace=\"" + Config.MAPPER_PACKAGE + "." + MAPPER_NAME + "\">");
        bw.newLine();
        bw.newLine();


        // 下面开始写SqlMapper中的方法
        // this.outputSqlMapperMethod(bw, columns, types);
//        BaseSqlProcessor.buildSQL(bw, columns, types);
        for (SqlProcessor sqlProcessor:sqlProcessorList) {
            sqlProcessor.buildSQL(bw,columns,types,comments);
        }

        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }
}
